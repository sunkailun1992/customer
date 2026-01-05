package com.gb.promoteform.service.query;

import com.gb.promoteform.entity.PromoteFormLabel;
import com.gb.promoteform.entity.query.PromoteFormLabelQuery;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;


/**
 * TODO 推广表单标签,Service查询实现
 * 代码生成器
 *
 * @author lijh
 * @className PromoteFormLabelServiceQuery
 * @time 2022-07-04 10:49:04
 */
@Slf4j
@Service
@Setter(onMethod_ = {@Autowired})
public class PromoteFormLabelServiceQuery {


	/**
     * TODO 查询增强
     *
     * @param promoteFormLabelQuery 推广表单标签
     * @return QueryWrapper
     * @author lijh
     * @methodName query
	 * @time 2022-07-04 10:49:04
	 */
    public QueryWrapper query(PromoteFormLabelQuery promoteFormLabelQuery, QueryWrapper<PromoteFormLabel> queryWrapper) {
        /**
         * 排序
         */
        if (promoteFormLabelQuery.getCollation() != null && StringUtils.isNotBlank(promoteFormLabelQuery.getCollationFields())) {
            if (promoteFormLabelQuery.getCollation()) {
                queryWrapper.orderByAsc(promoteFormLabelQuery.getCollationFields());
            } else {
                queryWrapper.orderByDesc(promoteFormLabelQuery.getCollationFields());
            }
        } else {
            queryWrapper.orderByDesc(promoteFormLabelQuery.getCollationFields());
        }

        /**
         * 显示字段
         */
        if (StringUtils.isNotBlank(promoteFormLabelQuery.getFields())) {
            queryWrapper.select(promoteFormLabelQuery.getFields());
        }
        return queryWrapper;
    }
}
