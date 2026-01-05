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
import com.gb.promoteform.service.PromoteFormProductService;
import com.gb.promoteform.entity.query.PromoteFormProductQuery;
import com.gb.promoteform.entity.vo.PromoteFormProductVO;
import com.gb.promoteform.entity.bo.PromoteFormProductBO;


/**
 * TODO 推广表单产品，Comment请求层
 * 代码生成器
 *
 * @author lijh
 * @className PromoteFormProductController
 * @time 2022-07-04 10:49:04
 */
@Slf4j
@RequestRequired
@ApiSupport(author = "lijh")
@Setter(onMethod_ = {@Autowired})
@RestController
@RequestMapping("/promote-form-product")
@Api(tags = "推广表单产品")
public class PromoteFormProductController {


    /**
     * 推广表单产品
     */
    private PromoteFormProductService promoteFormProductService;
    /**
     * redis操作
     */
    private StringRedisTemplate stringRedisTemplate;


    /**
     * TODO 分页
     *
     * @param promoteFormProductQuery 推广表单产品
     * @param current
     * @param size
     * @return Json<Page<PromoteFormProductVO>>
     * @author lijh
     * @methodName select
     * @time 2022-07-04 10:49:04
     */
    @Methods(methodsName = "推广表单产品集合分页查询", methods = "select")
    @ApiOperation(value = "推广表单产品集合分页查询", httpMethod = "GET", notes = "推广表单产品集合查询", response = Json.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", dataType = "int", required = true),
            @ApiImplicitParam(name = "size", value = "分页显示数量", dataType = "int", required = true),
    })
    @GetMapping("/select")
    public Json<Page<PromoteFormProductVO>> select(@Validated(value = PromoteFormProductQuery.Select.class) PromoteFormProductQuery promoteFormProductQuery, Integer current, Integer size) {
        //返回内容
        return new Json(ReturnCode.成功, promoteFormProductService.pageEnhance(new Page(current, size), promoteFormProductQuery));
    }


    /**
     * TODO 集合
     *
     * @param promoteFormProductQuery 推广表单产品
     * @return Json<List<PromoteFormProductVO>>
     * @author lijh
     * @methodName selectList
     * @time 2022-07-04 10:49:04
     */
    @Methods(methodsName = "推广表单产品集合查询", methods = "selectList")
    @ApiOperation(value = "推广表单产品集合查询", httpMethod = "GET", notes = "推广表单产品集合查询", response = Json.class)
    @GetMapping("/selectList")
    public Json<List<PromoteFormProductVO>> selectList(@Validated(value = PromoteFormProductQuery.SelectList.class) PromoteFormProductQuery promoteFormProductQuery) {
        //返回内容
        return new Json(ReturnCode.成功, promoteFormProductService.listEnhance(promoteFormProductQuery));
    }


    /**
     * TODO 单条
     *
     * @param promoteFormProductQuery 推广表单产品
     * @return Json<PromoteFormProductVO>
     * @author lijh
     * @methodName selectOne
     * @time 2022-07-04 10:49:04
     */
    @Methods(methodsName = "推广表单产品单条查询", methods = "selectOne")
    @ApiOperation(value = "推广表单产品单条查询", httpMethod = "GET", notes = "推广表单产品单条查询", response = Json.class)
    @GetMapping("/selectOne")
    public Json<PromoteFormProductVO> selectOne(@Validated(value = PromoteFormProductQuery.SelectOne.class) PromoteFormProductQuery promoteFormProductQuery) {
        //返回内容
        return new Json(ReturnCode.成功, promoteFormProductService.getOneEnhance(promoteFormProductQuery));
    }


    /**
     * TODO 总数
     *
     * @param promoteFormProductQuery 推广表单产品
     * @return Json<Integer>
     * @author lijh
     * @methodName count
     * @time 2022-07-04 10:49:04
     */
    @Methods(methodsName = "推广表单产品总数查询", methods = "count")
    @ApiOperation(value = "推广表单产品总数查询", httpMethod = "GET", notes = "推广表单产品总数查询", response = Json.class)
    @GetMapping(value = "/count")
    public Json<Integer> count(@Validated(value = PromoteFormProductQuery.Count.class) PromoteFormProductQuery promoteFormProductQuery) {
        //返回内容
        return new Json(ReturnCode.成功, promoteFormProductService.countEnhance(promoteFormProductQuery));
    }


    /**
     * TODO 新增
     *
     * @param promoteFormProductBO 推广表单产品
     * @return Json<String>
     * @author lijh
     * @methodName save
     * @time 2022-07-04 10:49:04
     */
    @PreventRepeat
    @Methods(methodsName = "推广表单产品新增", methods = "save")
    @ApiOperation(value = "推广表单产品新增", httpMethod = "POST", notes = "推广表单产品新增", response = Json.class)
    @ApiOperationSupport(ignoreParameters = {"id", "createDateTime", "createName", "modifyDateTime", "modifyName", "isDelete", "version"})
    @PostMapping("/save")
    public Json<String> save(@Validated(value = PromoteFormProductBO.Save.class) PromoteFormProductBO promoteFormProductBO, HttpServletRequest httpServletRequest) {
        //缓存取出用户
        Map<String, Object> u = RedisUtils.getToken(stringRedisTemplate, httpServletRequest);
        if (u != null) {
            promoteFormProductBO.setCreateName(u.get("name") + "-" + u.get("id"));
        }
        //返回内容
        return new Json(ReturnCode.成功, promoteFormProductService.saveEnhance(promoteFormProductBO));
    }


    /**
     * TODO 修改
     *
     * @param promoteFormProductBO 推广表单产品
     * @return Json<Boolean>
     * @author lijh
     * @methodName update
     * @time 2022-07-04 10:49:04
     */
    @PreventRepeat
    @Methods(methodsName = "推广表单产品修改", methods = "update")
    @ApiOperation(value = "推广表单产品修改", httpMethod = "PUT", notes = "推广表单产品修改", response = Json.class)
    @ApiOperationSupport(ignoreParameters = {"createDateTime", "createName", "modifyDateTime", "modifyName", "isDelete", "version"})
    @PutMapping("/update")
    public Json<Boolean> update(@Validated(value = PromoteFormProductBO.Update.class) PromoteFormProductBO promoteFormProductBO, HttpServletRequest httpServletRequest) {
        //缓存取出用户
        Map<String, Object> u = RedisUtils.getToken(stringRedisTemplate, httpServletRequest);
        if (u != null) {
            promoteFormProductBO.setModifyName(u.get("name") + "-" + u.get("id"));
        }
        //返回内容
        return new Json(ReturnCode.成功, promoteFormProductService.updateEnhance(promoteFormProductBO));
    }


    /**
     * TODO 删除
     *
     * @param promoteFormProductBO 推广表单产品
     * @return Json<Boolean>
     * @author lijh
     * @methodName remove
     * @time 2022-07-04 10:49:04
     */
    @Methods(methodsName = "推广表单产品删除", methods = "remove")
    @ApiOperation(value = "推广表单产品删除", httpMethod = "DELETE", notes = "推广表单产品删除", response = Json.class)
    @ApiOperationSupport(includeParameters = {"id"})
    @DeleteMapping("/remove")
    public Json<Boolean> remove(@Validated(value = PromoteFormProductBO.Remove.class) PromoteFormProductBO promoteFormProductBO) {
        return new Json(ReturnCode.成功, promoteFormProductService.removeEnhance(promoteFormProductBO));
    }
}