package com.gb.promoteform.service.query;

import com.gb.promoteform.entity.PromoteFormFloatingWindowDangerPlantedSpu;
import com.gb.promoteform.entity.query.PromoteFormFloatingWindowDangerPlantedSpuQuery;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;


/**
 * TODO 推广表单浮框险种产品,Service查询实现
 * 代码生成器
 *
 * @author lijh
 * @className PromoteFormFloatingWindowDangerPlantedSpuServiceQuery
 * @time 2022-10-28 03:09:31
 */
@Slf4j
@Service
@Setter(onMethod_ = {@Autowired})
public class PromoteFormFloatingWindowDangerPlantedSpuServiceQuery {


	/**
     * TODO 查询增强
     *
     * @param promoteFormFloatingWindowDangerPlantedSpuQuery 推广表单浮框险种产品
     * @return QueryWrapper
     * @author lijh
     * @methodName query
	 * @time 2022-10-28 03:09:31
	 */
    public QueryWrapper query(PromoteFormFloatingWindowDangerPlantedSpuQuery promoteFormFloatingWindowDangerPlantedSpuQuery, QueryWrapper<PromoteFormFloatingWindowDangerPlantedSpu> queryWrapper) {
        /**
         * 排序
         */
        if (promoteFormFloatingWindowDangerPlantedSpuQuery.getCollation() != null && StringUtils.isNotBlank(promoteFormFloatingWindowDangerPlantedSpuQuery.getCollationFields())) {
            if (promoteFormFloatingWindowDangerPlantedSpuQuery.getCollation()) {
                queryWrapper.orderByAsc(promoteFormFloatingWindowDangerPlantedSpuQuery.getCollationFields());
            } else {
                queryWrapper.orderByDesc(promoteFormFloatingWindowDangerPlantedSpuQuery.getCollationFields());
            }
        } else {
            queryWrapper.orderByDesc(promoteFormFloatingWindowDangerPlantedSpuQuery.getCollationFields());
        }

        /**
         * 显示字段
         */
        if (StringUtils.isNotBlank(promoteFormFloatingWindowDangerPlantedSpuQuery.getFields())) {
            queryWrapper.select(promoteFormFloatingWindowDangerPlantedSpuQuery.getFields());
        }
        return queryWrapper;
    }
}
