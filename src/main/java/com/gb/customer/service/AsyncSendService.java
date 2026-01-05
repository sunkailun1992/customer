package com.gb.customer.service;

import com.gb.customer.dto.OfflineCastInsuranceCustomerDto;
import com.gb.customer.entity.PotentialCustomer;

import java.util.List;

/**
 * @title: AsyncSendService
 * @author: lijh
 * @date: 2023/3/10 14:29
 * @description:
 */
public interface AsyncSendService {

    String asyncSendYunPromoteForm(PotentialCustomer potentialCustomer) throws Exception;


    /**
     * 转化线下单数据为客户
     *
     * @param offlineCastInsuranceCustomerDto
     */
    void transformationOfflineCastInsuranceCustomer(List<OfflineCastInsuranceCustomerDto> offlineCastInsuranceCustomerDto);

}
