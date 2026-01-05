package com.gb.promoteform.service.impl;

import com.gb.promoteform.entity.query.PromoteFormWindowQuery;
import com.gb.promoteform.entity.vo.PromoteFormWindowVO;
import com.gb.promoteform.entity.bo.PromoteFormWindowBO;
import com.gb.promoteform.entity.PromoteFormWindow;
import com.gb.promoteform.mapper.PromoteFormWindowMapper;
import com.gb.promoteform.service.PromoteFormWindowService;
import com.gb.promoteform.service.query.PromoteFormWindowServiceQuery;
import com.gb.promoteform.service.results.PromoteFormWindowServiceResults;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Setter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import com.gb.utils.GeneralConvertor;

import java.util.List;


/**
 * TODO 推广表单窗口，Service服务实现层
 * 代码生成器
 *
 * @author lijh
 * @className PromoteFormWindowServiceImpl
 * @time 2022-07-04 10:49:05
 */
@Slf4j
@Service
@Setter(onMethod_ = {@Autowired})
public class PromoteFormWindowServiceImpl extends ServiceImpl<PromoteFormWindowMapper, PromoteFormWindow> implements PromoteFormWindowService {


    /**
     * 推广表单窗口
     */
    private PromoteFormWindowMapper promoteFormWindowMapper;


    /**
     * 推广表单窗口
     */
    private PromoteFormWindowServiceResults promoteFormWindowServiceResults;


    /**
     * 推广表单窗口增强条件
     */
    private PromoteFormWindowServiceQuery promoteFormWindowServiceQuery;


    /**
     * TODO 集合
     *
     * @param promoteFormWindowQuery 推广表单窗口
     * @return List<PromoteFormWindowVO>
     * @author lijh
     * @methodName listEnhance
     * @time 2022-07-04 10:49:05
     */
    @Override
    public List<PromoteFormWindowVO> listEnhance(PromoteFormWindowQuery promoteFormWindowQuery) {
        PromoteFormWindow promoteFormWindow = GeneralConvertor.convertor(promoteFormWindowQuery, PromoteFormWindow.class);
        QueryWrapper<PromoteFormWindow> queryWrapper = new QueryWrapper<>(promoteFormWindow);
        // TODO 自动生成查询，禁止手动写语句
        promoteFormWindowServiceQuery.query(promoteFormWindowQuery, queryWrapper);
        // TODO 人工查询条件
        queryArtificial(promoteFormWindowQuery, queryWrapper);
        // DO数据
        List<PromoteFormWindow> promoteFormWindowDO = promoteFormWindowMapper.selectList(queryWrapper);
        // VO数据
        List<PromoteFormWindowVO> promoteFormWindowVO = GeneralConvertor.convertor(promoteFormWindowDO, PromoteFormWindowVO.class);
        // 判断是否增强
        if (promoteFormWindowQuery.getAssignment() == null) {
            return promoteFormWindowServiceResults.assignment(promoteFormWindowVO);
        } else {
            return promoteFormWindowQuery.getAssignment() ? promoteFormWindowServiceResults.assignment(promoteFormWindowVO) : promoteFormWindowVO;
        }
    }


    /**
     * TODO 分页
     *
     * @param page
     * @param promoteFormWindowQuery 推广表单窗口
     * @return Page<PromoteFormWindowVO>
     * @author lijh
     * @methodName pageEnhance
     * @time 2022-07-04 10:49:05
     */
    @Override
    public Page<PromoteFormWindowVO> pageEnhance(Page page, PromoteFormWindowQuery promoteFormWindowQuery) {
        PromoteFormWindow promoteFormWindow = GeneralConvertor.convertor(promoteFormWindowQuery, PromoteFormWindow.class);
        QueryWrapper<PromoteFormWindow> queryWrapper = new QueryWrapper<>(promoteFormWindow);
        // TODO 自动生成查询，禁止手动写语句
        promoteFormWindowServiceQuery.query(promoteFormWindowQuery, queryWrapper);
        // TODO 人工查询条件
        queryArtificial(promoteFormWindowQuery, queryWrapper);
        // DO数据
        Page<PromoteFormWindow> pageDO = promoteFormWindowMapper.selectPage(page, queryWrapper);
        // VO数据
        Page<PromoteFormWindowVO> pageVO = promoteFormWindowServiceResults.toPageVO(pageDO);
        // 判断是否增强
        if (promoteFormWindowQuery.getAssignment() == null) {
            return promoteFormWindowServiceResults.assignment(pageVO);
        } else {
            return promoteFormWindowQuery.getAssignment() ? promoteFormWindowServiceResults.assignment(pageVO) : pageVO;
        }
    }


    /**
     * TODO 单条
     *
     * @param promoteFormWindowQuery 推广表单窗口
     * @return PromoteFormWindowVO
     * @author lijh
     * @methodName getOneEnhance
     * @time 2022-07-04 10:49:05
     */
    @Override
    public PromoteFormWindowVO getOneEnhance(PromoteFormWindowQuery promoteFormWindowQuery) {
        PromoteFormWindow promoteFormWindow = GeneralConvertor.convertor(promoteFormWindowQuery, PromoteFormWindow.class);
        QueryWrapper<PromoteFormWindow> queryWrapper = new QueryWrapper<>(promoteFormWindow);
        // TODO 自动生成查询，禁止手动写语句
        promoteFormWindowServiceQuery.query(promoteFormWindowQuery, queryWrapper);
        // TODO 人工查询条件
        queryArtificial(promoteFormWindowQuery, queryWrapper);
        // DO数据
        PromoteFormWindow promoteFormWindowDO = promoteFormWindowMapper.selectOne(queryWrapper);
        // VO数据
        PromoteFormWindowVO promoteFormWindowVO = GeneralConvertor.convertor(promoteFormWindowDO, PromoteFormWindowVO.class);
        // 判断是否增强
        if (promoteFormWindowQuery.getAssignment() == null) {
            return promoteFormWindowServiceResults.assignment(promoteFormWindowVO);
        } else {
            return promoteFormWindowQuery.getAssignment() ? promoteFormWindowServiceResults.assignment(promoteFormWindowVO) : promoteFormWindowVO;
        }
    }


    /**
     * TODO 总数
     *
     * @param promoteFormWindowQuery 推广表单窗口
     * @return Integer
     * @author lijh
     * @methodName countEnhance
     * @time 2022-07-04 10:49:05
     */
    @Override
    public Long countEnhance(PromoteFormWindowQuery promoteFormWindowQuery) {
        PromoteFormWindow promoteFormWindow = GeneralConvertor.convertor(promoteFormWindowQuery, PromoteFormWindow.class);
        QueryWrapper<PromoteFormWindow> queryWrapper = new QueryWrapper<>(promoteFormWindow);
        // TODO 自动生成查询，禁止手动写语句
        promoteFormWindowServiceQuery.query(promoteFormWindowQuery, queryWrapper);
        // TODO 人工查询条件
        queryArtificial(promoteFormWindowQuery, queryWrapper);
        return promoteFormWindowMapper.selectCount(queryWrapper);
    }


    /**
     * TODO 新增
     *
     * @param promoteFormWindowBO 推广表单窗口
     * @return String
     * @author lijh
     * @methodName saveEnhance
     * @time 2022-07-04 10:49:05
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public String saveEnhance(PromoteFormWindowBO promoteFormWindowBO) {
        PromoteFormWindow promoteFormWindow = GeneralConvertor.convertor(promoteFormWindowBO, PromoteFormWindow.class);
        promoteFormWindowMapper.insert(promoteFormWindow);
        return promoteFormWindow.getId();
    }


    /**
     * TODO 修改
     *
     * @param promoteFormWindowBO 推广表单窗口
     * @return Boolean
     * @author lijh
     * @methodName updateEnhance
     * @time 2022-07-04 10:49:05
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public Boolean updateEnhance(PromoteFormWindowBO promoteFormWindowBO) {
        PromoteFormWindow promoteFormWindow = GeneralConvertor.convertor(promoteFormWindowBO, PromoteFormWindow.class);
        UpdateWrapper<PromoteFormWindow> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", promoteFormWindowBO.getId());
        Integer i = promoteFormWindowMapper.update(promoteFormWindow, updateWrapper);
        return i > 0 ? true : false;
    }


    /**
     * TODO 删除
     *
     * @param promoteFormWindowBO 推广表单窗口
     * @return Boolean
     * @author lijh
     * @methodName removeEnhance
     * @time 2022-07-04 10:49:05
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public Boolean removeEnhance(PromoteFormWindowBO promoteFormWindowBO) {
        PromoteFormWindow promoteFormWindow = GeneralConvertor.convertor(promoteFormWindowBO, PromoteFormWindow.class);
        QueryWrapper<PromoteFormWindow> queryWrapper = new QueryWrapper<>(promoteFormWindow);
        Integer i = promoteFormWindowMapper.delete(queryWrapper);
        return i > 0 ? true : false;
    }


    /**
     * TODO 人工查询条件
     *
     * @param promoteFormWindowQuery 推广表单窗口
     * @return QueryWrapper
     * @author lijh
     * @methodName queryArtificial
     * @time 2022-07-04 10:49:05
     */
    private QueryWrapper queryArtificial(PromoteFormWindowQuery promoteFormWindowQuery, QueryWrapper<PromoteFormWindow> queryWrapper) {
        return queryWrapper;
    }
}