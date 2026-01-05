package com.gb.log.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gb.customer.entity.CooperativeAppointment;
import com.gb.customer.service.CooperativeAppointmentService;
import com.gb.log.entity.CooperativeAppointmentLog;
import com.gb.log.entity.PotentialCustomerLog;
import com.gb.log.mapper.CooperativeAppointmentLogMapper;
import com.gb.log.service.CooperativeAppointmentLogService;
import com.gb.utils.RedisUtils;
import com.gb.utils.exception.BusinessException;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @title: CooperativeAppointmentLogServiceImp
 * @author: lijh
 * @date: 2023/1/9 16:38
 * @description: 合作预约操作日志实现类
 */
@Slf4j
@Service
@Setter(onMethod_ = {@Autowired})
public class CooperativeAppointmentLogServiceImpl implements CooperativeAppointmentLogService {

    /**
     * 注入MongDB
     */
    private MongoTemplate mongoTemplate;

    private CooperativeAppointmentLogMapper cooperativeAppointmentLogMapper;

    /**
     * 保存日志
     *
     * @param cooperativeAppointmentLog
     * @return
     */
    @Override
    public CooperativeAppointmentLog insert(CooperativeAppointmentLog cooperativeAppointmentLog) {
        log.info("合作预约操作日志保存,  接收数据:{}", cooperativeAppointmentLog);
        cooperativeAppointmentLog.setCreateDateTime(new Date());
        return cooperativeAppointmentLogMapper.insert(cooperativeAppointmentLog);
    }

    /**
     * 分页查询
     *
     * @param cooperativeAppointmentLog
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @Override
    public Page pageEnhance(CooperativeAppointmentLog cooperativeAppointmentLog, int pageNumber, int pageSize) {
        //定义条件
        Criteria criteria = Criteria.where("cooperativeAppointmentId").is(cooperativeAppointmentLog.getCooperativeAppointmentId());
        //定义条件
        Query query = new Query(criteria).skip((pageNumber - 1) * pageSize).limit(pageSize);
        //分页查询,先查询日志记录
        List<CooperativeAppointmentLog> logList = mongoTemplate.find(query, CooperativeAppointmentLog.class);
        //再查询总条数
        long total = mongoTemplate.count(new Query(criteria), CooperativeAppointmentLog.class);
        //塞到Page对象中去
        Page<CooperativeAppointmentLog> page = new Page<>();
        //设置记录
        page.setRecords(logList);
        //设置当前页
        page.setCurrent(pageNumber);
        //设置每页数量
        page.setSize(pageSize);
        //设置总数
        page.setTotal(total);
        return page;
    }

    /**
     * 查询全部
     *
     * @param cooperativeAppointmentLog
     * @return
     */
    @Override
    public List<CooperativeAppointmentLog> listEnhance(CooperativeAppointmentLog cooperativeAppointmentLog) {
        String cooperativeAppointmentId = cooperativeAppointmentLog.getCooperativeAppointmentId();
        if (StringUtils.isBlank(cooperativeAppointmentId)) {
            return new ArrayList<>();
        }
        //模糊查询
        return cooperativeAppointmentLogMapper.select(new Query(Criteria.where("cooperativeAppointmentId").is(cooperativeAppointmentId)));
    }
}
