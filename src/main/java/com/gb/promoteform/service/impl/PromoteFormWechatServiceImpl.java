package com.gb.promoteform.service.impl;

import com.gb.promoteform.entity.query.PromoteFormWechatQuery;
import com.gb.promoteform.entity.vo.PromoteFormWechatVO;
import com.gb.promoteform.entity.bo.PromoteFormWechatBO;
import com.gb.promoteform.entity.PromoteFormWechat;
import com.gb.promoteform.mapper.PromoteFormWechatMapper;
import com.gb.promoteform.service.PromoteFormWechatService;
import com.gb.promoteform.service.query.PromoteFormWechatServiceQuery;
import com.gb.promoteform.service.results.PromoteFormWechatServiceResults;
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
 * TODO 推广表单微信，Service服务实现层
 * 代码生成器
 *
 * @author lijh
 * @className PromoteFormWechatServiceImpl
 * @time 2022-07-04 10:49:05
 */
@Slf4j
@Service
@Setter(onMethod_ = {@Autowired})
public class PromoteFormWechatServiceImpl extends ServiceImpl<PromoteFormWechatMapper, PromoteFormWechat> implements PromoteFormWechatService {


    /**
     * 推广表单微信
     */
    private PromoteFormWechatMapper promoteFormWechatMapper;


    /**
     * 推广表单微信
     */
    private PromoteFormWechatServiceResults promoteFormWechatServiceResults;


    /**
     * 推广表单微信增强条件
     */
    private PromoteFormWechatServiceQuery promoteFormWechatServiceQuery;


    /**
     * TODO 集合
     *
     * @param promoteFormWechatQuery 推广表单微信
     * @return List<PromoteFormWechatVO>
     * @author lijh
     * @methodName listEnhance
     * @time 2022-07-04 10:49:05
     */
    @Override
    public List<PromoteFormWechatVO> listEnhance(PromoteFormWechatQuery promoteFormWechatQuery) {
        PromoteFormWechat promoteFormWechat = GeneralConvertor.convertor(promoteFormWechatQuery, PromoteFormWechat.class);
        QueryWrapper<PromoteFormWechat> queryWrapper = new QueryWrapper<>(promoteFormWechat);
        // TODO 自动生成查询，禁止手动写语句
        promoteFormWechatServiceQuery.query(promoteFormWechatQuery, queryWrapper);
        // TODO 人工查询条件
        queryArtificial(promoteFormWechatQuery, queryWrapper);
        // DO数据
        List<PromoteFormWechat> promoteFormWechatDO = promoteFormWechatMapper.selectList(queryWrapper);
        // VO数据
        List<PromoteFormWechatVO> promoteFormWechatVO = GeneralConvertor.convertor(promoteFormWechatDO, PromoteFormWechatVO.class);
        // 判断是否增强
        if (promoteFormWechatQuery.getAssignment() == null) {
            return promoteFormWechatServiceResults.assignment(promoteFormWechatVO);
        } else {
            return promoteFormWechatQuery.getAssignment() ? promoteFormWechatServiceResults.assignment(promoteFormWechatVO) : promoteFormWechatVO;
        }
    }


    /**
     * TODO 分页
     *
     * @param page
     * @param promoteFormWechatQuery 推广表单微信
     * @return Page<PromoteFormWechatVO>
     * @author lijh
     * @methodName pageEnhance
     * @time 2022-07-04 10:49:05
     */
    @Override
    public Page<PromoteFormWechatVO> pageEnhance(Page page, PromoteFormWechatQuery promoteFormWechatQuery) {
        PromoteFormWechat promoteFormWechat = GeneralConvertor.convertor(promoteFormWechatQuery, PromoteFormWechat.class);
        QueryWrapper<PromoteFormWechat> queryWrapper = new QueryWrapper<>(promoteFormWechat);
        // TODO 自动生成查询，禁止手动写语句
        promoteFormWechatServiceQuery.query(promoteFormWechatQuery, queryWrapper);
        // TODO 人工查询条件
        queryArtificial(promoteFormWechatQuery, queryWrapper);
        // DO数据
        Page<PromoteFormWechat> pageDO = promoteFormWechatMapper.selectPage(page, queryWrapper);
        // VO数据
        Page<PromoteFormWechatVO> pageVO = promoteFormWechatServiceResults.toPageVO(pageDO);
        // 判断是否增强
        if (promoteFormWechatQuery.getAssignment() == null) {
            return promoteFormWechatServiceResults.assignment(pageVO);
        } else {
            return promoteFormWechatQuery.getAssignment() ? promoteFormWechatServiceResults.assignment(pageVO) : pageVO;
        }
    }


    /**
     * TODO 单条
     *
     * @param promoteFormWechatQuery 推广表单微信
     * @return PromoteFormWechatVO
     * @author lijh
     * @methodName getOneEnhance
     * @time 2022-07-04 10:49:05
     */
    @Override
    public PromoteFormWechatVO getOneEnhance(PromoteFormWechatQuery promoteFormWechatQuery) {
        PromoteFormWechat promoteFormWechat = GeneralConvertor.convertor(promoteFormWechatQuery, PromoteFormWechat.class);
        QueryWrapper<PromoteFormWechat> queryWrapper = new QueryWrapper<>(promoteFormWechat);
        // TODO 自动生成查询，禁止手动写语句
        promoteFormWechatServiceQuery.query(promoteFormWechatQuery, queryWrapper);
        // TODO 人工查询条件
        queryArtificial(promoteFormWechatQuery, queryWrapper);
        // DO数据
        PromoteFormWechat promoteFormWechatDO = promoteFormWechatMapper.selectOne(queryWrapper);
        // VO数据
        PromoteFormWechatVO promoteFormWechatVO = GeneralConvertor.convertor(promoteFormWechatDO, PromoteFormWechatVO.class);
        // 判断是否增强
        if (promoteFormWechatQuery.getAssignment() == null) {
            return promoteFormWechatServiceResults.assignment(promoteFormWechatVO);
        } else {
            return promoteFormWechatQuery.getAssignment() ? promoteFormWechatServiceResults.assignment(promoteFormWechatVO) : promoteFormWechatVO;
        }
    }


    /**
     * TODO 总数
     *
     * @param promoteFormWechatQuery 推广表单微信
     * @return Integer
     * @author lijh
     * @methodName countEnhance
     * @time 2022-07-04 10:49:05
     */
    @Override
    public Long countEnhance(PromoteFormWechatQuery promoteFormWechatQuery) {
        PromoteFormWechat promoteFormWechat = GeneralConvertor.convertor(promoteFormWechatQuery, PromoteFormWechat.class);
        QueryWrapper<PromoteFormWechat> queryWrapper = new QueryWrapper<>(promoteFormWechat);
        // TODO 自动生成查询，禁止手动写语句
        promoteFormWechatServiceQuery.query(promoteFormWechatQuery, queryWrapper);
        // TODO 人工查询条件
        queryArtificial(promoteFormWechatQuery, queryWrapper);
        return promoteFormWechatMapper.selectCount(queryWrapper);
    }


    /**
     * TODO 新增
     *
     * @param promoteFormWechatBO 推广表单微信
     * @return String
     * @author lijh
     * @methodName saveEnhance
     * @time 2022-07-04 10:49:05
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public String saveEnhance(PromoteFormWechatBO promoteFormWechatBO) {
        PromoteFormWechat promoteFormWechat = GeneralConvertor.convertor(promoteFormWechatBO, PromoteFormWechat.class);
        promoteFormWechatMapper.insert(promoteFormWechat);
        return promoteFormWechat.getId();
    }


    /**
     * TODO 修改
     *
     * @param promoteFormWechatBO 推广表单微信
     * @return Boolean
     * @author lijh
     * @methodName updateEnhance
     * @time 2022-07-04 10:49:05
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public Boolean updateEnhance(PromoteFormWechatBO promoteFormWechatBO) {
        PromoteFormWechat promoteFormWechat = GeneralConvertor.convertor(promoteFormWechatBO, PromoteFormWechat.class);
        UpdateWrapper<PromoteFormWechat> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", promoteFormWechatBO.getId());
        Integer i = promoteFormWechatMapper.update(promoteFormWechat, updateWrapper);
        return i > 0 ? true : false;
    }


    /**
     * TODO 删除
     *
     * @param promoteFormWechatBO 推广表单微信
     * @return Boolean
     * @author lijh
     * @methodName removeEnhance
     * @time 2022-07-04 10:49:05
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public Boolean removeEnhance(PromoteFormWechatBO promoteFormWechatBO) {
        PromoteFormWechat promoteFormWechat = GeneralConvertor.convertor(promoteFormWechatBO, PromoteFormWechat.class);
        QueryWrapper<PromoteFormWechat> queryWrapper = new QueryWrapper<>(promoteFormWechat);
        Integer i = promoteFormWechatMapper.delete(queryWrapper);
        return i > 0 ? true : false;
    }


    /**
     * TODO 人工查询条件
     *
     * @param promoteFormWechatQuery 推广表单微信
     * @return QueryWrapper
     * @author lijh
     * @methodName queryArtificial
     * @time 2022-07-04 10:49:05
     */
    private QueryWrapper queryArtificial(PromoteFormWechatQuery promoteFormWechatQuery, QueryWrapper<PromoteFormWechat> queryWrapper) {
        return queryWrapper;
    }
}