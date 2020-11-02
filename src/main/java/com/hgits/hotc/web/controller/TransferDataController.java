package com.hgits.hotc.web.controller;

import com.hgits.hotc.entity.news.*;
import com.hgits.hotc.entity.old.OldStorage;
import com.hgits.hotc.entity.old.OldUnit;
import com.hgits.hotc.entity.old.OldUser;
import com.hgits.hotc.service.impl.news.*;
import com.hgits.hotc.service.impl.old.OldStorageServiceImpl;
import com.hgits.hotc.service.impl.old.OldUnitServiceImpl;
import com.hgits.hotc.service.impl.old.OldUserServiceImpl;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/tdc")
public class TransferDataController {

    @Resource
    StorageServiceImpl storageService;
    @Resource
    SysUserServiceImpl sysUserService;
    @Resource
    OldUserServiceImpl oldUserService;
    @Resource
    SysDictServiceImpl sysDictService;
    @Resource
    SysDeptServiceImpl sysDeptService;
    @Resource
    OldUnitServiceImpl oldUnitService;
    @Resource
    TownServiceImpl townService;
    @Resource
    OldStorageServiceImpl oldStorageService;

    List<Town> towns;
    List<SysDict> sysDicts;

    @ModelAttribute
    public void loadDict() {
        // towns = townService.selectAll();
        sysDicts = sysDictService.selectAll();
    }

    @GetMapping("/usertf")
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

    /**
     * 根据单位ID查询
     * @param unitid
     * @return
     */
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

    /**
     * 根据专业名称查询专业Id
     * @param major
     * @return
     */
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

    @GetMapping("/storagetf")
    public void storageTransfer() {
        List<OldStorage> oldStorages = oldStorageService.selectAll();

        List<Storage> storageList = new ArrayList<>();
        oldStorages.forEach(oldStorage -> {
            Storage storage = convertOld2New(oldStorage);
        });
    }

    private Storage convertOld2New(OldStorage oldStorage) {
        Storage storage = new Storage();
        /*Example example = new Example(SysUser.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("", oldStorage.getProjectLeader());
        List<SysUser> sysUsers = sysUserService.selectByExample();
        oldStorage.getProjectLeader();
        storage.setId(oldStorage.getUuid())
                .setSerialNo(oldStorage.getNumber())
                .setDemandSerialNo(oldStorage.getRequestNumber())
                .setPurchaseDemandSerialNo(oldStorage.getPurchaseRequestNumber())
                .setProjectName(oldStorage.getProjectName())
                .setWbsNo(oldStorage.getWbsNumber())
                .setProjectLeaderId(oldStorage.getProjectLeader())   //
                .setMaterialNo(oldStorage.getMaterialsName())        //
                .setBroughtQuantity(oldStorage.getBroughtNumber())
                .setTotalGoods(oldStorage.getTotalGoods())
                .setAlarmQuantity(oldStorage.getAlarm())
                .setCreateTime(oldStorage.getCreateTime())
                .setUpdateTime(oldStorage.getUpdateTime())
                .setCreateBy(oldStorage.getCreateBy())
                .setUpdateBy(oldStorage.getUpdateBy())
                .setOccupyQuantity(oldStorage.getOccupy())
                .setConstructionUnit(oldStorage.getTeam());*/
        oldStorage.getUuid();
        return storage;
    }


}
