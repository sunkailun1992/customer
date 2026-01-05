package com.gb.customer.service.results;

import com.gb.customer.entity.CooperativeAppointment;
import com.gb.customer.entity.vo.CooperativeAppointmentVO;
import com.gb.customer.entity.bo.CooperativeAppointmentBO;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import com.gb.utils.GeneralConvertor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * TODO 云服合作预约,Service返回实现
 * 代码生成器
 *
 * @author ljh
 * @className CooperativeAppointmentServiceResults
 * @time 2023-01-09 02:56:41
 */
@Slf4j
@Service
@Setter(onMethod_ = {@Autowired})
public class CooperativeAppointmentServiceResults {


    /**
     * TODO 单条，增强返回参数追加
     *
     * @param cooperativeAppointmentVO 云服合作预约
     * @return CooperativeAppointmentVO
     * @author ljh
     * @methodName assignment
     * @time 2023-01-09 02:56:41
     */
    public CooperativeAppointmentVO assignment(CooperativeAppointmentVO cooperativeAppointmentVO) {
        if (cooperativeAppointmentVO != null) {
            return cooperativeAppointmentVO;
        } else {
            return cooperativeAppointmentVO;
        }
    }


    /**
     * TODO 分页，增强返回参数追加
     *
     * @param cooperativeAppointmentVOList 云服合作预约
     * @return Page<CooperativeAppointmentVO>
     * @author ljh
     * @methodName assignment
     * @time 2023-01-09 02:56:41
     */
    public Page<CooperativeAppointmentVO> assignment(Page<CooperativeAppointmentVO> cooperativeAppointmentVOList) {
        cooperativeAppointmentVOList.getRecords().forEach(cooperativeAppointmentVO -> {
        });
        return cooperativeAppointmentVOList;
    }


    /**
     * TODO 集合，增强返回参数追加
     *
     * @param cooperativeAppointmentVOList 云服合作预约
     * @return List<CooperativeAppointmentVO>
     * @author ljh
     * @methodName assignment
     * @time 2023-01-09 02:56:41
     */
    public List<CooperativeAppointmentVO> assignment(List<CooperativeAppointmentVO> cooperativeAppointmentVOList) {
        cooperativeAppointmentVOList.forEach(cooperativeAppointmentVO -> {
        });
        return cooperativeAppointmentVOList;
    }


    /**
     * TODO DO转化VO
     *
     * @param pageDO 云服合作预约
     * @return Page<CooperativeAppointmentVO>
     * @author ljh
     * @methodName toPageVO
     * @time 2023-01-09 02:56:41
     */
    public Page<CooperativeAppointmentVO> toPageVO(Page<CooperativeAppointment> pageDO) {
        Page<CooperativeAppointmentVO> pageVO = new Page<CooperativeAppointmentVO>();
        pageVO.setRecords(GeneralConvertor.convertor(pageDO.getRecords(), CooperativeAppointmentVO.class));
        pageVO.setCurrent(pageDO.getCurrent());
        pageVO.setPages(pageDO.getPages());
        pageVO.setSize(pageDO.getSize());
        pageVO.setTotal(pageDO.getTotal());
        return pageVO;
    }
}