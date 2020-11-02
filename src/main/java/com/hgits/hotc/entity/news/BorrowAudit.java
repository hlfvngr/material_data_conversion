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
@Table(name = "tb_borrow_audit")
@ApiModel(value="BorrowAudit对象", description="")
public class BorrowAudit implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
    @Id
    private Integer id;

    @ApiModelProperty(value = "审核状态")
    private Integer state;

    @ApiModelProperty(value = "提交时间")
    private LocalDateTime submitTime;

    @ApiModelProperty(value = "截止时间")
    private LocalDateTime endTime;

    @ApiModelProperty(value = "监理审核时间")
    private String supervisorTime;

    @ApiModelProperty(value = "监理审核意见")
    private String supervisorSuggest;

    @ApiModelProperty(value = "客建审核时间")
    private String clientTime;

    @ApiModelProperty(value = "客建审核意见")
    private String clientSuggest;

    @ApiModelProperty(value = "监理签名")
    private String supervisorMan;

    @ApiModelProperty(value = "客建签名")
    private String clientMan;

    @ApiModelProperty(value = "采购签名")
    private String purchaseMan;

    @ApiModelProperty(value = "采购意见")
    private String purchaseSuggest;

    @ApiModelProperty(value = "采购时间")
    private String purchaseTime;

    @ApiModelProperty(value = "借料单ID")
    private String borrowId;

}
