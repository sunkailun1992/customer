package com.gb.mq;

import com.gb.bean.RabbitConfig;
import com.gb.customer.service.CustomerService;
import com.gb.mq.crm.RegistUserEvent;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * 线索生成
 *
 * @author ljh
 * @Date 2021/9/6
 */
@Slf4j
@Component
@RabbitListener(queues = RabbitConfig.REGIST_USER_Q)
@Setter(onMethod_ = {@Autowired})
public class CluesGenerateListener {

    private CustomerService customerService;

//    private QuotationFormInvitationService quotationFormInvitationService;

    @RabbitHandler
    public void generateCluesHandler(RegistUserEvent registUserEvent) {
        try {
            TimeUnit.SECONDS.sleep(5);
            log.debug("接收用户注册mq消息={}", registUserEvent);
            customerService.registerCustomer(registUserEvent);
            //如果为报价工具邀请注册，则记录用户邀请次数
//            if (StringUtils.isNotEmpty(registUserEvent.getInviteUserId()) && StringUtils.isNotEmpty(registUserEvent.getBusinessDetails())) {
//                quotationFormInvitationService.save(new QuotationFormInvitation() {{
//                    setQuotationFormId(registUserEvent.getBusinessDetails());
//                    setInvitationUserId(registUserEvent.getInviteUserId());
//                    setRegisterUserId(registUserEvent.getUserId());
//                }});
//            }
        } catch (Exception e) {
            log.error("用户注册生成线索失败");
        }
    }

}
