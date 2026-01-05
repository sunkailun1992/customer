package com.gb.promoteform.service.query;

import com.gb.promoteform.entity.PromoteFormButton;
import com.gb.promoteform.entity.query.PromoteFormButtonQuery;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;


/**
 * TODO 推广表单按钮,Service查询实现
 * 代码生成器
 *
 * @author lijh
 * @className PromoteFormButtonServiceQuery
 * @time 2022-07-04 10:49:04
 */
@Slf4j
@Service
@Setter(onMethod_ = {@Autowired})
public class PromoteFormButtonServiceQuery {


	/**
     * TODO 查询增强
     *
     * @param promoteFormButtonQuery 推广表单按钮
     * @return QueryWrapper
     * @author lijh
     * @methodName query
	 * @time 2022-07-04 10:49:04
	 */
    public QueryWrapper query(PromoteFormButtonQuery promoteFormButtonQuery, QueryWrapper<PromoteFormButton> queryWrapper) {
        /**
         * 排序
         */
        if (promoteFormButtonQuery.getCollation() != null && StringUtils.isNotBlank(promoteFormButtonQuery.getCollationFields())) {
            if (promoteFormButtonQuery.getCollation()) {
                queryWrapper.orderByAsc(promoteFormButtonQuery.getCollationFields());
            } else {
                queryWrapper.orderByDesc(promoteFormButtonQuery.getCollationFields());
            }
        } else {
            queryWrapper.orderByDesc(promoteFormButtonQuery.getCollationFields());
        }

        /**
         * 显示字段
         */
        if (StringUtils.isNotBlank(promoteFormButtonQuery.getFields())) {
            queryWrapper.select(promoteFormButtonQuery.getFields());
        }
        return queryWrapper;
    }
}
