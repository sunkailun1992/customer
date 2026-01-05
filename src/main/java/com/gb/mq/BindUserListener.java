package com.gb.mq;

import cn.hutool.core.date.LocalDateTimeUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.gb.bean.RabbitConfig;
import com.gb.customer.entity.AgentPeople;
import com.gb.customer.entity.Customer;
import com.gb.customer.entity.CustomerAgent;
import com.gb.customer.entity.PotentialCustomer;
import com.gb.customer.entity.enums.CustomerAgentStateEnum;
import com.gb.customer.entity.enums.CustomerAgentTypeEnum;
import com.gb.customer.enums.PotentialCustomerTypeEnum;
import com.gb.customer.service.AgentPeopleService;
import com.gb.customer.service.CustomerAgentService;
import com.gb.customer.service.CustomerService;
import com.gb.customer.service.PotentialCustomerService;
import com.gb.mq.crm.BindUserEvent;
import com.gb.mq.usermsg.UserMsgEvent;
import com.gb.rpc.entity.enums.TeamUserFormalStateEnum;
import com.gb.utils.enumeration.UserTypeEnum;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * 用户手机号修改、注销的时候，解除原手机号绑定的客户信息
 *
 * @author ljh
 * @date 2022/6/16 2:06 下午
 */
@Slf4j
@Component
@RabbitListener(queues = RabbitConfig.CRM_BIND_USER_MQ)
@Setter(onMethod_ = {@Autowired})
public class BindUserListener {

    private CustomerService customerService;

    @RabbitHandler
    public void untieCustomerInfo(BindUserEvent bindUserEvent) {
        log.info("用户修改/注销->>>,bindUserEvent={}", bindUserEvent);
        if (StringUtils.isEmpty(bindUserEvent.getMobile())) {
            return;
        }
        customerService.untieCustomerInfo(bindUserEvent);
    }
}
