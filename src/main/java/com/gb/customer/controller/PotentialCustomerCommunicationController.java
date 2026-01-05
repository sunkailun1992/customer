package com.gb.customer.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
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

import com.gb.customer.service.PotentialCustomerCommunicationService;
import com.gb.customer.entity.PotentialCustomerCommunication;
/**
 * <p>
 * 潜在客户沟通表 前端控制器
 * </p>
 *
 * @author 王一飞
 * @since 2021-04-13
 */
@Slf4j
@RequestRequired
@RestController
@Setter(onMethod_ = {@Autowired})
@RequestMapping("/potential-customer-communication")
@Api(tags = "潜在客户沟通表")
public class PotentialCustomerCommunicationController {

    /**
     * 潜在客户沟通表
     */
    private PotentialCustomerCommunicationService potentialCustomerCommunicationService;
    /**
     * redis操作
     */
    private StringRedisTemplate stringRedisTemplate;


    /**
     * 潜在客户沟通表集合查询
     *
     * @param current:
     * @param size:
     * @param potentialCustomerCommunication:
     * @return com.utils.Json
     * @author 王一飞
     * @since 2021-04-13
     */
    @Methods(methodsName = "潜在客户沟通表集合查询", methods = "select")
    @ApiOperation(value = "潜在客户沟通表集合查询", httpMethod = "GET", notes = "潜在客户沟通表集合查询", response = Json.class)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "current", value = "当前页", dataType = "int"),
        @ApiImplicitParam(name = "size", value = "分页显示数量", dataType = "int"),
    })
    @GetMapping("/select")
    public Json<IPage<PotentialCustomerCommunication>> select(Integer current, Integer size, PotentialCustomerCommunication potentialCustomerCommunication) {
        if (current != null && size != null) {
            Page page = new Page(current, size);
            //返回内容
            return new Json(ReturnCode.成功, potentialCustomerCommunicationService.pageEnhance(page, potentialCustomerCommunication));
        } else {
            //返回内容
            return new Json(ReturnCode.成功, potentialCustomerCommunicationService.listEnhance(potentialCustomerCommunication));
        }
    }


    /**
     * 潜在客户沟通表单条查询
     *
     * @param potentialCustomerCommunication:
     * @return com.utils.Json
     * @author 王一飞
     * @since 2021-04-13
     */
    @Methods(methodsName = "潜在客户沟通表单条查询", methods = "selectOne")
    @ApiOperation(value = "潜在客户沟通表单条查询", httpMethod = "GET", notes = "潜在客户沟通表单条查询", response = Json.class)
    @GetMapping("/selectOne")
    public Json<PotentialCustomerCommunication> selectOne(PotentialCustomerCommunication potentialCustomerCommunication) {
        //返回内容
        return new Json(ReturnCode.成功, potentialCustomerCommunicationService.getOneEnhance(potentialCustomerCommunication));
    }


    /**
     * 潜在客户沟通表总数查询
     *
     * @param potentialCustomerCommunication:
     * @return com.utils.Json
     * @author 王一飞
     * @since 2021-04-13
     */
    @Methods(methodsName = "潜在客户沟通表总数查询", methods = "count")
    @ApiOperation(value = "潜在客户沟通表总数查询", httpMethod = "GET", notes = "潜在客户沟通表总数查询", response = Json.class)
    @GetMapping(value = "/count")
    public Json<Integer> count(PotentialCustomerCommunication potentialCustomerCommunication) {
        //返回内容
        return new Json(ReturnCode.成功, potentialCustomerCommunicationService.countEnhance(potentialCustomerCommunication));
    }


   /**
    * 潜在客户沟通表新增
    *
    * @param potentialCustomerCommunication:
    * @return com.utils.Json
    * @author 王一飞
    * @since 2021-04-13
    */
    @PreventRepeat
    @Methods(methodsName = "潜在客户沟通表新增", methods = "save")
    @ApiOperation(value = "潜在客户沟通表新增", httpMethod = "POST", notes = "潜在客户沟通表新增", response = Json.class)
    @PostMapping("/save")
    public Json<String> save(PotentialCustomerCommunication potentialCustomerCommunication, HttpServletRequest httpServletRequest) {
        //缓存取出用户
        Map<String, Object> u = RedisUtils.getToken(stringRedisTemplate, httpServletRequest);
        if(u != null){
            potentialCustomerCommunication.setCreateName(u.get("name") + "-" + u.get("id"));
        }
        Boolean b = potentialCustomerCommunicationService.saveEnhance(potentialCustomerCommunication);
        if (b) {
            //返回内容
            return new Json(ReturnCode.成功, potentialCustomerCommunication.getId());
        } else {
            //返回内容
            return new Json(ReturnCode.系统执行出错, "新增参数数量为空");
        }

    }


    /**
     * 潜在客户沟通表修改
     *
     * @param potentialCustomerCommunication:
     * @return com.utils.Json
     * @author 王一飞
     * @since 2021-04-13
     */
    @PreventRepeat
    @Methods(methodsName = "潜在客户沟通表修改", methods = "update")
    @ApiOperation(value = "潜在客户沟通表修改", httpMethod = "PUT", notes = "潜在客户沟通表修改", response = Json.class)
    @PutMapping("/update")
    public Json<Boolean> update(PotentialCustomerCommunication potentialCustomerCommunication, HttpServletRequest httpServletRequest) {
        //缓存取出用户
        Map<String, Object> u = RedisUtils.getToken(stringRedisTemplate, httpServletRequest);
        if(u != null){
            potentialCustomerCommunication.setModifyName(u.get("name") + "-" + u.get("id"));
        }
        //返回内容
        return new Json(ReturnCode.成功, potentialCustomerCommunicationService.updateEnhance(potentialCustomerCommunication));
    }


    /**
     * 潜在客户沟通表删除
     *
     * @param potentialCustomerCommunication:
     * @return com.utils.Json
     * @author 王一飞
     * @since 2021-04-13
     */
    @Methods(methodsName = "潜在客户沟通表删除", methods = "remove")
    @ApiOperation(value = "潜在客户沟通表删除", httpMethod = "DELETE", notes = "潜在客户沟通表删除", response = Json.class)
    @DeleteMapping("/remove")
    public Json<Boolean> remove(PotentialCustomerCommunication potentialCustomerCommunication) {
        return new Json(ReturnCode.成功, potentialCustomerCommunicationService.removeEnhance(potentialCustomerCommunication));
    }


}
