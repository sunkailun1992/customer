package com.gb.customer.service.query;

import com.gb.customer.entity.CustomerAgentOperateRecord;
import com.gb.customer.entity.query.CustomerAgentOperateRecordQuery;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;


/**
 * TODO 客户经纪人操作记录,Service查询实现
 * 代码生成器
 *
 * @author lijh
 * @className CustomerAgentOperateRecordServiceQuery
 * @time 2022-09-20 11:02:25
 */
@Slf4j
@Service
@Setter(onMethod_ = {@Autowired})
public class CustomerAgentOperateRecordServiceQuery {


	/**
     * TODO 查询增强
     *
     * @param customerAgentOperateRecordQuery 客户经纪人操作记录
     * @return QueryWrapper
     * @author lijh
     * @methodName query
	 * @time 2022-09-20 11:02:25
	 */
    public QueryWrapper query(CustomerAgentOperateRecordQuery customerAgentOperateRecordQuery, QueryWrapper<CustomerAgentOperateRecord> queryWrapper) {
        /**
         * 排序
         */
        if (customerAgentOperateRecordQuery.getCollation() != null && StringUtils.isNotBlank(customerAgentOperateRecordQuery.getCollationFields())) {
            if (customerAgentOperateRecordQuery.getCollation()) {
                queryWrapper.orderByAsc(customerAgentOperateRecordQuery.getCollationFields());
            } else {
                queryWrapper.orderByDesc(customerAgentOperateRecordQuery.getCollationFields());
            }
        } else {
            queryWrapper.orderByDesc(customerAgentOperateRecordQuery.getCollationFields());
        }

        /**
         * 显示字段
         */
        if (StringUtils.isNotBlank(customerAgentOperateRecordQuery.getFields())) {
            queryWrapper.select(customerAgentOperateRecordQuery.getFields());
        }
        return queryWrapper;
    }
}
