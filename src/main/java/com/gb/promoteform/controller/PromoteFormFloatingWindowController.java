package com.gb.promoteform.controller;

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
import com.gb.promoteform.service.PromoteFormFloatingWindowService;
import com.gb.promoteform.entity.query.PromoteFormFloatingWindowQuery;
import com.gb.promoteform.entity.vo.PromoteFormFloatingWindowVO;
import com.gb.promoteform.entity.bo.PromoteFormFloatingWindowBO;


/**
 * TODO 推广表单浮框，Comment请求层
 * 代码生成器
 *
 * @author lijh
 * @className PromoteFormFloatingWindowController
 * @time 2022-07-04 10:49:04
 */
@Slf4j
@RequestRequired
@ApiSupport(author = "lijh")
@Setter(onMethod_ = {@Autowired})
@RestController
@RequestMapping("/promote-form-floating-window")
@Api(tags = "推广表单浮框")
public class PromoteFormFloatingWindowController {


    /**
     * 推广表单浮框
     */
    private PromoteFormFloatingWindowService promoteFormFloatingWindowService;
    /**
     * redis操作
     */
    private StringRedisTemplate stringRedisTemplate;


    /**
     * TODO 分页
     *
     * @param promoteFormFloatingWindowQuery 推广表单浮框
     * @param current
     * @param size
     * @return Json<Page<PromoteFormFloatingWindowVO>>
     * @author lijh
     * @methodName select
     * @time 2022-07-04 10:49:04
     */
    @Methods(methodsName = "推广表单浮框集合分页查询", methods = "select")
    @ApiOperation(value = "推广表单浮框集合分页查询", httpMethod = "GET", notes = "推广表单浮框集合查询", response = Json.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", dataType = "int", required = true),
            @ApiImplicitParam(name = "size", value = "分页显示数量", dataType = "int", required = true),
    })
    @GetMapping("/select")
    public Json<Page<PromoteFormFloatingWindowVO>> select(@Validated(value = PromoteFormFloatingWindowQuery.Select.class) PromoteFormFloatingWindowQuery promoteFormFloatingWindowQuery, Integer current, Integer size) {
        //返回内容
        return new Json(ReturnCode.成功, promoteFormFloatingWindowService.pageEnhance(new Page(current, size), promoteFormFloatingWindowQuery));
    }


    /**
     * TODO 集合
     *
     * @param promoteFormFloatingWindowQuery 推广表单浮框
     * @return Json<List<PromoteFormFloatingWindowVO>>
     * @author lijh
     * @methodName selectList
     * @time 2022-07-04 10:49:04
     */
    @Methods(methodsName = "推广表单浮框集合查询", methods = "selectList")
    @ApiOperation(value = "推广表单浮框集合查询", httpMethod = "GET", notes = "推广表单浮框集合查询", response = Json.class)
    @GetMapping("/selectList")
    public Json<List<PromoteFormFloatingWindowVO>> selectList(@Validated(value = PromoteFormFloatingWindowQuery.SelectList.class) PromoteFormFloatingWindowQuery promoteFormFloatingWindowQuery) {
        //返回内容
        return new Json(ReturnCode.成功, promoteFormFloatingWindowService.listEnhance(promoteFormFloatingWindowQuery));
    }


    /**
     * TODO 单条
     *
     * @param promoteFormFloatingWindowQuery 推广表单浮框
     * @return Json<PromoteFormFloatingWindowVO>
     * @author lijh
     * @methodName selectOne
     * @time 2022-07-04 10:49:04
     */
    @Methods(methodsName = "推广表单浮框单条查询", methods = "selectOne")
    @ApiOperation(value = "推广表单浮框单条查询", httpMethod = "GET", notes = "推广表单浮框单条查询", response = Json.class)
    @GetMapping("/selectOne")
    public Json<PromoteFormFloatingWindowVO> selectOne(@Validated(value = PromoteFormFloatingWindowQuery.SelectOne.class) PromoteFormFloatingWindowQuery promoteFormFloatingWindowQuery) {
        //返回内容
        return new Json(ReturnCode.成功, promoteFormFloatingWindowService.getOneEnhance(promoteFormFloatingWindowQuery));
    }


    /**
     * TODO 总数
     *
     * @param promoteFormFloatingWindowQuery 推广表单浮框
     * @return Json<Integer>
     * @author lijh
     * @methodName count
     * @time 2022-07-04 10:49:04
     */
    @Methods(methodsName = "推广表单浮框总数查询", methods = "count")
    @ApiOperation(value = "推广表单浮框总数查询", httpMethod = "GET", notes = "推广表单浮框总数查询", response = Json.class)
    @GetMapping(value = "/count")
    public Json<Integer> count(@Validated(value = PromoteFormFloatingWindowQuery.Count.class) PromoteFormFloatingWindowQuery promoteFormFloatingWindowQuery) {
        //返回内容
        return new Json(ReturnCode.成功, promoteFormFloatingWindowService.countEnhance(promoteFormFloatingWindowQuery));
    }


    /**
     * TODO 新增
     *
     * @param promoteFormFloatingWindowBO 推广表单浮框
     * @return Json<String>
     * @author lijh
     * @methodName save
     * @time 2022-07-04 10:49:04
     */
    @PreventRepeat
    @Methods(methodsName = "推广表单浮框新增", methods = "save")
    @ApiOperation(value = "推广表单浮框新增", httpMethod = "POST", notes = "推广表单浮框新增", response = Json.class)
    @ApiOperationSupport(ignoreParameters = {"id", "createDateTime", "createName", "modifyDateTime", "modifyName", "isDelete", "version"})
    @PostMapping("/save")
    public Json<String> save(@Validated(value = PromoteFormFloatingWindowBO.Save.class) PromoteFormFloatingWindowBO promoteFormFloatingWindowBO, HttpServletRequest httpServletRequest) {
        //缓存取出用户
        Map<String, Object> u = RedisUtils.getToken(stringRedisTemplate, httpServletRequest);
        if (u != null) {
            promoteFormFloatingWindowBO.setCreateName(u.get("name") + "-" + u.get("id"));
        }
        //返回内容
        return new Json(ReturnCode.成功, promoteFormFloatingWindowService.saveEnhance(promoteFormFloatingWindowBO));
    }


    /**
     * TODO 修改
     *
     * @param promoteFormFloatingWindowBO 推广表单浮框
     * @return Json<Boolean>
     * @author lijh
     * @methodName update
     * @time 2022-07-04 10:49:04
     */
    @PreventRepeat
    @Methods(methodsName = "推广表单浮框修改", methods = "update")
    @ApiOperation(value = "推广表单浮框修改", httpMethod = "PUT", notes = "推广表单浮框修改", response = Json.class)
    @ApiOperationSupport(ignoreParameters = {"createDateTime", "createName", "modifyDateTime", "modifyName", "isDelete", "version"})
    @PutMapping("/update")
    public Json<Boolean> update(@Validated(value = PromoteFormFloatingWindowBO.Update.class) PromoteFormFloatingWindowBO promoteFormFloatingWindowBO, HttpServletRequest httpServletRequest) {
        //缓存取出用户
        Map<String, Object> u = RedisUtils.getToken(stringRedisTemplate, httpServletRequest);
        if (u != null) {
            promoteFormFloatingWindowBO.setModifyName(u.get("name") + "-" + u.get("id"));
        }
        //返回内容
        return new Json(ReturnCode.成功, promoteFormFloatingWindowService.updateEnhance(promoteFormFloatingWindowBO));
    }


    /**
     * TODO 删除
     *
     * @param promoteFormFloatingWindowBO 推广表单浮框
     * @return Json<Boolean>
     * @author lijh
     * @methodName remove
     * @time 2022-07-04 10:49:04
     */
    @Methods(methodsName = "推广表单浮框删除", methods = "remove")
    @ApiOperation(value = "推广表单浮框删除", httpMethod = "DELETE", notes = "推广表单浮框删除", response = Json.class)
    @ApiOperationSupport(includeParameters = {"id"})
    @DeleteMapping("/remove")
    public Json<Boolean> remove(@Validated(value = PromoteFormFloatingWindowBO.Remove.class) PromoteFormFloatingWindowBO promoteFormFloatingWindowBO) {
        return new Json(ReturnCode.成功, promoteFormFloatingWindowService.removeEnhance(promoteFormFloatingWindowBO));
    }
}