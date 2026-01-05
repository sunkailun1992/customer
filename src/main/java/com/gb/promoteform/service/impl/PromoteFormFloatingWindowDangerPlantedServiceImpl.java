package com.gb.promoteform.service.impl;

import com.gb.promoteform.entity.query.PromoteFormFloatingWindowDangerPlantedQuery;
import com.gb.promoteform.entity.vo.PromoteFormFloatingWindowDangerPlantedVO;
import com.gb.promoteform.entity.bo.PromoteFormFloatingWindowDangerPlantedBO;
import com.gb.promoteform.entity.PromoteFormFloatingWindowDangerPlanted;
import com.gb.promoteform.mapper.PromoteFormFloatingWindowDangerPlantedMapper;
import com.gb.promoteform.service.PromoteFormFloatingWindowDangerPlantedService;
import com.gb.promoteform.service.query.PromoteFormFloatingWindowDangerPlantedServiceQuery;
import com.gb.promoteform.service.results.PromoteFormFloatingWindowDangerPlantedServiceResults;
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
 * TODO 推广表单浮框险种，Service服务实现层
 * 代码生成器
 *
 * @author lijh
 * @className PromoteFormFloatingWindowDangerPlantedServiceImpl
 * @time 2022-07-04 10:49:04
 */
@Slf4j
@Service
@Setter(onMethod_ = {@Autowired})
public class PromoteFormFloatingWindowDangerPlantedServiceImpl extends ServiceImpl<PromoteFormFloatingWindowDangerPlantedMapper, PromoteFormFloatingWindowDangerPlanted> implements PromoteFormFloatingWindowDangerPlantedService {


    /**
     * 推广表单浮框险种
     */
    private PromoteFormFloatingWindowDangerPlantedMapper promoteFormFloatingWindowDangerPlantedMapper;


    /**
     * 推广表单浮框险种
     */
    private PromoteFormFloatingWindowDangerPlantedServiceResults promoteFormFloatingWindowDangerPlantedServiceResults;


    /**
     * 推广表单浮框险种增强条件
     */
    private PromoteFormFloatingWindowDangerPlantedServiceQuery promoteFormFloatingWindowDangerPlantedServiceQuery;


    /**
     * TODO 集合
     *
     * @param promoteFormFloatingWindowDangerPlantedQuery 推广表单浮框险种
     * @return List<PromoteFormFloatingWindowDangerPlantedVO>
     * @author lijh
     * @methodName listEnhance
     * @time 2022-07-04 10:49:04
     */
    @Override
    public List<PromoteFormFloatingWindowDangerPlantedVO> listEnhance(PromoteFormFloatingWindowDangerPlantedQuery promoteFormFloatingWindowDangerPlantedQuery) {
        PromoteFormFloatingWindowDangerPlanted promoteFormFloatingWindowDangerPlanted = GeneralConvertor.convertor(promoteFormFloatingWindowDangerPlantedQuery, PromoteFormFloatingWindowDangerPlanted.class);
        QueryWrapper<PromoteFormFloatingWindowDangerPlanted> queryWrapper = new QueryWrapper<>(promoteFormFloatingWindowDangerPlanted);
        // TODO 自动生成查询，禁止手动写语句
        promoteFormFloatingWindowDangerPlantedServiceQuery.query(promoteFormFloatingWindowDangerPlantedQuery, queryWrapper);
        // TODO 人工查询条件
        queryArtificial(promoteFormFloatingWindowDangerPlantedQuery, queryWrapper);
        // DO数据
        List<PromoteFormFloatingWindowDangerPlanted> promoteFormFloatingWindowDangerPlantedDO = promoteFormFloatingWindowDangerPlantedMapper.selectList(queryWrapper);
        // VO数据
        List<PromoteFormFloatingWindowDangerPlantedVO> promoteFormFloatingWindowDangerPlantedVO = GeneralConvertor.convertor(promoteFormFloatingWindowDangerPlantedDO, PromoteFormFloatingWindowDangerPlantedVO.class);
        // 判断是否增强
        if (promoteFormFloatingWindowDangerPlantedQuery.getAssignment() == null) {
            return promoteFormFloatingWindowDangerPlantedServiceResults.assignment(promoteFormFloatingWindowDangerPlantedVO);
        } else {
            return promoteFormFloatingWindowDangerPlantedQuery.getAssignment() ? promoteFormFloatingWindowDangerPlantedServiceResults.assignment(promoteFormFloatingWindowDangerPlantedVO) : promoteFormFloatingWindowDangerPlantedVO;
        }
    }


    /**
     * TODO 分页
     *
     * @param page
     * @param promoteFormFloatingWindowDangerPlantedQuery 推广表单浮框险种
     * @return Page<PromoteFormFloatingWindowDangerPlantedVO>
     * @author lijh
     * @methodName pageEnhance
     * @time 2022-07-04 10:49:04
     */
    @Override
    public Page<PromoteFormFloatingWindowDangerPlantedVO> pageEnhance(Page page, PromoteFormFloatingWindowDangerPlantedQuery promoteFormFloatingWindowDangerPlantedQuery) {
        PromoteFormFloatingWindowDangerPlanted promoteFormFloatingWindowDangerPlanted = GeneralConvertor.convertor(promoteFormFloatingWindowDangerPlantedQuery, PromoteFormFloatingWindowDangerPlanted.class);
        QueryWrapper<PromoteFormFloatingWindowDangerPlanted> queryWrapper = new QueryWrapper<>(promoteFormFloatingWindowDangerPlanted);
        // TODO 自动生成查询，禁止手动写语句
        promoteFormFloatingWindowDangerPlantedServiceQuery.query(promoteFormFloatingWindowDangerPlantedQuery, queryWrapper);
        // TODO 人工查询条件
        queryArtificial(promoteFormFloatingWindowDangerPlantedQuery, queryWrapper);
        // DO数据
        Page<PromoteFormFloatingWindowDangerPlanted> pageDO = promoteFormFloatingWindowDangerPlantedMapper.selectPage(page, queryWrapper);
        // VO数据
        Page<PromoteFormFloatingWindowDangerPlantedVO> pageVO = promoteFormFloatingWindowDangerPlantedServiceResults.toPageVO(pageDO);
        // 判断是否增强
        if (promoteFormFloatingWindowDangerPlantedQuery.getAssignment() == null) {
            return promoteFormFloatingWindowDangerPlantedServiceResults.assignment(pageVO);
        } else {
            return promoteFormFloatingWindowDangerPlantedQuery.getAssignment() ? promoteFormFloatingWindowDangerPlantedServiceResults.assignment(pageVO) : pageVO;
        }
    }


    /**
     * TODO 单条
     *
     * @param promoteFormFloatingWindowDangerPlantedQuery 推广表单浮框险种
     * @return PromoteFormFloatingWindowDangerPlantedVO
     * @author lijh
     * @methodName getOneEnhance
     * @time 2022-07-04 10:49:04
     */
    @Override
    public PromoteFormFloatingWindowDangerPlantedVO getOneEnhance(PromoteFormFloatingWindowDangerPlantedQuery promoteFormFloatingWindowDangerPlantedQuery) {
        PromoteFormFloatingWindowDangerPlanted promoteFormFloatingWindowDangerPlanted = GeneralConvertor.convertor(promoteFormFloatingWindowDangerPlantedQuery, PromoteFormFloatingWindowDangerPlanted.class);
        QueryWrapper<PromoteFormFloatingWindowDangerPlanted> queryWrapper = new QueryWrapper<>(promoteFormFloatingWindowDangerPlanted);
        // TODO 自动生成查询，禁止手动写语句
        promoteFormFloatingWindowDangerPlantedServiceQuery.query(promoteFormFloatingWindowDangerPlantedQuery, queryWrapper);
        // TODO 人工查询条件
        queryArtificial(promoteFormFloatingWindowDangerPlantedQuery, queryWrapper);
        // DO数据
        PromoteFormFloatingWindowDangerPlanted promoteFormFloatingWindowDangerPlantedDO = promoteFormFloatingWindowDangerPlantedMapper.selectOne(queryWrapper);
        // VO数据
        PromoteFormFloatingWindowDangerPlantedVO promoteFormFloatingWindowDangerPlantedVO = GeneralConvertor.convertor(promoteFormFloatingWindowDangerPlantedDO, PromoteFormFloatingWindowDangerPlantedVO.class);
        // 判断是否增强
        if (promoteFormFloatingWindowDangerPlantedQuery.getAssignment() == null) {
            return promoteFormFloatingWindowDangerPlantedServiceResults.assignment(promoteFormFloatingWindowDangerPlantedVO);
        } else {
            return promoteFormFloatingWindowDangerPlantedQuery.getAssignment() ? promoteFormFloatingWindowDangerPlantedServiceResults.assignment(promoteFormFloatingWindowDangerPlantedVO) : promoteFormFloatingWindowDangerPlantedVO;
        }
    }


    /**
     * TODO 总数
     *
     * @param promoteFormFloatingWindowDangerPlantedQuery 推广表单浮框险种
     * @return Integer
     * @author lijh
     * @methodName countEnhance
     * @time 2022-07-04 10:49:04
     */
    @Override
    public Long countEnhance(PromoteFormFloatingWindowDangerPlantedQuery promoteFormFloatingWindowDangerPlantedQuery) {
        PromoteFormFloatingWindowDangerPlanted promoteFormFloatingWindowDangerPlanted = GeneralConvertor.convertor(promoteFormFloatingWindowDangerPlantedQuery, PromoteFormFloatingWindowDangerPlanted.class);
        QueryWrapper<PromoteFormFloatingWindowDangerPlanted> queryWrapper = new QueryWrapper<>(promoteFormFloatingWindowDangerPlanted);
        // TODO 自动生成查询，禁止手动写语句
        promoteFormFloatingWindowDangerPlantedServiceQuery.query(promoteFormFloatingWindowDangerPlantedQuery, queryWrapper);
        // TODO 人工查询条件
        queryArtificial(promoteFormFloatingWindowDangerPlantedQuery, queryWrapper);
        return promoteFormFloatingWindowDangerPlantedMapper.selectCount(queryWrapper);
    }


    /**
     * TODO 新增
     *
     * @param promoteFormFloatingWindowDangerPlantedBO 推广表单浮框险种
     * @return String
     * @author lijh
     * @methodName saveEnhance
     * @time 2022-07-04 10:49:04
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public String saveEnhance(PromoteFormFloatingWindowDangerPlantedBO promoteFormFloatingWindowDangerPlantedBO) {
        PromoteFormFloatingWindowDangerPlanted promoteFormFloatingWindowDangerPlanted = GeneralConvertor.convertor(promoteFormFloatingWindowDangerPlantedBO, PromoteFormFloatingWindowDangerPlanted.class);
        promoteFormFloatingWindowDangerPlantedMapper.insert(promoteFormFloatingWindowDangerPlanted);
        return promoteFormFloatingWindowDangerPlanted.getId();
    }


    /**
     * TODO 修改
     *
     * @param promoteFormFloatingWindowDangerPlantedBO 推广表单浮框险种
     * @return Boolean
     * @author lijh
     * @methodName updateEnhance
     * @time 2022-07-04 10:49:04
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public Boolean updateEnhance(PromoteFormFloatingWindowDangerPlantedBO promoteFormFloatingWindowDangerPlantedBO) {
        PromoteFormFloatingWindowDangerPlanted promoteFormFloatingWindowDangerPlanted = GeneralConvertor.convertor(promoteFormFloatingWindowDangerPlantedBO, PromoteFormFloatingWindowDangerPlanted.class);
        UpdateWrapper<PromoteFormFloatingWindowDangerPlanted> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", promoteFormFloatingWindowDangerPlantedBO.getId());
        Integer i = promoteFormFloatingWindowDangerPlantedMapper.update(promoteFormFloatingWindowDangerPlanted, updateWrapper);
        return i > 0 ? true : false;
    }


    /**
     * TODO 删除
     *
     * @param promoteFormFloatingWindowDangerPlantedBO 推广表单浮框险种
     * @return Boolean
     * @author lijh
     * @methodName removeEnhance
     * @time 2022-07-04 10:49:04
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public Boolean removeEnhance(PromoteFormFloatingWindowDangerPlantedBO promoteFormFloatingWindowDangerPlantedBO) {
        PromoteFormFloatingWindowDangerPlanted promoteFormFloatingWindowDangerPlanted = GeneralConvertor.convertor(promoteFormFloatingWindowDangerPlantedBO, PromoteFormFloatingWindowDangerPlanted.class);
        QueryWrapper<PromoteFormFloatingWindowDangerPlanted> queryWrapper = new QueryWrapper<>(promoteFormFloatingWindowDangerPlanted);
        Integer i = promoteFormFloatingWindowDangerPlantedMapper.delete(queryWrapper);
        return i > 0 ? true : false;
    }


    /**
     * TODO 人工查询条件
     *
     * @param promoteFormFloatingWindowDangerPlantedQuery 推广表单浮框险种
     * @return QueryWrapper
     * @author lijh
     * @methodName queryArtificial
     * @time 2022-07-04 10:49:04
     */
    private QueryWrapper queryArtificial(PromoteFormFloatingWindowDangerPlantedQuery promoteFormFloatingWindowDangerPlantedQuery, QueryWrapper<PromoteFormFloatingWindowDangerPlanted> queryWrapper) {
        return queryWrapper;
    }
}