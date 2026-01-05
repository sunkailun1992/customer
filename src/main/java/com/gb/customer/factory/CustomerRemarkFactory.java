package com.gb.customer.factory;

import com.gb.customer.entity.CustomerAgent;
import com.gb.customer.entity.enums.CustomerAgentGenderEnum;
import com.gb.customer.entity.enums.CustomerAgentStateEnum;
import com.gb.customer.entity.enums.CustomerAgentTypeEnum;

/**
 * 客户备注 实体构建类
 *
 * @author lijh
 */
public class CustomerRemarkFactory {

    /**
     * 构建经纪人对客户的备注信息
     *
     * @param customerId     客户id
     * @param agentUserId    经纪人id
     * @param name           姓名
     * @param gender         性别：1男2女
     * @param enterpriseName 企业名称
     * @param provinceCode   省
     * @param cityCode       市
     * @param areaCode       区
     * @param address        地址
     * @return
     */
    public static CustomerAgent getBeanCustomerAgentRemark(String customerId, String agentUserId, String name, Integer gender,
                                                           String enterpriseName, String provinceCode, String cityCode, String areaCode, String address) {

        CustomerAgent customerAgent = new CustomerAgent();
        customerAgent.setType(CustomerAgentTypeEnum.无);
        customerAgent.setState(CustomerAgentStateEnum.无);
        customerAgent.setCustomerId(customerId);
        customerAgent.setAgentUserId(agentUserId);
        customerAgent.setName(name);
        customerAgent.setGender(CustomerAgentGenderEnum.getCustomerAgentGenderEnum(gender));
        customerAgent.setEnterpriseName(enterpriseName);
        customerAgent.setProvinceCode(provinceCode);
        customerAgent.setCityCode(cityCode);
        customerAgent.setAreaCode(areaCode);
        customerAgent.setAddress(address);
        return customerAgent;
    }
}
