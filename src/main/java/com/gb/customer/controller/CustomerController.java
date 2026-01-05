package com.gb.customer.controller;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.Assert;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gb.customer.dto.CustomerLabelDto;
import com.gb.customer.dto.OfflineCastInsuranceCustomerDto;
import com.gb.customer.entity.Customer;
import com.gb.customer.enums.CluesPortEnum;
import com.gb.customer.enums.DataPermissionsEnum;
import com.gb.customer.service.AsyncSendService;
import com.gb.customer.service.CustomerService;
import com.gb.customer.service.impl.CheckPermissions;
import com.gb.rpc.entity.UserExtendsVO;
import com.gb.rpc.entity.enums.RpcTypeEnum;
import com.gb.rpc.impl.RpcComponent;
import com.gb.utils.Json;
import com.gb.utils.RedisUtils;
import com.gb.utils.annotations.Methods;
import com.gb.utils.annotations.PreventRepeat;
import com.gb.utils.annotations.RequestRequired;
import com.gb.utils.enumeration.ReturnCode;
import com.gb.utils.exception.BusinessException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 客户表 前端控制器
 * </p>
 *
 * @author lijh
 * @since 2021-06-08
 */
@Slf4j
@RequestRequired
@RestController
@Setter(onMethod_ = {@Autowired})
@RequestMapping("/customer")
@Api(tags = "客户表")
public class CustomerController {

    /**
     * 客户表
     */
    private CustomerService customerService;
    /**
     * redis操作
     */
    private StringRedisTemplate stringRedisTemplate;

    private CheckPermissions checkPermissions;

    private RpcComponent rpcComponent;

    private AsyncSendService asyncSendService;


    /**
     * 客户表集合查询
     *
     * @param current:
     * @param size:
     * @param customer:
     * @return com.utils.Json
     * @author lijh
     * @since 2021-06-08
     */
    @Methods(methodsName = "客户表集合查询", methods = "select")
    @ApiOperation(value = "客户表集合查询", httpMethod = "GET", notes = "客户表集合查询", response = Json.class)
    @ApiImplicitParams({@ApiImplicitParam(name = "current", value = "当前页", dataType = "int"), @ApiImplicitParam(name = "size", value = "分页显示数量", dataType = "int"),})
    @GetMapping("/select")
    public Json<IPage<Customer>> select(Integer current, Integer size, Customer customer, HttpServletRequest httpServletRequest) {
        //缓存取出用户
        Map<String, Object> u = RedisUtils.getToken(stringRedisTemplate, httpServletRequest);
        if (null == u) {
            throw new BusinessException("token过期，请重新登录");
        }
        List<String> strings = checkPermissions.checkUserPermissions(u);
        //非指定管家，默认展示当前账号下数据
        if (checkPermissions.checkDataPermissions(DataPermissionsEnum.CUSTOMER_SELECT, strings)) {
            customer.setUserIdQuery((String) u.get("id"));
        }
        if (current != null && size != null) {
            Page page = new Page(current, size);
            //返回内容
            return new Json(ReturnCode.成功, customerService.pageEnhance(page, customer));
        } else {
            //返回内容
            return new Json(ReturnCode.成功, customerService.listEnhance(customer));
        }
    }

    /**
     * 公海客户列表
     *
     * @param current
     * @param size
     * @param customer
     * @param httpServletRequest
     * @return
     */
    @Methods(methodsName = "公海客户分页查询", methods = "selectHighSeas")
    @ApiOperation(value = "公海客户分页查询", httpMethod = "GET", notes = "公海客户分页查询", response = Json.class)
    @ApiImplicitParams({@ApiImplicitParam(name = "current", value = "当前页", dataType = "int"), @ApiImplicitParam(name = "size", value = "分页显示数量", dataType = "int"),})
    @GetMapping("/selectHighSeas")
    public Json<IPage<Customer>> selectHighSeas(Integer current, Integer size, Customer customer, HttpServletRequest httpServletRequest) {
        //缓存取出用户
        Map<String, Object> u = RedisUtils.getToken(stringRedisTemplate, httpServletRequest);
        if (null == u) {
            throw new BusinessException("token过期，请重新登录");
        }
        //当前操作人id
        customer.setLoginAccountId((String) u.get("id"));
        customer.setCustomerListType(CluesPortEnum.公海列表.getCode());
        //按照更新时间倒叙
        customer.setCollationFields("modify_date_time");
        if (current != null && size != null) {
            Page page = new Page(current, size);
            //返回内容
            return new Json(ReturnCode.成功, customerService.pageEnhance(page, customer));
        } else {
            //返回内容
            return new Json(ReturnCode.成功, customerService.listEnhance(customer));
        }
    }

    /**
     * 获取客户详情
     *
     * @param customerId: 客户id
     * @return com.utils.Json
     * @author lijh
     * @since 2021-06-08
     */
    @Methods(methodsName = "获取客户详情", methods = "selectOneCustomer")
    @ApiOperation(value = "获取客户详情", httpMethod = "GET", notes = "获取客户详情", response = Json.class)
    @GetMapping("/selectOneCustomer")
    public Json<Customer> selectOneCustomer(String customerId, HttpServletRequest httpServletRequest) {
        //缓存取出用户
        Map<String, Object> u = RedisUtils.getToken(stringRedisTemplate, httpServletRequest);
        if (null == u) {
            throw new BusinessException("token过期，请重新登录");
        }
        return new Json(ReturnCode.成功, customerService.selectOneCustomer(customerId, (String) u.get("id")));
    }

    /**
     * 获取客户详情头部信息
     *
     * @param customerId: 客户id
     * @return com.utils.Json
     * @author lijh
     * @since 2021-06-08
     */
    @Methods(methodsName = "获取客户详情头部信息", methods = "selectOneCustomerInfoHead")
    @ApiOperation(value = "获取客户详情头部信息", httpMethod = "GET", notes = "获取客户详情头部信息", response = Json.class)
    @GetMapping("/selectOneCustomerInfoHead")
    public Json<Customer> selectOneCustomerInfoHead(String customerId, String agentUserId, HttpServletRequest httpServletRequest) {
        //缓存取出用户
        Map<String, Object> u = RedisUtils.getToken(stringRedisTemplate, httpServletRequest);
        if (null == u) {
            throw new BusinessException("token过期，请重新登录");
        }
        return new Json(ReturnCode.成功, customerService.selectOneCustomerInfoHead(customerId, agentUserId, "147604394"));
    }


    /**
     * 客户表单条查询
     *
     * @param customer:
     * @return com.utils.Json
     * @author lijh
     * @since 2021-06-08
     */
    @Methods(methodsName = "客户表单条查询", methods = "selectOne")
    @ApiOperation(value = "客户表单条查询", httpMethod = "GET", notes = "客户表单条查询", response = Json.class)
    @GetMapping("/selectOne")
    public Json<Customer> selectOne(Customer customer) {
        log.info("[查询单个客户]请求参数={}", customer);
        Map<String, Object> map = new HashMap<>(1);
        map.put("userId", customer.getUserId());
        UserExtendsVO userExtendsVO = rpcComponent.rpcQueryInfo(map, RpcTypeEnum.GET_USER_EXTENDS_ONE, UserExtendsVO.class);
        customer.setMobile(Convert.toStr(userExtendsVO.getMobile()));
        //返回内容
        return new Json(ReturnCode.成功, customerService.getOneEnhance(customer));
    }


    /**
     * 客户表总数查询
     *
     * @param customer:
     * @return com.utils.Json
     * @author lijh
     * @since 2021-06-08
     */
    @Methods(methodsName = "客户表总数查询", methods = "count")
    @ApiOperation(value = "客户表总数查询", httpMethod = "GET", notes = "客户表总数查询", response = Json.class)
    @GetMapping(value = "/count")
    public Json<Integer> count(Customer customer) {
        //返回内容
        return new Json(ReturnCode.成功, customerService.countEnhance(customer));
    }


    /**
     * 公海客户表新增
     *
     * @param customer:
     * @return com.utils.Json
     * @author lijh
     * @since 2021-06-08
     */
    @PreventRepeat
    @Methods(methodsName = "公海客户表新增", methods = "save")
    @ApiOperation(value = "公海客户表新增", httpMethod = "POST", notes = "客户表新增", response = Json.class)
    @PostMapping("/save")
    public Json<String> save(@RequestBody Customer customer, HttpServletRequest httpServletRequest) {
        //缓存取出用户
        Map<String, Object> u = RedisUtils.getToken(stringRedisTemplate, httpServletRequest);
        if (null == u) {
            throw new BusinessException("token过期，请重新登录");
        }
        log.debug("公海客户新增 ->>> customer={}", customer);
        customer.setCreateName(u.get("name") + "-" + u.get("id"));
        customer.setLoginAccountId((String) u.get("id"));
        Boolean b = customerService.saveEnhance(customer);
        if (b) {
            //返回内容
            return new Json(ReturnCode.成功, customer.getId());
        } else {
            //返回内容
            return new Json(ReturnCode.系统执行出错, "新增参数数量为空");
        }

    }

    @PreventRepeat
    @Methods(methodsName = "分销客户新增", methods = "saveDistributionCustomer")
    @ApiOperation(value = "分销客户新增", httpMethod = "POST", notes = "分销客户新增", response = Json.class)
    @PostMapping("/saveDistributionCustomer")
    public Json<String> saveDistributionCustomer(@RequestBody Customer customer, HttpServletRequest httpServletRequest) {
        //缓存取出用户
        Map<String, Object> u = RedisUtils.getToken(stringRedisTemplate, httpServletRequest);
        if (null == u) {
            throw new BusinessException("token过期，请重新登录");
        }
        log.debug("分销客户新增 ->>> customer={}", customer);
        customer.setCreateName(u.get("name") + "-" + u.get("id"));
        customer.setLoginAccountId((String) u.get("id"));
        customer.setAgentUserIdQuery((String) u.get("id"));
        Boolean b = customerService.saveDistributionCustomer(customer);
        if (b) {
            //返回内容
            return new Json(ReturnCode.成功, customer.getId());
        } else {
            //返回内容
            return new Json(ReturnCode.系统执行出错, "新增参数数量为空");
        }

    }


    /**
     * TODO 自营客户导入
     *
     * @param file
     * @param httpServletRequest
     * @return com.gb.utils.Json<java.lang.String>
     * @author 孙凯伦
     * @methodName importProprietaryCustomer
     * @time 2023/8/4 11:26
     */
    @PreventRepeat
    @Methods(methodsName = "分销客户导入", methods = "importDistributionCustomer")
    @ApiOperation(value = "分销客户导入", httpMethod = "POST", notes = "分销客户导入", response = Json.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file", value = "文件流对象,接收数组格式", required = true, dataType = "__File")
    })
    @PostMapping("/importDistributionCustomer")
    public Json<String> importDistributionCustomer(@RequestParam("file") MultipartFile file, HttpServletRequest httpServletRequest) throws IOException {
        //缓存取出用户
        Map<String, Object> u = RedisUtils.getToken(stringRedisTemplate, httpServletRequest);
        if (null == u) {
            throw new BusinessException("token过期，请重新登录");
        }
        //返回内容
        return new Json(ReturnCode.成功, customerService.importDistributionCustomer(file, httpServletRequest, u));
    }


    @PreventRepeat
    @Methods(methodsName = "自营客户新增", methods = "saveProprietaryCustomer")
    @ApiOperation(value = "自营客户新增", httpMethod = "POST", notes = "自营客户新增", response = Json.class)
    @PostMapping("/saveProprietaryCustomer")
    public Json<String> saveProprietaryCustomer(@RequestBody Customer customer, HttpServletRequest httpServletRequest) {
        //缓存取出用户
        Map<String, Object> u = RedisUtils.getToken(stringRedisTemplate, httpServletRequest);
        if (null == u) {
            throw new BusinessException("token过期，请重新登录");
        }
        log.debug("自营客户新增 ->>> customer={}", customer);
        customer.setCreateName(u.get("name") + "-" + u.get("id"));
        customer.setLoginAccountId((String) u.get("id"));
        customer.setAgentUserIdQuery((String) u.get("id"));
        Boolean b = customerService.saveProprietaryCustomer(customer);
        if (b) {
            //返回内容
            return new Json(ReturnCode.成功, customer.getId());
        } else {
            //返回内容
            return new Json(ReturnCode.系统执行出错, "新增参数数量为空");
        }

    }


    /**
     * TODO 自营客户导入
     *
     * @param file
     * @param httpServletRequest
     * @return com.gb.utils.Json<java.lang.String>
     * @author 孙凯伦
     * @methodName importProprietaryCustomer
     * @time 2023/8/4 11:26
     */
    @PreventRepeat
    @Methods(methodsName = "自营客户导入", methods = "importProprietaryCustomer")
    @ApiOperation(value = "自营客户导入", httpMethod = "POST", notes = "自营客户导入", response = Json.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file", value = "文件流对象,接收数组格式", required = true, dataType = "__File")
    })
    @PostMapping("/importProprietaryCustomer")
    public Json<String> importProprietaryCustomer(@RequestParam("file") MultipartFile file, HttpServletRequest httpServletRequest) throws IOException {
        //缓存取出用户
        Map<String, Object> u = RedisUtils.getToken(stringRedisTemplate, httpServletRequest);
        if (null == u) {
            throw new BusinessException("token过期，请重新登录");
        }
        //返回内容
        return new Json(ReturnCode.成功, customerService.importProprietaryCustomer(file, httpServletRequest, u));
    }


    /**
     * 客户表修改
     *
     * @param customer:
     * @return com.utils.Json
     * @author lijh
     * @since 2021-06-08
     */
    @PreventRepeat
    @Methods(methodsName = "客户表修改", methods = "update")
    @ApiOperation(value = "客户表修改", httpMethod = "PUT", notes = "客户表修改", response = Json.class)
    @PutMapping("/update")
    public Json<Boolean> update(Customer customer, HttpServletRequest httpServletRequest) {
        //缓存取出用户
        Map<String, Object> u = RedisUtils.getToken(stringRedisTemplate, httpServletRequest);
        if (u != null) {
            customer.setModifyName(u.get("name") + "-" + u.get("id"));
        }
        //返回内容
        return new Json(ReturnCode.成功, customerService.updateEnhance(customer));
    }


    /**
     * 客户表删除
     *
     * @param customer:
     * @return com.utils.Json
     * @author lijh
     * @since 2021-06-08
     */
    @Methods(methodsName = "客户表删除", methods = "remove")
    @ApiOperation(value = "客户表删除", httpMethod = "DELETE", notes = "客户表删除", response = Json.class)
    @DeleteMapping("/remove")
    public Json<Boolean> remove(Customer customer) {
        return new Json(ReturnCode.成功, customerService.removeEnhance(customer));
    }

    /**
     * 获取客户绑定的经纪人
     *
     * @param customer:
     * @return com.utils.Json
     * @author lijh
     * @since 2021-06-08
     */
    @Methods(methodsName = "获取客户绑定的经纪人", methods = "selectAgentUser")
    @ApiOperation(value = "获取客户绑定的经纪人", httpMethod = "GET", notes = "获取客户绑定的经纪人", response = Json.class)
    @GetMapping("/selectAgentUser")
    public Json<Customer> selectAgentUser(Customer customer) {
        String userId = customer.getUserId();
        log.info("查询客户绑定的经纪人 ->>>  userId={}", userId);
        Map<String, Object> map = new HashMap<>(1);
        map.put("userId", userId);
        UserExtendsVO userExtendsVO = rpcComponent.rpcQueryInfo(map, RpcTypeEnum.GET_USER_EXTENDS_ONE, UserExtendsVO.class);
        //返回内容
        return new Json(ReturnCode.成功, customerService.selectAgentUser(userId, userExtendsVO.getMobile()));
    }

    /**
     * 获取客户分配的经纪人
     *
     * @param customer: 用户id : userId       经纪人id : agentUserIdQuery
     * @return com.utils.Json
     * @author lijh
     * @since 2021-06-08
     */
    @Methods(methodsName = "获取客户分配的经纪人", methods = "selectBoundAgentUser")
    @ApiOperation(value = "获取客户分配的经纪人", httpMethod = "GET", notes = "获取客户分配的经纪人", response = Json.class)
    @GetMapping("/selectBoundAgentUser")
    public Json<Customer> selectBoundAgentUser(Customer customer) {
        log.info("获取客户分配的经纪人 ->>> userId={}， agentUserId={}", customer.getUserId(), customer.getAgentUserIdQuery());
        //返回内容
        return new Json(ReturnCode.成功, customerService.selectBoundAgentUser(customer));
    }


    @Methods(methodsName = "经纪人工作台", methods = "agentWorkbench")
    @ApiOperation(value = "经纪人工作台", httpMethod = "GET", notes = "经纪人工作台", response = Json.class)
    @GetMapping("/agentWorkbench")
    public Json<Customer> agentWorkbench(String agentUserId, HttpServletRequest httpServletRequest) {
        //缓存取出用户
        Map<String, Object> u = RedisUtils.getToken(stringRedisTemplate, httpServletRequest);
        if (null == u) {
            throw new BusinessException("token过期，请重新登录");
        }
        //返回内容
        return new Json(ReturnCode.成功, customerService.agentWorkbench((String) u.get("id")));
    }

    @Methods(methodsName = "更改用户标签", methods = "updateCustomerLabel")
    @ApiOperation(value = "更改用户标签", httpMethod = "POST", notes = "更改用户标签", response = Json.class)
    @PostMapping("/updateCustomerLabel")
    public Json<String> updateCustomerLabel(@RequestBody List<CustomerLabelDto> customerLabelDtoList) {
        log.debug("更改用户标签 ->>> customerLabelDtoList={}", customerLabelDtoList);
        if (CollectionUtils.isEmpty(customerLabelDtoList)) {
            return new Json(ReturnCode.成功, null);
        }
        customerService.updateCustomerLabel(customerLabelDtoList, false, false);
        //返回内容
        return new Json(ReturnCode.成功, null);
    }


    @Methods(methodsName = "批量更改用户标签", methods = "batchUpdateCustomerLabel")
    @ApiOperation(value = "批量更改用户标签", httpMethod = "POST", notes = "批量更改用户标签", response = Json.class)
    @PostMapping("/batchUpdateCustomerLabel")
    public Json<String> batchUpdateCustomerLabel(@RequestBody List<CustomerLabelDto> customerLabelDtoList, HttpServletRequest request) {
        log.debug("更改用户标签 ->>> customerLabelDtoList={}", customerLabelDtoList);
        if (StringUtils.isNotBlank(request.getHeader("clean"))) {
            customerService.updateCustomerLabel(customerLabelDtoList, true, true);
        } else {
            if (CollectionUtils.isEmpty(customerLabelDtoList)) {
                return new Json(ReturnCode.成功, null);
            }
            customerService.updateCustomerLabel(customerLabelDtoList, true, false);
        }
        //返回内容
        return new Json(ReturnCode.成功, null);
    }

    @Methods(methodsName = "生成客户", methods = "generateCustomer")
    @ApiOperation(value = "生成客户", httpMethod = "GET", notes = "生成客户", response = Json.class)
    @GetMapping("/generateCustomer")
    public Json<Customer> generateCustomer(String potentialCustomerId) {
        customerService.generateCustomer(potentialCustomerId);
        //返回内容
        return new Json(ReturnCode.成功);
    }

    @Methods(methodsName = "线下单转化客户", methods = "transformationOfflineCastInsuranceCustomer")
    @ApiOperation(value = "线下单转化客户", httpMethod = "GET", notes = "线下单转化客户", response = Json.class)
    @PostMapping("/transformationOfflineCastInsuranceCustomer")
    public Json transformationOfflineCastInsuranceCustomer(@RequestBody List<OfflineCastInsuranceCustomerDto> offlineCastInsuranceCustomerDto) {
        asyncSendService.transformationOfflineCastInsuranceCustomer(offlineCastInsuranceCustomerDto);
        //返回内容
        return new Json(ReturnCode.成功);
    }

    /************************               清洗客户相关数据           *******************************************/

    @Methods(methodsName = "清洗客户", methods = "cleaningCustomer")
    @ApiOperation(value = "清洗客户", httpMethod = "GET", notes = "清洗客户", response = Json.class)
    @GetMapping("/cleaningCustomer")
    public Json<Customer> cleaningCustomer(String sourceId) {
        //返回内容
        return new Json(ReturnCode.成功, customerService.cleaningCustomer(sourceId));
    }

    @Methods(methodsName = "清洗客户线索关联", methods = "cleaningCustomerAssociated")
    @ApiOperation(value = "清洗客户线索关联", httpMethod = "GET", notes = "清洗客户线索关联", response = Json.class)
    @GetMapping("/cleaningCustomerAssociated")
    public Json<Customer> cleaningCustomerAssociated() {
        customerService.cleaningCustomerAssociated();
        //返回内容
        return new Json(ReturnCode.成功);
    }

    /**
     * @param agentUserType 1 渠道经纪人   2 非渠道
     * @return
     */
    @Methods(methodsName = "清洗客户经纪人表", methods = "cleaningCustomerAgent")
    @ApiOperation(value = "清洗客户经纪人表", httpMethod = "GET", notes = "清洗客户经纪人表", response = Json.class)
    @GetMapping("/cleaningCustomerAgent")
    public Json<Customer> cleaningCustomerAgent(Integer agentUserType) {
        customerService.cleaningCustomerAgent(agentUserType);
        //返回内容
        return new Json(ReturnCode.成功);
    }

    @Methods(methodsName = "清洗客户注册状态", methods = "updateCustomerState")
    @ApiOperation(value = "清洗客户注册状态", httpMethod = "GET", notes = "清洗客户注册状态", response = Json.class)
    @GetMapping("/updateCustomerState")
    public Json<Customer> updateCustomerState() {
        customerService.updateCustomerState();
        //返回内容
        return new Json(ReturnCode.成功);
    }

    @Methods(methodsName = "清洗客户注册状态-手机号已注册", methods = "updateCustomerStateByMobile")
    @ApiOperation(value = "清洗客户注册状态-手机号已注册", httpMethod = "GET", notes = "清洗客户注册状态", response = Json.class)
    @GetMapping("/updateCustomerStateByMobile")
    public Json<Customer> updateCustomerStateByMobile() {
        customerService.updateCustomerStateByMobile();
        //返回内容
        return new Json(ReturnCode.成功);
    }

    @Methods(methodsName = "清洗客户备注信息-以分配或关联过", methods = "updateCustomerRemark")
    @ApiOperation(value = "清洗客户备注信息-以分配或关联过", httpMethod = "GET", notes = "清洗客户备注信息-以分配或关联过", response = Json.class)
    @GetMapping("/updateCustomerRemark")
    public Json<Customer> updateCustomerRemark() {
        customerService.updateCustomerRemark();
        //返回内容
        return new Json(ReturnCode.成功);
    }

    @Methods(methodsName = "清洗线下单数据转化为客户", methods = "cleaningOfflineCastInsurance")
    @ApiOperation(value = "清洗线下单数据转化为客户", httpMethod = "GET", notes = "清洗线下单数据转化为客户", response = Json.class)
    @GetMapping("/cleaningOfflineCastInsurance")
    public Json<Customer> cleaningOfflineCastInsurance(String agentUserId, String startTime, String endTime, Integer current, Integer size) {
//        customerService.cleaningOfflineCastInsurance(agentUserId, startTime, endTime, current, size);
        //返回内容
        return new Json(ReturnCode.成功);
    }

    @Methods(methodsName = "清洗线下单客户备注-更改地区", methods = "cleaningOfflineCastInsuranceCustomerRemark")
    @ApiOperation(value = "清洗线下单客户备注-更改地区", httpMethod = "GET", notes = "清洗线下单客户备注-更改地区", response = Json.class)
    @GetMapping("/cleaningOfflineCastInsuranceCustomerRemark")
    public Json<Customer> cleaningOfflineCastInsuranceCustomerRemark() {
        customerService.cleaningOfflineCastInsuranceCustomer();
        //返回内容
        return new Json(ReturnCode.成功);
    }

}
