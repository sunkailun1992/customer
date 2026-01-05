package com.gb.rpc.impl;

import com.gb.log.service.RpcLogService;
import com.gb.rpc.RulesRpc;
import com.gb.rpc.entity.UserGroupInfo;
import com.gb.utils.Json;
import com.gb.utils.enumeration.ReturnCode;
import com.gb.utils.exception.RpcException;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

/**
 * @author ljh
 * @date 2021/9/15 4:47 下午
 */
@Component
@Slf4j
public class RulesRpcImpl implements FallbackFactory<RulesRpc> {

    /**
     * rpc日志
     */
    @Autowired
    private RpcLogService rpcLogService;
    /**
     * 接收服务
     */
    private final String receiveServer = "rules";

    @Override
    public RulesRpc create(Throwable throwable) {
        return new RulesRpc() {
            @Override
            public Optional<Json> getUserGroupInfoByConditions(Map<String, Object> map) {
                rpcLogService.rpcLog(receiveServer, "/rules/getUserGroupInfoByConditions", map, throwable.getMessage());
                log.error("【规则服务】查询默认分配规则异常 msg: {},  map:{}", throwable.getMessage(), map);
                return Optional.of(new Json(ReturnCode.RPC服务出错));
            }
        };
    }
}
