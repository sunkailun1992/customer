package com.gb.rpc;

import com.gb.rpc.entity.CustomerMarketingInsuranceStatisticsQueryParam;
import com.gb.rpc.impl.InsuranceRpcImpl;
import com.gb.utils.Json;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

/**
 * 投保单
 *
 * @author lijinhao
 * @Date 2021/6/16
 */
@FeignClient(value = "insurance", fallbackFactory = InsuranceRpcImpl.class)
public interface InsuranceRpc {

    /**
     * 根据投保单号获取订单信息
     *
     * @param map
     * @return
     */
    @RequestMapping(value = "/cast-insurance/selectOne", method = RequestMethod.GET)
    Optional<Json> queryInsuranceSelectOne(@RequestParam Map<String, Object> map);

    /**
     * 根据投保单号获取用户信息
     *
     * @param map
     * @return
     */
    @RequestMapping(value = "/castInsuranceDistribution/selectOne", method = RequestMethod.GET)
    Optional<Json> getUserInfoByCastId(@RequestParam Map<String, Object> map);

    /**
     * 获取客户保费
     *
     * @param customerMarketingInsuranceStatisticsQueryParam
     * @return
     */
    @RequestMapping(value = "/statistic/workbenchMarketing/policyStatistics", method = RequestMethod.POST)
    Optional<Json> getCustomerPremium(@RequestBody CustomerMarketingInsuranceStatisticsQueryParam customerMarketingInsuranceStatisticsQueryParam);

    /**
     * 获取经纪人统计数据
     *
     * @param customerMarketingInsuranceStatisticsQueryParam
     * @return
     */
    @RequestMapping(value = "/cast-insurance/getCustomerMarketingInsuranceStatisticsResults", method = RequestMethod.POST)
    Optional<Json> getAgentStatistical(@RequestBody CustomerMarketingInsuranceStatisticsQueryParam customerMarketingInsuranceStatisticsQueryParam);

    /**
     * 线下单查询
     *
     * @param map
     * @return
     */
    @RequestMapping(value = "/offline-cast-insurance-data/select", method = RequestMethod.GET)
    Optional<Json> getOfflineCastInsurance(@RequestParam Map<String, Object> map);

}
