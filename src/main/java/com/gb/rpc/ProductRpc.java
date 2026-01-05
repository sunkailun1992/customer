package com.gb.rpc;

import com.gb.rpc.impl.ProductRpcImp;
import com.gb.utils.Json;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;
import java.util.Optional;

/**
 * @description: 　* @author wangyifei
 * 　* @date 2021/4/14
 */
@FeignClient(value = "product", fallbackFactory = ProductRpcImp.class)
public interface ProductRpc {

    /**
     * 产品表单条查询
     *
     * @param map
     * @return
     */
    @RequestMapping(value = "/product-spu/selectOne", method = RequestMethod.GET)
    Json productSpuSelectOne(@RequestParam Map<String, String> map);

    /**
     * 查询产品信息
     *
     * @param map
     * @return
     */
    @RequestMapping(value = "/product-spu/selectOne", method = RequestMethod.GET)
    Optional<Json> selectOneProductSpu(@RequestParam Map<String, Object> map);

    /**
     * 查询   省
     *
     * @param map
     * @return
     */
    @RequestMapping(value = "/province/selectOne", method = RequestMethod.GET)
    Optional<Json> selectProvinceOne(@RequestParam Map<String, String> map);

    /**
     * 查询   市
     *
     * @param map
     * @return
     */
    @RequestMapping(value = "/city/selectOne", method = RequestMethod.GET)
    Optional<Json> selectCityOne(@RequestParam Map<String, String> map);

    /**
     * 查询   区
     *
     * @param map
     * @return
     */
    @RequestMapping(value = "/area/selectOne", method = RequestMethod.GET)
    Optional<Json> selectAreaOne(@RequestParam Map<String, String> map);

    /**
     * 根据区码获取省市区
     *
     * @param map
     * @return
     */
    @RequestMapping(value = "/area/selectAreaCode", method = RequestMethod.GET)
    Optional<Json> selectAreaCode(@RequestParam Map<String, String> map);

    /**
     * 传入区码 or 市码 or 省码,获得对应省市区
     *
     * @param map
     * @return
     */
    @RequestMapping(value = "/area/findByEveryArea", method = RequestMethod.GET)
    Optional<Json> findByEveryArea(@RequestParam Map<String, Object> map);


    /**
     * 险种表单条查询
     *
     * @param map
     * @return
     */
    @RequestMapping(value = "/product-danger-planted/selectOne", method = RequestMethod.GET)
    Optional<Json> selectOneDangerPlanted(@RequestParam Map<String, Object> map);


    /**
     * 查询所有的市
     * @param map
     * @return
     */
    @GetMapping(value = "/city/select")
    Optional<Json> getCityList(@RequestParam Map<String, Object> map);

    /**
     * 查询所有的省份
     * @param map
     * @return
     */
    @GetMapping(value = "/province/select")
    Optional<Json> getProvinceList(@RequestParam Map<String, Object> map);
}
