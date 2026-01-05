package com.gb.log.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gb.log.entity.CooperativeAppointmentLog;
import com.gb.log.entity.PotentialCustomerLog;
import com.gb.log.service.CooperativeAppointmentLogService;
import com.gb.utils.Json;
import com.gb.utils.RedisUtils;
import com.gb.utils.annotations.Methods;
import com.gb.utils.annotations.RequestRequired;
import com.gb.utils.enumeration.ReturnCode;
import com.gb.utils.exception.BusinessException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Objects;

@Slf4j
@RequestRequired
@RestController
@Setter(onMethod_ = {@Autowired})
@RequestMapping("/cooperative-appointment-log")
@Api(tags = "合作预约操作日志记录")
public class CooperativeAppointmentLogController {

    /**
     * redis操作
     */
    private StringRedisTemplate stringRedisTemplate;
    private CooperativeAppointmentLogService cooperativeAppointmentLogService;


    /**
     * 合作预约查询
     *
     * @param cooperativeAppointmentLog
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @Methods(methodsName = "合作预约查询", methods = "select")
    @ApiOperation(value = "合作预约查询", httpMethod = "GET", notes = "合作预约查询", response = Json.class)
    @GetMapping("/select")
    public Json<IPage<CooperativeAppointmentLog>> select(CooperativeAppointmentLog cooperativeAppointmentLog, Integer pageNumber, Integer pageSize) {
        if (Objects.nonNull(pageNumber) && Objects.nonNull(pageSize)) {
            return new Json(ReturnCode.成功, cooperativeAppointmentLogService.pageEnhance(cooperativeAppointmentLog, pageNumber, pageSize));
        } else {
            //返回内容
            return new Json(ReturnCode.成功, cooperativeAppointmentLogService.listEnhance(cooperativeAppointmentLog));
        }
    }


    /**
     * 操作日志, 插入数据
     *
     * @param cooperativeAppointmentLog
     * @param httpServletRequest
     * @return
     */
    @Methods(methodsName = "合作预约MongoDB新增", methods = "save")
    @ApiOperation(value = "合作预约MongoDB新增", httpMethod = "POST", notes = "合作预约MongoDB新增", response = Json.class)
    @PostMapping("/save")
    public Json<PotentialCustomerLog> insert(CooperativeAppointmentLog cooperativeAppointmentLog, HttpServletRequest httpServletRequest) {
        //缓存取出用户
        Map<String, Object> u = RedisUtils.getToken(stringRedisTemplate, httpServletRequest);
        if (MapUtils.isEmpty(u) || null == u.get("id")) {
            throw new BusinessException("无效的token!");
        }
        cooperativeAppointmentLog.setOperator((String) u.get("name"));
        cooperativeAppointmentLog.setOperatorId((String) u.get("id"));
        return new Json(ReturnCode.成功, cooperativeAppointmentLogService.insert(cooperativeAppointmentLog));
    }
}
