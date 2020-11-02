package com.hgits.hotc.entity.news;

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
@Table(name = "tb_borrow_requisition_relate")
@ApiModel(value="BorrowRequisitionRelate对象", description="")
public class BorrowRequisitionRelate implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
    @Id
    private Integer id;

    @ApiModelProperty(value = "申请单所属专业")
    private Integer major;

    @ApiModelProperty(value = "申请单wbs编号")
    private String wbsNo;

    @ApiModelProperty(value = "申请单审核状态")
    private Integer auditStatus;

    @ApiModelProperty(value = "申请单镇分")
    private Integer townId;

    @ApiModelProperty(value = "申请单交付编号")
    private String deliveryNo;

    @ApiModelProperty(value = "项目负责人ID")
    private Integer projectLeaderId;

    @ApiModelProperty(value = "借料单编号")
    private String borrowId;

    @ApiModelProperty(value = "申请单编号")
    private Integer requisitionId;




}
