package com.gb.promoteform.service.results;

import com.gb.promoteform.entity.PromoteFormProduct;
import com.gb.promoteform.entity.vo.PromoteFormProductVO;
import com.gb.promoteform.entity.bo.PromoteFormProductBO;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import com.gb.utils.GeneralConvertor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * TODO 推广表单产品,Service返回实现
 * 代码生成器
 *
 * @author lijh
 * @className PromoteFormProductServiceResults
 * @time 2022-07-04 10:49:04
 */
@Slf4j
@Service
@Setter(onMethod_ = {@Autowired})
public class PromoteFormProductServiceResults {


    /**
     * TODO 单条，增强返回参数追加
     *
     * @param promoteFormProductVO 推广表单产品
     * @return PromoteFormProductVO
     * @author lijh
     * @methodName assignment
     * @time 2022-07-04 10:49:04
     */
    public PromoteFormProductVO assignment(PromoteFormProductVO promoteFormProductVO) {
        if (promoteFormProductVO != null) {
            return promoteFormProductVO;
        } else {
            return promoteFormProductVO;
        }
    }


    /**
     * TODO 分页，增强返回参数追加
     *
     * @param promoteFormProductVOList 推广表单产品
     * @return Page<PromoteFormProductVO>
     * @author lijh
     * @methodName assignment
     * @time 2022-07-04 10:49:04
     */
    public Page<PromoteFormProductVO> assignment(Page<PromoteFormProductVO> promoteFormProductVOList) {
        promoteFormProductVOList.getRecords().forEach(promoteFormProductVO -> {
        });
        return promoteFormProductVOList;
    }


    /**
     * TODO 集合，增强返回参数追加
     *
     * @param promoteFormProductVOList 推广表单产品
     * @return List<PromoteFormProductVO>
     * @author lijh
     * @methodName assignment
     * @time 2022-07-04 10:49:04
     */
    public List<PromoteFormProductVO> assignment(List<PromoteFormProductVO> promoteFormProductVOList) {
        promoteFormProductVOList.forEach(promoteFormProductVO -> {
        });
        return promoteFormProductVOList;
    }


    /**
     * TODO DO转化VO
     *
     * @param pageDO 推广表单产品
     * @return Page<PromoteFormProductVO>
     * @author lijh
     * @methodName toPageVO
     * @time 2022-07-04 10:49:04
     */
    public Page<PromoteFormProductVO> toPageVO(Page<PromoteFormProduct> pageDO) {
        Page<PromoteFormProductVO> pageVO = new Page<PromoteFormProductVO>();
        pageVO.setRecords(GeneralConvertor.convertor(pageDO.getRecords(), PromoteFormProductVO.class));
        pageVO.setCurrent(pageDO.getCurrent());
        pageVO.setPages(pageDO.getPages());
        pageVO.setSize(pageDO.getSize());
        pageVO.setTotal(pageDO.getTotal());
        return pageVO;
    }
}