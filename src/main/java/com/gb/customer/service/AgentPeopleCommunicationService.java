package com.gb.customer.service;

import com.gb.customer.entity.AgentPeopleCommunication;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.List;


/**
 * <p>
 * 经纪人沟通 服务类
 * </p>
 *
 * @author lijh
 * @since 2021-06-11
 */
public interface AgentPeopleCommunicationService extends IService<AgentPeopleCommunication> {


    /**
     * 集合条件查询
     * @author lijh
     * @since 2021-06-11
     * @param agentPeopleCommunication:
     * @return      java.util.List<com.entity.AgentPeopleCommunication>
     */
    List<AgentPeopleCommunication> listEnhance(AgentPeopleCommunication agentPeopleCommunication);


    /**
     * 分页条件查询
     * @author lijh
     * @since 2021-06-11
     * @param page:
     * @param agentPeopleCommunication:
     * @return      java.util.List<com.entity.AgentPeopleCommunication>
     */
    IPage pageEnhance(Page page, AgentPeopleCommunication agentPeopleCommunication);


    /**
     * 单条条件查询
     * @author lijh
     * @since 2021-06-11
     * @param agentPeopleCommunication:
     * @return      java.util.List<com.entity.AgentPeopleCommunication>
     */
    AgentPeopleCommunication getOneEnhance(AgentPeopleCommunication agentPeopleCommunication);


    /**
     * 总数
     * @author lijh
     * @since 2021-06-11
     * @param agentPeopleCommunication:
     * @return      java.util.List<com.entity.AgentPeopleCommunication>
     */
    Long countEnhance(AgentPeopleCommunication agentPeopleCommunication);


    /**
     * 新增
     * @author lijh
     * @since 2021-06-11
     * @param agentPeopleCommunication:
     * @return      java.util.List<com.entity.AgentPeopleCommunication>
     */
    Boolean saveEnhance(AgentPeopleCommunication agentPeopleCommunication);


    /**
     * 修改
     * @author lijh
     * @since 2021-06-11
     * @param agentPeopleCommunication:
     * @return      java.util.List<com.entity.AgentPeopleCommunication>
     */
    Boolean updateEnhance(AgentPeopleCommunication agentPeopleCommunication);


    /**
     * 删除
     * @author lijh
     * @since 2021-06-11
     * @param agentPeopleCommunication:
     * @return      java.util.List<com.entity.AgentPeopleCommunication>
     */
    Boolean removeEnhance(AgentPeopleCommunication agentPeopleCommunication);
}
