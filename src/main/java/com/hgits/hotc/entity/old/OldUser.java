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
@Table(name = "tbl_user")
@ApiModel(value="User对象", description="'material _cloud_pro.report' is not BASE TABLE")
public class OldUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "人员表主键")
    @Id
    private Integer uid;

    @ApiModelProperty(value = "登录ID")
    @Column(name = "LOGINID")
    private String loginid;

    @ApiModelProperty(value = "51账号")
    @Column(name = "FOID")
    private String foid;

    @ApiModelProperty(value = "密码")
    @Column(name = "PASSWORD")
    private String password;

    @ApiModelProperty(value = "人员名字")
    @Column(name = "USERNAME")
    private String username;

    @ApiModelProperty(value = "电话")
    @Column(name = "PHONE")
    private String phone;

    @ApiModelProperty(value = "身份证号码")
    @Column(name = "IDCARDNUM")
    private String idcardnum;

    @ApiModelProperty(value = "专业")
    @Column(name = "MAJOR")
    private String major;

    @ApiModelProperty(value = "是否领料员")
    @Column(name = "ISPICKING")
    private String ispicking;

    @ApiModelProperty(value = "单位ID")
    @Column(name = "UNITID")
    private Integer unitid;

}
