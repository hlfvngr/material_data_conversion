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
@Table(name = "tb_borrow_material")
@ApiModel(value="BorrowMaterial对象", description="")
public class BorrowMaterial implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
    @Id
    private Integer id;

    @ApiModelProperty(value = "物料编号")
    private String materialNo;

    @ApiModelProperty(value = "物料名称")
    private String materialName;

    @ApiModelProperty(value = "物料数量")
    private Integer materialQuantity;

    @ApiModelProperty(value = "物料单位")
    private String materialUnit;

    @ApiModelProperty(value = "物料51编号")
    private String materialFivenoeNo;

    @ApiModelProperty(value = "借料单ID")
    private String borrowId;

    @ApiModelProperty(value = "申请单ID")
    private Integer requisitionId;

    @ApiModelProperty(value = "申请详情单ID")
    private Integer requisitionDetailId;

}
