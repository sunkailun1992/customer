package com.gb.promoteform.service.results;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gb.promoteform.entity.PromoteFormFloatingWindow;
import com.gb.promoteform.entity.PromoteFormFloatingWindowDangerPlanted;
import com.gb.promoteform.entity.PromoteFormFloatingWindowDangerPlantedSpu;
import com.gb.promoteform.entity.query.PromoteFormFloatingWindowDangerPlantedSpuQuery;
import com.gb.promoteform.entity.vo.PromoteFormFloatingWindowDangerPlantedSpuVO;
import com.gb.promoteform.entity.vo.PromoteFormFloatingWindowVO;
import com.gb.promoteform.service.PromoteFormFloatingWindowDangerPlantedService;
import com.gb.promoteform.service.PromoteFormFloatingWindowDangerPlantedSpuService;
import com.gb.rpc.entity.enums.RpcTypeEnum;
import com.gb.rpc.impl.RpcComponent;
import com.gb.utils.GeneralConvertor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;


/**
 * TODO 推广表单浮框,Service返回实现
 * 代码生成器
 *
 * @author lijh
 * @className PromoteFormFloatingWindowServiceResults
 * @time 2022-07-04 10:49:04
 */
@Slf4j
@Service
@Setter(onMethod_ = {@Autowired})
public class PromoteFormFloatingWindowServiceResults {

    private PromoteFormFloatingWindowDangerPlantedService promoteFormFloatingWindowDangerPlantedService;
    private PromoteFormFloatingWindowDangerPlantedSpuService promoteFormFloatingWindowDangerPlantedSpuService;

    private RpcComponent rpcComponent;

    /**
     * TODO 单条，增强返回参数追加
     *
     * @param promoteFormFloatingWindowVO 推广表单浮框
     * @return PromoteFormFloatingWindowVO
     * @author lijh
     * @methodName assignment
     * @time 2022-07-04 10:49:04
     */
    public PromoteFormFloatingWindowVO assignment(PromoteFormFloatingWindowVO promoteFormFloatingWindowVO) {
        if (Objects.isNull(promoteFormFloatingWindowVO)) {
            return null;
        }
        List<PromoteFormFloatingWindowDangerPlanted> list = promoteFormFloatingWindowDangerPlantedService.list(new QueryWrapper<PromoteFormFloatingWindowDangerPlanted>().lambda().
                eq(PromoteFormFloatingWindowDangerPlanted::getPromoteFormFloatingWindowId, promoteFormFloatingWindowVO.getId()));
        if (CollectionUtils.isNotEmpty(list)) {
            for (PromoteFormFloatingWindowDangerPlanted dangerPlanted : list) {
                dangerPlanted.setDangerPlantedName(getDangerPlantedName(dangerPlanted.getDangerPlantedId()));
                //表单浮框险种产品数组
                List<PromoteFormFloatingWindowDangerPlantedSpuVO> promoteFormFloatingWindowDangerPlantedSpuVoS = promoteFormFloatingWindowDangerPlantedSpuService.listEnhance(new PromoteFormFloatingWindowDangerPlantedSpuQuery() {{
                    setPromoteFormFloatingWindowDangerPlantedId(dangerPlanted.getId());
                }});
                List<PromoteFormFloatingWindowDangerPlantedSpu> windowDangerPlantedSpuList = GeneralConvertor.convertor(promoteFormFloatingWindowDangerPlantedSpuVoS, PromoteFormFloatingWindowDangerPlantedSpu.class);
                dangerPlanted.setWindowDangerPlantedSpuList(windowDangerPlantedSpuList);
            }

        }
        promoteFormFloatingWindowVO.setWindowDangerPlantedList(list);
        return promoteFormFloatingWindowVO;
    }


    /**
     * TODO 分页，增强返回参数追加
     *
     * @param promoteFormFloatingWindowVOList 推广表单浮框
     * @return Page<PromoteFormFloatingWindowVO>
     * @author lijh
     * @methodName assignment
     * @time 2022-07-04 10:49:04
     */
    public Page<PromoteFormFloatingWindowVO> assignment(Page<PromoteFormFloatingWindowVO> promoteFormFloatingWindowVOList) {
        promoteFormFloatingWindowVOList.getRecords().forEach(promoteFormFloatingWindowVO -> {
        });
        return promoteFormFloatingWindowVOList;
    }


    /**
     * TODO 集合，增强返回参数追加
     *
     * @param promoteFormFloatingWindowVOList 推广表单浮框
     * @return List<PromoteFormFloatingWindowVO>
     * @author lijh
     * @methodName assignment
     * @time 2022-07-04 10:49:04
     */
    public List<PromoteFormFloatingWindowVO> assignment(List<PromoteFormFloatingWindowVO> promoteFormFloatingWindowVOList) {
        promoteFormFloatingWindowVOList.forEach(promoteFormFloatingWindowVO -> {
        });
        return promoteFormFloatingWindowVOList;
    }


    /**
     * TODO DO转化VO
     *
     * @param pageDO 推广表单浮框
     * @return Page<PromoteFormFloatingWindowVO>
     * @author lijh
     * @methodName toPageVO
     * @time 2022-07-04 10:49:04
     */
    public Page<PromoteFormFloatingWindowVO> toPageVO(Page<PromoteFormFloatingWindow> pageDO) {
        Page<PromoteFormFloatingWindowVO> pageVO = new Page<PromoteFormFloatingWindowVO>();
        pageVO.setRecords(GeneralConvertor.convertor(pageDO.getRecords(), PromoteFormFloatingWindowVO.class));
        pageVO.setCurrent(pageDO.getCurrent());
        pageVO.setPages(pageDO.getPages());
        pageVO.setSize(pageDO.getSize());
        pageVO.setTotal(pageDO.getTotal());
        return pageVO;
    }

    public String getDangerPlantedName(String dangerPlantedId) {
        HashMap<String, Object> hashMap = new HashMap<>(1);
        hashMap.put("id", dangerPlantedId);
        LinkedHashMap linkedHashMap = rpcComponent.rpcQueryInfo(hashMap, RpcTypeEnum.GET_ONE_DANGER_PLANTED, LinkedHashMap.class);
        if (CollectionUtils.isEmpty(linkedHashMap)) {
            return StringUtils.EMPTY;
        }
        return linkedHashMap.get("name").toString();
    }
}