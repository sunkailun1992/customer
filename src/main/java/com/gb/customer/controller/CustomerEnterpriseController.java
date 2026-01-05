package com.gb.customer.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.gb.customer.entity.Customer;
import com.gb.customer.enums.DataPermissionsEnum;
import com.gb.customer.service.CustomerService;
import com.gb.customer.service.impl.CheckPermissions;
import com.gb.utils.exception.BusinessException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import com.gb.utils.annotations.RequestRequired;
import com.gb.utils.enumeration.ReturnCode;
import com.gb.utils.annotations.Methods;
import com.gb.utils.Json;
import com.gb.utils.RedisUtils;
import com.gb.utils.annotations.PreventRepeat;

import javax.servlet.http.HttpServletRequest;

import com.gb.customer.service.CustomerEnterpriseService;
import com.gb.customer.entity.CustomerEnterprise;

/**
 * <p>
 * 客户企业 前端控制器
 * </p>
 *
 * @author lijh
 * @since 2021-06-10
 */
@Slf4j
@RequestRequired
@RestController
@Setter(onMethod_ = {@Autowired})
@RequestMapping("/customer-enterprise")
@Api(tags = "客户企业")
public class CustomerEnterpriseController {

    /**
     * 客户企业
     */
    private CustomerEnterpriseService customerEnterpriseService;
    /**
     * redis操作
     */
    private StringRedisTemplate stringRedisTemplate;

    private CustomerService customerService;

    private CheckPermissions checkPermissions;


    /**
     * 客户企业集合查询
     *
     * @param current:
     * @param size:
     * @param customerEnterprise:
     * @return com.utils.Json
     * @author lijh
     * @since 2021-06-10
     */
    @Methods(methodsName = "客户企业集合查询", methods = "select")
    @ApiOperation(value = "客户企业集合查询", httpMethod = "GET", notes = "客户企业集合查询", response = Json.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", dataType = "int"),
            @ApiImplicitParam(name = "size", value = "分页显示数量", dataType = "int"),
    })
    @GetMapping("/select")
    public Json<IPage<CustomerEnterprise>> select(Integer current, Integer size, CustomerEnterprise customerEnterprise, HttpServletRequest httpServletRequest) {
        //缓存取出用户
        Map<String, Object> u = RedisUtils.getToken(stringRedisTemplate, httpServletRequest);
        if (null == u) {
            throw new BusinessException("token过期，请重新登录");
        }
        List<String> list = new ArrayList<>();
        QueryWrapper<Customer> queryWrapper = new QueryWrapper<>();
        List<String> strings = checkPermissions.checkUserPermissions(u);
        //不属于指定主管组,查看是否有绑定用户
        if (checkPermissions.checkDataPermissions(DataPermissionsEnum.CUSTOMER_ENTERPRISE_SELECT,strings)) {
            queryWrapper.eq("agent_user_id", u.get("id"));
            list = customerService.list(queryWrapper).stream().map(Customer::getId).collect(Collectors.toList());
            //查不到所绑定用户，就没有企业数据
            if (CollectionUtils.isEmpty(list)) {
                return new Json(ReturnCode.成功, new Page(current, size));
            }
        }
        customerEnterprise.setCustomerIds(list);
        if (current != null && size != null) {
            Page page = new Page(current, size);
            //返回内容
            return new Json(ReturnCode.成功, customerEnterpriseService.pageEnhance(page, customerEnterprise));
        } else {
            //返回内容
            return new Json(ReturnCode.成功, customerEnterpriseService.listEnhance(customerEnterprise));
        }
    }


    /**
     * 客户企业单条查询
     *
     * @param customerEnterprise:
     * @return com.utils.Json
     * @author lijh
     * @since 2021-06-10
     */
    @Methods(methodsName = "客户企业单条查询", methods = "selectOne")
    @ApiOperation(value = "客户企业单条查询", httpMethod = "GET", notes = "客户企业单条查询", response = Json.class)
    @GetMapping("/selectOne")
    public Json<CustomerEnterprise> selectOne(CustomerEnterprise customerEnterprise) {
        //返回内容
        return new Json(ReturnCode.成功, customerEnterpriseService.getOneEnhance(customerEnterprise));
    }


    /**
     * 客户企业总数查询
     *
     * @param customerEnterprise:
     * @return com.utils.Json
     * @author lijh
     * @since 2021-06-10
     */
    @Methods(methodsName = "客户企业总数查询", methods = "count")
    @ApiOperation(value = "客户企业总数查询", httpMethod = "GET", notes = "客户企业总数查询", response = Json.class)
    @GetMapping(value = "/count")
    public Json<Integer> count(CustomerEnterprise customerEnterprise) {
        //返回内容
        return new Json(ReturnCode.成功, customerEnterpriseService.countEnhance(customerEnterprise));
    }


    /**
     * 客户企业新增
     *
     * @param customerEnterprise:
     * @return com.utils.Json
     * @author lijh
     * @since 2021-06-10
     */
    @PreventRepeat
    @Methods(methodsName = "客户企业新增", methods = "save")
    @ApiOperation(value = "客户企业新增", httpMethod = "POST", notes = "客户企业新增", response = Json.class)
    @PostMapping("/save")
    public Json<String> save(CustomerEnterprise customerEnterprise, HttpServletRequest httpServletRequest) {
        //缓存取出用户
        Map<String, Object> u = RedisUtils.getToken(stringRedisTemplate, httpServletRequest);
        if (u != null) {
            customerEnterprise.setCreateName(u.get("name") + "-" + u.get("id"));
        }
        Boolean b = customerEnterpriseService.saveEnhance(customerEnterprise);
        if (b) {
            //返回内容
            return new Json(ReturnCode.成功, customerEnterprise.getId());
        } else {
            //返回内容
            return new Json(ReturnCode.系统执行出错, "新增参数数量为空");
        }

    }


    /**
     * 客户企业修改
     *
     * @param customerEnterprise:
     * @return com.utils.Json
     * @author lijh
     * @since 2021-06-10
     */
    @PreventRepeat
    @Methods(methodsName = "客户企业修改", methods = "update")
    @ApiOperation(value = "客户企业修改", httpMethod = "PUT", notes = "客户企业修改", response = Json.class)
    @PutMapping("/update")
    public Json<Boolean> update(CustomerEnterprise customerEnterprise, HttpServletRequest httpServletRequest) {
        //缓存取出用户
        Map<String, Object> u = RedisUtils.getToken(stringRedisTemplate, httpServletRequest);
        if (u != null) {
            customerEnterprise.setModifyName(u.get("name") + "-" + u.get("id"));
        }
        //返回内容
        return new Json(ReturnCode.成功, customerEnterpriseService.updateEnhance(customerEnterprise));
    }


    /**
     * 客户企业删除
     *
     * @param customerEnterprise:
     * @return com.utils.Json
     * @author lijh
     * @since 2021-06-10
     */
    @Methods(methodsName = "客户企业删除", methods = "remove")
    @ApiOperation(value = "客户企业删除", httpMethod = "DELETE", notes = "客户企业删除", response = Json.class)
    @DeleteMapping("/remove")
    public Json<Boolean> remove(CustomerEnterprise customerEnterprise) {
        return new Json(ReturnCode.成功, customerEnterpriseService.removeEnhance(customerEnterprise));
    }


}
