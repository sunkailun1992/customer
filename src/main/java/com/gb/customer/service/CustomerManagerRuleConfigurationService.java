package com.gb.customer.service;

import com.gb.customer.entity.query.CustomerManagerRuleConfigurationQuery;
import com.gb.customer.entity.vo.CustomerManagerRuleConfigurationVO;
import com.gb.customer.entity.bo.CustomerManagerRuleConfigurationBO;
import com.gb.customer.entity.CustomerManagerRuleConfiguration;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;


/**
 * Created with IntelliJ IDEA.
 *
 * @author: ljh
 * @since: 2021-09-07 02:27:46
 * @description: TODO 客户管理规则配置表，Service服务接口层
 * @source: 代码生成器
 */
public interface CustomerManagerRuleConfigurationService extends IService<CustomerManagerRuleConfiguration> {


    /**
     * 集合条件查询
     *
     * @param customerManagerRuleConfigurationQuery:
     * @return java.util.List<com.entity.CustomerManagerRuleConfigurationVO>
     * @author ljh
     * @since 2021-09-07 02:27:46
     */
    List<CustomerManagerRuleConfigurationVO> listEnhance(CustomerManagerRuleConfigurationQuery customerManagerRuleConfigurationQuery);


    /**
     * 分页条件查询
     *
     * @param page:
     * @param customerManagerRuleConfigurationQuery:
     * @return com.baomidou.mybatisplus.extension.plugins.pagination.Page
     * @author ljh
     * @since 2021-09-07 02:27:46
     */
    Page<CustomerManagerRuleConfigurationVO> pageEnhance(Page page, CustomerManagerRuleConfigurationQuery customerManagerRuleConfigurationQuery);


    /**
     * 单条条件查询
     *
     * @param customerManagerRuleConfigurationQuery:
     * @return java.util.List<com.entity.CustomerManagerRuleConfigurationVO>
     * @author ljh
     * @since 2021-09-07 02:27:46
     */
    CustomerManagerRuleConfigurationVO getOneEnhance(CustomerManagerRuleConfigurationQuery customerManagerRuleConfigurationQuery);


    /**
     * 总数
     *
     * @param customerManagerRuleConfigurationQuery:
     * @return java.lang.Integer
     * @author ljh
     * @since 2021-09-07 02:27:46
     */
    Long countEnhance(CustomerManagerRuleConfigurationQuery customerManagerRuleConfigurationQuery);


    /**
     * 新增
     *
     * @param customerManagerRuleConfigurationBO:
     * @return java.lang.String
     * @author ljh
     * @since 2021-09-07 02:27:46
     */
    String saveEnhance(CustomerManagerRuleConfigurationBO customerManagerRuleConfigurationBO);


    /**
     * 修改
     *
     * @param customerManagerRuleConfigurationBO:
     * @return java.lang.Boolean
     * @author ljh
     * @since 2021-09-07 02:27:46
     */
    Boolean updateEnhance(CustomerManagerRuleConfigurationBO customerManagerRuleConfigurationBO);


    /**
     * 删除
     *
     * @param customerManagerRuleConfigurationBO:
     * @return java.lang.Boolean
     * @author ljh
     * @since 2021-09-07 02:27:46
     */
    Boolean removeEnhance(CustomerManagerRuleConfigurationBO customerManagerRuleConfigurationBO);

    /**
     * 是否自动分配
     *
     * @return
     */
    Boolean isAssigned();
}
