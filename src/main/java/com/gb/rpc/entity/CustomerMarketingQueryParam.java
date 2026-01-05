package com.gb.rpc.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author syrobin
 * @version v1.0
 * @description: 客户营销查询参数
 * @date 2022-09-20 17:36
 */
@Data
public class CustomerMarketingQueryParam{

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "投保用户id")
    private String castInsuranceUserId;
    @ApiModelProperty(value = "经纪人id")
    private String agentUserId;

}
