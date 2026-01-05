package com.gb.customer.dto;

import lombok.Data;

@Data
public class CustomerInfoReqDto {

    /**
     * 用户id
     */
    private String userId;

    /**
     * 经纪人id
     */
    private String agentUserId;
}
