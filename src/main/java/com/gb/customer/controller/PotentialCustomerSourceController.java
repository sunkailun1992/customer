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

import com.gb.customer.service.PotentialCustomerSourceService;
import com.gb.customer.entity.PotentialCustomerSource;
/**
 * <p>
 * 潜在客户来源表 前端控制器
 * </p>
 *
 * @author 王一飞
 * @since 2021-04-13
 */
@Slf4j
@RequestRequired
@RestController
@Setter(onMethod_ = {@Autowired})
@RequestMapping("/potential-customer-source")
@Api(tags = "潜在客户来源表")
public class PotentialCustomerSourceController {

    /**
     * 潜在客户来源表
     */
    private PotentialCustomerSourceService potentialCustomerSourceService;
    /**
     * redis操作
     */
    private StringRedisTemplate stringRedisTemplate;


    /**
     * 潜在客户来源表集合查询
     *
     * @param current:
     * @param size:
     * @param potentialCustomerSource:
     * @return com.utils.Json
     * @author 王一飞
     * @since 2021-04-13
     */
    @Methods(methodsName = "潜在客户来源表集合查询", methods = "select")
    @ApiOperation(value = "潜在客户来源表集合查询", httpMethod = "GET", notes = "潜在客户来源表集合查询", response = Json.class)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "current", value = "当前页", dataType = "int"),
        @ApiImplicitParam(name = "size", value = "分页显示数量", dataType = "int"),
    })
    @GetMapping("/select")
    public Json<IPage<PotentialCustomerSource>> select(Integer current, Integer size, PotentialCustomerSource potentialCustomerSource) {

        if (current != null && size != null) {
            Page page = new Page(current, size);
            //返回内容
            return new Json(ReturnCode.成功, potentialCustomerSourceService.pageEnhance(page, potentialCustomerSource));
        } else {
            //返回内容
            return new Json(ReturnCode.成功, potentialCustomerSourceService.listEnhance(potentialCustomerSource));
        }
    }


    /**
     * 潜在客户来源表单条查询
     *
     * @param potentialCustomerSource:
     * @return com.utils.Json
     * @author 王一飞
     * @since 2021-04-13
     */
    @Methods(methodsName = "潜在客户来源表单条查询", methods = "selectOne")
    @ApiOperation(value = "潜在客户来源表单条查询", httpMethod = "GET", notes = "潜在客户来源表单条查询", response = Json.class)
    @GetMapping("/selectOne")
    public Json<PotentialCustomerSource> selectOne(PotentialCustomerSource potentialCustomerSource) {
        //返回内容
        return new Json(ReturnCode.成功, potentialCustomerSourceService.getOneEnhance(potentialCustomerSource));
    }


    /**
     * 潜在客户来源表总数查询
     *
     * @param potentialCustomerSource:
     * @return com.utils.Json
     * @author 王一飞
     * @since 2021-04-13
     */
    @Methods(methodsName = "潜在客户来源表总数查询", methods = "count")
    @ApiOperation(value = "潜在客户来源表总数查询", httpMethod = "GET", notes = "潜在客户来源表总数查询", response = Json.class)
    @GetMapping(value = "/count")
    public Json<Integer> count(PotentialCustomerSource potentialCustomerSource) {
        //返回内容
        return new Json(ReturnCode.成功, potentialCustomerSourceService.countEnhance(potentialCustomerSource));
    }


   /**
    * 潜在客户来源表新增
    *
    * @param potentialCustomerSource:
    * @return com.utils.Json
    * @author 王一飞
    * @since 2021-04-13
    */
    @PreventRepeat
    @Methods(methodsName = "潜在客户来源表新增", methods = "save")
    @ApiOperation(value = "潜在客户来源表新增", httpMethod = "POST", notes = "潜在客户来源表新增", response = Json.class)
    @PostMapping("/save")
    public Json<String> save(PotentialCustomerSource potentialCustomerSource, HttpServletRequest httpServletRequest) {
        //缓存取出用户
        Map<String, Object> u = RedisUtils.getToken(stringRedisTemplate, httpServletRequest);
        if(u != null){
            potentialCustomerSource.setCreateName(u.get("name") + "-" + u.get("id"));
        }
        Boolean b = potentialCustomerSourceService.saveEnhance(potentialCustomerSource);
        if (b) {
            //返回内容
            return new Json(ReturnCode.成功, potentialCustomerSource.getId());
        } else {
            //返回内容
            return new Json(ReturnCode.系统执行出错, "新增参数数量为空");
        }

    }


    /**
     * 潜在客户来源表修改
     *
     * @param potentialCustomerSource:
     * @return com.utils.Json
     * @author 王一飞
     * @since 2021-04-13
     */
    @PreventRepeat
    @Methods(methodsName = "潜在客户来源表修改", methods = "update")
    @ApiOperation(value = "潜在客户来源表修改", httpMethod = "PUT", notes = "潜在客户来源表修改", response = Json.class)
    @PutMapping("/update")
    public Json<Boolean> update(PotentialCustomerSource potentialCustomerSource, HttpServletRequest httpServletRequest) {
        //缓存取出用户
        Map<String, Object> u = RedisUtils.getToken(stringRedisTemplate, httpServletRequest);
        if(u != null){
            potentialCustomerSource.setModifyName(u.get("name") + "-" + u.get("id"));
        }
        //返回内容
        return new Json(ReturnCode.成功, potentialCustomerSourceService.updateEnhance(potentialCustomerSource));
    }


    /**
     * 潜在客户来源表删除
     *
     * @param potentialCustomerSource:
     * @return com.utils.Json
     * @author 王一飞
     * @since 2021-04-13
     */
    @Methods(methodsName = "潜在客户来源表删除", methods = "remove")
    @ApiOperation(value = "潜在客户来源表删除", httpMethod = "DELETE", notes = "潜在客户来源表删除", response = Json.class)
    @DeleteMapping("/remove")
    public Json<Boolean> remove(PotentialCustomerSource potentialCustomerSource) {
        return new Json(ReturnCode.成功, potentialCustomerSourceService.removeEnhance(potentialCustomerSource));
    }


}
