package com.gb.customer.template.impl;

import com.gb.aliyun.sms.SmsUtils;
import com.gb.customer.enums.PotentialCustomerTypeEnum;
import com.gb.customer.template.event.CluesEvent;
import com.gb.rpc.entity.UserExtendsVO;
import com.gb.rpc.entity.enums.RpcTypeEnum;
import com.gb.rpc.impl.RpcComponent;
import com.gb.utils.JsonUtil;
import com.gb.utils.enumeration.SmsEnum;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 短信监听
 *
 * @author ljh
 * @date 2022/6/2 10:48 上午
 */
@Slf4j
@Service
public class SmsService {
    @Resource
    private RpcComponent rpcComponent;

    @EventListener
    public void addSms(CluesEvent event) {
        log.debug("投保咨询/经纪人咨询发送短信,线索id={},经纪人id={}", event.getId(), event.getAgentUserId());
        String userId = event.getAgentUserId();
        //给线索绑定的经纪人发送短信
        Map<String, Object> map = new HashMap<>(1);
        map.put("userId", userId);
        UserExtendsVO userExtendsVO = rpcComponent.rpcQueryInfo(map, RpcTypeEnum.GET_USER_EXTENDS_ONE, UserExtendsVO.class);
        if (StringUtils.isEmpty(userExtendsVO.getMobile())) {
            log.error("短信发送不成功,未查到团队长手机号,agentUserId={},userId={},mobile={}", event.getAgentUserId(), userId, userExtendsVO.getMobile());
            return;
        }
        Map<String, Object> templateParam = Maps.newHashMap();
        templateParam.put("potentialCustomerId", event.getId());
        SmsUtils.sendMessage(userExtendsVO.getMobile(),
                PotentialCustomerTypeEnum.CUSTOMER_TYPE_0.getCode().equals(event.getType()) ? SmsEnum.getSmsEnum("投保咨询") : SmsEnum.getSmsEnum("经纪人咨询"),
                JsonUtil.json(templateParam));
    }
}
