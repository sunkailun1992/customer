package com.gb.rpc.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


/**
 * TODO 线下投保数据渲染
 * 代码生成器
 *
 * @author lixinyao
 * @className OfflineCastInsuranceDataVO
 * @time 2022-06-08 09:13:23
 */
@Data
@ApiModel(value = "线下投保数据渲染")
public class OfflineCastInsuranceDataVO implements Serializable {

    @ApiModelProperty(value = "序列")
    private String id;

    @ApiModelProperty(value = "险种名称")
    private String dangerPlantedName;

    @ApiModelProperty(value = "险种id")
    private String dangerPlantedId;

    @ApiModelProperty(value = "省编码")
    private String provinceCode;

    @ApiModelProperty(value = "市编码")
    private String cityCode;

    @ApiModelProperty(value = "区编码")
    private String areaCode;



    @ApiModelProperty(value = "提交投保人id")
    private String submitId;

    @ApiModelProperty(value = "提交投保人")
    private String submitName;

    @ApiModelProperty(value = "提交人手机号")
    private String submitMobile;

    @ApiModelProperty(value = "项目名称")
    private String projectName;

    @ApiModelProperty(value = "投保企业名称")
    private String castEntepriseName;

    @ApiModelProperty(value = "被保企业名称")
    private String insuredEnterpriseName;

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
}


