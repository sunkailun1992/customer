package com.gb.mq.crm;


import lombok.Data;

/**
 * 通知订单服务的数据体
 *
 * @author lijh
 */
@Data
public class AgentRelationsChangeEvent {

    private static final Integer ADD = 1;
    private static final Integer CLEAR = 2;

    /**
     * 投保用户id
     */
    private String castUserId;


    /**
     * 经纪人类型(0：自营，1：分销)
     */
    private Integer agentUserType;


    /**
     * 解绑：2
     * 分配：1
     * 转交：先2后1  需推两次
     * 变更类型(1：新增， 2： 解绑)
     */
    private Integer changeType;

    /**
     * 经纪人id
     */
    private String agentUserId;

    /**
     * 经纪人姓名
     */
    private String agentUserName;


    /**
     * 经纪人电话
     */
    private String agentUserPhone;

    /**
     * 业务助理id
     */
    private String serviceStewardId;
    /**
     * 业务助理姓名
     */
    private String serviceStewardName;
    /**
     * 业务助理手机号
     */
    private String serviceStewardPhone;


    /**
     * 渠道经纪人id
     */
    private String channelAgentUserId;

    /**
     * 渠道经纪人姓名
     */
    private String channelAgentUserName;

    /**
     * 渠道经纪人电话
     */
    private String channelAgentUserPhone;

}
