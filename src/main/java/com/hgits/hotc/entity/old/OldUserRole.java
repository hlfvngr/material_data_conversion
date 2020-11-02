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
@Table(name = "tbl_user_role")
@ApiModel(value="UserRole对象", description="'material _cloud_pro.report' is not BASE TABLE")
public class OldUserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "角色人员的关联ID")
    @Id
    private Integer rlid;

    @ApiModelProperty(value = "登录ID")
    @Column(name = "LOGINID")
    private String loginid;

    @ApiModelProperty(value = "角色ID")
    @Column(name = "ROLEID")
    private Integer roleid;

}
