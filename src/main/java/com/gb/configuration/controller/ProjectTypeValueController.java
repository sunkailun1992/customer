package com.gb.configuration.controller;

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
import com.gb.configuration.service.ProjectTypeValueService;
import com.gb.configuration.entity.query.ProjectTypeValueQuery;
import com.gb.configuration.entity.vo.ProjectTypeValueVO;
import com.gb.configuration.entity.bo.ProjectTypeValueBO;


/**
 * TODO 项目类型值，Comment请求层
 * 代码生成器
 *
 * @author lijh
 * @className ProjectTypeValueController
 * @time 2022-05-05 02:45:25
 */
@Slf4j
@RequestRequired
@ApiSupport(author = "lijh")
@Setter(onMethod_ = {@Autowired})
@RestController
@RequestMapping("/project-type-value")
@Api(tags = "项目类型值")
public class ProjectTypeValueController {


    /**
     * 项目类型值
     */
    private ProjectTypeValueService projectTypeValueService;
    /**
     * redis操作
     */
    private StringRedisTemplate stringRedisTemplate;


    /**
     * TODO 分页
     *
     * @param projectTypeValueQuery 项目类型值
     * @param current
     * @param size
     * @return Json<Page<ProjectTypeValueVO>>
     * @author lijh
     * @methodName select
     * @time 2022-05-05 02:45:25
     */
    @Methods(methodsName = "项目类型值集合分页查询", methods = "select")
    @ApiOperation(value = "项目类型值集合分页查询", httpMethod = "GET", notes = "项目类型值集合查询", response = Json.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", dataType = "int", required = true),
            @ApiImplicitParam(name = "size", value = "分页显示数量", dataType = "int", required = true),
    })
    @GetMapping("/select")
    public Json<Page<ProjectTypeValueVO>> select(@Validated(value = ProjectTypeValueQuery.Select.class) ProjectTypeValueQuery projectTypeValueQuery, Integer current, Integer size) {
        //返回内容
        return new Json(ReturnCode.成功, projectTypeValueService.pageEnhance(new Page(current, size), projectTypeValueQuery));
    }


    /**
     * TODO 集合
     *
     * @param projectTypeValueQuery 项目类型值
     * @return Json<List<ProjectTypeValueVO>>
     * @author lijh
     * @methodName selectList
     * @time 2022-05-05 02:45:25
     */
    @Methods(methodsName = "项目类型值集合查询", methods = "selectList")
    @ApiOperation(value = "项目类型值集合查询", httpMethod = "GET", notes = "项目类型值集合查询", response = Json.class)
    @GetMapping("/selectList")
    public Json<List<ProjectTypeValueVO>> selectList(@Validated(value = ProjectTypeValueQuery.SelectList.class) ProjectTypeValueQuery projectTypeValueQuery) {
        //返回内容
        return new Json(ReturnCode.成功, projectTypeValueService.listEnhance(projectTypeValueQuery));
    }


    /**
     * TODO 单条
     *
     * @param projectTypeValueQuery 项目类型值
     * @return Json<ProjectTypeValueVO>
     * @author lijh
     * @methodName selectOne
     * @time 2022-05-05 02:45:25
     */
    @Methods(methodsName = "项目类型值单条查询", methods = "selectOne")
    @ApiOperation(value = "项目类型值单条查询", httpMethod = "GET", notes = "项目类型值单条查询", response = Json.class)
    @GetMapping("/selectOne")
    public Json<ProjectTypeValueVO> selectOne(@Validated(value = ProjectTypeValueQuery.SelectOne.class) ProjectTypeValueQuery projectTypeValueQuery) {
        //返回内容
        return new Json(ReturnCode.成功, projectTypeValueService.getOneEnhance(projectTypeValueQuery));
    }


    /**
     * TODO 总数
     *
     * @param projectTypeValueQuery 项目类型值
     * @return Json<Integer>
     * @author lijh
     * @methodName count
     * @time 2022-05-05 02:45:25
     */
    @Methods(methodsName = "项目类型值总数查询", methods = "count")
    @ApiOperation(value = "项目类型值总数查询", httpMethod = "GET", notes = "项目类型值总数查询", response = Json.class)
    @GetMapping(value = "/count")
    public Json<Integer> count(@Validated(value = ProjectTypeValueQuery.Count.class) ProjectTypeValueQuery projectTypeValueQuery) {
        //返回内容
        return new Json(ReturnCode.成功, projectTypeValueService.countEnhance(projectTypeValueQuery));
    }


    /**
     * TODO 新增
     *
     * @param projectTypeValueBO 项目类型值
     * @return Json<String>
     * @author lijh
     * @methodName save
     * @time 2022-05-05 02:45:25
     */
    @PreventRepeat
    @Methods(methodsName = "项目类型值新增", methods = "save")
    @ApiOperation(value = "项目类型值新增", httpMethod = "POST", notes = "项目类型值新增", response = Json.class)
    @ApiOperationSupport(ignoreParameters = {"id", "createDateTime", "createName", "modifyDateTime", "modifyName", "isDelete", "version"})
    @PostMapping("/save")
    public Json<String> save(@Validated(value = ProjectTypeValueBO.Save.class) ProjectTypeValueBO projectTypeValueBO, HttpServletRequest httpServletRequest) {
        //缓存取出用户
        Map<String, Object> u = RedisUtils.getToken(stringRedisTemplate, httpServletRequest);
        if (u != null) {
            projectTypeValueBO.setCreateName(u.get("name") + "-" + u.get("id"));
        }
        //返回内容
        return new Json(ReturnCode.成功, projectTypeValueService.saveEnhance(projectTypeValueBO));
    }


    /**
     * TODO 修改
     *
     * @param projectTypeValueBO 项目类型值
     * @return Json<Boolean>
     * @author lijh
     * @methodName update
     * @time 2022-05-05 02:45:25
     */
    @PreventRepeat
    @Methods(methodsName = "项目类型值修改", methods = "update")
    @ApiOperation(value = "项目类型值修改", httpMethod = "PUT", notes = "项目类型值修改", response = Json.class)
    @ApiOperationSupport(ignoreParameters = {"createDateTime", "createName", "modifyDateTime", "modifyName", "isDelete", "version"})
    @PutMapping("/update")
    public Json<Boolean> update(@Validated(value = ProjectTypeValueBO.Update.class) ProjectTypeValueBO projectTypeValueBO, HttpServletRequest httpServletRequest) {
        //缓存取出用户
        Map<String, Object> u = RedisUtils.getToken(stringRedisTemplate, httpServletRequest);
        if (u != null) {
            projectTypeValueBO.setModifyName(u.get("name") + "-" + u.get("id"));
        }
        //返回内容
        return new Json(ReturnCode.成功, projectTypeValueService.updateEnhance(projectTypeValueBO));
    }


    /**
     * TODO 删除
     *
     * @param projectTypeValueBO 项目类型值
     * @return Json<Boolean>
     * @author lijh
     * @methodName remove
     * @time 2022-05-05 02:45:25
     */
    @Methods(methodsName = "项目类型值删除", methods = "remove")
    @ApiOperation(value = "项目类型值删除", httpMethod = "DELETE", notes = "项目类型值删除", response = Json.class)
    @ApiOperationSupport(includeParameters = {"id"})
    @DeleteMapping("/remove")
    public Json<Boolean> remove(@Validated(value = ProjectTypeValueBO.Remove.class) ProjectTypeValueBO projectTypeValueBO) {
        return new Json(ReturnCode.成功, projectTypeValueService.removeEnhance(projectTypeValueBO));
    }
}