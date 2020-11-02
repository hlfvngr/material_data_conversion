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
@Table(name = "tb_borrow_material_storage_relate")
@ApiModel(value="BorrowMaterialStorageRelate对象", description="")
public class BorrowMaterialStorageRelate implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @Id
    private Integer id;

    @ApiModelProperty(value = "被借料的物料详情Id")
    private Integer borrowMaterialId;

    @ApiModelProperty(value = "借料的数量")
    private Integer decrNum;

    @ApiModelProperty(value = "与仓库表相对应的id")
    private String storageId;

    @ApiModelProperty(value = "扣除的时间")
    private LocalDateTime decrTime;

}
