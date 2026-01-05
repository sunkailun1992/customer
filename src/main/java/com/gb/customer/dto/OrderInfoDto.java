package com.gb.customer.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 订单信息
 *
 * @author lijinhao
 * @Date 2021/6/16
 */
@Data
public class OrderInfoDto {


    private String id;
    /**
     * 投保单id
     */
    private String castInsuranceId;
    /**
     * 提交时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createDateTime;
    /**
     * 险种名称
     */
    private String dangerPlantedName;
    /**
     * 产品
     */
    private String spuName;
    private String spuId;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 投保人
     */
    private String name;
    /**
     * 保费
     */
    private BigDecimal payMoney;
    /**
     * 订单状态（保险状态（0：待生效，1：保险中，2：待续保，3：保险过期，4：保险退保））
     */
    private Integer status;

    /**
     * 订单类型
     */
    private Integer orderType;
    /**
     * 业务管家
     */
    private String businessSteward;
    private String businessStewardId;
    /**
     * 服务管家
     */
    private String serviceSteward;
    private String serviceStewardId;
}
