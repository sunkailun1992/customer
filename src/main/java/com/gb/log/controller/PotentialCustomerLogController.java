package com.gb.log.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gb.log.entity.PotentialCustomerLog;
import com.gb.log.service.PotentialCustomerLogService;
import com.gb.utils.Json;
import com.gb.utils.annotations.Methods;
import com.gb.utils.annotations.RequestRequired;
import java.util.Optional;
import com.gb.utils.enumeration.ReturnCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @author wangyifei
 * @description:
 * @since 2021/3/12  17:23
 */
@Slf4j
@RequestRequired
@RestController
@Setter(onMethod_ = {@Autowired})
@RequestMapping("/potential-customer-log")
@Api(tags = "投保咨询操作日志记录")
public class PotentialCustomerLogController {

    private PotentialCustomerLogService potentialCustomerLogService;

    /**
     * 投保咨询查询
     *
     * @param potentialCustomerLog
     * @return com.utils.Json
     * @author 王一飞
     * @since 2021/3/12  17:27
     */
    @Methods(methodsName = "投保咨询查询", methods = "select")
    @ApiOperation(value = "投保咨询查询", httpMethod = "GET", notes = "投保咨询查询", response = Json.class)
    @GetMapping("/select")
    public Json<IPage<PotentialCustomerLog>> select(PotentialCustomerLog potentialCustomerLog, Integer pageNumber, Integer pageSize) {
        if (Objects.nonNull(pageNumber) && Objects.nonNull(pageSize)) {
            return new Json(ReturnCode.成功, potentialCustomerLogService.pageEnhance(potentialCustomerLog, pageNumber, pageSize));
        } else {
            //返回内容
            return new Json(ReturnCode.成功, potentialCustomerLogService.listEnhance(potentialCustomerLog));
        }
    }


    /**
     * @description: 操作日志, 插入数据
     * @params: com.gb.product.entity.UserSpuConsulting
     * 　* @author wangyifei
     * 　* @date 2021/3/12
     */
    @Methods(methodsName = "投保咨询MongoDB新增", methods = "save")
    @ApiOperation(value = "投保咨询MongoDB新增", httpMethod = "POST", notes = "投保咨询MongoDB新增", response = Json.class)
    @PostMapping("/save")
    public Json<PotentialCustomerLog> insert(PotentialCustomerLog potentialCustomerLog, HttpServletRequest httpServletRequest) {
        return new Json(ReturnCode.成功, potentialCustomerLogService.insert(potentialCustomerLog,httpServletRequest));
    }
}
