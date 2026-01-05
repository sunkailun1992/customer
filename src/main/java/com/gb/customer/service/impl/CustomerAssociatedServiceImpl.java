package com.gb.customer.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.gb.customer.entity.CustomerAssociated;
import com.gb.customer.enums.CustomerAssociatedTypeEnum;
import com.gb.customer.mapper.CustomerAssociatedMapper;
import com.gb.customer.service.CustomerAssociatedService;
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
 * 客户关联 服务实现类
 * </p>
 *
 * @author lijh
 * @since 2021-06-09
 */
@Slf4j
@Service
@Setter(onMethod_ = {@Autowired})
public class CustomerAssociatedServiceImpl extends ServiceImpl<CustomerAssociatedMapper, CustomerAssociated> implements CustomerAssociatedService {


    /**
     * 客户关联
     */
    private CustomerAssociatedMapper customerAssociatedMapper;


    /**
     * 集合条件查询
     *
     * @param customerAssociated:
     * @return java.util.List<com.entity.CustomerAssociated>
     * @author lijh
     * @since 2021-06-09
     */
    @Override
    public List<CustomerAssociated> listEnhance(CustomerAssociated customerAssociated) {
        QueryWrapper<CustomerAssociated> queryWrapper = new QueryWrapper<>(customerAssociated);
        query(customerAssociated, queryWrapper);
        return assignment(customerAssociatedMapper.selectList(queryWrapper));
    }


    /**
     * 分页条件查询
     *
     * @param customerAssociated:
     * @param page:
     * @return java.util.List<com.entity.CustomerAssociated>
     * @author lijh
     * @since 2021-06-09
     */
    @Override
    public IPage pageEnhance(Page page, CustomerAssociated customerAssociated) {
        QueryWrapper<CustomerAssociated> queryWrapper = new QueryWrapper<>(customerAssociated);
        query(customerAssociated, queryWrapper);
        return assignment(customerAssociatedMapper.selectPage(page, queryWrapper));
    }


    /**
     * 单条条件查询
     *
     * @param customerAssociated:
     * @return java.util.List<com.entity.CustomerAssociated>
     * @author lijh
     * @since 2021-06-09
     */
    @Override
    public CustomerAssociated getOneEnhance(CustomerAssociated customerAssociated) {
        QueryWrapper<CustomerAssociated> queryWrapper = new QueryWrapper<>(customerAssociated);
        query(customerAssociated, queryWrapper);
        return assignment(customerAssociatedMapper.selectOne(queryWrapper));
    }


    /**
     * 总数
     *
     * @param customerAssociated:
     * @return java.util.List<com.entity.CustomerAssociated>
     * @author lijh
     * @since 2021-06-09
     */
    @Override
    public Long countEnhance(CustomerAssociated customerAssociated) {
        QueryWrapper<CustomerAssociated> queryWrapper = new QueryWrapper<>(customerAssociated);
        query(customerAssociated, queryWrapper);
        return customerAssociatedMapper.selectCount(queryWrapper);
    }


    /**
     * 新增
     *
     * @param customerAssociated:
     * @return java.util.List<com.entity.CustomerAssociated>
     * @author lijh
     * @since 2021-06-09
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public Boolean saveEnhance(CustomerAssociated customerAssociated) {
        Integer i = customerAssociatedMapper.insert(customerAssociated);
        return i > 0 ? true : false;
    }


    /**
     * 修改
     *
     * @param customerAssociated:
     * @return java.util.List<com.entity.CustomerAssociated>
     * @author lijh
     * @since 2021-06-09
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public Boolean updateEnhance(CustomerAssociated customerAssociated) {
        UpdateWrapper<CustomerAssociated> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", customerAssociated.getId());
        Integer i = customerAssociatedMapper.update(customerAssociated, updateWrapper);
        return i > 0 ? true : false;
    }


    /**
     * 删除
     *
     * @param customerAssociated:
     * @return java.util.List<com.entity.CustomerAssociated>
     * @author lijh
     * @since 2021-06-09
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public Boolean removeEnhance(CustomerAssociated customerAssociated) {
        QueryWrapper<CustomerAssociated> queryWrapper = new QueryWrapper<>(customerAssociated);
        Integer i = customerAssociatedMapper.delete(queryWrapper);
        return i > 0 ? true : false;
    }

    /**
     * 保存客户和线索关联记录
     *
     * @param potentialCustomerId 线索id
     * @param customerId          客户id
     * @param associatedType      关联类型
     * @return
     */
    @Override
    public String saveCustomerAssociated(String potentialCustomerId, String customerId, CustomerAssociatedTypeEnum associatedType) {
        //客户和线索关联
        CustomerAssociated customerAssociated = new CustomerAssociated();
        customerAssociated.setType(0);
        //绑定客户和线索
        customerAssociated.setCustomerId(customerId);
        customerAssociated.setPotentialCustomerId(potentialCustomerId);
        customerAssociated.setType(associatedType.getValue());
        customerAssociatedMapper.insert(customerAssociated);
        return null;
    }


    /**
     * 增强查询条件
     *
     * @param customerAssociated:
     * @param queryWrapper:
     * @return void
     * @author lijh
     * @since 2021-06-09
     */
    private void query(CustomerAssociated customerAssociated, QueryWrapper<CustomerAssociated> queryWrapper) {
        /**
         * 排序
         */
        if (customerAssociated.getCollation() != null && StringUtils.isNotBlank(customerAssociated.getCollationFields())) {
            if (customerAssociated.getCollation()) {
                queryWrapper.orderByAsc(customerAssociated.getCollationFields());
            } else {
                queryWrapper.orderByDesc(customerAssociated.getCollationFields());
            }
        } else {
            queryWrapper.orderByDesc(customerAssociated.getCollationFields());
        }

        /**
         * 显示字段
         */
        if (StringUtils.isNotBlank(customerAssociated.getFields())) {
            queryWrapper.select(customerAssociated.getFields());
        }

        /**
         * 线索数组id
         */
        if (CollectionUtils.isNotEmpty(customerAssociated.getPotentialCustomerIds())) {
            queryWrapper.in("potential_customer_id", customerAssociated.getPotentialCustomerIds());
        }
    }


    /**
     * 单条，增强返回参数追加
     *
     * @param customerAssociated:
     * @return CustomerAssociated
     * @author lijh
     * @since 2021-06-09
     */
    private CustomerAssociated assignment(CustomerAssociated customerAssociated) {
        return customerAssociated;
    }

    /**
     * 分页,增强返回参数追加
     *
     * @param customerAssociatedList:
     * @return CustomerAssociated
     * @author lijh
     * @since 2021-06-09
     */
    private IPage assignment(IPage<CustomerAssociated> customerAssociatedList) {
        customerAssociatedList.getRecords().forEach(customerAssociated -> {
        });
        return customerAssociatedList;
    }


    /**
     * 集合,增强返回参数追加
     *
     * @param customerAssociatedList:
     * @return CustomerAssociated
     * @author lijh
     * @since 2021-06-09
     */
    private List<CustomerAssociated> assignment(List<CustomerAssociated> customerAssociatedList) {
        customerAssociatedList.forEach(customerAssociated -> {
        });
        return customerAssociatedList;
    }
}
