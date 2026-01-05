package com.gb.promoteform.service.results;

import com.gb.promoteform.entity.PromoteFormButton;
import com.gb.promoteform.entity.vo.PromoteFormButtonVO;
import com.gb.promoteform.entity.bo.PromoteFormButtonBO;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import com.gb.utils.GeneralConvertor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * TODO 推广表单按钮,Service返回实现
 * 代码生成器
 *
 * @author lijh
 * @className PromoteFormButtonServiceResults
 * @time 2022-07-04 10:49:04
 */
@Slf4j
@Service
@Setter(onMethod_ = {@Autowired})
public class PromoteFormButtonServiceResults {


    /**
     * TODO 单条，增强返回参数追加
     *
     * @param promoteFormButtonVO 推广表单按钮
     * @return PromoteFormButtonVO
     * @author lijh
     * @methodName assignment
     * @time 2022-07-04 10:49:04
     */
    public PromoteFormButtonVO assignment(PromoteFormButtonVO promoteFormButtonVO) {
        if (promoteFormButtonVO != null) {
            return promoteFormButtonVO;
        } else {
            return promoteFormButtonVO;
        }
    }


    /**
     * TODO 分页，增强返回参数追加
     *
     * @param promoteFormButtonVOList 推广表单按钮
     * @return Page<PromoteFormButtonVO>
     * @author lijh
     * @methodName assignment
     * @time 2022-07-04 10:49:04
     */
    public Page<PromoteFormButtonVO> assignment(Page<PromoteFormButtonVO> promoteFormButtonVOList) {
        promoteFormButtonVOList.getRecords().forEach(promoteFormButtonVO -> {
        });
        return promoteFormButtonVOList;
    }


    /**
     * TODO 集合，增强返回参数追加
     *
     * @param promoteFormButtonVOList 推广表单按钮
     * @return List<PromoteFormButtonVO>
     * @author lijh
     * @methodName assignment
     * @time 2022-07-04 10:49:04
     */
    public List<PromoteFormButtonVO> assignment(List<PromoteFormButtonVO> promoteFormButtonVOList) {
        promoteFormButtonVOList.forEach(promoteFormButtonVO -> {
        });
        return promoteFormButtonVOList;
    }


    /**
     * TODO DO转化VO
     *
     * @param pageDO 推广表单按钮
     * @return Page<PromoteFormButtonVO>
     * @author lijh
     * @methodName toPageVO
     * @time 2022-07-04 10:49:04
     */
    public Page<PromoteFormButtonVO> toPageVO(Page<PromoteFormButton> pageDO) {
        Page<PromoteFormButtonVO> pageVO = new Page<PromoteFormButtonVO>();
        pageVO.setRecords(GeneralConvertor.convertor(pageDO.getRecords(), PromoteFormButtonVO.class));
        pageVO.setCurrent(pageDO.getCurrent());
        pageVO.setPages(pageDO.getPages());
        pageVO.setSize(pageDO.getSize());
        pageVO.setTotal(pageDO.getTotal());
        return pageVO;
    }
}