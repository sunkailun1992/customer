package com.gb.customer.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.gb.customer.enums.DataPermissionsEnum;
import com.gb.customer.service.CustomerService;
import com.gb.customer.service.PotentialCustomerService;
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

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import com.gb.utils.annotations.RequestRequired;
import com.gb.utils.enumeration.ReturnCode;
import com.gb.utils.annotations.Methods;
import com.gb.utils.Json;
import com.gb.utils.RedisUtils;
import com.gb.utils.annotations.PreventRepeat;

import javax.servlet.http.HttpServletRequest;

import com.gb.customer.service.CustomerCastInsuranceService;
import com.gb.customer.entity.CustomerCastInsurance;

/**
 * <p>
 * 客户投保订单关联 前端控制器
 * </p>
 *
 * @author lijh
 * @since 2021-06-08
 */
@Slf4j
@RequestRequired
@RestController
@Setter(onMethod_ = {@Autowired})
@RequestMapping("/customer-cast-insurance")
@Api(tags = "客户投保订单关联")
public class CustomerCastInsuranceController {

    /**
     * 客户投保订单关联
     */
    private CustomerCastInsuranceService customerCastInsuranceService;
    /**
     * redis操作
     */
    private StringRedisTemplate stringRedisTemplate;

    private CustomerService customerService;

    private PotentialCustomerService potentialCustomerService;

    private CheckPermissions checkPermissions;


    /**
     * 客户投保订单关联集合查询
     *
     * @param current:
     * @param size:
     * @param customerCastInsurance:
     * @return com.utils.Json
     * @author lijh
     * @since 2021-06-08
     */
    @Methods(methodsName = "客户投保订单关联集合查询", methods = "select")
    @ApiOperation(value = "客户投保订单关联集合查询", httpMethod = "GET", notes = "客户投保订单关联集合查询", response = Json.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", dataType = "int"),
            @ApiImplicitParam(name = "size", value = "分页显示数量", dataType = "int"),
    })
    @GetMapping("/select")
    public Json<IPage<CustomerCastInsurance>> select(@RequestParam(required = false, defaultValue = "1") Integer current,
                       @RequestParam(required = false, defaultValue = "10") Integer size, CustomerCastInsurance customerCastInsurance, HttpServletRequest httpServletRequest) {
        //缓存取出用户
        Map<String, Object> u = RedisUtils.getToken(stringRedisTemplate, httpServletRequest);
        if (null == u) {
            throw new BusinessException("token过期，请重新登录");
        }
        List<String> strings = checkPermissions.checkUserPermissions(u);
        log.info("此用户拥有的用户组:" + strings);
        //不属于主管组
        if (checkPermissions.checkDataPermissions(DataPermissionsEnum.CUSTOMER_CAST_INSURANCE_SELECT,strings)) {
            customerCastInsurance.setBusinessStewardQuery((String) u.get("id"));
            customerCastInsurance.setServiceStewardQuery((String) u.get("id"));
        }
        if (current != null && size != null) {
            Page page = new Page(current, size);
            //返回内容
            return new Json(ReturnCode.成功, customerCastInsuranceService.pageEnhance(page, customerCastInsurance));
        } else {
            //返回内容
            return new Json(ReturnCode.成功, customerCastInsuranceService.listEnhance(customerCastInsurance));
        }
    }

    @Methods(methodsName = "分配服务管家", methods = "update")
    @ApiOperation(value = "分配服务管家", httpMethod = "GET", notes = "分配服务管家", response = Json.class)
    @GetMapping("/allotServiceSteward")
    public Json<Boolean> allotServiceSteward(CustomerCastInsurance customerCastInsurance, HttpServletRequest httpServletRequest) {
        //缓存取出用户
        Map<String, Object> u = RedisUtils.getToken(stringRedisTemplate, httpServletRequest);
        if (u != null) {
            customerCastInsurance.setModifyName(u.get("name") + "-" + u.get("id"));
        }
        List<String> userPermissions = checkPermissions.queryUserGroupByUserId(customerCastInsurance.getAgentUserId());
        if (CollectionUtils.isNotEmpty(userPermissions)) {
            customerCastInsurance.setAgentGroupId(userPermissions.get(0));
        }
        return new Json(ReturnCode.成功, customerCastInsuranceService.updateEnhance(customerCastInsurance));
    }

    /**
     * 客户投保订单关联单条查询
     *
     * @param customerCastInsurance:
     * @return com.utils.Json
     * @author lijh
     * @since 2021-06-08
     */
    @Methods(methodsName = "客户投保订单关联单条查询", methods = "selectOne")
    @ApiOperation(value = "客户投保订单关联单条查询", httpMethod = "GET", notes = "客户投保订单关联单条查询", response = Json.class)
    @GetMapping("/selectOne")
    public Json<CustomerCastInsurance> selectOne(CustomerCastInsurance customerCastInsurance) {
        //返回内容
        return new Json(ReturnCode.成功, customerCastInsuranceService.getOneEnhance(customerCastInsurance));
    }


    /**
     * 客户投保订单关联总数查询
     *
     * @param customerCastInsurance:
     * @return com.utils.Json
     * @author lijh
     * @since 2021-06-08
     */
    @Methods(methodsName = "客户投保订单关联总数查询", methods = "count")
    @ApiOperation(value = "客户投保订单关联总数查询", httpMethod = "GET", notes = "客户投保订单关联总数查询", response = Json.class)
    @GetMapping(value = "/count")
    public Json<Integer> count(CustomerCastInsurance customerCastInsurance) {
        //返回内容
        return new Json(ReturnCode.成功, customerCastInsuranceService.countEnhance(customerCastInsurance));
    }


    /**
     * 客户投保订单关联新增
     *
     * @param customerCastInsurance:
     * @return com.utils.Json
     * @author lijh
     * @since 2021-06-08
     */
    @PreventRepeat
    @Methods(methodsName = "客户投保订单关联新增", methods = "save")
    @ApiOperation(value = "客户投保订单关联新增", httpMethod = "POST", notes = "客户投保订单关联新增", response = Json.class)
    @PostMapping("/save")
    public Json<String> save(CustomerCastInsurance customerCastInsurance, HttpServletRequest httpServletRequest) {
        //缓存取出用户
        Map<String, Object> u = RedisUtils.getToken(stringRedisTemplate, httpServletRequest);
        if (u != null) {
            customerCastInsurance.setCreateName(u.get("name") + "-" + u.get("id"));
        }
        Boolean b = customerCastInsuranceService.saveEnhance(customerCastInsurance);
        if (b) {
            //返回内容
            return new Json(ReturnCode.成功, customerCastInsurance.getId());
        } else {
            //返回内容
            return new Json(ReturnCode.系统执行出错, "新增参数数量为空");
        }

    }


    /**
     * 客户投保订单关联修改
     *
     * @param customerCastInsurance:
     * @return com.utils.Json
     * @author lijh
     * @since 2021-06-08
     */
    @PreventRepeat
    @Methods(methodsName = "客户投保订单关联修改", methods = "update")
    @ApiOperation(value = "客户投保订单关联修改", httpMethod = "PUT", notes = "客户投保订单关联修改", response = Json.class)
    @PutMapping("/update")
    public Json<Boolean> update(CustomerCastInsurance customerCastInsurance, HttpServletRequest httpServletRequest) {
        //缓存取出用户
        Map<String, Object> u = RedisUtils.getToken(stringRedisTemplate, httpServletRequest);
        if (u != null) {
            customerCastInsurance.setModifyName(u.get("name") + "-" + u.get("id"));
        }
        //返回内容
        return new Json(ReturnCode.成功, customerCastInsuranceService.updateEnhance(customerCastInsurance));
    }


    /**
     * TODO 投保订单id修改
     *
     * @param customerCastInsurance
     * @param httpServletRequest
     * @return com.gb.utils.Json<java.lang.Boolean>
     * @author 孙凯伦
     * @methodName castInsuranceIdUpdate
     * @time 2023/8/9 15:13
     */
    @PreventRepeat
    @Methods(methodsName = "投保订单id修改", methods = "castInsuranceIdUpdate")
    @ApiOperation(value = "投保订单id修改", httpMethod = "PUT", notes = "投保订单id修改", response = Json.class)
    @PutMapping("/castInsuranceIdUpdate")
    public Json<Boolean> castInsuranceIdUpdate(CustomerCastInsurance customerCastInsurance, HttpServletRequest httpServletRequest) {
        //缓存取出用户
        Map<String, Object> u = RedisUtils.getToken(stringRedisTemplate, httpServletRequest);
        if (u != null) {
            customerCastInsurance.setModifyName(u.get("name") + "-" + u.get("id"));
        }
        //返回内容
        return new Json(ReturnCode.成功, customerCastInsuranceService.castInsuranceIdUpdate(customerCastInsurance));
    }


    /**
     * 客户投保订单关联删除
     *
     * @param customerCastInsurance:
     * @return com.utils.Json
     * @author lijh
     * @since 2021-06-08
     */
    @Methods(methodsName = "客户投保订单关联删除", methods = "remove")
    @ApiOperation(value = "客户投保订单关联删除", httpMethod = "DELETE", notes = "客户投保订单关联删除", response = Json.class)
    @DeleteMapping("/remove")
    public Json<Boolean> remove(CustomerCastInsurance customerCastInsurance) {
        return new Json(ReturnCode.成功, customerCastInsuranceService.removeEnhance(customerCastInsurance));
    }


}
