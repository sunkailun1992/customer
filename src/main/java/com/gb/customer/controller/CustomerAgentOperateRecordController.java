package com.gb.customer.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gb.customer.entity.bo.CustomerAgentOperateRecordBO;
import com.gb.customer.entity.query.CustomerAgentOperateRecordQuery;
import com.gb.customer.entity.vo.CustomerAgentOperateRecordVO;
import com.gb.customer.service.CustomerAgentOperateRecordService;
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
 * TODO 客户经纪人操作记录，Comment请求层
 * 代码生成器
 *
 * @author lijh
 * @className CustomerAgentOperateRecordController
 * @time 2022-09-20 11:02:25
 */
@Slf4j
@RequestRequired
@ApiSupport(author = "lijh")
@Setter(onMethod_ = {@Autowired})
@RestController
@RequestMapping("/customer-agent-operate-record")
@Api(tags = "客户经纪人操作记录")
public class CustomerAgentOperateRecordController {


    /**
     * 客户经纪人操作记录
     */
    private CustomerAgentOperateRecordService customerAgentOperateRecordService;
    /**
     * redis操作
     */
    private StringRedisTemplate stringRedisTemplate;


    /**
     * TODO 分页
     *
     * @param customerAgentOperateRecordQuery 客户经纪人操作记录
     * @param current
     * @param size
     * @return Json<Page<CustomerAgentOperateRecordVO>>
     * @author lijh
     * @methodName select
     * @time 2022-09-20 11:02:25
     */
    @Methods(methodsName = "客户经纪人操作记录集合分页查询", methods = "select")
    @ApiOperation(value = "客户经纪人操作记录集合分页查询", httpMethod = "GET", notes = "客户经纪人操作记录集合查询", response = Json.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", dataType = "int", required = true),
            @ApiImplicitParam(name = "size", value = "分页显示数量", dataType = "int", required = true),
    })
    @GetMapping("/select")
    public Json<Page<CustomerAgentOperateRecordVO>> select(@Validated(value = CustomerAgentOperateRecordQuery.Select.class) CustomerAgentOperateRecordQuery customerAgentOperateRecordQuery, Integer current, Integer size) {
        //返回内容
        return new Json(ReturnCode.成功, customerAgentOperateRecordService.pageEnhance(new Page(current, size), customerAgentOperateRecordQuery));
    }


    /**
     * TODO 集合
     *
     * @param customerAgentOperateRecordQuery 客户经纪人操作记录
     * @return Json<List<CustomerAgentOperateRecordVO>>
     * @author lijh
     * @methodName selectList
     * @time 2022-09-20 11:02:25
     */
    @Methods(methodsName = "客户经纪人操作记录集合查询", methods = "selectList")
    @ApiOperation(value = "客户经纪人操作记录集合查询", httpMethod = "GET", notes = "客户经纪人操作记录集合查询", response = Json.class)
    @GetMapping("/selectList")
    public Json<List<CustomerAgentOperateRecordVO>> selectList(@Validated(value = CustomerAgentOperateRecordQuery.SelectList.class) CustomerAgentOperateRecordQuery customerAgentOperateRecordQuery) {
        //返回内容
        return new Json(ReturnCode.成功, customerAgentOperateRecordService.listEnhance(customerAgentOperateRecordQuery));
    }


    /**
     * TODO 单条
     *
     * @param customerAgentOperateRecordQuery 客户经纪人操作记录
     * @return Json<CustomerAgentOperateRecordVO>
     * @author lijh
     * @methodName selectOne
     * @time 2022-09-20 11:02:25
     */
    @Methods(methodsName = "客户经纪人操作记录单条查询", methods = "selectOne")
    @ApiOperation(value = "客户经纪人操作记录单条查询", httpMethod = "GET", notes = "客户经纪人操作记录单条查询", response = Json.class)
    @GetMapping("/selectOne")
    public Json<CustomerAgentOperateRecordVO> selectOne(@Validated(value = CustomerAgentOperateRecordQuery.SelectOne.class) CustomerAgentOperateRecordQuery customerAgentOperateRecordQuery) {
        //返回内容
        return new Json(ReturnCode.成功, customerAgentOperateRecordService.getOneEnhance(customerAgentOperateRecordQuery));
    }


    /**
     * TODO 总数
     *
     * @param customerAgentOperateRecordQuery 客户经纪人操作记录
     * @return Json<Integer>
     * @author lijh
     * @methodName count
     * @time 2022-09-20 11:02:25
     */
    @Methods(methodsName = "客户经纪人操作记录总数查询", methods = "count")
    @ApiOperation(value = "客户经纪人操作记录总数查询", httpMethod = "GET", notes = "客户经纪人操作记录总数查询", response = Json.class)
    @GetMapping(value = "/count")
    public Json<Integer> count(@Validated(value = CustomerAgentOperateRecordQuery.Count.class) CustomerAgentOperateRecordQuery customerAgentOperateRecordQuery) {
        //返回内容
        return new Json(ReturnCode.成功, customerAgentOperateRecordService.countEnhance(customerAgentOperateRecordQuery));
    }


    /**
     * TODO 新增
     *
     * @param customerAgentOperateRecordBO 客户经纪人操作记录
     * @return Json<String>
     * @author lijh
     * @methodName save
     * @time 2022-09-20 11:02:25
     */
    @PreventRepeat
    @Methods(methodsName = "客户经纪人操作记录新增", methods = "save")
    @ApiOperation(value = "客户经纪人操作记录新增", httpMethod = "POST", notes = "客户经纪人操作记录新增", response = Json.class)
    @ApiOperationSupport(ignoreParameters = {"id", "createDateTime", "createName", "modifyDateTime", "modifyName", "isDelete", "version"})
    @PostMapping("/save")
    public Json<String> save(@Validated(value = CustomerAgentOperateRecordBO.Save.class) CustomerAgentOperateRecordBO customerAgentOperateRecordBO, HttpServletRequest httpServletRequest) {
        //缓存取出用户
        Map<String, Object> u = RedisUtils.getToken(stringRedisTemplate, httpServletRequest);
        if (u != null) {
            customerAgentOperateRecordBO.setCreateName(u.get("name") + "-" + u.get("id"));
        }
        //返回内容
        return new Json(ReturnCode.成功, customerAgentOperateRecordService.saveEnhance(customerAgentOperateRecordBO));
    }


    /**
     * TODO 修改
     *
     * @param customerAgentOperateRecordBO 客户经纪人操作记录
     * @return Json<Boolean>
     * @author lijh
     * @methodName update
     * @time 2022-09-20 11:02:25
     */
    @PreventRepeat
    @Methods(methodsName = "客户经纪人操作记录修改", methods = "update")
    @ApiOperation(value = "客户经纪人操作记录修改", httpMethod = "PUT", notes = "客户经纪人操作记录修改", response = Json.class)
    @ApiOperationSupport(ignoreParameters = {"createDateTime", "createName", "modifyDateTime", "modifyName", "isDelete", "version"})
    @PutMapping("/update")
    public Json<Boolean> update(@Validated(value = CustomerAgentOperateRecordBO.Update.class) CustomerAgentOperateRecordBO customerAgentOperateRecordBO, HttpServletRequest httpServletRequest) {
        //缓存取出用户
        Map<String, Object> u = RedisUtils.getToken(stringRedisTemplate, httpServletRequest);
        if (u != null) {
            customerAgentOperateRecordBO.setModifyName(u.get("name") + "-" + u.get("id"));
        }
        //返回内容
        return new Json(ReturnCode.成功, customerAgentOperateRecordService.updateEnhance(customerAgentOperateRecordBO));
    }


    /**
     * TODO 删除
     *
     * @param customerAgentOperateRecordBO 客户经纪人操作记录
     * @return Json<Boolean>
     * @author lijh
     * @methodName remove
     * @time 2022-09-20 11:02:25
     */
    @Methods(methodsName = "客户经纪人操作记录删除", methods = "remove")
    @ApiOperation(value = "客户经纪人操作记录删除", httpMethod = "DELETE", notes = "客户经纪人操作记录删除", response = Json.class)
    @ApiOperationSupport(includeParameters = {"id"})
    @DeleteMapping("/remove")
    public Json<Boolean> remove(@Validated(value = CustomerAgentOperateRecordBO.Remove.class) CustomerAgentOperateRecordBO customerAgentOperateRecordBO) {
        return new Json(ReturnCode.成功, customerAgentOperateRecordService.removeEnhance(customerAgentOperateRecordBO));
    }
}