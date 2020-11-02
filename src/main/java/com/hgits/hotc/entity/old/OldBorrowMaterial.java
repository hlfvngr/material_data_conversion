package com.hgits.hotc.entity.old;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

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
@Table(name = "borrow_material")
@ApiModel(value="BorrowMaterial对象", description="")
public class OldBorrowMaterial implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Integer id;

    @ApiModelProperty(value = "绑定借料父表的ID")
    private String billno;

    @ApiModelProperty(value = "编号")
    private Integer number;

    @ApiModelProperty(value = "需求号")
    private String requestNumber;

    @ApiModelProperty(value = "采购需求号")
    private String purchaseRequestNumber;

    @ApiModelProperty(value = "项目名称")
    private String projectName;

    @ApiModelProperty(value = "wbs编码（项目编码）")
    private String wbsNumber;

    @ApiModelProperty(value = "项目负责人")
    private String projectLeader;

    @ApiModelProperty(value = "物料编码")
    private String materialsNumber;

    @ApiModelProperty(value = "物料名称")
    private String materialsName;

    @ApiModelProperty(value = "借料数量")
    private Integer borrowQuantity;

    @ApiModelProperty(value = "专业")
    private String major;

    @ApiModelProperty(value = "监理审核")
    private Integer jlIsAgree;

    @ApiModelProperty(value = "客建审核")
    private Integer kjIsAgree;

    @ApiModelProperty(value = "监理审核时间")
    private LocalDateTime jlAuditingTime;

    @ApiModelProperty(value = "客建审核时间")
    private LocalDateTime kjAuditingTime;

    @ApiModelProperty(value = "绑定仓库表的uuid")
    private String uuid;

    @ApiModelProperty(value = "物料领取状态")
    private Integer state;

    @ApiModelProperty(value = "监理审核人")
    private String jlAuditingUser;

    @ApiModelProperty(value = "客建审核人")
    private String kjAuditingUser;

    @ApiModelProperty(value = "用于回退material待领取数量的id")
    private Integer mid;

}
