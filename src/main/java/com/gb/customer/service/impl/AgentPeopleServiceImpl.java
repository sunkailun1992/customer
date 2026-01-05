package com.gb.customer.service.impl;

import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gb.customer.entity.AgentPeople;
import com.gb.customer.entity.PotentialCustomer;
import com.gb.customer.enums.PotentialCustomerTypeEnum;
import com.gb.customer.mapper.AgentPeopleMapper;
import com.gb.customer.service.AgentPeopleService;
import com.gb.customer.service.PotentialCustomerService;
import com.gb.rpc.entity.UserExtendsVO;
import com.gb.rpc.entity.enums.RpcTypeEnum;
import com.gb.rpc.impl.RpcComponent;
import com.gb.utils.RedisUtils;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * 经纪人 服务实现类
 * </p>
 *
 * @author lijh
 * @since 2021-06-11
 */
@Slf4j
@Service
@Setter(onMethod_ = {@Autowired})
public class AgentPeopleServiceImpl extends ServiceImpl<AgentPeopleMapper, AgentPeople> implements AgentPeopleService {


    /**
     * 经纪人
     */
    private AgentPeopleMapper agentPeopleMapper;

    private PotentialCustomerService potentialCustomerService;
    /**
     * redis操作
     */
    private StringRedisTemplate stringRedisTemplate;
    private RpcComponent rpcComponent;


    /**
     * 集合条件查询
     *
     * @param agentPeople:
     * @return java.util.List<com.entity.AgentPeople>
     * @author lijh
     * @since 2021-06-11
     */
    @Override
    public List<AgentPeople> listEnhance(AgentPeople agentPeople) {
        QueryWrapper<AgentPeople> queryWrapper = new QueryWrapper<>(agentPeople);
        query(agentPeople, queryWrapper);
        return assignment(agentPeopleMapper.selectList(queryWrapper));
    }


    /**
     * 分页条件查询
     *
     * @param agentPeople:
     * @param page:
     * @return java.util.List<com.entity.AgentPeople>
     * @author lijh
     * @since 2021-06-11
     */
    @Override
    public IPage pageEnhance(Page page, AgentPeople agentPeople) {
        QueryWrapper<AgentPeople> queryWrapper = new QueryWrapper<>(agentPeople);
        query(agentPeople, queryWrapper);
        return assignment(agentPeopleMapper.selectPage(page, queryWrapper));
    }


    /**
     * 单条条件查询
     *
     * @param agentPeople:
     * @return java.util.List<com.entity.AgentPeople>
     * @author lijh
     * @since 2021-06-11
     */
    @Override
    public AgentPeople getOneEnhance(AgentPeople agentPeople) {
        QueryWrapper<AgentPeople> queryWrapper = new QueryWrapper<>(agentPeople);
        query(agentPeople, queryWrapper);
        AgentPeople agentPeople1 = agentPeopleMapper.selectOne(queryWrapper);
        if (Objects.nonNull(agentPeople1)) {
            return agentPeople1;
        }
        PotentialCustomer one = potentialCustomerService.getOne(new QueryWrapper<PotentialCustomer>().lambda().
                eq(PotentialCustomer::getMobile, agentPeople.getMobile()).eq(PotentialCustomer::getType, PotentialCustomerTypeEnum.CUSTOMER_TYPE_1.getCode()).orderByAsc(PotentialCustomer::getCreateDateTime).last("limit 1"));
        if (Objects.nonNull(one) && StringUtils.isNotBlank(one.getAgentUserId())) {
            agentPeople.setAgentUserId(one.getAgentUserId());
            agentPeople.setAgentUserName(one.getAgentUserName());
            agentPeople.setAgentGroupId(one.getAgentGroupId());
            return agentPeople;
        }
        return null;
    }


    /**
     * 总数
     *
     * @param agentPeople:
     * @return java.util.List<com.entity.AgentPeople>
     * @author lijh
     * @since 2021-06-11
     */
    @Override
    public Long countEnhance(AgentPeople agentPeople) {
        QueryWrapper<AgentPeople> queryWrapper = new QueryWrapper<>(agentPeople);
        query(agentPeople, queryWrapper);
        return agentPeopleMapper.selectCount(queryWrapper);
    }


    /**
     * 新增
     *
     * @param agentPeople:
     * @return java.util.List<com.entity.AgentPeople>
     * @author lijh
     * @since 2021-06-11
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public Boolean saveEnhance(AgentPeople agentPeople) {
        Integer i = agentPeopleMapper.insert(agentPeople);
        return i > 0 ? true : false;
    }


    /**
     * 修改
     *
     * @param agentPeople:
     * @return java.util.List<com.entity.AgentPeople>
     * @author lijh
     * @since 2021-06-11
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public Boolean updateEnhance(AgentPeople agentPeople) {
        UpdateWrapper<AgentPeople> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", agentPeople.getId());
        Integer i = agentPeopleMapper.update(agentPeople, updateWrapper);
        return i > 0 ? true : false;
    }


    /**
     * 删除
     *
     * @param agentPeople:
     * @return java.util.List<com.entity.AgentPeople>
     * @author lijh
     * @since 2021-06-11
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public Boolean removeEnhance(AgentPeople agentPeople) {
        QueryWrapper<AgentPeople> queryWrapper = new QueryWrapper<>(agentPeople);
        Integer i = agentPeopleMapper.delete(queryWrapper);
        return i > 0 ? true : false;
    }

    @Override
    public Boolean updateSteward(AgentPeople agentPeople, HttpServletRequest httpServletRequest) {
        //缓存取出用户
        Map<String, Object> u = RedisUtils.getToken(stringRedisTemplate, httpServletRequest);
        log.debug("[经纪人分配管家],操作人={},分配信息={}", u, agentPeople);
        if (u != null) {
            agentPeople.setModifyName(u.get("name") + "-" + u.get("id"));
        }
        return this.updateById(agentPeople);
    }

    @Override
    public AgentPeople getAgentPeople(PotentialCustomer potentialCustomer) {
        AgentPeople agentPeople = new AgentPeople();
        //根据邀请人id获取用户信息，拿到手机号和姓名
        Map<String, Object> map = new HashMap<>(1);
        map.put("userId", potentialCustomer.getAgentUserId());
        UserExtendsVO userExtendsVO = rpcComponent.rpcQueryInfo(map, RpcTypeEnum.GET_USER_EXTENDS_ONE, UserExtendsVO.class);
        log.info("用户受邀请时生成经纪人信息，根据经纪人id获取手机号={}", userExtendsVO.getMobile());
        agentPeople.setMobile(Convert.toStr(userExtendsVO.getMobile()));
        //根据经纪人手机号获取之前是否咨询过
        PotentialCustomer agentPeoplePotentialCustomer = potentialCustomerService.getOne(new QueryWrapper<PotentialCustomer>().lambda().eq(PotentialCustomer::getMobile, agentPeople.getMobile()));
        if (Objects.isNull(agentPeoplePotentialCustomer)) {
            //未咨询过，生成经纪人线索
            agentPeoplePotentialCustomer = new PotentialCustomer();
            agentPeoplePotentialCustomer.setMobile(agentPeople.getMobile()).
                    setUserName(agentPeople.getName()).
                    setUserId(potentialCustomer.getAgentUserId()).
                    setType(PotentialCustomerTypeEnum.CUSTOMER_TYPE_1.getCode()).
                    setDescription("此经纪人线索是邀请用户时生成,邀请的用户手机号为" + potentialCustomer.getMobile());
            potentialCustomerService.save(agentPeoplePotentialCustomer);
        }
        //生成经纪人信息。如果此经纪人之前已经存在线索。会直接绑定管家
        agentPeople.setPotentialCustomerId(agentPeoplePotentialCustomer.getId()).
                setUserId(potentialCustomer.getAgentUserId()).
                setName(potentialCustomer.getAgentUserName()).
                setDescription("此经纪人是邀请用户时生成,邀请的用户手机号为" + potentialCustomer.getMobile()).
                setAgentUserId(agentPeoplePotentialCustomer.getAgentUserId()).
                setAgentUserName(agentPeoplePotentialCustomer.getAgentUserName()).
                setAgentGroupId(agentPeoplePotentialCustomer.getAgentGroupId());
        agentPeopleMapper.insert(agentPeople);
        return agentPeople;

    }


    /**
     * 增强查询条件
     *
     * @param agentPeople:
     * @param queryWrapper:
     * @return void
     * @author lijh
     * @since 2021-06-11
     */
    private void query(AgentPeople agentPeople, QueryWrapper<AgentPeople> queryWrapper) {
        /**
         * 排序
         */
        if (agentPeople.getCollation() != null && StringUtils.isNotBlank(agentPeople.getCollationFields())) {
            if (agentPeople.getCollation()) {
                queryWrapper.orderByAsc(agentPeople.getCollationFields());
            } else {
                queryWrapper.orderByDesc(agentPeople.getCollationFields());
            }
        } else {
            queryWrapper.orderByDesc(agentPeople.getCollationFields());
        }

        /**
         * 显示字段
         */
        if (StringUtils.isNotBlank(agentPeople.getFields())) {
            queryWrapper.select(agentPeople.getFields());
        }

        /**
         * 提交时间 区间查询
         */
        String startTime = agentPeople.getStartTime();
        String endTime = agentPeople.getEndTime();
        if (StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)) {
            queryWrapper.between("create_date_time", startTime, endTime);
        }

        /**
         * 经纪人id
         */
        if (StringUtils.isNotBlank(agentPeople.getAgentUserId())) {
            queryWrapper.eq("agent_user_id", agentPeople.getAgentUserId());
        }
        /**
         * 用户id
         */
        if (StringUtils.isNotBlank(agentPeople.getUserId())) {
            queryWrapper.eq("user_id", agentPeople.getUserId());
        }
    }


    /**
     * 单条，增强返回参数追加
     *
     * @param agentPeople:
     * @return AgentPeople
     * @author lijh
     * @since 2021-06-11
     */
    private AgentPeople assignment(AgentPeople agentPeople) {
        return agentPeople;
    }

    /**
     * 分页,增强返回参数追加
     *
     * @param agentPeopleList:
     * @return AgentPeople
     * @author lijh
     * @since 2021-06-11
     */
    private IPage assignment(IPage<AgentPeople> agentPeopleList) {
        agentPeopleList.getRecords().forEach(agentPeople -> {
        });
        return agentPeopleList;
    }


    /**
     * 集合,增强返回参数追加
     *
     * @param agentPeopleList:
     * @return AgentPeople
     * @author lijh
     * @since 2021-06-11
     */
    private List<AgentPeople> assignment(List<AgentPeople> agentPeopleList) {
        agentPeopleList.forEach(agentPeople -> {
        });
        return agentPeopleList;
    }
}
