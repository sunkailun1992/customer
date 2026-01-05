package com.gb.customer.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gb.customer.entity.CustomerCommunication;
import com.gb.customer.service.CustomerCommunicationService;
import com.gb.utils.Json;
import com.gb.utils.RedisUtils;
import com.gb.utils.annotations.Methods;
import com.gb.utils.annotations.PreventRepeat;
import com.gb.utils.annotations.RequestRequired;
import com.gb.utils.enumeration.ReturnCode;
import com.gb.utils.exception.BusinessException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * <p>
 * 客户沟通表 前端控制器
 * </p>
 *
 * @author lijh
 * @since 2021-06-08
 */
@Slf4j
@RequestRequired
@RestController
@Setter(onMethod_ = {@Autowired})
@RequestMapping("/customer-communication")
@Api(tags = "客户沟通表")
public class CustomerCommunicationController {

    /**
     * 客户沟通表
     */
    private CustomerCommunicationService customerCommunicationService;
    /**
     * redis操作
     */
    private StringRedisTemplate stringRedisTemplate;


    /**
     * 客户沟通表集合查询
     *
     * @param current:
     * @param size:
     * @param customerCommunication:
     * @return com.utils.Json
     * @author lijh
     * @since 2021-06-08
     */
    @Methods(methodsName = "客户沟通表集合查询", methods = "select")
    @ApiOperation(value = "客户沟通表集合查询", httpMethod = "GET", notes = "客户沟通表集合查询", response = Json.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", dataType = "int"),
            @ApiImplicitParam(name = "size", value = "分页显示数量", dataType = "int"),
    })
    @GetMapping("/select")
    public Json<IPage<CustomerCommunication>> select(Integer current, Integer size, CustomerCommunication customerCommunication) {
        if (current != null && size != null) {
            Page page = new Page(current, size);
            //返回内容
            return new Json(ReturnCode.成功, customerCommunicationService.pageEnhance(page, customerCommunication));
        } else {
            //返回内容
            return new Json(ReturnCode.成功, customerCommunicationService.listEnhance(customerCommunication));
        }
    }


    /**
     * 客户沟通表单条查询
     *
     * @param customerCommunication:
     * @return com.utils.Json
     * @author lijh
     * @since 2021-06-08
     */
    @Methods(methodsName = "客户沟通表单条查询", methods = "selectOne")
    @ApiOperation(value = "客户沟通表单条查询", httpMethod = "GET", notes = "客户沟通表单条查询", response = Json.class)
    @GetMapping("/selectOne")
    public Json<CustomerCommunication> selectOne(CustomerCommunication customerCommunication) {
        //返回内容
        return new Json(ReturnCode.成功, customerCommunicationService.getOneEnhance(customerCommunication));
    }


    /**
     * 客户沟通表总数查询
     *
     * @param customerCommunication:
     * @return com.utils.Json
     * @author lijh
     * @since 2021-06-08
     */
    @Methods(methodsName = "客户沟通表总数查询", methods = "count")
    @ApiOperation(value = "客户沟通表总数查询", httpMethod = "GET", notes = "客户沟通表总数查询", response = Json.class)
    @GetMapping(value = "/count")
    public Json<Integer> count(CustomerCommunication customerCommunication) {
        //返回内容
        return new Json(ReturnCode.成功, customerCommunicationService.countEnhance(customerCommunication));
    }


    /**
     * 客户沟通表新增
     *
     * @param customerCommunication:
     * @return com.utils.Json
     * @author lijh
     * @since 2021-06-08
     */
    @PreventRepeat
    @Methods(methodsName = "客户沟通表新增", methods = "save")
    @ApiOperation(value = "客户沟通表新增", httpMethod = "POST", notes = "客户沟通表新增", response = Json.class)
    @PostMapping("/save")
    public Json<String> save(@RequestBody CustomerCommunication customerCommunication, HttpServletRequest httpServletRequest) {
        //缓存取出用户
        Map<String, Object> u = RedisUtils.getToken(stringRedisTemplate, httpServletRequest);
        if (null == u) {
            throw new BusinessException("token过期，请重新登录");
        }
        customerCommunication.setCreateName(u.get("name") + "-" + u.get("id"));
        customerCommunication.setUserId((String) u.get("id"));
        Boolean b = customerCommunicationService.saveEnhance(customerCommunication);
        if (b) {
            //返回内容
            return new Json(ReturnCode.成功, customerCommunication.getId());
        } else {
            //返回内容
            return new Json(ReturnCode.系统执行出错, "新增参数数量为空");
        }

    }


    /**
     * 客户沟通表修改
     *
     * @param customerCommunication:
     * @return com.utils.Json
     * @author lijh
     * @since 2021-06-08
     */
    @PreventRepeat
    @Methods(methodsName = "客户沟通表修改", methods = "update")
    @ApiOperation(value = "客户沟通表修改", httpMethod = "PUT", notes = "客户沟通表修改", response = Json.class)
    @PutMapping("/update")
    public Json<Boolean> update(CustomerCommunication customerCommunication, HttpServletRequest httpServletRequest) {
        //缓存取出用户
        Map<String, Object> u = RedisUtils.getToken(stringRedisTemplate, httpServletRequest);
        if (u != null) {
            customerCommunication.setModifyName(u.get("name") + "-" + u.get("id"));
        }
        //返回内容
        return new Json(ReturnCode.成功, customerCommunicationService.updateEnhance(customerCommunication));
    }


    /**
     * 客户沟通表删除
     *
     * @param customerCommunication:
     * @return com.utils.Json
     * @author lijh
     * @since 2021-06-08
     */
    @Methods(methodsName = "客户沟通表删除", methods = "remove")
    @ApiOperation(value = "客户沟通表删除", httpMethod = "DELETE", notes = "客户沟通表删除", response = Json.class)
    @DeleteMapping("/remove")
    public Json<Boolean> remove(CustomerCommunication customerCommunication) {
        return new Json(ReturnCode.成功, customerCommunicationService.removeEnhance(customerCommunication));
    }


}
