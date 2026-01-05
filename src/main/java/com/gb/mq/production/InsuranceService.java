package com.gb.mq.production;


import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.gb.bean.RabbitConfig;
import com.gb.mq.crm.AgentRelationsChangeEvent;
import com.gb.mq.enums.OperationSourceTypeEnum;
import com.gb.rpc.component.UserComponent;
import com.gb.rpc.entity.UserExtendsVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 订单服务
 *
 * @author lijh
 */
@Slf4j
@Service
public class InsuranceService {

    @Resource
    private RabbitTemplate rabbitTemplate;
    @Resource
    private UserComponent userComponent;

    /**
     * 通知订单服务  更改对应经纪人  多个客户
     *
     * @param userIds     投保用户id集合
     * @param agentUserId 经纪人id
     * @param type        经纪人类型 (0：自营，1：分销)
     * @param changeType  变更类型 (1：新增， 2： 解绑)
     */
    public void sendInsurance(List<String> userIds, String agentUserId, Integer type, Integer changeType, OperationSourceTypeEnum operationSourceTypeEnum) {
        if (CollectionUtils.isNotEmpty(userIds)) {
            for (String userId : userIds) {
                sendInsurance(userId, agentUserId, type, changeType, operationSourceTypeEnum);
            }
        }
    }

    /**
     * 通知订单服务  更改对应经纪人
     *
     * @param userId      投保用户id
     * @param agentUserId 经纪人id
     * @param type        经纪人类型 (0：自营，1：分销)
     * @param changeType  变更类型 (1：新增， 2： 解绑)
     */
    public void sendInsurance(String userId, String agentUserId, Integer type, Integer changeType, OperationSourceTypeEnum operationSourceTypeEnum) {
        try {
            log.debug("sendInsurance ->>> {}操作后,推送订单消息,投保用户id={},经纪人id={},经纪人类型={}", operationSourceTypeEnum.getDesc(), userId, agentUserId, type);
            AgentRelationsChangeEvent agentRelationsChangeEvent = new AgentRelationsChangeEvent();
            agentRelationsChangeEvent.setCastUserId(userId);
            agentRelationsChangeEvent.setAgentUserType(type);
            agentRelationsChangeEvent.setAgentUserId(agentUserId);
            agentRelationsChangeEvent.setChangeType(changeType);

            UserExtendsVO userExtendsVO = userComponent.getUserExtendsVOByUserId(agentUserId);
            agentRelationsChangeEvent.setAgentUserName(userExtendsVO.getName());
            agentRelationsChangeEvent.setAgentUserPhone(userExtendsVO.getMobile());

            rabbitTemplate.convertAndSend(RabbitConfig.AGENT_RELATIONS_CHANGE_Q, agentRelationsChangeEvent);
            log.debug("成功发送mq消息 mqKey {} content {}", RabbitConfig.AGENT_RELATIONS_CHANGE_Q, agentRelationsChangeEvent);
        } catch (Exception e) {
            log.error("mq发送失败 mqKey" + RabbitConfig.AGENT_RELATIONS_CHANGE_Q, e);
        }
    }
}
