package com.gb.promoteform.service.query;

import com.gb.promoteform.entity.PromoteFormFloatingWindowDangerPlanted;
import com.gb.promoteform.entity.query.PromoteFormFloatingWindowDangerPlantedQuery;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;


/**
 * TODO 推广表单浮框险种,Service查询实现
 * 代码生成器
 *
 * @author lijh
 * @className PromoteFormFloatingWindowDangerPlantedServiceQuery
 * @time 2022-07-04 10:49:04
 */
@Slf4j
@Service
@Setter(onMethod_ = {@Autowired})
public class PromoteFormFloatingWindowDangerPlantedServiceQuery {


	/**
     * TODO 查询增强
     *
     * @param promoteFormFloatingWindowDangerPlantedQuery 推广表单浮框险种
     * @return QueryWrapper
     * @author lijh
     * @methodName query
	 * @time 2022-07-04 10:49:04
	 */
    public QueryWrapper query(PromoteFormFloatingWindowDangerPlantedQuery promoteFormFloatingWindowDangerPlantedQuery, QueryWrapper<PromoteFormFloatingWindowDangerPlanted> queryWrapper) {
        /**
         * 排序
         */
        if (promoteFormFloatingWindowDangerPlantedQuery.getCollation() != null && StringUtils.isNotBlank(promoteFormFloatingWindowDangerPlantedQuery.getCollationFields())) {
            if (promoteFormFloatingWindowDangerPlantedQuery.getCollation()) {
                queryWrapper.orderByAsc(promoteFormFloatingWindowDangerPlantedQuery.getCollationFields());
            } else {
                queryWrapper.orderByDesc(promoteFormFloatingWindowDangerPlantedQuery.getCollationFields());
            }
        } else {
            queryWrapper.orderByDesc(promoteFormFloatingWindowDangerPlantedQuery.getCollationFields());
        }

        /**
         * 显示字段
         */
        if (StringUtils.isNotBlank(promoteFormFloatingWindowDangerPlantedQuery.getFields())) {
            queryWrapper.select(promoteFormFloatingWindowDangerPlantedQuery.getFields());
        }
        return queryWrapper;
    }
}
