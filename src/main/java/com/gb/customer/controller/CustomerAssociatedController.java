package com.gb.customer.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gb.customer.entity.AgentPeopleCommunication;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import java.util.Map;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import com.gb.utils.annotations.RequestRequired;
import java.util.Optional;
import com.gb.utils.enumeration.ReturnCode;
import com.gb.utils.annotations.Methods;
import com.gb.utils.Json;
import com.gb.utils.RedisUtils;
import com.gb.utils.annotations.PreventRepeat;
import javax.servlet.http.HttpServletRequest;

import com.gb.customer.service.CustomerAssociatedService;
import com.gb.customer.entity.CustomerAssociated;
/**
 * <p>
 * 客户关联 前端控制器
 * </p>
 *
 * @author lijh
 * @since 2021-06-09
 */
@Slf4j
@RequestRequired
@RestController
@Setter(onMethod_ = {@Autowired})
@RequestMapping("/customer-associated")
@Api(tags = "客户关联")
public class CustomerAssociatedController {

    /**
     * 客户关联
     */
    private CustomerAssociatedService customerAssociatedService;
    /**
     * redis操作
     */
    private StringRedisTemplate stringRedisTemplate;


    /**
     * 客户关联集合查询
     *
     * @param current:
     * @param size:
     * @param customerAssociated:
     * @return com.utils.Json
     * @author lijh
     * @since 2021-06-09
     */
    @Methods(methodsName = "客户关联集合查询", methods = "select")
    @ApiOperation(value = "客户关联集合查询", httpMethod = "GET", notes = "客户关联集合查询", response = Json.class)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "current", value = "当前页", dataType = "int"),
        @ApiImplicitParam(name = "size", value = "分页显示数量", dataType = "int"),
    })
    @GetMapping("/select")
    public Json<IPage<CustomerAssociated>> select(Integer current, Integer size, CustomerAssociated customerAssociated) {
        if (current != null && size != null) {
            Page page = new Page(current, size);
            //返回内容
            return new Json(ReturnCode.成功, customerAssociatedService.pageEnhance(page, customerAssociated));
        } else {
            //返回内容
            return new Json(ReturnCode.成功, customerAssociatedService.listEnhance(customerAssociated));
        }
    }


    /**
     * 客户关联单条查询
     *
     * @param customerAssociated:
     * @return com.utils.Json
     * @author lijh
     * @since 2021-06-09
     */
    @Methods(methodsName = "客户关联单条查询", methods = "selectOne")
    @ApiOperation(value = "客户关联单条查询", httpMethod = "GET", notes = "客户关联单条查询", response = Json.class)
    @GetMapping("/selectOne")
    public Json<CustomerAssociated> selectOne(CustomerAssociated customerAssociated) {
        //返回内容
        return new Json(ReturnCode.成功, customerAssociatedService.getOneEnhance(customerAssociated));
    }


    /**
     * 客户关联总数查询
     *
     * @param customerAssociated:
     * @return com.utils.Json
     * @author lijh
     * @since 2021-06-09
     */
    @Methods(methodsName = "客户关联总数查询", methods = "count")
    @ApiOperation(value = "客户关联总数查询", httpMethod = "GET", notes = "客户关联总数查询", response = Json.class)
    @GetMapping(value = "/count")
    public Json<Integer> count(CustomerAssociated customerAssociated) {
        //返回内容
        return new Json(ReturnCode.成功, customerAssociatedService.countEnhance(customerAssociated));
    }


   /**
    * 客户关联新增
    *
    * @param customerAssociated:
    * @return com.utils.Json
    * @author lijh
    * @since 2021-06-09
    */
    @PreventRepeat
    @Methods(methodsName = "客户关联新增", methods = "save")
    @ApiOperation(value = "客户关联新增", httpMethod = "POST", notes = "客户关联新增", response = Json.class)
    @PostMapping("/save")
    public Json<String> save(CustomerAssociated customerAssociated, HttpServletRequest httpServletRequest) {
        //缓存取出用户
        Map<String, Object> u = RedisUtils.getToken(stringRedisTemplate, httpServletRequest);
        if(u != null){
            customerAssociated.setCreateName(u.get("name") + "-" + u.get("id"));
        }
        Boolean b = customerAssociatedService.saveEnhance(customerAssociated);
        if (b) {
            //返回内容
            return new Json(ReturnCode.成功, customerAssociated.getId());
        } else {
            //返回内容
            return new Json(ReturnCode.系统执行出错, "新增参数数量为空");
        }

    }


    /**
     * 客户关联修改
     *
     * @param customerAssociated:
     * @return com.utils.Json
     * @author lijh
     * @since 2021-06-09
     */
    @PreventRepeat
    @Methods(methodsName = "客户关联修改", methods = "update")
    @ApiOperation(value = "客户关联修改", httpMethod = "PUT", notes = "客户关联修改", response = Json.class)
    @PutMapping("/update")
    public Json<Boolean> update(CustomerAssociated customerAssociated, HttpServletRequest httpServletRequest) {
        //缓存取出用户
        Map<String, Object> u = RedisUtils.getToken(stringRedisTemplate, httpServletRequest);
        if(u != null){
            customerAssociated.setModifyName(u.get("name") + "-" + u.get("id"));
        }
        //返回内容
        return new Json(ReturnCode.成功, customerAssociatedService.updateEnhance(customerAssociated));
    }


    /**
     * 客户关联删除
     *
     * @param customerAssociated:
     * @return com.utils.Json
     * @author lijh
     * @since 2021-06-09
     */
    @Methods(methodsName = "客户关联删除", methods = "remove")
    @ApiOperation(value = "客户关联删除", httpMethod = "DELETE", notes = "客户关联删除", response = Json.class)
    @DeleteMapping("/remove")
    public Json<Boolean> remove(CustomerAssociated customerAssociated) {
        return new Json(ReturnCode.成功, customerAssociatedService.removeEnhance(customerAssociated));
    }


}
