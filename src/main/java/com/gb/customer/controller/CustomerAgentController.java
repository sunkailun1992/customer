package com.gb.customer.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gb.customer.dto.CustomerInfoReqDto;
import com.gb.customer.entity.CustomerAgent;
import com.gb.customer.entity.bo.CustomerAgentBO;
import com.gb.customer.entity.enums.CustomerAgentStateEnum;
import com.gb.customer.entity.enums.CustomerAgentTypeEnum;
import com.gb.customer.entity.query.CustomerAgentQuery;
import com.gb.customer.entity.vo.CustomerAgentVO;
import com.gb.customer.enums.CluesPortEnum;
import com.gb.customer.service.CustomerAgentService;
import com.gb.customer.service.impl.CheckPermissions;
import com.gb.utils.Json;
import com.gb.utils.RedisUtils;
import com.gb.utils.annotations.Methods;
import com.gb.utils.annotations.PreventRepeat;
import com.gb.utils.annotations.RequestRequired;
import com.gb.utils.enumeration.ReturnCode;
import com.gb.utils.exception.BusinessException;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.compress.utils.Lists;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * TODO 客户经纪人，Comment请求层
 * 代码生成器
 *
 * @author lijh
 * @className CustomerAgentController
 * @time 2022-08-31 09:35:12
 */
@Slf4j
@RequestRequired
@ApiSupport(author = "lijh")
@Setter(onMethod_ = {@Autowired})
@RestController
@RequestMapping("/customer-agent")
@Api(tags = "客户经纪人")
public class CustomerAgentController {


    /**
     * 客户经纪人
     */
    private CustomerAgentService customerAgentService;
    /**
     * redis操作
     */
    private StringRedisTemplate stringRedisTemplate;

    private CheckPermissions checkPermissions;


    /**
     * 客户经纪人集合分页查询
     *
     * @param customerAgentQuery 客户经纪人
     * @param current
     * @param size
     * @return Json<Page < CustomerAgentVO>>
     * @author lijh
     * @methodName select
     * @time 2022-08-31 09:35:12
     */
    @Methods(methodsName = "客户经纪人集合分页查询", methods = "select")
    @ApiOperation(value = "客户经纪人集合分页查询", httpMethod = "GET", notes = "客户经纪人集合查询", response = Json.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", dataType = "int", required = true),
            @ApiImplicitParam(name = "size", value = "分页显示数量", dataType = "int", required = true),
    })
    @GetMapping("/select")
    public Json<Page<CustomerAgentVO>> select(@Validated(value = CustomerAgentQuery.Select.class) CustomerAgentQuery customerAgentQuery, Integer current, Integer size) {
        //返回内容
        return new Json(ReturnCode.成功, customerAgentService.pageEnhance(new Page(current, size), customerAgentQuery));
    }

    /**
     * TODO 集合
     *
     * @param customerAgentQuery 客户经纪人
     * @return Json<List < CustomerAgentVO>>
     * @author lijh
     * @methodName selectList
     * @time 2022-08-31 09:35:12
     */
    @Methods(methodsName = "客户经纪人集合查询", methods = "selectList")
    @ApiOperation(value = "客户经纪人集合查询", httpMethod = "GET", notes = "客户经纪人集合查询", response = Json.class)
    @GetMapping("/selectList")
    public Json<List<CustomerAgentVO>> selectList(@Validated(value = CustomerAgentQuery.SelectList.class) CustomerAgentQuery customerAgentQuery) {
        //返回内容
        return new Json(ReturnCode.成功, customerAgentService.listEnhance(customerAgentQuery));
    }


    /**
     * TODO 单条
     *
     * @param customerAgentQuery 客户经纪人
     * @return Json<CustomerAgentVO>
     * @author lijh
     * @methodName selectOne
     * @time 2022-08-31 09:35:12
     */
    @Methods(methodsName = "客户经纪人单条查询", methods = "selectOne")
    @ApiOperation(value = "客户经纪人单条查询", httpMethod = "GET", notes = "客户经纪人单条查询", response = Json.class)
    @GetMapping("/selectOne")
    public Json<CustomerAgentVO> selectOne(@Validated(value = CustomerAgentQuery.SelectOne.class) CustomerAgentQuery customerAgentQuery) {
        //返回内容
        return new Json(ReturnCode.成功, customerAgentService.getOneEnhance(customerAgentQuery));
    }


    /**
     * TODO 总数
     *
     * @param customerAgentQuery 客户经纪人
     * @return Json<Integer>
     * @author lijh
     * @methodName count
     * @time 2022-08-31 09:35:12
     */
    @Methods(methodsName = "客户经纪人总数查询", methods = "count")
    @ApiOperation(value = "客户经纪人总数查询", httpMethod = "GET", notes = "客户经纪人总数查询", response = Json.class)
    @GetMapping(value = "/count")
    public Json<Integer> count(@Validated(value = CustomerAgentQuery.Count.class) CustomerAgentQuery customerAgentQuery) {
        //返回内容
        return new Json(ReturnCode.成功, customerAgentService.countEnhance(customerAgentQuery));
    }


    /**
     * TODO 新增
     *
     * @param customerAgentBO 客户经纪人
     * @return Json<String>
     * @author lijh
     * @methodName save
     * @time 2022-08-31 09:35:12
     */
    @PreventRepeat
    @Methods(methodsName = "客户经纪人新增", methods = "save")
    @ApiOperation(value = "客户经纪人新增", httpMethod = "POST", notes = "客户经纪人新增", response = Json.class)
    @ApiOperationSupport(ignoreParameters = {"id", "createDateTime", "createName", "modifyDateTime", "modifyName", "isDelete", "version"})
    @PostMapping("/save")
    public Json<String> save(@RequestBody @Validated(value = CustomerAgentBO.Save.class) CustomerAgentBO customerAgentBO, HttpServletRequest httpServletRequest) {
        //缓存取出用户
        Map<String, Object> u = RedisUtils.getToken(stringRedisTemplate, httpServletRequest);
        if (u != null) {
            customerAgentBO.setCreateName(u.get("name") + "-" + u.get("id"));
        }
        //返回内容
        return new Json(ReturnCode.成功, customerAgentService.saveEnhance(customerAgentBO));
    }


    /**
     * TODO 修改
     *
     * @param customerAgentBO 客户经纪人
     * @return Json<Boolean>
     * @author lijh
     * @methodName update
     * @time 2022-08-31 09:35:12
     */
    @PreventRepeat
    @Methods(methodsName = "客户经纪人修改", methods = "update")
    @ApiOperation(value = "客户经纪人修改", httpMethod = "PUT", notes = "客户经纪人修改", response = Json.class)
    @ApiOperationSupport(ignoreParameters = {"createDateTime", "createName", "modifyDateTime", "modifyName", "isDelete", "version"})
    @PutMapping("/update")
    public Json<Boolean> update(@RequestBody @Validated(value = CustomerAgentBO.Update.class) CustomerAgentBO customerAgentBO, HttpServletRequest httpServletRequest) {
        //缓存取出用户
        Map<String, Object> u = RedisUtils.getToken(stringRedisTemplate, httpServletRequest);
        if (u != null) {
            customerAgentBO.setModifyName(u.get("name") + "-" + u.get("id"));
        }
        //返回内容
        return new Json(ReturnCode.成功, customerAgentService.updateEnhance(customerAgentBO));
    }

    /**
     * 客户经纪人备注编辑或保存
     *
     * @param customerAgentBO
     * @return Json<Boolean>
     * @author lijh
     * @methodName update
     * @time 2022-08-31 09:35:12
     */
    @PreventRepeat
    @Methods(methodsName = "客户经纪人备注编辑或保存", methods = "saveOrEdit")
    @ApiOperation(value = "客户经纪人备注编辑或保存", httpMethod = "POST", notes = "客户经纪人备注编辑或保存", response = Json.class)
    @ApiOperationSupport(ignoreParameters = {"createDateTime", "createName", "modifyDateTime", "modifyName", "isDelete", "version"})
    @PostMapping("/saveOrEdit")
    public Json<Boolean> saveOrEdit(@RequestBody @Validated(value = CustomerAgentBO.Save.class) CustomerAgentBO customerAgentBO, HttpServletRequest httpServletRequest) {
        log.debug("备注编辑或修改->>>customerAgentBO:{}", customerAgentBO);
        //缓存取出用户
        Map<String, Object> u = RedisUtils.getToken(stringRedisTemplate, httpServletRequest);
        if (u != null) {
            customerAgentBO.setModifyName(u.get("name") + "-" + u.get("id"));
            customerAgentBO.setCreateName(u.get("name") + "-" + u.get("id"));
        }
        if (StringUtils.isEmpty(customerAgentBO.getAgentUserId()) || StringUtils.isEmpty(customerAgentBO.getCustomerId())) {
            throw new BusinessException("缺少必传参数");
        }
        customerAgentBO.setType(CustomerAgentTypeEnum.无);
        customerAgentBO.setState(CustomerAgentStateEnum.无);
        //返回内容
        return new Json(ReturnCode.成功, customerAgentService.saveOrEdit(customerAgentBO));
    }


    /**
     * TODO 删除
     *
     * @param customerAgentBO 客户经纪人
     * @return Json<Boolean>
     * @author lijh
     * @methodName remove
     * @time 2022-08-31 09:35:12
     */
    @Methods(methodsName = "客户经纪人删除", methods = "remove")
    @ApiOperation(value = "客户经纪人删除", httpMethod = "DELETE", notes = "客户经纪人删除", response = Json.class)
    @ApiOperationSupport(includeParameters = {"id"})
    @DeleteMapping("/remove")
    public Json<Boolean> remove(@Validated(value = CustomerAgentBO.Remove.class) CustomerAgentBO customerAgentBO) {
        return new Json(ReturnCode.成功, customerAgentService.removeEnhance(customerAgentBO));
    }

    /**
     * 分配客户经纪人
     *
     * @param customerAgentBO
     * @param httpServletRequest
     * @return
     */
    @PreventRepeat
    @Methods(methodsName = "分配客户经纪人", methods = "save")
    @ApiOperation(value = "分配客户经纪人", httpMethod = "POST", notes = "分配客户经纪人", response = Json.class)
    @PostMapping("/allotCustomerAgent")
    public Json<String> allotCustomerAgent(@RequestBody CustomerAgentBO customerAgentBO, HttpServletRequest httpServletRequest) {
        //缓存取出用户
        Map<String, Object> u = RedisUtils.getToken(stringRedisTemplate, httpServletRequest);
        if (null == u) {
            throw new BusinessException("token过期，请重新登录");
        }
        customerAgentBO.setCreateName(u.get("name") + "-" + u.get("id"));
        customerAgentBO.setLoginAccountId(u.get("id").toString());
        log.info("分配客户经纪人 ->>>  token: {}", u);
        return new Json(ReturnCode.成功, customerAgentService.allotCustomerAgent(customerAgentBO));
    }

    @PreventRepeat
    @Methods(methodsName = "批量分配客户经纪人", methods = "batchAllotCustomerAgent")
    @ApiOperation(value = "批量分配客户经纪人", httpMethod = "POST", notes = "批量分配客户经纪人", response = Json.class)
    @PostMapping("/batchAllotCustomerAgent")
    public Json<String> batchAllotCustomerAgent(@RequestBody List<CustomerAgent> customerAgentList, HttpServletRequest httpServletRequest) {
        //缓存取出用户
        Map<String, Object> u = RedisUtils.getToken(stringRedisTemplate, httpServletRequest);
        if (null == u) {
            throw new BusinessException("token过期，请重新登录");
        }
        log.info("批量分配客户经纪人 ->>>  操作人: {},customerAgentList: {}", u.get("id"), customerAgentList);
        if (CollectionUtils.isEmpty(customerAgentList)) {
            throw new BusinessException("缺少必要参数");
        }
        customerAgentList.forEach(customerAgent -> {
            customerAgent.setModifyName(u.get("name") + "-" + u.get("id"));
            customerAgent.setCreateName(u.get("name") + "-" + u.get("id"));
            customerAgent.setLoginAccountId(u.get("id").toString());
        });
        return new Json(ReturnCode.成功, customerAgentService.batchAllotCustomerAgent(customerAgentList));
    }

    /**
     * 分销客户列表分页查询¬
     *
     * @param current            1
     * @param size               10
     * @param customerAgentQuery
     * @param httpServletRequest
     * @return
     */
    @Methods(methodsName = "分销客户列表", methods = "selectDistributionList")
    @ApiOperation(value = "分销客户列表", httpMethod = "GET", notes = "分销客户列表", response = Json.class)
    @ApiImplicitParams({@ApiImplicitParam(name = "current", value = "当前页", dataType = "int"), @ApiImplicitParam(name = "size", value = "分页显示数量", dataType = "int"),})
    @GetMapping("/selectDistributionList")
    public Json<Page<CustomerAgentVO>> selectDistributionList(@Validated(value = CustomerAgentQuery.Select.class) CustomerAgentQuery customerAgentQuery, Integer current, Integer size, HttpServletRequest httpServletRequest) {
        //缓存取出用户
        Map<String, Object> u = RedisUtils.getToken(stringRedisTemplate, httpServletRequest);
        if (null == u) {
            throw new BusinessException("token过期，请重新登录");
        }
        //当前操作账号可查看的数据权限
        List<String> strings = checkPermissions.getAgentTeam((String) u.get("id"));
        customerAgentQuery.setAgentUserIds(strings);
        customerAgentQuery.setCustomerListType(CluesPortEnum.分销列表.getCode());
        //此处按照 客户 的更新时间倒叙。自定义字段别名 customer_modify_date_time  非CustomerAgent表中的更新时间
        customerAgentQuery.setCollationFields("customer_modify_date_time");
        return new Json(ReturnCode.成功, customerAgentService.pageEnhance(new Page(current, size), customerAgentQuery));
    }

    /**
     * 自营客户列表分页查询
     *
     * @param current            1
     * @param size               10
     * @param customerAgentQuery
     * @param httpServletRequest
     * @return
     */
    @Methods(methodsName = "自营客户列表分页查询", methods = "selectProprietaryList")
    @ApiOperation(value = "自营客户列表分页查询", httpMethod = "GET", notes = "自营客户列表分页查询", response = Json.class)
    @ApiImplicitParams({@ApiImplicitParam(name = "current", value = "当前页", dataType = "int"), @ApiImplicitParam(name = "size", value = "分页显示数量", dataType = "int"),})
    @GetMapping("/selectProprietaryList")
    public Json<Page<CustomerAgentVO>> selectProprietaryList(@Validated(value = CustomerAgentQuery.Select.class) CustomerAgentQuery customerAgentQuery, Integer current, Integer size, HttpServletRequest httpServletRequest) {
        //缓存取出用户
        Map<String, Object> u = RedisUtils.getToken(stringRedisTemplate, httpServletRequest);
        if (null == u) {
            throw new BusinessException("token过期，请重新登录");
        }
        //当前操作账号可查看的数据权限
        List<String> strings = checkPermissions.getAgentTeam((String) u.get("id"));
        customerAgentQuery.setAgentUserIds(strings);
        customerAgentQuery.setCustomerListType(CluesPortEnum.自营列表.getCode());
        //按照更新时间倒叙
        customerAgentQuery.setCollationFields("customer_modify_date_time");
        return new Json(ReturnCode.成功, customerAgentService.pageEnhance(new Page(current, size), customerAgentQuery));
    }

    @PreventRepeat
    @Methods(methodsName = "转交经纪人", methods = "transferAgent")
    @ApiOperation(value = "转交经纪人", httpMethod = "POST", notes = "客户经纪人修改", response = Json.class)
    @ApiOperationSupport(ignoreParameters = {"createDateTime", "createName", "modifyDateTime", "modifyName", "isDelete", "version"})
    @PostMapping("/transferAgent")
    public Json<Boolean> transferAgent(@RequestBody CustomerAgentBO customerAgentBO, HttpServletRequest httpServletRequest) {
        //缓存取出用户
        Map<String, Object> u = RedisUtils.getToken(stringRedisTemplate, httpServletRequest);
        if (null == u) {
            throw new BusinessException("token过期，请重新登录");
        }
        log.info("转交经纪人 ->>>  操作人: {},customerAgentBO: {}", u.get("id"), customerAgentBO);
        customerAgentBO.setModifyName(u.get("name") + "-" + u.get("id"));
        customerAgentBO.setCreateName(u.get("name") + "-" + u.get("id"));
        //返回内容
        return new Json(ReturnCode.成功, customerAgentService.transferAgent(customerAgentBO));
    }

    @PreventRepeat
    @Methods(methodsName = "批量移入公海", methods = "batchAllotUntie")
    @ApiOperation(value = "批量移入公海", httpMethod = "POST", notes = "批量移入公海", response = Json.class)
    @PostMapping("/batchAllotUntie")
    public Json<String> batchAllotUntie(@RequestBody List<CustomerAgent> customerAgentList, HttpServletRequest httpServletRequest) {
        //缓存取出用户
        Map<String, Object> u = RedisUtils.getToken(stringRedisTemplate, httpServletRequest);
        if (null == u) {
            throw new BusinessException("token过期，请重新登录");
        }
        log.info("批量移入公海 ->>>  操作人: {},customerAgentList: {}", u.get("id"), customerAgentList);
        if (CollectionUtils.isEmpty(customerAgentList)) {
            throw new BusinessException("缺少必要参数");
        }
        customerAgentList.forEach(customerAgent -> {
            customerAgent.setModifyName(u.get("name") + "-" + u.get("id"));
            customerAgent.setCreateName(u.get("name") + "-" + u.get("id"));
        });
        return new Json(ReturnCode.成功, customerAgentService.batchAllotUntie(customerAgentList));
    }

    @PreventRepeat
    @Methods(methodsName = "移入公海", methods = "untieAgent")
    @ApiOperation(value = "移入公海", httpMethod = "POST", notes = "移入公海", response = Json.class)
    @ApiOperationSupport(ignoreParameters = {"createDateTime", "createName", "modifyDateTime", "modifyName", "isDelete", "version"})
    @PostMapping("/untieAgent")
    public Json<Boolean> untieAgent(@RequestBody CustomerAgent customerAgent, HttpServletRequest httpServletRequest) {
        //缓存取出用户
        Map<String, Object> u = RedisUtils.getToken(stringRedisTemplate, httpServletRequest);
        if (null == u) {
            throw new BusinessException("token过期，请重新登录");
        }
        customerAgent.setModifyName(u.get("name") + "-" + u.get("id"));
        customerAgent.setCreateName(u.get("name") + "-" + u.get("id"));
        List<CustomerAgent> customerAgentList = Lists.newArrayList();
        customerAgentList.add(customerAgent);
        log.info("移入公海 ->>>  操作人: {},customerAgentList: {}", u.get("id"), customerAgentList);
        //返回内容
        return new Json(ReturnCode.成功, customerAgentService.batchAllotUntie(customerAgentList));
    }

    @Methods(methodsName = "自营经纪人下所有客户", methods = "selectCustomerByAgentUserId")
    @ApiOperation(value = "自营经纪人下所有客户", httpMethod = "GET", notes = "自营经纪人下所有客户", response = Json.class)
    @GetMapping("/selectCustomerByAgentUserId")
    public Json<CustomerAgentVO> selectCustomerByAgentUserId(@Validated(value = CustomerAgentQuery.SelectOne.class) CustomerAgentQuery customerAgentQuery, Integer current, Integer size) {
        //返回内容
        return new Json(ReturnCode.成功, customerAgentService.selectCustomerByAgentUserId(new Page(current, size), customerAgentQuery));
    }


    @Methods(methodsName = "根据用户id获取客户信息", methods = "getCustomerIdByUserId")
    @ApiOperation(value = "根据用户id获取客户信息", httpMethod = "POST", notes = "根据用户id获取客户信息", response = Json.class)
    @PostMapping("/getCustomerIdByUserId")
    public Json<String> getCustomerIdByUserId(@RequestBody List<CustomerInfoReqDto> customerInfoReqDtoList) {
        log.info("根据用户id获取客户信息 ->>>  customerInfoReqDtoList: {}", customerInfoReqDtoList);
        if (CollectionUtils.isEmpty(customerInfoReqDtoList)) {
            throw new BusinessException("缺少必要参数");
        }
        return new Json(ReturnCode.成功, customerAgentService.getCustomerIdByUserId(customerInfoReqDtoList));
    }
}