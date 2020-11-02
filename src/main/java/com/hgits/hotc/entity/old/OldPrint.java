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
 * 'material _cloud_pro.report' is not BASE TABLE
 * </p>
 *
 * @author lzr
 * @since 2020-10-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Table(name = "tbl_print")
@ApiModel(value="Print对象", description="'material _cloud_pro.report' is not BASE TABLE")
public class OldPrint implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Integer id;

    @ApiModelProperty(value = "领料/借料单号")
    private String pickingnumber;

    @ApiModelProperty(value = "需求编码")
    private String requestNumber;

    @ApiModelProperty(value = "打印次数")
    private Long status;


}
