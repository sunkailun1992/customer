package com.gb.customer.controller;

import com.gb.customer.service.PotentialCustomerService;
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
import com.gb.customer.service.AssistantAssociatedService;
import com.gb.customer.entity.query.AssistantAssociatedQuery;
import com.gb.customer.entity.vo.AssistantAssociatedVO;
import com.gb.customer.entity.bo.AssistantAssociatedBO;


/**
 * Created with IntelliJ IDEA.
 *
 * @author: lijh
 * @since: 2021-11-02 10:03:07
 * @description: TODO 助理关联，Comment请求层
 * @source: 代码生成器
 */
@Slf4j
@RequestRequired
@ApiSupport(author = "lijh")
@Setter(onMethod_ = {@Autowired})
@RestController
@RequestMapping("/assistantAssociated")
@Api(tags = "助理关联")
public class AssistantAssociatedController {


    /**
     * 助理关联
     */
    private AssistantAssociatedService assistantAssociatedService;
    /**
     * redis操作
     */
    private StringRedisTemplate stringRedisTemplate;

    private PotentialCustomerService potentialCustomerService;


    /**
     * 助理关联集合分页查询
     *
     * @param current:
     * @param size:
     * @param assistantAssociatedQuery:
     * @return com.utils.Json
     * @author lijh
     * @since 2021-11-02 10:03:07
     */
    @Methods(methodsName = "助理关联集合分页查询", methods = "select")
    @ApiOperation(value = "助理关联集合分页查询", httpMethod = "GET", notes = "助理关联集合查询", response = Json.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", dataType = "int", required = true),
            @ApiImplicitParam(name = "size", value = "分页显示数量", dataType = "int", required = true),
    })
    @GetMapping("/select")
    public Json<Page<AssistantAssociatedVO>> select(@Validated(value = AssistantAssociatedQuery.Select.class) AssistantAssociatedQuery assistantAssociatedQuery, Integer current, Integer size) {
        //返回内容
        return new Json(ReturnCode.成功, assistantAssociatedService.pageEnhance(new Page(current, size), assistantAssociatedQuery));
    }


    /**
     * 助理关联集合查询
     *
     * @param assistantAssociatedQuery:
     * @return com.utils.Json
     * @author lijh
     * @since 2021-11-02 10:03:07
     */
    @Methods(methodsName = "助理关联集合查询", methods = "selectList")
    @ApiOperation(value = "助理关联集合查询", httpMethod = "GET", notes = "助理关联集合查询", response = Json.class)
    @GetMapping("/selectList")
    public Json<List<AssistantAssociatedVO>> selectList(@Validated(value = AssistantAssociatedQuery.SelectList.class) AssistantAssociatedQuery assistantAssociatedQuery) {
        //返回内容
        return new Json(ReturnCode.成功, assistantAssociatedService.listEnhance(assistantAssociatedQuery));
    }


    /**
     * 助理关联单条查询
     *
     * @param assistantAssociatedQuery:
     * @return com.utils.Json
     * @author lijh
     * @since 2021-11-02 10:03:07
     */
    @Methods(methodsName = "助理关联单条查询", methods = "selectOne")
    @ApiOperation(value = "助理关联单条查询", httpMethod = "GET", notes = "助理关联单条查询", response = Json.class)
    @GetMapping("/selectOne")
    public Json<AssistantAssociatedVO> selectOne(@Validated(value = AssistantAssociatedQuery.SelectOne.class) AssistantAssociatedQuery assistantAssociatedQuery) {
        log.info("[查询绑定业务助理] 管理id={},助理id={}", assistantAssociatedQuery.getHousekeeperId(), assistantAssociatedQuery.getAssistantId());
        //返回内容
        return new Json(ReturnCode.成功, assistantAssociatedService.getOneEnhance(assistantAssociatedQuery));
    }


    /**
     * 助理关联总数查询
     *
     * @param assistantAssociatedQuery:
     * @return com.utils.Json
     * @author lijh
     * @since 2021-11-02 10:03:07
     */
    @Methods(methodsName = "助理关联总数查询", methods = "count")
    @ApiOperation(value = "助理关联总数查询", httpMethod = "GET", notes = "助理关联总数查询", response = Json.class)
    @GetMapping(value = "/count")
    public Json<Integer> count(@Validated(value = AssistantAssociatedQuery.Count.class) AssistantAssociatedQuery assistantAssociatedQuery) {
        //返回内容
        return new Json(ReturnCode.成功, assistantAssociatedService.countEnhance(assistantAssociatedQuery));
    }


    /**
     * 助理关联新增
     *
     * @param assistantAssociatedBO:
     * @return com.utils.Json
     * @author lijh
     * @since 2021-11-02 10:03:07
     */
    @PreventRepeat
    @Methods(methodsName = "助理关联新增", methods = "save")
    @ApiOperation(value = "助理关联新增", httpMethod = "POST", notes = "助理关联新增", response = Json.class)
    @ApiOperationSupport(ignoreParameters = {"id", "createDateTime", "createName", "modifyDateTime", "modifyName", "isDelete", "version"})
    @PostMapping("/save")
    public Json<String> save(@Validated(value = AssistantAssociatedBO.Save.class) AssistantAssociatedBO assistantAssociatedBO, HttpServletRequest httpServletRequest) {
        //缓存取出用户
        Map<String, Object> u = RedisUtils.getToken(stringRedisTemplate, httpServletRequest);
        if (u != null) {
            assistantAssociatedBO.setCreateName(u.get("name") + "-" + u.get("id"));
        }
        //返回内容
        return new Json(ReturnCode.成功, assistantAssociatedService.saveEnhance(assistantAssociatedBO));
    }


    /**
     * 助理关联修改
     *
     * @param assistantAssociatedBO:
     * @return com.utils.Json
     * @author lijh
     * @since 2021-11-02 10:03:07
     */
    @PreventRepeat
    @Methods(methodsName = "助理关联修改", methods = "update")
    @ApiOperation(value = "助理关联修改", httpMethod = "PUT", notes = "助理关联修改", response = Json.class)
    @ApiOperationSupport(ignoreParameters = {"createDateTime", "createName", "modifyDateTime", "modifyName", "isDelete", "version"})
    @PutMapping("/update")
    public Json<Boolean> update(@Validated(value = AssistantAssociatedBO.Update.class) AssistantAssociatedBO assistantAssociatedBO, HttpServletRequest httpServletRequest) {
        //缓存取出用户
        Map<String, Object> u = RedisUtils.getToken(stringRedisTemplate, httpServletRequest);
        if (u != null) {
            assistantAssociatedBO.setModifyName(u.get("name") + "-" + u.get("id"));
        }
        //返回内容
        return new Json(ReturnCode.成功, assistantAssociatedService.updateEnhance(assistantAssociatedBO));
    }


    /**
     * 助理关联删除
     *
     * @param assistantAssociatedBO:
     * @return com.utils.Json
     * @author lijh
     * @since 2021-11-02 10:03:07
     */
    @Methods(methodsName = "助理关联删除", methods = "remove")
    @ApiOperation(value = "助理关联删除", httpMethod = "DELETE", notes = "助理关联删除", response = Json.class)
    @ApiOperationSupport(includeParameters = {"id"})
    @DeleteMapping("/remove")
    public Json<Boolean> remove(@Validated(value = AssistantAssociatedBO.Remove.class) AssistantAssociatedBO assistantAssociatedBO) {
        return new Json(ReturnCode.成功, assistantAssociatedService.removeEnhance(assistantAssociatedBO));
    }

    /**
     * 获取管家集合
     *
     * @return com.utils.Json
     * @author lijh
     * @since 2021-11-02 10:03:07
     */
    @Methods(methodsName = "获取管家集合", methods = "getStewardList")
    @ApiOperation(value = "获取管家集合", httpMethod = "GET", notes = "获取管家集合", response = Json.class)
    @GetMapping(value = "/getStewardList")
    public Json<Integer> getStewardList(String groupId) {
        //返回内容
        return new Json(ReturnCode.成功, potentialCustomerService.getUserGroupList(groupId));
    }


}