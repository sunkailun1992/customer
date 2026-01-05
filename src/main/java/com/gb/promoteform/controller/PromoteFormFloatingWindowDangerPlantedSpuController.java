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
import com.gb.promoteform.service.PromoteFormFloatingWindowDangerPlantedSpuService;
import com.gb.promoteform.entity.query.PromoteFormFloatingWindowDangerPlantedSpuQuery;
import com.gb.promoteform.entity.vo.PromoteFormFloatingWindowDangerPlantedSpuVO;
import com.gb.promoteform.entity.bo.PromoteFormFloatingWindowDangerPlantedSpuBO;


/**
 * TODO 推广表单浮框险种产品，Comment请求层
 * 代码生成器
 *
 * @author lijh
 * @className PromoteFormFloatingWindowDangerPlantedSpuController
 * @time 2022-10-28 03:09:31
 */
@Slf4j
@RequestRequired
@ApiSupport(author = "lijh")
@Setter(onMethod_ = {@Autowired})
@RestController
@RequestMapping("/promote-form-floating-window-danger-planted-spu")
@Api(tags = "推广表单浮框险种产品")
public class PromoteFormFloatingWindowDangerPlantedSpuController {


    /**
     * 推广表单浮框险种产品
     */
    private PromoteFormFloatingWindowDangerPlantedSpuService promoteFormFloatingWindowDangerPlantedSpuService;
    /**
     * redis操作
     */
    private StringRedisTemplate stringRedisTemplate;


    /**
     * TODO 分页
     *
     * @param promoteFormFloatingWindowDangerPlantedSpuQuery 推广表单浮框险种产品
     * @param current
     * @param size
     * @return Json<Page<PromoteFormFloatingWindowDangerPlantedSpuVO>>
     * @author lijh
     * @methodName select
     * @time 2022-10-28 03:09:31
     */
    @Methods(methodsName = "推广表单浮框险种产品集合分页查询", methods = "select")
    @ApiOperation(value = "推广表单浮框险种产品集合分页查询", httpMethod = "GET", notes = "推广表单浮框险种产品集合查询", response = Json.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", dataType = "int", required = true),
            @ApiImplicitParam(name = "size", value = "分页显示数量", dataType = "int", required = true),
    })
    @GetMapping("/select")
    public Json<Page<PromoteFormFloatingWindowDangerPlantedSpuVO>> select(@Validated(value = PromoteFormFloatingWindowDangerPlantedSpuQuery.Select.class) PromoteFormFloatingWindowDangerPlantedSpuQuery promoteFormFloatingWindowDangerPlantedSpuQuery, Integer current, Integer size) {
        //返回内容
        return new Json(ReturnCode.成功, promoteFormFloatingWindowDangerPlantedSpuService.pageEnhance(new Page(current, size), promoteFormFloatingWindowDangerPlantedSpuQuery));
    }


    /**
     * TODO 集合
     *
     * @param promoteFormFloatingWindowDangerPlantedSpuQuery 推广表单浮框险种产品
     * @return Json<List<PromoteFormFloatingWindowDangerPlantedSpuVO>>
     * @author lijh
     * @methodName selectList
     * @time 2022-10-28 03:09:31
     */
    @Methods(methodsName = "推广表单浮框险种产品集合查询", methods = "selectList")
    @ApiOperation(value = "推广表单浮框险种产品集合查询", httpMethod = "GET", notes = "推广表单浮框险种产品集合查询", response = Json.class)
    @GetMapping("/selectList")
    public Json<List<PromoteFormFloatingWindowDangerPlantedSpuVO>> selectList(@Validated(value = PromoteFormFloatingWindowDangerPlantedSpuQuery.SelectList.class) PromoteFormFloatingWindowDangerPlantedSpuQuery promoteFormFloatingWindowDangerPlantedSpuQuery) {
        //返回内容
        return new Json(ReturnCode.成功, promoteFormFloatingWindowDangerPlantedSpuService.listEnhance(promoteFormFloatingWindowDangerPlantedSpuQuery));
    }


    /**
     * TODO 单条
     *
     * @param promoteFormFloatingWindowDangerPlantedSpuQuery 推广表单浮框险种产品
     * @return Json<PromoteFormFloatingWindowDangerPlantedSpuVO>
     * @author lijh
     * @methodName selectOne
     * @time 2022-10-28 03:09:31
     */
    @Methods(methodsName = "推广表单浮框险种产品单条查询", methods = "selectOne")
    @ApiOperation(value = "推广表单浮框险种产品单条查询", httpMethod = "GET", notes = "推广表单浮框险种产品单条查询", response = Json.class)
    @GetMapping("/selectOne")
    public Json<PromoteFormFloatingWindowDangerPlantedSpuVO> selectOne(@Validated(value = PromoteFormFloatingWindowDangerPlantedSpuQuery.SelectOne.class) PromoteFormFloatingWindowDangerPlantedSpuQuery promoteFormFloatingWindowDangerPlantedSpuQuery) {
        //返回内容
        return new Json(ReturnCode.成功, promoteFormFloatingWindowDangerPlantedSpuService.getOneEnhance(promoteFormFloatingWindowDangerPlantedSpuQuery));
    }


    /**
     * TODO 总数
     *
     * @param promoteFormFloatingWindowDangerPlantedSpuQuery 推广表单浮框险种产品
     * @return Json<Integer>
     * @author lijh
     * @methodName count
     * @time 2022-10-28 03:09:31
     */
    @Methods(methodsName = "推广表单浮框险种产品总数查询", methods = "count")
    @ApiOperation(value = "推广表单浮框险种产品总数查询", httpMethod = "GET", notes = "推广表单浮框险种产品总数查询", response = Json.class)
    @GetMapping(value = "/count")
    public Json<Integer> count(@Validated(value = PromoteFormFloatingWindowDangerPlantedSpuQuery.Count.class) PromoteFormFloatingWindowDangerPlantedSpuQuery promoteFormFloatingWindowDangerPlantedSpuQuery) {
        //返回内容
        return new Json(ReturnCode.成功, promoteFormFloatingWindowDangerPlantedSpuService.countEnhance(promoteFormFloatingWindowDangerPlantedSpuQuery));
    }


    /**
     * TODO 新增
     *
     * @param promoteFormFloatingWindowDangerPlantedSpuBO 推广表单浮框险种产品
     * @return Json<String>
     * @author lijh
     * @methodName save
     * @time 2022-10-28 03:09:31
     */
    @PreventRepeat
    @Methods(methodsName = "推广表单浮框险种产品新增", methods = "save")
    @ApiOperation(value = "推广表单浮框险种产品新增", httpMethod = "POST", notes = "推广表单浮框险种产品新增", response = Json.class)
    @ApiOperationSupport(ignoreParameters = {"id", "createDateTime", "createName", "modifyDateTime", "modifyName", "isDelete", "version"})
    @PostMapping("/save")
    public Json<String> save(@Validated(value = PromoteFormFloatingWindowDangerPlantedSpuBO.Save.class) PromoteFormFloatingWindowDangerPlantedSpuBO promoteFormFloatingWindowDangerPlantedSpuBO, HttpServletRequest httpServletRequest) {
        //缓存取出用户
        Map<String, Object> u = RedisUtils.getToken(stringRedisTemplate, httpServletRequest);
        if (u != null) {
            promoteFormFloatingWindowDangerPlantedSpuBO.setCreateName(u.get("name") + "-" + u.get("id"));
        }
        //返回内容
        return new Json(ReturnCode.成功, promoteFormFloatingWindowDangerPlantedSpuService.saveEnhance(promoteFormFloatingWindowDangerPlantedSpuBO));
    }


    /**
     * TODO 修改
     *
     * @param promoteFormFloatingWindowDangerPlantedSpuBO 推广表单浮框险种产品
     * @return Json<Boolean>
     * @author lijh
     * @methodName update
     * @time 2022-10-28 03:09:31
     */
    @PreventRepeat
    @Methods(methodsName = "推广表单浮框险种产品修改", methods = "update")
    @ApiOperation(value = "推广表单浮框险种产品修改", httpMethod = "PUT", notes = "推广表单浮框险种产品修改", response = Json.class)
    @ApiOperationSupport(ignoreParameters = {"createDateTime", "createName", "modifyDateTime", "modifyName", "isDelete", "version"})
    @PutMapping("/update")
    public Json<Boolean> update(@Validated(value = PromoteFormFloatingWindowDangerPlantedSpuBO.Update.class) PromoteFormFloatingWindowDangerPlantedSpuBO promoteFormFloatingWindowDangerPlantedSpuBO, HttpServletRequest httpServletRequest) {
        //缓存取出用户
        Map<String, Object> u = RedisUtils.getToken(stringRedisTemplate, httpServletRequest);
        if (u != null) {
            promoteFormFloatingWindowDangerPlantedSpuBO.setModifyName(u.get("name") + "-" + u.get("id"));
        }
        //返回内容
        return new Json(ReturnCode.成功, promoteFormFloatingWindowDangerPlantedSpuService.updateEnhance(promoteFormFloatingWindowDangerPlantedSpuBO));
    }


    /**
     * TODO 删除
     *
     * @param promoteFormFloatingWindowDangerPlantedSpuBO 推广表单浮框险种产品
     * @return Json<Boolean>
     * @author lijh
     * @methodName remove
     * @time 2022-10-28 03:09:31
     */
    @Methods(methodsName = "推广表单浮框险种产品删除", methods = "remove")
    @ApiOperation(value = "推广表单浮框险种产品删除", httpMethod = "DELETE", notes = "推广表单浮框险种产品删除", response = Json.class)
    @ApiOperationSupport(includeParameters = {"id"})
    @DeleteMapping("/remove")
    public Json<Boolean> remove(@Validated(value = PromoteFormFloatingWindowDangerPlantedSpuBO.Remove.class) PromoteFormFloatingWindowDangerPlantedSpuBO promoteFormFloatingWindowDangerPlantedSpuBO) {
        return new Json(ReturnCode.成功, promoteFormFloatingWindowDangerPlantedSpuService.removeEnhance(promoteFormFloatingWindowDangerPlantedSpuBO));
    }
}