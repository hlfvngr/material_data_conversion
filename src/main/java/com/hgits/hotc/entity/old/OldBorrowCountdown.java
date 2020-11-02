package com.hgits.hotc.entity.old;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Column;
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
@Table(name = "borrow_countdown")
@ApiModel(value="BorrowCountdown对象", description="")
public class OldBorrowCountdown implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    @ApiModelProperty(value = "借料单号")
    @Column(name = "billNo")
    private String billNo;

    @ApiModelProperty(value = "提交时间")
    @Column(name = "commitTime")
    private LocalDateTime commitTime;

    @ApiModelProperty(value = "结束日期")
    @Column(name = "endDate")
    private LocalDateTime endDate;

    @ApiModelProperty(value = "出库编码")
    @Column(name = "outboundNo")
    private String outboundNo;

    @ApiModelProperty(value = "领取结果 0 没领取，1领取完成 2 超时")
    private Integer result;

    @ApiModelProperty(value = "借料人")
    @Column(name = "borrowMan")
    private String borrowMan;

    @ApiModelProperty(value = "具体单位")
    @Column(name = "concreteUnit")
    private String concreteUnit;

    @ApiModelProperty(value = "打印次数")
    @Column(name = "printCount")
    private Integer printCount;

    @ApiModelProperty(value = "出库结束时间")
    @Column(name = "updateResultTime")
    private LocalDateTime updateResultTime;

}
