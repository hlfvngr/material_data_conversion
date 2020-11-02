package com.hgits.hotc;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hgits.hotc.dao.news.PicklistMaterialStorageRelateMapper;
import com.hgits.hotc.entity.news.*;
import com.hgits.hotc.entity.old.*;
import com.hgits.hotc.service.impl.news.*;
import com.hgits.hotc.service.impl.old.*;
import com.hgits.hotc.utils.DateUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static org.apache.commons.lang3.ClassUtils.getName;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransferData {

    /********************************** start 新服务 start *****************************/
    @Resource
    StorageServiceImpl storageService;
    @Resource
    SysUserServiceImpl sysUserService;
    @Resource
    SysDictServiceImpl sysDictService;
    @Resource
    SysDeptServiceImpl sysDeptService;
    @Resource
    SysRoleServiceImpl sysRoleService;
    @Resource
    SysUserRoleServiceImpl sysUserRoleService;
    @Resource
    TownServiceImpl townService;
    @Resource
    MaterialServiceImpl materialService;
    @Resource
    RequisitionServiceImpl requisitionService;
    @Resource
    RequisitionDetailServiceImpl requisitionDetailService;
    // 领料相关
    @Resource
    PicklistOverviewServiceImpl picklistOverviewService;
    @Resource
    PicklistRequisitionRelateServiceImpl picklistRequisitionRelateService;
    @Resource
    PicklistMaterialServiceImpl picklistMaterialService;
    @Resource
    PicklistMaterialStorageRelateServiceImpl picklistMaterialStorageRelateService;
    @Resource
    PicklistAuditServiceImpl picklistAuditService;
    // 借料相关
    @Resource
    BorrowOverviewServiceImpl borrowOverviewService;
    @Resource
    BorrowRequisitionRelateServiceImpl borrowRequisitionRelateService;
    @Resource
    BorrowMaterialServiceImpl borrowMaterialService;
    @Resource
    BorrowMaterialStorageRelateServiceImpl borrowMaterialStorageRelateService;
    @Resource
    BorrowAuditServiceImpl borrowAuditService;
    /********************************** end 新服务 end *****************************/

    /********************************** start 旧服务 start *****************************/
    @Resource
    OldUserServiceImpl oldUserService;
    @Resource
    OldUnitServiceImpl oldUnitService;
    @Resource
    OldRoleServiceImpl oldRoleService;
    @Resource
    OldUserRoleServiceImpl oldUserRoleService;
    @Resource
    OldStorageServiceImpl oldStorageService;
    @Resource
    OldBuildingmaterialsServiceImpl oldBuildingmaterialsService;
    @Resource
    OldBlackbuildingmaterialsServiceImpl oldBlackbuildingmaterialsService;
    @Resource
    OldMaterialsServiceImpl oldMaterialsService;
    // 领料相关
    @Resource
    OldClaimticketbranchServiceImpl oldClaimticketbranchService;
    @Resource
    OldMyviewServiceImpl oldMyviewService;
    @Resource
    OldAuditServiceImpl oldAuditService;
    @Resource
    OldDeliverysuppliesServiceImpl oldDeliverysuppliesService;
    // 借料相关
    @Resource
    OldBorrowMaterialFaServiceImpl oldBorrowMaterialFaService;
    @Resource
    OldBorrowMaterialServiceImpl oldBorrowMaterialService;
    @Resource
    OldBorrowCountdownServiceImpl oldBorrowCountdownService;

    /********************************** end 旧服务 end *****************************/

    List<Town> towns;
    List<SysDict> sysDicts;
    @Before
    public void loadDict() {
        // towns = townService.selectAll();
        sysDicts = sysDictService.selectAll();
    }

    private static final LocalDateTime GreenTime = LocalDateTime.parse("1970-01-01 00:00:00",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

    /**
     * 转换用户
     */
    @Test
    public void sysUserTransfer() {
        List<OldUser> oldUsers = oldUserService.selectAll();
        List<SysUser> sysUsers = new ArrayList<>();
        oldUsers.forEach(oldUser -> {
            SysUser sysUser = convertUserFromOld2New(oldUser);
            if(sysUser != null) {
                sysUsers.add(sysUser);
            }
        });
        sysUserService.saveAll(sysUsers);
    }

    private SysUser convertUserFromOld2New(OldUser oldUser) {
        SysUser sysUser = new SysUser();
        if (oldUser.getUnitid() == null)  {
            return null;
        }
        Long deptId = getDeptId(oldUser.getUnitid());
        if(deptId == null) {
            return null;
        }
        sysUser.setId(oldUser.getUid().longValue())
                .setName(oldUser.getLoginid())
                .setNickName(oldUser.getUsername())
                .setFiveoneId(oldUser.getFoid())
                //.setAvatar()
                .setPassword(oldUser.getPassword())
                .setSalt("")
                // .setEmail()
                .setMobile(oldUser.getPhone())
                .setCardId(oldUser.getIdcardnum())
                .setStatus(1)
                .setDeptId(deptId)
                .setMajor(getMajor(oldUser.getMajor()))  // 需要转换
                .setPicking("是".equals(oldUser.getIspicking()) ? 1 : 0) //
                .setCreateBy("admin")
                .setCreateTime(LocalDateTime.now())
                .setLastUpdateBy("admin")
                .setLastUpdateTime(LocalDateTime.now())
                .setDelFlag(0);
        return sysUser;
    }

    // 根据单位ID查询
    private Long getDeptId(Integer unitid) {
        OldUnit oldUnits = oldUnitService.selectByKey(unitid);
        List<SysDept> sysDepts = null;
        System.out.println(oldUnits);
        if (oldUnits != null) {
            String rank = oldUnits.getRank();
            String concreteunit = oldUnits.getConcreteunit();
            if (rank != null
                    && !"夏天队".equals(rank)
                    && !"".equals(rank)
                    && rank.endsWith("队")) {
                Example example = new Example(SysDept.class);
                Example.Criteria criteria = example.createCriteria();
                criteria.andEqualTo("name", rank);
                sysDepts = sysDeptService.selectByExample(example);
            } else if ((rank == null || "".equals(rank))
                    && concreteunit != null
                    && concreteunit.length() > 0
                    && !"西瓜皮肤".equals(concreteunit)
                    && !"省略号施工".equals(concreteunit)) {
                Example example = new Example(SysDept.class);
                Example.Criteria criteria = example.createCriteria();
                criteria.andEqualTo("name", concreteunit);
                sysDepts = sysDeptService.selectByExample(example);
            }
        }
        return sysDepts == null ? null : sysDepts.get(0).getId();
    }

    // 根据专业名称查询专业Id
    private String getMajor(String major) {
        StringBuilder sb = new StringBuilder();
        if (major != null && major.length() > 0) {
            String[] split = major.split(",");
            Arrays.asList(split).forEach(s -> {
                sysDicts.forEach(sysDict -> {
                    String label = sysDict.getLabel();
                    if(s.equals(label)) {
                        sb.append(sysDict.getId());
                        sb.append(",");
                    }
                });
            });
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    /**
     * 转换角色 role，由于是单表操作，直接在数据库执行了
     */

    /**
     * 转换用户和角色关系
     */
    @Test
    public void userRoleTransfer() {
        List<OldUserRole> oldUserRoles = oldUserRoleService.selectAll();
        List<SysUserRole> sysUserRoles = new ArrayList<>();
        oldUserRoles.forEach(oldUserRole -> {

            System.out.println(oldUserRole);
            String loginid = oldUserRole.getLoginid();
            Integer roleid = oldUserRole.getRoleid();

            Example example1 = new Example(SysUser.class);
            Example.Criteria criteria1 = example1.createCriteria();
            criteria1.andEqualTo("name", loginid);
            List<SysUser> sysUsers = sysUserService.selectByExample(example1);
            if(sysUsers == null || sysUsers.isEmpty())
                return;
            Long sysUserId = sysUsers.get(0).getId();

            OldRole oldRole = oldRoleService.selectByKey(roleid);
            String rolename = oldRole.getRolename();

            Example example3 = new Example(SysRole.class);
            Example.Criteria criteria3 = example3.createCriteria();
            criteria3.andEqualTo("remark", rolename);
            List<SysRole> sysRoles = sysRoleService.selectByExample(example3);
            Long roleId = sysRoles.get(0).getId();

            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setUserId(sysUserId)
                    .setRoleId(roleId)
                    .setCreateBy("admin")
                    .setCreateTime(LocalDateTime.now())
                    .setLastUpdateBy("admin")
                    .setLastUpdateTime(LocalDateTime.now());
            sysUserRoles.add(sysUserRole);
        });
        sysUserRoleService.saveAll(sysUserRoles);
    }

    /**
     * 转换物料，直接从storage表中提取了
     */

    /**
     * 转换库存
     */
    @Test
    public void storageTransfer() {
        List<OldStorage> oldStorages = oldStorageService.selectAll();
        System.out.println("旧系统库存系统数据条数：" + oldStorages.size());
        List<Storage> storageList = new ArrayList<>();
        oldStorages.forEach(oldStorage -> {
            Storage storage = convertStorageOld2New(oldStorage);
            if (storage != null) {
                storageList.add(storage);
            }
        });
        System.out.println("转换后数据条数：" + storageList.size());
        storageService.saveAll(storageList);
    }

    private Storage convertStorageOld2New(OldStorage oldStorage) {
        Example example = new Example(SysUser.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("nickName", oldStorage.getProjectLeader());
        List<SysUser> sysUsers = sysUserService.selectByExample(example);
        if (sysUsers == null || sysUsers.isEmpty()) {
            System.out.println(oldStorage.getProjectLeader() + "项目负责人未在系统中注册信息，转换失败！");
            return null;
        }
        Long projectLeaderId = sysUsers.get(0).getId();

        String team = oldStorage.getTeam();
        StringBuilder sb = new StringBuilder();
        if (team != null && !"".equals(team)) {
            String[] split = team.split(",");
            Arrays.asList(split).forEach(s -> {
                Long deptId = getDeptId(Integer.parseInt(s));
                sb.append(deptId);
                sb.append(",");
            });
            sb.deleteCharAt(sb.length() - 1);
        }

        Storage storage = new Storage();
        storage.setId(oldStorage.getUuid())
                .setSerialNo(oldStorage.getNumber())
                .setDemandSerialNo(oldStorage.getRequestNumber())
                .setPurchaseDemandSerialNo(oldStorage.getPurchaseRequestNumber())
                .setProjectName(oldStorage.getProjectName())
                .setWbsNo(oldStorage.getWbsNumber())
                .setProjectLeaderId(projectLeaderId.intValue())   //
                .setMaterialNo(oldStorage.getMaterialsNumber())        //
                .setBroughtQuantity(oldStorage.getBroughtNumber())
                .setTotalGoods(oldStorage.getTotalGoods())
                .setAlarmQuantity(oldStorage.getAlarm())
                .setCreateTime(oldStorage.getCreateTime())
                .setUpdateTime(oldStorage.getUpdateTime())
                .setCreateBy(oldStorage.getCreateBy())
                .setUpdateBy(oldStorage.getUpdateBy())
                .setOccupyQuantity(oldStorage.getOccupy())
                .setConstructionUnit(sb.toString());

        return storage;
    }

    /**
     * 转换申请单
     */
    @Test
    public void requisitionTransfer() {
        List<OldBuildingmaterials> oldBuildingmaterials = oldBuildingmaterialsService.selectAll();
        List<Requisition> requisitionList = new ArrayList<>();
        oldBuildingmaterials.forEach(oldBuilding -> {
            Requisition requisition = convertRequisitionOld2New(oldBuilding, 0);
            if (requisition != null) {
                requisitionList.add(requisition);
            }
        });
        System.out.println("旧系统库存系统数据条数：" + oldBuildingmaterials.size());
        System.out.println("转换后数据条数：" + requisitionList.size());
        List<OldBlackbuildingmaterials> oldBlackbuildingmaterials = oldBlackbuildingmaterialsService.selectAll();
        oldBlackbuildingmaterials.forEach(oldBlackBuilding -> {
            Requisition requisition = convertRequisitionOld2New(oldBlackBuilding, 1);
            if (requisition != null) {
                requisitionList.add(requisition);
            }
        });
        System.out.println("旧系统库存系统数据条数：" + oldBlackbuildingmaterials.size());
        System.out.println("转换后数据条数：" + requisitionList.size());
        requisitionService.saveAll(requisitionList);
    }

    private Requisition convertRequisitionOld2New(OldBuildingmaterials oldBuilding, int isBlack) {
        if ("s".equals(oldBuilding.getScene()) || "null".equals(oldBuilding.getScene())) {
            return null;
        }

        if ("null".equals(oldBuilding.getProjecttime())) {
            return null;
        }

        if (oldBuilding.getMajor() == null || "".equals(oldBuilding.getMajor())) {
            return null;
        }

        Integer deptId = getDeptIdByName(oldBuilding.getConstructionteam());
        String major = getMajor(oldBuilding.getMajor());
        String deliverytype = getDeliveryType(oldBuilding.getDeliverytype());
        Integer constructionType = getIdByLabel(oldBuilding.getConstruction());
        Integer constructionunit = getDeptIdByName(oldBuilding.getConstructionunit());
        Integer town = getTownId(oldBuilding.getTown());
        Integer scene = getIdByLabel(oldBuilding.getScene());
        Integer submitId = getSysUserId(oldBuilding.getSubmitname());
        Integer submitunit = getDeptIdByName(oldBuilding.getSubmitunit());
        String submitmajor = getMajor(oldBuilding.getSubmitmajor());

        Requisition requisition = new Requisition();
        requisition.setId(oldBuilding.getRequisition().longValue())
                .setDeliveryNo(oldBuilding.getDeliverynumber())
                .setDeliveryName(oldBuilding.getDeliveryname())
                .setDeliveryAdress(oldBuilding.getDeliveryadress())
                .setProjectTime(!"null".equalsIgnoreCase(oldBuilding.getProjecttime()) ? DateUtils.parseDate(oldBuilding.getProjecttime()) : GreenTime)
                .setConstructionTeam(deptId) //
                .setMajor(major != null && !"".equals(major) ? Integer.valueOf(major) : null)   //
                .setDeliveryType(deliverytype != null && !"".equals(deliverytype) ? Integer.valueOf(deliverytype) : null)   //
                .setDemandClaimant(oldBuilding.getDemandclaimant())
                .setEngineeringNo(oldBuilding.getEngineeringnumber())
                .setDemandClaimantPhone(oldBuilding.getDemandclaimantphone())
                .setSinglePointName(oldBuilding.getSinglepointname())
                .setConstructionType(constructionType)      //
                .setConstructionUnit(constructionunit)  //
                .setPortQuantity(oldBuilding.getPortnumber())
                .setTownId(town)   //
                .setArea(oldBuilding.getArea())
                .setTissue(oldBuilding.getTissue())
                .setProjectAnnualBatch(oldBuilding.getBatch())
                .setScene(scene)              //
                .setCost(oldBuilding.getCost())
                .setProjectName(oldBuilding.getProjectname())
                .setWbsNo(oldBuilding.getWesnumber())
                .setRemark(oldBuilding.getMemo())
                .setSubmitId(submitId)                             //
                .setSubmitName(oldBuilding.getSubmitname())
                .setSubmitUnit(submitunit)
                .setSubmitMajor(submitmajor)
                .setIsBlack(isBlack);
        return requisition;
    }

    private Requisition convertRequisitionOld2New(OldBlackbuildingmaterials oldBlackBuilding, int isBlack) {
        if ("s".equals(oldBlackBuilding.getScene()) || "null".equals(oldBlackBuilding.getScene())) {
            return null;
        }

        if ("null".equals(oldBlackBuilding.getProjecttime())) {
            return null;
        }

        if (oldBlackBuilding.getMajor() == null || "".equals(oldBlackBuilding.getMajor())) {
            return null;
        }

        Integer deptId = getDeptIdByName(oldBlackBuilding.getConstructionteam());
        String major = getMajor(oldBlackBuilding.getMajor());
        String deliverytype = getDeliveryType(oldBlackBuilding.getDeliverytype());
        Integer constructionType = getIdByLabel(oldBlackBuilding.getConstruction());
        Integer constructionunit = getDeptIdByName(oldBlackBuilding.getConstructionunit());
        Integer town = getTownId(oldBlackBuilding.getTown());
        Integer scene = getIdByLabel(oldBlackBuilding.getScene());
        Integer submitId = getSysUserId(oldBlackBuilding.getSubmitname());
        Integer submitunit = getDeptIdByName(oldBlackBuilding.getSubmitunit());
        String submitmajor = getMajor(oldBlackBuilding.getSubmitmajor());

        Requisition requisition = new Requisition();
        requisition.setId(oldBlackBuilding.getRequisition().longValue())
                .setDeliveryNo(oldBlackBuilding.getDeliverynumber())
                .setDeliveryName(oldBlackBuilding.getDeliveryname())
                .setDeliveryAdress(oldBlackBuilding.getDeliveryadress())
                .setProjectTime(!"null".equalsIgnoreCase(oldBlackBuilding.getProjecttime()) ? DateUtils.parseDate(oldBlackBuilding.getProjecttime()) : GreenTime)
                .setConstructionTeam(deptId) //
                .setMajor(major != null && !"".equals(major) ? Integer.valueOf(major) : null)   //
                .setDeliveryType(deliverytype != null && !"".equals(deliverytype) ? Integer.valueOf(deliverytype) : null)   //
                .setDemandClaimant(oldBlackBuilding.getDemandclaimant())
                .setEngineeringNo(oldBlackBuilding.getEngineeringnumber())
                .setDemandClaimantPhone(oldBlackBuilding.getDemandclaimantphone())
                .setSinglePointName(oldBlackBuilding.getSinglepointname())
                .setConstructionType(constructionType)      //
                .setConstructionUnit(constructionunit)  //
                .setPortQuantity(oldBlackBuilding.getPortnumber())
                .setTownId(town)   //
                .setArea(oldBlackBuilding.getArea())
                .setTissue(oldBlackBuilding.getTissue())
                .setProjectAnnualBatch(oldBlackBuilding.getBatch())
                .setScene(scene)              //
                .setCost(oldBlackBuilding.getCost())
                .setProjectName(oldBlackBuilding.getProjectname())
                .setWbsNo(oldBlackBuilding.getWesnumber())
                .setRemark(oldBlackBuilding.getMemo())
                .setSubmitId(submitId)                             //
                .setSubmitName(oldBlackBuilding.getSubmitname())
                .setSubmitUnit(submitunit)
                .setSubmitMajor(submitmajor)
                .setIsBlack(isBlack);
        return requisition;
    }

    // 根据用户名称获得相应Id
    private Integer getSysUserId(String submitname) {
        if (submitname != null && !"".equals(submitname)) {
            Example example = new Example(SysUser.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("nickName", submitname);
            List<SysUser> sysUsers = sysUserService.selectByExample(example);
            if (sysUsers != null && !sysUsers.isEmpty()) {
                return sysUsers.get(0).getId().intValue();
            } {
                System.out.println("用户：" + submitname + "没有找到相应的Id，返回null");
            }
        }
        return null;
    }
    // 根据镇分名称获得相应Id
    private Integer getTownId(String town) {
        if (town != null && !"".equals(town)) {
            Example example = new Example(Town.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("town", town);
            List<Town> towns = townService.selectByExample(example);
            if (towns != null && !towns.isEmpty()) {
                return towns.get(0).getId();
            } {
                System.out.println("镇分：" + town + "没有找到相应的Id，返回null");
            }
        }
        return null;
    }
    // 根据标签去数据库字典中获得相应的类型ID
    private Integer getIdByLabel(String label) {
        if (label != null && !"".equals(label)) {
            Example example = new Example(SysDict.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("label", label);
            List<SysDict> sysDicts = sysDictService.selectByExample(example);
            if (sysDicts != null && !sysDicts.isEmpty()) {
                return sysDicts.get(0).getId().intValue();
            } {
                System.out.println("名称：" + label + "没有找到相应的Id，返回null");
            }
        }
        return null;
    }
    // 根据交付类型查找对应的Id，但是数据库中这个字段都为空，所以直接返回null
    private String getDeliveryType(String deliverytype) {
        return null;
    }
    // 根据施工队的名称去查找对应的Id
    private Integer getDeptIdByName(String constructionteam) {
        if (constructionteam != null && !"".equals(constructionteam)) {
            Example example = new Example(SysDept.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("name", constructionteam);
            List<SysDept> sysDepts = sysDeptService.selectByExample(example);
            if (sysDepts != null && !sysDepts.isEmpty()) {
                return sysDepts.get(0).getId().intValue();
            } {
                System.out.println("施工队：" + constructionteam + "没有找到相应的Id，返回null");
            }
        }
        return null;
    }

    /**
     * 转换申请单详情
     */
    @Test
    public void requisitionDetailTransfer() {
        List<OldMaterials> oldMaterials = oldMaterialsService.selectAll();
        List<RequisitionDetail> requisitionDetails = new ArrayList<>();
        oldMaterials.forEach(oldMaterials1 -> {
            RequisitionDetail requisitionDetail = convertRequisitionDetailOld2New(oldMaterials1);
            if (requisitionDetail != null) {
                requisitionDetails.add(requisitionDetail);
            }
        });
        requisitionDetailService.saveAll(requisitionDetails);
    }

    private RequisitionDetail convertRequisitionDetailOld2New(OldMaterials oldMaterials1) {
        RequisitionDetail requisitionDetail = new RequisitionDetail();

        requisitionDetail.setId(oldMaterials1.getMid())
                .setMaterialName(oldMaterials1.getMname())
                .setMaterialQuantity(oldMaterials1.getNumber())
                .setDesignQuantity(oldMaterials1.getDesign())
                .setMaterialUnit(oldMaterials1.getUnit())
                .setRequisitionId(oldMaterials1.getRid())
                .setUpdateQuantity(oldMaterials1.getUpdateNumber())
                .setIsDisplay(oldMaterials1.getIsDisplay());
        return requisitionDetail;
    }

    /**
     * 重点：领料单、领料单详情、领料审核单及领料回退依据的转换
     */
    @Test
    public void picklistTransfer() {
        // 此Set用来记录哪个领料单下的项目已经生成了相应的记录，用来去重
        Set<String> picklistRequisitionStrSet = new HashSet<>();

        List<OldClaimticketbranch> oldClaimticketbranches = oldClaimticketbranchService.selectAll();
        List<PicklistOverview> picklistOverviews = new ArrayList<>();
        List<PicklistAudit> picklistAudits = new ArrayList<>();
        List<PicklistRequisitionRelate>  picklistRequisitionRelates = new ArrayList<>();
        List<PicklistMaterial> picklistMaterials = new ArrayList<>();
        List<PicklistMaterialStorageRelate> picklistMaterialStorageRelates = new ArrayList<>();

        oldClaimticketbranches.forEach(oldClaimticketbranch -> {
            if (null == oldClaimticketbranch.getTeam()
                    || "".equals(oldClaimticketbranch.getTeam())
                    || oldClaimticketbranch.getTeam().matches("\\d+")
                    || !oldClaimticketbranch.getPickingnumber().startsWith("LL")) {
                return;
            }
            // 总览对象转换
            PicklistOverview picklistOverview = createPicklistOverview(oldClaimticketbranch);
            if (picklistOverview != null) {
                picklistOverviews.add(picklistOverview);
            }
            // 审核对象转换
            PicklistAudit picklistAudit = createPicklistAudit(oldClaimticketbranch);
            if(picklistAudit != null) {
                picklistAudits.add(picklistAudit);
            }

            Example example = new Example(OldMyview.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("ccid", oldClaimticketbranch.getCid());
            List<OldMyview> oldMyviews = oldMyviewService.selectByExample(example);
            if (oldMyviews != null && !oldMyviews.isEmpty()) {
                oldMyviews.forEach(oldMyview -> {
                    // 转换成 PicklistRequisitionRelate 对象和 PicklistMaterial
                    Integer requisitionDetailId = oldMyview.getMid();// 申请单详情单ID
                    RequisitionDetail requisitionDetail = requisitionDetailService.selectByKey(requisitionDetailId);
                    Integer relateRequisitionId = requisitionDetail.getRequisitionId();
                    Requisition requisition = requisitionService.selectByKey(relateRequisitionId);
                    Long requisitionId = requisition.getId();
                    String picklistRequisitionStr = "" + oldClaimticketbranch.getCid() + requisitionId;
                    if (!picklistRequisitionStrSet.contains(picklistRequisitionStr)) {
                        picklistRequisitionStrSet.add(picklistRequisitionStr);
                        PicklistRequisitionRelate picklistRequisitionRelate = createPicklistRequisitionRelate(oldClaimticketbranch, oldMyview);
                        if (picklistRequisitionRelate != null) {
                            picklistRequisitionRelates.add(picklistRequisitionRelate);
                        } else {
                            System.out.println("申请单详情" + oldMyview.getCid() + "转换失败");
                            return;
                        }
                    }
                    PicklistMaterial picklistMaterial = createPicklistMaterial(oldClaimticketbranch, oldMyview, requisitionId);
                    if (picklistMaterial != null) {
                        picklistMaterials.add(picklistMaterial);
                    }
                });
            }

        });

        // 单独处理picklistMaterialStorageRelates领料详情和库存关系
        List<OldDeliverysupplies> oldDeliverysupplies = oldDeliverysuppliesService.selectAll();
        oldDeliverysupplies.forEach(supply -> {
            PicklistMaterialStorageRelate picklistMaterialStorageRelate = new PicklistMaterialStorageRelate();
            picklistMaterialStorageRelate.setId(supply.getId())
                    .setDecrNum(supply.getSum())
                    .setDecrTime(DateUtils.parseDate(checkDatePattern(supply.getOutmin())))
                    .setPicklistMaterialId(supply.getMyviweid())
                    .setStorageId(supply.getUuid());
            picklistMaterialStorageRelates.add(picklistMaterialStorageRelate);
        });

        picklistOverviewService.saveAll(picklistOverviews);
        picklistRequisitionRelateService.saveAll(picklistRequisitionRelates);
        picklistMaterialService.saveAll(picklistMaterials);
        picklistMaterialStorageRelateService.saveAll(picklistMaterialStorageRelates);
        picklistAuditService.saveAll(picklistAudits);
    }

    // 根据旧领料单和旧领料单详情生成最新的领料申请单关联表
    private PicklistRequisitionRelate createPicklistRequisitionRelate(OldClaimticketbranch oldClaimticketbranch, OldMyview oldMyview) {
        String deliverynumber = oldMyview.getDeliverynumber();
        Example example = new Example(Requisition.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("deliveryNo", deliverynumber);
        List<Requisition> requisitionList = requisitionService.selectByExample(example);

        if(requisitionList == null || requisitionList.size() != 1) {
            return null;
        }
        Requisition req = requisitionList.get(0);

        List<Storage> storageList = getStorageListByWbsNo(oldMyview.getWbsnumber());
        if (storageList == null || storageList.isEmpty()) {
            return null;
        }

        PicklistRequisitionRelate picklistRequisitionRelate = new PicklistRequisitionRelate();
            picklistRequisitionRelate
                    // .setId()
                    .setMajor(req.getMajor())
                    .setWbsNo(req.getWbsNo())
                    // 根据领料单的状态来进行转换
                    .setAuditStatus(getAuditStatus(oldClaimticketbranch.getState()))
                    .setTownId(req.getTownId())
                    .setDeliveryNo(req.getDeliveryNo())
                    // 拿wbsNo去查找相应的项目负责人
                    .setProjectLeaderId(storageList.get(0).getProjectLeaderId())
                    .setPicklistId(oldClaimticketbranch.getPickingnumber())
                    .setRequisitionId(req.getId().intValue());

        return picklistRequisitionRelate;
    }

    private Integer getAuditStatus(String state) {
        if (state == null || "".equals(state)) {
            throw new RuntimeException("状态不能为空");
        }
        switch (state) {
            case "1" : return 1;
            case "2" : return 2;
            case "3" : return 3;
            case "6" : return 6;
            case "7" : return 7;
            case "8" : return 8;
            case "10" : return 10;
            case "11" : return 11;
            case "12" : return 12;
            default: return Integer.valueOf(state);
        }
    }

    // 拿wbsNo去查找相应的库存列表
    private List<Storage> getStorageListByWbsNo(String wbsNo) {
        Example example = new Example(Storage.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("wbsNo", wbsNo);
        List<Storage> storageList = storageService.selectByExample(example);
        return storageList;
    }

    private PicklistMaterial createPicklistMaterial(OldClaimticketbranch oldClaimticketbranch, OldMyview oldMyview, Long requisitionId) {
        String materialName = oldMyview.getName();
        String fivenoeNo = materialService.selectMaterialFivenoeNoByName(materialName);
        PicklistMaterial picklistMaterial = new PicklistMaterial();
        picklistMaterial.setId(oldMyview.getCid())
                // .setMaterialNo()
                .setMaterialName(oldMyview.getName())
                .setMaterialQuantity(oldMyview.getNumber().intValue())
                .setMaterialUnit(oldMyview.getAssemblyitem())
                .setMaterialFivenoeNo(fivenoeNo)
                .setPicklistId(oldClaimticketbranch.getPickingnumber())
                .setRequisitionId(requisitionId.intValue())
                .setRequisitionDetailId(oldMyview.getMid());
        return picklistMaterial;
    }

    private PicklistAudit createPicklistAudit(OldClaimticketbranch oldClaimticketbranch) {
        String examine = checkDatePattern(oldClaimticketbranch.getExamine());
        String drawtime = checkDatePattern(oldClaimticketbranch.getDrawtime());

        String supervisortime = convertCheckTime(oldClaimticketbranch.getSupervisortime());
        String clienttime = convertCheckTime(oldClaimticketbranch.getClienttime());
        String purchasetime = convertCheckTime(oldClaimticketbranch.getPurchasetime());

        PicklistAudit picklistAudit = new PicklistAudit();
        picklistAudit
                .setState(getAuditStatus(oldClaimticketbranch.getState()))
                .setSubmitTime(DateUtils.parseDate(examine))
                .setEndTime(DateUtils.parseDate(drawtime))
                .setSupervisorTime(supervisortime)
                .setSupervisorSuggest(convertSuggest(oldClaimticketbranch.getSupervisorsuggest()))
                .setClientTime(clienttime)
                .setClientSuggest(convertSuggest(oldClaimticketbranch.getClientsuggest()))
                .setSupervisorMan(convertSignature(oldClaimticketbranch.getSupervisorman()))
                .setClientMan(convertSignature(oldClaimticketbranch.getClientman()))
                .setPurchaseMan(convertSignature(oldClaimticketbranch.getPurchaseman()))
                .setPurchaseSuggest(convertSuggest(oldClaimticketbranch.getPurchaseidea()))
                .setPurchaseTime(purchasetime)
                .setPicklistId(oldClaimticketbranch.getPickingnumber());
        return picklistAudit;
    }
    // 转换审核时间
    private String convertCheckTime(String timeStr) {
        String s = convertSignature(timeStr);
        StringBuilder stringBuilder = new StringBuilder();
        if (s != null) {
            String[] timeArr = s.split(",");
            for (int i = timeArr.length - 1; i >= 0; i--) {
                int index = timeArr[i].indexOf(":");
                stringBuilder.append(timeArr[i], index + 1, index + 20);
                stringBuilder.append(",");
            }
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            return stringBuilder.toString();
        }
        return null;
    }

    private String convertSignature(String signature) {
        if (signature == null
                || "".equals(signature)
                || "暂无".equals(signature)
                || signature.matches("[,|(暂无)]+")
                ) {
            return null;
        }
        String sign = null;
        while(!signature.equals(sign)) {
            sign = signature;
            if (signature.endsWith("暂无")) {
                signature = signature.substring(0, signature.length() - "暂无".length());
            }
            if (signature.endsWith(",")) {
                signature = signature.substring(0, signature.length() - ",".length());
            }
        }
        return signature;
    }

    // 将监理/客建的意见转化成json字符串
    private String convertSuggest(String suggests) {
        JSONArray allSuggest = new JSONArray();
        if (suggests == null
                || "".equals(suggests)
                || "暂无".equals(suggests)
                || "暂无，".equals(suggests)) {
            return allSuggest.toJSONString();
        }
        String[] suggestArr = suggests.split(",");
        for (int i = 0; i < suggestArr.length; i++) {
            String[] pair = suggestArr[i].split(":");
            JSONObject s = new JSONObject().fluentPut(pair[0].trim(), pair[1].trim());
            allSuggest.add(s);
        }
        return allSuggest.toJSONString();
    }

    private PicklistOverview createPicklistOverview(OldClaimticketbranch oldClaimticketbranch) {
        PicklistOverview picklistOverview = new PicklistOverview();
        Integer deptId = getDeptIdByName(oldClaimticketbranch.getDepartment());
        SysDept sysDept = getDeptByName(oldClaimticketbranch.getTeam());
        if (sysDept == null) {
            return null;
        }
        String parentIds = sysDept.getParentIds();

        String examine = checkDatePattern(oldClaimticketbranch.getExamine());
        String drawtime = checkDatePattern(oldClaimticketbranch.getDrawtime());
        String practicaltime = checkDatePattern(oldClaimticketbranch.getPracticaltime());

        LocalDateTime submitTime = DateUtils.parseDate(examine);
        LocalDateTime endTime = DateUtils.parseDate(drawtime);
        LocalDateTime actualPickTime = DateUtils.parseDate(practicaltime);
        Integer broughtResult = getBroughtResult(oldClaimticketbranch.getState());

        picklistOverview.setId(String.valueOf(oldClaimticketbranch.getPickingnumber()))
                .setSubmitId(oldClaimticketbranch.getConstruction())
                .setSubmitName(oldClaimticketbranch.getUsername())
                .setSubmitDepartment(deptId)
                .setConcreteUnit(parentIds.contains(",") ?
                        Integer.valueOf(parentIds.substring(0, parentIds.indexOf(","))) : Integer.valueOf(parentIds))
                .setConstructionTeam(sysDept.getId().intValue())
                .setSubmitTime(submitTime)
                .setEndTime(endTime)
                .setOutOfStackNo(oldClaimticketbranch.getOutnumber())
                .setActualPickTime(actualPickTime)
                .setBroughtResult(broughtResult) //
                .setPrintCount(0)
                .setCreateBy(oldClaimticketbranch.getConstruction())
                .setCreateTime(submitTime);
        return picklistOverview;
    }

    // 根据订单状态判断领取结果
    private Integer getBroughtResult(String state) {
        if (state == null || "".equals(state)) {
            throw new RuntimeException("领料单状态不能为空！");
        }
        switch (state) {
            case "6": return 0;
            case "8": return 2;
            case "10": return 1;
            default: return null;
        }
    }

    private SysDept getDeptByName(String team) {
        if (team != null && !"".equals(team)) {
            Example example = new Example(SysDept.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("name", team);
            List<SysDept> sysDepts = sysDeptService.selectByExample(example);
            if (sysDepts != null && !sysDepts.isEmpty()) {
                return sysDepts.get(0);
            } {
                System.out.println("施工队：" + team + "没有找到相应的Id，返回null");
            }
        }
        return null;
    }

    private String checkDatePattern(String dateStr) {
        if (dateStr == null || "".equals(dateStr) || !dateStr.matches("\\d{4}([-| |:]\\d{2}){5}")) {
            return null;
        }
        return dateStr;
    }

    /**
     * 重点：借料单、借料单详情、借料审核单及借料回退依据的转换
     */
    @Test
    public void borrowlistTransfer() {
        List<OldBorrowMaterialFa> oldBorrowMaterialFas = oldBorrowMaterialFaService.selectAll();

        List<BorrowOverview> borrowOverviews = new ArrayList<>();
        List<BorrowRequisitionRelate> borrowRequisitionRelates = new ArrayList<>();
        List<BorrowMaterial> borrowMaterials = new ArrayList<>();
        List<BorrowAudit> borrowAudits = new ArrayList<>();
        List<BorrowMaterialStorageRelate> borrowMaterialStorageRelates = new ArrayList<>();

        oldBorrowMaterialFas.forEach(fa -> {

            Example example = new Example(OldBorrowCountdown.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("billNo", fa.getBillno());
            List<OldBorrowCountdown> oldBorrowCountdowns = oldBorrowCountdownService.selectByExample(example);
            if (oldBorrowCountdowns != null && oldBorrowCountdowns.size() != 1) {
                return;
            }

            OldBorrowCountdown oldBorrowCountdown = CollectionUtils.isEmpty(oldBorrowCountdowns) ? null : oldBorrowCountdowns.get(0);
            BorrowOverview borrowOverview = createBorrowOverview(oldBorrowCountdown, fa);
            if (borrowOverview != null) {
                borrowOverviews.add(borrowOverview);
            }

            Example example1 = new Example(OldBorrowMaterial.class);
            Example.Criteria criteria1 = example1.createCriteria();
            criteria1.andEqualTo("billno", fa.getBillno());
            List<OldBorrowMaterial> oldBorrowMaterials = oldBorrowMaterialService.selectByExample(example1);

            // 此Set用来记录哪个借料单下的项目已经生成了相应的记录，用来去重
            Set<String> borrowRequisitionStrSet = new HashSet<>();
            Integer[] status = new Integer[1];
            StringBuilder supervisorTimes = new StringBuilder();
            JSONArray supervisorSuggests = new JSONArray();
            StringBuilder clientTimes = new StringBuilder();
            JSONArray clientSuggests = new JSONArray();
            StringBuilder clientMans = new StringBuilder();
            StringBuilder supervisorMans = new StringBuilder();

            oldBorrowMaterials.forEach(bm -> {

                status[0] = convertStatus(bm.getJlIsAgree(), bm.getKjIsAgree(), bm.getState());
                String supervisorTime = DateUtils.format(bm.getJlAuditingTime());
                supervisorTimes.append(supervisorTime);
                supervisorTimes.append(",");
                String supervisorMan = bm.getJlAuditingUser()== null ? "" : bm.getJlAuditingUser();
                supervisorMans.append(supervisorMan);
                supervisorMans.append(",");
                String supervisorSuggest = String.valueOf(bm.getJlIsAgree());
                JSONObject suggest = new JSONObject().fluentPut(supervisorMan, supervisorSuggest);
                supervisorSuggests.add(suggest);

                String clientTime = DateUtils.format(bm.getKjAuditingTime());
                clientTimes.append(clientTime);
                clientTimes.append(",");
                String clientMan = bm.getKjAuditingUser() == null ? "" : bm.getKjAuditingUser();
                clientMans.append(clientMan);
                clientMans.append(",");
                String clientSuggest = String.valueOf(bm.getKjIsAgree());
                JSONObject suggest1 = new JSONObject().fluentPut(clientMan, clientSuggest);
                clientSuggests.add(suggest1);


                OldMaterials oldMaterials = oldMaterialsService.selectByKey(bm.getMid());
                Integer rid = oldMaterials.getRid();
                OldBuildingmaterials oldBuildingmaterials = oldBuildingmaterialsService.selectByKey(rid);
                String borrowRequisitionStr = "" + fa.getBillno() + oldBuildingmaterials.getRequisition();
                if(!borrowRequisitionStrSet.contains(borrowRequisitionStr)){
                    BorrowRequisitionRelate borrowRequisitionRelate = creatBorrowRequisitionRelate(bm, oldBuildingmaterials, fa.getBillno());
                    if (borrowRequisitionRelate != null) {
                        borrowRequisitionRelates.add(borrowRequisitionRelate);
                        borrowRequisitionStrSet.add(borrowRequisitionStr);
                    }
                }

                // 生成借料详情记录
                BorrowMaterial borrowMaterial = createBorrowMaterial(bm, fa.getBillno(), rid);
                if (borrowMaterial != null) {
                    borrowMaterials.add(borrowMaterial);
                }

                // 生成借料详情与库存之间的关系
                if (oldBorrowCountdown != null) {
                    BorrowMaterialStorageRelate borrowMaterialStorageRelate = createBorrowMaterialStorageRelate(bm, oldBorrowCountdown);
                    if (borrowMaterialStorageRelate != null) {
                        borrowMaterialStorageRelates.add(borrowMaterialStorageRelate);
                    }
                }
            });

            BorrowAudit borrowAudit = new BorrowAudit();
            borrowAudit
                    // .setId()
                    .setState(status[0])  // 需根据所有项目的状态来设置
                    .setSubmitTime(fa.getCreateTime())
                    .setEndTime(oldBorrowCountdown == null ? null : oldBorrowCountdown.getEndDate())
                    .setSupervisorTime(supervisorTimes.deleteCharAt(supervisorTimes.length() - 1).toString())
                    .setSupervisorSuggest(supervisorSuggests.toJSONString())
                    .setClientTime(clientTimes.deleteCharAt(clientTimes.length() - 1).toString())
                    .setClientSuggest(clientSuggests.toJSONString())
                    .setClientMan(clientMans.deleteCharAt(clientMans.length() - 1).toString())
                    .setSupervisorMan(supervisorMans.deleteCharAt(supervisorMans.length() - 1).toString())
                    // .setPurchaseMan()
                    // .setPurchaseSuggest()
                    // .setPurchaseTime()
                    .setBorrowId(fa.getBillno());
            borrowAudits.add(borrowAudit);
        });

        borrowOverviewService.saveAll(borrowOverviews);
        borrowRequisitionRelateService.saveAll(borrowRequisitionRelates);
        borrowMaterialService.saveAll(borrowMaterials);
        borrowAuditService.saveAll(borrowAudits);
        borrowMaterialStorageRelateService.saveAll(borrowMaterialStorageRelates);

    }

    private Integer convertStatus(Integer jlIsAgree, Integer kjIsAgree, Integer state) {
        if (jlIsAgree == null) {
            jlIsAgree = 0;
        }
        if (kjIsAgree == null) {
            kjIsAgree = 0;
        }
        if (state == null) {
            state = 0;
        }
        String join = "" + jlIsAgree + kjIsAgree + state;

        switch (join) {
            case "000" : return 1;
            case "100" : return 2;
            case "200" : return 3;
            case "110" : return 6;
            case "120" : return 7;
            case "111" : return 10;
            // case "112" : return 11;
            default: return 1;
        }
    }

    private BorrowMaterialStorageRelate createBorrowMaterialStorageRelate(OldBorrowMaterial bm, OldBorrowCountdown oldBorrowCountdown) {
        BorrowMaterialStorageRelate borrowMaterialStorageRelate = new BorrowMaterialStorageRelate();
        borrowMaterialStorageRelate
                // .setId()
                .setDecrNum(bm.getBorrowQuantity())
                .setDecrTime(oldBorrowCountdown.getUpdateResultTime())
                .setBorrowMaterialId(bm.getId())
                .setStorageId(bm.getUuid());
        return borrowMaterialStorageRelate;
    }

    private BorrowMaterial createBorrowMaterial(OldBorrowMaterial bm, String borrowId, Integer requisitionId) {
        BorrowMaterial borrowMaterial = new BorrowMaterial();
        // 根据物料编码去查询物料名称
        List<String> materialNames = materialService.selectMaterialNameByMaterialNo(bm.getMaterialsNumber());
        String materialUnit = null;
        String fiveoneNo = null;
        for (int i = 0; i < materialNames.size(); i++) {
            String materialName = materialNames.get(i);
            materialUnit = materialService.selectMaterialUnitByName(materialName);
            if (materialUnit != null && !"".equals(materialUnit)) {
                break;
            }
        }

        for (int i = 0; i < materialNames.size(); i++) {
            String materialName = materialNames.get(i);
            fiveoneNo = materialService.selectMaterialFivenoeNoByName(materialName);
            if (fiveoneNo != null && !"".equals(fiveoneNo)) {
                break;
            }
        }

        String materialsName = bm.getMaterialsName();
        // int[] quantifier = findQuantifier(materialsName);
        if (materialUnit == null || "".equals(materialUnit)) {
            materialUnit = materialService.selectMaterialUnitByName(materialsName);
        }

        if (fiveoneNo == null || "".equals(fiveoneNo)) {
            fiveoneNo = materialService.selectMaterialFivenoeNoByName(materialsName);
            if(fiveoneNo == null) fiveoneNo = "";
        }

        System.out.println("材料名称：" + bm.getMaterialsName() + " 单位：" + materialUnit);
        borrowMaterial
                .setId(bm.getId())
                .setMaterialNo(bm.getMaterialsNumber())
                .setMaterialName(bm.getMaterialsName())
                .setMaterialQuantity(bm.getBorrowQuantity())
                .setMaterialUnit(materialUnit)
                .setBorrowId(borrowId)
                .setRequisitionId(requisitionId)
                .setRequisitionDetailId(bm.getMid())
                .setMaterialFivenoeNo(fiveoneNo);
        return borrowMaterial;
    }

    private BorrowRequisitionRelate creatBorrowRequisitionRelate(OldBorrowMaterial bm, OldBuildingmaterials oldBuildingmaterials, String borrowId) {
        String major = getMajor(bm.getMajor());
        Integer townId = getTownId(oldBuildingmaterials.getTown());
        Integer projectLeaderId = getSysUserId(bm.getProjectLeader());
        BorrowRequisitionRelate borrowRequisitionRelate = new BorrowRequisitionRelate();
        borrowRequisitionRelate
                //.setId()
                .setMajor(Integer.valueOf(major))
                .setWbsNo(bm.getWbsNumber())
                .setAuditStatus(bm.getState())
                .setTownId(townId)
                .setDeliveryNo(oldBuildingmaterials.getDeliverynumber())
                .setProjectLeaderId(projectLeaderId)
                .setBorrowId(borrowId)
                .setRequisitionId(oldBuildingmaterials.getRequisition());
        return borrowRequisitionRelate;
    }

    private BorrowOverview createBorrowOverview(OldBorrowCountdown oldBorrowCountdown, OldBorrowMaterialFa fa) {
        Integer submitId = getSysUserId(fa.getCreateBy());
        SysDept dept = getDeptByName(fa.getConstructionUnit());
        if (dept == null) {
            return null;
        }

        BorrowOverview borrowOverview = new BorrowOverview();
        borrowOverview.setId(fa.getBillno())
                .setSubmitId(submitId)  //
                .setSubmitName(fa.getCreateBy())
                .setSubmitDepartment(null) // 可删除数据库此字段
                .setConcreteUnit(dept.getParentId().intValue()) //
                .setConstructionTeam(dept.getId().intValue()) //
                .setSubmitTime(fa.getCreateTime())
                .setEndTime(oldBorrowCountdown == null ? null : oldBorrowCountdown.getEndDate())
                .setOutOfStackNo(oldBorrowCountdown == null ? null : oldBorrowCountdown.getOutboundNo())
                .setActualPickTime(oldBorrowCountdown == null ? null : oldBorrowCountdown.getUpdateResultTime())
                .setBroughtResult(oldBorrowCountdown == null ? null : oldBorrowCountdown.getResult())
                .setPrintCount(oldBorrowCountdown == null ? null : oldBorrowCountdown.getPrintCount())
                .setCreateBy(submitId)
                .setCreateTime(fa.getCreateTime());
        return borrowOverview;
    }

    // 寻找量词
    private int[] findQuantifier(String materialsName) {
        int lastIndex = materialsName.length() - 1;
        int i = materialsName.indexOf(" ");
        if (i != -1) {
            lastIndex = i;
        }
        i = materialsName.indexOf("（");
        if (i != -1) {
            lastIndex = i;
        }
        char[] arr = materialsName.substring(0, lastIndex).toCharArray();
        int start = -1;
        for (int j = 0; j < arr.length; j++) {
            if(('0'<= arr[j] && arr[j] <='9') || arr[j] == '.') {
                if (start == -1) {
                    start = j;
                }
            }
        }
        if (start == -1) {
            return null;
        }
        return new int[]{start, lastIndex};
    }
}
