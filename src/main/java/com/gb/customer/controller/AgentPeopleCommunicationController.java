package com.gb.customer.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import java.util.Map;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import com.gb.utils.annotations.RequestRequired;
import java.util.Optional;
import com.gb.utils.enumeration.ReturnCode;
import com.gb.utils.annotations.Methods;
import com.gb.utils.Json;
import com.gb.utils.RedisUtils;
import com.gb.utils.annotations.PreventRepeat;
import javax.servlet.http.HttpServletRequest;

import com.gb.customer.service.AgentPeopleCommunicationService;
import com.gb.customer.entity.AgentPeopleCommunication;
/**
 * <p>
 * 经纪人沟通 前端控制器
 * </p>
 *
 * @author lijh
 * @since 2021-06-11
 */
@Slf4j
@RequestRequired
@RestController
@Setter(onMethod_ = {@Autowired})
@RequestMapping("/agent-people-communication")
@Api(tags = "经纪人沟通")
public class AgentPeopleCommunicationController {

    /**
     * 经纪人沟通
     */
    private AgentPeopleCommunicationService agentPeopleCommunicationService;
    /**
     * redis操作
     */
    private StringRedisTemplate stringRedisTemplate;


    /**
     * 经纪人沟通集合查询
     *
     * @param current:
     * @param size:
     * @param agentPeopleCommunication:
     * @return com.utils.Json
     * @author lijh
     * @since 2021-06-11
     */
    @Methods(methodsName = "经纪人沟通集合查询", methods = "select")
    @ApiOperation(value = "经纪人沟通集合查询", httpMethod = "GET", notes = "经纪人沟通集合查询", response = Json.class)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "current", value = "当前页", dataType = "int"),
        @ApiImplicitParam(name = "size", value = "分页显示数量", dataType = "int"),
    })
    @GetMapping("/select")
    public Json<IPage<AgentPeopleCommunication>> select(Integer current, Integer size, AgentPeopleCommunication agentPeopleCommunication) {
        if (current != null && size != null) {
            Page page = new Page(current, size);
            //返回内容
            return new Json(ReturnCode.成功, agentPeopleCommunicationService.pageEnhance(page, agentPeopleCommunication));
        } else {
            //返回内容
            return new Json(ReturnCode.成功, agentPeopleCommunicationService.listEnhance(agentPeopleCommunication));
        }
    }


    /**
     * 经纪人沟通单条查询
     *
     * @param agentPeopleCommunication:
     * @return com.utils.Json
     * @author lijh
     * @since 2021-06-11
     */
    @Methods(methodsName = "经纪人沟通单条查询", methods = "selectOne")
    @ApiOperation(value = "经纪人沟通单条查询", httpMethod = "GET", notes = "经纪人沟通单条查询", response = Json.class)
    @GetMapping("/selectOne")
    public Json<AgentPeopleCommunication> selectOne(AgentPeopleCommunication agentPeopleCommunication) {
        //返回内容
        return new Json(ReturnCode.成功, agentPeopleCommunicationService.getOneEnhance(agentPeopleCommunication));
    }


    /**
     * 经纪人沟通总数查询
     *
     * @param agentPeopleCommunication:
     * @return com.utils.Json
     * @author lijh
     * @since 2021-06-11
     */
    @Methods(methodsName = "经纪人沟通总数查询", methods = "count")
    @ApiOperation(value = "经纪人沟通总数查询", httpMethod = "GET", notes = "经纪人沟通总数查询", response = Json.class)
    @GetMapping(value = "/count")
    public Json<Integer> count(AgentPeopleCommunication agentPeopleCommunication) {
        //返回内容
        return new Json(ReturnCode.成功, agentPeopleCommunicationService.countEnhance(agentPeopleCommunication));
    }


   /**
    * 经纪人沟通新增
    *
    * @param agentPeopleCommunication:
    * @return com.utils.Json
    * @author lijh
    * @since 2021-06-11
    */
    @PreventRepeat
    @Methods(methodsName = "经纪人沟通新增", methods = "save")
    @ApiOperation(value = "经纪人沟通新增", httpMethod = "POST", notes = "经纪人沟通新增", response = Json.class)
    @PostMapping("/save")
    public Json<String> save(AgentPeopleCommunication agentPeopleCommunication, HttpServletRequest httpServletRequest) {
        //缓存取出用户
        Map<String, Object> u = RedisUtils.getToken(stringRedisTemplate, httpServletRequest);
        if(u != null){
            agentPeopleCommunication.setCreateName(u.get("name") + "-" + u.get("id"));
        }
        Boolean b = agentPeopleCommunicationService.saveEnhance(agentPeopleCommunication);
        if (b) {
            //返回内容
            return new Json(ReturnCode.成功, agentPeopleCommunication.getId());
        } else {
            //返回内容
            return new Json(ReturnCode.系统执行出错, "新增参数数量为空");
        }

    }


    /**
     * 经纪人沟通修改
     *
     * @param agentPeopleCommunication:
     * @return com.utils.Json
     * @author lijh
     * @since 2021-06-11
     */
    @PreventRepeat
    @Methods(methodsName = "经纪人沟通修改", methods = "update")
    @ApiOperation(value = "经纪人沟通修改", httpMethod = "PUT", notes = "经纪人沟通修改", response = Json.class)
    @PutMapping("/update")
    public Json<Boolean> update(AgentPeopleCommunication agentPeopleCommunication, HttpServletRequest httpServletRequest) {
        //缓存取出用户
        Map<String, Object> u = RedisUtils.getToken(stringRedisTemplate, httpServletRequest);
        if(u != null){
            agentPeopleCommunication.setModifyName(u.get("name") + "-" + u.get("id"));
        }
        //返回内容
        return new Json(ReturnCode.成功, agentPeopleCommunicationService.updateEnhance(agentPeopleCommunication));
    }


    /**
     * 经纪人沟通删除
     *
     * @param agentPeopleCommunication:
     * @return com.utils.Json
     * @author lijh
     * @since 2021-06-11
     */
    @Methods(methodsName = "经纪人沟通删除", methods = "remove")
    @ApiOperation(value = "经纪人沟通删除", httpMethod = "DELETE", notes = "经纪人沟通删除", response = Json.class)
    @DeleteMapping("/remove")
    public Json<Boolean> remove(AgentPeopleCommunication agentPeopleCommunication) {
        return new Json(ReturnCode.成功, agentPeopleCommunicationService.removeEnhance(agentPeopleCommunication));
    }


}
