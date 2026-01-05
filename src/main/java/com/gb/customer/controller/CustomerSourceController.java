package com.gb.customer.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gb.customer.entity.bo.CustomerSourceBO;
import com.gb.customer.entity.query.CustomerSourceQuery;
import com.gb.customer.entity.vo.CustomerSourceVO;
import com.gb.customer.service.CustomerSourceService;
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
 * TODO 客户来源，Comment请求层
 * 代码生成器
 *
 * @author lijh
 * @className CustomerSourceController
 * @time 2022-09-01 03:12:09
 */
@Slf4j
@RequestRequired
@ApiSupport(author = "lijh")
@Setter(onMethod_ = {@Autowired})
@RestController
@RequestMapping("/customer-source")
@Api(tags = "客户来源")
public class CustomerSourceController {


    /**
     * 客户来源
     */
    private CustomerSourceService customerSourceService;
    /**
     * redis操作
     */
    private StringRedisTemplate stringRedisTemplate;


    /**
     * TODO 分页
     *
     * @param customerSourceQuery 客户来源
     * @param current
     * @param size
     * @return Json<Page<CustomerSourceVO>>
     * @author lijh
     * @methodName select
     * @time 2022-09-01 03:12:09
     */
    @Methods(methodsName = "客户来源集合分页查询", methods = "select")
    @ApiOperation(value = "客户来源集合分页查询", httpMethod = "GET", notes = "客户来源集合查询", response = Json.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", dataType = "int", required = true),
            @ApiImplicitParam(name = "size", value = "分页显示数量", dataType = "int", required = true),
    })
    @GetMapping("/select")
    public Json<Page<CustomerSourceVO>> select(@Validated(value = CustomerSourceQuery.Select.class) CustomerSourceQuery customerSourceQuery, Integer current, Integer size) {
        //返回内容
        return new Json(ReturnCode.成功, customerSourceService.pageEnhance(new Page(current, size), customerSourceQuery));
    }


    /**
     * TODO 集合
     *
     * @param customerSourceQuery 客户来源
     * @return Json<List<CustomerSourceVO>>
     * @author lijh
     * @methodName selectList
     * @time 2022-09-01 03:12:09
     */
    @Methods(methodsName = "客户来源集合查询", methods = "selectList")
    @ApiOperation(value = "客户来源集合查询", httpMethod = "GET", notes = "客户来源集合查询", response = Json.class)
    @GetMapping("/selectList")
    public Json<List<CustomerSourceVO>> selectList(@Validated(value = CustomerSourceQuery.SelectList.class) CustomerSourceQuery customerSourceQuery) {
        //返回内容
        return new Json(ReturnCode.成功, customerSourceService.listEnhance(customerSourceQuery));
    }


    /**
     * TODO 单条
     *
     * @param customerSourceQuery 客户来源
     * @return Json<CustomerSourceVO>
     * @author lijh
     * @methodName selectOne
     * @time 2022-09-01 03:12:09
     */
    @Methods(methodsName = "客户来源单条查询", methods = "selectOne")
    @ApiOperation(value = "客户来源单条查询", httpMethod = "GET", notes = "客户来源单条查询", response = Json.class)
    @GetMapping("/selectOne")
    public Json<CustomerSourceVO> selectOne(@Validated(value = CustomerSourceQuery.SelectOne.class) CustomerSourceQuery customerSourceQuery) {
        //返回内容
        return new Json(ReturnCode.成功, customerSourceService.getOneEnhance(customerSourceQuery));
    }


    /**
     * TODO 总数
     *
     * @param customerSourceQuery 客户来源
     * @return Json<Integer>
     * @author lijh
     * @methodName count
     * @time 2022-09-01 03:12:09
     */
    @Methods(methodsName = "客户来源总数查询", methods = "count")
    @ApiOperation(value = "客户来源总数查询", httpMethod = "GET", notes = "客户来源总数查询", response = Json.class)
    @GetMapping(value = "/count")
    public Json<Integer> count(@Validated(value = CustomerSourceQuery.Count.class) CustomerSourceQuery customerSourceQuery) {
        //返回内容
        return new Json(ReturnCode.成功, customerSourceService.countEnhance(customerSourceQuery));
    }


    /**
     * TODO 新增
     *
     * @param customerSourceBO 客户来源
     * @return Json<String>
     * @author lijh
     * @methodName save
     * @time 2022-09-01 03:12:09
     */
    @PreventRepeat
    @Methods(methodsName = "客户来源新增", methods = "save")
    @ApiOperation(value = "客户来源新增", httpMethod = "POST", notes = "客户来源新增", response = Json.class)
    @ApiOperationSupport(ignoreParameters = {"id", "createDateTime", "createName", "modifyDateTime", "modifyName", "isDelete", "version"})
    @PostMapping("/save")
    public Json<String> save(@Validated(value = CustomerSourceBO.Save.class) CustomerSourceBO customerSourceBO, HttpServletRequest httpServletRequest) {
        //缓存取出用户
        Map<String, Object> u = RedisUtils.getToken(stringRedisTemplate, httpServletRequest);
        if (u != null) {
            customerSourceBO.setCreateName(u.get("name") + "-" + u.get("id"));
        }
        //返回内容
        return new Json(ReturnCode.成功, customerSourceService.saveEnhance(customerSourceBO));
    }


    /**
     * TODO 修改
     *
     * @param customerSourceBO 客户来源
     * @return Json<Boolean>
     * @author lijh
     * @methodName update
     * @time 2022-09-01 03:12:09
     */
    @PreventRepeat
    @Methods(methodsName = "客户来源修改", methods = "update")
    @ApiOperation(value = "客户来源修改", httpMethod = "PUT", notes = "客户来源修改", response = Json.class)
    @ApiOperationSupport(ignoreParameters = {"createDateTime", "createName", "modifyDateTime", "modifyName", "isDelete", "version"})
    @PutMapping("/update")
    public Json<Boolean> update(@Validated(value = CustomerSourceBO.Update.class) CustomerSourceBO customerSourceBO, HttpServletRequest httpServletRequest) {
        //缓存取出用户
        Map<String, Object> u = RedisUtils.getToken(stringRedisTemplate, httpServletRequest);
        if (u != null) {
            customerSourceBO.setModifyName(u.get("name") + "-" + u.get("id"));
        }
        //返回内容
        return new Json(ReturnCode.成功, customerSourceService.updateEnhance(customerSourceBO));
    }


    /**
     * TODO 删除
     *
     * @param customerSourceBO 客户来源
     * @return Json<Boolean>
     * @author lijh
     * @methodName remove
     * @time 2022-09-01 03:12:09
     */
    @Methods(methodsName = "客户来源删除", methods = "remove")
    @ApiOperation(value = "客户来源删除", httpMethod = "DELETE", notes = "客户来源删除", response = Json.class)
    @ApiOperationSupport(includeParameters = {"id"})
    @DeleteMapping("/remove")
    public Json<Boolean> remove(@Validated(value = CustomerSourceBO.Remove.class) CustomerSourceBO customerSourceBO) {
        return new Json(ReturnCode.成功, customerSourceService.removeEnhance(customerSourceBO));
    }
}