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
@Table(name = "tb_black_delivery_no_list")
@ApiModel(value="BlackDeliveryNoList对象", description="")
public class BlackDeliveryNoList implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "交付编码")
    private String deliveryNo;

}
