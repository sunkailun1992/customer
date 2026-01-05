package com.gb.mq;

import cn.hutool.core.date.LocalDateTimeUtil;
import com.gb.SpringTest;
import com.gb.customer.enums.PotentialCustomerTypeEnum;
import com.gb.mq.usermsg.UserMsgEvent;
import org.junit.Test;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

/**
 * @author ljh
 * @date 2022/6/6 9:34 上午
 */
public class UserMesTest extends SpringTest {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Test
    public void userMesMq(){
        UserMsgEvent userMsgEvent = new UserMsgEvent();
        userMsgEvent.setUserId("107731927");
        userMsgEvent.setContent(String.format(PotentialCustomerTypeEnum.getCustomerTypeEnum(0).getMsg(), "1533624119134195714"));
        userMsgEvent.setUserType(1);
        userMsgEvent.setMsgType(PotentialCustomerTypeEnum.getCustomerTypeEnum(0).getMsgType());
        userMsgEvent.setCreateDate(LocalDateTimeUtil.formatNormal(LocalDateTime.now()));
        rabbitTemplate.convertAndSend("userInsuranceMsgQ", userMsgEvent);
    }

}
