package com.gb.mq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author lijinhao
 */
@Component
@RabbitListener(queues = "test")
public class TextReceiver {

    @RabbitHandler
    public void process(String json) throws Exception {
        System.out.printf(json);
    }

}