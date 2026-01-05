package com.gb.mq.crm;

import com.gb.utils.enumeration.UserTypeEnum;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 投保mq接收数据
 *
 * @author lijinhao
 * @Date 2021/6/8
 */
@Data
public class InsuredCustomerEvent {
    /**
     * 投保用户id(首次用户投保时，当前登录人的)
     */
    private String castInsuranceUserId;
    /**
     * 用户类型（0：用户端，1：经纪人，2：渠道普通用户，3:业务管家，4:特别业务管家，5:渠道经纪人，6:业务助理）
     */
    private Integer userType;
    /**
     * 投保人手机号
     */
    private String mobile;
    /**
     * 投保人姓名
     */
    private String name;

    /**
     * 投保单id
     */
    private String castInsuranceId;


    //***  新加
    /**
     * 业务管家id
     */
    private String agentUserId;

    /**
     * 业务管家组id
     */
    private String agentGroupId = UserTypeEnum.业务管家.getTypeCode();

    /**
     * 业务管家姓名
     */
    private String agentUserName;

    /**
     * 业务管家姓名
     */
    private String agentUserPhone;

    /**
     * 经纪人类型  0 自营  1 分销
     */
    private Integer type;


    /**
     * TODO 废弃
     * 外部经纪人id
     */
    private String outAgentUserId;

    /**
     * 外部经纪人名字
     */
    private String outAgentName;

    /**
     * 外部经纪人手机号
     */
    private String outAgentPhone;
    /**
     * 外部经纪人组id
     */
    private String outAgentGroupId;


    /**
     * TODO 废弃
     * 服务管家id
     */
    private String serviceStewardId;
    /**
     * 服务管家姓名
     */
    private String serviceStewardName;
    /**
     * 服务管家手机号
     */
    private String serviceStewardPhone;
    /**
     * 服务管家组id
     */
    private String serviceStewardGroupId = UserTypeEnum.业务助理管家.getTypeCode();


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


    /**
     * TODO 废弃
     * 特殊管家（特别经纪人投保的管家）id
     */
    private String specialAgentUserId;

    /**
     * 特殊管家（特别经纪人投保的管家）姓名
     */
    private String specialAgentUserName;

    /**
     * 特殊管家（特别经纪人投保的管家）电话
     */
    private String specialAgentUserPhone;

    /**
     * 特殊管家组id
     */
    private String specialAgentGroupId = UserTypeEnum.特别业务管家.getTypeCode();
    ;


    /*新加*/


    /**
     * ------------------------------投保单需要信息 --------------------------------
     * 险种id
     */
    private String dangerPlantedId;
    /**
     * 险种名称
     */
    private String dangerPlantedName;
    /**
     * 咨询产品id
     */
    private String spuId;
    /**
     * 地址
     */
    private String address;
    /**
     * 企业名称
     */
    private String enterpriseName;
    /**
     * 企业唯一信用代码
     */
    private String enterpriseCode;
    /**
     * 企业id
     */
    private String enterpriseId;
    /**
     * 省id
     */
    private String provinceCode;
    /**
     * 省name
     */
    private String provinceName;
    /**
     * 市id
     */
    private String cityCode;
    /**
     * 市name
     */
    private String cityName;
    /**
     * 区id
     */
    private String areaCode;
    /**
     * 区name
     */
    private String areaName;
    /**
     * 险种分类id
     */
    private String dangerPlantedCategoryId;
    /**
     * 险种分类名称
     */
    private String dangerPlantedCategoryName;

    /**
     * 是否渠道投保
     */
    private Boolean channelCast;

    /**
     * 投保时间
     */
    private LocalDateTime submitTime;

    /**
     * 提交人手机号(第二次提交的人，当前登录的账号)
     */
    private String castPhone;


    /*******  非mq传的参数  *********/
    /**
     * 客户id
     */
    private String customerId;
    /**
     * 线索id
     */
    private String potentialCustomerId;

    /**
     * 订单来源
     * {@link com.gb.rpc.entity.enums.InsuranceSourceEnum}
     */
    private String source;
}
