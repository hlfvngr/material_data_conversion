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
 * 'material _cloud_pro.report' is not BASE TABLE
 * </p>
 *
 * @author lzr
 * @since 2020-10-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Table(name = "storage")
@ApiModel(value="Storage对象", description="'material _cloud_pro.report' is not BASE TABLE")
public class OldStorage implements Serializable {

    private static final long serialVersionUID = 1L;

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

    @ApiModelProperty(value = "已领数量")
    private Integer broughtNumber;

    @ApiModelProperty(value = "到货总计")
    private Integer totalGoods;

    @ApiModelProperty(value = "预警值")
    private Integer alarm;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "修改人")
    private String updateBy;

    @ApiModelProperty(value = "唯一标识")
    @Id
    private String uuid;

    @ApiModelProperty(value = "申领名称")
    private String applymaterialsname;

    @ApiModelProperty(value = "预占值")
    private Integer occupy;

    private String team;

}
