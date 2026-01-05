package com.gb.customer.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gb.customer.dto.CustomerLabelDto;
import com.gb.customer.dto.OfflineCastInsuranceCustomerDto;
import com.gb.customer.entity.Customer;
import com.gb.mq.crm.BindUserEvent;
import com.gb.mq.crm.InsuredCustomerEvent;
import com.gb.mq.crm.RegistUserEvent;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;


/**
 * <p>
 * 客户表 服务类
 * </p>
 *
 * @author lijh
 * @since 2021-06-08
 */
public interface CustomerService extends IService<Customer> {


    /**
     * 集合条件查询
     *
     * @param customer:
     * @return java.util.List<com.entity.Customer>
     * @author lijh
     * @since 2021-06-08
     */
    List<Customer> listEnhance(Customer customer);


    /**
     * 分页条件查询
     *
     * @param page:
     * @param customer:
     * @return java.util.List<com.entity.Customer>
     * @author lijh
     * @since 2021-06-08
     */
    IPage pageEnhance(Page page, Customer customer);

    /**
     * 单条条件查询
     *
     * @param customer:
     * @return java.util.List<com.entity.Customer>
     * @author lijh
     * @since 2021-06-08
     */
    Customer getOneEnhance(Customer customer);


    /**
     * 总数
     *
     * @param customer:
     * @return java.util.List<com.entity.Customer>
     * @author lijh
     * @since 2021-06-08
     */
    Long countEnhance(Customer customer);


    /**
     * 新增
     *
     * @param customer:
     * @return java.util.List<com.entity.Customer>
     * @author lijh
     * @since 2021-06-08
     */
    Boolean saveEnhance(Customer customer);

    /**
     * 分销客户新增
     *
     * @param customer
     * @return
     */
    Boolean saveDistributionCustomer(Customer customer);

    /**
     * TODO 分销客户导入
     *
     * @param file
     * @param httpServletRequest
     * @param user
     * @return java.lang.Boolean
     * @author 孙凯伦
     * @methodName importProprietaryCustomer
     * @time 2023/8/4 11:32
     */
    String importDistributionCustomer(MultipartFile file, HttpServletRequest httpServletRequest,Map<String, Object> user) throws IOException;

    /**
     * 自营客户新增
     *
     * @param customer
     * @return
     */
    Boolean saveProprietaryCustomer(Customer customer);

    /**
     * TODO 自营客户导入
     *
     * @param file
     * @param httpServletRequest
     * @param user
     * @return java.lang.Boolean
     * @author 孙凯伦
     * @methodName importProprietaryCustomer
     * @time 2023/8/4 11:32
     */
    String importProprietaryCustomer(MultipartFile file, HttpServletRequest httpServletRequest,Map<String, Object> user) throws IOException;

    /**
     * 修改
     *
     * @param customer:
     * @return java.util.List<com.entity.Customer>
     * @author lijh
     * @since 2021-06-08
     */
    Boolean updateEnhance(Customer customer);


    /**
     * 删除
     *
     * @param customer:
     * @return java.util.List<com.entity.Customer>
     * @author lijh
     * @since 2021-06-08
     */
    Boolean removeEnhance(Customer customer);

    /**
     * 直接投保客户
     *
     * @param insuredCustomerEvent
     * @throws Exception
     */
    void saveCustomerInsuredMq(InsuredCustomerEvent insuredCustomerEvent);

    /**
     * 工保金投保 推送数据，
     *
     * @param insuredCustomerEvent
     * @throws Exception
     */
    void saveGbjCustomerInsuredMq(InsuredCustomerEvent insuredCustomerEvent);

    /**
     * 保存投保客户
     *
     * @param insuredCustomerEvent
     * @throws Exception
     */
    void saveInsuredCustomerEvent(InsuredCustomerEvent insuredCustomerEvent);


    /**
     * 查询客户绑定的经纪人
     *
     * @param userId:
     * @param mobile:
     * @return java.util.List<com.entity.Customer>
     * @author lijh
     * @since 2022-01-25
     */
    Customer selectAgentUser(String userId, String mobile);

    /**
     * 获取客户详情
     *
     * @param customerId 客户id
     * @param accountId  操作人id
     * @return
     */
    Map selectOneCustomer(String customerId, String accountId);

    /**
     * 获取客户详情头部信息
     *
     * @param customerId  客户id
     * @param agentUserId 经纪人id
     * @param accountId   操作人id
     * @return
     */
    Map selectOneCustomerInfoHead(String customerId, String agentUserId, String accountId);

    /**
     * 获取客户分配的经纪人
     *
     * @param customer 客户对象
     * @return
     */
    Customer selectBoundAgentUser(Customer customer);

    /**
     * 查询经纪人 工作台信息
     *
     * @param agentUserId 经纪人id
     * @return
     */
    Map agentWorkbench(String agentUserId);

    /**
     * 拉取线下保单数据
     *
     * @param agentUserId 经纪人id
     * @param startTime   开始时间
     * @param endTime     结束时间
     * @param current     1
     * @param size        5000
     */
    void pullOfflineCastInsurance(String agentUserId, String startTime, String endTime, Integer current, Integer size);

    /**
     * 批量更改用户标签
     *
     * @param customerLabelDtoList
     */
    void updateCustomerLabel(List<CustomerLabelDto> customerLabelDtoList, boolean batch, boolean clean);

    /**
     * 注册客户
     *
     * @param registUserEvent
     */
    void registerCustomer(RegistUserEvent registUserEvent);

    /**
     * 客户操作(解绑、转交、修改手机号、注销)
     *
     * @param bindUserEvent
     */
    void untieCustomerInfo(BindUserEvent bindUserEvent);

    /**
     * 修改客户更新时间
     *
     * @param customerId
     */
    void updateModifyDateTime(String customerId);

    /**
     * 批量修改客户更新时间
     *
     * @param customerIds
     */
    void updateModifyDateTime(List<String> customerIds);

    /**
     * 修改客户分配时间
     *
     * @param customerId
     */
    void updateAllocateDateTime(String customerId);

    /**
     * 批量修改客户分配时间
     *
     * @param customerIds
     */
    void updateAllocateDateTime(List<String> customerIds);

    /**
     * 根据线索生成客户
     *
     * @param potentialCustomerId
     */
    void generateCustomer(String potentialCustomerId);


    /************************               清洗客户相关数据           *******************************************/

    /**
     * 清洗客户线索关联
     */
    void cleaningCustomerAssociated();

    /**
     * 清洗客户
     *
     * @param sourceId 客户来源id
     * @return
     */
    Object cleaningCustomer(String sourceId);

    /**
     * 清洗 填充客户经纪人表
     *
     * @param agentUserType 经纪人类型
     */
    void cleaningCustomerAgent(Integer agentUserType);

    /**
     * 清洗客户注册状态
     */
    void updateCustomerState();

    /**
     * 清洗客户注册状态-手机号已注册
     */
    void updateCustomerStateByMobile();

    /**
     * 清洗客户备注信息-以分配或关联过
     */
    void updateCustomerRemark();

    /**
     * 清洗线下单数据转化为客户
     *
     * @param agentUserId 经纪人id
     * @param startTime   开始
     * @param endTime     结束时间
     * @param current     页
     * @param size        每页数量
     */
    void cleaningOfflineCastInsurance(String agentUserId, String startTime, String endTime, Integer current, Integer size);

    /**
     * 清洗线下单客户备注-更改地区
     */
    void cleaningOfflineCastInsuranceCustomer();


}
