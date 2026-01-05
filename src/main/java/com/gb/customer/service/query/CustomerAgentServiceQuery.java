package com.gb.customer.service.query;

import com.gb.customer.entity.CustomerAgent;
import com.gb.customer.entity.query.CustomerAgentQuery;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;


/**
 * TODO 客户经纪人,Service查询实现
 * 代码生成器
 *
 * @author lijh
 * @className CustomerAgentServiceQuery
 * @time 2022-08-31 09:35:12
 */
@Slf4j
@Service
@Setter(onMethod_ = {@Autowired})
public class CustomerAgentServiceQuery {


	/**
     * TODO 查询增强
     *
     * @param customerAgentQuery 客户经纪人
     * @return QueryWrapper
     * @author lijh
     * @methodName query
	 * @time 2022-08-31 09:35:12
	 */
    public QueryWrapper query(CustomerAgentQuery customerAgentQuery, QueryWrapper<CustomerAgent> queryWrapper) {
        /**
         * 排序
         */
        if (customerAgentQuery.getCollation() != null && StringUtils.isNotBlank(customerAgentQuery.getCollationFields())) {
            if (customerAgentQuery.getCollation()) {
                queryWrapper.orderByAsc(customerAgentQuery.getCollationFields());
            } else {
                queryWrapper.orderByDesc(customerAgentQuery.getCollationFields());
            }
        } else {
            queryWrapper.orderByDesc(customerAgentQuery.getCollationFields());
        }

        /**
         * 显示字段
         */
        if (StringUtils.isNotBlank(customerAgentQuery.getFields())) {
            queryWrapper.select(customerAgentQuery.getFields());
        }
        return queryWrapper;
    }
}
