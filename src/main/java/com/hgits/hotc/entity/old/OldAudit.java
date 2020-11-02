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
@Table(name = "audit")
@ApiModel(value="Audit对象", description="")
public class OldAudit implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Integer aid;

    @ApiModelProperty(value = "全部的专业")
    private String allmajor;

    @ApiModelProperty(value = "监理包含专业")
    private String supervisor;

    @ApiModelProperty(value = "采购包含专业")
    private String suggest;

    @ApiModelProperty(value = "wbs号码")
    private String wbs;

    @ApiModelProperty(value = "计数器监理")
    private Integer supnumber;

    @ApiModelProperty(value = "计数器采购")
    private Integer sugnumber;

    @ApiModelProperty(value = "wbs计数器")
    private Integer wbsnumber;

    @ApiModelProperty(value = "所对应的申领单id")
    private Integer cid;


}
