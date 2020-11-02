package com.hgits.hotc.entity.old;


import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
@Table(name = "black_list")
@ApiModel(value="BlackList对象", description="")
public class OldBlackList implements Serializable {

    private static final long serialVersionUID = 1L;

    private String deliverynumber;

}
