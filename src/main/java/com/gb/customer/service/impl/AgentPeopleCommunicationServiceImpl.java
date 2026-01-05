package com.gb.customer.service.impl;

import com.gb.customer.entity.AgentPeopleCommunication;
import com.gb.customer.mapper.AgentPeopleCommunicationMapper;
import com.gb.customer.service.AgentPeopleCommunicationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Setter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * <p>
 * 经纪人沟通 服务实现类
 * </p>
 *
 * @author lijh
 * @since 2021-06-11
 */
@Slf4j
@Service
@Setter(onMethod_ = {@Autowired})
public class AgentPeopleCommunicationServiceImpl extends ServiceImpl<AgentPeopleCommunicationMapper, AgentPeopleCommunication> implements AgentPeopleCommunicationService {


    /**
     * 经纪人沟通
     */
    private AgentPeopleCommunicationMapper agentPeopleCommunicationMapper;


    /**
     * 集合条件查询
     * @author lijh
     * @since 2021-06-11
     * @param agentPeopleCommunication:
     * @return      java.util.List<com.entity.AgentPeopleCommunication>
     */
    @Override
    public List<AgentPeopleCommunication> listEnhance(AgentPeopleCommunication agentPeopleCommunication) {
        QueryWrapper<AgentPeopleCommunication> queryWrapper = new QueryWrapper<>(agentPeopleCommunication);
        query(agentPeopleCommunication, queryWrapper);
        return assignment(agentPeopleCommunicationMapper.selectList(queryWrapper));
    }


    /**
     * 分页条件查询
     * @author lijh
     * @since 2021-06-11
     * @param agentPeopleCommunication:
     * @param page:
     * @return      java.util.List<com.entity.AgentPeopleCommunication>
     */
    @Override
    public IPage pageEnhance(Page page, AgentPeopleCommunication agentPeopleCommunication) {
        QueryWrapper<AgentPeopleCommunication> queryWrapper = new QueryWrapper<>(agentPeopleCommunication);
        query(agentPeopleCommunication, queryWrapper);
        return assignment(agentPeopleCommunicationMapper.selectPage(page, queryWrapper));
    }


    /**
     * 单条条件查询
     * @author lijh
     * @since 2021-06-11
     * @param agentPeopleCommunication:
     * @return      java.util.List<com.entity.AgentPeopleCommunication>
     */
    @Override
    public AgentPeopleCommunication getOneEnhance(AgentPeopleCommunication agentPeopleCommunication) {
        QueryWrapper<AgentPeopleCommunication> queryWrapper = new QueryWrapper<>(agentPeopleCommunication);
        query(agentPeopleCommunication, queryWrapper);
        return assignment(agentPeopleCommunicationMapper.selectOne(queryWrapper));
    }


    /**
     * 总数
     * @author lijh
     * @since 2021-06-11
     * @param agentPeopleCommunication:
     * @return      java.util.List<com.entity.AgentPeopleCommunication>
     */
    @Override
    public Long countEnhance(AgentPeopleCommunication agentPeopleCommunication) {
        QueryWrapper<AgentPeopleCommunication> queryWrapper = new QueryWrapper<>(agentPeopleCommunication);
        query(agentPeopleCommunication, queryWrapper);
        return agentPeopleCommunicationMapper.selectCount(queryWrapper);
    }


    /**
     * 新增
     * @author lijh
     * @since 2021-06-11
     * @param agentPeopleCommunication:
     * @return      java.util.List<com.entity.AgentPeopleCommunication>
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public Boolean saveEnhance(AgentPeopleCommunication agentPeopleCommunication) {
        Integer i = agentPeopleCommunicationMapper.insert(agentPeopleCommunication);
        return i > 0 ? true : false;
    }


    /**
     * 修改
     * @author lijh
     * @since 2021-06-11
     * @param agentPeopleCommunication:
     * @return      java.util.List<com.entity.AgentPeopleCommunication>
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public Boolean updateEnhance(AgentPeopleCommunication agentPeopleCommunication) {
        UpdateWrapper<AgentPeopleCommunication > updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", agentPeopleCommunication.getId());
        Integer i = agentPeopleCommunicationMapper.update(agentPeopleCommunication, updateWrapper);
        return i > 0 ? true : false;
    }


    /**
     * 删除
     * @author lijh
     * @since 2021-06-11
     * @param agentPeopleCommunication:
     * @return      java.util.List<com.entity.AgentPeopleCommunication>
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public Boolean removeEnhance(AgentPeopleCommunication agentPeopleCommunication) {
        QueryWrapper<AgentPeopleCommunication> queryWrapper = new QueryWrapper<>(agentPeopleCommunication);
        Integer i = agentPeopleCommunicationMapper.delete(queryWrapper);
        return i > 0 ? true : false;
    }


    /**
     * 增强查询条件
     *
     * @author lijh
     * @since 2021-06-11
     * @param agentPeopleCommunication:
     * @param queryWrapper:
     * @return      void
     */
    private void query(AgentPeopleCommunication agentPeopleCommunication, QueryWrapper<AgentPeopleCommunication> queryWrapper) {
        /**
         * 排序
         */
        if(agentPeopleCommunication.getCollation() != null && StringUtils.isNotBlank(agentPeopleCommunication.getCollationFields())){
            if(agentPeopleCommunication.getCollation()){
                queryWrapper.orderByAsc(agentPeopleCommunication.getCollationFields());
            }else{
                queryWrapper.orderByDesc(agentPeopleCommunication.getCollationFields());
            }
        }else{
            queryWrapper.orderByDesc(agentPeopleCommunication.getCollationFields());
        }

        /**
        * 显示字段
        */
        if(StringUtils.isNotBlank(agentPeopleCommunication.getFields())){
            queryWrapper.select(agentPeopleCommunication.getFields());
        }
    }


    /**
     * 单条，增强返回参数追加
     *
     * @author lijh
     * @since 2021-06-11
     * @param agentPeopleCommunication:
     *
     * @return      AgentPeopleCommunication
     */
    private AgentPeopleCommunication assignment(AgentPeopleCommunication agentPeopleCommunication) {
        return agentPeopleCommunication;
    }

    /**
     * 分页,增强返回参数追加
     *
     * @author lijh
     * @since 2021-06-11
     * @param agentPeopleCommunicationList:
     *
     * @return      AgentPeopleCommunication
     */
    private IPage assignment(IPage<AgentPeopleCommunication> agentPeopleCommunicationList) {
        agentPeopleCommunicationList.getRecords().forEach(agentPeopleCommunication -> {
        });
        return agentPeopleCommunicationList;
    }


    /**
     * 集合,增强返回参数追加
     *
     * @author lijh
     * @since 2021-06-11
     * @param agentPeopleCommunicationList:
     *
     * @return      AgentPeopleCommunication
     */
    private List<AgentPeopleCommunication> assignment(List<AgentPeopleCommunication> agentPeopleCommunicationList) {
        agentPeopleCommunicationList.forEach(agentPeopleCommunication -> {
        });
        return agentPeopleCommunicationList;
    }
}
