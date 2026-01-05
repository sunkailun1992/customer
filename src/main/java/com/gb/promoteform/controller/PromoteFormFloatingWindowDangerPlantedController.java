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
import com.gb.promoteform.service.PromoteFormFloatingWindowDangerPlantedService;
import com.gb.promoteform.entity.query.PromoteFormFloatingWindowDangerPlantedQuery;
import com.gb.promoteform.entity.vo.PromoteFormFloatingWindowDangerPlantedVO;
import com.gb.promoteform.entity.bo.PromoteFormFloatingWindowDangerPlantedBO;


/**
 * TODO 推广表单浮框险种，Comment请求层
 * 代码生成器
 *
 * @author lijh
 * @className PromoteFormFloatingWindowDangerPlantedController
 * @time 2022-07-04 10:49:04
 */
@Slf4j
@RequestRequired
@ApiSupport(author = "lijh")
@Setter(onMethod_ = {@Autowired})
@RestController
@RequestMapping("/promote-form-floating-window-danger-planted")
@Api(tags = "推广表单浮框险种")
public class PromoteFormFloatingWindowDangerPlantedController {


    /**
     * 推广表单浮框险种
     */
    private PromoteFormFloatingWindowDangerPlantedService promoteFormFloatingWindowDangerPlantedService;
    /**
     * redis操作
     */
    private StringRedisTemplate stringRedisTemplate;


    /**
     * TODO 分页
     *
     * @param promoteFormFloatingWindowDangerPlantedQuery 推广表单浮框险种
     * @param current
     * @param size
     * @return Json<Page<PromoteFormFloatingWindowDangerPlantedVO>>
     * @author lijh
     * @methodName select
     * @time 2022-07-04 10:49:04
     */
    @Methods(methodsName = "推广表单浮框险种集合分页查询", methods = "select")
    @ApiOperation(value = "推广表单浮框险种集合分页查询", httpMethod = "GET", notes = "推广表单浮框险种集合查询", response = Json.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", dataType = "int", required = true),
            @ApiImplicitParam(name = "size", value = "分页显示数量", dataType = "int", required = true),
    })
    @GetMapping("/select")
    public Json<Page<PromoteFormFloatingWindowDangerPlantedVO>> select(@Validated(value = PromoteFormFloatingWindowDangerPlantedQuery.Select.class) PromoteFormFloatingWindowDangerPlantedQuery promoteFormFloatingWindowDangerPlantedQuery, Integer current, Integer size) {
        //返回内容
        return new Json(ReturnCode.成功, promoteFormFloatingWindowDangerPlantedService.pageEnhance(new Page(current, size), promoteFormFloatingWindowDangerPlantedQuery));
    }


    /**
     * TODO 集合
     *
     * @param promoteFormFloatingWindowDangerPlantedQuery 推广表单浮框险种
     * @return Json<List<PromoteFormFloatingWindowDangerPlantedVO>>
     * @author lijh
     * @methodName selectList
     * @time 2022-07-04 10:49:04
     */
    @Methods(methodsName = "推广表单浮框险种集合查询", methods = "selectList")
    @ApiOperation(value = "推广表单浮框险种集合查询", httpMethod = "GET", notes = "推广表单浮框险种集合查询", response = Json.class)
    @GetMapping("/selectList")
    public Json<List<PromoteFormFloatingWindowDangerPlantedVO>> selectList(@Validated(value = PromoteFormFloatingWindowDangerPlantedQuery.SelectList.class) PromoteFormFloatingWindowDangerPlantedQuery promoteFormFloatingWindowDangerPlantedQuery) {
        //返回内容
        return new Json(ReturnCode.成功, promoteFormFloatingWindowDangerPlantedService.listEnhance(promoteFormFloatingWindowDangerPlantedQuery));
    }


    /**
     * TODO 单条
     *
     * @param promoteFormFloatingWindowDangerPlantedQuery 推广表单浮框险种
     * @return Json<PromoteFormFloatingWindowDangerPlantedVO>
     * @author lijh
     * @methodName selectOne
     * @time 2022-07-04 10:49:04
     */
    @Methods(methodsName = "推广表单浮框险种单条查询", methods = "selectOne")
    @ApiOperation(value = "推广表单浮框险种单条查询", httpMethod = "GET", notes = "推广表单浮框险种单条查询", response = Json.class)
    @GetMapping("/selectOne")
    public Json<PromoteFormFloatingWindowDangerPlantedVO> selectOne(@Validated(value = PromoteFormFloatingWindowDangerPlantedQuery.SelectOne.class) PromoteFormFloatingWindowDangerPlantedQuery promoteFormFloatingWindowDangerPlantedQuery) {
        //返回内容
        return new Json(ReturnCode.成功, promoteFormFloatingWindowDangerPlantedService.getOneEnhance(promoteFormFloatingWindowDangerPlantedQuery));
    }


    /**
     * TODO 总数
     *
     * @param promoteFormFloatingWindowDangerPlantedQuery 推广表单浮框险种
     * @return Json<Integer>
     * @author lijh
     * @methodName count
     * @time 2022-07-04 10:49:04
     */
    @Methods(methodsName = "推广表单浮框险种总数查询", methods = "count")
    @ApiOperation(value = "推广表单浮框险种总数查询", httpMethod = "GET", notes = "推广表单浮框险种总数查询", response = Json.class)
    @GetMapping(value = "/count")
    public Json<Integer> count(@Validated(value = PromoteFormFloatingWindowDangerPlantedQuery.Count.class) PromoteFormFloatingWindowDangerPlantedQuery promoteFormFloatingWindowDangerPlantedQuery) {
        //返回内容
        return new Json(ReturnCode.成功, promoteFormFloatingWindowDangerPlantedService.countEnhance(promoteFormFloatingWindowDangerPlantedQuery));
    }


    /**
     * TODO 新增
     *
     * @param promoteFormFloatingWindowDangerPlantedBO 推广表单浮框险种
     * @return Json<String>
     * @author lijh
     * @methodName save
     * @time 2022-07-04 10:49:04
     */
    @PreventRepeat
    @Methods(methodsName = "推广表单浮框险种新增", methods = "save")
    @ApiOperation(value = "推广表单浮框险种新增", httpMethod = "POST", notes = "推广表单浮框险种新增", response = Json.class)
    @ApiOperationSupport(ignoreParameters = {"id", "createDateTime", "createName", "modifyDateTime", "modifyName", "isDelete", "version"})
    @PostMapping("/save")
    public Json<String> save(@Validated(value = PromoteFormFloatingWindowDangerPlantedBO.Save.class) PromoteFormFloatingWindowDangerPlantedBO promoteFormFloatingWindowDangerPlantedBO, HttpServletRequest httpServletRequest) {
        //缓存取出用户
        Map<String, Object> u = RedisUtils.getToken(stringRedisTemplate, httpServletRequest);
        if (u != null) {
            promoteFormFloatingWindowDangerPlantedBO.setCreateName(u.get("name") + "-" + u.get("id"));
        }
        //返回内容
        return new Json(ReturnCode.成功, promoteFormFloatingWindowDangerPlantedService.saveEnhance(promoteFormFloatingWindowDangerPlantedBO));
    }


    /**
     * TODO 修改
     *
     * @param promoteFormFloatingWindowDangerPlantedBO 推广表单浮框险种
     * @return Json<Boolean>
     * @author lijh
     * @methodName update
     * @time 2022-07-04 10:49:04
     */
    @PreventRepeat
    @Methods(methodsName = "推广表单浮框险种修改", methods = "update")
    @ApiOperation(value = "推广表单浮框险种修改", httpMethod = "PUT", notes = "推广表单浮框险种修改", response = Json.class)
    @ApiOperationSupport(ignoreParameters = {"createDateTime", "createName", "modifyDateTime", "modifyName", "isDelete", "version"})
    @PutMapping("/update")
    public Json<Boolean> update(@Validated(value = PromoteFormFloatingWindowDangerPlantedBO.Update.class) PromoteFormFloatingWindowDangerPlantedBO promoteFormFloatingWindowDangerPlantedBO, HttpServletRequest httpServletRequest) {
        //缓存取出用户
        Map<String, Object> u = RedisUtils.getToken(stringRedisTemplate, httpServletRequest);
        if (u != null) {
            promoteFormFloatingWindowDangerPlantedBO.setModifyName(u.get("name") + "-" + u.get("id"));
        }
        //返回内容
        return new Json(ReturnCode.成功, promoteFormFloatingWindowDangerPlantedService.updateEnhance(promoteFormFloatingWindowDangerPlantedBO));
    }


    /**
     * TODO 删除
     *
     * @param promoteFormFloatingWindowDangerPlantedBO 推广表单浮框险种
     * @return Json<Boolean>
     * @author lijh
     * @methodName remove
     * @time 2022-07-04 10:49:04
     */
    @Methods(methodsName = "推广表单浮框险种删除", methods = "remove")
    @ApiOperation(value = "推广表单浮框险种删除", httpMethod = "DELETE", notes = "推广表单浮框险种删除", response = Json.class)
    @ApiOperationSupport(includeParameters = {"id"})
    @DeleteMapping("/remove")
    public Json<Boolean> remove(@Validated(value = PromoteFormFloatingWindowDangerPlantedBO.Remove.class) PromoteFormFloatingWindowDangerPlantedBO promoteFormFloatingWindowDangerPlantedBO) {
        return new Json(ReturnCode.成功, promoteFormFloatingWindowDangerPlantedService.removeEnhance(promoteFormFloatingWindowDangerPlantedBO));
    }
}