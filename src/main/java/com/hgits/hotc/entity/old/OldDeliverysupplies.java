package com.hgits.hotc.entity.old;

import com.sun.javafx.beans.IDProperty;
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
@Table(name = "deliverysupplies")
@ApiModel(value="Deliverysupplies对象", description="")
public class OldDeliverysupplies implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @Id
    private Integer id;

    @ApiModelProperty(value = "与myview形成的表")
    private Integer myviweid;

    @ApiModelProperty(value = "扣除的数量")
    private Integer sum;

    @ApiModelProperty(value = "与仓库表相对应的uuid")
    private String uuid;

    @ApiModelProperty(value = "扣除的时间")
    private String outmin;

}
