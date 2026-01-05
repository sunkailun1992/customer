package com.gb.log.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gb.log.entity.PotentialCustomerLog;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author wangyifei
 * @Description : 产品咨询操作日志
 * @date 2021/3/1210:17
 */
public interface PotentialCustomerLogService {

    /**
     * 操作日志, 插入数据
     *
     * @param potentialCustomerLog
     * @param httpServletRequest
     * @return
     */
    PotentialCustomerLog insert(PotentialCustomerLog potentialCustomerLog, HttpServletRequest httpServletRequest);

    /**
     * 操作日志, 查询分页
     *
     * @param potentialCustomerLog
     * @param pageNumber
     * @param pageSize
     * @return
     */
    Page pageEnhance(PotentialCustomerLog potentialCustomerLog, int pageNumber, int pageSize);

    /**
     * 操作日志, 查询
     *
     * @param potentialCustomerLog
     * @return
     */
    List<PotentialCustomerLog> listEnhance(PotentialCustomerLog potentialCustomerLog);
}
