package com.hgits.hotc.entity.news;

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
@Table(name = "tb_material")
@ApiModel(value="Material对象", description="")
public class Material implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
    private Integer id;

    /*@ApiModelProperty(value = "物料编码")
    private String materialNo;*/

    @ApiModelProperty(value = "物料名称")
    private String materialName;

    @ApiModelProperty(value = "申领名称")
    private String alias;

    @ApiModelProperty(value = "物料单位")
    private String materialUnit;


}
