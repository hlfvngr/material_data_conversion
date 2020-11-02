package com.hgits.hotc.entity.news;

import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Id;

/**
 * <p>
 * 机构管理
 * </p>
 *
 * @author lzr
 * @since 2020-10-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="SysDept对象", description="机构管理")
public class SysDept implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "编号")
    @Id
    private Long id;

    @ApiModelProperty(value = "机构名称")
    private String name;

    @ApiModelProperty(value = "上级机构ID，一级机构为0")
    private Long parentId;

    @ApiModelProperty(value = "上级所有机构，根据level大小降序排列,以逗号分隔")
    private String parentIds;

    @ApiModelProperty(value = "等级")
    private Integer level;

    @ApiModelProperty(value = "排序")
    private Integer orderNum;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新人")
    private String lastUpdateBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime lastUpdateTime;

    @ApiModelProperty(value = "是否删除  -1：已删除  0：正常")
    private Integer delFlag;




}
