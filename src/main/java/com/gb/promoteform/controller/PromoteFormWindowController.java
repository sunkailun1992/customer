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
import com.gb.promoteform.service.PromoteFormWindowService;
import com.gb.promoteform.entity.query.PromoteFormWindowQuery;
import com.gb.promoteform.entity.vo.PromoteFormWindowVO;
import com.gb.promoteform.entity.bo.PromoteFormWindowBO;


/**
 * TODO 推广表单窗口，Comment请求层
 * 代码生成器
 *
 * @author lijh
 * @className PromoteFormWindowController
 * @time 2022-07-04 10:49:05
 */
@Slf4j
@RequestRequired
@ApiSupport(author = "lijh")
@Setter(onMethod_ = {@Autowired})
@RestController
@RequestMapping("/promote-form-window")
@Api(tags = "推广表单窗口")
public class PromoteFormWindowController {


    /**
     * 推广表单窗口
     */
    private PromoteFormWindowService promoteFormWindowService;
    /**
     * redis操作
     */
    private StringRedisTemplate stringRedisTemplate;


    /**
     * TODO 分页
     *
     * @param promoteFormWindowQuery 推广表单窗口
     * @param current
     * @param size
     * @return Json<Page<PromoteFormWindowVO>>
     * @author lijh
     * @methodName select
     * @time 2022-07-04 10:49:05
     */
    @Methods(methodsName = "推广表单窗口集合分页查询", methods = "select")
    @ApiOperation(value = "推广表单窗口集合分页查询", httpMethod = "GET", notes = "推广表单窗口集合查询", response = Json.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", dataType = "int", required = true),
            @ApiImplicitParam(name = "size", value = "分页显示数量", dataType = "int", required = true),
    })
    @GetMapping("/select")
    public Json<Page<PromoteFormWindowVO>> select(@Validated(value = PromoteFormWindowQuery.Select.class) PromoteFormWindowQuery promoteFormWindowQuery, Integer current, Integer size) {
        //返回内容
        return new Json(ReturnCode.成功, promoteFormWindowService.pageEnhance(new Page(current, size), promoteFormWindowQuery));
    }


    /**
     * TODO 集合
     *
     * @param promoteFormWindowQuery 推广表单窗口
     * @return Json<List<PromoteFormWindowVO>>
     * @author lijh
     * @methodName selectList
     * @time 2022-07-04 10:49:05
     */
    @Methods(methodsName = "推广表单窗口集合查询", methods = "selectList")
    @ApiOperation(value = "推广表单窗口集合查询", httpMethod = "GET", notes = "推广表单窗口集合查询", response = Json.class)
    @GetMapping("/selectList")
    public Json<List<PromoteFormWindowVO>> selectList(@Validated(value = PromoteFormWindowQuery.SelectList.class) PromoteFormWindowQuery promoteFormWindowQuery) {
        //返回内容
        return new Json(ReturnCode.成功, promoteFormWindowService.listEnhance(promoteFormWindowQuery));
    }


    /**
     * TODO 单条
     *
     * @param promoteFormWindowQuery 推广表单窗口
     * @return Json<PromoteFormWindowVO>
     * @author lijh
     * @methodName selectOne
     * @time 2022-07-04 10:49:05
     */
    @Methods(methodsName = "推广表单窗口单条查询", methods = "selectOne")
    @ApiOperation(value = "推广表单窗口单条查询", httpMethod = "GET", notes = "推广表单窗口单条查询", response = Json.class)
    @GetMapping("/selectOne")
    public Json<PromoteFormWindowVO> selectOne(@Validated(value = PromoteFormWindowQuery.SelectOne.class) PromoteFormWindowQuery promoteFormWindowQuery) {
        //返回内容
        return new Json(ReturnCode.成功, promoteFormWindowService.getOneEnhance(promoteFormWindowQuery));
    }


    /**
     * TODO 总数
     *
     * @param promoteFormWindowQuery 推广表单窗口
     * @return Json<Integer>
     * @author lijh
     * @methodName count
     * @time 2022-07-04 10:49:05
     */
    @Methods(methodsName = "推广表单窗口总数查询", methods = "count")
    @ApiOperation(value = "推广表单窗口总数查询", httpMethod = "GET", notes = "推广表单窗口总数查询", response = Json.class)
    @GetMapping(value = "/count")
    public Json<Integer> count(@Validated(value = PromoteFormWindowQuery.Count.class) PromoteFormWindowQuery promoteFormWindowQuery) {
        //返回内容
        return new Json(ReturnCode.成功, promoteFormWindowService.countEnhance(promoteFormWindowQuery));
    }


    /**
     * TODO 新增
     *
     * @param promoteFormWindowBO 推广表单窗口
     * @return Json<String>
     * @author lijh
     * @methodName save
     * @time 2022-07-04 10:49:05
     */
    @PreventRepeat
    @Methods(methodsName = "推广表单窗口新增", methods = "save")
    @ApiOperation(value = "推广表单窗口新增", httpMethod = "POST", notes = "推广表单窗口新增", response = Json.class)
    @ApiOperationSupport(ignoreParameters = {"id", "createDateTime", "createName", "modifyDateTime", "modifyName", "isDelete", "version"})
    @PostMapping("/save")
    public Json<String> save(@Validated(value = PromoteFormWindowBO.Save.class) PromoteFormWindowBO promoteFormWindowBO, HttpServletRequest httpServletRequest) {
        //缓存取出用户
        Map<String, Object> u = RedisUtils.getToken(stringRedisTemplate, httpServletRequest);
        if (u != null) {
            promoteFormWindowBO.setCreateName(u.get("name") + "-" + u.get("id"));
        }
        //返回内容
        return new Json(ReturnCode.成功, promoteFormWindowService.saveEnhance(promoteFormWindowBO));
    }


    /**
     * TODO 修改
     *
     * @param promoteFormWindowBO 推广表单窗口
     * @return Json<Boolean>
     * @author lijh
     * @methodName update
     * @time 2022-07-04 10:49:05
     */
    @PreventRepeat
    @Methods(methodsName = "推广表单窗口修改", methods = "update")
    @ApiOperation(value = "推广表单窗口修改", httpMethod = "PUT", notes = "推广表单窗口修改", response = Json.class)
    @ApiOperationSupport(ignoreParameters = {"createDateTime", "createName", "modifyDateTime", "modifyName", "isDelete", "version"})
    @PutMapping("/update")
    public Json<Boolean> update(@Validated(value = PromoteFormWindowBO.Update.class) PromoteFormWindowBO promoteFormWindowBO, HttpServletRequest httpServletRequest) {
        //缓存取出用户
        Map<String, Object> u = RedisUtils.getToken(stringRedisTemplate, httpServletRequest);
        if (u != null) {
            promoteFormWindowBO.setModifyName(u.get("name") + "-" + u.get("id"));
        }
        //返回内容
        return new Json(ReturnCode.成功, promoteFormWindowService.updateEnhance(promoteFormWindowBO));
    }


    /**
     * TODO 删除
     *
     * @param promoteFormWindowBO 推广表单窗口
     * @return Json<Boolean>
     * @author lijh
     * @methodName remove
     * @time 2022-07-04 10:49:05
     */
    @Methods(methodsName = "推广表单窗口删除", methods = "remove")
    @ApiOperation(value = "推广表单窗口删除", httpMethod = "DELETE", notes = "推广表单窗口删除", response = Json.class)
    @ApiOperationSupport(includeParameters = {"id"})
    @DeleteMapping("/remove")
    public Json<Boolean> remove(@Validated(value = PromoteFormWindowBO.Remove.class) PromoteFormWindowBO promoteFormWindowBO) {
        return new Json(ReturnCode.成功, promoteFormWindowService.removeEnhance(promoteFormWindowBO));
    }
}