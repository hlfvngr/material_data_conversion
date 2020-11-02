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
@Table(name = "tbl_unit")
@ApiModel(value="Unit对象", description="'material _cloud_pro.report' is not BASE TABLE")
public class OldUnit implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "单位ID")
    @Id
    private Integer unitid;

    @ApiModelProperty(value = "单位名称")
    @Column(name = "UNITNAME")
    private String unitname;

    @ApiModelProperty(value = "具体名称")
    @Column(name = "CONCRETEUNIT")
    private String concreteunit;

    @ApiModelProperty(value = "具体公司名成")
    @Column(name = "COMPANYNAME")
    private String companyname;

    @ApiModelProperty(value = "是否施工队")
    @Column(name = "ISRANK")
    private String isrank;

    @ApiModelProperty(value = "施工队")
    @Column(name = "`RANK`")
    private String rank;


}
