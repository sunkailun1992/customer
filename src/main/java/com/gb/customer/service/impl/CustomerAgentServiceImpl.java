package com.gb.customer.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gb.bean.GongBaoConfig;
import com.gb.customer.dto.CustomerInfoReqDto;
import com.gb.customer.entity.*;
import com.gb.customer.entity.bo.CustomerAgentBO;
import com.gb.customer.entity.enums.*;
import com.gb.customer.entity.query.CustomerAgentQuery;
import com.gb.customer.entity.vo.CustomerAgentVO;
import com.gb.customer.enums.CluesPortEnum;
import com.gb.customer.mapper.CustomerAgentMapper;
import com.gb.customer.mapper.CustomerCommunicationMapper;
import com.gb.customer.service.CustomerAgentOperateRecordService;
import com.gb.customer.service.CustomerAgentService;
import com.gb.customer.service.CustomerCommunicationService;
import com.gb.customer.service.CustomerService;
import com.gb.customer.service.query.CustomerAgentServiceQuery;
import com.gb.customer.service.results.CustomerAgentServiceResults;
import com.gb.mq.enums.ChangeTypeEnum;
import com.gb.mq.enums.OperationSourceTypeEnum;
import com.gb.mq.production.InsuranceService;
import com.gb.rpc.component.UserComponent;
import com.gb.rpc.entity.TeamUserVO;
import com.gb.rpc.entity.UserExtendsVO;
import com.gb.rpc.entity.UserVO;
import com.gb.rpc.entity.enums.RpcTypeEnum;
import com.gb.rpc.impl.RpcComponent;
import com.gb.utils.GeneralConvertor;
import com.gb.utils.exception.BusinessException;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.compress.utils.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;


/**
 * TODO 客户经纪人，Service服务实现层
 * 代码生成器
 *
 * @author lijh
 * @className CustomerAgentServiceImpl
 * @time 2022-08-31 09:35:12
 */
@Slf4j
@Service
@Setter(onMethod_ = {@Autowired})
public class CustomerAgentServiceImpl extends ServiceImpl<CustomerAgentMapper, CustomerAgent> implements CustomerAgentService {


    /**
     * 客户经纪人
     */
    private CustomerAgentMapper customerAgentMapper;


    /**
     * 客户经纪人
     */
    private CustomerAgentServiceResults customerAgentServiceResults;


    /**
     * 客户经纪人增强条件
     */
    private CustomerAgentServiceQuery customerAgentServiceQuery;

    private RpcComponent rpcComponent;

    private UserComponent userComponent;

    private CustomerAgentOperateRecordService customerAgentOperateRecordService;

    private CustomerService customerService;

    private InsuranceService insuranceService;

    /**
     * 客户沟通表
     */
    private CustomerCommunicationMapper customerCommunicationMapper;


    /**
     * TODO 集合
     *
     * @param customerAgentQuery 客户经纪人
     * @return List<CustomerAgentVO>
     * @author lijh
     * @methodName listEnhance
     * @time 2022-08-31 09:35:12
     */
    @Override
    public List<CustomerAgentVO> listEnhance(CustomerAgentQuery customerAgentQuery) {
        CustomerAgent customerAgent = GeneralConvertor.convertor(customerAgentQuery, CustomerAgent.class);
        QueryWrapper<CustomerAgent> queryWrapper = new QueryWrapper<>(customerAgent);
        // TODO 自动生成查询，禁止手动写语句
        customerAgentServiceQuery.query(customerAgentQuery, queryWrapper);
        // TODO 人工查询条件
        queryArtificial(customerAgentQuery, queryWrapper);
        // DO数据
        List<CustomerAgent> customerAgentDO = customerAgentMapper.selectList(queryWrapper);
        // VO数据
        List<CustomerAgentVO> customerAgentVO = GeneralConvertor.convertor(customerAgentDO, CustomerAgentVO.class);
        // 判断是否增强
        if (customerAgentQuery.getAssignment() == null) {
            return customerAgentServiceResults.assignment(customerAgentVO);
        } else {
            return customerAgentQuery.getAssignment() ? customerAgentServiceResults.assignment(customerAgentVO) : customerAgentVO;
        }
    }


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
    @Override
    public Page<CustomerAgentVO> pageEnhance(Page page, CustomerAgentQuery customerAgentQuery) {
        CustomerAgent customerAgent = GeneralConvertor.convertor(customerAgentQuery, CustomerAgent.class);
        QueryWrapper<CustomerAgent> queryWrapper = new QueryWrapper<>(customerAgent);
        // TODO 自动生成查询，禁止手动写语句
        customerAgentServiceQuery.query(customerAgentQuery, queryWrapper);
        //筛选查询条件
        conditionScreening(customerAgentQuery);
        // TODO 人工查询条件
        queryArtificial(customerAgentQuery, queryWrapper);
        // DO数据
        Page<CustomerAgent> pageDO = customerAgentMapper.selectPage(page, queryWrapper);
        // VO数据
        Page<CustomerAgentVO> pageVO = customerAgentServiceResults.toPageVO(pageDO);
        // 判断是否增强
        if (customerAgentQuery.getAssignment() == null) {
            return customerAgentServiceResults.assignment(pageVO);
        } else {
            return customerAgentQuery.getAssignment() ? customerAgentServiceResults.assignment(pageVO) : pageVO;
        }
    }

    /**
     * 筛选条件
     *
     * @param customerAgentQuery
     */
    private void conditionScreening(CustomerAgentQuery customerAgentQuery) {
        //筛选更新时间 查询符合条件的客户id
        if (StringUtils.isNotBlank(customerAgentQuery.getModifyStartDateTime()) && StringUtils.isNotBlank(customerAgentQuery.getModifyEndDateTime())) {
            List<String> customerIds = new ArrayList<>();
            customerIds.add("99999999");
            List<Customer> list = customerService.list(new QueryWrapper<Customer>().lambda().
                    ge(Customer::getModifyDateTime, customerAgentQuery.getModifyStartDateTime()).le(Customer::getModifyDateTime, customerAgentQuery.getModifyEndDateTime()));
            if (CollectionUtils.isNotEmpty(list)) {
                customerIds = list.stream().map(Customer::getId).collect(Collectors.toList());
            }
            customerAgentQuery.setCustomerIds(customerIds);
        }
    }


    /**
     * TODO 单条
     *
     * @param customerAgentQuery 客户经纪人
     * @return CustomerAgentVO
     * @author lijh
     * @methodName getOneEnhance
     * @time 2022-08-31 09:35:12
     */
    @Override
    public CustomerAgentVO getOneEnhance(CustomerAgentQuery customerAgentQuery) {
        CustomerAgent customerAgent = GeneralConvertor.convertor(customerAgentQuery, CustomerAgent.class);
        QueryWrapper<CustomerAgent> queryWrapper = new QueryWrapper<>(customerAgent);
        // TODO 自动生成查询，禁止手动写语句
        customerAgentServiceQuery.query(customerAgentQuery, queryWrapper);
        // TODO 人工查询条件
        queryArtificial(customerAgentQuery, queryWrapper);
        // DO数据
        CustomerAgent customerAgentDO = customerAgentMapper.selectOne(queryWrapper);
        // VO数据
        CustomerAgentVO customerAgentVO = GeneralConvertor.convertor(customerAgentDO, CustomerAgentVO.class);
        // 判断是否增强
        if (customerAgentQuery.getAssignment() == null) {
            return customerAgentServiceResults.assignment(customerAgentVO);
        } else {
            return customerAgentQuery.getAssignment() ? customerAgentServiceResults.assignment(customerAgentVO) : customerAgentVO;
        }
    }


    /**
     * TODO 总数
     *
     * @param customerAgentQuery 客户经纪人
     * @return Integer
     * @author lijh
     * @methodName countEnhance
     * @time 2022-08-31 09:35:12
     */
    @Override
    public Long countEnhance(CustomerAgentQuery customerAgentQuery) {
        CustomerAgent customerAgent = GeneralConvertor.convertor(customerAgentQuery, CustomerAgent.class);
        QueryWrapper<CustomerAgent> queryWrapper = new QueryWrapper<>(customerAgent);
        // TODO 自动生成查询，禁止手动写语句
        customerAgentServiceQuery.query(customerAgentQuery, queryWrapper);
        // TODO 人工查询条件
        queryArtificial(customerAgentQuery, queryWrapper);
        return customerAgentMapper.selectCount(queryWrapper);
    }


    /**
     * TODO 新增
     *
     * @param customerAgentBO 客户经纪人
     * @return String
     * @author lijh
     * @methodName saveEnhance
     * @time 2022-08-31 09:35:12
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public String saveEnhance(CustomerAgentBO customerAgentBO) {
        CustomerAgent customerAgent = GeneralConvertor.convertor(customerAgentBO, CustomerAgent.class);
        customerAgentMapper.insert(customerAgent);
        return customerAgent.getId();
    }


    /**
     * TODO 修改
     *
     * @param customerAgentBO 客户经纪人
     * @return Boolean
     * @author lijh
     * @methodName updateEnhance
     * @time 2022-08-31 09:35:12
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public Boolean updateEnhance(CustomerAgentBO customerAgentBO) {
        CustomerAgent customerAgent = GeneralConvertor.convertor(customerAgentBO, CustomerAgent.class);
        UpdateWrapper<CustomerAgent> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", customerAgentBO.getId());
        Integer i = customerAgentMapper.update(customerAgent, updateWrapper);
        return i > 0 ? true : false;
    }

    /**
     * 客户经纪人备注编辑或保存
     *
     * @param customerAgentBO
     * @return Json<Boolean>
     * @author lijh
     * @methodName update
     * @time 2022-08-31 09:35:12
     */
    @Override
    public Boolean saveOrEdit(CustomerAgentBO customerAgentBO) {
        CustomerAgent customerAgent = GeneralConvertor.convertor(customerAgentBO, CustomerAgent.class);
        // 修改客户更新时间
        customerService.updateModifyDateTime(customerAgent.getCustomerId());
        return this.saveOrUpdate(customerAgent);
    }


    /**
     * TODO 删除
     *
     * @param customerAgentBO 客户经纪人
     * @return Boolean
     * @author lijh
     * @methodName removeEnhance
     * @time 2022-08-31 09:35:12
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public Boolean removeEnhance(CustomerAgentBO customerAgentBO) {
        CustomerAgent customerAgent = GeneralConvertor.convertor(customerAgentBO, CustomerAgent.class);
        QueryWrapper<CustomerAgent> queryWrapper = new QueryWrapper<>(customerAgent);
        Integer i = customerAgentMapper.delete(queryWrapper);
        return i > 0;
    }

    /**
     * 分配客户经纪人
     *
     * @param customerAgentBO
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public String allotCustomerAgent(CustomerAgentBO customerAgentBO) {
        CustomerAgent customerAgent = GeneralConvertor.convertor(customerAgentBO, CustomerAgent.class);
        // 校验经纪人是否可分配
        checkAgentUser(customerAgentBO.getAgentUserId());
        // 生成绑定关系
        customerAgent.setState(CustomerAgentStateEnum.主关联);
        customerAgent.setType(CustomerAgentTypeEnum.自营);
        this.save(customerAgent);
        // 生成操作记录
        customerAgentOperateRecordService.operateRecord(customerAgent.getCustomerId(), customerAgent.getAgentUserId(), CustomerAgentOperateRecordSourceEnum.公海分配, CustomerAgentOperateRecordOperationEnum.分配, customerAgent.getCreateName());
        // 复制备注
        List<CustomerAgent> remarkList = Lists.newArrayList();
        copyCustomerRemark(customerAgent, remarkList);
        this.saveOrUpdateBatch(remarkList);
        // mq通知订单服务
        allotSendInsurance(customerAgent, ChangeTypeEnum.新增.getCode(), OperationSourceTypeEnum.分配);
        // 修改客户更新时间
        customerService.updateModifyDateTime(customerAgent.getCustomerId());
        // 更新客户跟进信息
        customerCommunication(customerAgentBO);
        // 修改客户分配时间和跟进状态
        customerService.updateAllocateDateTime(customerAgent.getCustomerId());
        return customerAgent.getId();
    }

    /**
     * 批量分配经纪人
     *
     * @param customerAgentList
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public Boolean batchAllotCustomerAgent(List<CustomerAgent> customerAgentList) {
        //校验经纪人是否可分配
        checkAgentUser(customerAgentList.get(0).getAgentUserId());
        List<CustomerAgent> remarkList = Lists.newArrayList();
        for (CustomerAgent customerAgent : customerAgentList) {
            customerAgent.setState(CustomerAgentStateEnum.主关联);
            customerAgent.setType(CustomerAgentTypeEnum.自营);
            // 生成操作记录
            customerAgentOperateRecordService.operateRecord(customerAgent.getCustomerId(), customerAgent.getAgentUserId(), CustomerAgentOperateRecordSourceEnum.公海分配, CustomerAgentOperateRecordOperationEnum.分配, customerAgent.getCreateName());
            // 复制备注
            copyCustomerRemark(customerAgent, remarkList);
            // mq通知订单服务
            allotSendInsurance(customerAgent, ChangeTypeEnum.新增.getCode(), OperationSourceTypeEnum.批量分配);
        }
        this.saveOrUpdateBatch(remarkList);
        // 批量修改客户更新时间
        List<String> customerIdList = customerAgentList.stream().map(CustomerAgent::getCustomerId).collect(Collectors.toList());
        customerService.updateModifyDateTime(customerIdList);
        // 修改客户分配时间和跟进状态
        customerService.updateAllocateDateTime(customerIdList);
        return this.saveBatch(customerAgentList);
    }

    /**
     * 复制客户备注
     *
     * @param customerAgent
     */
    private void copyCustomerRemark(CustomerAgent customerAgent, List<CustomerAgent> remarkList) {
        //判断当前登录人和分配人是否一致。   一致不再复制客户备注
        if (customerAgent.getLoginAccountId().equals(customerAgent.getAgentUserId())) {
            return;
        }
        /**
         * 复制当前登录账号的备注
         * 如果分配的经纪人已存在备注，则更新   不存在，则新增
         */
        CustomerAgent loginAccountRemark = baseMapper.selectOne(new QueryWrapper<CustomerAgent>().lambda().eq(CustomerAgent::getCustomerId, customerAgent.getCustomerId()).eq(CustomerAgent::getAgentUserId, customerAgent.getLoginAccountId()).
                eq(CustomerAgent::getType, CustomerAgentTypeEnum.无).eq(CustomerAgent::getState, CustomerAgentStateEnum.无.getValue()));
        if (Objects.nonNull(loginAccountRemark)) {
            //分配的经纪人 对此客户的备注
            CustomerAgent agentRemark = baseMapper.selectOne(new QueryWrapper<CustomerAgent>().lambda().eq(CustomerAgent::getCustomerId, customerAgent.getCustomerId()).eq(CustomerAgent::getAgentUserId, customerAgent.getAgentUserId()).
                    eq(CustomerAgent::getType, CustomerAgentTypeEnum.无).eq(CustomerAgent::getState, CustomerAgentStateEnum.无.getValue()));
            if (Objects.isNull(agentRemark)) {
                agentRemark = new CustomerAgent();
            }
            //复制经纪人备注
            loginAccountRemark.copyAgentRemark(agentRemark);
            agentRemark.setAgentUserId(customerAgent.getAgentUserId());
            agentRemark.setCreateName(customerAgent.getCreateName());
            remarkList.add(agentRemark);
        }
    }

    /**
     * 生成客户经纪人
     *
     * @param customerId                           客户id
     * @param agentUserId                          经纪人id
     * @param customerAgentType                    经纪人类型
     * @param customerAgentOperateRecordSourceEnum 操作来源
     */
    @Override
    public void saveCustomerAgent(String customerId, String agentUserId, Integer customerAgentType, CustomerAgentOperateRecordSourceEnum customerAgentOperateRecordSourceEnum) {
        log.debug("生成客户经纪人关系->>>  customerId:{},  agentUserId:{},   customerAgentType:{}", customerId, agentUserId, customerAgentType);
        if (StringUtils.isEmpty(agentUserId)) {
            return;
        }
        if (Objects.isNull(customerAgentType)) {
            //查看团队成员
            TeamUserVO teamUserVO = userComponent.getTeamUserVO(agentUserId);
            if (Objects.isNull(teamUserVO) || StringUtils.isEmpty(teamUserVO.getUserId())) {
                log.info("非团队人员,agentUserId={}", agentUserId);
                return;
            }
            customerAgentType = CustomerAgentTypeEnum.getValue(teamUserVO.getType());
        }
        //经纪人类型
        CustomerAgentTypeEnum customerAgentTypeEnum = CustomerAgentTypeEnum.getCustomerAgentTypeEnum(customerAgentType);
        switch (customerAgentTypeEnum) {
            case 自营:
                //自营经纪人。无绑定关系就生成
                CustomerAgent customerAgentOne = this.getOne(new QueryWrapper<CustomerAgent>().lambda().eq(CustomerAgent::getCustomerId, customerId).
                        eq(CustomerAgent::getType, CustomerAgentTypeEnum.自营).eq(CustomerAgent::getState, CustomerAgentStateEnum.主关联));
                if (Objects.isNull(customerAgentOne)) {
                    customerAgentOne = new CustomerAgent();
                    customerAgentOne.setCustomerId(customerId);
                    customerAgentOne.setAgentUserId(agentUserId);
                    customerAgentOne.setType(customerAgentTypeEnum);
                    customerAgentOne.setState(CustomerAgentStateEnum.主关联);
                    this.save(customerAgentOne);
                    //生成操作记录
                    customerAgentOperateRecordService.operateRecord(customerId, agentUserId, customerAgentOperateRecordSourceEnum, CustomerAgentOperateRecordOperationEnum.分配, "系统生成");
                }
                break;
            case 分销:
                //关联经纪人。无绑定关系就生成
                CustomerAgent customerAgent = this.getOne(new QueryWrapper<CustomerAgent>().lambda().eq(CustomerAgent::getCustomerId, customerId).eq(CustomerAgent::getAgentUserId, agentUserId).
                        eq(CustomerAgent::getType, CustomerAgentTypeEnum.分销).eq(CustomerAgent::getState, CustomerAgentStateEnum.副关联));
                //客户和同一个经纪人只建立一次关联关系
                if (Objects.isNull(customerAgent)) {
                    customerAgent = new CustomerAgent();
                    //分销经纪人  一直生成关联关系
                    customerAgent.setCustomerId(customerId);
                    customerAgent.setAgentUserId(agentUserId);
                    customerAgent.setType(customerAgentTypeEnum);
                    customerAgent.setState(CustomerAgentStateEnum.副关联);
                    this.save(customerAgent);
                    //生成操作记录
                    customerAgentOperateRecordService.operateRecord(customerAgent.getCustomerId(), customerAgent.getAgentUserId(), customerAgentOperateRecordSourceEnum, CustomerAgentOperateRecordOperationEnum.关联, "系统生成");
                }
                break;
            default:
                break;
        }
    }

    /**
     * 转交经纪人
     *
     * @param customerAgentBO
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public Boolean transferAgent(CustomerAgentBO customerAgentBO) {
        CustomerAgent customerAgent = baseMapper.selectOne(new QueryWrapper<CustomerAgent>().lambda().eq(CustomerAgent::getCustomerId, customerAgentBO.getCustomerId()).
                eq(CustomerAgent::getType, CustomerAgentTypeEnum.自营).eq(CustomerAgent::getState, CustomerAgentStateEnum.主关联.getValue()));
        if (Objects.isNull(customerAgent)) {
            throw new BusinessException("此客户还未分配经纪人!");
        }
        if (customerAgent.getAgentUserId().equals(customerAgentBO.getAgentUserId())) {
            throw new BusinessException("只可转交给非原经纪人!");
        }
        //校验经纪人分配名额
        checkAgentUser(customerAgentBO.getAgentUserId());
        customerAgent.setModifyName(customerAgentBO.getModifyName());
        customerAgent.setState(CustomerAgentStateEnum.副关联);
        baseMapper.updateById(customerAgent);
        //新增分配记录
        CustomerAgent customerAgentOne = new CustomerAgent();
        customerAgentOne.setCustomerId(customerAgentBO.getCustomerId());
        customerAgentOne.setAgentUserId(customerAgentBO.getAgentUserId());
        customerAgentOne.setType(CustomerAgentTypeEnum.自营);
        customerAgentOne.setState(CustomerAgentStateEnum.主关联);
        customerAgentOne.setCreateName(customerAgentBO.getCreateName());
        int insert = baseMapper.insert(customerAgentOne);

        //新增操作记录
        customerAgentOperateRecordService.operateRecord(customerAgentOne.getCustomerId(), customerAgentOne.getAgentUserId(), CustomerAgentOperateRecordSourceEnum.自营转交, CustomerAgentOperateRecordOperationEnum.转移, customerAgentOne.getCreateName());
        //通知订单   转交经纪人推送两次 先解绑，后新增
        Customer customer = customerService.getById(customerAgent.getCustomerId());
        if (Objects.nonNull(customer) && StringUtils.isNotEmpty(customer.getUserId())) {
            //解绑通知
            insuranceService.sendInsurance(customer.getUserId(), customerAgent.getAgentUserId(), 0, ChangeTypeEnum.解绑.getCode(), OperationSourceTypeEnum.转交经纪人);
            //新增通知
            insuranceService.sendInsurance(customer.getUserId(), customerAgentOne.getAgentUserId(), 0, ChangeTypeEnum.新增.getCode(), OperationSourceTypeEnum.转交经纪人);
        }
        //修改客户更新时间
        customerService.updateModifyDateTime(customerAgentBO.getCustomerId());
        return insert > 0;
    }

    /**
     * 批量移入公海
     *
     * @param customerAgentList
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public Boolean batchAllotUntie(List<CustomerAgent> customerAgentList) {
        List<CustomerAgentOperateRecord> list = new ArrayList<>();
        for (CustomerAgent customerAgent : customerAgentList) {
            customerAgent.setState(CustomerAgentStateEnum.副关联);
            baseMapper.update(customerAgent, new QueryWrapper<CustomerAgent>().lambda().eq(CustomerAgent::getCustomerId, customerAgent.getCustomerId()).
                    eq(CustomerAgent::getType, CustomerAgentTypeEnum.自营).eq(CustomerAgent::getState, CustomerAgentStateEnum.主关联));
            //添加操作记录
            CustomerAgentOperateRecord customerAgentOperateRecord = new CustomerAgentOperateRecord();
            customerAgentOperateRecord.setCustomerId(customerAgent.getCustomerId());
            customerAgentOperateRecord.setAgentUserId(customerAgent.getAgentUserId());
            customerAgentOperateRecord.setSource(CustomerAgentOperateRecordSourceEnum.自营解绑);
            customerAgentOperateRecord.setOperation(CustomerAgentOperateRecordOperationEnum.解绑);
            customerAgentOperateRecord.setCreateName(customerAgent.getCreateName());
            list.add(customerAgentOperateRecord);
        }
        customerAgentOperateRecordService.saveBatch(list);
        //通知订单
        for (CustomerAgent customerAgent : customerAgentList) {
            allotSendInsurance(customerAgent, ChangeTypeEnum.解绑.getCode(), OperationSourceTypeEnum.移入公海);
        }
        // 批量修改客户更新时间
        List<String> customerIdList = customerAgentList.stream().map(CustomerAgent::getCustomerId).collect(Collectors.toList());
        customerService.updateModifyDateTime(customerIdList);
        return true;
    }

    @Override
    public Object selectCustomerByAgentUserId(Page page, CustomerAgentQuery customerAgentQuery) {
        Page<CustomerAgent> customerAgents = baseMapper.selectPage(page, new QueryWrapper<CustomerAgent>().lambda().eq(CustomerAgent::getAgentUserId, customerAgentQuery.getAgentUserId()).
                eq(CustomerAgent::getState, CustomerAgentStateEnum.主关联).eq(CustomerAgent::getType, CustomerAgentTypeEnum.自营));
        if (CollectionUtils.isEmpty(customerAgents.getRecords())) {
            return customerAgents;
        }
        for (CustomerAgent customerAgent : customerAgents.getRecords()) {
            //客户信息
            Customer customer = customerService.getById(customerAgent.getCustomerId());
            customerAgent.setCustomerMobile(customer.getMobile());
            customerAgent.setCustomerName(customer.getName());
        }
        return customerAgents;
    }

    /**
     * 根据查询条件 获取客户id
     *
     * @param customerAgentQuery
     * @return
     */
    @Override
    public List<String> selectCustomerIds(CustomerAgentQuery customerAgentQuery) {
        QueryWrapper<CustomerAgent> queryWrapper = new QueryWrapper();
        queryWrapper.eq("type", customerAgentQuery.getType().getValue());
        queryWrapper.eq("agent_user_id", customerAgentQuery.getAgentUserIdQuery());
        //企业名称查询
        if (StringUtils.isNotBlank(customerAgentQuery.getEnterpriseName())) {
            queryWrapper.like("enterprise_name", customerAgentQuery.getEnterpriseName());
        }
        //省市区
        if (StringUtils.isNotBlank(customerAgentQuery.getProvinceCode())) {
            queryWrapper.eq("province_code", customerAgentQuery.getProvinceCode());
        }
        if (StringUtils.isNotBlank(customerAgentQuery.getCityCode())) {
            queryWrapper.eq("city_code", customerAgentQuery.getCityCode());
        }
        if (StringUtils.isNotBlank(customerAgentQuery.getAreaCode())) {
            queryWrapper.eq("area_code", customerAgentQuery.getAreaCode());
        }
        List<CustomerAgent> customerAgentList = this.list(queryWrapper);
        if (CollectionUtils.isEmpty(customerAgentList)) {
            return new ArrayList<>();
        }
        return customerAgentList.stream().map(CustomerAgent::getCustomerId).collect(Collectors.toList());
    }

    /**
     * 新增客户经纪人备注   仅限客户生成咨询单时调用
     *
     * @param customerAgentRemark
     */
    @Override
    public void saveCustomerRemark(CustomerAgent customerAgentRemark) {
        log.info("新增客户经纪人备注->>>  customerId={},agentUserId={}", customerAgentRemark.getCustomerId(), customerAgentRemark.getAgentUserId());
        //备注的经纪人为空。默认赋值公海管理员id
        if (StringUtils.isEmpty(customerAgentRemark.getAgentUserId())) {
            customerAgentRemark.setAgentUserId(GongBaoConfig.highSeasAdminUserId);
        }
        CustomerAgent customerAgent = baseMapper.selectOne(new QueryWrapper<CustomerAgent>().lambda().eq(CustomerAgent::getCustomerId, customerAgentRemark.getCustomerId()).eq(CustomerAgent::getAgentUserId, customerAgentRemark.getAgentUserId()).
                eq(CustomerAgent::getType, CustomerAgentTypeEnum.无).eq(CustomerAgent::getState, CustomerAgentStateEnum.无));
        //已存在备注更新  否则做新增
        if (Objects.isNull(customerAgent)) {
            baseMapper.insert(customerAgentRemark);
        } else {
            customerAgentRemark.copyAgentRemark(customerAgent);
            baseMapper.updateById(customerAgent);
        }

    }

    /**
     * 获取已经分配经纪人的客户(不在公海)
     *
     * @return 客户手机号集合
     */
    @Override
    public List<String> getCustomerMobileAlreadyAllotAgent() {
        List<CustomerAgent> customerAgentList = this.list(new QueryWrapper<CustomerAgent>().eq("type", CustomerAgentTypeEnum.自营).eq("state", CustomerAgentStateEnum.主关联).
                select("distinct customer_id", "agent_user_id", "(select c.mobile  from customer c where c.id = customer_agent.customer_id) as customerMobile"));
        if (CollectionUtils.isEmpty(customerAgentList)) {
            return Lists.newArrayList();
        }
        return customerAgentList.stream().map(CustomerAgent::getCustomerMobile).collect(Collectors.toList());

    }

    /**
     * 获取经纪人对客户备注
     *
     * @param customerId  客户id
     * @param agentUserId 经纪人id
     * @return
     */
    @Override
    public CustomerAgent getAgentRemarkCustomer(String customerId, String agentUserId) {
        return baseMapper.selectOne(new QueryWrapper<CustomerAgent>().lambda().eq(CustomerAgent::getCustomerId, customerId).
                eq(CustomerAgent::getAgentUserId, agentUserId).eq(CustomerAgent::getState, CustomerAgentStateEnum.无).eq(CustomerAgent::getType, CustomerAgentTypeEnum.无));
    }

    /**
     * 根据userId批量获取客户id
     *
     * @param customerInfoReqDtoList
     * @return userId|agentUserId :  customerId
     */
    @Override
    public Map<String, String> getCustomerIdByUserId(List<CustomerInfoReqDto> customerInfoReqDtoList) {
        Map<String, String> resultMap = new HashMap<>();
        for (CustomerInfoReqDto customerInfoReqDto : customerInfoReqDtoList) {
            String key = customerInfoReqDto.getUserId() + "|" + customerInfoReqDto.getAgentUserId();
            resultMap.put(key, null);
            List<Customer> customers = customerService.list(new QueryWrapper<Customer>().lambda().eq(Customer::getUserId, customerInfoReqDto.getUserId()));
            if (CollectionUtils.isEmpty(customers)) {
                continue;
            }
            for (Customer customer : customers) {
                //客户和经纪人 是否有分配关系或关联关系
                long count = this.count(new QueryWrapper<CustomerAgent>().lambda().eq(CustomerAgent::getCustomerId, customer.getId()).eq(CustomerAgent::getAgentUserId, customerInfoReqDto.getAgentUserId()).
                        and(broker -> broker.eq(CustomerAgent::getState, CustomerAgentStateEnum.主关联).eq(CustomerAgent::getType, CustomerAgentTypeEnum.自营).
                                or().eq(CustomerAgent::getType, CustomerAgentTypeEnum.分销))
                );
                if (count > 0) {
                    resultMap.put(key, customer.getId());
                    continue;
                }
            }

        }
        return resultMap;
    }

    /**
     * 校验分配的经纪人 是否超出分配数量
     *
     * @param agentUserId
     */
    private void checkAgentUser(String agentUserId) {
        //获取分配经纪人的团队信息
        TeamUserVO teamUserVO = userComponent.getTeamUserVO(agentUserId);
        //获取此经纪人已分配客户数量
        long count = this.count(new QueryWrapper<CustomerAgent>().lambda().eq(CustomerAgent::getAgentUserId, agentUserId).eq(CustomerAgent::getType, CustomerAgentTypeEnum.自营));
        //判断分配的经纪人是否超出限制数量
        if (Objects.nonNull(teamUserVO.getTeamGroupValueLimitNum()) && count + 1 > teamUserVO.getTeamGroupValueLimitNum()) {
            throw new BusinessException("该经纪人分配客户已满，请选择其他经纪人！");
        }
    }


    /**
     * TODO 人工查询条件
     *
     * @param customerAgentQuery 客户经纪人
     * @return QueryWrapper
     * @author lijh
     * @methodName queryArtificial
     * @time 2022-08-31 09:35:12
     */
    private void queryArtificial(CustomerAgentQuery customerAgentQuery, QueryWrapper<CustomerAgent> queryWrapper) {

        /**
         * 排除已删除的客户
         */
        queryWrapper.inSql("customer_id", "select `id` from `customer` where `is_delete` = false");
        /**
         * 用于数据权限
         * 查询指定经纪人下的数据
         */
        if (CollectionUtils.isNotEmpty(customerAgentQuery.getAgentUserIds())) {
            queryWrapper.in("agent_user_id", customerAgentQuery.getAgentUserIds());
        }

        /**
         * 分销客户列表筛选
         * 客户经纪人表 副关联的客户
         */
        if (CluesPortEnum.分销列表.getCode().equals(customerAgentQuery.getCustomerListType())) {
            queryWrapper.eq("type", CustomerAgentTypeEnum.分销.getValue());
            queryWrapper.select("id", "customer_id", "agent_user_id", "(select c.modify_date_time  from customer c where c.id = customer_agent.customer_id and c.is_delete = false) as customer_modify_date_time", "(select `allocate_date_time` from `customer` where `customer_id` = `customer`.`id`) as allocate_date_time");
        }


        /**
         * 自营客户列表筛选
         * 客户经纪人表 主关联的客户
         */
        if (CluesPortEnum.自营列表.getCode().equals(customerAgentQuery.getCustomerListType())) {
            queryWrapper.eq("type", CustomerAgentTypeEnum.自营.getValue()).eq("state", CustomerAgentStateEnum.主关联);
            queryWrapper.select("id", "customer_id", "agent_user_id", "(select c.modify_date_time  from customer c where c.id = customer_agent.customer_id and c.is_delete = false) as customer_modify_date_time", "(select `allocate_date_time` from `customer` where `customer_id` = `customer`.`id`) as allocate_date_time");
        }

        /**
         * 更新时间 区间查询符合条件的客户id
         */
        if (CollectionUtils.isNotEmpty(customerAgentQuery.getCustomerIds())) {
            queryWrapper.in("customer_id", customerAgentQuery.getCustomerIds());
        }

        /**
         * 经纪人id
         */
        if (Objects.nonNull(customerAgentQuery.getAgentUserIdQuery())) {
            queryWrapper.eq("agent_user_id", customerAgentQuery.getAgentUserIdQuery());
        }

        /**
         * 手机号
         * 优先获取注册用户
         * 1：已注册就拿userId匹配客户
         * 2：未注册拿手机号匹配客户
         */
        if (Objects.nonNull(customerAgentQuery.getMobileQuery())) {
            //根据手机号获取用户信息
            Map<String, Object> userMap = new HashMap<>(1);
            userMap.put("mobile", customerAgentQuery.getMobileQuery());
            UserExtendsVO userExtendsVO = rpcComponent.rpcQueryInfo(userMap, RpcTypeEnum.GET_USER_EXTENDS_ONE, UserExtendsVO.class);
            //已注册
            if (Objects.nonNull(userExtendsVO) && StringUtils.isNotBlank(userExtendsVO.getUserId())) {
                queryWrapper.inSql("customer_id", "select `id` from `customer` where `user_id` = " + userExtendsVO.getUserId() + " and `is_delete` = false");
            } else {
                queryWrapper.inSql("customer_id", "select `id` from `customer` where `mobile` = " + customerAgentQuery.getMobileQuery() + " and `is_delete` = false");
            }
        }

        /**
         * 类型
         */
        if (Objects.nonNull(customerAgentQuery.getType())) {
            queryWrapper.eq("type", customerAgentQuery.getType().getValue());
        }

        /**
         * 分销和自营的列表 由此筛选
         */
        if (StringUtils.isNotEmpty(customerAgentQuery.getCustomerListType())) {
            //  客户意向
            if (Objects.nonNull(customerAgentQuery.getIntentionQuery())) {
                queryWrapper.inSql("customer_id",
                        "SELECT ca.customer_id FROM customer_agent ca WHERE ca.type = 2 AND ca.customer_id = customer_agent.customer_id AND ca.agent_user_id = customer_agent.agent_user_id  AND `intention` = \n'" + customerAgentQuery.getIntentionQuery() + "'\n and `is_delete` = false");
            }
            //  企业名称
            if (StringUtils.isNotBlank(customerAgentQuery.getEnterpriseNameQuery())) {
                queryWrapper.inSql("customer_id",
                        "SELECT ca.customer_id FROM customer_agent ca WHERE ca.type = 2 AND ca.customer_id = customer_agent.customer_id AND ca.agent_user_id = customer_agent.agent_user_id  AND `enterprise_name` like \n'" + customerAgentQuery.getEnterpriseNameQuery() + "%'\n and `is_delete` = false");
            }
            //  姓名
            if (StringUtils.isNotBlank(customerAgentQuery.getNameQuery())) {
                queryWrapper.inSql("customer_id",
                        "SELECT ca.customer_id FROM customer_agent ca WHERE ca.type = 2 AND ca.customer_id = customer_agent.customer_id AND ca.agent_user_id = customer_agent.agent_user_id  AND `name` like \n'" + customerAgentQuery.getNameQuery() + "%'\n and `is_delete` = false");
            }
            //  省
            if (StringUtils.isNotBlank(customerAgentQuery.getProvinceCodeQuery())) {
                queryWrapper.inSql("customer_id",
                        "SELECT ca.customer_id FROM customer_agent ca WHERE ca.type = 2 AND ca.customer_id = customer_agent.customer_id AND ca.agent_user_id = customer_agent.agent_user_id  AND `province_code` = \n'" + customerAgentQuery.getProvinceCodeQuery() + "'\n and `is_delete` = false");
            }
            //  市
            if (StringUtils.isNotBlank(customerAgentQuery.getCityCodeQuery())) {
                queryWrapper.inSql("customer_id",
                        "SELECT ca.customer_id FROM customer_agent ca WHERE ca.type = 2 AND ca.customer_id = customer_agent.customer_id AND ca.agent_user_id = customer_agent.agent_user_id  AND `city_code` = \n'" + customerAgentQuery.getCityCodeQuery() + "'\n and `is_delete` = false");
            }
            //  区
            if (StringUtils.isNotBlank(customerAgentQuery.getAreaCodeQuery())) {
                queryWrapper.inSql("customer_id",
                        "SELECT ca.customer_id FROM customer_agent ca WHERE ca.type = 2 AND ca.customer_id = customer_agent.customer_id AND ca.agent_user_id = customer_agent.agent_user_id  AND `area_code` = \n'" + customerAgentQuery.getAreaCodeQuery() + "'\n and `is_delete` = false");
            }
            //  客户阶段
            if (Objects.nonNull(customerAgentQuery.getStageQuery())) {
                queryWrapper.inSql("customer_id",
                        "SELECT ca.customer_id FROM customer_agent ca WHERE ca.type = 2 AND ca.customer_id = customer_agent.customer_id AND ca.agent_user_id = customer_agent.agent_user_id  AND `stage` = \n'" + customerAgentQuery.getStageQuery() + "'\n and `is_delete` = false");
            }

        }


        /**
         * 是否注册
         */
        if (Objects.nonNull(customerAgentQuery.getRegisterQuery())) {
            queryWrapper.inSql("customer_id", "select `id` from `customer` where `state` = " + customerAgentQuery.getRegisterQuery() + " and `is_delete` = false");
        }

        /**
         * 跟进状态
         */
        if (Objects.nonNull(customerAgentQuery.getConnectionState())) {
            queryWrapper.inSql("customer_id", "select `id` from `customer` where `connection_state` = " + customerAgentQuery.getConnectionState() + " and `is_delete` = false");
        }

        /**
         * 分配时间区间查询
         */
        if (StringUtils.isNotBlank(customerAgentQuery.getStartAllocateDateTime()) && StringUtils.isNotBlank(customerAgentQuery.getEndAllocateDateTime())) {
            queryWrapper.inSql("customer_id", "select `id` from `customer` where `allocate_date_time` between '" + customerAgentQuery.getStartAllocateDateTime() + "' and '" + customerAgentQuery.getEndAllocateDateTime() + "' and `is_delete` = false");
        }
    }

    /**
     * 客户操作 mq推送订单
     *
     * @param customerAgent
     * @param changeType    1新增 2解绑
     */
    private void allotSendInsurance(CustomerAgent customerAgent, Integer changeType, OperationSourceTypeEnum operationSourceTypeEnum) {
        Customer customer = customerService.getById(customerAgent.getCustomerId());
        if (Objects.nonNull(customer) && StringUtils.isNotEmpty(customer.getUserId())) {
            insuranceService.sendInsurance(customer.getUserId(), customerAgent.getAgentUserId(), 0, changeType, operationSourceTypeEnum);
        }
    }


    /**
     * TODO 更新客户跟进记录
     *
     * @param customerAgentBO
     * @return void
     * @author 孙凯伦
     * @methodName customerCommunication
     * @time 2023/7/11 10:46 AM
     */
    private void customerCommunication(CustomerAgentBO customerAgentBO) {
        UpdateWrapper<CustomerCommunication> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("customer_id", customerAgentBO.getCustomerId());
        customerCommunicationMapper.update(new CustomerCommunication() {{
            setUserId(customerAgentBO.getAgentUserId());
        }}, updateWrapper);
    }
}