package com.gb.log.mapper;

import com.gb.log.entity.CooperativeAppointmentLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @title: CooperativeAppointmentLogMapper
 * @author: lijh
 * @date: 2023/1/9 16:51
 * @description: 操作日志db类
 */
@Repository
public class CooperativeAppointmentLogMapper {

    /**
     * 由springboot自动注入，默认配置会产生mongoTemplate这个bean
     */
    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 操作日志, 插入数据
     *
     * @param cooperativeAppointmentLog
     * @return
     */
    public CooperativeAppointmentLog insert(CooperativeAppointmentLog cooperativeAppointmentLog) {
        return mongoTemplate.insert(cooperativeAppointmentLog);
    }

    /**
     * 操作日志查询
     *
     * @param query
     * @return
     */
    public List<CooperativeAppointmentLog> select(Query query) {
        return mongoTemplate.find(query, CooperativeAppointmentLog.class);
    }
}
