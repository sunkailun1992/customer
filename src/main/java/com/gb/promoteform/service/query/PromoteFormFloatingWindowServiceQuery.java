package com.gb.promoteform.service.query;

import com.gb.promoteform.entity.PromoteFormFloatingWindow;
import com.gb.promoteform.entity.query.PromoteFormFloatingWindowQuery;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;


/**
 * TODO 推广表单浮框,Service查询实现
 * 代码生成器
 *
 * @author lijh
 * @className PromoteFormFloatingWindowServiceQuery
 * @time 2022-07-04 10:49:04
 */
@Slf4j
@Service
@Setter(onMethod_ = {@Autowired})
public class PromoteFormFloatingWindowServiceQuery {


	/**
     * TODO 查询增强
     *
     * @param promoteFormFloatingWindowQuery 推广表单浮框
     * @return QueryWrapper
     * @author lijh
     * @methodName query
	 * @time 2022-07-04 10:49:04
	 */
    public QueryWrapper query(PromoteFormFloatingWindowQuery promoteFormFloatingWindowQuery, QueryWrapper<PromoteFormFloatingWindow> queryWrapper) {
        /**
         * 排序
         */
        if (promoteFormFloatingWindowQuery.getCollation() != null && StringUtils.isNotBlank(promoteFormFloatingWindowQuery.getCollationFields())) {
            if (promoteFormFloatingWindowQuery.getCollation()) {
                queryWrapper.orderByAsc(promoteFormFloatingWindowQuery.getCollationFields());
            } else {
                queryWrapper.orderByDesc(promoteFormFloatingWindowQuery.getCollationFields());
            }
        } else {
            queryWrapper.orderByDesc(promoteFormFloatingWindowQuery.getCollationFields());
        }

        /**
         * 显示字段
         */
        if (StringUtils.isNotBlank(promoteFormFloatingWindowQuery.getFields())) {
            queryWrapper.select(promoteFormFloatingWindowQuery.getFields());
        }
        return queryWrapper;
    }
}
