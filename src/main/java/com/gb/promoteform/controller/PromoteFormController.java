package com.gb.promoteform.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gb.promoteform.entity.PromoteForm;
import com.gb.promoteform.entity.bo.PromoteFormBO;
import com.gb.promoteform.entity.query.PromoteFormQuery;
import com.gb.promoteform.entity.vo.PromoteFormVO;
import com.gb.promoteform.service.PromoteFormService;
import com.gb.utils.GeneralConvertor;
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
 * TODO 推广表单，Comment请求层
 * 代码生成器
 *
 * @author lijh
 * @className PromoteFormController
 * @time 2022-07-04 10:47:25
 */
@Slf4j
@RequestRequired
@ApiSupport(author = "lijh")
@Setter(onMethod_ = {@Autowired})
@RestController
@RequestMapping("/promote-form")
@Api(tags = "推广表单")
public class PromoteFormController {


    /**
     * 推广表单
     */
    private PromoteFormService promoteFormService;
    /**
     * redis操作
     */
    private StringRedisTemplate stringRedisTemplate;


    /**
     * TODO 分页
     *
     * @param promoteFormQuery 推广表单
     * @param current
     * @param size
     * @return Json<Page < PromoteFormVO>>
     * @author lijh
     * @methodName select
     * @time 2022-07-04 10:47:25
     */
    @Methods(methodsName = "推广表单集合分页查询", methods = "select")
    @ApiOperation(value = "推广表单集合分页查询", httpMethod = "GET", notes = "推广表单集合查询", response = Json.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", dataType = "int", required = true),
            @ApiImplicitParam(name = "size", value = "分页显示数量", dataType = "int", required = true),
    })
    @GetMapping("/select")
    public Json<Page<PromoteFormVO>> select(@Validated(value = PromoteFormQuery.Select.class) PromoteFormQuery promoteFormQuery, Integer current, Integer size) {
        //返回内容
        return new Json(ReturnCode.成功, promoteFormService.pageEnhance(new Page(current, size), promoteFormQuery));
    }

    /**
     * 推广获客 分页查询
     *
     * @param promoteFormQuery
     * @param current
     * @param size
     * @return
     */
    @Methods(methodsName = "营销推广页表单集合分页查询", methods = "select")
    @ApiOperation(value = "营销推广页表单集合分页查询", httpMethod = "GET", notes = "营销推广页表单集合分页查询", response = Json.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", dataType = "int", required = true),
            @ApiImplicitParam(name = "size", value = "分页显示数量", dataType = "int", required = true),
    })
    @GetMapping("/selectListPage")
    public Json<Page<PromoteFormVO>> selectListPage(@Validated(value = PromoteFormQuery.Select.class) PromoteFormQuery promoteFormQuery, Integer current, Integer size) {
        //返回内容
        promoteFormQuery.setAgent(Boolean.TRUE);
        promoteFormQuery.setEnable(Boolean.TRUE);
        return new Json(ReturnCode.成功, promoteFormService.pageEnhance(new Page(current, size), promoteFormQuery));
    }


    /**
     * TODO 集合
     *
     * @param promoteFormQuery 推广表单
     * @return Json<List < PromoteFormVO>>
     * @author lijh
     * @methodName selectList
     * @time 2022-07-04 10:47:25
     */
    @Methods(methodsName = "推广表单集合查询", methods = "selectList")
    @ApiOperation(value = "推广表单集合查询", httpMethod = "GET", notes = "推广表单集合查询", response = Json.class)
    @GetMapping("/selectList")
    public Json<List<PromoteFormVO>> selectList(@Validated(value = PromoteFormQuery.SelectList.class) PromoteFormQuery promoteFormQuery) {
        //返回内容
        return new Json(ReturnCode.成功, promoteFormService.listEnhance(promoteFormQuery));
    }


    /**
     * TODO 单条
     *
     * @param promoteFormQuery 推广表单
     * @return Json<PromoteFormVO>
     * @author lijh
     * @methodName selectOne
     * @time 2022-07-04 10:47:25
     */
    @Methods(methodsName = "推广表单单条查询", methods = "selectOne")
    @ApiOperation(value = "推广表单单条查询", httpMethod = "GET", notes = "推广表单单条查询", response = Json.class)
    @GetMapping("/selectOne")
    public Json<PromoteFormVO> selectOne(@Validated(value = PromoteFormQuery.SelectOne.class) PromoteFormQuery promoteFormQuery) {
        //返回内容
        return new Json(ReturnCode.成功, promoteFormService.getOneEnhance(promoteFormQuery));
    }


    /**
     * TODO 总数
     *
     * @param promoteFormQuery 推广表单
     * @return Json<Integer>
     * @author lijh
     * @methodName count
     * @time 2022-07-04 10:47:25
     */
    @Methods(methodsName = "推广表单总数查询", methods = "count")
    @ApiOperation(value = "推广表单总数查询", httpMethod = "GET", notes = "推广表单总数查询", response = Json.class)
    @GetMapping(value = "/count")
    public Json<Integer> count(@Validated(value = PromoteFormQuery.Count.class) PromoteFormQuery promoteFormQuery) {
        //返回内容
        return new Json(ReturnCode.成功, promoteFormService.countEnhance(promoteFormQuery));
    }


    /**
     * TODO 新增
     *
     * @param promoteFormBO 推广表单
     * @return Json<String>
     * @author lijh
     * @methodName save
     * @time 2022-07-04 10:47:25
     */
    @PreventRepeat
    @Methods(methodsName = "推广表单新增", methods = "save")
    @ApiOperation(value = "推广表单新增", httpMethod = "POST", notes = "推广表单新增", response = Json.class)
    @ApiOperationSupport(ignoreParameters = {"id", "createDateTime", "createName", "modifyDateTime", "modifyName", "isDelete", "version"})
    @PostMapping("/save")
    public Json<String> save(@RequestBody @Validated(value = PromoteFormBO.Save.class) PromoteFormBO promoteFormBO, HttpServletRequest httpServletRequest) {
        //缓存取出用户
        Map<String, Object> u = RedisUtils.getToken(stringRedisTemplate, httpServletRequest);
        if (u != null) {
            promoteFormBO.setCreateName(u.get("name") + "-" + u.get("id"));
            promoteFormBO.setModifyName(u.get("name") + "-" + u.get("id"));
        }
        //返回内容
        return new Json(ReturnCode.成功, promoteFormService.saveEnhance(promoteFormBO));
    }


    /**
     * TODO 修改
     *
     * @param promoteFormBO 推广表单
     * @return Json<Boolean>
     * @author lijh
     * @methodName update
     * @time 2022-07-04 10:47:25
     */
    @PreventRepeat
    @Methods(methodsName = "推广表单修改", methods = "update")
    @ApiOperation(value = "推广表单修改", httpMethod = "PUT", notes = "推广表单修改", response = Json.class)
    @ApiOperationSupport(ignoreParameters = {"createDateTime", "createName", "modifyDateTime", "modifyName", "isDelete", "version"})
    @PutMapping("/update")
    public Json<Boolean> update(@RequestBody @Validated(value = PromoteFormBO.Update.class) PromoteFormBO promoteFormBO, HttpServletRequest httpServletRequest) {
        //缓存取出用户
        Map<String, Object> u = RedisUtils.getToken(stringRedisTemplate, httpServletRequest);
        if (u != null) {
            promoteFormBO.setModifyName(u.get("name") + "-" + u.get("id"));
        }
        //返回内容
        return new Json(ReturnCode.成功, promoteFormService.updateEnhance(promoteFormBO));
    }


    /**
     * TODO 删除
     *
     * @param promoteFormBO 推广表单
     * @return Json<Boolean>
     * @author lijh
     * @methodName remove
     * @time 2022-07-04 10:47:25
     */
    @Methods(methodsName = "推广表单删除", methods = "remove")
    @ApiOperation(value = "推广表单删除", httpMethod = "DELETE", notes = "推广表单删除", response = Json.class)
    @ApiOperationSupport(includeParameters = {"id"})
    @DeleteMapping("/remove")
    public Json<Boolean> remove(@Validated(value = PromoteFormBO.Remove.class) PromoteFormBO promoteFormBO) {
        return new Json(ReturnCode.成功, promoteFormService.removeEnhance(promoteFormBO));
    }

    @PreventRepeat
    @Methods(methodsName = "推广表单启用", methods = "update")
    @ApiOperation(value = "推广表单启用", httpMethod = "POST", notes = "推广表单启用", response = Json.class)
    @ApiOperationSupport(ignoreParameters = {"createDateTime", "createName", "modifyDateTime", "modifyName", "isDelete", "version"})
    @PostMapping("/enablePromoteForm")
    public Json<Boolean> enablePromoteForm(@Validated(value = PromoteFormBO.Update.class) PromoteFormBO promoteFormBO, HttpServletRequest httpServletRequest) {
        //缓存取出用户
        Map<String, Object> u = RedisUtils.getToken(stringRedisTemplate, httpServletRequest);
        if (u != null) {
            promoteFormBO.setModifyName(u.get("name") + "-" + u.get("id"));
        }
        PromoteForm promoteForm = GeneralConvertor.convertor(promoteFormBO, PromoteForm.class);
        //返回内容
        return new Json(ReturnCode.成功, promoteFormService.updateById(promoteForm));
    }

    /**
     * 经纪人预览表单-前台预览
     */
    @Methods(methodsName = "预览表单", methods = "previewForm")
    @ApiOperation(value = "预览表单", httpMethod = "GET", notes = "推广表单单条查询")
    @GetMapping("/previewForm")
    public Json<PromoteFormVO> previewForm(String id, String agentUserId, String agentUserName) {
        //返回内容
        return new Json(ReturnCode.成功, promoteFormService.previewForm(id, agentUserId, agentUserName));
    }

    /**
     * 经纪人预览表单-后台预览
     */
    @Methods(methodsName = "预览表单", methods = "previewForm")
    @ApiOperation(value = "预览表单", httpMethod = "GET", notes = "推广表单单条查询")
    @GetMapping("/getQrCodeLinkAddress")
    public Json<PromoteFormVO> getQrCodeLinkAddress(String qrCodeLinkAddress) {
        //返回内容
        return new Json(ReturnCode.成功, promoteFormService.getQrCodeLinkAddress(qrCodeLinkAddress));
    }
}