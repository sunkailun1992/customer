package com.gb.promoteform.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gb.promoteform.entity.bo.PromoteFormButtonBO;
import com.gb.promoteform.entity.query.PromoteFormButtonQuery;
import com.gb.promoteform.entity.vo.PromoteFormButtonVO;
import com.gb.promoteform.service.PromoteFormButtonService;
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
 * TODO 推广表单按钮，Comment请求层
 * 代码生成器
 *
 * @author lijh
 * @className PromoteFormButtonController
 * @time 2022-07-04 10:49:04
 */
@Slf4j
@RequestRequired
@ApiSupport(author = "lijh")
@Setter(onMethod_ = {@Autowired})
@RestController
@RequestMapping("/promote-form-button")
@Api(tags = "推广表单按钮")
public class PromoteFormButtonController {


    /**
     * 推广表单按钮
     */
    private PromoteFormButtonService promoteFormButtonService;
    /**
     * redis操作
     */
    private StringRedisTemplate stringRedisTemplate;


    /**
     * TODO 分页
     *
     * @param promoteFormButtonQuery 推广表单按钮
     * @param current
     * @param size
     * @return Json<Page<PromoteFormButtonVO>>
     * @author lijh
     * @methodName select
     * @time 2022-07-04 10:49:04
     */
    @Methods(methodsName = "推广表单按钮集合分页查询", methods = "select")
    @ApiOperation(value = "推广表单按钮集合分页查询", httpMethod = "GET", notes = "推广表单按钮集合查询", response = Json.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", dataType = "int", required = true),
            @ApiImplicitParam(name = "size", value = "分页显示数量", dataType = "int", required = true),
    })
    @GetMapping("/select")
    public Json<Page<PromoteFormButtonVO>> select(@Validated(value = PromoteFormButtonQuery.Select.class) PromoteFormButtonQuery promoteFormButtonQuery, Integer current, Integer size) {
        //返回内容
        return new Json(ReturnCode.成功, promoteFormButtonService.pageEnhance(new Page(current, size), promoteFormButtonQuery));
    }


    /**
     * TODO 集合
     *
     * @param promoteFormButtonQuery 推广表单按钮
     * @return Json<List<PromoteFormButtonVO>>
     * @author lijh
     * @methodName selectList
     * @time 2022-07-04 10:49:04
     */
    @Methods(methodsName = "推广表单按钮集合查询", methods = "selectList")
    @ApiOperation(value = "推广表单按钮集合查询", httpMethod = "GET", notes = "推广表单按钮集合查询", response = Json.class)
    @GetMapping("/selectList")
    public Json<List<PromoteFormButtonVO>> selectList(@Validated(value = PromoteFormButtonQuery.SelectList.class) PromoteFormButtonQuery promoteFormButtonQuery) {
        //返回内容
        return new Json(ReturnCode.成功, promoteFormButtonService.listEnhance(promoteFormButtonQuery));
    }


    /**
     * TODO 单条
     *
     * @param promoteFormButtonQuery 推广表单按钮
     * @return Json<PromoteFormButtonVO>
     * @author lijh
     * @methodName selectOne
     * @time 2022-07-04 10:49:04
     */
    @Methods(methodsName = "推广表单按钮单条查询", methods = "selectOne")
    @ApiOperation(value = "推广表单按钮单条查询", httpMethod = "GET", notes = "推广表单按钮单条查询", response = Json.class)
    @GetMapping("/selectOne")
    public Json<PromoteFormButtonVO> selectOne(@Validated(value = PromoteFormButtonQuery.SelectOne.class) PromoteFormButtonQuery promoteFormButtonQuery) {
        //返回内容
        return new Json(ReturnCode.成功, promoteFormButtonService.getOneEnhance(promoteFormButtonQuery));
    }


    /**
     * TODO 总数
     *
     * @param promoteFormButtonQuery 推广表单按钮
     * @return Json<Integer>
     * @author lijh
     * @methodName count
     * @time 2022-07-04 10:49:04
     */
    @Methods(methodsName = "推广表单按钮总数查询", methods = "count")
    @ApiOperation(value = "推广表单按钮总数查询", httpMethod = "GET", notes = "推广表单按钮总数查询", response = Json.class)
    @GetMapping(value = "/count")
    public Json<Integer> count(@Validated(value = PromoteFormButtonQuery.Count.class) PromoteFormButtonQuery promoteFormButtonQuery) {
        //返回内容
        return new Json(ReturnCode.成功, promoteFormButtonService.countEnhance(promoteFormButtonQuery));
    }


    /**
     * TODO 新增
     *
     * @param promoteFormButtonBO 推广表单按钮
     * @return Json<String>
     * @author lijh
     * @methodName save
     * @time 2022-07-04 10:49:04
     */
    @PreventRepeat
    @Methods(methodsName = "推广表单按钮新增", methods = "save")
    @ApiOperation(value = "推广表单按钮新增", httpMethod = "POST", notes = "推广表单按钮新增", response = Json.class)
    @ApiOperationSupport(ignoreParameters = {"id", "createDateTime", "createName", "modifyDateTime", "modifyName", "isDelete", "version"})
    @PostMapping("/save")
    public Json<String> save(@Validated(value = PromoteFormButtonBO.Save.class) PromoteFormButtonBO promoteFormButtonBO, HttpServletRequest httpServletRequest) {
        //缓存取出用户
        Map<String, Object> u = RedisUtils.getToken(stringRedisTemplate, httpServletRequest);
        if (u != null) {
            promoteFormButtonBO.setCreateName(u.get("name") + "-" + u.get("id"));
        }
        //返回内容
        return new Json(ReturnCode.成功, promoteFormButtonService.saveEnhance(promoteFormButtonBO));
    }


    /**
     * TODO 修改
     *
     * @param promoteFormButtonBO 推广表单按钮
     * @return Json<Boolean>
     * @author lijh
     * @methodName update
     * @time 2022-07-04 10:49:04
     */
    @PreventRepeat
    @Methods(methodsName = "推广表单按钮修改", methods = "update")
    @ApiOperation(value = "推广表单按钮修改", httpMethod = "PUT", notes = "推广表单按钮修改", response = Json.class)
    @ApiOperationSupport(ignoreParameters = {"createDateTime", "createName", "modifyDateTime", "modifyName", "isDelete", "version"})
    @PutMapping("/update")
    public Json<Boolean> update(@Validated(value = PromoteFormButtonBO.Update.class) PromoteFormButtonBO promoteFormButtonBO, HttpServletRequest httpServletRequest) {
        //缓存取出用户
        Map<String, Object> u = RedisUtils.getToken(stringRedisTemplate, httpServletRequest);
        if (u != null) {
            promoteFormButtonBO.setModifyName(u.get("name") + "-" + u.get("id"));
        }
        //返回内容
        return new Json(ReturnCode.成功, promoteFormButtonService.updateEnhance(promoteFormButtonBO));
    }


    /**
     * TODO 删除
     *
     * @param promoteFormButtonBO 推广表单按钮
     * @return Json<Boolean>
     * @author lijh
     * @methodName remove
     * @time 2022-07-04 10:49:04
     */
    @Methods(methodsName = "推广表单按钮删除", methods = "remove")
    @ApiOperation(value = "推广表单按钮删除", httpMethod = "DELETE", notes = "推广表单按钮删除", response = Json.class)
    @ApiOperationSupport(includeParameters = {"id"})
    @DeleteMapping("/remove")
    public Json<Boolean> remove(@Validated(value = PromoteFormButtonBO.Remove.class) PromoteFormButtonBO promoteFormButtonBO) {
        return new Json(ReturnCode.成功, promoteFormButtonService.removeEnhance(promoteFormButtonBO));
    }
}