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
@Table(name = "tb_picklist_material_storage_relate")
@ApiModel(value="PicklistMaterialStorageRelate对象", description="")
public class PicklistMaterialStorageRelate implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @Id
    private Integer id;

    @ApiModelProperty(value = "被领取的物料详情Id")
    private Integer picklistMaterialId;

    @ApiModelProperty(value = "扣除的数量")
    private Integer decrNum;

    @ApiModelProperty(value = "与仓库表相对应的id")
    private String storageId;

    @ApiModelProperty(value = "扣除的时间")
    private LocalDateTime decrTime;




}
