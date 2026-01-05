package com.gb.customer.service.query;

import com.gb.customer.entity.CooperativeAppointment;
import com.gb.customer.entity.query.CooperativeAppointmentQuery;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;


/**
 * TODO 云服合作预约,Service查询实现
 * 代码生成器
 *
 * @author ljh
 * @className CooperativeAppointmentServiceQuery
 * @time 2023-01-09 02:56:41
 */
@Slf4j
@Service
@Setter(onMethod_ = {@Autowired})
public class CooperativeAppointmentServiceQuery {


	/**
     * TODO 查询增强
     *
     * @param cooperativeAppointmentQuery 云服合作预约
     * @return QueryWrapper
     * @author ljh
     * @methodName query
	 * @time 2023-01-09 02:56:41
	 */
    public QueryWrapper query(CooperativeAppointmentQuery cooperativeAppointmentQuery, QueryWrapper<CooperativeAppointment> queryWrapper) {
        /**
         * 排序
         */
        if (cooperativeAppointmentQuery.getCollation() != null && StringUtils.isNotBlank(cooperativeAppointmentQuery.getCollationFields())) {
            if (cooperativeAppointmentQuery.getCollation()) {
                queryWrapper.orderByAsc(cooperativeAppointmentQuery.getCollationFields());
            } else {
                queryWrapper.orderByDesc(cooperativeAppointmentQuery.getCollationFields());
            }
        } else {
            queryWrapper.orderByDesc(cooperativeAppointmentQuery.getCollationFields());
        }

        /**
         * 显示字段
         */
        if (StringUtils.isNotBlank(cooperativeAppointmentQuery.getFields())) {
            queryWrapper.select(cooperativeAppointmentQuery.getFields());
        }
        return queryWrapper;
    }
}
