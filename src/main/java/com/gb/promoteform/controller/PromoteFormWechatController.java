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
import com.gb.promoteform.service.PromoteFormWechatService;
import com.gb.promoteform.entity.query.PromoteFormWechatQuery;
import com.gb.promoteform.entity.vo.PromoteFormWechatVO;
import com.gb.promoteform.entity.bo.PromoteFormWechatBO;


/**
 * TODO 推广表单微信，Comment请求层
 * 代码生成器
 *
 * @author lijh
 * @className PromoteFormWechatController
 * @time 2022-07-04 10:49:05
 */
@Slf4j
@RequestRequired
@ApiSupport(author = "lijh")
@Setter(onMethod_ = {@Autowired})
@RestController
@RequestMapping("/promote-form-wechat")
@Api(tags = "推广表单微信")
public class PromoteFormWechatController {


    /**
     * 推广表单微信
     */
    private PromoteFormWechatService promoteFormWechatService;
    /**
     * redis操作
     */
    private StringRedisTemplate stringRedisTemplate;


    /**
     * TODO 分页
     *
     * @param promoteFormWechatQuery 推广表单微信
     * @param current
     * @param size
     * @return Json<Page<PromoteFormWechatVO>>
     * @author lijh
     * @methodName select
     * @time 2022-07-04 10:49:05
     */
    @Methods(methodsName = "推广表单微信集合分页查询", methods = "select")
    @ApiOperation(value = "推广表单微信集合分页查询", httpMethod = "GET", notes = "推广表单微信集合查询", response = Json.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", dataType = "int", required = true),
            @ApiImplicitParam(name = "size", value = "分页显示数量", dataType = "int", required = true),
    })
    @GetMapping("/select")
    public Json<Page<PromoteFormWechatVO>> select(@Validated(value = PromoteFormWechatQuery.Select.class) PromoteFormWechatQuery promoteFormWechatQuery, Integer current, Integer size) {
        //返回内容
        return new Json(ReturnCode.成功, promoteFormWechatService.pageEnhance(new Page(current, size), promoteFormWechatQuery));
    }


    /**
     * TODO 集合
     *
     * @param promoteFormWechatQuery 推广表单微信
     * @return Json<List<PromoteFormWechatVO>>
     * @author lijh
     * @methodName selectList
     * @time 2022-07-04 10:49:05
     */
    @Methods(methodsName = "推广表单微信集合查询", methods = "selectList")
    @ApiOperation(value = "推广表单微信集合查询", httpMethod = "GET", notes = "推广表单微信集合查询", response = Json.class)
    @GetMapping("/selectList")
    public Json<List<PromoteFormWechatVO>> selectList(@Validated(value = PromoteFormWechatQuery.SelectList.class) PromoteFormWechatQuery promoteFormWechatQuery) {
        //返回内容
        return new Json(ReturnCode.成功, promoteFormWechatService.listEnhance(promoteFormWechatQuery));
    }


    /**
     * TODO 单条
     *
     * @param promoteFormWechatQuery 推广表单微信
     * @return Json<PromoteFormWechatVO>
     * @author lijh
     * @methodName selectOne
     * @time 2022-07-04 10:49:05
     */
    @Methods(methodsName = "推广表单微信单条查询", methods = "selectOne")
    @ApiOperation(value = "推广表单微信单条查询", httpMethod = "GET", notes = "推广表单微信单条查询", response = Json.class)
    @GetMapping("/selectOne")
    public Json<PromoteFormWechatVO> selectOne(@Validated(value = PromoteFormWechatQuery.SelectOne.class) PromoteFormWechatQuery promoteFormWechatQuery) {
        //返回内容
        return new Json(ReturnCode.成功, promoteFormWechatService.getOneEnhance(promoteFormWechatQuery));
    }


    /**
     * TODO 总数
     *
     * @param promoteFormWechatQuery 推广表单微信
     * @return Json<Integer>
     * @author lijh
     * @methodName count
     * @time 2022-07-04 10:49:05
     */
    @Methods(methodsName = "推广表单微信总数查询", methods = "count")
    @ApiOperation(value = "推广表单微信总数查询", httpMethod = "GET", notes = "推广表单微信总数查询", response = Json.class)
    @GetMapping(value = "/count")
    public Json<Integer> count(@Validated(value = PromoteFormWechatQuery.Count.class) PromoteFormWechatQuery promoteFormWechatQuery) {
        //返回内容
        return new Json(ReturnCode.成功, promoteFormWechatService.countEnhance(promoteFormWechatQuery));
    }


    /**
     * TODO 新增
     *
     * @param promoteFormWechatBO 推广表单微信
     * @return Json<String>
     * @author lijh
     * @methodName save
     * @time 2022-07-04 10:49:05
     */
    @PreventRepeat
    @Methods(methodsName = "推广表单微信新增", methods = "save")
    @ApiOperation(value = "推广表单微信新增", httpMethod = "POST", notes = "推广表单微信新增", response = Json.class)
    @ApiOperationSupport(ignoreParameters = {"id", "createDateTime", "createName", "modifyDateTime", "modifyName", "isDelete", "version"})
    @PostMapping("/save")
    public Json<String> save(@Validated(value = PromoteFormWechatBO.Save.class) PromoteFormWechatBO promoteFormWechatBO, HttpServletRequest httpServletRequest) {
        //缓存取出用户
        Map<String, Object> u = RedisUtils.getToken(stringRedisTemplate, httpServletRequest);
        if (u != null) {
            promoteFormWechatBO.setCreateName(u.get("name") + "-" + u.get("id"));
        }
        //返回内容
        return new Json(ReturnCode.成功, promoteFormWechatService.saveEnhance(promoteFormWechatBO));
    }


    /**
     * TODO 修改
     *
     * @param promoteFormWechatBO 推广表单微信
     * @return Json<Boolean>
     * @author lijh
     * @methodName update
     * @time 2022-07-04 10:49:05
     */
    @PreventRepeat
    @Methods(methodsName = "推广表单微信修改", methods = "update")
    @ApiOperation(value = "推广表单微信修改", httpMethod = "PUT", notes = "推广表单微信修改", response = Json.class)
    @ApiOperationSupport(ignoreParameters = {"createDateTime", "createName", "modifyDateTime", "modifyName", "isDelete", "version"})
    @PutMapping("/update")
    public Json<Boolean> update(@Validated(value = PromoteFormWechatBO.Update.class) PromoteFormWechatBO promoteFormWechatBO, HttpServletRequest httpServletRequest) {
        //缓存取出用户
        Map<String, Object> u = RedisUtils.getToken(stringRedisTemplate, httpServletRequest);
        if (u != null) {
            promoteFormWechatBO.setModifyName(u.get("name") + "-" + u.get("id"));
        }
        //返回内容
        return new Json(ReturnCode.成功, promoteFormWechatService.updateEnhance(promoteFormWechatBO));
    }


    /**
     * TODO 删除
     *
     * @param promoteFormWechatBO 推广表单微信
     * @return Json<Boolean>
     * @author lijh
     * @methodName remove
     * @time 2022-07-04 10:49:05
     */
    @Methods(methodsName = "推广表单微信删除", methods = "remove")
    @ApiOperation(value = "推广表单微信删除", httpMethod = "DELETE", notes = "推广表单微信删除", response = Json.class)
    @ApiOperationSupport(includeParameters = {"id"})
    @DeleteMapping("/remove")
    public Json<Boolean> remove(@Validated(value = PromoteFormWechatBO.Remove.class) PromoteFormWechatBO promoteFormWechatBO) {
        return new Json(ReturnCode.成功, promoteFormWechatService.removeEnhance(promoteFormWechatBO));
    }
}