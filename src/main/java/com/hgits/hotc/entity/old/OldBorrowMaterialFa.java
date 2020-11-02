package com.hgits.hotc.entity.old;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
@Table(name = "borrow_material_fa")
@ApiModel(value="BorrowMaterialFa对象", description="")
public class OldBorrowMaterialFa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Integer id;

    @ApiModelProperty(value = "借料单编号")
    private String billno;

    @ApiModelProperty(value = "借料人施工单位")
    private String constructionUnit;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

}
