package com.gb.promoteform.service.query;

import com.gb.promoteform.entity.PromoteFormWechat;
import com.gb.promoteform.entity.query.PromoteFormWechatQuery;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;


/**
 * TODO 推广表单微信,Service查询实现
 * 代码生成器
 *
 * @author lijh
 * @className PromoteFormWechatServiceQuery
 * @time 2022-07-04 10:49:05
 */
@Slf4j
@Service
@Setter(onMethod_ = {@Autowired})
public class PromoteFormWechatServiceQuery {


	/**
     * TODO 查询增强
     *
     * @param promoteFormWechatQuery 推广表单微信
     * @return QueryWrapper
     * @author lijh
     * @methodName query
	 * @time 2022-07-04 10:49:05
	 */
    public QueryWrapper query(PromoteFormWechatQuery promoteFormWechatQuery, QueryWrapper<PromoteFormWechat> queryWrapper) {
        /**
         * 排序
         */
        if (promoteFormWechatQuery.getCollation() != null && StringUtils.isNotBlank(promoteFormWechatQuery.getCollationFields())) {
            if (promoteFormWechatQuery.getCollation()) {
                queryWrapper.orderByAsc(promoteFormWechatQuery.getCollationFields());
            } else {
                queryWrapper.orderByDesc(promoteFormWechatQuery.getCollationFields());
            }
        } else {
            queryWrapper.orderByDesc(promoteFormWechatQuery.getCollationFields());
        }

        /**
         * 显示字段
         */
        if (StringUtils.isNotBlank(promoteFormWechatQuery.getFields())) {
            queryWrapper.select(promoteFormWechatQuery.getFields());
        }
        return queryWrapper;
    }
}
