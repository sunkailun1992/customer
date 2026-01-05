package com.gb.rpc.entity.enums;

import com.gb.rpc.entity.CastInsuranceDistributionVO;
import com.gb.utils.exception.ParameterNullException;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Optional;

/**
 * @author ljh
 * @date 2021/11/25 2:27 下午
 */
@Getter
public enum RpcTypeEnum {
    /**
     * RPC调用接口
     */
    GET_STEWARD_CAST_ID("GET_STEWARD_CAST_ID", "获取分销关系管家信息", CastInsuranceDistributionVO.class),
    GET_CAST_INSURANCE_ID("GET_CAST_INSURANCE_ID", "根据投保单号获取订单信息"),
    GET_LABEL_USER_TYPE("GET_LABEL_USER_TYPE", "根据标签code查询用户组信息"),
    GET_USER_GROUP_CONDITIONS("GET_USER_GROUP_CONDITIONS", "查询默认分配规则"),
    GET_ONE_USER("GET_ONE_USER", "查询用户信息单条"),
    GET_USER_EXTENDS_ONE("GET_USER_EXTENDS_ONE", "根据用户唯一标识查用户信息(userId)"),
    GET_USER_GROUP_BY_USERID("GET_USER_GROUP_BY_USERID", "用户类型值表集合查询，根据userid查询"),
    GET_USER_AGENT_CERTIFICATION("GET_USER_AGENT_CERTIFICATION", "根据用户唯一标志获取经纪人认证信息"),
    SAVE_USER_MESSAGE("SAVE_USER_MESSAGE", "保存用户消息"),
    GET_ONE_DANGER_PLANTED("GET_ONE_DANGER_PLANTED", "获取单条险种"),
    GET_ONE_REGION_LABEL("GET_ONE_REGION_LABEL", "查询地区绑定的标签"),
    SEND_CODE("SEND_CODE", "用户发送验证码"),
    QUICK_LOGIN("QUICK_LOGIN", "快速登录"),
    GET_USER_INVOICE("GET_USER_INVOICE", "用户发票表集合查询"),
    GET_USER_SHIPPING_ADDRESS("getUserShippingAddress", "用户收货地址集合查询"),
    GET_USER_LIST("GET_USER_LIST", "查询用户信息集合"),
    GET_TEAM_USER("GET_TEAM_USER", "获取团队人员信息"),
    GET_USER_EXTENDS_LIST("GET_USER_EXTENDS_LIST", "用户扩展信息集合"),
    GET_CUSTOMER_PREMIUM("GET_CUSTOMER_PREMIUM", "获取客户保费"),
    GET_PARENT_TEAM("GET_PARENT_TEAM", "获取父级团队"),
    GET_AGENT_STATISTICAL("GET_AGENT_STATISTICAL", "获取经纪人统计数据"),
    GET_TEAM_AUTH_BROKER_LIST("GET_TEAM_AUTH_BROKER_LIST", "获取用户权限团队下的 所有经纪人"),
    GET_OFFLINE_CAST_INSURANCE("GET_OFFLINE_CAST_INSURANCE", "线下单查询"),
    GET_ONE_PRODUCT_SPU("GET_ONE_PRODUCT_SPU", "获取产品详情"),
    GET_PLATFORM_SYSTEM_LIST("GET_PLATFORM_SYSTEM_LIST", "获取外部系统平台list"),
    GETPROVINCES("GETPROVINCES", "获取省"),
    GETCITYS("GETCITYS", "获取市"),;


    /**
     * 码值
     */
    private String code;

    /**
     * 含义
     */
    private String desc;

    /**
     * 接收数据class
     */
    private Class<?> rpcReturnClass;

    RpcTypeEnum(String code, String desc, Class<?> rpcReturnClass) {
        this.code = code;
        this.desc = desc;
        this.rpcReturnClass = rpcReturnClass;
    }

    RpcTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static RpcTypeEnum getRpcType(String code) {
        if (StringUtils.isBlank(code)) {
            throw new ParameterNullException("平台编码不能为空!");
        }
        Optional<RpcTypeEnum> codeEnum = Arrays.stream(values()).filter(x -> StringUtils.equals(code, x.getCode())).findFirst();
        return codeEnum.orElseThrow(() -> new ParameterNullException("平台编码不存在!"));
    }
}
