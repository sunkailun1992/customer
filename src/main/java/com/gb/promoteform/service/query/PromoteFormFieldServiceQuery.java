package com.gb.promoteform.service.query;

import com.gb.promoteform.entity.PromoteFormField;
import com.gb.promoteform.entity.query.PromoteFormFieldQuery;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;


/**
 * TODO 推广表单字段,Service查询实现
 * 代码生成器
 *
 * @author lijh
 * @className PromoteFormFieldServiceQuery
 * @time 2022-07-04 10:49:04
 */
@Slf4j
@Service
@Setter(onMethod_ = {@Autowired})
public class PromoteFormFieldServiceQuery {


	/**
     * TODO 查询增强
     *
     * @param promoteFormFieldQuery 推广表单字段
     * @return QueryWrapper
     * @author lijh
     * @methodName query
	 * @time 2022-07-04 10:49:04
	 */
    public QueryWrapper query(PromoteFormFieldQuery promoteFormFieldQuery, QueryWrapper<PromoteFormField> queryWrapper) {
        /**
         * 排序
         */
        if (promoteFormFieldQuery.getCollation() != null && StringUtils.isNotBlank(promoteFormFieldQuery.getCollationFields())) {
            if (promoteFormFieldQuery.getCollation()) {
                queryWrapper.orderByAsc(promoteFormFieldQuery.getCollationFields());
            } else {
                queryWrapper.orderByDesc(promoteFormFieldQuery.getCollationFields());
            }
        } else {
            queryWrapper.orderByDesc(promoteFormFieldQuery.getCollationFields());
        }

        /**
         * 显示字段
         */
        if (StringUtils.isNotBlank(promoteFormFieldQuery.getFields())) {
            queryWrapper.select(promoteFormFieldQuery.getFields());
        }
        return queryWrapper;
    }
}
