package com.gb.promoteform.service.results;

import com.gb.promoteform.entity.PromoteFormWindow;
import com.gb.promoteform.entity.vo.PromoteFormWindowVO;
import com.gb.promoteform.entity.bo.PromoteFormWindowBO;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import com.gb.utils.GeneralConvertor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * TODO 推广表单窗口,Service返回实现
 * 代码生成器
 *
 * @author lijh
 * @className PromoteFormWindowServiceResults
 * @time 2022-07-04 10:49:05
 */
@Slf4j
@Service
@Setter(onMethod_ = {@Autowired})
public class PromoteFormWindowServiceResults {


    /**
     * TODO 单条，增强返回参数追加
     *
     * @param promoteFormWindowVO 推广表单窗口
     * @return PromoteFormWindowVO
     * @author lijh
     * @methodName assignment
     * @time 2022-07-04 10:49:05
     */
    public PromoteFormWindowVO assignment(PromoteFormWindowVO promoteFormWindowVO) {
        if (promoteFormWindowVO != null) {
            return promoteFormWindowVO;
        } else {
            return promoteFormWindowVO;
        }
    }


    /**
     * TODO 分页，增强返回参数追加
     *
     * @param promoteFormWindowVOList 推广表单窗口
     * @return Page<PromoteFormWindowVO>
     * @author lijh
     * @methodName assignment
     * @time 2022-07-04 10:49:05
     */
    public Page<PromoteFormWindowVO> assignment(Page<PromoteFormWindowVO> promoteFormWindowVOList) {
        promoteFormWindowVOList.getRecords().forEach(promoteFormWindowVO -> {
        });
        return promoteFormWindowVOList;
    }


    /**
     * TODO 集合，增强返回参数追加
     *
     * @param promoteFormWindowVOList 推广表单窗口
     * @return List<PromoteFormWindowVO>
     * @author lijh
     * @methodName assignment
     * @time 2022-07-04 10:49:05
     */
    public List<PromoteFormWindowVO> assignment(List<PromoteFormWindowVO> promoteFormWindowVOList) {
        promoteFormWindowVOList.forEach(promoteFormWindowVO -> {
        });
        return promoteFormWindowVOList;
    }


    /**
     * TODO DO转化VO
     *
     * @param pageDO 推广表单窗口
     * @return Page<PromoteFormWindowVO>
     * @author lijh
     * @methodName toPageVO
     * @time 2022-07-04 10:49:05
     */
    public Page<PromoteFormWindowVO> toPageVO(Page<PromoteFormWindow> pageDO) {
        Page<PromoteFormWindowVO> pageVO = new Page<PromoteFormWindowVO>();
        pageVO.setRecords(GeneralConvertor.convertor(pageDO.getRecords(), PromoteFormWindowVO.class));
        pageVO.setCurrent(pageDO.getCurrent());
        pageVO.setPages(pageDO.getPages());
        pageVO.setSize(pageDO.getSize());
        pageVO.setTotal(pageDO.getTotal());
        return pageVO;
    }
}