package com.gb.rpc.impl;

import com.gb.log.service.RpcLogService;
import com.gb.rpc.ProductRpc;
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
 * @ClassName ProductRpcImp
 * @Description 产品rpc熔断
 * @Author 孙凯伦
 * @Mobile 13777579028
 * @Email 376253703@qq.com
 * @Time 2021/6/15 11:53 上午
 */
@Slf4j
@Component
public class ProductRpcImp implements FallbackFactory<ProductRpc> {

    /**
     * rpc日志
     */
    @Autowired
    private RpcLogService rpcLogService;
    /**
     * 接收服务
     */
    private final String receiveServer = "product";

    @Override
    public ProductRpc create(Throwable throwable) {
        return new ProductRpc() {
            @Override
            public Json productSpuSelectOne(Map<String, String> map) {
                rpcLogService.rpcLog(receiveServer, "/product-spu/selectOne", map, throwable.getMessage());
                throw new RpcException(throwable.getMessage());
            }

            @Override
            public Optional<Json> selectOneProductSpu(Map<String, Object> map) {
                rpcLogService.rpcLog(receiveServer, "/product-spu/selectOne", map, throwable.getMessage());
                log.error("[产品服务]RPC获得产品详情异常 msg: {},  map:{}", throwable.getMessage(), map);
                return Optional.of(new Json(ReturnCode.RPC服务出错));
            }

            @Override
            public Optional<Json> selectProvinceOne(Map<String, String> map) {
                return Optional.empty();
            }

            @Override
            public Optional<Json> selectCityOne(Map<String, String> map) {
                return Optional.empty();
            }

            @Override
            public Optional<Json> selectAreaOne(Map<String, String> map) {
                return Optional.empty();
            }

            @Override
            public Optional<Json> selectAreaCode(Map<String, String> map) {
                return Optional.empty();
            }

            @Override
            public Optional<Json> findByEveryArea(Map<String, Object> map) {
                rpcLogService.rpcLog(receiveServer, "/area/findByEveryArea", map, throwable.getMessage());
                log.error("[产品服务]RPC获得对应省市区异常 msg: {},  map:{}", throwable.getMessage(), map);
                return Optional.of(new Json(ReturnCode.RPC服务出错));
            }

            @Override
            public Optional<Json> selectOneDangerPlanted(Map<String, Object> map) {
                rpcLogService.rpcLog(receiveServer, "/product-danger-planted/selectOne", map, throwable.getMessage());
                log.error("[产品服务]RPC险种信息异常 msg: {},  map:{}", throwable.getMessage(), map);
                return Optional.of(new Json(ReturnCode.RPC服务出错));
            }

            @Override
            public Optional<Json> getCityList(Map<String, Object> map) {
                rpcLogService.rpcLog(receiveServer, "/city/select", map, throwable.getMessage());
                log.error("[产品服务]RPC市区异常 msg: {},  map:{}", throwable.getMessage(), map);
                return Optional.of(new Json(ReturnCode.RPC服务出错));
            }

            @Override
            public Optional<Json> getProvinceList(Map<String, Object> map) {
                rpcLogService.rpcLog(receiveServer, "/province/select", map, throwable.getMessage());
                log.error("[产品服务]RPC省分异常 msg: {},  map:{}", throwable.getMessage(), map);
                return Optional.of(new Json(ReturnCode.RPC服务出错));
            }
        };
    }
}
