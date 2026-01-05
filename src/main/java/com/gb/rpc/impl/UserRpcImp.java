package com.gb.rpc.impl;

import com.gb.log.service.RpcLogService;
import com.gb.rpc.UserRpc;
import com.gb.utils.Json;
import com.gb.utils.enumeration.ReturnCode;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

/**
 * @ClassName ProductRpcImp
 * @Description 产品rpc熔断
 * @Author 孙凯伦
 * @Mobile 13777579028
 * @Email 376253703@qq.com
 * @Time 2021/6/15 11:53 上午
 */
@Slf4j
@Component
@SuppressWarnings("all")
public class UserRpcImp implements FallbackFactory<UserRpc> {

    /**
     * rpc日志
     */
    @Autowired
    private RpcLogService rpcLogService;
    /**
     * 接收服务
     */
    private final String receiveServer = "user";

    @Override
    public UserRpc create(Throwable cause) {
        return new UserRpc() {
            @Override
            public Optional<Json> queryLabelInUserByTypeParams(Map<String, Object> map) {
                rpcLogService.rpcLog(receiveServer, "/userQuery/queryLabelInUserByTypeParams", map, cause.getMessage());
                log.error("[USER服务]RPC获得指定标签下的用户信息异常 msg: {},  map:{}", cause.getMessage(), map);
                return Optional.of(new Json(ReturnCode.RPC服务出错));
            }

            @Override
            public Optional<Json> queryUserGroupByUserId(Map<String, Object> map) {
                rpcLogService.rpcLog(receiveServer, "/userTypeValue/queryUserGroupByUserId", map, cause.getMessage());
                log.error("[USER服务]RPC调用用户类型值表集合查询接口异常 msg: {},  map:{}", cause.getMessage(), map);
                return Optional.of(new Json(ReturnCode.RPC服务出错));
            }

            @Override
            public Optional<Json> queryUserInfoByUserId(Map<String, Object> map) {
                rpcLogService.rpcLog(receiveServer, "/userExtends/selectOne", map, cause.getMessage());
                log.error("[USER服务]RPC获得单个用户扩展信息异常 msg: {},  map:{}", cause.getMessage(), map);
                return Optional.of(new Json(ReturnCode.RPC服务出错));
            }

            @Override
            public Optional<Json> getOneUserInfo(Map<String, Object> map) {
                rpcLogService.rpcLog(receiveServer, "/user/selectOne", map, cause.getMessage());
                log.error("[USER服务]RPC获得单个用户全部信息异常 msg: {},  map:{}", cause.getMessage(), map);
                return Optional.of(new Json(ReturnCode.RPC服务出错));
            }

            @Override
            public Optional<Json> selectOneAgentCertificationByUserId(Map<String, Object> map) {
                rpcLogService.rpcLog(receiveServer, "/user-agent-certification/selectOne", map, cause.getMessage());
                log.error("[USER服务]RPC调用根据用户唯一标志获取经纪人认证信息 msg: {},  map:{}", cause.getMessage(), map);
                return Optional.of(new Json(ReturnCode.RPC服务出错));
            }

            @Override
            public Optional<Json> saveUserMessage(Map<String, Object> map) {
                rpcLogService.rpcLog(receiveServer, "/user-message/save", map, cause.getMessage());
                log.error("[USER服务]保存用户消息 msg: {},  map:{}", cause.getMessage(), map);
                return Optional.of(new Json(ReturnCode.RPC服务出错));
            }

            @Override
            public Optional<Json> selectOneRegionLabel(Map<String, Object> map) {
                rpcLogService.rpcLog(receiveServer, "/user-type-value-region/selectOne", map, cause.getMessage());
                log.error("[USER服务]查询地区绑定的标签 msg: {},  map:{}", cause.getMessage(), map);
                return Optional.of(new Json(ReturnCode.RPC服务出错));
            }

            @Override
            public Optional<Json> sendUserCode(Map<String, Object> map, Map<String, String> headerMap) {
                rpcLogService.rpcLog(receiveServer, "/sendCode", map, cause.getMessage());
                log.error("[USER服务]发送用户验证码 msg: {},  map:{},  headerMap:{}", cause.getMessage(), map, headerMap);
                return Optional.of(new Json(ReturnCode.RPC服务出错));
            }

            @Override
            public Optional<Json> userQuickLogin(Map<String, Object> map, Map<String, String> headerMap) {
                rpcLogService.rpcLog(receiveServer, "/quickLogin", map, cause.getMessage());
                log.error("[USER服务]快速登录 msg: {},  map:{},   headerMap:{}", cause.getMessage(), map, headerMap);
                return Optional.of(new Json(ReturnCode.RPC服务出错));
            }

            @Override
            public Optional<Json> getUserInvoice(Map<String, Object> map) {
                rpcLogService.rpcLog(receiveServer, "/user-invoice/select", map, cause.getMessage());
                log.error("[USER服务]用户发票表集合查询 msg: {},  map:{}", cause.getMessage(), map);
                return Optional.of(new Json(ReturnCode.RPC服务出错));
            }

            @Override
            public Optional<Json> getUserShippingAddress(Map<String, Object> map) {
                rpcLogService.rpcLog(receiveServer, "/user-shipping-address/select", map, cause.getMessage());
                log.error("[USER服务]用户收货地址集合查询 msg: {},  map:{}", cause.getMessage(), map);
                return Optional.of(new Json(ReturnCode.RPC服务出错));
            }

            @Override
            public Optional<Json> getUserList(Map<String, Object> map) {
                rpcLogService.rpcLog(receiveServer, "/user/selectList", map, cause.getMessage());
                log.error("[USER服务]查询用户信息集合 msg: {},  map:{}", cause.getMessage(), map);
                return Optional.of(new Json(ReturnCode.RPC服务出错));
            }

            @Override
            public Optional<Json> getTeamUser(Map<String, Object> map) {
                rpcLogService.rpcLog(receiveServer, "/team-user/selectOne", map, cause.getMessage());
                log.error("[USER服务]获取团队人员信息 msg: {},  map:{}", cause.getMessage(), map);
                return Optional.of(new Json(ReturnCode.RPC服务出错));
            }

            @Override
            public Optional<Json> getUserExtendsList(Map<String, Object> map) {
                rpcLogService.rpcLog(receiveServer, "/userExtends/selectList", map, cause.getMessage());
                log.error("[USER服务]用户扩展信息集合 msg: {},  map:{}", cause.getMessage(), map);
                return Optional.of(new Json(ReturnCode.RPC服务出错));
            }

            @Override
            public Optional<Json> getParentTeam(Map<String, Object> map) {
                rpcLogService.rpcLog(receiveServer, "/team/queryParentTeam", map, cause.getMessage());
                log.error("[USER服务]获取父级团队 msg: {},  map:{}", cause.getMessage(), map);
                return Optional.of(new Json(ReturnCode.RPC服务出错));
            }

            @Override
            public Optional<Json> getTeamAuthBrokerList(Map<String, Object> map) {
                rpcLogService.rpcLog(receiveServer, "/team-user/teamAuthBrokerSelectList", map, cause.getMessage());
                log.error("[USER服务]获取用户权限团队下的 所有经纪人 msg: {},  map:{}", cause.getMessage(), map);
                return Optional.of(new Json(ReturnCode.RPC服务出错));
            }

            @Override
            public Optional<Json> getPlatformSystemList(Map<String, Object> map) {
                rpcLogService.rpcLog(receiveServer, "/transformation-external-platform-system/selectList", map, cause.getMessage());
                log.error("[USER服务]获取外部系统平台列表   msg: {},  map:{}", cause.getMessage(), map);
                return Optional.of(new Json(ReturnCode.RPC服务出错));
            }
        };
    }
}
