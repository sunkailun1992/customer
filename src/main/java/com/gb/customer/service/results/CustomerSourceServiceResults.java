package com.gb.customer.service.results;

import com.gb.customer.entity.CustomerSource;
import com.gb.customer.entity.vo.CustomerSourceVO;
import com.gb.customer.entity.bo.CustomerSourceBO;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import com.gb.utils.GeneralConvertor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * TODO 客户来源,Service返回实现
 * 代码生成器
 *
 * @author lijh
 * @className CustomerSourceServiceResults
 * @time 2022-09-01 03:12:09
 */
@Slf4j
@Service
@Setter(onMethod_ = {@Autowired})
public class CustomerSourceServiceResults {


    /**
     * TODO 单条，增强返回参数追加
     *
     * @param customerSourceVO 客户来源
     * @return CustomerSourceVO
     * @author lijh
     * @methodName assignment
     * @time 2022-09-01 03:12:09
     */
    public CustomerSourceVO assignment(CustomerSourceVO customerSourceVO) {
        if (customerSourceVO != null) {
            return customerSourceVO;
        } else {
            return customerSourceVO;
        }
    }


    /**
     * TODO 分页，增强返回参数追加
     *
     * @param customerSourceVOList 客户来源
     * @return Page<CustomerSourceVO>
     * @author lijh
     * @methodName assignment
     * @time 2022-09-01 03:12:09
     */
    public Page<CustomerSourceVO> assignment(Page<CustomerSourceVO> customerSourceVOList) {
        customerSourceVOList.getRecords().forEach(customerSourceVO -> {
        });
        return customerSourceVOList;
    }


    /**
     * TODO 集合，增强返回参数追加
     *
     * @param customerSourceVOList 客户来源
     * @return List<CustomerSourceVO>
     * @author lijh
     * @methodName assignment
     * @time 2022-09-01 03:12:09
     */
    public List<CustomerSourceVO> assignment(List<CustomerSourceVO> customerSourceVOList) {
        customerSourceVOList.forEach(customerSourceVO -> {
        });
        return customerSourceVOList;
    }


    /**
     * TODO DO转化VO
     *
     * @param pageDO 客户来源
     * @return Page<CustomerSourceVO>
     * @author lijh
     * @methodName toPageVO
     * @time 2022-09-01 03:12:09
     */
    public Page<CustomerSourceVO> toPageVO(Page<CustomerSource> pageDO) {
        Page<CustomerSourceVO> pageVO = new Page<CustomerSourceVO>();
        pageVO.setRecords(GeneralConvertor.convertor(pageDO.getRecords(), CustomerSourceVO.class));
        pageVO.setCurrent(pageDO.getCurrent());
        pageVO.setPages(pageDO.getPages());
        pageVO.setSize(pageDO.getSize());
        pageVO.setTotal(pageDO.getTotal());
        return pageVO;
    }
}