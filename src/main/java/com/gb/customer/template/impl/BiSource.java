package com.gb.customer.template.impl;

import cn.hutool.core.convert.Convert;
import com.alibaba.schedulerx.shade.org.apache.commons.collections.CollectionUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gb.customer.entity.*;
import com.gb.customer.entity.enums.CustomerAgentStateEnum;
import com.gb.customer.entity.enums.CustomerAgentTypeEnum;
import com.gb.customer.enums.CustomerDataTypeEnum;
import com.gb.customer.enums.CustomerStateEnum;
import com.gb.customer.enums.SourceTypeEnum;
import com.gb.customer.factory.PotentialCustomerFactory;
import com.gb.customer.mapper.CustomerAssociatedMapper;
import com.gb.customer.service.CustomerAgentService;
import com.gb.customer.service.CustomerService;
import com.gb.customer.service.CustomerSourceService;
import com.gb.customer.service.PotentialCustomerService;
import com.gb.customer.template.AbstractCluesTemplate;
import com.gb.customer.template.CluesPriorityEnum;
import com.gb.rpc.component.UserComponent;
import com.gb.rpc.entity.UserExtendsVO;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * BI来源
 *
 * @author ljh
 * @date 2022/3/10 11:17 上午
 */
@Slf4j
@Service
@Setter(onMethod_ = {@Autowired})
public class BiSource extends AbstractCluesTemplate<List<PotentialCustomer>> {

    private PotentialCustomerService potentialCustomerService;
    private UserComponent userComponent;

    private CustomerAssociatedMapper customerAssociatedMapper;

    private CustomerAgentService customerAgentService;

    private CustomerService customerService;

    private CustomerSourceService customerSourceService;


    /**
     * 校验筛选
     *
     * @param potentialCustomerList
     * @return
     */
    @Override
    public Boolean checkScreening(List<PotentialCustomer> potentialCustomerList) {
        return CollectionUtils.isEmpty(potentialCustomerList);
    }

    @Override
    public Boolean isBeInvited(List<PotentialCustomer> potentialCustomerList) {
        return false;
    }

    /**
     * 分配管家
     *
     * @param potentialCustomerList
     */
    @Override
    public void allotSteward(List<PotentialCustomer> potentialCustomerList) {
        log.info("BI来源默认不分配管家");
    }

    /**
     * 新增
     *
     * @param potentialCustomerList
     * @return
     */
    @Override
    public Boolean saveCluesData(List<PotentialCustomer> potentialCustomerList, HttpServletRequest httpServletRequest) {
        log.info("BI来源新增,potentialCustomerList={}", potentialCustomerList);
        CustomerSource customerSource = customerSourceService.getOne(new QueryWrapper<CustomerSource>().lambda().eq(CustomerSource::getName, SourceTypeEnum.BI系统.getDesc()));
        if (Objects.isNull(customerSource)) {
            log.error("BI线索新增失败,未查到来源id");
            return false;
        }
        for (PotentialCustomer pc : potentialCustomerList) {
            pc.setPotentialCustomerSourceId(SourceTypeEnum.SOURCE_TYPE_16.getCode());
            pc.setState(1);
            pc.setDataType(CustomerDataTypeEnum.DATA_TYPE_0.getCode());
            pc.setDescription("BI来源新增线索");

            PotentialCustomerFactory.getBeanPotentialCustomer(pc);
            saveCustomer(customerSource, pc);
        }
        return true;
    }

    @Override
    public String priority() {
        return CluesPriorityEnum.BI.getValue();
    }


    /**
     * 生成客户相关信息
     *
     * @param customerSource
     * @param pc
     */
    private void saveCustomer(CustomerSource customerSource, PotentialCustomer pc) {
        Customer customer = customerService.getOne(new QueryWrapper<Customer>().lambda().eq(Customer::getMobile, pc.getMobile()));
        //默认客户线索关联为副关联
        CustomerAssociated customerAssociated = new CustomerAssociated();
        customerAssociated.setType(0);
        if (Objects.isNull(customer)) {
            UserExtendsVO userExtendsVO = userComponent.getUserExtendsVOByMobile(pc.getMobile());
            customer = new Customer();
            customer.setMobile(pc.getMobile());
            customer.setState(StringUtils.isNotEmpty(userExtendsVO.getUserId()) ? CustomerStateEnum.已注册.getValue() : CustomerStateEnum.未注册.getValue());
            customer.setUserId(Convert.toStr(userExtendsVO.getUserId()));
            customer.setName(pc.getName());
            customer.setCustomerSourceId(customerSource.getId());
            customer.setAllocateDateTime(LocalDateTime.now());
            customerService.save(customer);
            customerAssociated.setType(1);
        }

        CustomerAgent customerAgentOne = customerAgentService.getOne(new QueryWrapper<CustomerAgent>().lambda().eq(CustomerAgent::getCustomerId, customer.getId()).
                eq(CustomerAgent::getType, CustomerAgentTypeEnum.自营).eq(CustomerAgent::getState, CustomerAgentStateEnum.主关联));
        if (Objects.nonNull(customerAgentOne)) {
            pc.setAgentUserId(customerAgentOne.getAgentUserId());
            pc.setAgentUserName(userComponent.getOneUserByUserId(customerAgentOne.getAgentUserId()).getName());
        }
        //生成线索
        potentialCustomerService.save(pc);
        //绑定客户和线索
        customerAssociated.setCustomerId(customer.getId());
        customerAssociated.setPotentialCustomerId(pc.getId());
        customerAssociatedMapper.insert(customerAssociated);
    }
}
