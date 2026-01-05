package com.gb.log.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gb.log.entity.CooperativeAppointmentLog;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface CooperativeAppointmentLogService {

    /**
     * 操作日志, 插入数据
     *
     * @param cooperativeAppointmentLog
     * @return
     */
    CooperativeAppointmentLog insert(CooperativeAppointmentLog cooperativeAppointmentLog);

    /**
     * 操作日志, 查询分页
     *
     * @param cooperativeAppointmentLog
     * @param pageNumber
     * @param pageSize
     * @return
     */
    Page pageEnhance(CooperativeAppointmentLog cooperativeAppointmentLog, int pageNumber, int pageSize);

    /**
     * 操作日志, 查询
     *
     * @param cooperativeAppointmentLog
     * @return
     */
    List<CooperativeAppointmentLog> listEnhance(CooperativeAppointmentLog cooperativeAppointmentLog);
}
