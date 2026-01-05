package com.gb.customer.controller;

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
import java.util.Optional;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import com.gb.utils.annotations.RequestRequired;
import com.gb.utils.annotations.Methods;
import com.gb.utils.Json;
import com.gb.utils.RedisUtils;
import com.gb.utils.annotations.PreventRepeat;
import javax.servlet.http.HttpServletRequest;
import org.springframework.validation.annotation.Validated;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.gb.utils.enumeration.ReturnCode;
import com.gb.customer.service.CustomerManagerRuleConfigurationService;
import com.gb.customer.entity.query.CustomerManagerRuleConfigurationQuery;
import com.gb.customer.entity.vo.CustomerManagerRuleConfigurationVO;
import com.gb.customer.entity.bo.CustomerManagerRuleConfigurationBO;


/**
 * Created with IntelliJ IDEA.
 * @author:     	ljh
 * @since:   	    2021-09-07 02:27:46
 * @description:	TODO  客户管理规则配置表，Comment请求层
 * @source:  	    代码生成器
 */
@Slf4j
@RequestRequired
@ApiSupport(author = "ljh")
@Setter(onMethod_ = {@Autowired})
@RestController
@RequestMapping("/customerManagerRuleConfiguration")
@Api(tags = "客户管理规则配置表")
public class CustomerManagerRuleConfigurationController {


    /**
     * 客户管理规则配置表
     */
    private CustomerManagerRuleConfigurationService customerManagerRuleConfigurationService;
    /**
     * redis操作
     */
    private StringRedisTemplate stringRedisTemplate;


    /**
     * 客户管理规则配置表集合分页查询
     *
     * @param current:
     * @param size:
     * @param customerManagerRuleConfigurationQuery:
     * @return com.utils.Json
     * @author ljh
     * @since 2021-09-07 02:27:46
     */
    @Methods(methodsName = "客户管理规则配置表集合分页查询", methods = "select")
    @ApiOperation(value = "客户管理规则配置表集合分页查询", httpMethod = "GET", notes = "客户管理规则配置表集合查询", response = Json.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", dataType = "int", required = true),
            @ApiImplicitParam(name = "size", value = "分页显示数量", dataType = "int", required = true),
    })
    @GetMapping("/select")
    public Json<Page<CustomerManagerRuleConfigurationVO>> select(@Validated(value = CustomerManagerRuleConfigurationQuery.Select.class) CustomerManagerRuleConfigurationQuery customerManagerRuleConfigurationQuery, Integer current, Integer size) {
        //返回内容
        return new Json(ReturnCode.成功, customerManagerRuleConfigurationService.pageEnhance(new Page(current, size), customerManagerRuleConfigurationQuery));
    }


    /**
     * 客户管理规则配置表集合查询
     *
     * @param customerManagerRuleConfigurationQuery:
     * @return com.utils.Json
     * @author ljh
     * @since 2021-09-07 02:27:46
     */
    @Methods(methodsName = "客户管理规则配置表集合查询", methods = "selectList")
    @ApiOperation(value = "客户管理规则配置表集合查询", httpMethod = "GET", notes = "客户管理规则配置表集合查询", response = Json.class)
    @GetMapping("/selectList")
    public Json<List<CustomerManagerRuleConfigurationVO>> selectList(@Validated(value = CustomerManagerRuleConfigurationQuery.SelectList.class) CustomerManagerRuleConfigurationQuery customerManagerRuleConfigurationQuery) {
        //返回内容
        return new Json(ReturnCode.成功, customerManagerRuleConfigurationService.listEnhance(customerManagerRuleConfigurationQuery));
    }


    /**
     * 客户管理规则配置表单条查询
     *
     * @param customerManagerRuleConfigurationQuery:
     * @return com.utils.Json
     * @author ljh
     * @since 2021-09-07 02:27:46
     */
    @Methods(methodsName = "客户管理规则配置表单条查询", methods = "selectOne")
    @ApiOperation(value = "客户管理规则配置表单条查询", httpMethod = "GET", notes = "客户管理规则配置表单条查询", response = Json.class)
    @GetMapping("/selectOne")
    public Json<CustomerManagerRuleConfigurationVO> selectOne(@Validated(value = CustomerManagerRuleConfigurationQuery.SelectOne.class) CustomerManagerRuleConfigurationQuery customerManagerRuleConfigurationQuery) {
        //返回内容
        return new Json(ReturnCode.成功, customerManagerRuleConfigurationService.getOneEnhance(customerManagerRuleConfigurationQuery));
    }


    /**
     * 客户管理规则配置表总数查询
     *
     * @param customerManagerRuleConfigurationQuery:
     * @return com.utils.Json
     * @author ljh
     * @since 2021-09-07 02:27:46
     */
    @Methods(methodsName = "客户管理规则配置表总数查询", methods = "count")
    @ApiOperation(value = "客户管理规则配置表总数查询", httpMethod = "GET", notes = "客户管理规则配置表总数查询", response = Json.class)
    @GetMapping(value = "/count")
    public Json<Integer> count(@Validated(value = CustomerManagerRuleConfigurationQuery.Count.class) CustomerManagerRuleConfigurationQuery customerManagerRuleConfigurationQuery) {
        //返回内容
        return new Json(ReturnCode.成功, customerManagerRuleConfigurationService.countEnhance(customerManagerRuleConfigurationQuery));
    }


   /**
    * 客户管理规则配置表新增
    *
    * @param customerManagerRuleConfigurationBO:
    * @return com.utils.Json
    * @author ljh
    * @since 2021-09-07 02:27:46
    */
    @PreventRepeat
    @Methods(methodsName = "客户管理规则配置表新增", methods = "save")
    @ApiOperation(value = "客户管理规则配置表新增", httpMethod = "POST", notes = "客户管理规则配置表新增", response = Json.class)
    @ApiOperationSupport(ignoreParameters = {"id","createDateTime","createName","modifyDateTime","modifyName","isDelete","version"})
    @PostMapping("/save")
    public Json<String> save(@Validated(value = CustomerManagerRuleConfigurationBO.Save.class) CustomerManagerRuleConfigurationBO customerManagerRuleConfigurationBO, HttpServletRequest httpServletRequest) {
        //缓存取出用户
        Map<String, Object> u = RedisUtils.getToken(stringRedisTemplate, httpServletRequest);
        if(u != null){
            customerManagerRuleConfigurationBO.setCreateName(u.get("name") + "-" + u.get("id"));
        }
        //返回内容
        return new Json(ReturnCode.成功, customerManagerRuleConfigurationService.saveEnhance(customerManagerRuleConfigurationBO));
    }


    /**
     * 客户管理规则配置表修改
     *
     * @param customerManagerRuleConfigurationBO:
     * @return com.utils.Json
     * @author ljh
     * @since 2021-09-07 02:27:46
     */
    @PreventRepeat
    @Methods(methodsName = "客户管理规则配置表修改", methods = "update")
    @ApiOperation(value = "客户管理规则配置表修改", httpMethod = "PUT", notes = "客户管理规则配置表修改", response = Json.class)
    @ApiOperationSupport(ignoreParameters = {"createDateTime","createName","modifyDateTime","modifyName","isDelete","version"})
    @PutMapping("/update")
    public Json<Boolean> update(@Validated(value = CustomerManagerRuleConfigurationBO.Update.class) CustomerManagerRuleConfigurationBO customerManagerRuleConfigurationBO, HttpServletRequest httpServletRequest) {
        //缓存取出用户
        Map<String, Object> u = RedisUtils.getToken(stringRedisTemplate, httpServletRequest);
        if(u != null){
            customerManagerRuleConfigurationBO.setModifyName(u.get("name") + "-" + u.get("id"));
        }
        //返回内容
        return new Json(ReturnCode.成功, customerManagerRuleConfigurationService.updateEnhance(customerManagerRuleConfigurationBO));
    }


    /**
     * 客户管理规则配置表删除
     *
     * @param customerManagerRuleConfigurationBO:
     * @return com.utils.Json
     * @author ljh
     * @since 2021-09-07 02:27:46
     */
    @Methods(methodsName = "客户管理规则配置表删除", methods = "remove")
    @ApiOperation(value = "客户管理规则配置表删除", httpMethod = "DELETE", notes = "客户管理规则配置表删除", response = Json.class)
    @ApiOperationSupport(includeParameters = {"id"})
    @DeleteMapping("/remove")
    public Json<Boolean> remove(@Validated(value = CustomerManagerRuleConfigurationBO.Remove.class) CustomerManagerRuleConfigurationBO customerManagerRuleConfigurationBO) {
        return new Json(ReturnCode.成功, customerManagerRuleConfigurationService.removeEnhance(customerManagerRuleConfigurationBO));
    }


}