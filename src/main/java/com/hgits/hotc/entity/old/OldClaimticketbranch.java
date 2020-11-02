package com.hgits.hotc.entity.old;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

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
@Table(name = "claimticketbranch")
@ApiModel(value="Claimticketbranch对象", description="")
public class OldClaimticketbranch implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Integer cid;

    @ApiModelProperty(value = "提交人id")
    private Integer construction;

    @ApiModelProperty(value = "提交人姓名")
    private String username;

    @ApiModelProperty(value = "意见")
    private String opinion;

    @ApiModelProperty(value = "具体单位")
    private String department;

    @ApiModelProperty(value = "状态")
    private String state;

    @ApiModelProperty(value = "提交时间，随着审核不断变化用来倒计时")
    private String examine;

    @ApiModelProperty(value = "显示文字状态")
    private String drawtime;

    @ApiModelProperty(value = "领料员id")
    private Integer getid;

    @ApiModelProperty(value = "wbs号码")
    private String wbsnumber;

    @ApiModelProperty(value = "施工队")
    private String team;

    @ApiModelProperty(value = "还料单号")
    private String returnnumber;

    @ApiModelProperty(value = "还料时间")
    private String returntime;

    @ApiModelProperty(value = "出库单号")
    private String outnumber;

    @ApiModelProperty(value = "监理时间")
    private String supervisortime;

    @ApiModelProperty(value = "监理意见")
    private String supervisorsuggest;

    @ApiModelProperty(value = "客建时间")
    private String clienttime;

    @ApiModelProperty(value = "客建意见")
    private String clientsuggest;

    @ApiModelProperty(value = "监理审核姓名")
    private String supervisorman;

    @ApiModelProperty(value = "客建审核姓名")
    private String clientman;

    @ApiModelProperty(value = "领料单号")
    private String pickingnumber;

    @ApiModelProperty(value = "采购名称")
    private String purchaseman;

    @ApiModelProperty(value = "采购意见")
    private String purchaseidea;

    @ApiModelProperty(value = "镇分")
    private String town;

    @ApiModelProperty(value = "专业")
    private String major;

    @ApiModelProperty(value = "实际领料时间")
    private String practicaltime;

    @ApiModelProperty(value = "采购时间")
    private String purchasetime;

    @ApiModelProperty(value = "51交付编号")
    private String fiveonenumber;

}
