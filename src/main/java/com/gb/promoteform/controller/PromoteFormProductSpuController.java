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
import com.gb.promoteform.service.PromoteFormProductSpuService;
import com.gb.promoteform.entity.query.PromoteFormProductSpuQuery;
import com.gb.promoteform.entity.vo.PromoteFormProductSpuVO;
import com.gb.promoteform.entity.bo.PromoteFormProductSpuBO;


/**
 * TODO 推广表单产品id，Comment请求层
 * 代码生成器
 *
 * @author lijh
 * @className PromoteFormProductSpuController
 * @time 2022-10-28 03:09:32
 */
@Slf4j
@RequestRequired
@ApiSupport(author = "lijh")
@Setter(onMethod_ = {@Autowired})
@RestController
@RequestMapping("/promote-form-product-spu")
@Api(tags = "推广表单产品id")
public class PromoteFormProductSpuController {


    /**
     * 推广表单产品id
     */
    private PromoteFormProductSpuService promoteFormProductSpuService;
    /**
     * redis操作
     */
    private StringRedisTemplate stringRedisTemplate;


    /**
     * TODO 分页
     *
     * @param promoteFormProductSpuQuery 推广表单产品id
     * @param current
     * @param size
     * @return Json<Page<PromoteFormProductSpuVO>>
     * @author lijh
     * @methodName select
     * @time 2022-10-28 03:09:32
     */
    @Methods(methodsName = "推广表单产品id集合分页查询", methods = "select")
    @ApiOperation(value = "推广表单产品id集合分页查询", httpMethod = "GET", notes = "推广表单产品id集合查询", response = Json.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", dataType = "int", required = true),
            @ApiImplicitParam(name = "size", value = "分页显示数量", dataType = "int", required = true),
    })
    @GetMapping("/select")
    public Json<Page<PromoteFormProductSpuVO>> select(@Validated(value = PromoteFormProductSpuQuery.Select.class) PromoteFormProductSpuQuery promoteFormProductSpuQuery, Integer current, Integer size) {
        //返回内容
        return new Json(ReturnCode.成功, promoteFormProductSpuService.pageEnhance(new Page(current, size), promoteFormProductSpuQuery));
    }


    /**
     * TODO 集合
     *
     * @param promoteFormProductSpuQuery 推广表单产品id
     * @return Json<List<PromoteFormProductSpuVO>>
     * @author lijh
     * @methodName selectList
     * @time 2022-10-28 03:09:32
     */
    @Methods(methodsName = "推广表单产品id集合查询", methods = "selectList")
    @ApiOperation(value = "推广表单产品id集合查询", httpMethod = "GET", notes = "推广表单产品id集合查询", response = Json.class)
    @GetMapping("/selectList")
    public Json<List<PromoteFormProductSpuVO>> selectList(@Validated(value = PromoteFormProductSpuQuery.SelectList.class) PromoteFormProductSpuQuery promoteFormProductSpuQuery) {
        //返回内容
        return new Json(ReturnCode.成功, promoteFormProductSpuService.listEnhance(promoteFormProductSpuQuery));
    }


    /**
     * TODO 单条
     *
     * @param promoteFormProductSpuQuery 推广表单产品id
     * @return Json<PromoteFormProductSpuVO>
     * @author lijh
     * @methodName selectOne
     * @time 2022-10-28 03:09:32
     */
    @Methods(methodsName = "推广表单产品id单条查询", methods = "selectOne")
    @ApiOperation(value = "推广表单产品id单条查询", httpMethod = "GET", notes = "推广表单产品id单条查询", response = Json.class)
    @GetMapping("/selectOne")
    public Json<PromoteFormProductSpuVO> selectOne(@Validated(value = PromoteFormProductSpuQuery.SelectOne.class) PromoteFormProductSpuQuery promoteFormProductSpuQuery) {
        //返回内容
        return new Json(ReturnCode.成功, promoteFormProductSpuService.getOneEnhance(promoteFormProductSpuQuery));
    }


    /**
     * TODO 总数
     *
     * @param promoteFormProductSpuQuery 推广表单产品id
     * @return Json<Integer>
     * @author lijh
     * @methodName count
     * @time 2022-10-28 03:09:32
     */
    @Methods(methodsName = "推广表单产品id总数查询", methods = "count")
    @ApiOperation(value = "推广表单产品id总数查询", httpMethod = "GET", notes = "推广表单产品id总数查询", response = Json.class)
    @GetMapping(value = "/count")
    public Json<Integer> count(@Validated(value = PromoteFormProductSpuQuery.Count.class) PromoteFormProductSpuQuery promoteFormProductSpuQuery) {
        //返回内容
        return new Json(ReturnCode.成功, promoteFormProductSpuService.countEnhance(promoteFormProductSpuQuery));
    }


    /**
     * TODO 新增
     *
     * @param promoteFormProductSpuBO 推广表单产品id
     * @return Json<String>
     * @author lijh
     * @methodName save
     * @time 2022-10-28 03:09:32
     */
    @PreventRepeat
    @Methods(methodsName = "推广表单产品id新增", methods = "save")
    @ApiOperation(value = "推广表单产品id新增", httpMethod = "POST", notes = "推广表单产品id新增", response = Json.class)
    @ApiOperationSupport(ignoreParameters = {"id", "createDateTime", "createName", "modifyDateTime", "modifyName", "isDelete", "version"})
    @PostMapping("/save")
    public Json<String> save(@Validated(value = PromoteFormProductSpuBO.Save.class) PromoteFormProductSpuBO promoteFormProductSpuBO, HttpServletRequest httpServletRequest) {
        //缓存取出用户
        Map<String, Object> u = RedisUtils.getToken(stringRedisTemplate, httpServletRequest);
        if (u != null) {
            promoteFormProductSpuBO.setCreateName(u.get("name") + "-" + u.get("id"));
        }
        //返回内容
        return new Json(ReturnCode.成功, promoteFormProductSpuService.saveEnhance(promoteFormProductSpuBO));
    }


    /**
     * TODO 修改
     *
     * @param promoteFormProductSpuBO 推广表单产品id
     * @return Json<Boolean>
     * @author lijh
     * @methodName update
     * @time 2022-10-28 03:09:32
     */
    @PreventRepeat
    @Methods(methodsName = "推广表单产品id修改", methods = "update")
    @ApiOperation(value = "推广表单产品id修改", httpMethod = "PUT", notes = "推广表单产品id修改", response = Json.class)
    @ApiOperationSupport(ignoreParameters = {"createDateTime", "createName", "modifyDateTime", "modifyName", "isDelete", "version"})
    @PutMapping("/update")
    public Json<Boolean> update(@Validated(value = PromoteFormProductSpuBO.Update.class) PromoteFormProductSpuBO promoteFormProductSpuBO, HttpServletRequest httpServletRequest) {
        //缓存取出用户
        Map<String, Object> u = RedisUtils.getToken(stringRedisTemplate, httpServletRequest);
        if (u != null) {
            promoteFormProductSpuBO.setModifyName(u.get("name") + "-" + u.get("id"));
        }
        //返回内容
        return new Json(ReturnCode.成功, promoteFormProductSpuService.updateEnhance(promoteFormProductSpuBO));
    }


    /**
     * TODO 删除
     *
     * @param promoteFormProductSpuBO 推广表单产品id
     * @return Json<Boolean>
     * @author lijh
     * @methodName remove
     * @time 2022-10-28 03:09:32
     */
    @Methods(methodsName = "推广表单产品id删除", methods = "remove")
    @ApiOperation(value = "推广表单产品id删除", httpMethod = "DELETE", notes = "推广表单产品id删除", response = Json.class)
    @ApiOperationSupport(includeParameters = {"id"})
    @DeleteMapping("/remove")
    public Json<Boolean> remove(@Validated(value = PromoteFormProductSpuBO.Remove.class) PromoteFormProductSpuBO promoteFormProductSpuBO) {
        return new Json(ReturnCode.成功, promoteFormProductSpuService.removeEnhance(promoteFormProductSpuBO));
    }
}