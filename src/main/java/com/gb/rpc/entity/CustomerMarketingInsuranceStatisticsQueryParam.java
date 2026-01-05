package com.gb.rpc.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author syrobin
 * @version v1.0
 * @description: 保险统计查询参数
 * @date 2022-09-19 16:56
 */
@Data
@ApiModel(value = "客户营销保险统计参数")
public class CustomerMarketingInsuranceStatisticsQueryParam implements Serializable {

    @ApiModelProperty(value = "日期开始时间")
    private String dayStartTime;

    @ApiModelProperty(value = "日期结束时间")
    private String dayEndTime;

    @ApiModelProperty(value = "月份开始时间")
    private String monthStartTime;

    @ApiModelProperty(value = "月份结束时间")
    private String monthEndTime;

    @ApiModelProperty(value = "经纪人ids")
    private List<String> agentUserIds;

    @ApiModelProperty(value = "客户营销查询参数")
    private List<CustomerMarketingQueryParam> marketingQueryParams;


}
