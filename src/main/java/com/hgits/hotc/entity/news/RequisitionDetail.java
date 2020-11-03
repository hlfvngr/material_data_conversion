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
@Table(name = "tb_requisition_detail")
@ApiModel(value="RequisitionDetail对象", description="")
public class RequisitionDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Integer id;

    @ApiModelProperty(value = "物料ID")
    private Integer materialId;

    @ApiModelProperty(value = "材料名称")
    private String materialName;

    @ApiModelProperty(value = "数量")
    private Integer materialQuantity;

    @ApiModelProperty(value = "设计师出图")
    private Integer designQuantity;

    @ApiModelProperty(value = "单位")
    private String materialUnit;

    @ApiModelProperty(value = "所属申领单")
    private Integer requisitionId;

    @ApiModelProperty(value = "修改后的数量")
    private Integer updateQuantity;

    @ApiModelProperty(value = "是否可编辑 1:可以编辑 0:不可以编辑")
    private Integer isDisplay;




}
