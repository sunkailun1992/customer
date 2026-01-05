package com.gb.customer.template.event;

import org.springframework.context.ApplicationEvent;

/**
 * 线索事件
 *
 * @author ljh
 * @date 2022/6/2 10:50 上午
 */
public class CluesEvent extends ApplicationEvent {

    /**
     * 线索id
     */
    private final String id;
    /**
     * 经纪人id
     */
    private final String agentUserId;
    /**
     * 经纪人类型（0：业务管家，1：外部经纪人，2：渠道经纪人，3：服务管家，4：普通经纪人）
     */
    private final Integer agentUserType;
    /**
     * 手机号
     */
    private final String mobile;
    /**
     * 类型（0：产品咨询，1：经纪人咨询）
     */
    private final Integer type;


    public CluesEvent(Object source, String id, String agentUserId, Integer agentUserType, String mobile, Integer type) {
        super(source);
        this.id = id;
        this.agentUserId = agentUserId;
        this.agentUserType = agentUserType;
        this.mobile = mobile;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public String getAgentUserId() {
        return agentUserId;
    }

    public Integer getAgentUserType() {
        return agentUserType;
    }

    public String getMobile() {
        return mobile;
    }

    public Integer getType() {
        return type;
    }
}
