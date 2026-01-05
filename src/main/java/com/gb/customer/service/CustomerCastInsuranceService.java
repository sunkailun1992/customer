package com.gb.customer.service;

import com.gb.customer.entity.CustomerCastInsurance;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gb.mq.crm.InsuredCustomerEvent;

import java.util.List;


/**
 * <p>
 * 客户投保订单关联 服务类
 * </p>
 *
 * @author lijh
 * @since 2021-06-08
 */
public interface CustomerCastInsuranceService extends IService<CustomerCastInsurance> {


    /**
     * 集合条件查询
     *
     * @param customerCastInsurance:
     * @return java.util.List<com.entity.CustomerCastInsurance>
     * @author lijh
     * @since 2021-06-08
     */
    List<CustomerCastInsurance> listEnhance(CustomerCastInsurance customerCastInsurance);


    /**
     * 分页条件查询
     *
     * @param page:
     * @param customerCastInsurance:
     * @return java.util.List<com.entity.CustomerCastInsurance>
     * @author lijh
     * @since 2021-06-08
     */
    IPage pageEnhance(Page page, CustomerCastInsurance customerCastInsurance);


    /**
     * 单条条件查询
     *
     * @param customerCastInsurance:
     * @return java.util.List<com.entity.CustomerCastInsurance>
     * @author lijh
     * @since 2021-06-08
     */
    CustomerCastInsurance getOneEnhance(CustomerCastInsurance customerCastInsurance);

    /**
     * 总数
     *
     * @param customerCastInsurance:
     * @return java.util.List<com.entity.CustomerCastInsurance>
     * @author lijh
     * @since 2021-06-08
     */
    Long countEnhance(CustomerCastInsurance customerCastInsurance);


    /**
     * 新增
     *
     * @param customerCastInsurance:
     * @return java.util.List<com.entity.CustomerCastInsurance>
     * @author lijh
     * @since 2021-06-08
     */
    Boolean saveEnhance(CustomerCastInsurance customerCastInsurance);


    /**
     * 修改
     *
     * @param customerCastInsurance:
     * @return java.util.List<com.entity.CustomerCastInsurance>
     * @author lijh
     * @since 2021-06-08
     */
    Boolean updateEnhance(CustomerCastInsurance customerCastInsurance);


    /**
     * TODO 投保订单id修改
     *
     * @param customerCastInsurance
     * @return java.lang.Boolean
     * @author 孙凯伦
     * @methodName castInsuranceIdUpdate
     * @time 2023/8/9 15:10
     */
    Boolean castInsuranceIdUpdate(CustomerCastInsurance customerCastInsurance);


    /**
     * 删除
     *
     * @param customerCastInsurance:
     * @return java.util.List<com.entity.CustomerCastInsurance>
     * @author lijh
     * @since 2021-06-08
     */
    Boolean removeEnhance(CustomerCastInsurance customerCastInsurance);

    /**
     * 根据投保单获取相关管家信息
     *
     * @param insuredCustomerEvent 赋值对象
     */
    void getInsuredCustomerEvent(InsuredCustomerEvent insuredCustomerEvent);
}
