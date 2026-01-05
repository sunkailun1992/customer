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
import com.gb.configuration.service.ProjectTypeService;
import com.gb.configuration.entity.query.ProjectTypeQuery;
import com.gb.configuration.entity.vo.ProjectTypeVO;
import com.gb.configuration.entity.bo.ProjectTypeBO;


/**
 * TODO 项目类型，Comment请求层
 * 代码生成器
 *
 * @author lijh
 * @className ProjectTypeController
 * @time 2022-05-05 02:45:25
 */
@Slf4j
@RequestRequired
@ApiSupport(author = "lijh")
@Setter(onMethod_ = {@Autowired})
@RestController
@RequestMapping("/project-type")
@Api(tags = "项目类型")
public class ProjectTypeController {


    /**
     * 项目类型
     */
    private ProjectTypeService projectTypeService;
    /**
     * redis操作
     */
    private StringRedisTemplate stringRedisTemplate;


    /**
     * TODO 分页
     *
     * @param projectTypeQuery 项目类型
     * @param current
     * @param size
     * @return Json<Page<ProjectTypeVO>>
     * @author lijh
     * @methodName select
     * @time 2022-05-05 02:45:25
     */
    @Methods(methodsName = "项目类型集合分页查询", methods = "select")
    @ApiOperation(value = "项目类型集合分页查询", httpMethod = "GET", notes = "项目类型集合查询", response = Json.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", dataType = "int", required = true),
            @ApiImplicitParam(name = "size", value = "分页显示数量", dataType = "int", required = true),
    })
    @GetMapping("/select")
    public Json<Page<ProjectTypeVO>> select(@Validated(value = ProjectTypeQuery.Select.class) ProjectTypeQuery projectTypeQuery, Integer current, Integer size) {
        //返回内容
        return new Json(ReturnCode.成功, projectTypeService.pageEnhance(new Page(current, size), projectTypeQuery));
    }


    /**
     * TODO 集合
     *
     * @param projectTypeQuery 项目类型
     * @return Json<List<ProjectTypeVO>>
     * @author lijh
     * @methodName selectList
     * @time 2022-05-05 02:45:25
     */
    @Methods(methodsName = "项目类型集合查询", methods = "selectList")
    @ApiOperation(value = "项目类型集合查询", httpMethod = "GET", notes = "项目类型集合查询", response = Json.class)
    @GetMapping("/selectList")
    public Json<List<ProjectTypeVO>> selectList(@Validated(value = ProjectTypeQuery.SelectList.class) ProjectTypeQuery projectTypeQuery) {
        //返回内容
        return new Json(ReturnCode.成功, projectTypeService.listEnhance(projectTypeQuery));
    }


    /**
     * TODO 单条
     *
     * @param projectTypeQuery 项目类型
     * @return Json<ProjectTypeVO>
     * @author lijh
     * @methodName selectOne
     * @time 2022-05-05 02:45:25
     */
    @Methods(methodsName = "项目类型单条查询", methods = "selectOne")
    @ApiOperation(value = "项目类型单条查询", httpMethod = "GET", notes = "项目类型单条查询", response = Json.class)
    @GetMapping("/selectOne")
    public Json<ProjectTypeVO> selectOne(@Validated(value = ProjectTypeQuery.SelectOne.class) ProjectTypeQuery projectTypeQuery) {
        //返回内容
        return new Json(ReturnCode.成功, projectTypeService.getOneEnhance(projectTypeQuery));
    }


    /**
     * TODO 总数
     *
     * @param projectTypeQuery 项目类型
     * @return Json<Integer>
     * @author lijh
     * @methodName count
     * @time 2022-05-05 02:45:25
     */
    @Methods(methodsName = "项目类型总数查询", methods = "count")
    @ApiOperation(value = "项目类型总数查询", httpMethod = "GET", notes = "项目类型总数查询", response = Json.class)
    @GetMapping(value = "/count")
    public Json<Integer> count(@Validated(value = ProjectTypeQuery.Count.class) ProjectTypeQuery projectTypeQuery) {
        //返回内容
        return new Json(ReturnCode.成功, projectTypeService.countEnhance(projectTypeQuery));
    }


    /**
     * TODO 新增
     *
     * @param projectTypeBO 项目类型
     * @return Json<String>
     * @author lijh
     * @methodName save
     * @time 2022-05-05 02:45:25
     */
    @PreventRepeat
    @Methods(methodsName = "项目类型新增", methods = "save")
    @ApiOperation(value = "项目类型新增", httpMethod = "POST", notes = "项目类型新增", response = Json.class)
    @ApiOperationSupport(ignoreParameters = {"id", "createDateTime", "createName", "modifyDateTime", "modifyName", "isDelete", "version"})
    @PostMapping("/save")
    public Json<String> save(@Validated(value = ProjectTypeBO.Save.class) ProjectTypeBO projectTypeBO, HttpServletRequest httpServletRequest) {
        //缓存取出用户
        Map<String, Object> u = RedisUtils.getToken(stringRedisTemplate, httpServletRequest);
        if (u != null) {
            projectTypeBO.setCreateName(u.get("name") + "-" + u.get("id"));
        }
        //返回内容
        return new Json(ReturnCode.成功, projectTypeService.saveEnhance(projectTypeBO));
    }


    /**
     * TODO 修改
     *
     * @param projectTypeBO 项目类型
     * @return Json<Boolean>
     * @author lijh
     * @methodName update
     * @time 2022-05-05 02:45:25
     */
    @PreventRepeat
    @Methods(methodsName = "项目类型修改", methods = "update")
    @ApiOperation(value = "项目类型修改", httpMethod = "PUT", notes = "项目类型修改", response = Json.class)
    @ApiOperationSupport(ignoreParameters = {"createDateTime", "createName", "modifyDateTime", "modifyName", "isDelete", "version"})
    @PutMapping("/update")
    public Json<Boolean> update(@Validated(value = ProjectTypeBO.Update.class) ProjectTypeBO projectTypeBO, HttpServletRequest httpServletRequest) {
        //缓存取出用户
        Map<String, Object> u = RedisUtils.getToken(stringRedisTemplate, httpServletRequest);
        if (u != null) {
            projectTypeBO.setModifyName(u.get("name") + "-" + u.get("id"));
        }
        //返回内容
        return new Json(ReturnCode.成功, projectTypeService.updateEnhance(projectTypeBO));
    }


    /**
     * TODO 删除
     *
     * @param projectTypeBO 项目类型
     * @return Json<Boolean>
     * @author lijh
     * @methodName remove
     * @time 2022-05-05 02:45:25
     */
    @Methods(methodsName = "项目类型删除", methods = "remove")
    @ApiOperation(value = "项目类型删除", httpMethod = "DELETE", notes = "项目类型删除", response = Json.class)
    @ApiOperationSupport(includeParameters = {"id"})
    @DeleteMapping("/remove")
    public Json<Boolean> remove(@Validated(value = ProjectTypeBO.Remove.class) ProjectTypeBO projectTypeBO) {
        return new Json(ReturnCode.成功, projectTypeService.removeEnhance(projectTypeBO));
    }
}