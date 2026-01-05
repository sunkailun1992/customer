package com.gb.promoteform.service.results;

import com.gb.promoteform.entity.PromoteFormField;
import com.gb.promoteform.entity.vo.PromoteFormFieldVO;
import com.gb.promoteform.entity.bo.PromoteFormFieldBO;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import com.gb.utils.GeneralConvertor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * TODO 推广表单字段,Service返回实现
 * 代码生成器
 *
 * @author lijh
 * @className PromoteFormFieldServiceResults
 * @time 2022-07-04 10:49:04
 */
@Slf4j
@Service
@Setter(onMethod_ = {@Autowired})
public class PromoteFormFieldServiceResults {


    /**
     * TODO 单条，增强返回参数追加
     *
     * @param promoteFormFieldVO 推广表单字段
     * @return PromoteFormFieldVO
     * @author lijh
     * @methodName assignment
     * @time 2022-07-04 10:49:04
     */
    public PromoteFormFieldVO assignment(PromoteFormFieldVO promoteFormFieldVO) {
        if (promoteFormFieldVO != null) {
            return promoteFormFieldVO;
        } else {
            return promoteFormFieldVO;
        }
    }


    /**
     * TODO 分页，增强返回参数追加
     *
     * @param promoteFormFieldVOList 推广表单字段
     * @return Page<PromoteFormFieldVO>
     * @author lijh
     * @methodName assignment
     * @time 2022-07-04 10:49:04
     */
    public Page<PromoteFormFieldVO> assignment(Page<PromoteFormFieldVO> promoteFormFieldVOList) {
        promoteFormFieldVOList.getRecords().forEach(promoteFormFieldVO -> {
        });
        return promoteFormFieldVOList;
    }


    /**
     * TODO 集合，增强返回参数追加
     *
     * @param promoteFormFieldVOList 推广表单字段
     * @return List<PromoteFormFieldVO>
     * @author lijh
     * @methodName assignment
     * @time 2022-07-04 10:49:04
     */
    public List<PromoteFormFieldVO> assignment(List<PromoteFormFieldVO> promoteFormFieldVOList) {
        promoteFormFieldVOList.forEach(promoteFormFieldVO -> {
        });
        return promoteFormFieldVOList;
    }


    /**
     * TODO DO转化VO
     *
     * @param pageDO 推广表单字段
     * @return Page<PromoteFormFieldVO>
     * @author lijh
     * @methodName toPageVO
     * @time 2022-07-04 10:49:04
     */
    public Page<PromoteFormFieldVO> toPageVO(Page<PromoteFormField> pageDO) {
        Page<PromoteFormFieldVO> pageVO = new Page<PromoteFormFieldVO>();
        pageVO.setRecords(GeneralConvertor.convertor(pageDO.getRecords(), PromoteFormFieldVO.class));
        pageVO.setCurrent(pageDO.getCurrent());
        pageVO.setPages(pageDO.getPages());
        pageVO.setSize(pageDO.getSize());
        pageVO.setTotal(pageDO.getTotal());
        return pageVO;
    }
}