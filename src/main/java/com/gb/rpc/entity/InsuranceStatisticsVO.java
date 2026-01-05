package com.gb.rpc.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @author syrobin
 * @version v1.0
 * @description: 保险信息统计
 * @date 2022-09-19 15:48
 */
@Data
public class InsuranceStatisticsVO {

    @ApiModelProperty(value = "保额总额")
    private BigDecimal insuranceAmountTotal;
    @ApiModelProperty(value = "保费总额")
    private BigDecimal insurancePremiumTotal;
    @ApiModelProperty(value = "保单总数")
    private Integer insuranceCount;
    @ApiModelProperty(value = "成交保费集合", notes = "key: 投保人ID, value: 保费")
    private Map<String,BigDecimal> insuranceTransactionPremiumMap;


    @ApiModelProperty(value = "成交佣金集合", notes = "key: 投保人ID, value: 佣金")
    private Map<String,BigDecimal> insuranceSettlementPremiumMap;

    @ApiModelProperty(value = "佣金总额")
    private BigDecimal insuranceSettlementTotal;

    @ApiModelProperty(value = "今日佣金")
    private BigDecimal todaySettlementPremium;

    @ApiModelProperty(value = "当月佣金")
    private BigDecimal monthSettlementPremium;

    @ApiModelProperty(value = "今日保费")
    private BigDecimal todayInsurancePremium;
    @ApiModelProperty(value = "当月保费")
    private BigDecimal monthInsurancePremium;

    @ApiModelProperty(value = "客户成交保费集合", notes = "key: 经纪人ID|投保人ID, value: 保费")
    private Map<String,BigDecimal> customerTransactionPremiumMap;

}
