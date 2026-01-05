package com.gb.promoteform.service.query;

import com.gb.promoteform.entity.PromoteFormProductSpu;
import com.gb.promoteform.entity.query.PromoteFormProductSpuQuery;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;


/**
 * TODO 推广表单产品id,Service查询实现
 * 代码生成器
 *
 * @author lijh
 * @className PromoteFormProductSpuServiceQuery
 * @time 2022-10-28 03:09:32
 */
@Slf4j
@Service
@Setter(onMethod_ = {@Autowired})
public class PromoteFormProductSpuServiceQuery {


	/**
     * TODO 查询增强
     *
     * @param promoteFormProductSpuQuery 推广表单产品id
     * @return QueryWrapper
     * @author lijh
     * @methodName query
	 * @time 2022-10-28 03:09:32
	 */
    public QueryWrapper query(PromoteFormProductSpuQuery promoteFormProductSpuQuery, QueryWrapper<PromoteFormProductSpu> queryWrapper) {
        /**
         * 排序
         */
        if (promoteFormProductSpuQuery.getCollation() != null && StringUtils.isNotBlank(promoteFormProductSpuQuery.getCollationFields())) {
            if (promoteFormProductSpuQuery.getCollation()) {
                queryWrapper.orderByAsc(promoteFormProductSpuQuery.getCollationFields());
            } else {
                queryWrapper.orderByDesc(promoteFormProductSpuQuery.getCollationFields());
            }
        } else {
            queryWrapper.orderByDesc(promoteFormProductSpuQuery.getCollationFields());
        }

        /**
         * 显示字段
         */
        if (StringUtils.isNotBlank(promoteFormProductSpuQuery.getFields())) {
            queryWrapper.select(promoteFormProductSpuQuery.getFields());
        }
        return queryWrapper;
    }
}
