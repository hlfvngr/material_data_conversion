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
 * 
 * </p>
 *
 * @author lzr
 * @since 2020-10-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Table(name = "myview")
@ApiModel(value="Myview对象", description="")
public class OldMyview implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Integer cid;

    private String name;

    private Float number;

    private String assemblyitem;

    private String claimsfortheunit;

    private String concreteunit;

    private String constructionteam;

    private Integer staff;

    private Integer ccid;

    private Integer mid;

    private String wbsnumber;

    private String principal;

    private String deliverynumber;

    private Float backfill;

    @ApiModelProperty(value = "用来判断库存中的值1是正常，0是失败")
    private Integer state;

}
