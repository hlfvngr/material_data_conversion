package com.hgits.hotc.entity.old;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Column;
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
@Table(name = "blackmaterials")
@ApiModel(value="Blackmaterials对象", description="")
public class OldBlackmaterials implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Integer mid;

    private String mname;

    private Integer number;

    private Integer design;

    private Integer rid;

    private String construction;

    private String unit;

    @Column(name = "updateNumber")
    private Integer updateNumber;

    @Column(name = "isDisplay")
    private Integer isDisplay;

}
