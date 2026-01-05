package com.gb.customer.service.results;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gb.customer.dto.CustomerLabelDto;
import com.gb.customer.entity.Customer;
import com.gb.customer.entity.CustomerAgent;
import com.gb.customer.entity.enums.CustomerAgentTypeEnum;
import com.gb.customer.entity.vo.CustomerAgentVO;
import com.gb.customer.entity.vo.CustomerVO;
import com.gb.customer.enums.CustomerStateEnum;
import com.gb.customer.service.CustomerAgentService;
import com.gb.mq.crm.BindUserEvent;
import com.gb.mq.crm.InsuredCustomerEvent;
import com.gb.mq.crm.RegistUserEvent;
import com.gb.rpc.component.UserComponent;
import com.gb.rpc.entity.UserVO;
import com.gb.utils.GeneralConvertor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;


/**
 * <p>
 * 客户表 服务类 Service返回实现
 * </p>
 *
 * @author lijh
 * @since 2021-06-08
 */
@Slf4j
@Service
@Setter(onMethod_ = {@Autowired})
public class CustomerServiceResults {

    private UserComponent userComponent;
    private CustomerAgentService customerAgentService;


    /**
     * 单条，增强返回参数追加
     *
     * @param customer:
     * @return Customer
     * @author lijh
     * @since 2021-06-08
     */
    public Customer assignment(Customer customer) {
        return customer;
    }

    /**
     * 分页,增强返回参数追加
     *
     * @param customerVOList:
     * @return Customer
     * @author lijh
     * @since 2021-06-08
     */
    public IPage<CustomerVO> assignment(Customer customerQuery, IPage<CustomerVO> customerVOList) {
        if (CollectionUtils.isEmpty(customerVOList.getRecords())) {
            return customerVOList;
        }
        customerVOList.getRecords().forEach(customerVO -> {
            //判断用户是否注册   未注册获取客户信息的手机号，   已注册获取用户注册手机号
            if (CustomerStateEnum.已注册.getValue().equals(customerVO.getState())) {
                //根据用户id获取用户信息   产品需求， 手机号搜用户的登录账号
                UserVO userVO = userComponent.getOneUserByUserId(customerVO.getUserId());
                customerVO.setMobile(userVO.getUserName());
                customerVO.setRegisterDateTime(userVO.getCreateDateTime());
                customerVO.setUserSourceName(userVO.getSourceName());
                customerVO.setUserSourceValueName(customerVO.getUserSourceValueName());
            }
            //校验当前操作账号  获取此账号对客户备注/客户意向
            if (StringUtils.isNotEmpty(customerQuery.getLoginAccountId())) {
                CustomerAgent one = customerAgentService.getOne(new QueryWrapper<CustomerAgent>().lambda().
                        eq(CustomerAgent::getCustomerId, customerVO.getId()).eq(CustomerAgent::getAgentUserId, customerQuery.getLoginAccountId()).eq(CustomerAgent::getType, CustomerAgentTypeEnum.无));
                if (Objects.nonNull(one)) {
                    customerVO.setIntention(Objects.nonNull(one.getIntention()) ? one.getIntention().getValue() : null);
                    customerVO.setEnterpriseName(one.getEnterpriseName());
                }
            }
        });
        return customerVOList;
    }


    /**
     * 集合,增强返回参数追加
     *
     * @param customerList:
     * @return Customer
     * @author lijh
     * @since 2021-06-08
     */
    public List<Customer> assignment(List<Customer> customerList) {
        customerList.forEach(customer -> {
        });
        return customerList;
    }


    /**
     * TODO DO转化VO
     *
     * @param pageDO 客户
     * @return Page<CustomerVO>
     * @author lijh
     * @methodName toPageVO
     * @time 2022-08-31 09:35:12
     */
    public Page<CustomerVO> toPageVO(Page<Customer> pageDO) {
        Page<CustomerVO> pageVO = new Page<CustomerVO>();
        pageVO.setRecords(GeneralConvertor.convertor(pageDO.getRecords(), CustomerVO.class));
        pageVO.setCurrent(pageDO.getCurrent());
        pageVO.setPages(pageDO.getPages());
        pageVO.setSize(pageDO.getSize());
        pageVO.setTotal(pageDO.getTotal());
        return pageVO;
    }
}
