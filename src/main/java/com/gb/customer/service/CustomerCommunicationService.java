package com.gb.customer.service;

import com.gb.customer.entity.CustomerCommunication;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.List;


/**
 * <p>
 * 客户沟通表 服务类
 * </p>
 *
 * @author lijh
 * @since 2021-06-08
 */
public interface CustomerCommunicationService extends IService<CustomerCommunication> {


    /**
     * 集合条件查询
     * @author lijh
     * @since 2021-06-08
     * @param customerCommunication:
     * @return      java.util.List<com.entity.CustomerCommunication>
     */
    List<CustomerCommunication> listEnhance(CustomerCommunication customerCommunication);


    /**
     * 分页条件查询
     * @author lijh
     * @since 2021-06-08
     * @param page:
     * @param customerCommunication:
     * @return      java.util.List<com.entity.CustomerCommunication>
     */
    IPage pageEnhance(Page page, CustomerCommunication customerCommunication);


    /**
     * 单条条件查询
     * @author lijh
     * @since 2021-06-08
     * @param customerCommunication:
     * @return      java.util.List<com.entity.CustomerCommunication>
     */
    CustomerCommunication getOneEnhance(CustomerCommunication customerCommunication);


    /**
     * 总数
     * @author lijh
     * @since 2021-06-08
     * @param customerCommunication:
     * @return      java.util.List<com.entity.CustomerCommunication>
     */
    Long countEnhance(CustomerCommunication customerCommunication);


    /**
     * 新增
     * @author lijh
     * @since 2021-06-08
     * @param customerCommunication:
     * @return      java.util.List<com.entity.CustomerCommunication>
     */
    Boolean saveEnhance(CustomerCommunication customerCommunication);


    /**
     * 修改
     * @author lijh
     * @since 2021-06-08
     * @param customerCommunication:
     * @return      java.util.List<com.entity.CustomerCommunication>
     */
    Boolean updateEnhance(CustomerCommunication customerCommunication);


    /**
     * 删除
     * @author lijh
     * @since 2021-06-08
     * @param customerCommunication:
     * @return      java.util.List<com.entity.CustomerCommunication>
     */
    Boolean removeEnhance(CustomerCommunication customerCommunication);
}
