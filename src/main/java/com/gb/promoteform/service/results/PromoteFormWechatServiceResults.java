package com.gb.promoteform.service.results;

import com.gb.promoteform.entity.PromoteFormWechat;
import com.gb.promoteform.entity.vo.PromoteFormWechatVO;
import com.gb.promoteform.entity.bo.PromoteFormWechatBO;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import com.gb.utils.GeneralConvertor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * TODO 推广表单微信,Service返回实现
 * 代码生成器
 *
 * @author lijh
 * @className PromoteFormWechatServiceResults
 * @time 2022-07-04 10:49:05
 */
@Slf4j
@Service
@Setter(onMethod_ = {@Autowired})
public class PromoteFormWechatServiceResults {


    /**
     * TODO 单条，增强返回参数追加
     *
     * @param promoteFormWechatVO 推广表单微信
     * @return PromoteFormWechatVO
     * @author lijh
     * @methodName assignment
     * @time 2022-07-04 10:49:05
     */
    public PromoteFormWechatVO assignment(PromoteFormWechatVO promoteFormWechatVO) {
        if (promoteFormWechatVO != null) {
            return promoteFormWechatVO;
        } else {
            return promoteFormWechatVO;
        }
    }


    /**
     * TODO 分页，增强返回参数追加
     *
     * @param promoteFormWechatVOList 推广表单微信
     * @return Page<PromoteFormWechatVO>
     * @author lijh
     * @methodName assignment
     * @time 2022-07-04 10:49:05
     */
    public Page<PromoteFormWechatVO> assignment(Page<PromoteFormWechatVO> promoteFormWechatVOList) {
        promoteFormWechatVOList.getRecords().forEach(promoteFormWechatVO -> {
        });
        return promoteFormWechatVOList;
    }


    /**
     * TODO 集合，增强返回参数追加
     *
     * @param promoteFormWechatVOList 推广表单微信
     * @return List<PromoteFormWechatVO>
     * @author lijh
     * @methodName assignment
     * @time 2022-07-04 10:49:05
     */
    public List<PromoteFormWechatVO> assignment(List<PromoteFormWechatVO> promoteFormWechatVOList) {
        promoteFormWechatVOList.forEach(promoteFormWechatVO -> {
        });
        return promoteFormWechatVOList;
    }


    /**
     * TODO DO转化VO
     *
     * @param pageDO 推广表单微信
     * @return Page<PromoteFormWechatVO>
     * @author lijh
     * @methodName toPageVO
     * @time 2022-07-04 10:49:05
     */
    public Page<PromoteFormWechatVO> toPageVO(Page<PromoteFormWechat> pageDO) {
        Page<PromoteFormWechatVO> pageVO = new Page<PromoteFormWechatVO>();
        pageVO.setRecords(GeneralConvertor.convertor(pageDO.getRecords(), PromoteFormWechatVO.class));
        pageVO.setCurrent(pageDO.getCurrent());
        pageVO.setPages(pageDO.getPages());
        pageVO.setSize(pageDO.getSize());
        pageVO.setTotal(pageDO.getTotal());
        return pageVO;
    }
}