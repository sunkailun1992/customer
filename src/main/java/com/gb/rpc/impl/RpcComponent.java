package com.gb.rpc.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gb.rpc.InsuranceRpc;
import com.gb.rpc.ProductRpc;
import com.gb.rpc.RulesRpc;
import com.gb.rpc.UserRpc;
import com.gb.rpc.entity.CustomerMarketingInsuranceStatisticsQueryParam;
import com.gb.rpc.entity.enums.RpcTypeEnum;
import com.gb.utils.GeneralConvertor;
import com.gb.utils.Json;
import com.gb.utils.enumeration.ReturnCode;
import com.gb.utils.exception.BusinessException;
import com.gb.utils.exception.CustomerException;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * RPC调用
 *
 * @author ljh
 * @date 2021/11/25 2:24 下午
 */
@Component
@Slf4j
@Setter(onMethod_ = {@Autowired})
public class RpcComponent {

    private InsuranceRpc insuranceRpc;

    private ProductRpc productRpc;

    private RulesRpc rulesRpc;

    private UserRpc userRpc;

    /**
     * rpc调用，返回集合
     *
     * @param params;      请求参数
     * @param headerParams 请求投
     * @param returnType   返回类型
     * @return List<T>
     * @oaram rpcTypeEnum; 请求参数
     */
    public <T> List<T> rpcQueryList(Map<String, Object> params, Map<String, String> headerParams, RpcTypeEnum queryType, Class<T> returnType) {
        log.debug("RPC调用【{}】开始，请求参数信息：{}", queryType.toString(), params);
        Optional<Json> result = Optional.empty();
        switch (queryType) {
            //根据标签code获取标签下用户组的用户信息
            case GET_LABEL_USER_TYPE:
                result = userRpc.queryLabelInUserByTypeParams(params);
                break;
            case GET_USER_GROUP_BY_USERID:
                result = userRpc.queryUserGroupByUserId(params);
                break;
            //用户发票表集合查询
            case GET_USER_INVOICE:
                result = userRpc.getUserInvoice(params);
                break;
            //用户收货地址集合查询
            case GET_USER_SHIPPING_ADDRESS:
                result = userRpc.getUserShippingAddress(params);
                break;
            //查询用户信息集合
            case GET_USER_LIST:
                result = userRpc.getUserList(params);
                break;
            //用户扩展信息集合
            case GET_USER_EXTENDS_LIST:
                result = userRpc.getUserExtendsList(params);
                break;
            //获取用户权限团队下的 所有经纪人
            case GET_TEAM_AUTH_BROKER_LIST:
                result = userRpc.getTeamAuthBrokerList(params);
                break;
            //获取外部系统平台集合
            case GET_PLATFORM_SYSTEM_LIST:
                result = userRpc.getPlatformSystemList(params);
                break;
            //获取省分信息
            case GETPROVINCES:
                result = productRpc.getProvinceList(params);
                break;
            //获取省市信息
            case GETCITYS:
                result = productRpc.getCityList(params);
                break;
            default:
                break;
        }
        log.debug("RPC调用【{}】结束，请求参数信息：{}，返回信息：{}", queryType, params, JSON.toJSONString(result));
        if (result.isPresent() && result.get().getSuccess()) {
            return GeneralConvertor.convertor(Json.getList(result.get()), returnType);
        }
        String msg = result.isPresent() ? String.format("CRM调用rpc{%s}调用失败, 返回{%s}", queryType, result.get().getMsg()) : "";
        log.error("RPC调用失败,queryType={},msg={}", queryType, msg);
        throw new BusinessException(msg);
    }

    /**
     * rpc调用，返回单个对象
     *
     * @param params;    请求参数
     * @param returnType 返回类型
     * @return List<T>
     * @oaram rpcTypeEnum; 请求参数
     */
    public <T> T rpcQueryInfo(Map<String, Object> params, RpcTypeEnum queryType, Class<T> returnType) {
        log.debug("RPC调用【{}】开始，请求参数信息：{}", queryType.toString(), params);
        Optional<Json> result = Optional.empty();
        switch (queryType) {
            //订单服务: 根据投保单号获取订单信息
            case GET_CAST_INSURANCE_ID:
                result = insuranceRpc.queryInsuranceSelectOne(params);
                break;
            //订单服务: 根据保单号获取分销关系相关管家
            case GET_STEWARD_CAST_ID:
                result = insuranceRpc.getUserInfoByCastId(params);
                break;
            //规则服务:  获取默认分配规则
            case GET_USER_GROUP_CONDITIONS:
                result = rulesRpc.getUserGroupInfoByConditions(params);
                break;
            //根据userid获取用户详细信息
            case GET_ONE_USER:
                result = userRpc.getOneUserInfo(params);
                break;
            //根据userId获取用户信息
            case GET_USER_EXTENDS_ONE:
                result = userRpc.queryUserInfoByUserId(params);
                break;
            //根据用户唯一标志获取经纪人认证信息
            case GET_USER_AGENT_CERTIFICATION:
                result = userRpc.selectOneAgentCertificationByUserId(params);
                break;
            //保存用户消息
            case SAVE_USER_MESSAGE:
                result = userRpc.saveUserMessage(params);
                break;
            //获取单条险种
            case GET_ONE_DANGER_PLANTED:
                result = productRpc.selectOneDangerPlanted(params);
                break;
            //获取地区标签
            case GET_ONE_REGION_LABEL:
                result = userRpc.selectOneRegionLabel(params);
                break;
            //获取团队人员信息
            case GET_TEAM_USER:
                result = userRpc.getTeamUser(params);
                break;
            //获取客户保费
            case GET_CUSTOMER_PREMIUM:
                result = insuranceRpc.getCustomerPremium((CustomerMarketingInsuranceStatisticsQueryParam) params.get("marketingQueryParams"));
                break;
            //获取经纪人统计数据
            case GET_AGENT_STATISTICAL:
                result = insuranceRpc.getAgentStatistical((CustomerMarketingInsuranceStatisticsQueryParam) params.get("marketingQueryParams"));
                break;
            //获取父级团队
            case GET_PARENT_TEAM:
                result = userRpc.getParentTeam(params);
                break;
            //线下单查询
            case GET_OFFLINE_CAST_INSURANCE:
                result = insuranceRpc.getOfflineCastInsurance(params);
                break;
            //查询产品信息详情
            case GET_ONE_PRODUCT_SPU:
                result = productRpc.selectOneProductSpu(params);
                break;
            default:
                break;
        }
        log.debug("RPC调用【{}】结束，请求参数信息：{}，返回信息：{}", queryType, params, JSON.toJSONString(result));
        if (result.isPresent() && result.get().getSuccess()) {
            return JSONObject.parseObject(JSONObject.toJSONString(Json.get(result.get())), returnType);
        }
        String msg = result.isPresent() ? String.format("rpc{%s}调用失败, 返回{%s}", queryType, result.get().getMsg()) : "";
        throw new BusinessException(msg);
    }

    /**
     * 用户模块rpc调用，返回字符串
     *
     * @param params;      请求参数
     * @param headerParams 请求头
     * @param queryType    请求参数
     * @param returnType   返回类型
     * @return List<T>
     */
    public <T> T rpcQueryInfo(Map<String, Object> params, Map<String, String> headerParams, RpcTypeEnum queryType, Class<T> returnType) {
        log.debug("RPC调用【{}】开始，请求头：{},请求参数信息：{}", queryType.toString(), headerParams, params);
        Optional<Json> result = Optional.empty();
        switch (queryType) {
            //发送验证码
            case SEND_CODE:
                result = userRpc.sendUserCode(params, headerParams);
                break;
            //快速登录
            case QUICK_LOGIN:
                result = userRpc.userQuickLogin(params, headerParams);
                break;
            default:
                break;
        }
        log.debug("RPC调用【{}】结束，请求头：{},请求参数信息：{}，返回信息：{}", queryType, headerParams, params, JSON.toJSONString(result));
        if (result.isPresent() && result.get().getSuccess()) {
            return JSONObject.parseObject(JSONObject.toJSONString(result.get()), returnType);
        }
        String msg = result.isPresent() ? result.get().getErrorMessage() : "";
        throw new CustomerException(ReturnCode.用户端错误.getState(), msg);
    }
}
