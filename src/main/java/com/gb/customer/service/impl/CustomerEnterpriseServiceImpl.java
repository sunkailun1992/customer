package com.gb.customer.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.gb.customer.entity.CustomerEnterprise;
import com.gb.customer.mapper.CustomerEnterpriseMapper;
import com.gb.customer.service.CustomerEnterpriseService;
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

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 客户企业 服务实现类
 * </p>
 *
 * @author lijh
 * @since 2021-06-10
 */
@Slf4j
@Service
@Setter(onMethod_ = {@Autowired})
public class CustomerEnterpriseServiceImpl extends ServiceImpl<CustomerEnterpriseMapper, CustomerEnterprise> implements CustomerEnterpriseService {


    /**
     * 客户企业
     */
    private CustomerEnterpriseMapper customerEnterpriseMapper;


    /**
     * 集合条件查询
     *
     * @param customerEnterprise:
     * @return java.util.List<com.entity.CustomerEnterprise>
     * @author lijh
     * @since 2021-06-10
     */
    @Override
    public List<CustomerEnterprise> listEnhance(CustomerEnterprise customerEnterprise) {
        QueryWrapper<CustomerEnterprise> queryWrapper = new QueryWrapper<>(customerEnterprise);
        query(customerEnterprise, queryWrapper);
        return assignment(customerEnterpriseMapper.selectList(queryWrapper));
    }


    /**
     * 分页条件查询
     *
     * @param customerEnterprise:
     * @param page:
     * @return java.util.List<com.entity.CustomerEnterprise>
     * @author lijh
     * @since 2021-06-10
     */
    @Override
    public IPage pageEnhance(Page page, CustomerEnterprise customerEnterprise) {
        QueryWrapper<CustomerEnterprise> queryWrapper = new QueryWrapper<>(customerEnterprise);
        query(customerEnterprise, queryWrapper);
        return assignment(customerEnterpriseMapper.selectPage(page, queryWrapper));
    }


    /**
     * 单条条件查询
     *
     * @param customerEnterprise:
     * @return java.util.List<com.entity.CustomerEnterprise>
     * @author lijh
     * @since 2021-06-10
     */
    @Override
    public CustomerEnterprise getOneEnhance(CustomerEnterprise customerEnterprise) {
        QueryWrapper<CustomerEnterprise> queryWrapper = new QueryWrapper<>(customerEnterprise);
        query(customerEnterprise, queryWrapper);
        return assignment(customerEnterpriseMapper.selectOne(queryWrapper));
    }


    /**
     * 总数
     *
     * @param customerEnterprise:
     * @return java.util.List<com.entity.CustomerEnterprise>
     * @author lijh
     * @since 2021-06-10
     */
    @Override
    public Long countEnhance(CustomerEnterprise customerEnterprise) {
        QueryWrapper<CustomerEnterprise> queryWrapper = new QueryWrapper<>(customerEnterprise);
        query(customerEnterprise, queryWrapper);
        return customerEnterpriseMapper.selectCount(queryWrapper);
    }


    /**
     * 新增
     *
     * @param customerEnterprise:
     * @return java.util.List<com.entity.CustomerEnterprise>
     * @author lijh
     * @since 2021-06-10
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public Boolean saveEnhance(CustomerEnterprise customerEnterprise) {
        Integer i = customerEnterpriseMapper.insert(customerEnterprise);
        return i > 0 ? true : false;
    }


    /**
     * 修改
     *
     * @param customerEnterprise:
     * @return java.util.List<com.entity.CustomerEnterprise>
     * @author lijh
     * @since 2021-06-10
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public Boolean updateEnhance(CustomerEnterprise customerEnterprise) {
        UpdateWrapper<CustomerEnterprise> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", customerEnterprise.getId());
        Integer i = customerEnterpriseMapper.update(customerEnterprise, updateWrapper);
        return i > 0 ? true : false;
    }


    /**
     * 删除
     *
     * @param customerEnterprise:
     * @return java.util.List<com.entity.CustomerEnterprise>
     * @author lijh
     * @since 2021-06-10
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public Boolean removeEnhance(CustomerEnterprise customerEnterprise) {
        QueryWrapper<CustomerEnterprise> queryWrapper = new QueryWrapper<>(customerEnterprise);
        Integer i = customerEnterpriseMapper.delete(queryWrapper);
        return i > 0 ? true : false;
    }


    /**
     * 增强查询条件
     *
     * @param customerEnterprise:
     * @param queryWrapper:
     * @return void
     * @author lijh
     * @since 2021-06-10
     */
    private void query(CustomerEnterprise customerEnterprise, QueryWrapper<CustomerEnterprise> queryWrapper) {
        /**
         * 排序
         */
        if (customerEnterprise.getCollation() != null && StringUtils.isNotBlank(customerEnterprise.getCollationFields())) {
            if (customerEnterprise.getCollation()) {
                queryWrapper.orderByAsc(customerEnterprise.getCollationFields());
            } else {
                queryWrapper.orderByDesc(customerEnterprise.getCollationFields());
            }
        } else {
            queryWrapper.orderByDesc(customerEnterprise.getCollationFields());
        }

        /**
         * 显示字段
         */
        if (StringUtils.isNotBlank(customerEnterprise.getFields())) {
            queryWrapper.select(customerEnterprise.getFields());
        }

        /**
         * 提交时间 区间查询
         */
        String startTime = customerEnterprise.getStartTime();
        String endTime = customerEnterprise.getEndTime();
        if (StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)) {
            queryWrapper.between("create_date_time", startTime, endTime);
        }

        /**
         * 根据企业名称查找
         */
        if (StringUtils.isNotBlank(customerEnterprise.getNameQuery())) {
            queryWrapper.eq("name", customerEnterprise.getNameQuery());
        }
        /**
         * 查找所有用户对应的企业
         */
        if (CollectionUtils.isNotEmpty(customerEnterprise.getCustomerIds())) {
            queryWrapper.in("customer_id", customerEnterprise.getCustomerIds());
        }
    }


    /**
     * 单条，增强返回参数追加
     *
     * @param customerEnterprise:
     * @return CustomerEnterprise
     * @author lijh
     * @since 2021-06-10
     */
    private CustomerEnterprise assignment(CustomerEnterprise customerEnterprise) {
        return customerEnterprise;
    }

    /**
     * 分页,增强返回参数追加
     *
     * @param customerEnterpriseList:
     * @return CustomerEnterprise
     * @author lijh
     * @since 2021-06-10
     */
    private IPage assignment(IPage<CustomerEnterprise> customerEnterpriseList) {
        customerEnterpriseList.getRecords().forEach(customerEnterprise -> {
        });
        return customerEnterpriseList;
    }


    /**
     * 集合,增强返回参数追加
     *
     * @param customerEnterpriseList:
     * @return CustomerEnterprise
     * @author lijh
     * @since 2021-06-10
     */
    private List<CustomerEnterprise> assignment(List<CustomerEnterprise> customerEnterpriseList) {
        customerEnterpriseList.forEach(customerEnterprise -> {
        });
        return customerEnterpriseList;
    }
}
