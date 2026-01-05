package com.gb.rpc.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


/**
 * TODO 转化外部系统平台用户关联渲染
 * 代码生成器
 *
 * @author sunx
 * @className TransformationExternalPlatformSystemUserVO
 * @time 2022-12-16 03:10:09
 */
@Data
@ApiModel(value = "转化外部系统平台用户关联渲染")
public class TransformationExternalPlatformSystemUserVO implements Serializable {

    @ApiModelProperty(value = "序列")
    private String id;

    @ApiModelProperty(value = "转化外部系统平台id")
    private String transformationExternalPlatformSystemId;

    @ApiModelProperty(value = "用户id")
    private String userId;

    @ApiModelProperty(value = "线索（true：生成，false：不生成）")
    private Boolean clue;

    @ApiModelProperty(value = "说明")
    private String description;

    @ApiModelProperty(value = "修改人")
    private String modifyName;

    @ApiModelProperty(value = "删除状态")
    private Boolean isDelete;

    @ApiModelProperty(value = "类型（0：默认）")
    private String type;

    @ApiModelProperty(value = "状态（0：默认）")
    private String state;

    @ApiModelProperty(value = "标签")
    private String label;

    @ApiModelProperty(value = "排序")
    private Integer sorting;

    @ApiModelProperty(value = "版本号")
    private Integer version;

    @ApiModelProperty(value = "外部平台id")
    private String externalPlatformId;

    @ApiModelProperty(value = "外部平台名称")
    private String externalPlatformName;

    @ApiModelProperty(value = "外部平台编码")
    private String externalPlatformCode;

    @ApiModelProperty(value = "外部系统id")
    private String externalSystemId;

    @ApiModelProperty(value = "外部系统名称")
    private String externalSystemName;

    @ApiModelProperty(value = "外部系统编码")
    private String externalSystemCode;
}


