package com.gb.bean;

import com.gb.utils.DynamicSourceTtl;
import jodd.util.StringUtil;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * mq配置
 *
 * @author lijinhao
 */
@Configuration
public class RabbitConfig {

    /**
     * 工保网直接投保
     */
    public static final String SUBMIT_INSURED_Q = "submitInsuredQ";

    /**
     * 工保网保存投保
     */
    public static final String SAVE_INSURED_Q = "saveInsuredQ";

    /**
     * 工保网注册用户
     */
    public static final String REGIST_USER_Q = "registUserQ";

    /**
     * 用户消息推送
     */
    public static final String USER_MSG_INSURANCE = "userInsuranceMsgQ";

    /**
     * 绑定用户队列
     */
    public static final String CRM_BIND_USER_MQ = "bindUserQ";

    /***********crm更新用户关系start*************/
    public static final String AGENT_RELATIONS_CHANGE_Q = "agentRelationsChangeQ";
    /***********crm更新用户关系end*************/

    /**
     * rabbitmq
     */
    @Value("${rabbitmq.host}")
    private String rabbitmqHost;
    @Value("${rabbitmq.port}")
    private Integer rabbitmqPort;
    @Value("${rabbitmq.username}")
    private String rabbitmqUsername;
    @Value("${rabbitmq.password}")
    private String rabbitmqPassword;
    @Autowired
    private ConfigurableApplicationContext applicationContext;

    /**
     * 测试
     *
     * @return
     */
    @Bean
    public Queue test() {
        return new Queue("test");
    }

    @Bean
    public Queue submitInsuredQueue() {
        return new Queue(SUBMIT_INSURED_Q);
    }

    @Bean
    public Queue saveInsuredqueue() {
        return new Queue(SAVE_INSURED_Q);
    }

    @Bean
    public Queue registUserQ() {
        return new Queue(REGIST_USER_Q);
    }

    @Bean
    public Queue userInsuranceMsgQ() {
        return new Queue(USER_MSG_INSURANCE);
    }

    @Bean
    public Queue bindUserQ() {
        return new Queue(CRM_BIND_USER_MQ);
    }

    @Bean
    public Queue agentRelationsChangeQ() {
        return new Queue(AGENT_RELATIONS_CHANGE_Q);
    }

    /**
     * rabbitmq 处理连接端口
     *
     * @return
     */
    @Bean
    public CachingConnectionFactory connectionFactory() {
        String virtualHost = "rabbitmq.virtualHost";
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(rabbitmqHost, rabbitmqPort);
        connectionFactory.setUsername(rabbitmqUsername);
        connectionFactory.setPassword(rabbitmqPassword);
        if (StringUtil.isNotBlank(applicationContext.getEnvironment().getProperty(virtualHost))) {
            connectionFactory.setVirtualHost(applicationContext.getEnvironment().getProperty(virtualHost));
        }
        return connectionFactory;
    }

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        RabbitAdmin admin = new RabbitAdmin(connectionFactory);
        admin.setAutoStartup(true);
        return admin;
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setUseDirectReplyToContainer(false);
        //发送之前加一个拦截器，每次发送都会调用这个方法，方法名称已经说明了一切了
        template.setBeforePublishPostProcessors(new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                //拦截逻辑添加环境变量
                message.getMessageProperties().getHeaders().put("dataSource", DynamicSourceTtl.get());
                return message;
            }
        });
        template.setMessageConverter(messageConverter());
        return template;
    }


    @Bean(name = "rabbitListenerContainerFactory")
    @ConditionalOnProperty(prefix = "spring.rabbitmq.listener", name = "type", havingValue = "simple", matchIfMissing = true)
    public SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory(SimpleRabbitListenerContainerFactoryConfigurer configurer,
                                                                                     ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        String dataSource = "dataSource";
        //消息接收之前加拦截处理，每次接收消息都会调用，标记消息的，先存到副本变量，后续的操作数据库根据这个变量进行切换影子库
        factory.setAfterReceivePostProcessors(new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                Map header = message.getMessageProperties().getHeaders();
                //判断是动态切换影子库
                if (StringUtil.isNotBlank(String.valueOf(header.get(dataSource)))) {
                    DynamicSourceTtl.push(String.valueOf(header.get(dataSource)));
                }
                return message;
            }
        });
        configurer.configure(factory, connectionFactory);
        return factory;
    }


    @Bean
    public MessageConverter messageConverter() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.registerModule(new JavaTimeModule());
        return new Jackson2JsonMessageConverter(objectMapper);
    }

}