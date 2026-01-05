package com.gb.customer.service;

import com.gb.customer.entity.AgentPeople;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gb.customer.entity.PotentialCustomer;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * <p>
 * 经纪人 服务类
 * </p>
 *
 * @author lijh
 * @since 2021-06-11
 */
public interface AgentPeopleService extends IService<AgentPeople> {


    /**
     * 集合条件查询
     *
     * @param agentPeople:
     * @return java.util.List<com.entity.AgentPeople>
     * @author lijh
     * @since 2021-06-11
     */
    List<AgentPeople> listEnhance(AgentPeople agentPeople);


    /**
     * 分页条件查询
     *
     * @param page:
     * @param agentPeople:
     * @return java.util.List<com.entity.AgentPeople>
     * @author lijh
     * @since 2021-06-11
     */
    IPage pageEnhance(Page page, AgentPeople agentPeople);


    /**
     * 单条条件查询
     *
     * @param agentPeople:
     * @return java.util.List<com.entity.AgentPeople>
     * @author lijh
     * @since 2021-06-11
     */
    AgentPeople getOneEnhance(AgentPeople agentPeople);


    /**
     * 总数
     *
     * @param agentPeople:
     * @return java.util.List<com.entity.AgentPeople>
     * @author lijh
     * @since 2021-06-11
     */
    Long countEnhance(AgentPeople agentPeople);


    /**
     * 新增
     *
     * @param agentPeople:
     * @return java.util.List<com.entity.AgentPeople>
     * @author lijh
     * @since 2021-06-11
     */
    Boolean saveEnhance(AgentPeople agentPeople);


    /**
     * 修改
     *
     * @param agentPeople:
     * @return java.util.List<com.entity.AgentPeople>
     * @author lijh
     * @since 2021-06-11
     */
    Boolean updateEnhance(AgentPeople agentPeople);


    /**
     * 删除
     *
     * @param agentPeople:
     * @return java.util.List<com.entity.AgentPeople>
     * @author lijh
     * @since 2021-06-11
     */
    Boolean removeEnhance(AgentPeople agentPeople);

    /**
     * 分配管家
     *
     * @param agentPeople
     * @param httpServletRequest
     * @return
     */
    Boolean updateSteward(AgentPeople agentPeople, HttpServletRequest httpServletRequest);


    /**
     * 获取经纪人信息,经纪人不存在重新生成
     *
     * @param potentialCustomer
     * @return
     */
    AgentPeople getAgentPeople(PotentialCustomer potentialCustomer);

}
