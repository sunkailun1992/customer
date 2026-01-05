package com.gb.mq;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gb.bean.RabbitConfig;
import com.gb.customer.entity.CustomerCastInsurance;
import com.gb.customer.mapper.CustomerCastInsuranceMapper;
import com.gb.customer.service.CustomerCastInsuranceService;
import com.gb.customer.service.CustomerService;
import com.gb.mq.crm.InsuredCustomerEvent;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * TODO 这一步不再生成客户线索
 * 直接投保,客户生成
 *
 * @author lijinhao
 * @Date 2021/6/8
 */
@Slf4j
@Component
@RabbitListener(queues = RabbitConfig.SUBMIT_INSURED_Q)
@Setter(onMethod_ = {@Autowired})
public class CustomerListener {

    private CustomerService customerService;
    private CustomerCastInsuranceMapper customerCastInsuranceMapper;
    private CustomerCastInsuranceService customerCastInsuranceService;

    @RabbitHandler
    public void insuredCustomerHandler(InsuredCustomerEvent insuredCustomerEvent) {
        log.debug("接收直接投保的MQ消息..." + insuredCustomerEvent);
        if (Objects.isNull(insuredCustomerEvent.getUserType())) {
            log.error("未知消息类型：" + insuredCustomerEvent);
            return;
        }
        try {
            CustomerCastInsurance customerCastInsurance = customerCastInsuranceMapper.selectOne(new QueryWrapper<CustomerCastInsurance>().lambda().
                    eq(CustomerCastInsurance::getCastInsuranceId, insuredCustomerEvent.getCastInsuranceId()).last("limit 1"));
            if (null != customerCastInsurance) {
                log.error("此订单已存在,订单号:" + insuredCustomerEvent.getCastInsuranceId());
                return;
            }
            //获取分销表订单信息。
            customerCastInsuranceService.getInsuredCustomerEvent(insuredCustomerEvent);
            //TODO 改动: 为渠道(工保金) 不分配服务管家。非渠道crm自动分配服务管家
            if (insuredCustomerEvent.getChannelCast()) {
                customerService.saveGbjCustomerInsuredMq(insuredCustomerEvent);
            } else {
                customerService.saveCustomerInsuredMq(insuredCustomerEvent);
            }
        } catch (Exception e) {
            log.error("直接投保订单同步客户信息失败", e);
        }

    }
}
