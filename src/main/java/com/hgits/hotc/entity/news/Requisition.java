package com.hgits.hotc.entity.news;

import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p>
 * 
 * </p>
 *
 * @author lzr
 * @since 2020-10-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Table(name = "tb_requisition")
@ApiModel(value="Requisition对象", description="")
public class Requisition implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "自增编号")
    @Id
    private Long id;

    @ApiModelProperty(value = "交付编号")
    private String deliveryNo;

    @ApiModelProperty(value = "交付名称")
    private String deliveryName;

    @ApiModelProperty(value = "交付地址")
    private String deliveryAdress;

    @ApiModelProperty(value = "立项会审时间")
    private LocalDateTime projectTime;

    @ApiModelProperty(value = "施工队")
    private Integer constructionTeam;

    @ApiModelProperty(value = "所属专业")
    private Integer major;

    @ApiModelProperty(value = "交付类型")
    private Integer deliveryType;

    @ApiModelProperty(value = "需求提出人")
    private String demandClaimant;

    @ApiModelProperty(value = "单点工程编号")
    private String engineeringNo;

    @ApiModelProperty(value = "需求提出人电话")
    private String demandClaimantPhone;

    @ApiModelProperty(value = "单点名称")
    private String singlePointName;

    @ApiModelProperty(value = "建设类型")
    private Integer constructionType;

    @ApiModelProperty(value = "拟派单位")
    private Integer constructionUnit;

    @ApiModelProperty(value = "立项端口")
    private Integer portQuantity;

    @ApiModelProperty(value = "镇分")
    private Integer townId;

    @ApiModelProperty(value = "区域")
    private String area;

    @ApiModelProperty(value = "组织")
    private String tissue;

    @ApiModelProperty(value = "项目年度批次")
    private String projectAnnualBatch;

    @ApiModelProperty(value = "场景类型")
    private Integer scene;

    @ApiModelProperty(value = "单点造价")
    private String cost;

    @ApiModelProperty(value = "所属工程项目名称")
    private String projectName;

    @ApiModelProperty(value = "WBS号（拟选)")
    private String wbsNo;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "提交者ID")
    private Integer submitId;

    @ApiModelProperty(value = "提交者名称")
    private String submitName;

    @ApiModelProperty(value = "提交者单位")
    private Integer submitUnit;

    @ApiModelProperty(value = "提交者专业")
    private String submitMajor;

    @ApiModelProperty(value = "是否黑名称数据 1:是 0:不是")
    private Integer isBlack;




}
