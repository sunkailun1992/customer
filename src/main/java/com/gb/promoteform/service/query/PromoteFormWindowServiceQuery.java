package com.gb.promoteform.service.query;

import com.gb.promoteform.entity.PromoteFormWindow;
import com.gb.promoteform.entity.query.PromoteFormWindowQuery;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;


/**
 * TODO 推广表单窗口,Service查询实现
 * 代码生成器
 *
 * @author lijh
 * @className PromoteFormWindowServiceQuery
 * @time 2022-07-04 10:49:05
 */
@Slf4j
@Service
@Setter(onMethod_ = {@Autowired})
public class PromoteFormWindowServiceQuery {


	/**
     * TODO 查询增强
     *
     * @param promoteFormWindowQuery 推广表单窗口
     * @return QueryWrapper
     * @author lijh
     * @methodName query
	 * @time 2022-07-04 10:49:05
	 */
    public QueryWrapper query(PromoteFormWindowQuery promoteFormWindowQuery, QueryWrapper<PromoteFormWindow> queryWrapper) {
        /**
         * 排序
         */
        if (promoteFormWindowQuery.getCollation() != null && StringUtils.isNotBlank(promoteFormWindowQuery.getCollationFields())) {
            if (promoteFormWindowQuery.getCollation()) {
                queryWrapper.orderByAsc(promoteFormWindowQuery.getCollationFields());
            } else {
                queryWrapper.orderByDesc(promoteFormWindowQuery.getCollationFields());
            }
        } else {
            queryWrapper.orderByDesc(promoteFormWindowQuery.getCollationFields());
        }

        /**
         * 显示字段
         */
        if (StringUtils.isNotBlank(promoteFormWindowQuery.getFields())) {
            queryWrapper.select(promoteFormWindowQuery.getFields());
        }
        return queryWrapper;
    }
}
