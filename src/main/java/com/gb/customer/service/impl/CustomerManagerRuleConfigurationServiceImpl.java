package com.gb.customer.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.gb.customer.entity.enums.CustomerManagerRuleConfigurationRuleStatusEnum;
import com.gb.customer.entity.query.CustomerManagerRuleConfigurationQuery;
import com.gb.customer.entity.vo.CustomerManagerRuleConfigurationVO;
import com.gb.customer.entity.bo.CustomerManagerRuleConfigurationBO;
import com.gb.customer.entity.CustomerManagerRuleConfiguration;
import com.gb.customer.mapper.CustomerManagerRuleConfigurationMapper;
import com.gb.customer.service.CustomerManagerRuleConfigurationService;
import com.gb.customer.service.query.CustomerManagerRuleConfigurationServiceQuery;
import com.gb.customer.service.results.CustomerManagerRuleConfigurationServiceResults;
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
 * Created with IntelliJ IDEA.
 * @author:     	ljh
 * @since:   	    2021-09-07 02:27:46
 * @description:	TODO  客户管理规则配置表，Service服务实现层
 * @source:  	    代码生成器
 */
@Slf4j
@Service
@Setter(onMethod_ = {@Autowired})
public class CustomerManagerRuleConfigurationServiceImpl extends ServiceImpl<CustomerManagerRuleConfigurationMapper, CustomerManagerRuleConfiguration> implements CustomerManagerRuleConfigurationService {


    /**
     * 客户管理规则配置表
     */
    private CustomerManagerRuleConfigurationMapper customerManagerRuleConfigurationMapper;


    /**
     * 集合条件查询
     * @author  ljh
     * @since   2021-09-07 02:27:46
     * @param   customerManagerRuleConfigurationQuery:
     * @return  java.util.List<com.entity.CustomerManagerRuleConfigurationVO>
     */
    @Override
    public List<CustomerManagerRuleConfigurationVO> listEnhance(CustomerManagerRuleConfigurationQuery customerManagerRuleConfigurationQuery) {
        CustomerManagerRuleConfiguration customerManagerRuleConfiguration = GeneralConvertor.convertor(customerManagerRuleConfigurationQuery, CustomerManagerRuleConfiguration.class);
        QueryWrapper<CustomerManagerRuleConfiguration> queryWrapper = new QueryWrapper<>(customerManagerRuleConfiguration);
        //TODO 自动生成查询，禁止手动写语句
        CustomerManagerRuleConfigurationServiceQuery.query(customerManagerRuleConfigurationQuery, queryWrapper);
        // TODO 人工查询条件
        queryArtificial(customerManagerRuleConfigurationQuery, queryWrapper);
        //DO数据
        List<CustomerManagerRuleConfiguration> customerManagerRuleConfigurationDO = customerManagerRuleConfigurationMapper.selectList(queryWrapper);
        //VO数据
        List<CustomerManagerRuleConfigurationVO> customerManagerRuleConfigurationVO = GeneralConvertor.convertor(customerManagerRuleConfigurationDO, CustomerManagerRuleConfigurationVO.class);
        return CustomerManagerRuleConfigurationServiceResults.assignment(customerManagerRuleConfigurationVO);
    }


    /**
     * 分页条件查询
     * @author  ljh
     * @since   2021-09-07 02:27:46
     * @param   page:
     * @param   customerManagerRuleConfigurationQuery:
     * @return  com.baomidou.mybatisplus.core.metadata.IPage
     */
    @Override
    public Page<CustomerManagerRuleConfigurationVO> pageEnhance(Page page, CustomerManagerRuleConfigurationQuery customerManagerRuleConfigurationQuery) {
        CustomerManagerRuleConfiguration customerManagerRuleConfiguration = GeneralConvertor.convertor(customerManagerRuleConfigurationQuery, CustomerManagerRuleConfiguration.class);
        QueryWrapper<CustomerManagerRuleConfiguration> queryWrapper = new QueryWrapper<>(customerManagerRuleConfiguration);
        //TODO 自动生成查询，禁止手动写语句
        CustomerManagerRuleConfigurationServiceQuery.query(customerManagerRuleConfigurationQuery, queryWrapper);
        // TODO 人工查询条件
        queryArtificial(customerManagerRuleConfigurationQuery, queryWrapper);
        //DO数据
        Page<CustomerManagerRuleConfiguration> pageDO = customerManagerRuleConfigurationMapper.selectPage(page, queryWrapper);
        //VO数据
        Page<CustomerManagerRuleConfigurationVO> pageVO = CustomerManagerRuleConfigurationServiceResults.toPageVO(pageDO);
        return CustomerManagerRuleConfigurationServiceResults.assignment(pageVO);
    }


    /**
     * 单条条件查询
     * @author  ljh
     * @since   2021-09-07 02:27:46
     * @param   customerManagerRuleConfigurationQuery:
     * @return  java.util.List<com.entity.CustomerManagerRuleConfigurationVO>
     */
    @Override
    public CustomerManagerRuleConfigurationVO getOneEnhance(CustomerManagerRuleConfigurationQuery customerManagerRuleConfigurationQuery) {
        CustomerManagerRuleConfiguration customerManagerRuleConfiguration = GeneralConvertor.convertor(customerManagerRuleConfigurationQuery, CustomerManagerRuleConfiguration.class);
        QueryWrapper<CustomerManagerRuleConfiguration> queryWrapper = new QueryWrapper<>(customerManagerRuleConfiguration);
        //TODO 自动生成查询，禁止手动写语句
        CustomerManagerRuleConfigurationServiceQuery.query(customerManagerRuleConfigurationQuery, queryWrapper);
        // TODO 人工查询条件
        queryArtificial(customerManagerRuleConfigurationQuery, queryWrapper);
        //DO数据
        CustomerManagerRuleConfiguration customerManagerRuleConfigurationDO = customerManagerRuleConfigurationMapper.selectOne(queryWrapper);
        //VO数据
        CustomerManagerRuleConfigurationVO customerManagerRuleConfigurationVO = GeneralConvertor.convertor(customerManagerRuleConfigurationDO, CustomerManagerRuleConfigurationVO.class);
        return CustomerManagerRuleConfigurationServiceResults.assignment(customerManagerRuleConfigurationVO);
    }


    /**
     * 总数
     * @author  ljh
     * @since   2021-09-07 02:27:46
     * @param   customerManagerRuleConfigurationQuery:
     * @return  java.lang.Integer
     */
    @Override
    public Long countEnhance(CustomerManagerRuleConfigurationQuery customerManagerRuleConfigurationQuery) {
        CustomerManagerRuleConfiguration customerManagerRuleConfiguration = GeneralConvertor.convertor(customerManagerRuleConfigurationQuery, CustomerManagerRuleConfiguration.class);
        QueryWrapper<CustomerManagerRuleConfiguration> queryWrapper = new QueryWrapper<>(customerManagerRuleConfiguration);
        //TODO 自动生成查询，禁止手动写语句
        CustomerManagerRuleConfigurationServiceQuery.query(customerManagerRuleConfigurationQuery, queryWrapper);
        // TODO 人工查询条件
        queryArtificial(customerManagerRuleConfigurationQuery, queryWrapper);
        return customerManagerRuleConfigurationMapper.selectCount(queryWrapper);
    }


    /**
     * 新增
     * @author  ljh
     * @since   2021-09-07 02:27:46
     * @param   customerManagerRuleConfigurationBO:
     * @return  java.lang.String
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public String saveEnhance(CustomerManagerRuleConfigurationBO customerManagerRuleConfigurationBO) {
        CustomerManagerRuleConfiguration customerManagerRuleConfiguration = GeneralConvertor.convertor(customerManagerRuleConfigurationBO, CustomerManagerRuleConfiguration.class);
        customerManagerRuleConfigurationMapper.insert(customerManagerRuleConfiguration);
        return customerManagerRuleConfiguration.getId();
    }


    /**
     * 修改
     * @author  ljh
     * @since   2021-09-07 02:27:46
     * @param   customerManagerRuleConfigurationBO:
     * @return  java.lang.Boolean
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public Boolean updateEnhance(CustomerManagerRuleConfigurationBO customerManagerRuleConfigurationBO) {
        CustomerManagerRuleConfiguration customerManagerRuleConfiguration = GeneralConvertor.convertor(customerManagerRuleConfigurationBO, CustomerManagerRuleConfiguration.class);
        UpdateWrapper<CustomerManagerRuleConfiguration > updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", customerManagerRuleConfigurationBO.getId());
        Integer i = customerManagerRuleConfigurationMapper.update(customerManagerRuleConfiguration, updateWrapper);
        return i > 0 ? true : false;
    }


    /**
     * 删除
     * @author  ljh
     * @since   2021-09-07 02:27:46
     * @param   customerManagerRuleConfigurationBO:
     * @return  java.lang.Boolean
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public Boolean removeEnhance(CustomerManagerRuleConfigurationBO customerManagerRuleConfigurationBO) {
        CustomerManagerRuleConfiguration customerManagerRuleConfiguration = GeneralConvertor.convertor(customerManagerRuleConfigurationBO, CustomerManagerRuleConfiguration.class);
        QueryWrapper<CustomerManagerRuleConfiguration> queryWrapper = new QueryWrapper<>(customerManagerRuleConfiguration);
        Integer i = customerManagerRuleConfigurationMapper.delete(queryWrapper);
        return i > 0 ? true : false;
    }

    @Override
    public Boolean isAssigned() {
        List<CustomerManagerRuleConfiguration> customerManagerRuleConfigurationDO = customerManagerRuleConfigurationMapper.selectList(new QueryWrapper<>());
        if (CollectionUtils.isEmpty(customerManagerRuleConfigurationDO)){
            return true;
        }
        if (CustomerManagerRuleConfigurationRuleStatusEnum.开启.getValue().equals(customerManagerRuleConfigurationDO.get(0).getRuleStatus().getValue())){
            return true;
        }
        return false;
    }


    /**
     * 查询人工查询条件
     *
     * @author     	ljh
     * @since   	2021-09-07 02:27:46
     * @param       customerManagerRuleConfigurationQuery 客户管理规则配置表
     * @return      QueryWrapper
     */
     private QueryWrapper queryArtificial(CustomerManagerRuleConfigurationQuery customerManagerRuleConfigurationQuery, QueryWrapper<CustomerManagerRuleConfiguration> queryWrapper) {
        return queryWrapper;
    }
}