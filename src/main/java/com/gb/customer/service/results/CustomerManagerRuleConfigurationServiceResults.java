package com.gb.customer.service.results;

import com.gb.customer.entity.CustomerManagerRuleConfiguration;
import com.gb.customer.entity.vo.CustomerManagerRuleConfigurationVO;
import com.gb.customer.entity.bo.CustomerManagerRuleConfigurationBO;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import com.gb.utils.GeneralConvertor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;


/**
 * Created with IntelliJ IDEA.
 * @author:     	ljh
 * @since:   	    2021-09-07 02:27:46
 * @description:	TODO  客户管理规则配置表,Service返回实现
 * @source:  	    代码生成器
 */
@Slf4j
@Setter(onMethod_ = {@Autowired})
public class CustomerManagerRuleConfigurationServiceResults{


    /**
     * 单条，增强返回参数追加
     *
     * @author     	ljh
     * @since   	2021-09-07 02:27:46
     * @param       customerManagerRuleConfigurationVO 客户管理规则配置表
     * @return      CustomerManagerRuleConfigurationVO
     */
    public static CustomerManagerRuleConfigurationVO assignment(CustomerManagerRuleConfigurationVO customerManagerRuleConfigurationVO) {
        if(customerManagerRuleConfigurationVO != null){
            return customerManagerRuleConfigurationVO;
        }else{
            return customerManagerRuleConfigurationVO;
        }
    }

    /**
     * 分页，增强返回参数追加
     *
     * @author     	ljh
     * @since   	2021-09-07 02:27:46
     * @param       customerManagerRuleConfigurationVOList 客户管理规则配置表
     * @return      Page<CustomerManagerRuleConfigurationVO>
     */
    public static Page<CustomerManagerRuleConfigurationVO> assignment(Page<CustomerManagerRuleConfigurationVO> customerManagerRuleConfigurationVOList) {
        customerManagerRuleConfigurationVOList.getRecords().forEach(customerManagerRuleConfigurationVO -> {
        });
        return customerManagerRuleConfigurationVOList;
    }


    /**
     * 集合，增强返回参数追加
     *
     * @author     	ljh
     * @since   	2021-09-07 02:27:46
     * @param       customerManagerRuleConfigurationVOList 客户管理规则配置表
     * @return      List<CustomerManagerRuleConfigurationVO>
     */
     public static List<CustomerManagerRuleConfigurationVO> assignment(List<CustomerManagerRuleConfigurationVO> customerManagerRuleConfigurationVOList) {
        customerManagerRuleConfigurationVOList.forEach(customerManagerRuleConfigurationVO -> {
        });
        return customerManagerRuleConfigurationVOList;
    }


    /**
     * DO转化VO
     *
     * @author     	ljh
     * @since   	2021-09-07 02:27:46
     * @param       pageDO 客户管理规则配置表
     * @return      com.baomidou.mybatisplus.extension.plugins.pagination.Page
     */
     public static Page<CustomerManagerRuleConfigurationVO> toPageVO(Page<CustomerManagerRuleConfiguration> pageDO) {
        Page<CustomerManagerRuleConfigurationVO> pageVO = new Page<CustomerManagerRuleConfigurationVO>();
        pageVO.setRecords(GeneralConvertor.convertor(pageDO.getRecords(), CustomerManagerRuleConfigurationVO.class));
        pageVO.setCurrent(pageDO.getCurrent());
        pageVO.setPages(pageDO.getPages());
        pageVO.setSize(pageDO.getSize());
        pageVO.setTotal(pageDO.getTotal());
        return pageVO;
    }


}