package com.gb.promoteform.service.query;

import com.gb.promoteform.entity.PromoteFormProduct;
import com.gb.promoteform.entity.query.PromoteFormProductQuery;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;


/**
 * TODO 推广表单产品,Service查询实现
 * 代码生成器
 *
 * @author lijh
 * @className PromoteFormProductServiceQuery
 * @time 2022-07-04 10:49:04
 */
@Slf4j
@Service
@Setter(onMethod_ = {@Autowired})
public class PromoteFormProductServiceQuery {


	/**
     * TODO 查询增强
     *
     * @param promoteFormProductQuery 推广表单产品
     * @return QueryWrapper
     * @author lijh
     * @methodName query
	 * @time 2022-07-04 10:49:04
	 */
    public QueryWrapper query(PromoteFormProductQuery promoteFormProductQuery, QueryWrapper<PromoteFormProduct> queryWrapper) {
        /**
         * 排序
         */
        if (promoteFormProductQuery.getCollation() != null && StringUtils.isNotBlank(promoteFormProductQuery.getCollationFields())) {
            if (promoteFormProductQuery.getCollation()) {
                queryWrapper.orderByAsc(promoteFormProductQuery.getCollationFields());
            } else {
                queryWrapper.orderByDesc(promoteFormProductQuery.getCollationFields());
            }
        } else {
            queryWrapper.orderByDesc(promoteFormProductQuery.getCollationFields());
        }

        /**
         * 显示字段
         */
        if (StringUtils.isNotBlank(promoteFormProductQuery.getFields())) {
            queryWrapper.select(promoteFormProductQuery.getFields());
        }
        return queryWrapper;
    }
}
