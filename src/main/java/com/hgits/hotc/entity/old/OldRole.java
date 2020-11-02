package com.hgits.hotc.entity.old;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Column;
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
@Table(name = "tbl_role")
@ApiModel(value="Role对象", description="'material _cloud_pro.report' is not BASE TABLE")
public class OldRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "角色id")
    @Id
    private Integer roleid;

    @ApiModelProperty(value = "角色（一般用户，管理员，查看用户）")
    @Column(name = "ROLE")
    private String role;

    @ApiModelProperty(value = "角色名称")
    @Column(name = "ROLENAME")
    private String rolename;

}
