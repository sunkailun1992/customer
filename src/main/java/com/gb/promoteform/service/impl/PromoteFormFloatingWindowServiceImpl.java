package com.gb.promoteform.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gb.promoteform.entity.PromoteFormFloatingWindow;
import com.gb.promoteform.entity.bo.PromoteFormFloatingWindowBO;
import com.gb.promoteform.entity.query.PromoteFormFloatingWindowQuery;
import com.gb.promoteform.entity.vo.PromoteFormFloatingWindowVO;
import com.gb.promoteform.mapper.PromoteFormFloatingWindowMapper;
import com.gb.promoteform.service.PromoteFormFloatingWindowService;
import com.gb.promoteform.service.query.PromoteFormFloatingWindowServiceQuery;
import com.gb.promoteform.service.results.PromoteFormFloatingWindowServiceResults;
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
 * TODO 推广表单浮框，Service服务实现层
 * 代码生成器
 *
 * @author lijh
 * @className PromoteFormFloatingWindowServiceImpl
 * @time 2022-07-04 10:49:04
 */
@Slf4j
@Service
@Setter(onMethod_ = {@Autowired})
public class PromoteFormFloatingWindowServiceImpl extends ServiceImpl<PromoteFormFloatingWindowMapper, PromoteFormFloatingWindow> implements PromoteFormFloatingWindowService {


    /**
     * 推广表单浮框
     */
    private PromoteFormFloatingWindowMapper promoteFormFloatingWindowMapper;


    /**
     * 推广表单浮框
     */
    private PromoteFormFloatingWindowServiceResults promoteFormFloatingWindowServiceResults;


    /**
     * 推广表单浮框增强条件
     */
    private PromoteFormFloatingWindowServiceQuery promoteFormFloatingWindowServiceQuery;


    /**
     * TODO 集合
     *
     * @param promoteFormFloatingWindowQuery 推广表单浮框
     * @return List<PromoteFormFloatingWindowVO>
     * @author lijh
     * @methodName listEnhance
     * @time 2022-07-04 10:49:04
     */
    @Override
    public List<PromoteFormFloatingWindowVO> listEnhance(PromoteFormFloatingWindowQuery promoteFormFloatingWindowQuery) {
        PromoteFormFloatingWindow promoteFormFloatingWindow = GeneralConvertor.convertor(promoteFormFloatingWindowQuery, PromoteFormFloatingWindow.class);
        QueryWrapper<PromoteFormFloatingWindow> queryWrapper = new QueryWrapper<>(promoteFormFloatingWindow);
        // TODO 自动生成查询，禁止手动写语句
        promoteFormFloatingWindowServiceQuery.query(promoteFormFloatingWindowQuery, queryWrapper);
        // TODO 人工查询条件
        queryArtificial(promoteFormFloatingWindowQuery, queryWrapper);
        // DO数据
        List<PromoteFormFloatingWindow> promoteFormFloatingWindowDO = promoteFormFloatingWindowMapper.selectList(queryWrapper);
        // VO数据
        List<PromoteFormFloatingWindowVO> promoteFormFloatingWindowVO = GeneralConvertor.convertor(promoteFormFloatingWindowDO, PromoteFormFloatingWindowVO.class);
        // 判断是否增强
        if (promoteFormFloatingWindowQuery.getAssignment() == null) {
            return promoteFormFloatingWindowServiceResults.assignment(promoteFormFloatingWindowVO);
        } else {
            return promoteFormFloatingWindowQuery.getAssignment() ? promoteFormFloatingWindowServiceResults.assignment(promoteFormFloatingWindowVO) : promoteFormFloatingWindowVO;
        }
    }


    /**
     * TODO 分页
     *
     * @param page
     * @param promoteFormFloatingWindowQuery 推广表单浮框
     * @return Page<PromoteFormFloatingWindowVO>
     * @author lijh
     * @methodName pageEnhance
     * @time 2022-07-04 10:49:04
     */
    @Override
    public Page<PromoteFormFloatingWindowVO> pageEnhance(Page page, PromoteFormFloatingWindowQuery promoteFormFloatingWindowQuery) {
        PromoteFormFloatingWindow promoteFormFloatingWindow = GeneralConvertor.convertor(promoteFormFloatingWindowQuery, PromoteFormFloatingWindow.class);
        QueryWrapper<PromoteFormFloatingWindow> queryWrapper = new QueryWrapper<>(promoteFormFloatingWindow);
        // TODO 自动生成查询，禁止手动写语句
        promoteFormFloatingWindowServiceQuery.query(promoteFormFloatingWindowQuery, queryWrapper);
        // TODO 人工查询条件
        queryArtificial(promoteFormFloatingWindowQuery, queryWrapper);
        // DO数据
        Page<PromoteFormFloatingWindow> pageDO = promoteFormFloatingWindowMapper.selectPage(page, queryWrapper);
        // VO数据
        Page<PromoteFormFloatingWindowVO> pageVO = promoteFormFloatingWindowServiceResults.toPageVO(pageDO);
        // 判断是否增强
        if (promoteFormFloatingWindowQuery.getAssignment() == null) {
            return promoteFormFloatingWindowServiceResults.assignment(pageVO);
        } else {
            return promoteFormFloatingWindowQuery.getAssignment() ? promoteFormFloatingWindowServiceResults.assignment(pageVO) : pageVO;
        }
    }


    /**
     * TODO 单条
     *
     * @param promoteFormFloatingWindowQuery 推广表单浮框
     * @return PromoteFormFloatingWindowVO
     * @author lijh
     * @methodName getOneEnhance
     * @time 2022-07-04 10:49:04
     */
    @Override
    public PromoteFormFloatingWindowVO getOneEnhance(PromoteFormFloatingWindowQuery promoteFormFloatingWindowQuery) {
        PromoteFormFloatingWindow promoteFormFloatingWindow = GeneralConvertor.convertor(promoteFormFloatingWindowQuery, PromoteFormFloatingWindow.class);
        QueryWrapper<PromoteFormFloatingWindow> queryWrapper = new QueryWrapper<>(promoteFormFloatingWindow);
        // TODO 自动生成查询，禁止手动写语句
        promoteFormFloatingWindowServiceQuery.query(promoteFormFloatingWindowQuery, queryWrapper);
        // TODO 人工查询条件
        queryArtificial(promoteFormFloatingWindowQuery, queryWrapper);
        // DO数据
        PromoteFormFloatingWindow promoteFormFloatingWindowDO = promoteFormFloatingWindowMapper.selectOne(queryWrapper);
        // VO数据
        PromoteFormFloatingWindowVO promoteFormFloatingWindowVO = GeneralConvertor.convertor(promoteFormFloatingWindowDO, PromoteFormFloatingWindowVO.class);
        return promoteFormFloatingWindowServiceResults.assignment(promoteFormFloatingWindowVO);

    }


    /**
     * TODO 总数
     *
     * @param promoteFormFloatingWindowQuery 推广表单浮框
     * @return Integer
     * @author lijh
     * @methodName countEnhance
     * @time 2022-07-04 10:49:04
     */
    @Override
    public Long countEnhance(PromoteFormFloatingWindowQuery promoteFormFloatingWindowQuery) {
        PromoteFormFloatingWindow promoteFormFloatingWindow = GeneralConvertor.convertor(promoteFormFloatingWindowQuery, PromoteFormFloatingWindow.class);
        QueryWrapper<PromoteFormFloatingWindow> queryWrapper = new QueryWrapper<>(promoteFormFloatingWindow);
        // TODO 自动生成查询，禁止手动写语句
        promoteFormFloatingWindowServiceQuery.query(promoteFormFloatingWindowQuery, queryWrapper);
        // TODO 人工查询条件
        queryArtificial(promoteFormFloatingWindowQuery, queryWrapper);
        return promoteFormFloatingWindowMapper.selectCount(queryWrapper);
    }


    /**
     * TODO 新增
     *
     * @param promoteFormFloatingWindowBO 推广表单浮框
     * @return String
     * @author lijh
     * @methodName saveEnhance
     * @time 2022-07-04 10:49:04
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public String saveEnhance(PromoteFormFloatingWindowBO promoteFormFloatingWindowBO) {
        PromoteFormFloatingWindow promoteFormFloatingWindow = GeneralConvertor.convertor(promoteFormFloatingWindowBO, PromoteFormFloatingWindow.class);
        promoteFormFloatingWindowMapper.insert(promoteFormFloatingWindow);
        return promoteFormFloatingWindow.getId();
    }


    /**
     * TODO 修改
     *
     * @param promoteFormFloatingWindowBO 推广表单浮框
     * @return Boolean
     * @author lijh
     * @methodName updateEnhance
     * @time 2022-07-04 10:49:04
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public Boolean updateEnhance(PromoteFormFloatingWindowBO promoteFormFloatingWindowBO) {
        PromoteFormFloatingWindow promoteFormFloatingWindow = GeneralConvertor.convertor(promoteFormFloatingWindowBO, PromoteFormFloatingWindow.class);
        UpdateWrapper<PromoteFormFloatingWindow> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", promoteFormFloatingWindowBO.getId());
        Integer i = promoteFormFloatingWindowMapper.update(promoteFormFloatingWindow, updateWrapper);
        return i > 0 ? true : false;
    }


    /**
     * TODO 删除
     *
     * @param promoteFormFloatingWindowBO 推广表单浮框
     * @return Boolean
     * @author lijh
     * @methodName removeEnhance
     * @time 2022-07-04 10:49:04
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public Boolean removeEnhance(PromoteFormFloatingWindowBO promoteFormFloatingWindowBO) {
        PromoteFormFloatingWindow promoteFormFloatingWindow = GeneralConvertor.convertor(promoteFormFloatingWindowBO, PromoteFormFloatingWindow.class);
        QueryWrapper<PromoteFormFloatingWindow> queryWrapper = new QueryWrapper<>(promoteFormFloatingWindow);
        Integer i = promoteFormFloatingWindowMapper.delete(queryWrapper);
        return i > 0 ? true : false;
    }


    /**
     * TODO 人工查询条件
     *
     * @param promoteFormFloatingWindowQuery 推广表单浮框
     * @return QueryWrapper
     * @author lijh
     * @methodName queryArtificial
     * @time 2022-07-04 10:49:04
     */
    private QueryWrapper queryArtificial(PromoteFormFloatingWindowQuery promoteFormFloatingWindowQuery, QueryWrapper<PromoteFormFloatingWindow> queryWrapper) {
        return queryWrapper;
    }
}