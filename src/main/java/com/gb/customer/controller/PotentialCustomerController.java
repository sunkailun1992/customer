package com.gb.customer.controller;

import cn.hutool.core.lang.Validator;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gb.customer.entity.AgentPeople;
import com.gb.customer.entity.Customer;
import com.gb.customer.entity.PotentialCustomer;
import com.gb.customer.entity.enums.CustomerAgentTypeEnum;
import com.gb.customer.enums.*;
import com.gb.customer.factory.PotentialCustomerFactory;
import com.gb.customer.service.*;
import com.gb.customer.service.impl.CheckPermissions;
import com.gb.customer.template.AbstractCluesTemplate;
import com.gb.customer.template.CluesContainer;
import com.gb.customer.template.CluesPriorityEnum;
import com.gb.customer.template.event.CluesEvent;
import com.gb.rpc.entity.TeamUserVO;
import com.gb.rpc.entity.UserBasicInfoVO;
import com.gb.rpc.entity.UserLabelInfoVO;
import com.gb.rpc.entity.UserTypeValueVO;
import com.gb.rpc.entity.enums.AppCodeEnum;
import com.gb.rpc.entity.enums.RpcTypeEnum;
import com.gb.rpc.impl.RpcComponent;
import com.gb.utils.Json;
import com.gb.utils.OkhttpUtils;
import com.gb.utils.RedisUtils;
import com.gb.utils.annotations.Methods;
import com.gb.utils.annotations.PreventRepeat;
import com.gb.utils.annotations.RequestRequired;
import com.gb.utils.constants.UniversalConstant;
import com.gb.utils.enumeration.HttpType;
import com.gb.utils.enumeration.HttpWay;
import com.gb.utils.enumeration.ReturnCode;
import com.gb.utils.exception.BusinessException;
import com.google.common.collect.Maps;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Request;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 潜在客户表 前端控制器
 * </p>
 *
 * @author 王一飞
 * @since 2021-04-13
 */
@Slf4j
@RequestRequired
@RestController
@Setter(onMethod_ = {@Autowired})
@RequestMapping("/potential-customer")
@Api(tags = "潜在客户表")
public class PotentialCustomerController {

    /**
     * 潜在客户表
     */
    private PotentialCustomerService potentialCustomerService;
    /**
     * redis操作
     */
    private StringRedisTemplate stringRedisTemplate;

    private AgentPeopleService agentPeopleService;

    private CheckPermissions checkPermissions;

    private RpcComponent rpcComponent;

    private CluesContainer cluesContainer;

    private ApplicationEventPublisher applicationEventPublisher;

    private CustomerService customerService;

    private CustomerAssociatedService customerAssociatedService;

    private AsyncSendService asyncSendService;


    /**
     * 潜在客户表集合查询
     *
     * @param current:
     * @param size:
     * @param potentialCustomer:
     * @return com.utils.Json
     * @author 王一飞
     * @since 2021-04-13
     */
    @Methods(methodsName = "潜在客户表集合查询", methods = "select")
    @ApiOperation(value = "潜在客户表集合查询", httpMethod = "GET", notes = "潜在客户表集合查询", response = Json.class)
    @ApiImplicitParams({@ApiImplicitParam(name = "current", value = "当前页", dataType = "int"), @ApiImplicitParam(name = "size", value = "分页显示数量", dataType = "int"),})
    @GetMapping("/select")
    public Json<IPage<PotentialCustomer>> select(Integer current, Integer size, PotentialCustomer potentialCustomer, HttpServletRequest httpServletRequest) {
        //crm后台访问，校验数据权限
        if (Objects.nonNull(potentialCustomer.getIsCrm()) && 1 == potentialCustomer.getIsCrm()) {
            checkCrmPermissions(potentialCustomer, httpServletRequest);
        }
        if (current != null && size != null) {
            Page page = new Page(current, size);
            //返回内容
            return new Json(ReturnCode.成功, potentialCustomerService.pageEnhance(page, potentialCustomer));
        } else {
            //返回内容
            return new Json(ReturnCode.成功, potentialCustomerService.listEnhance(potentialCustomer));
        }
    }

    /**
     * CRM 2.0 客户详情页  潜在客户表集合查询   线索管理
     *
     * @param current:
     * @param size:
     * @param potentialCustomer:
     * @return com.utils.Json
     * @author lijh
     */
    @Methods(methodsName = "CRM 2.0 潜在客户表集合查询", methods = "select")
    @ApiOperation(value = "潜在客户表集合查询", httpMethod = "GET", notes = "潜在客户表集合查询", response = Json.class)
    @ApiImplicitParams({@ApiImplicitParam(name = "current", value = "当前页", dataType = "int"), @ApiImplicitParam(name = "size", value = "分页显示数量", dataType = "int"),})
    @GetMapping("/selectListPage")
    public Json<IPage<PotentialCustomer>> selectListPage(Integer current, Integer size, PotentialCustomer potentialCustomer, HttpServletRequest httpServletRequest) {
        //缓存取出用户
        Map<String, Object> u = RedisUtils.getToken(stringRedisTemplate, httpServletRequest);
        if (null == u) {
            throw new BusinessException("token过期，请重新登录");
        }
        //区分查询来源是线索管理列表做数据权限控制：查看所属团队权限下所有经纪人的线索
        if (CluesPortEnum.线索管理.getCode().equals(potentialCustomer.getCluesPort())) {
            //获取经纪人标签
            TeamUserVO teamUserVoInfo = checkPermissions.getTeamUserVoInfo(u.get("id").toString());
            potentialCustomer.setCustomerAgentType(CustomerAgentTypeEnum.getValue(teamUserVoInfo.getType()));
            potentialCustomer.setAgentUserIds(checkPermissions.getAgentTeamByTeamUser(teamUserVoInfo));
        }
        //crm 来源查所有线索
        potentialCustomer.setIsCrm(1);
        if (current != null && size != null) {
            Page page = new Page(current, size);
            //返回内容
            return new Json(ReturnCode.成功, potentialCustomerService.pageEnhance(page, potentialCustomer));
        } else {
            //返回内容
            return new Json(ReturnCode.成功, potentialCustomerService.listEnhance(potentialCustomer));
        }
    }

    /**
     * 潜在客户表单条查询
     *
     * @param potentialCustomer:
     * @return com.utils.Json
     * @author 王一飞
     * @since 2021-04-13
     */
    @Methods(methodsName = "潜在客户表单条查询", methods = "selectOne")
    @ApiOperation(value = "潜在客户表单条查询", httpMethod = "GET", notes = "潜在客户表单条查询", response = Json.class)
    @GetMapping("/selectOne")
    public Json<PotentialCustomer> selectOne(PotentialCustomer potentialCustomer, HttpServletRequest httpServletRequest) {
        //返回内容
        return new Json(ReturnCode.成功, potentialCustomerService.getOneEnhance(potentialCustomer, httpServletRequest));
    }


    /**
     * 潜在客户表总数查询
     *
     * @param potentialCustomer:
     * @return com.utils.Json
     * @author 王一飞
     * @since 2021-04-13
     */
    @Methods(methodsName = "潜在客户表总数查询", methods = "count")
    @ApiOperation(value = "潜在客户表总数查询", httpMethod = "GET", notes = "潜在客户表总数查询", response = Json.class)
    @GetMapping(value = "/count")
    public Json<Integer> count(PotentialCustomer potentialCustomer) {
        //返回内容
        return new Json(ReturnCode.成功, potentialCustomerService.countEnhance(potentialCustomer));
    }


    /**
     * 潜在客户表新增
     *
     * @param potentialCustomer:
     * @return com.utils.Json
     * @author 王一飞
     * @since 2021-04-13
     */
    @PreventRepeat
    @Methods(methodsName = "潜在客户表新增", methods = "save")
    @ApiOperation(value = "潜在客户表新增", httpMethod = "POST", notes = "潜在客户表新增", response = Json.class)
    @PostMapping("/save")
    public Json<String> save(PotentialCustomer potentialCustomer, HttpServletRequest httpServletRequest) {
        log.info("线索新增接收参数={}", potentialCustomer);
        //防止重复提交
        String mobile = potentialCustomer.getMobile();
        String key = "potentialCustomer:" + mobile;
        String value = RedisUtils.get(stringRedisTemplate, key);
        if (StringUtils.isNotBlank(value)) {
            return new Json<>(ReturnCode.成功);
        }
        try {
            //同一个手机号 10秒内只允许提交一次
            RedisUtils.add(stringRedisTemplate, key, mobile, 10, TimeUnit.SECONDS);
            //缓存取出用户
            Map<String, Object> u = RedisUtils.getToken(stringRedisTemplate, httpServletRequest);
            if (u != null) {
                potentialCustomer.setCreateName(u.get("name") + "-" + u.get("id"));
            }
            AbstractCluesTemplate cluesSourceService = cluesContainer.getCluesSourceService(CluesPriorityEnum.GBW_WEB);
            return new Json(ReturnCode.成功, cluesSourceService.generateClues(potentialCustomer, httpServletRequest));
        } finally {
            RedisUtils.delete(stringRedisTemplate, key);
        }
    }

    /**
     * 新增线索
     * 智核生成
     *
     * @param potentialCustomer
     * @param httpServletRequest
     * @return
     */
    @PreventRepeat
    @Methods(methodsName = "新增线索", methods = "save")
    @ApiOperation(value = "新增线索", httpMethod = "POST", notes = "潜在客户表新增", response = Json.class)
    @PostMapping("/savePotentialCustomer")
    public Json<String> savePotentialCustomer(PotentialCustomer potentialCustomer, HttpServletRequest httpServletRequest) {
        log.info("智核生成线索接收参数={}", potentialCustomer);
        //防止重复提交
        String mobile = potentialCustomer.getMobile();
        String key = "potentialCustomer:" + mobile;
        String value = RedisUtils.get(stringRedisTemplate, key);
        if (StringUtils.isNotBlank(value)) {
            return new Json<>(ReturnCode.成功);
        }
        try {
            //同一个手机号 10秒内只允许提交一次
            RedisUtils.add(stringRedisTemplate, key, mobile, 10, TimeUnit.SECONDS);
            //缓存取出用户
            Map<String, Object> u = RedisUtils.getToken(stringRedisTemplate, httpServletRequest);
            if (u != null) {
                potentialCustomer.setCreateName(u.get("name") + "-" + u.get("id"));
            }
            PotentialCustomerFactory.getBeanPotentialCustomer(potentialCustomer);
            potentialCustomerService.save(potentialCustomer);
            Customer customer = customerService.getOne(new QueryWrapper<Customer>().lambda().eq(Customer::getMobile, mobile));
            if (Objects.nonNull(customer)) {
                //客户和线索关联
                customerAssociatedService.saveCustomerAssociated(potentialCustomer.getId(), customer.getId(), CustomerAssociatedTypeEnum.副关联);
            }
            //消息保存成功 发送短信和推送用户消息
            applicationEventPublisher.publishEvent(new CluesEvent(this, potentialCustomer.getId(), potentialCustomer.getAgentUserId(), potentialCustomer.getAgentUserType(), potentialCustomer.getMobile(), potentialCustomer.getType()));
            return new Json(ReturnCode.成功);
        } finally {
            RedisUtils.delete(stringRedisTemplate, key);
        }
    }


    /**
     * 潜在客户表修改
     *
     * @param potentialCustomer:
     * @return com.utils.Json
     * @author 王一飞
     * @since 2021-04-13
     */
    @PreventRepeat
    @Methods(methodsName = "潜在客户表修改", methods = "update")
    @ApiOperation(value = "潜在客户表修改", httpMethod = "PUT", notes = "潜在客户表修改", response = Json.class)
    @PutMapping("/update")
    public Json<Boolean> update(PotentialCustomer potentialCustomer, HttpServletRequest httpServletRequest) {
        //缓存取出用户
        Map<String, Object> u = RedisUtils.getToken(stringRedisTemplate, httpServletRequest);
        if (u != null) {
            potentialCustomer.setModifyName(u.get("name") + "-" + u.get("id"));
        }
        //返回内容
        return new Json(ReturnCode.成功, potentialCustomerService.updateEnhance(potentialCustomer));
    }


    /**
     * 潜在客户表删除
     *
     * @param potentialCustomer:
     * @return com.utils.Json
     * @author 王一飞
     * @since 2021-04-13
     */
    @Methods(methodsName = "潜在客户表删除", methods = "remove")
    @ApiOperation(value = "潜在客户表删除", httpMethod = "DELETE", notes = "潜在客户表删除", response = Json.class)
    @DeleteMapping("/remove")
    public Json<Boolean> remove(PotentialCustomer potentialCustomer) {
        return new Json(ReturnCode.成功, potentialCustomerService.removeEnhance(potentialCustomer));
    }


    /**
     * 投保咨询验证码校验
     *
     * @param phone:
     * @return com.utils.Json
     * @author 王一飞
     * @since 2021-04-20
     */
    @Methods(methodsName = "投保咨询验证码校验", methods = "validationSms")
    @ApiOperation(value = "投保咨询验证码校验", httpMethod = "POST", notes = "投保咨询验证码校验", response = Json.class)
    @ApiImplicitParams({@ApiImplicitParam(name = "phone", value = "手机号", dataType = "String", required = true), @ApiImplicitParam(name = "code", value = "验证码", dataType = "String", required = true),})
    @PostMapping("/validationSms")
    public Json validationSms(String phone, String code) {
        //TODO 发送验证码调取工保通
        String k = "advisoryValidation:" + phone;
        //查询缓存code
        String c = RedisUtils.get(stringRedisTemplate, k);
        //判断code是否存在
        if (StringUtils.isNotBlank(c)) {
            if (c.equals(code)) {
                return new Json(ReturnCode.成功, "成功");
            } else {
                return new Json(ReturnCode.用户验证码错误, "验证码不正确");
            }
        } else {
            return new Json(ReturnCode.系统执行出错, "未找到验证码");
        }
    }


    /**
     * 发送投保咨询验证码
     *
     * @param phone:
     * @return com.utils.Json
     * @author 王一飞
     * @since 2021-04-20
     */
    @Methods(methodsName = "发送投保咨询验证码", methods = "sendSms")
    @ApiOperation(value = "发送投保咨询验证码", httpMethod = "POST", notes = "发送投保咨询验证码", response = Json.class)
    @ApiImplicitParams({@ApiImplicitParam(name = "phone", value = "手机号", dataType = "String", required = true),})
    @PostMapping("/sendSms")
    public Json sendSms(@RequestParam("phone") String phone, HttpServletRequest httpServletRequest) {
        if (!Validator.isMobile(phone)) {
            throw new BusinessException("手机号不正确!");
        }
        String key = "advisoryValidation:" + phone;

        HashMap<String, String> headerMap = Maps.newHashMap();
        // sourceCode 默认写死，返回结果会是验证码
        headerMap.put(UniversalConstant.SOURCECODE, httpServletRequest.getHeader("sourceCode"));
        headerMap.put(UniversalConstant.SOURCE_VALUE_CODE, httpServletRequest.getHeader("sourceValueCode"));
        headerMap.put(UniversalConstant.BUSINESS_DETAILS, httpServletRequest.getHeader("businessDetails"));
        headerMap.put(UniversalConstant.APPCODE, AppCodeEnum.NET_USER.getCode());
        Map<String, Object> map = Maps.newHashMap();
        map.put("phone", phone);

        LinkedHashMap linkedHashMap = rpcComponent.rpcQueryInfo(map, headerMap, RpcTypeEnum.SEND_CODE, LinkedHashMap.class);
        if (CollectionUtils.isEmpty(linkedHashMap)) {
            return new Json(ReturnCode.成功, "发送失败");
        }
        //放入缓存，设置时间
        RedisUtils.add(stringRedisTemplate, key, linkedHashMap.get("obj").toString(), 10, TimeUnit.MINUTES);
        return new Json(ReturnCode.成功, "发送成功");

        //TODO 改动 调用工保通发送验证码
        //String k = "advisoryValidation:" + phone;
        ////查询缓存code
        //String verCode = RedisUtils.get(stringRedisTemplate, k);
        ////判断code是否存在
        //if (StringUtils.isNotBlank(verCode)) {
        //    //发送的验证码
        //    Map<String, Object> map = Maps.newHashMap();
        //    map.put("verCode", verCode);
        //    //阿里云短信发送
        //    SmsUtils.sendMessage(phone, SmsEnum.getSmsEnum("投保咨询验证"), JsonUtil.json(map));
        //} else {
        //    //放入新生产的code
        //    verCode = RandomUtil.randomNumbers(6);
        //    //发送的验证码
        //    Map<String, Object> map = Maps.newHashMap();
        //    map.put("verCode", verCode);
        //    //阿里云短信发送
        //    SmsUtils.sendMessage(phone, SmsEnum.getSmsEnum("投保咨询验证"), JsonUtil.json(map));
        //    //放入缓存，设置时间
        //    RedisUtils.add(stringRedisTemplate, k, verCode, 15, TimeUnit.MINUTES);
        //}
        //return new Json(ReturnCode.成功, "发送成功");
    }


    /**
     * 完成咨询
     *
     * @param potentialCustomer:
     * @return com.utils.Json
     * @author 王一飞
     * @since 2021-04-13
     */
    @PreventRepeat
    @Methods(methodsName = "完成咨询", methods = "update")
    @ApiOperation(value = "完成咨询", httpMethod = "PUT", notes = "完成咨询", response = Json.class)
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "潜在客户表id", dataType = "String", required = true),})
    @PutMapping("/completeConsulting")
    public Json completeConsulting(PotentialCustomer potentialCustomer, HttpServletRequest httpServletRequest) {
        //缓存取出用户
        Map<String, Object> u = RedisUtils.getToken(stringRedisTemplate, httpServletRequest);
        if (u != null) {
            potentialCustomer.setModifyName(u.get("name") + "-" + u.get("id"));
        }
        //返回内容
        return new Json(ReturnCode.成功, potentialCustomerService.completeConsulting(potentialCustomer));
    }


    /**
     * 关闭咨询
     *
     * @param potentialCustomer:
     * @return com.utils.Json
     * @author 王一飞
     * @since 2021-04-13
     */
    @PreventRepeat
    @Methods(methodsName = "关闭咨询", methods = "update")
    @ApiOperation(value = "关闭咨询", httpMethod = "PUT", notes = "关闭咨询", response = Json.class)
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "潜在客户表id", dataType = "String", required = true),})
    @PutMapping("/closeConsulting")
    public Json closeConsulting(PotentialCustomer potentialCustomer, HttpServletRequest httpServletRequest) {
        //缓存取出用户
        Map<String, Object> u = RedisUtils.getToken(stringRedisTemplate, httpServletRequest);
        if (u != null) {
            potentialCustomer.setModifyName(u.get("name") + "-" + u.get("id"));
        }
        //返回内容
        return new Json(ReturnCode.成功, potentialCustomerService.closeConsulting(potentialCustomer));
    }


    /**
     * 取消咨询
     *
     * @param potentialCustomer:
     * @return com.utils.Json
     * @author 王一飞
     * @since 2021-04-13
     */
    @PreventRepeat
    @Methods(methodsName = "取消咨询", methods = "update")
    @ApiOperation(value = "取消咨询", httpMethod = "PUT", notes = "取消咨询", response = Json.class)
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "潜在客户表id", dataType = "String", required = true),})
    @PutMapping("/cancelConsulting")
    public Json cancelConsulting(PotentialCustomer potentialCustomer, HttpServletRequest httpServletRequest) {
        //缓存取出用户
        Map<String, Object> u = RedisUtils.getToken(stringRedisTemplate, httpServletRequest);
        if (u != null) {
            potentialCustomer.setModifyName(u.get("name") + "-" + u.get("id"));
        }
        //返回内容
        return new Json(ReturnCode.成功, potentialCustomerService.cancelConsulting(potentialCustomer));
    }


    /**
     * 经纪人查询（过滤当前 投保咨询经纪人）
     *
     * @param potentialCustomer:
     * @return com.utils.Json
     * @author 王一飞
     * @since 2021-04-13
     */
    @Methods(methodsName = "经纪人查询（过滤当前 投保咨询经纪人）", methods = "selectOne")
    @ApiOperation(value = "经纪人查询（过滤当前 投保咨询经纪人）", httpMethod = "GET", notes = "经纪人查询（过滤当前 投保咨询经纪人）", response = Json.class)
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "潜在客户表id", dataType = "String", required = true),})
    @GetMapping("/selectAgentAndFilterCurrentAgent")
    public Json selectAgentAndFilterCurrentAgent(PotentialCustomer potentialCustomer, HttpServletRequest httpServletRequest) {
        //返回内容
        return new Json(ReturnCode.成功, potentialCustomerService.selectAgentAndFilterCurrentAgent(potentialCustomer, httpServletRequest));
    }

    /**
     * 拉取三方客户线索批量新增
     *
     * @param potentialCustomers
     * @return
     */
    @Methods(methodsName = "潜在客户表批量新增", methods = "saveAll")
    @ApiOperation(value = "潜在客户表批量新增", httpMethod = "POST", notes = "潜在客户表批量新增", response = Json.class)
    @PostMapping("/saveAll")
    public Json<String> saveAll(@RequestBody List<PotentialCustomer> potentialCustomers, HttpServletRequest httpServletRequest) {
        log.debug("接收七陌云数据={}", JSONArray.toJSON(potentialCustomers).toString());
        if (CollectionUtils.isEmpty(potentialCustomers)) {
            throw new BusinessException("无线索可新增");
        }
        AbstractCluesTemplate cluesSourceService = cluesContainer.getCluesSourceService(CluesPriorityEnum.QI_MO_YUN);
        return new Json(ReturnCode.成功, cluesSourceService.generateClues(potentialCustomers, httpServletRequest));
    }

    @Methods(methodsName = "BI客户线索数据批量新增", methods = "saveAllBiInfo")
    @ApiOperation(value = "BI客户线索数据批量新增", httpMethod = "POST", notes = "BI客户线索数据批量新增", response = Json.class)
    @PostMapping("/saveAllBiInfo")
    public Json<String> saveAllBiInfo(@RequestBody List<PotentialCustomer> potentialCustomers, HttpServletRequest httpServletRequest) {
        log.debug("接收BI数据={}", potentialCustomers);
        if (CollectionUtils.isEmpty(potentialCustomers)) {
            return new Json(ReturnCode.系统执行出错, "无线索可新增");
        }
        AbstractCluesTemplate cluesSourceService = cluesContainer.getCluesSourceService(CluesPriorityEnum.BI);
        return new Json(ReturnCode.成功, cluesSourceService.generateClues(potentialCustomers, httpServletRequest));
    }

    /**
     * 手动添加线索
     * 限制重复提交，线索存在，已是客户
     *
     * @param potentialCustomer
     * @param httpServletRequest
     * @return
     */
    @PreventRepeat
    @Methods(methodsName = "手动添加客户线索", methods = "save")
    @ApiOperation(value = "手动添加客户线索", httpMethod = "POST", notes = "手动添加客户线索", response = Json.class)
    @PostMapping("/saveCustomerClues")
    public Json<String> saveCustomerClues(PotentialCustomer potentialCustomer, HttpServletRequest httpServletRequest) {
        //缓存取出用户
        Map<String, Object> u = RedisUtils.getToken(stringRedisTemplate, httpServletRequest);
        log.debug("[手动添加线索],操作人信息={},添加信息={}", u, potentialCustomer);
        if (u != null) {
            potentialCustomer.setCreateName(u.get("name") + "-" + u.get("id"));
        }
        Boolean b = potentialCustomerService.saveCustomerClues(potentialCustomer);
        if (b) {
            //返回内容
            return new Json(ReturnCode.成功, potentialCustomer.getId());
        } else {
            //返回内容
            return new Json(ReturnCode.系统执行出错, "手动添加失败");
        }
    }

    /**
     * 分配管家
     *
     * @param id                 当前信息id
     * @param httpServletRequest
     * @param agentUserId        经纪人唯一标识
     * @param agentUserName      经纪人名称
     * @param type               区分列表。 0线索管理，1 客户管理，2 经纪人管理 3.经纪人线索列表
     * @return
     */
    @Methods(methodsName = "分配管家", methods = "update")
    @ApiOperation(value = "分配管家", httpMethod = "GET", notes = "分配管家", response = Json.class)
    @GetMapping("/allotSteward")
    public Json allotSteward(String id, HttpServletRequest httpServletRequest, String agentUserId, String agentUserName, Integer type, String mobile) {
        if (StringUtils.isBlank(agentUserId)) {
            throw new BusinessException("未选择管家");
        }
        String agentGroupId = null;
        List<String> userPermissions = checkPermissions.queryUserGroupByUserId(agentUserId);
        if (CollectionUtils.isNotEmpty(userPermissions)) {
            agentGroupId = userPermissions.get(0);
        }
        Boolean result = false;
        //分配线索管家
        if (!CustomerDataTypeEnum.DATA_TYPE_2.getCode().equals(type)) {
            String finalAgentGroupId = agentGroupId;
            result = potentialCustomerService.allotSteward(new PotentialCustomer() {{
                setMobile(mobile);
                setAgentUserId(agentUserId);
                setAgentUserName(agentUserName);
                setId(id);
                setType(CustomerDataTypeEnum.DATA_TYPE_3.getCode().equals(type) ? PotentialCustomerTypeEnum.CUSTOMER_TYPE_1.getCode() : PotentialCustomerTypeEnum.CUSTOMER_TYPE_0.getCode());
                setAgentGroupId(finalAgentGroupId);
            }}, httpServletRequest);
        }
        //分配经纪人的管家
        if (CustomerDataTypeEnum.DATA_TYPE_2.getCode().equals(type)) {
            String finalAgentGroupId = agentGroupId;
            result = agentPeopleService.updateSteward(new AgentPeople() {{
                setAgentUserId(agentUserId);
                setAgentUserName(agentUserName);
                setId(id);
                setMobile(mobile);
                setAgentGroupId(finalAgentGroupId);
            }}, httpServletRequest);
        }
        //返回内容
        return new Json(ReturnCode.成功, result);
    }

    /**
     * 获取管家列表，  条件筛选下拉列表框使用
     *
     * @param type 区分列表。  0 客户线索, 1 经纪人线索, 2 客户列表(订单列表，业务管家筛选), 3.经纪人列表，4.订单列表(服务管家筛选)
     * @return
     */
    @Methods(methodsName = "获取管家列表", methods = "select")
    @ApiOperation(value = "获取管家列表", httpMethod = "GET", notes = "获取管家列表", response = Json.class)
    @GetMapping("/getStewardList")
    public Json getStewardList(Integer type, HttpServletRequest httpServletRequest) {
        //缓存取出用户
        Map<String, Object> u = RedisUtils.getToken(stringRedisTemplate, httpServletRequest);
        if (null == u) {
            throw new BusinessException("token不存在，请重新登录");
        }
        List<String> strings = checkPermissions.checkUserPermissions(u);
        log.info("此用户拥有的用户组:" + strings);
        String userId = null;
        //不属于主管组
        if (checkPermissions.checkDataPermissions(DataPermissionsEnum.POTENTIAL_CUSTOMEER_GETSTEWARDLIST, strings)) {
            userId = (String) u.get("id");
        }
        return new Json(ReturnCode.成功, potentialCustomerService.getStewardList(type, userId));
    }

    @Methods(methodsName = "管家分页列表", methods = "select")
    @ApiOperation(value = "管家分页列表", httpMethod = "GET", notes = "管家分页列表", response = Json.class)
    @ApiImplicitParams({@ApiImplicitParam(name = "phone", value = "手机号", dataType = "String"), @ApiImplicitParam(name = "name", value = "姓名", dataType = "String"),})
    @GetMapping("/queryStewardByPage")
    public Json queryStewardByPage(String phone, String name, HttpServletRequest httpServletRequest) {
        //缓存取出用户
        Map<String, Object> u = RedisUtils.getToken(stringRedisTemplate, httpServletRequest);
        if (null == u) {
            throw new BusinessException("token过期，请重新登录");
        }
        List<String> strings = checkPermissions.checkUserPermissions(u);
        log.info("此用户拥有的用户组:" + strings);
        //不属于主管组
        if (checkPermissions.checkDataPermissions(DataPermissionsEnum.POTENTIAL_CUSTOMEER_QUERYSTEWARDBYPAGE, strings)) {
            return new Json(ReturnCode.成功, null);
        }
        return new Json(ReturnCode.成功, potentialCustomerService.queryStewardList(phone, name));
    }

    @Methods(methodsName = "认证经纪人", methods = "update")
    @ApiOperation(value = "认证经纪人", httpMethod = "PUT", notes = "认证经纪人", response = Json.class)
    @PutMapping("/certificationAgentPeople")
    public Json certificationAgentPeople(AgentPeople agentPeopleBO) {
        log.debug("[认证经纪人],接收参数={}", agentPeopleBO);
        if (StringUtils.isEmpty(agentPeopleBO.getUserId())) {
            return new Json(ReturnCode.系统执行出错, "缺少必要参数");
        }
        return new Json(ReturnCode.成功, potentialCustomerService.certificationAgentPeople(agentPeopleBO));
    }

    @Methods(methodsName = "根据标签code获取所有用户组", methods = "select")
    @ApiOperation(value = "根据标签code获取所有用户组", httpMethod = "GET", notes = "根据标签code获取所有用户组", response = Json.class)
    @GetMapping("/queryStewardByUserTypeCode")
    public Json queryStewardByUserTypeCode() {
        HashMap<String, Object> hashMap = new HashMap<>(2);
        hashMap.put("userTypeCode", "4");
        hashMap.put("queryType", 1);
        List<UserLabelInfoVO> userLabelInfos = rpcComponent.rpcQueryList(hashMap, null, RpcTypeEnum.GET_LABEL_USER_TYPE, UserLabelInfoVO.class);
        log.debug("【根据标签code获取所有管家组】info = {}", userLabelInfos);
        return new Json(ReturnCode.成功, userLabelInfos);
    }

    @Methods(methodsName = "获取时间最近的单条数据", methods = "select")
    @ApiOperation(value = "获取时间最近的单条数据", httpMethod = "GET", notes = "获取时间最近的单条数据", response = Json.class)
    @GetMapping("/selectOneRecentlyClues")
    public Json selectOneRecentlyClues(PotentialCustomer potentialCustomer) {
        log.debug("根据来源id获取最近的数据,sourceId={}", potentialCustomer.getSourceQuery());
        PotentialCustomer one = potentialCustomerService.getOne(new QueryWrapper<PotentialCustomer>().lambda().eq(PotentialCustomer::getPotentialCustomerSourceId, potentialCustomer.getSourceQuery()).orderByDesc(PotentialCustomer::getCreateDateTime).last("limit 1"));
        return new Json(ReturnCode.成功, one);
    }

    @Methods(methodsName = "获取指定管家组信息", methods = "select")
    @ApiOperation(value = "获取指定管家组信息", httpMethod = "GET", notes = "获取指定管家组信息", response = Json.class)
    @GetMapping("/queryUserGroupByGroupId")
    public Json queryUserGroupByGroupId(String groupId) {
        HashMap<String, Object> hashMap = new HashMap<>(2);
        hashMap.put("userTypeValueCode", groupId);
        hashMap.put("queryType", 1);
        List<UserLabelInfoVO> userLabelInfos = rpcComponent.rpcQueryList(hashMap, null, RpcTypeEnum.GET_LABEL_USER_TYPE, UserLabelInfoVO.class);
        if (CollectionUtils.isEmpty(userLabelInfos)) {
            return new Json(ReturnCode.成功, userLabelInfos);
        }
        List<UserBasicInfoVO> userBasicInfoVOList = userLabelInfos.get(0).getUserBasicInfoVOList();
        if (CollectionUtils.isNotEmpty(userBasicInfoVOList)) {
            for (UserBasicInfoVO userBasicInfoVO : userBasicInfoVOList) {
                userBasicInfoVO.setUserName(userBasicInfoVO.getUserId());
            }
        }
        return new Json(ReturnCode.成功, userBasicInfoVOList);
    }

    @Methods(methodsName = "获取用户组信息-测试", methods = "select")
    @ApiOperation(value = "获取用户组信息-测试", httpMethod = "GET", notes = "获取用户组信息", response = Json.class)
    @ApiImplicitParams({@ApiImplicitParam(name = "queryType", value = "是否认证 1是0否", dataType = "Integer"), @ApiImplicitParam(name = "groupId", value = "组id", dataType = "String"),})
    @GetMapping("/getUserGroupInfoByGroupId")
    public Json getUserGroupInfoByGroupId(Integer queryType, String groupId) {
        HashMap<String, Object> hashMap = new HashMap<>(8);
        hashMap.put("userId", "121197596");
        List<UserTypeValueVO> userTypeValueVOList = rpcComponent.rpcQueryList(hashMap, null, RpcTypeEnum.GET_USER_GROUP_BY_USERID, UserTypeValueVO.class);
        return new Json(ReturnCode.成功, userTypeValueVOList);
    }

    @Methods(methodsName = "异步通知云", methods = "asyncSendYunPromoteForm")
    @ApiOperation(value = "异步通知云", httpMethod = "POST", notes = "获取用户组信息", response = Json.class)
    @PostMapping("/asyncSendYunPromoteForm")
    public Json asyncSendYunPromoteForm(PotentialCustomer potentialCustomer) throws Exception {
        return new Json(ReturnCode.成功, asyncSendService.asyncSendYunPromoteForm(potentialCustomer));
    }

    /**
     * 校验crm后台权限
     *
     * @param potentialCustomer
     * @param httpServletRequest
     */
    private void checkCrmPermissions(PotentialCustomer potentialCustomer, HttpServletRequest httpServletRequest) {
        //缓存取出用户
        Map<String, Object> u = RedisUtils.getToken(stringRedisTemplate, httpServletRequest);
        if (null == u) {
            throw new BusinessException("token过期，请重新登录");
        }
        potentialCustomer.setDifferentCharacterPerspectives(1);
        List<String> groupCodes = checkPermissions.checkUserPermissions(u);
        log.info("用户{}拥有的用户组:{}", u.get("id"), groupCodes);
        //非指定管家，默认展示当前账号下数据
        //客户线索  允许业务管家，互联网管家，商务管家
        //经纪人线索  允许业务管家，互联网管家
        if (Objects.equals(PotentialCustomerTypeEnum.CUSTOMER_TYPE_0.getCode(), potentialCustomer.getType())) {
            if (checkPermissions.checkDataPermissions(DataPermissionsEnum.POTENTIAL_CUSTOMEER_SELECT_0, groupCodes)) {
                potentialCustomer.setUserIdQuery((String) u.get("id"));
            }
        } else {
            if (checkPermissions.checkDataPermissions(DataPermissionsEnum.POTENTIAL_CUSTOMEER_SELECT_1, groupCodes)) {
                potentialCustomer.setUserIdQuery((String) u.get("id"));
            }
        }
    }
}

