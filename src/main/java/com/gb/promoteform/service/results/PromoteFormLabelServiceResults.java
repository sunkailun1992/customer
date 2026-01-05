package com.gb.promoteform.service.results;

import com.gb.promoteform.entity.PromoteFormLabel;
import com.gb.promoteform.entity.vo.PromoteFormLabelVO;
import com.gb.promoteform.entity.bo.PromoteFormLabelBO;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import com.gb.utils.GeneralConvertor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * TODO 推广表单标签,Service返回实现
 * 代码生成器
 *
 * @author lijh
 * @className PromoteFormLabelServiceResults
 * @time 2022-07-04 10:49:04
 */
@Slf4j
@Service
@Setter(onMethod_ = {@Autowired})
public class PromoteFormLabelServiceResults {


    /**
     * TODO 单条，增强返回参数追加
     *
     * @param promoteFormLabelVO 推广表单标签
     * @return PromoteFormLabelVO
     * @author lijh
     * @methodName assignment
     * @time 2022-07-04 10:49:04
     */
    public PromoteFormLabelVO assignment(PromoteFormLabelVO promoteFormLabelVO) {
        if (promoteFormLabelVO != null) {
            return promoteFormLabelVO;
        } else {
            return promoteFormLabelVO;
        }
    }


    /**
     * TODO 分页，增强返回参数追加
     *
     * @param promoteFormLabelVOList 推广表单标签
     * @return Page<PromoteFormLabelVO>
     * @author lijh
     * @methodName assignment
     * @time 2022-07-04 10:49:04
     */
    public Page<PromoteFormLabelVO> assignment(Page<PromoteFormLabelVO> promoteFormLabelVOList) {
        promoteFormLabelVOList.getRecords().forEach(promoteFormLabelVO -> {
        });
        return promoteFormLabelVOList;
    }


    /**
     * TODO 集合，增强返回参数追加
     *
     * @param promoteFormLabelVOList 推广表单标签
     * @return List<PromoteFormLabelVO>
     * @author lijh
     * @methodName assignment
     * @time 2022-07-04 10:49:04
     */
    public List<PromoteFormLabelVO> assignment(List<PromoteFormLabelVO> promoteFormLabelVOList) {
        promoteFormLabelVOList.forEach(promoteFormLabelVO -> {
        });
        return promoteFormLabelVOList;
    }


    /**
     * TODO DO转化VO
     *
     * @param pageDO 推广表单标签
     * @return Page<PromoteFormLabelVO>
     * @author lijh
     * @methodName toPageVO
     * @time 2022-07-04 10:49:04
     */
    public Page<PromoteFormLabelVO> toPageVO(Page<PromoteFormLabel> pageDO) {
        Page<PromoteFormLabelVO> pageVO = new Page<PromoteFormLabelVO>();
        pageVO.setRecords(GeneralConvertor.convertor(pageDO.getRecords(), PromoteFormLabelVO.class));
        pageVO.setCurrent(pageDO.getCurrent());
        pageVO.setPages(pageDO.getPages());
        pageVO.setSize(pageDO.getSize());
        pageVO.setTotal(pageDO.getTotal());
        return pageVO;
    }
}