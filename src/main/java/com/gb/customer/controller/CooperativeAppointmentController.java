package com.gb.customer.controller;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.gb.aliyun.sms.SmsUtils;
import com.gb.customer.entity.PotentialCustomer;
import com.gb.rpc.entity.enums.AppCodeEnum;
import com.gb.rpc.entity.enums.RpcTypeEnum;
import com.gb.utils.JsonUtil;
import com.gb.utils.constants.UniversalConstant;
import com.gb.utils.enumeration.SmsEnum;
import com.gb.utils.exception.BusinessException;
import com.google.common.collect.Maps;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Setter;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.*;
import java.util.concurrent.TimeUnit;

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
import com.gb.customer.service.CooperativeAppointmentService;
import com.gb.customer.entity.query.CooperativeAppointmentQuery;
import com.gb.customer.entity.vo.CooperativeAppointmentVO;
import com.gb.customer.entity.bo.CooperativeAppointmentBO;


/**
 * TODO 云服合作预约，Comment请求层
 * 代码生成器
 *
 * @author ljh
 * @className CooperativeAppointmentController
 * @time 2023-01-09 02:56:41
 */
@Slf4j
@RequestRequired
@ApiSupport(author = "ljh")
@Setter(onMethod_ = {@Autowired})
@RestController
@RequestMapping("/cooperative-appointment")
@Api(tags = "云服合作预约")
public class CooperativeAppointmentController {


    /**
     * 云服合作预约
     */
    private CooperativeAppointmentService cooperativeAppointmentService;
    /**
     * redis操作
     */
    private StringRedisTemplate stringRedisTemplate;


    /**
     * TODO 分页
     *
     * @param cooperativeAppointmentQuery 云服合作预约
     * @param current
     * @param size
     * @return Json<Page < CooperativeAppointmentVO>>
     * @author ljh
     * @methodName select
     * @time 2023-01-09 02:56:41
     */
    @Methods(methodsName = "云服合作预约集合分页查询", methods = "select")
    @ApiOperation(value = "云服合作预约集合分页查询", httpMethod = "GET", notes = "云服合作预约集合查询", response = Json.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", dataType = "int", required = true),
            @ApiImplicitParam(name = "size", value = "分页显示数量", dataType = "int", required = true),
    })
    @GetMapping("/select")
    public Json<Page<CooperativeAppointmentVO>> select(@Validated(value = CooperativeAppointmentQuery.Select.class) CooperativeAppointmentQuery cooperativeAppointmentQuery, Integer current, Integer size) {
        //返回内容
        return new Json(ReturnCode.成功, cooperativeAppointmentService.pageEnhance(new Page(current, size), cooperativeAppointmentQuery));
    }


    /**
     * TODO 集合
     *
     * @param cooperativeAppointmentQuery 云服合作预约
     * @return Json<List < CooperativeAppointmentVO>>
     * @author ljh
     * @methodName selectList
     * @time 2023-01-09 02:56:41
     */
    @Methods(methodsName = "云服合作预约集合查询", methods = "selectList")
    @ApiOperation(value = "云服合作预约集合查询", httpMethod = "GET", notes = "云服合作预约集合查询", response = Json.class)
    @GetMapping("/selectList")
    public Json<List<CooperativeAppointmentVO>> selectList(@Validated(value = CooperativeAppointmentQuery.SelectList.class) CooperativeAppointmentQuery cooperativeAppointmentQuery) {
        //返回内容
        return new Json(ReturnCode.成功, cooperativeAppointmentService.listEnhance(cooperativeAppointmentQuery));
    }


    /**
     * TODO 单条
     *
     * @param cooperativeAppointmentQuery 云服合作预约
     * @return Json<CooperativeAppointmentVO>
     * @author ljh
     * @methodName selectOne
     * @time 2023-01-09 02:56:41
     */
    @Methods(methodsName = "云服合作预约单条查询", methods = "selectOne")
    @ApiOperation(value = "云服合作预约单条查询", httpMethod = "GET", notes = "云服合作预约单条查询", response = Json.class)
    @GetMapping("/selectOne")
    public Json<CooperativeAppointmentVO> selectOne(@Validated(value = CooperativeAppointmentQuery.SelectOne.class) CooperativeAppointmentQuery cooperativeAppointmentQuery) {
        //返回内容
        return new Json(ReturnCode.成功, cooperativeAppointmentService.getOneEnhance(cooperativeAppointmentQuery));
    }


    /**
     * TODO 总数
     *
     * @param cooperativeAppointmentQuery 云服合作预约
     * @return Json<Integer>
     * @author ljh
     * @methodName count
     * @time 2023-01-09 02:56:41
     */
    @Methods(methodsName = "云服合作预约总数查询", methods = "count")
    @ApiOperation(value = "云服合作预约总数查询", httpMethod = "GET", notes = "云服合作预约总数查询", response = Json.class)
    @GetMapping(value = "/count")
    public Json<Integer> count(@Validated(value = CooperativeAppointmentQuery.Count.class) CooperativeAppointmentQuery cooperativeAppointmentQuery) {
        //返回内容
        return new Json(ReturnCode.成功, cooperativeAppointmentService.countEnhance(cooperativeAppointmentQuery));
    }


    /**
     * TODO 新增
     *
     * @param cooperativeAppointmentBO 云服合作预约
     * @return Json<String>
     * @author ljh
     * @methodName save
     * @time 2023-01-09 02:56:41
     */
    @PreventRepeat
    @Methods(methodsName = "云服合作预约新增", methods = "save")
    @ApiOperation(value = "云服合作预约新增", httpMethod = "POST", notes = "云服合作预约新增", response = Json.class)
    @ApiOperationSupport(ignoreParameters = {"id", "createDateTime", "createName", "modifyDateTime", "modifyName", "isDelete", "version"})
    @PostMapping("/save")
    public Json<String> save(@Validated(value = CooperativeAppointmentBO.Save.class) CooperativeAppointmentBO cooperativeAppointmentBO, HttpServletRequest httpServletRequest) {
        //缓存取出用户
        Map<String, Object> u = RedisUtils.getToken(stringRedisTemplate, httpServletRequest);
        if (u != null) {
            cooperativeAppointmentBO.setCreateName(u.get("name") + "-" + u.get("id"));
        }
        //返回内容
        return new Json(ReturnCode.成功, cooperativeAppointmentService.saveEnhance(cooperativeAppointmentBO));
    }


    /**
     * TODO 修改
     *
     * @param cooperativeAppointmentBO 云服合作预约
     * @return Json<Boolean>
     * @author ljh
     * @methodName update
     * @time 2023-01-09 02:56:41
     */
    @PreventRepeat
    @Methods(methodsName = "云服合作预约修改", methods = "update")
    @ApiOperation(value = "云服合作预约修改", httpMethod = "PUT", notes = "云服合作预约修改", response = Json.class)
    @ApiOperationSupport(ignoreParameters = {"createDateTime", "createName", "modifyDateTime", "modifyName", "isDelete", "version"})
    @PutMapping("/update")
    public Json<Boolean> update(@Validated(value = CooperativeAppointmentBO.Update.class) CooperativeAppointmentBO cooperativeAppointmentBO, HttpServletRequest httpServletRequest) {
        //校验token
        cooperativeAppointmentCheckToken(cooperativeAppointmentBO, httpServletRequest);
        //返回内容
        return new Json(ReturnCode.成功, cooperativeAppointmentService.updateEnhance(cooperativeAppointmentBO));
    }


    /**
     * TODO 删除
     *
     * @param cooperativeAppointmentBO 云服合作预约
     * @return Json<Boolean>
     * @author ljh
     * @methodName remove
     * @time 2023-01-09 02:56:41
     */
    @Methods(methodsName = "云服合作预约删除", methods = "remove")
    @ApiOperation(value = "云服合作预约删除", httpMethod = "DELETE", notes = "云服合作预约删除", response = Json.class)
    @ApiOperationSupport(includeParameters = {"id"})
    @DeleteMapping("/remove")
    public Json<Boolean> remove(@Validated(value = CooperativeAppointmentBO.Remove.class) CooperativeAppointmentBO cooperativeAppointmentBO) {
        return new Json(ReturnCode.成功, cooperativeAppointmentService.removeEnhance(cooperativeAppointmentBO));
    }

    /**
     * 云服合作预约-修改预约订单状态
     *
     * @param cooperativeAppointmentBO id,state
     * @param httpServletRequest
     * @return
     */
    @PreventRepeat
    @Methods(methodsName = "修改预约订单状态", methods = "updateAppointmentState")
    @ApiOperation(value = "修改预约订单状态", httpMethod = "PUT", notes = "修改预约订单状态", response = Json.class)
    @PutMapping("/updateAppointmentState")
    public Json updateAppointmentState(CooperativeAppointmentBO cooperativeAppointmentBO, HttpServletRequest httpServletRequest) {
        //校验token
        cooperativeAppointmentCheckToken(cooperativeAppointmentBO, httpServletRequest);
        //返回内容
        return new Json(ReturnCode.成功, cooperativeAppointmentService.updateAppointmentState(cooperativeAppointmentBO));
    }

    /**
     * 工保云服发送预约验证码
     *
     * @param phone
     * @param httpServletRequest
     * @return
     */
    @Methods(methodsName = "工保云服发送预约验证码", methods = "sendSms")
    @ApiOperation(value = "工保云服发送预约验证码", httpMethod = "POST", notes = "工保云服发送预约验证码", response = Json.class)
    @ApiImplicitParams({@ApiImplicitParam(name = "phone", value = "手机号", dataType = "String", required = true),})
    @PostMapping("/sendSms")
    public Json sendSms(@RequestParam("phone") String phone, HttpServletRequest httpServletRequest) {
        if (!Validator.isMobile(phone)) {
            throw new BusinessException("手机号不正确!");
        }
        String k = "advisoryValidation:" + phone;
        //查询缓存code
        String verCode = RedisUtils.get(stringRedisTemplate, k);
        //判断code是否存在
        if (StringUtils.isNotBlank(verCode)) {
            //发送的验证码
            Map<String, Object> map = Maps.newHashMap();
            map.put("verCode", verCode);
            //阿里云短信发送
            SmsUtils.sendMessage("工保云服", phone, SmsEnum.getSmsEnum("投保咨询验证"), JsonUtil.json(map));
        } else {
            //放入新生产的code
            verCode = RandomUtil.randomNumbers(6);
            //发送的验证码
            Map<String, Object> map = Maps.newHashMap();
            map.put("verCode", verCode);
            //阿里云短信发送
            SmsUtils.sendMessage("工保云服", phone, SmsEnum.getSmsEnum("投保咨询验证"), JsonUtil.json(map));
            //放入缓存，设置时间
            RedisUtils.add(stringRedisTemplate, k, verCode, 10, TimeUnit.MINUTES);
        }
        return new Json(ReturnCode.成功, "发送成功");
    }

    /**
     * 工保云服预约验证码校验
     *
     * @param phone
     * @param code
     * @return
     */
    @Methods(methodsName = "工保云服预约验证码校验", methods = "validationSms")
    @ApiOperation(value = "工保云服预约验证码校验", httpMethod = "POST", notes = "工保云服预约验证码校验", response = Json.class)
    @ApiImplicitParams({@ApiImplicitParam(name = "phone", value = "手机号", dataType = "String", required = true), @ApiImplicitParam(name = "code", value = "验证码", dataType = "String", required = true),})
    @PostMapping("/validationSms")
    public Json validationSms(String phone, String code) {
        //TODO 发送验证码调取工保通
        String k = "advisoryValidation:" + phone;
        //查询缓存code
        String c = RedisUtils.get(stringRedisTemplate, k);
        //判断code是否存在
        if (org.apache.commons.lang3.StringUtils.isNotBlank(c)) {
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
     * 校验token
     *
     * @param cooperativeAppointmentBO
     * @param httpServletRequest
     */
    private void cooperativeAppointmentCheckToken(CooperativeAppointmentBO cooperativeAppointmentBO, HttpServletRequest httpServletRequest) {
        //缓存取出用户
        Map<String, Object> u = RedisUtils.getToken(stringRedisTemplate, httpServletRequest);
        if (MapUtils.isEmpty(u) || null == u.get("id")) {
            throw new BusinessException("无效的token!");
        }
        cooperativeAppointmentBO.setOperator(Convert.toStr(u.get("name")));
        cooperativeAppointmentBO.setOperatorId(Convert.toStr(u.get("id")));
        cooperativeAppointmentBO.setModifyName(u.get("name") + "-" + u.get("id"));
    }

}