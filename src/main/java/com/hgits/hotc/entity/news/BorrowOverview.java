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
@Table(name = "tb_borrow_overview")
@ApiModel(value="BorrowOverview对象", description="")
public class BorrowOverview implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "借料单编号")
    private String id;

    @ApiModelProperty(value = "提交人id")
    private Integer submitId;

    @ApiModelProperty(value = "提交人姓名")
    private String submitName;

    @ApiModelProperty(value = "单位")
    private Integer submitDepartment;

    @ApiModelProperty(value = "具体单位")
    private Integer concreteUnit;

    @ApiModelProperty(value = "施工队")
    private Integer constructionTeam;

    @ApiModelProperty(value = "提交时间，随着审核不断变化用来倒计时")
    private LocalDateTime submitTime;

    @ApiModelProperty(value = "截止时间")
    private LocalDateTime endTime;

    @ApiModelProperty(value = "出库单号")
    private String outOfStackNo;

    @ApiModelProperty(value = "实际领料时间")
    private LocalDateTime actualPickTime;

    @ApiModelProperty(value = "领取结果")
    private Integer broughtResult;

    @ApiModelProperty(value = "打印次数")
    private Integer printCount;

    @ApiModelProperty(value = "创建人")
    private Integer createBy;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;



}
