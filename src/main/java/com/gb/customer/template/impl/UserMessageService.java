package com.gb.customer.template.impl;

import cn.hutool.core.date.LocalDateTimeUtil;
import com.aliyuncs.utils.StringUtils;
import com.gb.bean.GongBaoConfig;
import com.gb.bean.RabbitConfig;
import com.gb.customer.enums.PotentialCustomerTypeEnum;
import com.gb.customer.template.event.CluesEvent;
import com.gb.mq.usermsg.UserMsgEvent;
import jodd.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * 用户消息
 *
 * @author ljh
 * @date 2022/6/2 11:08 上午
 */
@Slf4j
@Service
public class UserMessageService {

    @Resource
    private RabbitTemplate rabbitTemplate;

    @EventListener
    public void addUserMessage(CluesEvent event) {
        log.debug("mq推送用户消息,event={}", event);
        sendUserMessage(event.getId(), event.getAgentUserId(), event.getType());
    }

    /**
     * 推送用户消息
     *
     * @param id     服务单id
     * @param userId 用户id
     * @param type   0：客户咨询    1: 经纪人咨询
     */
    public void sendUserMessage(String id, String userId, Integer type) {
        try {
            log.debug("推送用户消息,服务单id={},用户id={},消息类型={}", id, userId, type);
            UserMsgEvent userMsgEvent = new UserMsgEvent();
            userMsgEvent.setUserId(userId);
            userMsgEvent.setContent(String.format(PotentialCustomerTypeEnum.getCustomerTypeEnum(type).getMsg(), id));
            userMsgEvent.setUserType(1);
            userMsgEvent.setMsgType(PotentialCustomerTypeEnum.getCustomerTypeEnum(type).getMsgType());
            userMsgEvent.setCreateDate(LocalDateTimeUtil.formatNormal(LocalDateTime.now()));
            rabbitTemplate.convertAndSend(RabbitConfig.USER_MSG_INSURANCE, userMsgEvent);
            log.debug("成功发送mq消息 mqKey {} content {}", RabbitConfig.USER_MSG_INSURANCE, userMsgEvent);
        } catch (Exception e) {
            log.error("mq发送失败 mqKey" + RabbitConfig.USER_MSG_INSURANCE, e);
        }
    }

    /**
     * 推送用户消息-判断是否用默认用户id
     * userId为空 推送给默认人
     *
     * @param id     服务单id
     * @param userId 用户id
     * @param type   0：客户咨询    1: 经纪人咨询
     */
    public void sendUserMessageIsDefault(String id, String userId, Integer type) {
        if (StringUtils.isEmpty(userId)) {
            userId = GongBaoConfig.highSeasAdminUserId;
        }
        sendUserMessage(id, userId, type);
    }
}
