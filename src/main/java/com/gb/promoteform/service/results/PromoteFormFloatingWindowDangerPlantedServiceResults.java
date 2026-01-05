package com.gb.promoteform.service.results;

import com.gb.promoteform.entity.PromoteFormFloatingWindowDangerPlanted;
import com.gb.promoteform.entity.vo.PromoteFormFloatingWindowDangerPlantedVO;
import com.gb.promoteform.entity.bo.PromoteFormFloatingWindowDangerPlantedBO;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import com.gb.utils.GeneralConvertor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * TODO 推广表单浮框险种,Service返回实现
 * 代码生成器
 *
 * @author lijh
 * @className PromoteFormFloatingWindowDangerPlantedServiceResults
 * @time 2022-07-04 10:49:04
 */
@Slf4j
@Service
@Setter(onMethod_ = {@Autowired})
public class PromoteFormFloatingWindowDangerPlantedServiceResults {


    /**
     * TODO 单条，增强返回参数追加
     *
     * @param promoteFormFloatingWindowDangerPlantedVO 推广表单浮框险种
     * @return PromoteFormFloatingWindowDangerPlantedVO
     * @author lijh
     * @methodName assignment
     * @time 2022-07-04 10:49:04
     */
    public PromoteFormFloatingWindowDangerPlantedVO assignment(PromoteFormFloatingWindowDangerPlantedVO promoteFormFloatingWindowDangerPlantedVO) {
        if (promoteFormFloatingWindowDangerPlantedVO != null) {
            return promoteFormFloatingWindowDangerPlantedVO;
        } else {
            return promoteFormFloatingWindowDangerPlantedVO;
        }
    }


    /**
     * TODO 分页，增强返回参数追加
     *
     * @param promoteFormFloatingWindowDangerPlantedVOList 推广表单浮框险种
     * @return Page<PromoteFormFloatingWindowDangerPlantedVO>
     * @author lijh
     * @methodName assignment
     * @time 2022-07-04 10:49:04
     */
    public Page<PromoteFormFloatingWindowDangerPlantedVO> assignment(Page<PromoteFormFloatingWindowDangerPlantedVO> promoteFormFloatingWindowDangerPlantedVOList) {
        promoteFormFloatingWindowDangerPlantedVOList.getRecords().forEach(promoteFormFloatingWindowDangerPlantedVO -> {
        });
        return promoteFormFloatingWindowDangerPlantedVOList;
    }


    /**
     * TODO 集合，增强返回参数追加
     *
     * @param promoteFormFloatingWindowDangerPlantedVOList 推广表单浮框险种
     * @return List<PromoteFormFloatingWindowDangerPlantedVO>
     * @author lijh
     * @methodName assignment
     * @time 2022-07-04 10:49:04
     */
    public List<PromoteFormFloatingWindowDangerPlantedVO> assignment(List<PromoteFormFloatingWindowDangerPlantedVO> promoteFormFloatingWindowDangerPlantedVOList) {
        promoteFormFloatingWindowDangerPlantedVOList.forEach(promoteFormFloatingWindowDangerPlantedVO -> {
        });
        return promoteFormFloatingWindowDangerPlantedVOList;
    }


    /**
     * TODO DO转化VO
     *
     * @param pageDO 推广表单浮框险种
     * @return Page<PromoteFormFloatingWindowDangerPlantedVO>
     * @author lijh
     * @methodName toPageVO
     * @time 2022-07-04 10:49:04
     */
    public Page<PromoteFormFloatingWindowDangerPlantedVO> toPageVO(Page<PromoteFormFloatingWindowDangerPlanted> pageDO) {
        Page<PromoteFormFloatingWindowDangerPlantedVO> pageVO = new Page<PromoteFormFloatingWindowDangerPlantedVO>();
        pageVO.setRecords(GeneralConvertor.convertor(pageDO.getRecords(), PromoteFormFloatingWindowDangerPlantedVO.class));
        pageVO.setCurrent(pageDO.getCurrent());
        pageVO.setPages(pageDO.getPages());
        pageVO.setSize(pageDO.getSize());
        pageVO.setTotal(pageDO.getTotal());
        return pageVO;
    }
}