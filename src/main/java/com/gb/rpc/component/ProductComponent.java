package com.gb.rpc.component;

import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.gb.rpc.ProductRpc;
import com.gb.utils.Json;
import com.gb.utils.exception.BusinessException;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author lijh
 */
@Component
@Slf4j
@Setter(onMethod_ = {@Autowired})
public class ProductComponent {

    private ProductRpc productRpc;


    /**
     * 根据code rpc获取省市区
     *
     * @param
     */
    public Map<String, String> getRegion(String areaCode, String cityCode, String provinceCode) {
        Optional<Json> everyAreaResult;
        LinkedHashMap<String, Object> areaHash = new LinkedHashMap<>();
        try {
            everyAreaResult = productRpc.findByEveryArea(new HashMap<String, Object>(9) {{
                put("areaCode", Convert.toStr(areaCode));
                put("cityCode", Convert.toStr(cityCode));
                put("provinceCode", Convert.toStr(provinceCode));
            }});
            if (everyAreaResult.isPresent() && everyAreaResult.get().getSuccess()) {
                areaHash = (LinkedHashMap<String, Object>) everyAreaResult.get().getObj();
            }
        } catch (Exception e) {
            throw new BusinessException("根据code RPC获取 赋值省市区rpc--/product/area/findByEveryArea调用失败");
        }
        Map<String, String> map = new HashMap<>(3);
        if (CollectionUtils.isEmpty(areaHash)) {
            return null;
        }
        map.put("areaName", StringUtils.isNotBlank(Convert.toStr(areaHash.get("areaName"))) ? Convert.toStr(areaHash.get("areaName")) : StringUtils.EMPTY);
        map.put("cityName", StringUtils.isNotBlank(Convert.toStr(areaHash.get("cityName"))) ? Convert.toStr(areaHash.get("cityName")) : StringUtils.EMPTY);
        map.put("provinceName", StringUtils.isNotBlank(Convert.toStr(areaHash.get("provinceName"))) ? Convert.toStr(areaHash.get("provinceName")) : StringUtils.EMPTY);
        return map;
    }
}
