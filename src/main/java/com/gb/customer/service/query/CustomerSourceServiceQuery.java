package com.gb.customer.service.query;

import com.gb.customer.entity.CustomerSource;
import com.gb.customer.entity.query.CustomerSourceQuery;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;


/**
 * TODO 客户来源,Service查询实现
 * 代码生成器
 *
 * @author lijh
 * @className CustomerSourceServiceQuery
 * @time 2022-09-01 03:12:09
 */
@Slf4j
@Service
@Setter(onMethod_ = {@Autowired})
public class CustomerSourceServiceQuery {


	/**
     * TODO 查询增强
     *
     * @param customerSourceQuery 客户来源
     * @return QueryWrapper
     * @author lijh
     * @methodName query
	 * @time 2022-09-01 03:12:09
	 */
    public QueryWrapper query(CustomerSourceQuery customerSourceQuery, QueryWrapper<CustomerSource> queryWrapper) {
        /**
         * 排序
         */
        if (customerSourceQuery.getCollation() != null && StringUtils.isNotBlank(customerSourceQuery.getCollationFields())) {
            if (customerSourceQuery.getCollation()) {
                queryWrapper.orderByAsc(customerSourceQuery.getCollationFields());
            } else {
                queryWrapper.orderByDesc(customerSourceQuery.getCollationFields());
            }
        } else {
            queryWrapper.orderByDesc(customerSourceQuery.getCollationFields());
        }

        /**
         * 显示字段
         */
        if (StringUtils.isNotBlank(customerSourceQuery.getFields())) {
            queryWrapper.select(customerSourceQuery.getFields());
        }
        return queryWrapper;
    }
}
