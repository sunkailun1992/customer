package com.gb.promoteform.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gb.promoteform.entity.bo.PromoteFormLabelBO;
import com.gb.promoteform.entity.query.PromoteFormLabelQuery;
import com.gb.promoteform.entity.vo.PromoteFormLabelVO;
import com.gb.promoteform.service.PromoteFormLabelService;
import com.gb.utils.Json;
import com.gb.utils.RedisUtils;
import com.gb.utils.annotations.Methods;
import com.gb.utils.annotations.PreventRepeat;
import com.gb.utils.annotations.RequestRequired;
import com.gb.utils.enumeration.ReturnCode;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


/**
 * TODO 推广表单标签，Comment请求层
 * 代码生成器
 *
 * @author lijh
 * @className PromoteFormLabelController
 * @time 2022-07-04 10:49:04
 */
@Slf4j
@RequestRequired
@ApiSupport(author = "lijh")
@Setter(onMethod_ = {@Autowired})
@RestController
@RequestMapping("/promote-form-label")
@Api(tags = "推广表单标签")
public class PromoteFormLabelController {


    /**
     * 推广表单标签
     */
    private PromoteFormLabelService promoteFormLabelService;
    /**
     * redis操作
     */
    private StringRedisTemplate stringRedisTemplate;


    /**
     * TODO 分页
     *
     * @param promoteFormLabelQuery 推广表单标签
     * @param current
     * @param size
     * @return Json<Page < PromoteFormLabelVO>>
     * @author lijh
     * @methodName select
     * @time 2022-07-04 10:49:04
     */
    @Methods(methodsName = "推广表单标签集合分页查询", methods = "select")
    @ApiOperation(value = "推广表单标签集合分页查询", httpMethod = "GET", notes = "推广表单标签集合查询", response = Json.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", dataType = "int", required = true),
            @ApiImplicitParam(name = "size", value = "分页显示数量", dataType = "int", required = true),
    })
    @GetMapping("/select")
    public Json<Page<PromoteFormLabelVO>> select(@Validated(value = PromoteFormLabelQuery.Select.class) PromoteFormLabelQuery promoteFormLabelQuery, Integer current, Integer size) {
        //返回内容
        return new Json(ReturnCode.成功, promoteFormLabelService.pageEnhance(new Page(current, size), promoteFormLabelQuery));
    }


    /**
     * TODO 集合
     *
     * @param promoteFormLabelQuery 推广表单标签
     * @return Json<List < PromoteFormLabelVO>>
     * @author lijh
     * @methodName selectList
     * @time 2022-07-04 10:49:04
     */
    @Methods(methodsName = "推广表单标签集合查询", methods = "selectList")
    @ApiOperation(value = "推广表单标签集合查询", httpMethod = "GET", notes = "推广表单标签集合查询", response = Json.class)
    @GetMapping("/selectList")
    public Json<List<PromoteFormLabelVO>> selectList(@Validated(value = PromoteFormLabelQuery.SelectList.class) PromoteFormLabelQuery promoteFormLabelQuery) {
        //返回内容
        return new Json(ReturnCode.成功, promoteFormLabelService.listEnhance(promoteFormLabelQuery));
    }


    /**
     * TODO 单条
     *
     * @param promoteFormLabelQuery 推广表单标签
     * @return Json<PromoteFormLabelVO>
     * @author lijh
     * @methodName selectOne
     * @time 2022-07-04 10:49:04
     */
    @Methods(methodsName = "推广表单标签单条查询", methods = "selectOne")
    @ApiOperation(value = "推广表单标签单条查询", httpMethod = "GET", notes = "推广表单标签单条查询", response = Json.class)
    @GetMapping("/selectOne")
    public Json<PromoteFormLabelVO> selectOne(@Validated(value = PromoteFormLabelQuery.SelectOne.class) PromoteFormLabelQuery promoteFormLabelQuery) {
        //返回内容
        return new Json(ReturnCode.成功, promoteFormLabelService.getOneEnhance(promoteFormLabelQuery));
    }


    /**
     * TODO 总数
     *
     * @param promoteFormLabelQuery 推广表单标签
     * @return Json<Integer>
     * @author lijh
     * @methodName count
     * @time 2022-07-04 10:49:04
     */
    @Methods(methodsName = "推广表单标签总数查询", methods = "count")
    @ApiOperation(value = "推广表单标签总数查询", httpMethod = "GET", notes = "推广表单标签总数查询", response = Json.class)
    @GetMapping(value = "/count")
    public Json<Integer> count(@Validated(value = PromoteFormLabelQuery.Count.class) PromoteFormLabelQuery promoteFormLabelQuery) {
        //返回内容
        return new Json(ReturnCode.成功, promoteFormLabelService.countEnhance(promoteFormLabelQuery));
    }


    /**
     * TODO 新增
     *
     * @param promoteFormLabelBO 推广表单标签
     * @return Json<String>
     * @author lijh
     * @methodName save
     * @time 2022-07-04 10:49:04
     */
    @PreventRepeat
    @Methods(methodsName = "推广表单标签新增", methods = "save")
    @ApiOperation(value = "推广表单标签新增", httpMethod = "POST", notes = "推广表单标签新增", response = Json.class)
    @ApiOperationSupport(ignoreParameters = {"id", "createDateTime", "createName", "modifyDateTime", "modifyName", "isDelete", "version"})
    @PostMapping("/save")
    public Json<String> save(@Validated(value = PromoteFormLabelBO.Save.class) PromoteFormLabelBO promoteFormLabelBO, HttpServletRequest httpServletRequest) {
        //缓存取出用户
        Map<String, Object> u = RedisUtils.getToken(stringRedisTemplate, httpServletRequest);
        if (u != null) {
            promoteFormLabelBO.setCreateName(u.get("name") + "-" + u.get("id"));
        }
        //返回内容
        return new Json(ReturnCode.成功, promoteFormLabelService.saveEnhance(promoteFormLabelBO));
    }


    /**
     * TODO 修改
     *
     * @param promoteFormLabelBO 推广表单标签
     * @return Json<Boolean>
     * @author lijh
     * @methodName update
     * @time 2022-07-04 10:49:04
     */
    @PreventRepeat
    @Methods(methodsName = "推广表单标签修改", methods = "update")
    @ApiOperation(value = "推广表单标签修改", httpMethod = "PUT", notes = "推广表单标签修改", response = Json.class)
    @ApiOperationSupport(ignoreParameters = {"createDateTime", "createName", "modifyDateTime", "modifyName", "isDelete", "version"})
    @PutMapping("/update")
    public Json<Boolean> update(@Validated(value = PromoteFormLabelBO.Update.class) PromoteFormLabelBO promoteFormLabelBO, HttpServletRequest httpServletRequest) {
        //缓存取出用户
        Map<String, Object> u = RedisUtils.getToken(stringRedisTemplate, httpServletRequest);
        if (u != null) {
            promoteFormLabelBO.setModifyName(u.get("name") + "-" + u.get("id"));
        }
        //返回内容
        return new Json(ReturnCode.成功, promoteFormLabelService.updateEnhance(promoteFormLabelBO));
    }


    /**
     * TODO 删除
     *
     * @param promoteFormLabelBO 推广表单标签
     * @return Json<Boolean>
     * @author lijh
     * @methodName remove
     * @time 2022-07-04 10:49:04
     */
    @Methods(methodsName = "推广表单标签删除", methods = "remove")
    @ApiOperation(value = "推广表单标签删除", httpMethod = "DELETE", notes = "推广表单标签删除", response = Json.class)
    @ApiOperationSupport(includeParameters = {"id"})
    @DeleteMapping("/remove")
    public Json<Boolean> remove(@Validated(value = PromoteFormLabelBO.Remove.class) PromoteFormLabelBO promoteFormLabelBO) {
        return new Json(ReturnCode.成功, promoteFormLabelService.removeEnhance(promoteFormLabelBO));
    }

    @PreventRepeat
    @Methods(methodsName = "获取所有标签", methods = "get")
    @ApiOperation(value = "获取所有标签", httpMethod = "GET", notes = "获取所有标签", response = Json.class)
    @GetMapping("/getLabelList")
    public Json<Boolean> getLabelList(String labelName) {
        //返回内容
        return new Json(ReturnCode.成功, promoteFormLabelService.getLabelList(labelName));
    }
}