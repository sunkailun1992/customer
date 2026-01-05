package com.gb.customer.service.query;

import com.gb.customer.entity.CustomerManagerRuleConfiguration;
import com.gb.customer.entity.query.CustomerManagerRuleConfigurationQuery;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;


/**
 * Created with IntelliJ IDEA.
 * @author:     	ljh
 * @since:   	    2021-09-07 02:27:46
 * @description:	TODO  客户管理规则配置表,Service查询实现
 * @source:  	    代码生成器
 */
@Slf4j
@Setter(onMethod_ = {@Autowired})
public class CustomerManagerRuleConfigurationServiceQuery{


	/**
	 * 查询增强
     *
     * @author     	ljh
	 * @since   	2021-09-07 02:27:46
	 * @param       customerManagerRuleConfigurationQuery 客户管理规则配置表
	 * @return      QueryWrapper
	 */
    public static QueryWrapper query(CustomerManagerRuleConfigurationQuery customerManagerRuleConfigurationQuery, QueryWrapper<CustomerManagerRuleConfiguration> queryWrapper) {
        /**
         * 排序
         */
        if(customerManagerRuleConfigurationQuery.getCollation() != null && StringUtils.isNotBlank(customerManagerRuleConfigurationQuery.getCollationFields())){
            if(customerManagerRuleConfigurationQuery.getCollation()){
                queryWrapper.orderByAsc(customerManagerRuleConfigurationQuery.getCollationFields());
            }else{
                queryWrapper.orderByDesc(customerManagerRuleConfigurationQuery.getCollationFields());
            }
        }else{
            queryWrapper.orderByDesc(customerManagerRuleConfigurationQuery.getCollationFields());
        }

        /**
        * 显示字段
        */
        if(StringUtils.isNotBlank(customerManagerRuleConfigurationQuery.getFields())){
            queryWrapper.select(customerManagerRuleConfigurationQuery.getFields());
        }
        return queryWrapper;
    }


}
