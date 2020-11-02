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
@Table(name = "tb_black_list")
@ApiModel(value="BlackList对象", description="")
public class BlackList implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
    private Integer id;

    @ApiModelProperty(value = "单位ID")
    private Integer deptId;

    @ApiModelProperty(value = "镇分ID")
    private Integer townId;

    @ApiModelProperty(value = "物料编码")
    private Integer materialNo;

}
