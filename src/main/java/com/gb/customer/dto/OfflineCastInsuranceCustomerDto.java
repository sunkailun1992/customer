package com.gb.customer.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 线下单信息
 */
@Data
public class OfflineCastInsuranceCustomerDto {

    @ApiModelProperty(value = "外部平台编码")
    private String externalPlatformCode;
    @ApiModelProperty(value = "外部平台名称")
    private String externalPlatformName;
    @ApiModelProperty(value = "外部系统编码")
    private String externalSystemCode;
    @ApiModelProperty(value = "外部系统名称")
    private String externalSystemName;

    @ApiModelProperty(value = "提交投保人id")
    private String submitUserId;

    @ApiModelProperty(value = "提交投保人")
    private String submitName;

    @ApiModelProperty(value = "提交人手机号")
    private String submitMobile;

    @ApiModelProperty(value = "险种id")
    private String dangerPlantedId;
    @ApiModelProperty(value = "险种名称")
    private String dangerPlantedName;

    @ApiModelProperty(value = "省编码")
    private String provinceCode;

    @ApiModelProperty(value = "市编码")
    private String cityCode;

    @ApiModelProperty(value = "区编码")
    private String areaCode;

    @ApiModelProperty(value = "项目名称")
    private String projectName;

    @ApiModelProperty(value = "投保企业名称")
    private String castEnterpriseName;

    @ApiModelProperty(value = "企业名称")
    private String enterpriseName;

    @ApiModelProperty(value = "提交时间")
    private String submitTime;

    @ApiModelProperty(value = "类型（0：默认）")
    private String type;

}
