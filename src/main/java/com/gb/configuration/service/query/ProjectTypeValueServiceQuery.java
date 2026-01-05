package com.gb.configuration.service.query;

import com.gb.configuration.entity.ProjectTypeValue;
import com.gb.configuration.entity.query.ProjectTypeValueQuery;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;


/**
 * TODO 项目类型值,Service查询实现
 * 代码生成器
 *
 * @author lijh
 * @className ProjectTypeValueServiceQuery
 * @time 2022-05-05 02:45:25
 */
@Slf4j
@Service
@Setter(onMethod_ = {@Autowired})
public class ProjectTypeValueServiceQuery {


	/**
     * TODO 查询增强
     *
     * @param projectTypeValueQuery 项目类型值
     * @return QueryWrapper
     * @author lijh
     * @methodName query
	 * @time 2022-05-05 02:45:25
	 */
    public QueryWrapper query(ProjectTypeValueQuery projectTypeValueQuery, QueryWrapper<ProjectTypeValue> queryWrapper) {
        /**
         * 排序
         */
        if (projectTypeValueQuery.getCollation() != null && StringUtils.isNotBlank(projectTypeValueQuery.getCollationFields())) {
            if (projectTypeValueQuery.getCollation()) {
                queryWrapper.orderByAsc(projectTypeValueQuery.getCollationFields());
            } else {
                queryWrapper.orderByDesc(projectTypeValueQuery.getCollationFields());
            }
        } else {
            queryWrapper.orderByDesc(projectTypeValueQuery.getCollationFields());
        }

        /**
         * 显示字段
         */
        if (StringUtils.isNotBlank(projectTypeValueQuery.getFields())) {
            queryWrapper.select(projectTypeValueQuery.getFields());
        }
        return queryWrapper;
    }
}
