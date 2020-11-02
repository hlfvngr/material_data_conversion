package com.hgits.hotc.entity.news;

import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
@Table(name = "tb_storage")
@ApiModel(value="Storage对象", description="")
public class Storage implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "唯一标识")
    private String id;

    @ApiModelProperty(value = "编号")
    private Integer serialNo;

    @ApiModelProperty(value = "需求号")
    private String demandSerialNo;

    @ApiModelProperty(value = "采购需求号")
    private String purchaseDemandSerialNo;

    @ApiModelProperty(value = "项目名称")
    private String projectName;

    @ApiModelProperty(value = "wbs编码（项目编码）")
    private String wbsNo;

    @ApiModelProperty(value = "项目负责人")
    private Integer projectLeaderId;

    @ApiModelProperty(value = "物料编码")
    private String materialNo;

    @ApiModelProperty(value = "已领数量")
    private Integer broughtQuantity;

    @ApiModelProperty(value = "到货总计")
    private Integer totalGoods;

    @ApiModelProperty(value = "预警值")
    private Integer alarmQuantity;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "修改人")
    private String updateBy;

    @ApiModelProperty(value = "预占值")
    private Integer occupyQuantity;

    private String constructionUnit;


}
