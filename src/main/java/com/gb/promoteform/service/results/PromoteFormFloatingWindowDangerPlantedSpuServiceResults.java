package com.gb.promoteform.service.results;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.gb.promoteform.entity.PromoteFormFloatingWindowDangerPlantedSpu;
import com.gb.promoteform.entity.vo.PromoteFormFloatingWindowDangerPlantedSpuVO;
import com.gb.promoteform.entity.bo.PromoteFormFloatingWindowDangerPlantedSpuBO;
import com.gb.rpc.entity.ProductSpu;
import com.gb.rpc.entity.enums.RpcTypeEnum;
import com.gb.rpc.impl.RpcComponent;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import com.gb.utils.GeneralConvertor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * TODO 推广表单浮框险种产品,Service返回实现
 * 代码生成器
 *
 * @author lijh
 * @className PromoteFormFloatingWindowDangerPlantedSpuServiceResults
 * @time 2022-10-28 03:09:31
 */
@Slf4j
@Service
@Setter(onMethod_ = {@Autowired})
public class PromoteFormFloatingWindowDangerPlantedSpuServiceResults {

    private RpcComponent rpcComponent;


    /**
     * TODO 单条，增强返回参数追加
     *
     * @param promoteFormFloatingWindowDangerPlantedSpuVO 推广表单浮框险种产品
     * @return PromoteFormFloatingWindowDangerPlantedSpuVO
     * @author lijh
     * @methodName assignment
     * @time 2022-10-28 03:09:31
     */
    public PromoteFormFloatingWindowDangerPlantedSpuVO assignment(PromoteFormFloatingWindowDangerPlantedSpuVO promoteFormFloatingWindowDangerPlantedSpuVO) {
        if (promoteFormFloatingWindowDangerPlantedSpuVO != null) {
            return promoteFormFloatingWindowDangerPlantedSpuVO;
        } else {
            return promoteFormFloatingWindowDangerPlantedSpuVO;
        }
    }


    /**
     * TODO 分页，增强返回参数追加
     *
     * @param promoteFormFloatingWindowDangerPlantedSpuVOList 推广表单浮框险种产品
     * @return Page<PromoteFormFloatingWindowDangerPlantedSpuVO>
     * @author lijh
     * @methodName assignment
     * @time 2022-10-28 03:09:31
     */
    public Page<PromoteFormFloatingWindowDangerPlantedSpuVO> assignment(Page<PromoteFormFloatingWindowDangerPlantedSpuVO> promoteFormFloatingWindowDangerPlantedSpuVOList) {
        promoteFormFloatingWindowDangerPlantedSpuVOList.getRecords().forEach(promoteFormFloatingWindowDangerPlantedSpuVO -> {
        });
        return promoteFormFloatingWindowDangerPlantedSpuVOList;
    }


    /**
     * TODO 集合，增强返回参数追加
     *
     * @param promoteFormFloatingWindowDangerPlantedSpuVOList 推广表单浮框险种产品
     * @return List<PromoteFormFloatingWindowDangerPlantedSpuVO>
     * @author lijh
     * @methodName assignment
     * @time 2022-10-28 03:09:31
     */
    public List<PromoteFormFloatingWindowDangerPlantedSpuVO> assignment(List<PromoteFormFloatingWindowDangerPlantedSpuVO> promoteFormFloatingWindowDangerPlantedSpuVOList) {
        if (CollectionUtils.isEmpty(promoteFormFloatingWindowDangerPlantedSpuVOList)) {
            return promoteFormFloatingWindowDangerPlantedSpuVOList;
        }
        promoteFormFloatingWindowDangerPlantedSpuVOList.forEach(promoteFormFloatingWindowDangerPlantedSpuVO -> {
            Map<String, Object> map = new HashMap<>(1);
            map.put("id", promoteFormFloatingWindowDangerPlantedSpuVO.getSpuId());
            //获取产品详情
            ProductSpu productSpu = rpcComponent.rpcQueryInfo(map, RpcTypeEnum.GET_ONE_PRODUCT_SPU, ProductSpu.class);
            promoteFormFloatingWindowDangerPlantedSpuVO.setProductSpu(productSpu);
        });
        return promoteFormFloatingWindowDangerPlantedSpuVOList;
    }


    /**
     * TODO DO转化VO
     *
     * @param pageDO 推广表单浮框险种产品
     * @return Page<PromoteFormFloatingWindowDangerPlantedSpuVO>
     * @author lijh
     * @methodName toPageVO
     * @time 2022-10-28 03:09:31
     */
    public Page<PromoteFormFloatingWindowDangerPlantedSpuVO> toPageVO(Page<PromoteFormFloatingWindowDangerPlantedSpu> pageDO) {
        Page<PromoteFormFloatingWindowDangerPlantedSpuVO> pageVO = new Page<PromoteFormFloatingWindowDangerPlantedSpuVO>();
        pageVO.setRecords(GeneralConvertor.convertor(pageDO.getRecords(), PromoteFormFloatingWindowDangerPlantedSpuVO.class));
        pageVO.setCurrent(pageDO.getCurrent());
        pageVO.setPages(pageDO.getPages());
        pageVO.setSize(pageDO.getSize());
        pageVO.setTotal(pageDO.getTotal());
        return pageVO;
    }
}