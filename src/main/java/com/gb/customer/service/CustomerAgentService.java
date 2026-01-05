package com.gb.customer.service;

import com.gb.customer.dto.CustomerInfoReqDto;
import com.gb.customer.entity.enums.CustomerAgentOperateRecordSourceEnum;
import com.gb.customer.entity.query.CustomerAgentQuery;
import com.gb.customer.entity.vo.CustomerAgentVO;
import com.gb.customer.entity.bo.CustomerAgentBO;
import com.gb.customer.entity.CustomerAgent;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;
import java.util.Map;


/**
 * TODO 客户经纪人，Service服务接口层
 * 代码生成器
 *
 * @author lijh
 * @className CustomerAgentService
 * @time 2022-08-31 09:35:12
 */
public interface CustomerAgentService extends IService<CustomerAgent> {


    /**
     * TODO 分页
     *
     * @param page
     * @param customerAgentQuery 客户经纪人
     * @return Page<CustomerAgentVO>
     * @author lijh
     * @methodName pageEnhance
     * @time 2022-08-31 09:35:12
     */
    Page<CustomerAgentVO> pageEnhance(Page page, CustomerAgentQuery customerAgentQuery);


    /**
     * TODO 集合
     *
     * @param customerAgentQuery 客户经纪人
     * @return List<CustomerAgentVO>
     * @author lijh
     * @methodName listEnhance
     * @time 2022-08-31 09:35:12
     */
    List<CustomerAgentVO> listEnhance(CustomerAgentQuery customerAgentQuery);


    /**
     * TODO 单条
     *
     * @param customerAgentQuery 客户经纪人
     * @return CustomerAgentVO
     * @author lijh
     * @methodName getOneEnhance
     * @time 2022-08-31 09:35:12
     */
    CustomerAgentVO getOneEnhance(CustomerAgentQuery customerAgentQuery);


    /**
     * TODO 总数
     *
     * @param customerAgentQuery 客户经纪人
     * @return Long
     * @author lijh
     * @methodName countEnhance
     * @time 2022-08-31 09:35:12
     */
    Long countEnhance(CustomerAgentQuery customerAgentQuery);


    /**
     * TODO 新增
     *
     * @param customerAgentBO 客户经纪人
     * @return String
     * @author lijh
     * @methodName saveEnhance
     * @time 2022-08-31 09:35:12
     */
    String saveEnhance(CustomerAgentBO customerAgentBO);


    /**
     * TODO 修改
     *
     * @param customerAgentBO 客户经纪人
     * @return Boolean
     * @author lijh
     * @methodName updateEnhance
     * @time 2022-08-31 09:35:12
     */
    Boolean updateEnhance(CustomerAgentBO customerAgentBO);

    /**
     * 编辑或修改 客户经纪人
     *
     * @param customerAgentBO
     * @return
     */
    Boolean saveOrEdit(CustomerAgentBO customerAgentBO);


    /**
     * TODO 删除
     *
     * @param customerAgentBO 客户经纪人
     * @return Boolean
     * @author lijh
     * @methodName removeEnhance
     * @time 2022-08-31 09:35:12
     */
    Boolean removeEnhance(CustomerAgentBO customerAgentBO);

    /**
     * 分配客户经纪人
     *
     * @param customerAgentBO
     * @return
     */
    String allotCustomerAgent(CustomerAgentBO customerAgentBO);

    /**
     * 批量分配经纪人
     *
     * @param customerAgentList
     * @return
     */
    Boolean batchAllotCustomerAgent(List<CustomerAgent> customerAgentList);

    /**
     * 生成客户经纪人
     *
     * @param customerId                           客户id
     * @param agentUserId                          经纪人id
     * @param customerAgentType                    经纪人类型
     * @param customerAgentOperateRecordSourceEnum 操作来源
     */
    void saveCustomerAgent(String customerId, String agentUserId, Integer customerAgentType, CustomerAgentOperateRecordSourceEnum customerAgentOperateRecordSourceEnum);

    /**
     * 转交经纪人
     *
     * @param customerAgentBO
     * @return
     */
    Boolean transferAgent(CustomerAgentBO customerAgentBO);

    /**
     * 批量移入公海
     *
     * @param customerAgentList
     * @return
     */
    Boolean batchAllotUntie(List<CustomerAgent> customerAgentList);

    /**
     * 自营经纪人下所有客户
     *
     * @param page
     * @param customerAgentQuery
     * @return
     */
    Object selectCustomerByAgentUserId(Page page, CustomerAgentQuery customerAgentQuery);

    /**
     * 根据筛选条件 获取客户id
     *
     * @param customerAgentQuery
     * @return
     */
    List<String> selectCustomerIds(CustomerAgentQuery customerAgentQuery);

    /**
     * 新增客户经纪人备注   仅限客户生成咨询单时调用
     *
     * @param customerAgent
     */
    void saveCustomerRemark(CustomerAgent customerAgent);

    /**
     * 获取已经分配经纪人的客户(不在公海)
     *
     * @return 客户手机号集合
     */
    List<String> getCustomerMobileAlreadyAllotAgent();

    /**
     * 获取经纪人对客户备注
     *
     * @param customerId
     * @param agentUserId
     * @return
     */
    CustomerAgent getAgentRemarkCustomer(String customerId, String agentUserId);

    /**
     * 根据userId批量获取客户id
     *
     * @param customerInfoReqDtoList
     * @return
     */
    Map<String, String> getCustomerIdByUserId(List<CustomerInfoReqDto> customerInfoReqDtoList);

}
