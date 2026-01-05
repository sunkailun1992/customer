package com.gb.customer.template.impl;

import cn.hutool.core.convert.Convert;
import com.alibaba.schedulerx.shade.org.apache.commons.collections.CollectionUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gb.customer.entity.*;
import com.gb.customer.entity.enums.CustomerAgentStateEnum;
import com.gb.customer.entity.enums.CustomerAgentTypeEnum;
import com.gb.customer.enums.*;
import com.gb.customer.factory.PotentialCustomerFactory;
import com.gb.customer.mapper.CustomerAssociatedMapper;
import com.gb.customer.mapper.PotentialCustomerSourceMapper;
import com.gb.customer.service.CustomerAgentService;
import com.gb.customer.service.CustomerService;
import com.gb.customer.service.CustomerSourceService;
import com.gb.customer.service.PotentialCustomerService;
import com.gb.customer.template.AbstractCluesTemplate;
import com.gb.customer.template.CluesPriorityEnum;
import com.gb.rpc.component.UserComponent;
import com.gb.rpc.entity.UserExtendsVO;
import com.gb.rpc.entity.UserGroupInfo;
import com.gb.rpc.entity.enums.RpcTypeEnum;
import com.gb.rpc.impl.RpcComponent;
import com.gb.utils.enumeration.UserTypeEnum;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * @author ljh
 * @date 2022/3/10 10:41 上午
 */
@Slf4j
@Service
@Setter(onMethod_ = {@Autowired})
public class QiMoYunSource extends AbstractCluesTemplate<List<PotentialCustomer>> {

    private PotentialCustomerService potentialCustomerService;
    private PotentialCustomerSourceMapper potentialCustomerSourceMapper;
    private RpcComponent rpcComponent;

    private CustomerService customerService;

    private CustomerSourceService customerSourceService;

    private UserComponent userComponent;

    private CustomerAssociatedMapper customerAssociatedMapper;

    private CustomerAgentService customerAgentService;

    /**
     * 校验筛选
     */
    @Override
    public Boolean checkScreening(List<PotentialCustomer> potentialCustomerList) {

        return CollectionUtils.isEmpty(potentialCustomerList);
    }

    @Override
    public Boolean isBeInvited(List<PotentialCustomer> potentialCustomerList) {
        return false;
    }


    /**
     * 分配管家
     */
    @Override
    public void allotSteward(List<PotentialCustomer> potentialCustomerList) {

        log.info("七陌云来源数据分配成功条数={}", potentialCustomerList.size());
    }

    /**
     * 最终新增
     *
     * @return
     */
    @Override
    public Boolean saveCluesData(List<PotentialCustomer> potentialCustomerList, HttpServletRequest httpServletRequest) {
        //获取线索来源
        List<PotentialCustomerSource> sourceList = potentialCustomerSourceMapper.selectList(new QueryWrapper<PotentialCustomerSource>().lambda().eq(PotentialCustomerSource::getIsDelete, false));
        //获取客户来源
        CustomerSource customerSource = customerSourceService.getOne(new QueryWrapper<CustomerSource>().lambda().eq(CustomerSource::getName, SourceTypeEnum.七陌云.getDesc()));
        for (PotentialCustomer pc : potentialCustomerList) {
            pc.setPotentialCustomerSourceId(this.getCluesSourceId(pc, sourceList));
            pc.setState(1);
            pc.setDataType(CustomerDataTypeEnum.DATA_TYPE_0.getCode());
            pc.setState(PotentialCustomerStateEnum.处理中.getCode());
            pc.setDescription("七陌云来源新增线索");
            PotentialCustomerFactory.getBeanPotentialCustomer(pc);
            //生成客户相关
            saveCustomer(customerSource, pc);
        }
        log.info("七陌云来源保存线索,条数={},list={}", potentialCustomerList.size(), potentialCustomerList);
        return true;
    }

    /**
     * 生成客户相关信息
     *
     * @param customerSource
     * @param pc
     */
    private void saveCustomer(CustomerSource customerSource, PotentialCustomer pc) {
        Customer customer = customerService.getOne(new QueryWrapper<Customer>().lambda().eq(Customer::getMobile, pc.getMobile()));
        //默认客户线索关联为副关联
        CustomerAssociated customerAssociated = new CustomerAssociated();
        customerAssociated.setType(0);
        if (Objects.isNull(customer)) {
            UserExtendsVO userExtendsVO = userComponent.getUserExtendsVOByMobile(pc.getMobile());
            customer = new Customer();
            customer.setMobile(pc.getMobile());
            customer.setState(StringUtils.isNotEmpty(userExtendsVO.getUserId()) ? CustomerStateEnum.已注册.getValue() : CustomerStateEnum.未注册.getValue());
            customer.setUserId(Convert.toStr(userExtendsVO.getUserId()));
            customer.setName(pc.getName());
            if (Objects.nonNull(customerSource)) {
                customer.setCustomerSourceId(customerSource.getId());
            }
            customer.setAllocateDateTime(LocalDateTime.now());
            customerService.save(customer);
            customerAssociated.setType(1);
        }

        CustomerAgent customerAgentOne = customerAgentService.getOne(new QueryWrapper<CustomerAgent>().lambda().eq(CustomerAgent::getCustomerId, customer.getId()).
                eq(CustomerAgent::getType, CustomerAgentTypeEnum.自营).eq(CustomerAgent::getState, CustomerAgentStateEnum.主关联));
        if (Objects.nonNull(customerAgentOne)) {
            pc.setAgentUserId(customerAgentOne.getAgentUserId());
            pc.setAgentUserName(userComponent.getOneUserByUserId(customerAgentOne.getAgentUserId()).getName());
        }
        //生成线索
        potentialCustomerService.save(pc);
        //绑定客户和线索
        customerAssociated.setCustomerId(customer.getId());
        customerAssociated.setPotentialCustomerId(pc.getId());
        customerAssociatedMapper.insert(customerAssociated);
    }


    @Override
    public String priority() {
        return CluesPriorityEnum.QI_MO_YUN.getValue();
    }

    /**
     * 获取分配的管家
     *
     * @param rulesMap rpc请求参数
     * @param pc       赋值对象
     */
    public void gainAssignedSteward(HashMap<String, Object> rulesMap, PotentialCustomer pc) {
        rulesMap.put("groupId", UserTypeEnum.用户咨询.getTypeCode());
        rulesMap.put("queryType", CustomerDataTypeEnum.DATA_TYPE_0.getCode());
        UserGroupInfo realUser = rpcComponent.rpcQueryInfo(rulesMap, RpcTypeEnum.GET_USER_GROUP_CONDITIONS, UserGroupInfo.class);
        log.debug("定时拉取三方数据保存用户mobile={},agentUser={}", pc.getMobile(), realUser);
        if (Objects.nonNull(realUser) && Objects.nonNull(realUser.getUserName())) {
            //状态（-1：取消，0：待处理，1：处理中，2：已完成，3：关闭）
            pc.setAgentUserId(realUser.getUserName());
            pc.setAgentUserName(realUser.getRealName());
            pc.setAgentGroupId(realUser.getAgentGroupId());
            pc.setAgentUserType(AgentUserTypeEnum.AGENT_PEOPLE_TYPE_0.getCode());
        }
    }
}
