package com.gb.customer.service;

import com.gb.customer.entity.CustomerAssociated;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gb.customer.enums.CustomerAssociatedTypeEnum;

import java.util.List;


/**
 * <p>
 * 客户关联 服务类
 * </p>
 *
 * @author lijh
 * @since 2021-06-09
 */
public interface CustomerAssociatedService extends IService<CustomerAssociated> {


    /**
     * 集合条件查询
     *
     * @param customerAssociated:
     * @return java.util.List<com.entity.CustomerAssociated>
     * @author lijh
     * @since 2021-06-09
     */
    List<CustomerAssociated> listEnhance(CustomerAssociated customerAssociated);


    /**
     * 分页条件查询
     *
     * @param page:
     * @param customerAssociated:
     * @return java.util.List<com.entity.CustomerAssociated>
     * @author lijh
     * @since 2021-06-09
     */
    IPage pageEnhance(Page page, CustomerAssociated customerAssociated);


    /**
     * 单条条件查询
     *
     * @param customerAssociated:
     * @return java.util.List<com.entity.CustomerAssociated>
     * @author lijh
     * @since 2021-06-09
     */
    CustomerAssociated getOneEnhance(CustomerAssociated customerAssociated);


    /**
     * 总数
     *
     * @param customerAssociated:
     * @return java.util.List<com.entity.CustomerAssociated>
     * @author lijh
     * @since 2021-06-09
     */
    Long countEnhance(CustomerAssociated customerAssociated);


    /**
     * 新增
     *
     * @param customerAssociated:
     * @return java.util.List<com.entity.CustomerAssociated>
     * @author lijh
     * @since 2021-06-09
     */
    Boolean saveEnhance(CustomerAssociated customerAssociated);


    /**
     * 修改
     *
     * @param customerAssociated:
     * @return java.util.List<com.entity.CustomerAssociated>
     * @author lijh
     * @since 2021-06-09
     */
    Boolean updateEnhance(CustomerAssociated customerAssociated);


    /**
     * 删除
     *
     * @param customerAssociated:
     * @return java.util.List<com.entity.CustomerAssociated>
     * @author lijh
     * @since 2021-06-09
     */
    Boolean removeEnhance(CustomerAssociated customerAssociated);

    /**
     * 添加客户线索关联记录
     *
     * @param potentialCustomerId 线索id
     * @param customerId          客户id
     * @param associatedType      关联类型
     * @return
     */
    String saveCustomerAssociated(String potentialCustomerId, String customerId, CustomerAssociatedTypeEnum associatedType);
}
