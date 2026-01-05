package com.gb.customer.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gb.customer.entity.AgentPeople;
import com.gb.customer.entity.PotentialCustomer;
import com.gb.rpc.entity.UserGroupInfo;
import com.gb.utils.Json;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * <p>
 * 潜在客户表 服务类
 * </p>
 *
 * @author 王一飞
 * @since 2021-04-13
 */
public interface PotentialCustomerService extends IService<PotentialCustomer> {

    /**
     * 集合条件查询
     *
     * @param potentialCustomer
     * @return
     */
    List<PotentialCustomer> listEnhance(PotentialCustomer potentialCustomer);


    /**
     * 分页条件查询
     *
     * @param page
     * @param potentialCustomer
     * @return
     */
    IPage pageEnhance(Page page, PotentialCustomer potentialCustomer);

    /**
     * 单条条件查询
     *
     * @param potentialCustomer
     * @param httpServletRequest
     * @return
     */
    PotentialCustomer getOneEnhance(PotentialCustomer potentialCustomer, HttpServletRequest httpServletRequest);


    /**
     * 总数
     *
     * @param potentialCustomer:
     * @return java.util.List<com.entity.PotentialCustomer>
     * @author 王一飞
     * @since 2021-04-13
     */
    Long countEnhance(PotentialCustomer potentialCustomer);

    /**
     * 新增
     *
     * @param potentialCustomer
     * @param httpServletRequest
     * @return
     */
    Json<String> saveEnhance(PotentialCustomer potentialCustomer, HttpServletRequest httpServletRequest);


    /**
     * 修改
     *
     * @param potentialCustomer:
     * @return java.util.List<com.entity.PotentialCustomer>
     * @author 王一飞
     * @since 2021-04-13
     */
    Boolean updateEnhance(PotentialCustomer potentialCustomer);

    /**
     * 分配管家
     *
     * @param potentialCustomer
     * @param httpServletRequest
     * @return
     */
    Boolean allotSteward(PotentialCustomer potentialCustomer, HttpServletRequest httpServletRequest);


    /**
     * 删除
     *
     * @param potentialCustomer:
     * @return java.util.List<com.entity.PotentialCustomer>
     * @author 王一飞
     * @since 2021-04-13
     */
    Boolean removeEnhance(PotentialCustomer potentialCustomer);

    /**
     * 完成咨询
     *
     * @param potentialCustomer
     * @return
     */
    Boolean completeConsulting(PotentialCustomer potentialCustomer);

    /**
     * 关闭咨询
     *
     * @param potentialCustomer
     * @return
     */
    Boolean closeConsulting(PotentialCustomer potentialCustomer);

    /**
     * 取消咨询
     *
     * @param potentialCustomer
     * @return
     */
    Object cancelConsulting(PotentialCustomer potentialCustomer);

    /**
     * 经纪人查询（过滤当前 投保咨询经纪人）
     *
     * @param potentialCustomer
     * @param httpServletRequest
     * @return
     */
    Object selectAgentAndFilterCurrentAgent(PotentialCustomer potentialCustomer, HttpServletRequest httpServletRequest);

    /**
     * 添加线索
     *
     * @param potentialCustomer
     * @return
     */
    Boolean saveCustomerClues(PotentialCustomer potentialCustomer);

    /**
     * 根据groupId 获取经纪人列表
     *
     * @param groupId
     * @return
     */
    List<UserGroupInfo> getUserGroupList(String groupId);

    /**
     * 下拉管家列表
     *
     * @param type
     * @param userId
     * @return
     */
    List<Object> getStewardList(Integer type, String userId);

    /**
     * 获取管家列表
     *
     * @param phone
     * @param name
     * @return
     */
    List<UserGroupInfo> queryStewardList(String phone, String name);

    /**
     * 认证经纪人
     *
     * @param agentPeopleBO
     * @return
     */
    Boolean certificationAgentPeople(AgentPeople agentPeopleBO);

    /**
     * 获取客户id
     *
     * @param potentialCustomer
     * @return
     */
    List<String> selectCustomerIds(PotentialCustomer potentialCustomer);

    /**
     * 咨询 生成客户备注信息
     *
     * @param potentialCustomer 咨询单
     * @param customerId        客户id
     */
    void consultCreateCustomerRemark(PotentialCustomer potentialCustomer, String customerId);

}
