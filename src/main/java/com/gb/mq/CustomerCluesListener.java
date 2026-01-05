package com.gb.mq;

import com.gb.bean.RabbitConfig;
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
 * 保存投保,客户线索生成 TODO 暂时不用
 *
 * @author lijinhao
 * @Date 2021/6/8
 */
@Slf4j
@Component
@RabbitListener(queues = RabbitConfig.SAVE_INSURED_Q)
@Setter(onMethod_ = {@Autowired})
public class CustomerCluesListener {

    private CustomerService customerService;

    private CustomerCastInsuranceService customerCastInsuranceService;

    @RabbitHandler
    public void insuredCustomerCluesHandler(InsuredCustomerEvent insuredCustomerEvent) {
        log.debug("首次保存投保的MQ消息..." + insuredCustomerEvent);
        if (Objects.isNull(insuredCustomerEvent.getUserType())) {
            log.error("未知消息类型：" + insuredCustomerEvent);
            return;
        }
        try {
            //获取分销表订单信息。
            customerCastInsuranceService.getInsuredCustomerEvent(insuredCustomerEvent);
            //为工保金（渠道）  首次投保不生成线索
            if (insuredCustomerEvent.getChannelCast()) {
                log.debug("为工保金订单,不生成线索");
                return;
            }
            customerService.saveInsuredCustomerEvent(insuredCustomerEvent);
        } catch (Exception e) {
            log.error("保存投保订单同步客户线索失败", e);
        }
    }
}
