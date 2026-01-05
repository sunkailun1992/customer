package com.gb.customer.service.impl;

import com.gb.customer.entity.enums.AssistantAssociatedStateEnum;
import com.gb.customer.entity.query.AssistantAssociatedQuery;
import com.gb.customer.entity.vo.AssistantAssociatedVO;
import com.gb.customer.entity.bo.AssistantAssociatedBO;
import com.gb.customer.entity.AssistantAssociated;
import com.gb.customer.mapper.AssistantAssociatedMapper;
import com.gb.customer.service.AssistantAssociatedService;
import com.gb.customer.service.query.AssistantAssociatedServiceQuery;
import com.gb.customer.service.results.AssistantAssociatedServiceResults;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gb.rpc.UserRpc;
import com.gb.utils.exception.BusinessException;
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
import java.util.Objects;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: lijh
 * @since: 2021-11-02 10:03:07
 * @description: TODO 助理关联，Service服务实现层
 * @source: 代码生成器
 */
@Slf4j
@Service
@Setter(onMethod_ = {@Autowired})
public class AssistantAssociatedServiceImpl extends ServiceImpl<AssistantAssociatedMapper, AssistantAssociated> implements AssistantAssociatedService {


    /**
     * 助理关联
     */
    private AssistantAssociatedMapper assistantAssociatedMapper;


    /**
     * 助理关联
     */
    private AssistantAssociatedServiceResults assistantAssociatedServiceResults;

    private UserRpc userRpc;


    /**
     * 集合条件查询
     *
     * @param assistantAssociatedQuery:
     * @return java.util.List<com.entity.AssistantAssociatedVO>
     * @author lijh
     * @since 2021-11-02 10:03:07
     */
    @Override
    public List<AssistantAssociatedVO> listEnhance(AssistantAssociatedQuery assistantAssociatedQuery) {
        AssistantAssociated assistantAssociated = GeneralConvertor.convertor(assistantAssociatedQuery, AssistantAssociated.class);
        QueryWrapper<AssistantAssociated> queryWrapper = new QueryWrapper<>(assistantAssociated);
        // TODO 人工查询条件
        queryArtificial(assistantAssociatedQuery, queryWrapper);
        //TODO 目前只用在查询业务助理id。其他地方使用需要修改
        queryWrapper.select("DISTINCT assistant_id");
        //DO数据
        List<AssistantAssociated> assistantAssociatedDO = assistantAssociatedMapper.selectList(queryWrapper);
        //VO数据
        List<AssistantAssociatedVO> assistantAssociatedVO = GeneralConvertor.convertor(assistantAssociatedDO, AssistantAssociatedVO.class);
        return assistantAssociatedServiceResults.assignment(assistantAssociatedVO);
    }


    /**
     * 分页条件查询
     *
     * @param page:
     * @param assistantAssociatedQuery:
     * @return com.baomidou.mybatisplus.core.metadata.IPage
     * @author lijh
     * @since 2021-11-02 10:03:07
     */
    @Override
    public Page<AssistantAssociatedVO> pageEnhance(Page page, AssistantAssociatedQuery assistantAssociatedQuery) {
        AssistantAssociated assistantAssociated = GeneralConvertor.convertor(assistantAssociatedQuery, AssistantAssociated.class);
        QueryWrapper<AssistantAssociated> queryWrapper = new QueryWrapper<>(assistantAssociated);
        //TODO 自动生成查询，禁止手动写语句
        AssistantAssociatedServiceQuery.query(assistantAssociatedQuery, queryWrapper);
        // TODO 人工查询条件
        queryArtificial(assistantAssociatedQuery, queryWrapper);
        //DO数据
        Page<AssistantAssociated> pageDO = assistantAssociatedMapper.selectPage(page, queryWrapper);
        //VO数据
        Page<AssistantAssociatedVO> pageVO = assistantAssociatedServiceResults.toPageVO(pageDO);
        return assistantAssociatedServiceResults.assignment(pageVO);
    }


    /**
     * 单条条件查询
     *
     * @param assistantAssociatedQuery:
     * @return java.util.List<com.entity.AssistantAssociatedVO>
     * @author lijh
     * @since 2021-11-02 10:03:07
     */
    @Override
    public AssistantAssociatedVO getOneEnhance(AssistantAssociatedQuery assistantAssociatedQuery) {
        AssistantAssociated assistantAssociated = GeneralConvertor.convertor(assistantAssociatedQuery, AssistantAssociated.class);
        QueryWrapper<AssistantAssociated> queryWrapper = new QueryWrapper<>(assistantAssociated);
        //TODO 自动生成查询，禁止手动写语句
        AssistantAssociatedServiceQuery.query(assistantAssociatedQuery, queryWrapper);
        // TODO 人工查询条件
        queryArtificial(assistantAssociatedQuery, queryWrapper);
        //默认查启用的
        queryWrapper.eq("state", AssistantAssociatedStateEnum.启用.getValue());
        //DO数据
        AssistantAssociated assistantAssociatedDO = assistantAssociatedMapper.selectOne(queryWrapper);
        //VO数据
        AssistantAssociatedVO assistantAssociatedVO = GeneralConvertor.convertor(assistantAssociatedDO, AssistantAssociatedVO.class);
        return assistantAssociatedServiceResults.assignment(assistantAssociatedVO);
    }


    /**
     * 总数
     *
     * @param assistantAssociatedQuery:
     * @return java.lang.Integer
     * @author lijh
     * @since 2021-11-02 10:03:07
     */
    @Override
    public Long countEnhance(AssistantAssociatedQuery assistantAssociatedQuery) {
        AssistantAssociated assistantAssociated = GeneralConvertor.convertor(assistantAssociatedQuery, AssistantAssociated.class);
        QueryWrapper<AssistantAssociated> queryWrapper = new QueryWrapper<>(assistantAssociated);
        //TODO 自动生成查询，禁止手动写语句
        AssistantAssociatedServiceQuery.query(assistantAssociatedQuery, queryWrapper);
        // TODO 人工查询条件
        queryArtificial(assistantAssociatedQuery, queryWrapper);
        return assistantAssociatedMapper.selectCount(queryWrapper);
    }


    /**
     * 新增
     *
     * @param assistantAssociatedBO:
     * @return java.lang.String
     * @author lijh
     * @since 2021-11-02 10:03:07
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public String saveEnhance(AssistantAssociatedBO assistantAssociatedBO) {
        AssistantAssociated assistantAssociatedInfo = assistantAssociatedMapper.selectOne(new QueryWrapper<AssistantAssociated>().lambda().eq(AssistantAssociated::getHousekeeperId, assistantAssociatedBO.getHousekeeperId()));
        if (Objects.nonNull(assistantAssociatedInfo)) {
            throw new BusinessException("此管理已经关联过助理,请重新关联。");
        }
        AssistantAssociated assistantAssociated = GeneralConvertor.convertor(assistantAssociatedBO, AssistantAssociated.class);
        assistantAssociatedMapper.insert(assistantAssociated);
        return assistantAssociated.getId();
    }


    /**
     * 修改
     *
     * @param assistantAssociatedBO:
     * @return java.lang.Boolean
     * @author lijh
     * @since 2021-11-02 10:03:07
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public Boolean updateEnhance(AssistantAssociatedBO assistantAssociatedBO) {
        AssistantAssociated assistantAssociatedInfo = assistantAssociatedMapper.selectOne(new QueryWrapper<AssistantAssociated>().lambda().
                eq(AssistantAssociated::getHousekeeperId, assistantAssociatedBO.getHousekeeperId()).ne(AssistantAssociated::getId, assistantAssociatedBO.getId()));
        if (Objects.nonNull(assistantAssociatedInfo)) {
            throw new BusinessException("此管理已经关联过助理,请重新关联。");
        }
        AssistantAssociated assistantAssociated = GeneralConvertor.convertor(assistantAssociatedBO, AssistantAssociated.class);
        UpdateWrapper<AssistantAssociated> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", assistantAssociatedBO.getId());
        int i = assistantAssociatedMapper.update(assistantAssociated, updateWrapper);
        return i > 0;
    }


    /**
     * 删除
     *
     * @param assistantAssociatedBO:
     * @return java.lang.Boolean
     * @author lijh
     * @since 2021-11-02 10:03:07
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public Boolean removeEnhance(AssistantAssociatedBO assistantAssociatedBO) {
        AssistantAssociated assistantAssociated = GeneralConvertor.convertor(assistantAssociatedBO, AssistantAssociated.class);
        QueryWrapper<AssistantAssociated> queryWrapper = new QueryWrapper<>(assistantAssociated);
        Integer i = assistantAssociatedMapper.delete(queryWrapper);
        return i > 0 ? true : false;
    }


    /**
     * 查询人工查询条件
     *
     * @param assistantAssociatedQuery 助理关联
     * @return QueryWrapper
     * @author lijh
     * @since 2021-11-02 10:03:07
     */
    private QueryWrapper queryArtificial(AssistantAssociatedQuery assistantAssociatedQuery, QueryWrapper<AssistantAssociated> queryWrapper) {
        /**
         * 助理id
         */
        if (StringUtils.isNotBlank(assistantAssociatedQuery.getAssistantId())) {
            queryWrapper.eq("assistant_id", assistantAssociatedQuery.getAssistantId());
        }
        /**
         * 管理id
         */
        if (StringUtils.isNotBlank(assistantAssociatedQuery.getHousekeeperId())) {
            queryWrapper.eq("housekeeper_id", assistantAssociatedQuery.getHousekeeperId());
        }
        return queryWrapper;
    }
}