package com.gb.configuration.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gb.configuration.entity.ProjectType;
import com.gb.configuration.entity.bo.ProjectTypeBO;
import com.gb.configuration.entity.query.ProjectTypeQuery;
import com.gb.configuration.entity.vo.ProjectTypeVO;
import com.gb.configuration.mapper.ProjectTypeMapper;
import com.gb.configuration.service.ProjectTypeService;
import com.gb.configuration.service.query.ProjectTypeServiceQuery;
import com.gb.configuration.service.results.ProjectTypeServiceResults;
import com.gb.utils.GeneralConvertor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * TODO 项目类型，Service服务实现层
 * 代码生成器
 *
 * @author lijh
 * @className ProjectTypeServiceImpl
 * @time 2022-05-05 02:45:25
 */
@Slf4j
@Service
@Setter(onMethod_ = {@Autowired})
public class ProjectTypeServiceImpl extends ServiceImpl<ProjectTypeMapper, ProjectType> implements ProjectTypeService {


    /**
     * 项目类型
     */
    private ProjectTypeMapper projectTypeMapper;


    /**
     * 项目类型
     */
    private ProjectTypeServiceResults projectTypeServiceResults;


    /**
     * 项目类型增强条件
     */
    private ProjectTypeServiceQuery projectTypeServiceQuery;


    /**
     * TODO 集合
     *
     * @param projectTypeQuery 项目类型
     * @return List<ProjectTypeVO>
     * @author lijh
     * @methodName listEnhance
     * @time 2022-05-05 02:45:25
     */
    @Override
    public List<ProjectTypeVO> listEnhance(ProjectTypeQuery projectTypeQuery) {
        ProjectType projectType = GeneralConvertor.convertor(projectTypeQuery, ProjectType.class);
        QueryWrapper<ProjectType> queryWrapper = new QueryWrapper<>(projectType);
        // TODO 自动生成查询，禁止手动写语句
        projectTypeServiceQuery.query(projectTypeQuery, queryWrapper);
        // TODO 人工查询条件
        queryArtificial(projectTypeQuery, queryWrapper);
        // DO数据
        List<ProjectType> projectTypeDO = projectTypeMapper.selectList(queryWrapper);
        // VO数据
        List<ProjectTypeVO> projectTypeVO = GeneralConvertor.convertor(projectTypeDO, ProjectTypeVO.class);
        return projectTypeServiceResults.assignment(projectTypeVO);
    }


    /**
     * TODO 分页
     *
     * @param page
     * @param projectTypeQuery 项目类型
     * @return Page<ProjectTypeVO>
     * @author lijh
     * @methodName pageEnhance
     * @time 2022-05-05 02:45:25
     */
    @Override
    public Page<ProjectTypeVO> pageEnhance(Page page, ProjectTypeQuery projectTypeQuery) {
        ProjectType projectType = GeneralConvertor.convertor(projectTypeQuery, ProjectType.class);
        QueryWrapper<ProjectType> queryWrapper = new QueryWrapper<>(projectType);
        // TODO 自动生成查询，禁止手动写语句
        projectTypeServiceQuery.query(projectTypeQuery, queryWrapper);
        // TODO 人工查询条件
        queryArtificial(projectTypeQuery, queryWrapper);
        // DO数据
        Page<ProjectType> pageDO = projectTypeMapper.selectPage(page, queryWrapper);
        // VO数据
        Page<ProjectTypeVO> pageVO = projectTypeServiceResults.toPageVO(pageDO);
        return projectTypeServiceResults.assignment(pageVO);
    }


    /**
     * TODO 单条
     *
     * @param projectTypeQuery 项目类型
     * @return ProjectTypeVO
     * @author lijh
     * @methodName getOneEnhance
     * @time 2022-05-05 02:45:25
     */
    @Override
    public ProjectTypeVO getOneEnhance(ProjectTypeQuery projectTypeQuery) {
        ProjectType projectType = GeneralConvertor.convertor(projectTypeQuery, ProjectType.class);
        QueryWrapper<ProjectType> queryWrapper = new QueryWrapper<>(projectType);
        // TODO 自动生成查询，禁止手动写语句
        projectTypeServiceQuery.query(projectTypeQuery, queryWrapper);
        // TODO 人工查询条件
        queryArtificial(projectTypeQuery, queryWrapper);
        // DO数据
        ProjectType projectTypeDO = projectTypeMapper.selectOne(queryWrapper);
        // VO数据
        ProjectTypeVO projectTypeVO = GeneralConvertor.convertor(projectTypeDO, ProjectTypeVO.class);
        return projectTypeServiceResults.assignment(projectTypeVO);
    }


    /**
     * TODO 总数
     *
     * @param projectTypeQuery 项目类型
     * @return Integer
     * @author lijh
     * @methodName countEnhance
     * @time 2022-05-05 02:45:25
     */
    @Override
    public Long countEnhance(ProjectTypeQuery projectTypeQuery) {
        ProjectType projectType = GeneralConvertor.convertor(projectTypeQuery, ProjectType.class);
        QueryWrapper<ProjectType> queryWrapper = new QueryWrapper<>(projectType);
        // TODO 自动生成查询，禁止手动写语句
        projectTypeServiceQuery.query(projectTypeQuery, queryWrapper);
        // TODO 人工查询条件
        queryArtificial(projectTypeQuery, queryWrapper);
        return projectTypeMapper.selectCount(queryWrapper);
    }


    /**
     * TODO 新增
     *
     * @param projectTypeBO 项目类型
     * @return String
     * @author lijh
     * @methodName saveEnhance
     * @time 2022-05-05 02:45:25
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public String saveEnhance(ProjectTypeBO projectTypeBO) {
        ProjectType projectType = GeneralConvertor.convertor(projectTypeBO, ProjectType.class);
        projectTypeMapper.insert(projectType);
        return projectType.getId();
    }


    /**
     * TODO 修改
     *
     * @param projectTypeBO 项目类型
     * @return Boolean
     * @author lijh
     * @methodName updateEnhance
     * @time 2022-05-05 02:45:25
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public Boolean updateEnhance(ProjectTypeBO projectTypeBO) {
        ProjectType projectType = GeneralConvertor.convertor(projectTypeBO, ProjectType.class);
        UpdateWrapper<ProjectType> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", projectTypeBO.getId());
        Integer i = projectTypeMapper.update(projectType, updateWrapper);
        return i > 0 ? true : false;
    }


    /**
     * TODO 删除
     *
     * @param projectTypeBO 项目类型
     * @return Boolean
     * @author lijh
     * @methodName removeEnhance
     * @time 2022-05-05 02:45:25
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public Boolean removeEnhance(ProjectTypeBO projectTypeBO) {
        ProjectType projectType = GeneralConvertor.convertor(projectTypeBO, ProjectType.class);
        QueryWrapper<ProjectType> queryWrapper = new QueryWrapper<>(projectType);
        Integer i = projectTypeMapper.delete(queryWrapper);
        return i > 0 ? true : false;
    }


    /**
     * TODO 人工查询条件
     *
     * @param projectTypeQuery 项目类型
     * @return QueryWrapper
     * @author lijh
     * @methodName queryArtificial
     * @time 2022-05-05 02:45:25
     */
    private QueryWrapper queryArtificial(ProjectTypeQuery projectTypeQuery, QueryWrapper<ProjectType> queryWrapper) {
        queryWrapper.orderByAsc("sorting");
        return queryWrapper;
    }
}