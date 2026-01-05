package com.gb.rpc.impl;

import com.gb.log.service.RpcLogService;
import com.gb.rpc.InsuranceRpc;
import com.gb.rpc.entity.CustomerMarketingInsuranceStatisticsQueryParam;
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
 * @author lijh
 * @Date 2021/6/17
 */
@Component
@Slf4j
public class InsuranceRpcImpl implements FallbackFactory<InsuranceRpc> {
    /**
     * rpc日志
     */
    @Autowired
    private RpcLogService rpcLogService;
    /**
     * 接收服务
     */
    private final String receiveServer = "insurance";

    @Override
    public InsuranceRpc create(Throwable throwable) {
        return new InsuranceRpc() {
            @Override
            public Optional<Json> queryInsuranceSelectOne(Map<String, Object> map) {
                rpcLogService.rpcLog(receiveServer, "/cast-insurance/selectOne", map, throwable.getMessage());
                log.error("【订单服务】获取单条投保单数据异常 msg: {},  map:{}", throwable.getMessage(), map);
                return Optional.of(new Json(ReturnCode.RPC服务出错));
            }

            @Override
            public Optional<Json> getUserInfoByCastId(Map<String, Object> map) {
                rpcLogService.rpcLog(receiveServer, "/castInsuranceDistribution/selectOne", map, throwable.getMessage());
                log.error("【订单服务】获取单条投保单管家信息异常 msg: {},  map:{}", throwable.getMessage(), map);
                return Optional.of(new Json(ReturnCode.RPC服务出错));
            }

            @Override
            public Optional<Json> getCustomerPremium(CustomerMarketingInsuranceStatisticsQueryParam customerMarketingInsuranceStatisticsQueryParam) {
                rpcLogService.rpcLog(receiveServer, "/statistic/workbenchMarketing/policyStatistics", customerMarketingInsuranceStatisticsQueryParam, throwable.getMessage());
                log.error("【订单服务】获取客户保费信息异常 msg: {},  map:{}", throwable.getMessage(), customerMarketingInsuranceStatisticsQueryParam);
                return Optional.of(new Json(ReturnCode.RPC服务出错));
            }

            @Override
            public Optional<Json> getAgentStatistical(CustomerMarketingInsuranceStatisticsQueryParam customerMarketingInsuranceStatisticsQueryParam) {
                rpcLogService.rpcLog(receiveServer, "/cast-insurance/getCustomerMarketingInsuranceStatisticsResults", customerMarketingInsuranceStatisticsQueryParam, throwable.getMessage());
                log.error("【订单服务】获取经纪人统计数据 msg: {},  map:{}", throwable.getMessage(), customerMarketingInsuranceStatisticsQueryParam);
                return Optional.of(new Json(ReturnCode.RPC服务出错));
            }

            @Override
            public Optional<Json> getOfflineCastInsurance(Map<String, Object> map) {
                rpcLogService.rpcLog(receiveServer, "/offline-cast-insurance-data/select", map, throwable.getMessage());
                log.error("【订单服务】线下单查询 msg: {},  map:{}", throwable.getMessage(), map);
                return Optional.of(new Json(ReturnCode.RPC服务出错));
            }
        };
    }
}
