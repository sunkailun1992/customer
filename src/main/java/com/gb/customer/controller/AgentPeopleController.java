package com.gb.customer.controller;

import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gb.customer.entity.AgentPeople;
import com.gb.customer.enums.DataPermissionsEnum;
import com.gb.customer.service.AgentPeopleService;
import com.gb.customer.service.impl.CheckPermissions;
import com.gb.rpc.entity.UserAgentCertification;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 经纪人 前端控制器
 * </p>
 *
 * @author lijh
 * @since 2021-06-11
 */
@Slf4j
@RequestRequired
@RestController
@Setter(onMethod_ = {@Autowired})
@RequestMapping("/agent-people")
@Api(tags = "经纪人")
public class AgentPeopleController {

    /**
     * 经纪人
     */
    private AgentPeopleService agentPeopleService;
    /**
     * redis操作
     */
    private StringRedisTemplate stringRedisTemplate;

    private CheckPermissions checkPermissions;

    private RpcComponent rpcComponent;


    /**
     * 经纪人集合查询
     *
     * @param current:
     * @param size:
     * @param agentPeople:
     * @return com.utils.Json
     * @author lijh
     * @since 2021-06-11
     */
    @Methods(methodsName = "经纪人集合查询", methods = "select")
    @ApiOperation(value = "经纪人集合查询", httpMethod = "GET", notes = "经纪人集合查询", response = Json.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", dataType = "int"),
            @ApiImplicitParam(name = "size", value = "分页显示数量", dataType = "int"),
    })
    @GetMapping("/select")
    public Json<IPage<AgentPeople>> select(Integer current, Integer size, AgentPeople agentPeople, HttpServletRequest httpServletRequest) {
        //缓存取出用户
        Map<String, Object> u = RedisUtils.getToken(stringRedisTemplate, httpServletRequest);
        if (null == u) {
            throw new BusinessException("token过期，请重新登录");
        }
        List<String> strings = checkPermissions.checkUserPermissions(u);
        agentPeople.setAgentUserId(agentPeople.getUserIdQuery());
        log.info("当前用户所属的用户组:" + strings);
        //非指定管家，默认获取当前账号下数据
        if (checkPermissions.checkDataPermissions(DataPermissionsEnum.AGENT_PEOPLE_SELECT, strings)) {
            agentPeople.setAgentUserId((String) u.get("id"));
        }
        if (current != null && size != null) {
            Page page = new Page(current, size);
            //返回内容
            return new Json(ReturnCode.成功, agentPeopleService.pageEnhance(page, agentPeople));
        } else {
            //返回内容
            return new Json(ReturnCode.成功, agentPeopleService.listEnhance(agentPeople));
        }
    }

    @Methods(methodsName = "根据管家id获取经纪人集合", methods = "selectByAgentUserId")
    @ApiOperation(value = "根据管家id获取经纪人集合", httpMethod = "GET", notes = "根据管家id获取经纪人集合", response = Json.class)
    @GetMapping("/selectByAgentUserId")
    public Json<AgentPeople> selectByAgentUserId(AgentPeople agentPeople) {
        //返回内容
        return new Json(ReturnCode.成功, agentPeopleService.listEnhance(agentPeople));
    }


    /**
     * 经纪人单条查询
     *
     * @param agentPeople:
     * @return com.utils.Json
     * @author lijh
     * @since 2021-06-11
     */
    @Methods(methodsName = "经纪人单条查询", methods = "selectOne")
    @ApiOperation(value = "经纪人单条查询", httpMethod = "GET", notes = "经纪人单条查询", response = Json.class)
    @GetMapping("/selectOne")
    public Json<AgentPeople> selectOne(AgentPeople agentPeople) {
        log.info("[查询单个经纪人]请求参数={}", agentPeople);
        Map<String, Object> map = new HashMap<>(1);
        map.put("userId", agentPeople.getUserId());
        UserExtendsVO userExtendsVO = rpcComponent.rpcQueryInfo(map, RpcTypeEnum.GET_USER_EXTENDS_ONE, UserExtendsVO.class);
        agentPeople.setMobile(Convert.toStr(userExtendsVO.getMobile()));
        //返回内容
        return new Json(ReturnCode.成功, agentPeopleService.getOneEnhance(agentPeople));
    }


    /**
     * 经纪人总数查询
     *
     * @param agentPeople:
     * @return com.utils.Json
     * @author lijh
     * @since 2021-06-11
     */
    @Methods(methodsName = "经纪人总数查询", methods = "count")
    @ApiOperation(value = "经纪人总数查询", httpMethod = "GET", notes = "经纪人总数查询", response = Json.class)
    @GetMapping(value = "/count")
    public Json<Integer> count(AgentPeople agentPeople) {
        //返回内容
        return new Json(ReturnCode.成功, agentPeopleService.countEnhance(agentPeople));
    }


    /**
     * 经纪人新增
     *
     * @param agentPeople:
     * @return com.utils.Json
     * @author lijh
     * @since 2021-06-11
     */
    @PreventRepeat
    @Methods(methodsName = "经纪人新增", methods = "save")
    @ApiOperation(value = "经纪人新增", httpMethod = "POST", notes = "经纪人新增", response = Json.class)
    @PostMapping("/save")
    public Json<String> save(AgentPeople agentPeople, HttpServletRequest httpServletRequest) {
        //缓存取出用户
        Map<String, Object> u = RedisUtils.getToken(stringRedisTemplate, httpServletRequest);
        if (u != null) {
            agentPeople.setCreateName(u.get("name") + "-" + u.get("id"));
        }
        Boolean b = agentPeopleService.saveEnhance(agentPeople);
        if (b) {
            //返回内容
            return new Json(ReturnCode.成功, agentPeople.getId());
        } else {
            //返回内容
            return new Json(ReturnCode.系统执行出错, "新增参数数量为空");
        }

    }


    /**
     * 经纪人修改
     *
     * @param agentPeople:
     * @return com.utils.Json
     * @author lijh
     * @since 2021-06-11
     */
    @PreventRepeat
    @Methods(methodsName = "经纪人修改", methods = "update")
    @ApiOperation(value = "经纪人修改", httpMethod = "PUT", notes = "经纪人修改", response = Json.class)
    @PutMapping("/update")
    public Json<Boolean> update(AgentPeople agentPeople, HttpServletRequest httpServletRequest) {
        //缓存取出用户
        Map<String, Object> u = RedisUtils.getToken(stringRedisTemplate, httpServletRequest);
        if (u != null) {
            agentPeople.setModifyName(u.get("name") + "-" + u.get("id"));
        }
        //返回内容
        return new Json(ReturnCode.成功, agentPeopleService.updateEnhance(agentPeople));
    }


    /**
     * 经纪人删除
     *
     * @param agentPeople:
     * @return com.utils.Json
     * @author lijh
     * @since 2021-06-11
     */
    @Methods(methodsName = "经纪人删除", methods = "remove")
    @ApiOperation(value = "经纪人删除", httpMethod = "DELETE", notes = "经纪人删除", response = Json.class)
    @DeleteMapping("/remove")
    public Json<Boolean> remove(AgentPeople agentPeople) {
        return new Json(ReturnCode.成功, agentPeopleService.removeEnhance(agentPeople));
    }

    /**
     * 经纪人认证信息查询
     *
     * @param agentUserId:
     * @return com.utils.Json
     * @author lijh
     * @since 2021-06-11
     */
    @Methods(methodsName = "经纪人认证信息查询", methods = "getAgentUserCertification")
    @ApiOperation(value = "经纪人认证信息查询", httpMethod = "GET", notes = "经纪人认证信息查询", response = Json.class)
    @GetMapping(value = "/getAgentUserCertification")
    public Json<Integer> getAgentUserCertification(String agentUserId) {
        Map<String, Object> map = new HashMap<>(1);
        map.put("userId", agentUserId);
        UserAgentCertification userAgentCertification = rpcComponent.rpcQueryInfo(map, RpcTypeEnum.GET_USER_AGENT_CERTIFICATION, UserAgentCertification.class);
        return new Json(ReturnCode.成功, userAgentCertification);
    }

}
