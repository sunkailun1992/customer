package com.gb.configuration.service.query;

import com.gb.configuration.entity.ProjectType;
import com.gb.configuration.entity.query.ProjectTypeQuery;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;


/**
 * TODO 项目类型,Service查询实现
 * 代码生成器
 *
 * @author lijh
 * @className ProjectTypeServiceQuery
 * @time 2022-05-05 02:45:25
 */
@Slf4j
@Service
@Setter(onMethod_ = {@Autowired})
public class ProjectTypeServiceQuery {


	/**
     * TODO 查询增强
     *
     * @param projectTypeQuery 项目类型
     * @return QueryWrapper
     * @author lijh
     * @methodName query
	 * @time 2022-05-05 02:45:25
	 */
    public QueryWrapper query(ProjectTypeQuery projectTypeQuery, QueryWrapper<ProjectType> queryWrapper) {
        /**
         * 排序
         */
        if (projectTypeQuery.getCollation() != null && StringUtils.isNotBlank(projectTypeQuery.getCollationFields())) {
            if (projectTypeQuery.getCollation()) {
                queryWrapper.orderByAsc(projectTypeQuery.getCollationFields());
            } else {
                queryWrapper.orderByDesc(projectTypeQuery.getCollationFields());
            }
        } else {
            queryWrapper.orderByDesc(projectTypeQuery.getCollationFields());
        }

        /**
         * 显示字段
         */
        if (StringUtils.isNotBlank(projectTypeQuery.getFields())) {
            queryWrapper.select(projectTypeQuery.getFields());
        }
        return queryWrapper;
    }
}
