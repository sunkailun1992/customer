package com.gb.configuration.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gb.configuration.entity.ProjectTypeValue;
import com.gb.configuration.entity.bo.ProjectTypeValueBO;
import com.gb.configuration.entity.query.ProjectTypeValueQuery;
import com.gb.configuration.entity.vo.ProjectTypeValueVO;
import com.gb.configuration.mapper.ProjectTypeValueMapper;
import com.gb.configuration.service.ProjectTypeValueService;
import com.gb.configuration.service.query.ProjectTypeValueServiceQuery;
import com.gb.configuration.service.results.ProjectTypeValueServiceResults;
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
 * TODO 项目类型值，Service服务实现层
 * 代码生成器
 *
 * @author lijh
 * @className ProjectTypeValueServiceImpl
 * @time 2022-05-05 02:45:25
 */
@Slf4j
@Service
@Setter(onMethod_ = {@Autowired})
public class ProjectTypeValueServiceImpl extends ServiceImpl<ProjectTypeValueMapper, ProjectTypeValue> implements ProjectTypeValueService {


    /**
     * 项目类型值
     */
    private ProjectTypeValueMapper projectTypeValueMapper;


    /**
     * 项目类型值
     */
    private ProjectTypeValueServiceResults projectTypeValueServiceResults;


    /**
     * 项目类型值增强条件
     */
    private ProjectTypeValueServiceQuery projectTypeValueServiceQuery;


    /**
     * TODO 集合
     *
     * @param projectTypeValueQuery 项目类型值
     * @return List<ProjectTypeValueVO>
     * @author lijh
     * @methodName listEnhance
     * @time 2022-05-05 02:45:25
     */
    @Override
    public List<ProjectTypeValueVO> listEnhance(ProjectTypeValueQuery projectTypeValueQuery) {
        ProjectTypeValue projectTypeValue = GeneralConvertor.convertor(projectTypeValueQuery, ProjectTypeValue.class);
        QueryWrapper<ProjectTypeValue> queryWrapper = new QueryWrapper<>(projectTypeValue);
        // TODO 自动生成查询，禁止手动写语句
        projectTypeValueServiceQuery.query(projectTypeValueQuery, queryWrapper);
        // TODO 人工查询条件
        queryArtificial(projectTypeValueQuery, queryWrapper);
        // DO数据
        List<ProjectTypeValue> projectTypeValueDO = projectTypeValueMapper.selectList(queryWrapper);
        // VO数据
        List<ProjectTypeValueVO> projectTypeValueVO = GeneralConvertor.convertor(projectTypeValueDO, ProjectTypeValueVO.class);
        return projectTypeValueServiceResults.assignment(projectTypeValueVO);
    }


    /**
     * TODO 分页
     *
     * @param page
     * @param projectTypeValueQuery 项目类型值
     * @return Page<ProjectTypeValueVO>
     * @author lijh
     * @methodName pageEnhance
     * @time 2022-05-05 02:45:25
     */
    @Override
    public Page<ProjectTypeValueVO> pageEnhance(Page page, ProjectTypeValueQuery projectTypeValueQuery) {
        ProjectTypeValue projectTypeValue = GeneralConvertor.convertor(projectTypeValueQuery, ProjectTypeValue.class);
        QueryWrapper<ProjectTypeValue> queryWrapper = new QueryWrapper<>(projectTypeValue);
        // TODO 自动生成查询，禁止手动写语句
        projectTypeValueServiceQuery.query(projectTypeValueQuery, queryWrapper);
        // TODO 人工查询条件
        queryArtificial(projectTypeValueQuery, queryWrapper);
        // DO数据
        Page<ProjectTypeValue> pageDO = projectTypeValueMapper.selectPage(page, queryWrapper);
        // VO数据
        Page<ProjectTypeValueVO> pageVO = projectTypeValueServiceResults.toPageVO(pageDO);
        return projectTypeValueServiceResults.assignment(pageVO);
    }


    /**
     * TODO 单条
     *
     * @param projectTypeValueQuery 项目类型值
     * @return ProjectTypeValueVO
     * @author lijh
     * @methodName getOneEnhance
     * @time 2022-05-05 02:45:25
     */
    @Override
    public ProjectTypeValueVO getOneEnhance(ProjectTypeValueQuery projectTypeValueQuery) {
        ProjectTypeValue projectTypeValue = GeneralConvertor.convertor(projectTypeValueQuery, ProjectTypeValue.class);
        QueryWrapper<ProjectTypeValue> queryWrapper = new QueryWrapper<>(projectTypeValue);
        // TODO 自动生成查询，禁止手动写语句
        projectTypeValueServiceQuery.query(projectTypeValueQuery, queryWrapper);
        // TODO 人工查询条件
        queryArtificial(projectTypeValueQuery, queryWrapper);
        // DO数据
        ProjectTypeValue projectTypeValueDO = projectTypeValueMapper.selectOne(queryWrapper);
        // VO数据
        ProjectTypeValueVO projectTypeValueVO = GeneralConvertor.convertor(projectTypeValueDO, ProjectTypeValueVO.class);
        return projectTypeValueServiceResults.assignment(projectTypeValueVO);
    }


    /**
     * TODO 总数
     *
     * @param projectTypeValueQuery 项目类型值
     * @return Integer
     * @author lijh
     * @methodName countEnhance
     * @time 2022-05-05 02:45:25
     */
    @Override
    public Long countEnhance(ProjectTypeValueQuery projectTypeValueQuery) {
        ProjectTypeValue projectTypeValue = GeneralConvertor.convertor(projectTypeValueQuery, ProjectTypeValue.class);
        QueryWrapper<ProjectTypeValue> queryWrapper = new QueryWrapper<>(projectTypeValue);
        // TODO 自动生成查询，禁止手动写语句
        projectTypeValueServiceQuery.query(projectTypeValueQuery, queryWrapper);
        // TODO 人工查询条件
        queryArtificial(projectTypeValueQuery, queryWrapper);
        return projectTypeValueMapper.selectCount(queryWrapper);
    }


    /**
     * TODO 新增
     *
     * @param projectTypeValueBO 项目类型值
     * @return String
     * @author lijh
     * @methodName saveEnhance
     * @time 2022-05-05 02:45:25
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public String saveEnhance(ProjectTypeValueBO projectTypeValueBO) {
        ProjectTypeValue projectTypeValue = GeneralConvertor.convertor(projectTypeValueBO, ProjectTypeValue.class);
        projectTypeValueMapper.insert(projectTypeValue);
        return projectTypeValue.getId();
    }


    /**
     * TODO 修改
     *
     * @param projectTypeValueBO 项目类型值
     * @return Boolean
     * @author lijh
     * @methodName updateEnhance
     * @time 2022-05-05 02:45:25
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public Boolean updateEnhance(ProjectTypeValueBO projectTypeValueBO) {
        ProjectTypeValue projectTypeValue = GeneralConvertor.convertor(projectTypeValueBO, ProjectTypeValue.class);
        UpdateWrapper<ProjectTypeValue> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", projectTypeValueBO.getId());
        Integer i = projectTypeValueMapper.update(projectTypeValue, updateWrapper);
        return i > 0 ? true : false;
    }


    /**
     * TODO 删除
     *
     * @param projectTypeValueBO 项目类型值
     * @return Boolean
     * @author lijh
     * @methodName removeEnhance
     * @time 2022-05-05 02:45:25
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public Boolean removeEnhance(ProjectTypeValueBO projectTypeValueBO) {
        ProjectTypeValue projectTypeValue = GeneralConvertor.convertor(projectTypeValueBO, ProjectTypeValue.class);
        QueryWrapper<ProjectTypeValue> queryWrapper = new QueryWrapper<>(projectTypeValue);
        Integer i = projectTypeValueMapper.delete(queryWrapper);
        return i > 0 ? true : false;
    }


    /**
     * TODO 人工查询条件
     *
     * @param projectTypeValueQuery 项目类型值
     * @return QueryWrapper
     * @author lijh
     * @methodName queryArtificial
     * @time 2022-05-05 02:45:25
     */
    private QueryWrapper queryArtificial(ProjectTypeValueQuery projectTypeValueQuery, QueryWrapper<ProjectTypeValue> queryWrapper) {
        queryWrapper.orderByAsc("sorting");
        return queryWrapper;
    }
}