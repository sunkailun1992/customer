package com.gb.customer.service.results;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gb.customer.entity.Customer;
import com.gb.customer.entity.CustomerAgent;
import com.gb.customer.entity.enums.CustomerAgentTypeEnum;
import com.gb.customer.entity.vo.CustomerAgentVO;
import com.gb.customer.enums.CustomerStateEnum;
import com.gb.customer.mapper.CustomerAgentMapper;
import com.gb.customer.mapper.CustomerMapper;
import com.gb.customer.service.CustomerService;
import com.gb.rpc.component.ProductComponent;
import com.gb.rpc.component.UserComponent;
import com.gb.rpc.entity.*;
import com.gb.rpc.entity.enums.RpcTypeEnum;
import com.gb.rpc.impl.RpcComponent;
import com.gb.utils.GeneralConvertor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;


/**
 * TODO 客户经纪人,Service返回实现
 * 代码生成器
 *
 * @author lijh
 * @className CustomerAgentServiceResults
 * @time 2022-08-31 09:35:12
 */
@Slf4j
@Service
@Setter(onMethod_ = {@Autowired})
public class CustomerAgentServiceResults {

    private CustomerMapper customerMapper;

    private CustomerService customerService;

    private RpcComponent rpcComponent;

    private UserComponent userComponent;

    private CustomerAgentMapper customerAgentMapper;

    private ProductComponent productComponent;


    /**
     * TODO 单条，增强返回参数追加
     *
     * @param customerAgentVO 客户经纪人
     * @return CustomerAgentVO
     * @author lijh
     * @methodName assignment
     * @time 2022-08-31 09:35:12
     */
    public CustomerAgentVO assignment(CustomerAgentVO customerAgentVO) {
        if (customerAgentVO != null) {
            //获取省市区
            Map<String, String> region = productComponent.getRegion(customerAgentVO.getAreaCode(), customerAgentVO.getCityCode(), customerAgentVO.getProvinceCode());
            if (com.baomidou.mybatisplus.core.toolkit.CollectionUtils.isNotEmpty(region)) {
                customerAgentVO.setAreaName(region.get("areaName"));
                customerAgentVO.setCityName(region.get("cityName"));
                customerAgentVO.setProvinceName(region.get("provinceName"));
            }
        }
        return customerAgentVO;
    }

    /**
     * TODO 分页，增强返回参数追加
     *
     * @param customerAgentVOList 客户经纪人
     * @return Page<CustomerAgentVO>
     * @author lijh
     * @methodName assignment
     * @time 2022-08-31 09:35:12
     */
    public Page<CustomerAgentVO> assignment(Page<CustomerAgentVO> customerAgentVOList) {
        CustomerMarketingInsuranceStatisticsQueryParam customerMarketingInsuranceStatisticsQueryParam = new CustomerMarketingInsuranceStatisticsQueryParam();
        if (CollectionUtils.isNotEmpty(customerAgentVOList.getRecords())) {
            List<CustomerMarketingQueryParam> rpcList = new ArrayList<>();
            for (CustomerAgentVO customerAgentVO : customerAgentVOList.getRecords()) {
                //客户信息
                getCustomer(customerAgentVO);
                //备注信息
                remark(customerAgentVO);

                //保费参数
                CustomerMarketingQueryParam customerMarketingQueryParam = new CustomerMarketingQueryParam();
                customerMarketingQueryParam.setCastInsuranceUserId(customerAgentVO.getUserId());
                customerMarketingQueryParam.setAgentUserId(customerAgentVO.getAgentUserId());
                rpcList.add(customerMarketingQueryParam);
            }
            //获取客户保费
            HashMap<String, Object> hashMapRpc = new HashMap<>(1);
            customerMarketingInsuranceStatisticsQueryParam.setMarketingQueryParams(rpcList);
            hashMapRpc.put("marketingQueryParams", customerMarketingInsuranceStatisticsQueryParam);
            InsuranceStatisticsVO insuranceStatisticsVO = rpcComponent.rpcQueryInfo(hashMapRpc, RpcTypeEnum.GET_CUSTOMER_PREMIUM, InsuranceStatisticsVO.class);
            if (Objects.nonNull(insuranceStatisticsVO.getCustomerTransactionPremiumMap())) {
                for (CustomerAgentVO customerAgentVO : customerAgentVOList.getRecords()) {
                    BigDecimal bigDecimal = insuranceStatisticsVO.getCustomerTransactionPremiumMap().get(customerAgentVO.getAgentUserId() + "|" + customerAgentVO.getUserId());
                    customerAgentVO.setPremium(Optional.ofNullable(bigDecimal).orElse(new BigDecimal("0.00")));
                }
            }
        }
        return customerAgentVOList;
    }

    /**
     * 获取客户信息
     *
     * @param customerAgentVO
     */
    private void getCustomer(CustomerAgentVO customerAgentVO) {
        //客户信息
        Customer customer = customerService.getById(customerAgentVO.getCustomerId());
        customerAgentVO.setCustomerMobile(customer.getMobile());
        customerAgentVO.setCustomerCreateDateTime(customer.getCreateDateTime());
        customerAgentVO.setCustomerModifyDateTime(customer.getModifyDateTime());
        customerAgentVO.setUserId(customer.getUserId());
        //判断用户是否注册   未注册获取客户信息的手机号，   已注册获取用户注册手机号
        if (CustomerStateEnum.已注册.getValue().equals(customer.getState())) {
            //根据手机号获取用户信息
            UserExtendsVO userExtendsVO = userComponent.getUserExtendsVOByUserId(customer.getUserId());
            customerAgentVO.setCustomerMobile(userExtendsVO.getMobile());
            customerAgentVO.setRegisterDateTime(userExtendsVO.getCreateDateTime());
        }
        //获取经纪人姓名
        UserVO userVO = userComponent.getOneUserByUserId(customerAgentVO.getAgentUserId());
        customerAgentVO.setAgentUserName(userVO.getName());
    }

    /**
     * 经纪人对客户备注信息
     *
     * @param customerAgentVO
     */
    private void remark(CustomerAgentVO customerAgentVO) {
        //经纪人对客户的备注信息
        CustomerAgent customerAgent = customerAgentMapper.selectOne(new QueryWrapper<CustomerAgent>().lambda().eq(CustomerAgent::getCustomerId, customerAgentVO.getCustomerId()).
                eq(CustomerAgent::getAgentUserId, customerAgentVO.getAgentUserId()).eq(CustomerAgent::getType, CustomerAgentTypeEnum.无));
        if (Objects.nonNull(customerAgent)) {
            customerAgentVO.setName(customerAgent.getName());
            customerAgentVO.setEnterpriseName(customerAgent.getEnterpriseName());
            customerAgentVO.setStage(customerAgent.getStage());
            //获取省市区
            Map<String, String> region = productComponent.getRegion(customerAgent.getAreaCode(), customerAgent.getCityCode(), customerAgent.getProvinceCode());
            if (com.baomidou.mybatisplus.core.toolkit.CollectionUtils.isNotEmpty(region)) {
                customerAgentVO.setAreaName(region.get("areaName"));
                customerAgentVO.setCityName(region.get("cityName"));
                customerAgentVO.setProvinceName(region.get("provinceName"));
            }
            customerAgentVO.setIntention(customerAgent.getIntention());
        }
    }


    /**
     * TODO 集合，增强返回参数追加
     *
     * @param customerAgentVOList 客户经纪人
     * @return List<CustomerAgentVO>
     * @author lijh
     * @methodName assignment
     * @time 2022-08-31 09:35:12
     */
    public List<CustomerAgentVO> assignment(List<CustomerAgentVO> customerAgentVOList) {
        customerAgentVOList.forEach(customerAgentVO -> {
        });
        return customerAgentVOList;
    }


    /**
     * TODO DO转化VO
     *
     * @param pageDO 客户经纪人
     * @return Page<CustomerAgentVO>
     * @author lijh
     * @methodName toPageVO
     * @time 2022-08-31 09:35:12
     */
    public Page<CustomerAgentVO> toPageVO(Page<CustomerAgent> pageDO) {
        Page<CustomerAgentVO> pageVO = new Page<CustomerAgentVO>();
        pageVO.setRecords(GeneralConvertor.convertor(pageDO.getRecords(), CustomerAgentVO.class));
        pageVO.setCurrent(pageDO.getCurrent());
        pageVO.setPages(pageDO.getPages());
        pageVO.setSize(pageDO.getSize());
        pageVO.setTotal(pageDO.getTotal());
        return pageVO;
    }
}