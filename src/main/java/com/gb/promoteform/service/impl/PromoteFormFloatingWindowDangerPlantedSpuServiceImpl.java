package com.gb.promoteform.service.impl;

import com.gb.promoteform.entity.query.PromoteFormFloatingWindowDangerPlantedSpuQuery;
import com.gb.promoteform.entity.vo.PromoteFormFloatingWindowDangerPlantedSpuVO;
import com.gb.promoteform.entity.bo.PromoteFormFloatingWindowDangerPlantedSpuBO;
import com.gb.promoteform.entity.PromoteFormFloatingWindowDangerPlantedSpu;
import com.gb.promoteform.mapper.PromoteFormFloatingWindowDangerPlantedSpuMapper;
import com.gb.promoteform.service.PromoteFormFloatingWindowDangerPlantedSpuService;
import com.gb.promoteform.service.query.PromoteFormFloatingWindowDangerPlantedSpuServiceQuery;
import com.gb.promoteform.service.results.PromoteFormFloatingWindowDangerPlantedSpuServiceResults;
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
 * TODO 推广表单浮框险种产品，Service服务实现层
 * 代码生成器
 *
 * @author lijh
 * @className PromoteFormFloatingWindowDangerPlantedSpuServiceImpl
 * @time 2022-10-28 03:09:31
 */
@Slf4j
@Service
@Setter(onMethod_ = {@Autowired})
public class PromoteFormFloatingWindowDangerPlantedSpuServiceImpl extends ServiceImpl<PromoteFormFloatingWindowDangerPlantedSpuMapper, PromoteFormFloatingWindowDangerPlantedSpu> implements PromoteFormFloatingWindowDangerPlantedSpuService {


    /**
     * 推广表单浮框险种产品
     */
    private PromoteFormFloatingWindowDangerPlantedSpuMapper promoteFormFloatingWindowDangerPlantedSpuMapper;


    /**
     * 推广表单浮框险种产品
     */
    private PromoteFormFloatingWindowDangerPlantedSpuServiceResults promoteFormFloatingWindowDangerPlantedSpuServiceResults;


    /**
     * 推广表单浮框险种产品增强条件
     */
    private PromoteFormFloatingWindowDangerPlantedSpuServiceQuery promoteFormFloatingWindowDangerPlantedSpuServiceQuery;


    /**
     * TODO 集合
     *
     * @param promoteFormFloatingWindowDangerPlantedSpuQuery 推广表单浮框险种产品
     * @return List<PromoteFormFloatingWindowDangerPlantedSpuVO>
     * @author lijh
     * @methodName listEnhance
     * @time 2022-10-28 03:09:31
     */
    @Override
    public List<PromoteFormFloatingWindowDangerPlantedSpuVO> listEnhance(PromoteFormFloatingWindowDangerPlantedSpuQuery promoteFormFloatingWindowDangerPlantedSpuQuery) {
        PromoteFormFloatingWindowDangerPlantedSpu promoteFormFloatingWindowDangerPlantedSpu = GeneralConvertor.convertor(promoteFormFloatingWindowDangerPlantedSpuQuery, PromoteFormFloatingWindowDangerPlantedSpu.class);
        QueryWrapper<PromoteFormFloatingWindowDangerPlantedSpu> queryWrapper = new QueryWrapper<>(promoteFormFloatingWindowDangerPlantedSpu);
        // TODO 自动生成查询，禁止手动写语句
        promoteFormFloatingWindowDangerPlantedSpuServiceQuery.query(promoteFormFloatingWindowDangerPlantedSpuQuery, queryWrapper);
        // TODO 人工查询条件
        queryArtificial(promoteFormFloatingWindowDangerPlantedSpuQuery, queryWrapper);
        // DO数据
        List<PromoteFormFloatingWindowDangerPlantedSpu> promoteFormFloatingWindowDangerPlantedSpuDO = promoteFormFloatingWindowDangerPlantedSpuMapper.selectList(queryWrapper);
        // VO数据
        List<PromoteFormFloatingWindowDangerPlantedSpuVO> promoteFormFloatingWindowDangerPlantedSpuVO = GeneralConvertor.convertor(promoteFormFloatingWindowDangerPlantedSpuDO, PromoteFormFloatingWindowDangerPlantedSpuVO.class);
        // 判断是否增强
        if (promoteFormFloatingWindowDangerPlantedSpuQuery.getAssignment() == null) {
            return promoteFormFloatingWindowDangerPlantedSpuServiceResults.assignment(promoteFormFloatingWindowDangerPlantedSpuVO);
        } else {
            return promoteFormFloatingWindowDangerPlantedSpuQuery.getAssignment() ? promoteFormFloatingWindowDangerPlantedSpuServiceResults.assignment(promoteFormFloatingWindowDangerPlantedSpuVO) : promoteFormFloatingWindowDangerPlantedSpuVO;
        }
    }


    /**
     * TODO 分页
     *
     * @param page
     * @param promoteFormFloatingWindowDangerPlantedSpuQuery 推广表单浮框险种产品
     * @return Page<PromoteFormFloatingWindowDangerPlantedSpuVO>
     * @author lijh
     * @methodName pageEnhance
     * @time 2022-10-28 03:09:31
     */
    @Override
    public Page<PromoteFormFloatingWindowDangerPlantedSpuVO> pageEnhance(Page page, PromoteFormFloatingWindowDangerPlantedSpuQuery promoteFormFloatingWindowDangerPlantedSpuQuery) {
        PromoteFormFloatingWindowDangerPlantedSpu promoteFormFloatingWindowDangerPlantedSpu = GeneralConvertor.convertor(promoteFormFloatingWindowDangerPlantedSpuQuery, PromoteFormFloatingWindowDangerPlantedSpu.class);
        QueryWrapper<PromoteFormFloatingWindowDangerPlantedSpu> queryWrapper = new QueryWrapper<>(promoteFormFloatingWindowDangerPlantedSpu);
        // TODO 自动生成查询，禁止手动写语句
        promoteFormFloatingWindowDangerPlantedSpuServiceQuery.query(promoteFormFloatingWindowDangerPlantedSpuQuery, queryWrapper);
        // TODO 人工查询条件
        queryArtificial(promoteFormFloatingWindowDangerPlantedSpuQuery, queryWrapper);
        // DO数据
        Page<PromoteFormFloatingWindowDangerPlantedSpu> pageDO = promoteFormFloatingWindowDangerPlantedSpuMapper.selectPage(page, queryWrapper);
        // VO数据
        Page<PromoteFormFloatingWindowDangerPlantedSpuVO> pageVO = promoteFormFloatingWindowDangerPlantedSpuServiceResults.toPageVO(pageDO);
        // 判断是否增强
        if (promoteFormFloatingWindowDangerPlantedSpuQuery.getAssignment() == null) {
            return promoteFormFloatingWindowDangerPlantedSpuServiceResults.assignment(pageVO);
        } else {
            return promoteFormFloatingWindowDangerPlantedSpuQuery.getAssignment() ? promoteFormFloatingWindowDangerPlantedSpuServiceResults.assignment(pageVO) : pageVO;
        }
    }


    /**
     * TODO 单条
     *
     * @param promoteFormFloatingWindowDangerPlantedSpuQuery 推广表单浮框险种产品
     * @return PromoteFormFloatingWindowDangerPlantedSpuVO
     * @author lijh
     * @methodName getOneEnhance
     * @time 2022-10-28 03:09:31
     */
    @Override
    public PromoteFormFloatingWindowDangerPlantedSpuVO getOneEnhance(PromoteFormFloatingWindowDangerPlantedSpuQuery promoteFormFloatingWindowDangerPlantedSpuQuery) {
        PromoteFormFloatingWindowDangerPlantedSpu promoteFormFloatingWindowDangerPlantedSpu = GeneralConvertor.convertor(promoteFormFloatingWindowDangerPlantedSpuQuery, PromoteFormFloatingWindowDangerPlantedSpu.class);
        QueryWrapper<PromoteFormFloatingWindowDangerPlantedSpu> queryWrapper = new QueryWrapper<>(promoteFormFloatingWindowDangerPlantedSpu);
        // TODO 自动生成查询，禁止手动写语句
        promoteFormFloatingWindowDangerPlantedSpuServiceQuery.query(promoteFormFloatingWindowDangerPlantedSpuQuery, queryWrapper);
        // TODO 人工查询条件
        queryArtificial(promoteFormFloatingWindowDangerPlantedSpuQuery, queryWrapper);
        // DO数据
        PromoteFormFloatingWindowDangerPlantedSpu promoteFormFloatingWindowDangerPlantedSpuDO = promoteFormFloatingWindowDangerPlantedSpuMapper.selectOne(queryWrapper);
        // VO数据
        PromoteFormFloatingWindowDangerPlantedSpuVO promoteFormFloatingWindowDangerPlantedSpuVO = GeneralConvertor.convertor(promoteFormFloatingWindowDangerPlantedSpuDO, PromoteFormFloatingWindowDangerPlantedSpuVO.class);
        // 判断是否增强
        if (promoteFormFloatingWindowDangerPlantedSpuQuery.getAssignment() == null) {
            return promoteFormFloatingWindowDangerPlantedSpuServiceResults.assignment(promoteFormFloatingWindowDangerPlantedSpuVO);
        } else {
            return promoteFormFloatingWindowDangerPlantedSpuQuery.getAssignment() ? promoteFormFloatingWindowDangerPlantedSpuServiceResults.assignment(promoteFormFloatingWindowDangerPlantedSpuVO) : promoteFormFloatingWindowDangerPlantedSpuVO;
        }
    }


    /**
     * TODO 总数
     *
     * @param promoteFormFloatingWindowDangerPlantedSpuQuery 推广表单浮框险种产品
     * @return Integer
     * @author lijh
     * @methodName countEnhance
     * @time 2022-10-28 03:09:31
     */
    @Override
    public Long countEnhance(PromoteFormFloatingWindowDangerPlantedSpuQuery promoteFormFloatingWindowDangerPlantedSpuQuery) {
        PromoteFormFloatingWindowDangerPlantedSpu promoteFormFloatingWindowDangerPlantedSpu = GeneralConvertor.convertor(promoteFormFloatingWindowDangerPlantedSpuQuery, PromoteFormFloatingWindowDangerPlantedSpu.class);
        QueryWrapper<PromoteFormFloatingWindowDangerPlantedSpu> queryWrapper = new QueryWrapper<>(promoteFormFloatingWindowDangerPlantedSpu);
        // TODO 自动生成查询，禁止手动写语句
        promoteFormFloatingWindowDangerPlantedSpuServiceQuery.query(promoteFormFloatingWindowDangerPlantedSpuQuery, queryWrapper);
        // TODO 人工查询条件
        queryArtificial(promoteFormFloatingWindowDangerPlantedSpuQuery, queryWrapper);
        return promoteFormFloatingWindowDangerPlantedSpuMapper.selectCount(queryWrapper);
    }


    /**
     * TODO 新增
     *
     * @param promoteFormFloatingWindowDangerPlantedSpuBO 推广表单浮框险种产品
     * @return String
     * @author lijh
     * @methodName saveEnhance
     * @time 2022-10-28 03:09:31
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public String saveEnhance(PromoteFormFloatingWindowDangerPlantedSpuBO promoteFormFloatingWindowDangerPlantedSpuBO) {
        PromoteFormFloatingWindowDangerPlantedSpu promoteFormFloatingWindowDangerPlantedSpu = GeneralConvertor.convertor(promoteFormFloatingWindowDangerPlantedSpuBO, PromoteFormFloatingWindowDangerPlantedSpu.class);
        promoteFormFloatingWindowDangerPlantedSpuMapper.insert(promoteFormFloatingWindowDangerPlantedSpu);
        return promoteFormFloatingWindowDangerPlantedSpu.getId();
    }


    /**
     * TODO 修改
     *
     * @param promoteFormFloatingWindowDangerPlantedSpuBO 推广表单浮框险种产品
     * @return Boolean
     * @author lijh
     * @methodName updateEnhance
     * @time 2022-10-28 03:09:31
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public Boolean updateEnhance(PromoteFormFloatingWindowDangerPlantedSpuBO promoteFormFloatingWindowDangerPlantedSpuBO) {
        PromoteFormFloatingWindowDangerPlantedSpu promoteFormFloatingWindowDangerPlantedSpu = GeneralConvertor.convertor(promoteFormFloatingWindowDangerPlantedSpuBO, PromoteFormFloatingWindowDangerPlantedSpu.class);
        UpdateWrapper<PromoteFormFloatingWindowDangerPlantedSpu> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", promoteFormFloatingWindowDangerPlantedSpuBO.getId());
        Integer i = promoteFormFloatingWindowDangerPlantedSpuMapper.update(promoteFormFloatingWindowDangerPlantedSpu, updateWrapper);
        return i > 0 ? true : false;
    }


    /**
     * TODO 删除
     *
     * @param promoteFormFloatingWindowDangerPlantedSpuBO 推广表单浮框险种产品
     * @return Boolean
     * @author lijh
     * @methodName removeEnhance
     * @time 2022-10-28 03:09:31
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public Boolean removeEnhance(PromoteFormFloatingWindowDangerPlantedSpuBO promoteFormFloatingWindowDangerPlantedSpuBO) {
        PromoteFormFloatingWindowDangerPlantedSpu promoteFormFloatingWindowDangerPlantedSpu = GeneralConvertor.convertor(promoteFormFloatingWindowDangerPlantedSpuBO, PromoteFormFloatingWindowDangerPlantedSpu.class);
        QueryWrapper<PromoteFormFloatingWindowDangerPlantedSpu> queryWrapper = new QueryWrapper<>(promoteFormFloatingWindowDangerPlantedSpu);
        Integer i = promoteFormFloatingWindowDangerPlantedSpuMapper.delete(queryWrapper);
        return i > 0 ? true : false;
    }


    /**
     * TODO 人工查询条件
     *
     * @param promoteFormFloatingWindowDangerPlantedSpuQuery 推广表单浮框险种产品
     * @return QueryWrapper
     * @author lijh
     * @methodName queryArtificial
     * @time 2022-10-28 03:09:31
     */
    private QueryWrapper queryArtificial(PromoteFormFloatingWindowDangerPlantedSpuQuery promoteFormFloatingWindowDangerPlantedSpuQuery, QueryWrapper<PromoteFormFloatingWindowDangerPlantedSpu> queryWrapper) {
        return queryWrapper;
    }
}