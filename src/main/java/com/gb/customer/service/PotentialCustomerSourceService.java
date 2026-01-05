package com.gb.customer.service;

import com.gb.customer.entity.PotentialCustomerSource;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.List;


/**
 * <p>
 * 潜在客户来源表 服务类
 * </p>
 *
 * @author 王一飞
 * @since 2021-04-13
 */
public interface PotentialCustomerSourceService extends IService<PotentialCustomerSource> {


    /**
     * 集合条件查询
     * @author 王一飞
     * @since 2021-04-13
     * @param potentialCustomerSource:
     * @return      java.util.List<com.entity.PotentialCustomerSource>
     */
    List<PotentialCustomerSource> listEnhance(PotentialCustomerSource potentialCustomerSource);


    /**
     * 分页条件查询
     * @author 王一飞
     * @since 2021-04-13
     * @param page:
     * @param potentialCustomerSource:
     * @return      java.util.List<com.entity.PotentialCustomerSource>
     */
    IPage pageEnhance(Page page, PotentialCustomerSource potentialCustomerSource);


    /**
     * 单条条件查询
     * @author 王一飞
     * @since 2021-04-13
     * @param potentialCustomerSource:
     * @return      java.util.List<com.entity.PotentialCustomerSource>
     */
    PotentialCustomerSource getOneEnhance(PotentialCustomerSource potentialCustomerSource);


    /**
     * 总数
     * @author 王一飞
     * @since 2021-04-13
     * @param potentialCustomerSource:
     * @return      java.util.List<com.entity.PotentialCustomerSource>
     */
    Long countEnhance(PotentialCustomerSource potentialCustomerSource);


    /**
     * 新增
     * @author 王一飞
     * @since 2021-04-13
     * @param potentialCustomerSource:
     * @return      java.util.List<com.entity.PotentialCustomerSource>
     */
    Boolean saveEnhance(PotentialCustomerSource potentialCustomerSource);


    /**
     * 修改
     * @author 王一飞
     * @since 2021-04-13
     * @param potentialCustomerSource:
     * @return      java.util.List<com.entity.PotentialCustomerSource>
     */
    Boolean updateEnhance(PotentialCustomerSource potentialCustomerSource);


    /**
     * 删除
     * @author 王一飞
     * @since 2021-04-13
     * @param potentialCustomerSource:
     * @return      java.util.List<com.entity.PotentialCustomerSource>
     */
    Boolean removeEnhance(PotentialCustomerSource potentialCustomerSource);
}
