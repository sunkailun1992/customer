package com.gb.customer.service.impl;

import cn.hutool.core.convert.Convert;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gb.customer.dto.OrderInfoDto;
import com.gb.customer.entity.AgentPeople;
import com.gb.customer.entity.Customer;
import com.gb.customer.entity.CustomerCastInsurance;
import com.gb.customer.entity.enums.CustomerAgentTypeEnum;
import com.gb.customer.enums.AgentUserTypeEnum;
import com.gb.customer.enums.PotentialCustomerTypeEnum;
import com.gb.customer.mapper.AgentPeopleMapper;
import com.gb.customer.mapper.CustomerCastInsuranceMapper;
import com.gb.customer.service.AgentPeopleService;
import com.gb.customer.service.CustomerCastInsuranceService;
import com.gb.customer.service.CustomerService;
import com.gb.mq.crm.InsuredCustomerEvent;
import com.gb.rpc.InsuranceRpc;
import com.gb.rpc.ProductRpc;
import com.gb.rpc.UserRpc;
import com.gb.rpc.component.UserComponent;
import com.gb.rpc.entity.CastInsurance;
import com.gb.rpc.entity.CastInsuranceDistributionVO;
import com.gb.rpc.entity.UserExtendsVO;
import com.gb.rpc.entity.enums.InsuranceSourceEnum;
import com.gb.rpc.entity.enums.RpcTypeEnum;
import com.gb.rpc.impl.RpcComponent;
import com.gb.utils.Json;
import com.gb.utils.exception.BusinessException;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 客户投保订单关联 服务实现类
 * </p>
 *
 * @author lijh
 * @since 2021-06-08
 */
@Slf4j
@Service
@Setter(onMethod_ = {@Autowired})
public class CustomerCastInsuranceServiceImpl extends ServiceImpl<CustomerCastInsuranceMapper, CustomerCastInsurance> implements CustomerCastInsuranceService {


    /**
     * 客户投保订单关联
     */
    private CustomerCastInsuranceMapper customerCastInsuranceMapper;

    private AgentPeopleMapper agentPeopleMapper;

    private CustomerService customerService;

    private ProductRpc productRpc;

    private InsuranceRpc insuranceRpc;
    /**
     * 用户中心
     */
    private UserRpc userRpc;

    private AgentPeopleService agentPeopleService;

    private RpcComponent rpcComponent;

    private UserComponent userComponent;


    /**
     * 集合条件查询
     *
     * @param customerCastInsurance:
     * @return java.util.List<com.entity.CustomerCastInsurance>
     * @author lijh
     * @since 2021-06-08
     */
    @Override
    public List<CustomerCastInsurance> listEnhance(CustomerCastInsurance customerCastInsurance) {
        QueryWrapper<CustomerCastInsurance> queryWrapper = new QueryWrapper<>(customerCastInsurance);
        query(customerCastInsurance, queryWrapper);
        return assignment(customerCastInsuranceMapper.selectList(queryWrapper));
    }


    /**
     * 分页条件查询
     *
     * @param customerCastInsurance:
     * @param page:
     * @return java.util.List<com.entity.CustomerCastInsurance>
     * @author lijh
     * @since 2021-06-08
     */
    @Override
    public IPage pageEnhance(Page page, CustomerCastInsurance customerCastInsurance) {
        //根据条件查所有用户
        List<Customer> customers = customerService.listEnhance(new Customer() {{
            setName(customerCastInsurance.getNameQuery());
            setMobile(customerCastInsurance.getMobileQuery());
            setUserIdQuery(customerCastInsurance.getBusinessStewardQuery());
        }});
        //根据条件查询所有经纪人
        List<AgentPeople> agentPeople = agentPeopleService.listEnhance(new AgentPeople() {{
            setName(customerCastInsurance.getNameQuery());
            setMobile(customerCastInsurance.getMobileQuery());
            setAgentUserId(customerCastInsurance.getBusinessStewardQuery());
        }});
        //上述条件未查到相关客户信息，直接返回空数据
        if (CollectionUtils.isEmpty(customers) && CollectionUtils.isEmpty(agentPeople)) {
            return new Page(page.getCurrent(), page.getSize());
        }
        //所查的所用客户和经纪人id
        List<String> queryIds = new ArrayList<>();
        List<String> customerIds = customers.stream().map(Customer::getId).collect(Collectors.toList());
        List<String> agentPeopleIds = agentPeople.stream().map(AgentPeople::getId).collect(Collectors.toList());
        queryIds.addAll(customerIds);
        queryIds.addAll(agentPeopleIds);
        //获取符合条件订单
        customerCastInsurance.setCustomerIdList(queryIds);
        customerCastInsurance.setCastInsuranceId(customerCastInsurance.getCastInsuranceId());
        customerCastInsurance.setAgentUserId(customerCastInsurance.getServiceStewardQuery());
        QueryWrapper<CustomerCastInsurance> queryWrapper = new QueryWrapper<>(customerCastInsurance);
        query(customerCastInsurance, queryWrapper);
        //个人账号下的客户订单
        Page<CustomerCastInsurance> page1 = customerCastInsuranceMapper.selectPage(page, queryWrapper);
        log.debug("符合条件的订单数据=" + page1.getRecords());
        //包装返回订单数据
        List<OrderInfoDto> orderDtoList = backOrderInfo(page1);
        //获取产品信息
        for (OrderInfoDto orderinfoDto : orderDtoList) {
            orderinfoDto.setSpuName(setObjectStatement(orderinfoDto.getSpuId()));
        }
        Page pageResult = new Page(page1.getCurrent(), page1.getSize());
        pageResult.setRecords(orderDtoList);
        pageResult.setTotal(page1.getTotal());
        pageResult.setPages(page1.getPages());
        return pageResult;
    }

    /**
     * 根据条件获取所有订单信息。包装订单数据返回
     *
     * @param pageQuery
     * @return
     */
    private List<OrderInfoDto> backOrderInfo(Page<CustomerCastInsurance> pageQuery) {
        List<OrderInfoDto> orderInfoDtoList = new ArrayList<>();
        HashMap<String, Object> hashMapRpc = new HashMap<>(1);
        if (CollectionUtils.isNotEmpty(pageQuery.getRecords())) {
            //遍历符合条件订单调用RPC 获取订单详细数据
            for (CustomerCastInsurance records : pageQuery.getRecords()) {
                hashMapRpc.put("id", records.getCastInsuranceId());
                OrderInfoDto orderInfoDto = new OrderInfoDto();
                //RPC调订单服务
                CastInsurance castInsurance = rpcComponent.rpcQueryInfo(hashMapRpc, RpcTypeEnum.GET_CAST_INSURANCE_ID, CastInsurance.class);
                //放入订单基本信息
                orderInfoDto.setDangerPlantedName(Objects.nonNull((castInsurance.getProductDangerPlantedName())) ? (String) castInsurance.getProductDangerPlantedName() : null);
                orderInfoDto.setPayMoney(Objects.nonNull((castInsurance.getPayMoney())) ? castInsurance.getPayMoney() : new BigDecimal("0.00"));
                orderInfoDto.setStatus(Objects.nonNull((castInsurance.getInsuranceStatus())) ? castInsurance.getInsuranceStatus() : 0);
                orderInfoDto.setSpuId(Objects.nonNull((castInsurance.getSpuId())) ? castInsurance.getSpuId() : null);
                orderInfoDto.setServiceSteward(records.getAgentUserName());
                orderInfoDto.setServiceStewardId(records.getAgentUserId());
                orderInfoDto.setCastInsuranceId(records.getCastInsuranceId());
                orderInfoDto.setCreateDateTime(records.getCreateDateTime());
                orderInfoDto.setId(records.getId());
                //放入业务管家
                extractedBusinessSteward(records, orderInfoDto);

                orderInfoDtoList.add(orderInfoDto);
            }
        }
        return orderInfoDtoList;
    }

    /**
     * 提取业务管家
     *
     * @param records
     * @param orderInfoDto 返回订单信息
     */
    private void extractedBusinessSteward(CustomerCastInsurance records, OrderInfoDto orderInfoDto) {
        switch (PotentialCustomerTypeEnum.getCustomerTypeEnum(records.getType())) {
            //用户投保单
            case CUSTOMER_TYPE_0:
                Customer customer = customerService.getById(records.getCustomerId());
                if (null != customer) {
                    orderInfoDto.setName(customer.getName());
                    orderInfoDto.setMobile(customer.getMobile());
                    orderInfoDto.setBusinessSteward(customer.getAgentUserName());
                    orderInfoDto.setBusinessStewardId(customer.getAgentUserId());
                    //判断用户绑定的经纪人id是否为管家。  不是管家就查经纪人信息获取业务管家
                    if (!AgentUserTypeEnum.AGENT_PEOPLE_TYPE_0.getCode().equals(customer.getAgentUserType())) {
                        AgentPeople agentPeople = agentPeopleMapper.selectOne(new QueryWrapper<AgentPeople>().lambda().eq(AgentPeople::getUserId, customer.getAgentUserId()));
                        orderInfoDto.setBusinessStewardId(agentPeople.getAgentUserId());
                        orderInfoDto.setBusinessSteward(agentPeople.getAgentUserName());
                    }
                }
                break;
            //为经纪人投保单
            case CUSTOMER_TYPE_1:
                AgentPeople agentPeople = agentPeopleService.getById(records.getCustomerId());
                if (null != agentPeople) {
                    orderInfoDto.setName(agentPeople.getName());
                    orderInfoDto.setMobile(agentPeople.getMobile());
                    orderInfoDto.setBusinessSteward(agentPeople.getAgentUserName());
                    orderInfoDto.setBusinessStewardId(agentPeople.getAgentUserId());
                }
                break;
            default:
                break;
        }
    }

    /**
     * 单条条件查询
     *
     * @param customerCastInsurance:
     * @return java.util.List<com.entity.CustomerCastInsurance>
     * @author lijh
     * @since 2021-06-08
     */
    @Override
    public CustomerCastInsurance getOneEnhance(CustomerCastInsurance customerCastInsurance) {
        QueryWrapper<CustomerCastInsurance> queryWrapper = new QueryWrapper<>(customerCastInsurance);
        query(customerCastInsurance, queryWrapper);
        return assignment(customerCastInsuranceMapper.selectOne(queryWrapper));
    }

    /**
     * 总数
     *
     * @param customerCastInsurance:
     * @return java.util.List<com.entity.CustomerCastInsurance>
     * @author lijh
     * @since 2021-06-08
     */
    @Override
    public Long countEnhance(CustomerCastInsurance customerCastInsurance) {
        QueryWrapper<CustomerCastInsurance> queryWrapper = new QueryWrapper<>(customerCastInsurance);
        query(customerCastInsurance, queryWrapper);
        return customerCastInsuranceMapper.selectCount(queryWrapper);
    }


    /**
     * 新增
     *
     * @param customerCastInsurance:
     * @return java.util.List<com.entity.CustomerCastInsurance>
     * @author lijh
     * @since 2021-06-08
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public Boolean saveEnhance(CustomerCastInsurance customerCastInsurance) {
        Integer i = customerCastInsuranceMapper.insert(customerCastInsurance);
        return i > 0 ? true : false;
    }


    /**
     * 修改
     *
     * @param customerCastInsurance:
     * @return java.util.List<com.entity.CustomerCastInsurance>
     * @author lijh
     * @since 2021-06-08
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public Boolean updateEnhance(CustomerCastInsurance customerCastInsurance) {
        UpdateWrapper<CustomerCastInsurance> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", customerCastInsurance.getId());
        Integer i = customerCastInsuranceMapper.update(customerCastInsurance, updateWrapper);
        return i > 0 ? true : false;
    }


    /**
     * TODO 投保订单id修改
     *
     * @param customerCastInsurance
     * @return java.lang.Boolean
     * @author 孙凯伦
     * @methodName castInsuranceIdUpdate
     * @time 2023/8/9 15:10
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public Boolean castInsuranceIdUpdate(CustomerCastInsurance customerCastInsurance) {
        UpdateWrapper<CustomerCastInsurance> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("cast_insurance_id", customerCastInsurance.getCastInsuranceId());
        //判断用户id是否存在，映射客户id
        if (StringUtils.isNotBlank(customerCastInsurance.getUserId())) {
            //存在多条数据，无法保证重复，取最新客户数据
            List<Customer> list = customerService.listEnhance(new Customer() {{
                setUserId(customerCastInsurance.getUserId());
            }});
            if (list.size() != 0) {
                customerCastInsurance.setCustomerId(list.get(0).getId());
            } else {
                log.error("当前投保订单id：" + customerCastInsurance.getCastInsuranceId() + "，无法找到客户id，对应用户id为：" + customerCastInsurance.getUserId());
            }
        }
        Integer i = customerCastInsuranceMapper.update(customerCastInsurance, updateWrapper);
        return i > 0 ? true : false;
    }


    /**
     * 删除
     *
     * @param customerCastInsurance:
     * @return java.util.List<com.entity.CustomerCastInsurance>
     * @author lijh
     * @since 2021-06-08
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public Boolean removeEnhance(CustomerCastInsurance customerCastInsurance) {
        QueryWrapper<CustomerCastInsurance> queryWrapper = new QueryWrapper<>(customerCastInsurance);
        Integer i = customerCastInsuranceMapper.delete(queryWrapper);
        return i > 0 ? true : false;
    }

    /**
     * 接收投保信息，根据保单id获取分销关系管家信息
     *
     * @param insuredCustomerEvent 赋值对象
     */
    @Override
    public void getInsuredCustomerEvent(InsuredCustomerEvent insuredCustomerEvent) {
        Map<String, Object> map = new HashMap<>(2);
        map.put("castInsuranceId", insuredCustomerEvent.getCastInsuranceId());
        //0：用户端，1：经纪人，2：渠道，3：业务管家，4：特别业务管家
        map.put("userType", insuredCustomerEvent.getUserType());
        CastInsuranceDistributionVO castInsuranceDistributionVO = rpcComponent.rpcQueryInfo(map, RpcTypeEnum.GET_STEWARD_CAST_ID, CastInsuranceDistributionVO.class);
        log.debug("RPC获取保单关联的管家，castInsuranceDistributionVO={}", JSON.toJSONString(castInsuranceDistributionVO));

        //业务管家
        insuredCustomerEvent.setAgentUserId(castInsuranceDistributionVO.getHousekeeperId());
        insuredCustomerEvent.setAgentUserName(castInsuranceDistributionVO.getHousekeeperName());
        UserExtendsVO userExtendsVoInfo = userComponent.getUserExtendsVOByUserId(castInsuranceDistributionVO.getHousekeeperId());
        insuredCustomerEvent.setAgentUserPhone(userExtendsVoInfo.getMobile());

        //业务助理
        insuredCustomerEvent.setServiceStewardId(castInsuranceDistributionVO.getAssistantId());
        insuredCustomerEvent.setServiceStewardName(castInsuranceDistributionVO.getAssistantName());
        insuredCustomerEvent.setServiceStewardPhone(castInsuranceDistributionVO.getAssistantMobile());

        //渠道经纪人
        insuredCustomerEvent.setChannelAgentUserId(castInsuranceDistributionVO.getChannelId());
        insuredCustomerEvent.setChannelAgentUserName(castInsuranceDistributionVO.getChannelName());
        insuredCustomerEvent.setChannelAgentUserPhone(castInsuranceDistributionVO.getChannelMobile());


        /**
         * 投保用户信息
         */
        //获取投保用户的手机号
        UserExtendsVO userExtendsVO = userComponent.getUserExtendsVOByUserId(castInsuranceDistributionVO.getCastUserId());
        insuredCustomerEvent.setMobile(Convert.toStr(userExtendsVO.getMobile()));
        insuredCustomerEvent.setName(Convert.toStr(userExtendsVO.getName()));
        //投保用户id
        insuredCustomerEvent.setCastInsuranceUserId(castInsuranceDistributionVO.getCastUserId());
        insuredCustomerEvent.setType(CustomerAgentTypeEnum.getValue(castInsuranceDistributionVO.getType()));
        //获取投保信息
        HashMap<String, Object> hashMapRpc = new HashMap<>(1);
        hashMapRpc.put("id", insuredCustomerEvent.getCastInsuranceId());
        CastInsurance castInsurance = rpcComponent.rpcQueryInfo(hashMapRpc, RpcTypeEnum.GET_CAST_INSURANCE_ID, CastInsurance.class);
        insuredCustomerEvent.setSource(InsuranceSourceEnum.getByCode(castInsurance.getSource()).getDesc());
    }


    /**
     * 增强查询条件
     *
     * @param customerCastInsurance:
     * @param queryWrapper:
     * @return void
     * @author lijh
     * @since 2021-06-08
     */
    private void query(CustomerCastInsurance customerCastInsurance, QueryWrapper<CustomerCastInsurance> queryWrapper) {
        /**
         * 排序
         */
        if (customerCastInsurance.getCollation() != null && StringUtils.isNotBlank(customerCastInsurance.getCollationFields())) {
            if (customerCastInsurance.getCollation()) {
                queryWrapper.orderByAsc(customerCastInsurance.getCollationFields());
            } else {
                queryWrapper.orderByDesc(customerCastInsurance.getCollationFields());
            }
        } else {
            queryWrapper.orderByDesc(customerCastInsurance.getCollationFields());
        }

        /**
         * 显示字段
         */
        if (StringUtils.isNotBlank(customerCastInsurance.getFields())) {
            queryWrapper.select(customerCastInsurance.getFields());
        }
        /**
         * 客户id
         */
        if (CollectionUtils.isNotEmpty(customerCastInsurance.getCustomerIdList())) {
            queryWrapper.or().in("customer_id", customerCastInsurance.getCustomerIdList());
        }

        /**
         * 提交时间 区间查询
         */
        String startTime = customerCastInsurance.getStartTime();
        String endTime = customerCastInsurance.getEndTime();
        if (StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)) {
            queryWrapper.between("create_date_time", startTime, endTime);
        }

        /**
         * 订单号
         */
        if (StringUtils.isNotBlank(customerCastInsurance.getCastInsuranceId())) {
            queryWrapper.eq("cast_insurance_id", customerCastInsurance.getCastInsuranceId());
        }
        /**
         * 服务管家id
         */
        if (StringUtils.isNotBlank(customerCastInsurance.getAgentUserId())) {
            queryWrapper.or().eq("agent_user_id", customerCastInsurance.getAgentUserId());
        }
    }


    /**
     * 单条，增强返回参数追加
     *
     * @param customerCastInsurance:
     * @return CustomerCastInsurance
     * @author lijh
     * @since 2021-06-08
     */
    private CustomerCastInsurance assignment(CustomerCastInsurance customerCastInsurance) {
        return customerCastInsurance;
    }

    /**
     * 分页,增强返回参数追加
     *
     * @param customerCastInsuranceList:
     * @return CustomerCastInsurance
     * @author lijh
     * @since 2021-06-08
     */
    private IPage assignment(IPage<CustomerCastInsurance> customerCastInsuranceList) {
        customerCastInsuranceList.getRecords().forEach(customerCastInsurance -> {
        });
        return customerCastInsuranceList;
    }


    /**
     * 集合,增强返回参数追加
     *
     * @param customerCastInsuranceList:
     * @return CustomerCastInsurance
     * @author lijh
     * @since 2021-06-08
     */
    private List<CustomerCastInsurance> assignment(List<CustomerCastInsurance> customerCastInsuranceList) {
        customerCastInsuranceList.forEach(customerCastInsurance -> {
        });
        return customerCastInsuranceList;
    }

    private String setObjectStatement(String spuId) {
        //查询并赋值 产品
        String spuName = null;
        if (StringUtils.isNotBlank(spuId)) {
            try {
                HashMap<String, String> hashMap = new HashMap<>(1);
                hashMap.put("id", spuId);
                Json json = productRpc.productSpuSelectOne(hashMap);
                log.info("产品表:" + json.getObj());
                if (Objects.nonNull(json.getObj())) {
                    spuName = JSON.parseObject(JSON.toJSONString(json.getObj()), Map.class).get("name").toString();
                }
            } catch (Exception e) {
                throw new BusinessException("RPC产品表单条查询失败！");
            }
        }
        return spuName;
    }
}
