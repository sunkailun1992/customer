package com.gb.customer.service;

import com.gb.customer.entity.CustomerEnterprise;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.List;


/**
 * <p>
 * 客户企业 服务类
 * </p>
 *
 * @author lijh
 * @since 2021-06-10
 */
public interface CustomerEnterpriseService extends IService<CustomerEnterprise> {


    /**
     * 集合条件查询
     * @author lijh
     * @since 2021-06-10
     * @param customerEnterprise:
     * @return      java.util.List<com.entity.CustomerEnterprise>
     */
    List<CustomerEnterprise> listEnhance(CustomerEnterprise customerEnterprise);


    /**
     * 分页条件查询
     * @author lijh
     * @since 2021-06-10
     * @param page:
     * @param customerEnterprise:
     * @return      java.util.List<com.entity.CustomerEnterprise>
     */
    IPage pageEnhance(Page page, CustomerEnterprise customerEnterprise);


    /**
     * 单条条件查询
     * @author lijh
     * @since 2021-06-10
     * @param customerEnterprise:
     * @return      java.util.List<com.entity.CustomerEnterprise>
     */
    CustomerEnterprise getOneEnhance(CustomerEnterprise customerEnterprise);


    /**
     * 总数
     * @author lijh
     * @since 2021-06-10
     * @param customerEnterprise:
     * @return      java.util.List<com.entity.CustomerEnterprise>
     */
    Long countEnhance(CustomerEnterprise customerEnterprise);


    /**
     * 新增
     * @author lijh
     * @since 2021-06-10
     * @param customerEnterprise:
     * @return      java.util.List<com.entity.CustomerEnterprise>
     */
    Boolean saveEnhance(CustomerEnterprise customerEnterprise);


    /**
     * 修改
     * @author lijh
     * @since 2021-06-10
     * @param customerEnterprise:
     * @return      java.util.List<com.entity.CustomerEnterprise>
     */
    Boolean updateEnhance(CustomerEnterprise customerEnterprise);


    /**
     * 删除
     * @author lijh
     * @since 2021-06-10
     * @param customerEnterprise:
     * @return      java.util.List<com.entity.CustomerEnterprise>
     */
    Boolean removeEnhance(CustomerEnterprise customerEnterprise);
}
