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
import com.gb.promoteform.service.PromoteFormFieldService;
import com.gb.promoteform.entity.query.PromoteFormFieldQuery;
import com.gb.promoteform.entity.vo.PromoteFormFieldVO;
import com.gb.promoteform.entity.bo.PromoteFormFieldBO;


/**
 * TODO 推广表单字段，Comment请求层
 * 代码生成器
 *
 * @author lijh
 * @className PromoteFormFieldController
 * @time 2022-07-04 10:49:04
 */
@Slf4j
@RequestRequired
@ApiSupport(author = "lijh")
@Setter(onMethod_ = {@Autowired})
@RestController
@RequestMapping("/promote-form-field")
@Api(tags = "推广表单字段")
public class PromoteFormFieldController {


    /**
     * 推广表单字段
     */
    private PromoteFormFieldService promoteFormFieldService;
    /**
     * redis操作
     */
    private StringRedisTemplate stringRedisTemplate;


    /**
     * TODO 分页
     *
     * @param promoteFormFieldQuery 推广表单字段
     * @param current
     * @param size
     * @return Json<Page<PromoteFormFieldVO>>
     * @author lijh
     * @methodName select
     * @time 2022-07-04 10:49:04
     */
    @Methods(methodsName = "推广表单字段集合分页查询", methods = "select")
    @ApiOperation(value = "推广表单字段集合分页查询", httpMethod = "GET", notes = "推广表单字段集合查询", response = Json.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", dataType = "int", required = true),
            @ApiImplicitParam(name = "size", value = "分页显示数量", dataType = "int", required = true),
    })
    @GetMapping("/select")
    public Json<Page<PromoteFormFieldVO>> select(@Validated(value = PromoteFormFieldQuery.Select.class) PromoteFormFieldQuery promoteFormFieldQuery, Integer current, Integer size) {
        //返回内容
        return new Json(ReturnCode.成功, promoteFormFieldService.pageEnhance(new Page(current, size), promoteFormFieldQuery));
    }


    /**
     * TODO 集合
     *
     * @param promoteFormFieldQuery 推广表单字段
     * @return Json<List<PromoteFormFieldVO>>
     * @author lijh
     * @methodName selectList
     * @time 2022-07-04 10:49:04
     */
    @Methods(methodsName = "推广表单字段集合查询", methods = "selectList")
    @ApiOperation(value = "推广表单字段集合查询", httpMethod = "GET", notes = "推广表单字段集合查询", response = Json.class)
    @GetMapping("/selectList")
    public Json<List<PromoteFormFieldVO>> selectList(@Validated(value = PromoteFormFieldQuery.SelectList.class) PromoteFormFieldQuery promoteFormFieldQuery) {
        //返回内容
        return new Json(ReturnCode.成功, promoteFormFieldService.listEnhance(promoteFormFieldQuery));
    }


    /**
     * TODO 单条
     *
     * @param promoteFormFieldQuery 推广表单字段
     * @return Json<PromoteFormFieldVO>
     * @author lijh
     * @methodName selectOne
     * @time 2022-07-04 10:49:04
     */
    @Methods(methodsName = "推广表单字段单条查询", methods = "selectOne")
    @ApiOperation(value = "推广表单字段单条查询", httpMethod = "GET", notes = "推广表单字段单条查询", response = Json.class)
    @GetMapping("/selectOne")
    public Json<PromoteFormFieldVO> selectOne(@Validated(value = PromoteFormFieldQuery.SelectOne.class) PromoteFormFieldQuery promoteFormFieldQuery) {
        //返回内容
        return new Json(ReturnCode.成功, promoteFormFieldService.getOneEnhance(promoteFormFieldQuery));
    }


    /**
     * TODO 总数
     *
     * @param promoteFormFieldQuery 推广表单字段
     * @return Json<Integer>
     * @author lijh
     * @methodName count
     * @time 2022-07-04 10:49:04
     */
    @Methods(methodsName = "推广表单字段总数查询", methods = "count")
    @ApiOperation(value = "推广表单字段总数查询", httpMethod = "GET", notes = "推广表单字段总数查询", response = Json.class)
    @GetMapping(value = "/count")
    public Json<Integer> count(@Validated(value = PromoteFormFieldQuery.Count.class) PromoteFormFieldQuery promoteFormFieldQuery) {
        //返回内容
        return new Json(ReturnCode.成功, promoteFormFieldService.countEnhance(promoteFormFieldQuery));
    }


    /**
     * TODO 新增
     *
     * @param promoteFormFieldBO 推广表单字段
     * @return Json<String>
     * @author lijh
     * @methodName save
     * @time 2022-07-04 10:49:04
     */
    @PreventRepeat
    @Methods(methodsName = "推广表单字段新增", methods = "save")
    @ApiOperation(value = "推广表单字段新增", httpMethod = "POST", notes = "推广表单字段新增", response = Json.class)
    @ApiOperationSupport(ignoreParameters = {"id", "createDateTime", "createName", "modifyDateTime", "modifyName", "isDelete", "version"})
    @PostMapping("/save")
    public Json<String> save(@Validated(value = PromoteFormFieldBO.Save.class) PromoteFormFieldBO promoteFormFieldBO, HttpServletRequest httpServletRequest) {
        //缓存取出用户
        Map<String, Object> u = RedisUtils.getToken(stringRedisTemplate, httpServletRequest);
        if (u != null) {
            promoteFormFieldBO.setCreateName(u.get("name") + "-" + u.get("id"));
        }
        //返回内容
        return new Json(ReturnCode.成功, promoteFormFieldService.saveEnhance(promoteFormFieldBO));
    }


    /**
     * TODO 修改
     *
     * @param promoteFormFieldBO 推广表单字段
     * @return Json<Boolean>
     * @author lijh
     * @methodName update
     * @time 2022-07-04 10:49:04
     */
    @PreventRepeat
    @Methods(methodsName = "推广表单字段修改", methods = "update")
    @ApiOperation(value = "推广表单字段修改", httpMethod = "PUT", notes = "推广表单字段修改", response = Json.class)
    @ApiOperationSupport(ignoreParameters = {"createDateTime", "createName", "modifyDateTime", "modifyName", "isDelete", "version"})
    @PutMapping("/update")
    public Json<Boolean> update(@Validated(value = PromoteFormFieldBO.Update.class) PromoteFormFieldBO promoteFormFieldBO, HttpServletRequest httpServletRequest) {
        //缓存取出用户
        Map<String, Object> u = RedisUtils.getToken(stringRedisTemplate, httpServletRequest);
        if (u != null) {
            promoteFormFieldBO.setModifyName(u.get("name") + "-" + u.get("id"));
        }
        //返回内容
        return new Json(ReturnCode.成功, promoteFormFieldService.updateEnhance(promoteFormFieldBO));
    }


    /**
     * TODO 删除
     *
     * @param promoteFormFieldBO 推广表单字段
     * @return Json<Boolean>
     * @author lijh
     * @methodName remove
     * @time 2022-07-04 10:49:04
     */
    @Methods(methodsName = "推广表单字段删除", methods = "remove")
    @ApiOperation(value = "推广表单字段删除", httpMethod = "DELETE", notes = "推广表单字段删除", response = Json.class)
    @ApiOperationSupport(includeParameters = {"id"})
    @DeleteMapping("/remove")
    public Json<Boolean> remove(@Validated(value = PromoteFormFieldBO.Remove.class) PromoteFormFieldBO promoteFormFieldBO) {
        return new Json(ReturnCode.成功, promoteFormFieldService.removeEnhance(promoteFormFieldBO));
    }
}