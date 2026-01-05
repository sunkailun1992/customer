package com.gb.promoteform.service.query;

import com.gb.promoteform.entity.PromoteForm;
import com.gb.promoteform.entity.query.PromoteFormQuery;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;


/**
 * TODO 推广表单,Service查询实现
 * 代码生成器
 *
 * @author lijh
 * @className PromoteFormServiceQuery
 * @time 2022-07-04 10:47:25
 */
@Slf4j
@Service
@Setter(onMethod_ = {@Autowired})
public class PromoteFormServiceQuery {


	/**
     * TODO 查询增强
     *
     * @param promoteFormQuery 推广表单
     * @return QueryWrapper
     * @author lijh
     * @methodName query
	 * @time 2022-07-04 10:47:25
	 */
    public QueryWrapper query(PromoteFormQuery promoteFormQuery, QueryWrapper<PromoteForm> queryWrapper) {
        /**
         * 排序
         */
        if (promoteFormQuery.getCollation() != null && StringUtils.isNotBlank(promoteFormQuery.getCollationFields())) {
            if (promoteFormQuery.getCollation()) {
                queryWrapper.orderByAsc(promoteFormQuery.getCollationFields());
            } else {
                queryWrapper.orderByDesc(promoteFormQuery.getCollationFields());
            }
        } else {
            queryWrapper.orderByDesc(promoteFormQuery.getCollationFields());
        }

        /**
         * 显示字段
         */
        if (StringUtils.isNotBlank(promoteFormQuery.getFields())) {
            queryWrapper.select(promoteFormQuery.getFields());
        }
        return queryWrapper;
    }
}
