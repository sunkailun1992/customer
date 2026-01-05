package com.gb.customer.service.query;

import com.gb.customer.entity.AssistantAssociated;
import com.gb.customer.entity.query.AssistantAssociatedQuery;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;


/**
 * Created with IntelliJ IDEA.
 * @author:     	lijh
 * @since:   	    2021-11-02 10:03:07
 * @description:	TODO  助理关联,Service查询实现
 * @source:  	    代码生成器
 */
@Slf4j
@Setter(onMethod_ = {@Autowired})
public class AssistantAssociatedServiceQuery{


	/**
	 * 查询增强
     *
     * @author     	lijh
	 * @since   	2021-11-02 10:03:07
	 * @param       assistantAssociatedQuery 助理关联
	 * @return      QueryWrapper
	 */
    public static QueryWrapper query(AssistantAssociatedQuery assistantAssociatedQuery, QueryWrapper<AssistantAssociated> queryWrapper) {
        /**
         * 排序
         */
        if(assistantAssociatedQuery.getCollation() != null && StringUtils.isNotBlank(assistantAssociatedQuery.getCollationFields())){
            if(assistantAssociatedQuery.getCollation()){
                queryWrapper.orderByAsc(assistantAssociatedQuery.getCollationFields());
            }else{
                queryWrapper.orderByDesc(assistantAssociatedQuery.getCollationFields());
            }
        }else{
            queryWrapper.orderByDesc(assistantAssociatedQuery.getCollationFields());
        }

        /**
        * 显示字段
        */
        if(StringUtils.isNotBlank(assistantAssociatedQuery.getFields())){
            queryWrapper.select(assistantAssociatedQuery.getFields());
        }
        return queryWrapper;
    }


}
