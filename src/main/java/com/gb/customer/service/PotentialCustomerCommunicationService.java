package com.gb.customer.service;

import com.gb.customer.entity.PotentialCustomerCommunication;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.List;


/**
 * <p>
 * 潜在客户沟通表 服务类
 * </p>
 *
 * @author 王一飞
 * @since 2021-04-13
 */
public interface PotentialCustomerCommunicationService extends IService<PotentialCustomerCommunication> {


    /**
     * 集合条件查询
     * @author 王一飞
     * @since 2021-04-13
     * @param potentialCustomerCommunication:
     * @return      java.util.List<com.entity.PotentialCustomerCommunication>
     */
    List<PotentialCustomerCommunication> listEnhance(PotentialCustomerCommunication potentialCustomerCommunication);


    /**
     * 分页条件查询
     * @author 王一飞
     * @since 2021-04-13
     * @param page:
     * @param potentialCustomerCommunication:
     * @return      java.util.List<com.entity.PotentialCustomerCommunication>
     */
    IPage pageEnhance(Page page, PotentialCustomerCommunication potentialCustomerCommunication);


    /**
     * 单条条件查询
     * @author 王一飞
     * @since 2021-04-13
     * @param potentialCustomerCommunication:
     * @return      java.util.List<com.entity.PotentialCustomerCommunication>
     */
    PotentialCustomerCommunication getOneEnhance(PotentialCustomerCommunication potentialCustomerCommunication);


    /**
     * 总数
     * @author 王一飞
     * @since 2021-04-13
     * @param potentialCustomerCommunication:
     * @return      java.util.List<com.entity.PotentialCustomerCommunication>
     */
    Long countEnhance(PotentialCustomerCommunication potentialCustomerCommunication);


    /**
     * 新增
     * @author 王一飞
     * @since 2021-04-13
     * @param potentialCustomerCommunication:
     * @return      java.util.List<com.entity.PotentialCustomerCommunication>
     */
    Boolean saveEnhance(PotentialCustomerCommunication potentialCustomerCommunication);


    /**
     * 修改
     * @author 王一飞
     * @since 2021-04-13
     * @param potentialCustomerCommunication:
     * @return      java.util.List<com.entity.PotentialCustomerCommunication>
     */
    Boolean updateEnhance(PotentialCustomerCommunication potentialCustomerCommunication);


    /**
     * 删除
     * @author 王一飞
     * @since 2021-04-13
     * @param potentialCustomerCommunication:
     * @return      java.util.List<com.entity.PotentialCustomerCommunication>
     */
    Boolean removeEnhance(PotentialCustomerCommunication potentialCustomerCommunication);
}
