package com.gb.customer.template.impl;

import cn.hutool.core.convert.Convert;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.gb.bean.GongBaoConfig;
import com.gb.customer.entity.*;
import com.gb.customer.entity.enums.*;
import com.gb.customer.enums.*;
import com.gb.customer.factory.PotentialCustomerFactory;
import com.gb.customer.mapper.AgentPeopleMapper;
import com.gb.customer.service.*;
import com.gb.customer.service.impl.CheckPermissions;
import com.gb.customer.template.AbstractCluesTemplate;
import com.gb.customer.template.CluesPriorityEnum;
import com.gb.customer.template.event.CluesEvent;
import com.gb.promoteform.entity.PromoteForm;
import com.gb.promoteform.service.PromoteFormService;
import com.gb.rpc.ProductRpc;
import com.gb.rpc.component.RulesComponent;
import com.gb.rpc.component.UserComponent;
import com.gb.rpc.entity.TeamUserVO;
import com.gb.rpc.entity.UserAgentCertification;
import com.gb.rpc.entity.UserExtendsVO;
import com.gb.rpc.entity.UserGroupInfo;
import com.gb.rpc.entity.enums.RpcTypeEnum;
import com.gb.rpc.entity.enums.TeamUserFormalStateEnum;
import com.gb.rpc.impl.RpcComponent;
import com.gb.utils.Json;
import com.gb.utils.OkhttpUtils;
import com.gb.utils.RedisUtils;
import com.gb.utils.constants.UniversalConstant;
import com.gb.utils.enumeration.HttpType;
import com.gb.utils.enumeration.HttpWay;
import com.gb.utils.enumeration.ReturnCode;
import com.gb.utils.enumeration.UserTypeEnum;
import com.gb.utils.exception.BusinessException;
import com.gb.utils.exception.CustomerException;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Request;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.CompletableFuture;


/**
 * @author ljh
 * @date 2022/3/15 10:37 上午
 */
@Slf4j
@Service
@Setter(onMethod_ = {@Autowired})
public class WebsiteSource extends AbstractCluesTemplate<PotentialCustomer> implements ApplicationEventPublisherAware {

    private PotentialCustomerService potentialCustomerService;
    private CheckPermissions checkPermissions;
    private AgentPeopleMapper agentPeopleMapper;
    private RpcComponent rpcComponent;
    private ProductRpc productRpc;
    private CustomerAssociatedService customerAssociatedService;
    private ApplicationEventPublisher applicationEventPublisher;

    private StringRedisTemplate stringRedisTemplate;
    /**
     * 推广表单
     */
    private PromoteFormService promoteFormService;

    private UserComponent userComponent;

    private CustomerService customerService;

    private CustomerAgentService customerAgentService;

    private CustomerSourceService customerSourceService;

    private RulesComponent rulesComponent;


    final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    /**
     * 校验 筛选
     *
     * @param potentialCustomer
     * @return
     */
    @Override
    public Boolean checkScreening(PotentialCustomer potentialCustomer) {
        log.info("官网来源校验筛选,potentialCustomer={}", potentialCustomer);
        if (null == potentialCustomer.getType()) {
            throw new CustomerException(ReturnCode.用户端错误.getState(), "未知咨询类型！");
        }
        //获取订单来源
        orderSource(potentialCustomer);
        //查询历史咨询记录,已存在线索不再生成
        PotentialCustomer existPotentialCustomer = potentialCustomerService.getOne(new LambdaQueryWrapper<PotentialCustomer>() {{
            eq(PotentialCustomer::getMobile, Convert.toStr(potentialCustomer.getMobile()));
            eq(PotentialCustomer::getType, Convert.toStr(potentialCustomer.getType())).orderByDesc(PotentialCustomer::getCreateDateTime).last("limit 1");
        }});
        //类型（0：产品咨询，1：经纪人咨询
        switch (potentialCustomer.getType()) {
            case 0:
                //1.已咨询取之前信息，未咨询默认分配
                if (Objects.nonNull(existPotentialCustomer)) {
                    log.info("手机号{}已咨询过,不再重新分配管家", potentialCustomer.getMobile());
                    return false;
                }
                break;
            case 1:
                //1.根据手机号判断是否是 经纪人，如果是经纪人，不能进行经纪人咨询
                if (agentPeopleMapper.selectCount(new LambdaQueryWrapper<AgentPeople>() {{
                    eq(AgentPeople::getMobile, potentialCustomer.getMobile());
                }}) > 0) {
                    throw new CustomerException(ReturnCode.用户端错误.getState(), "您已认证成为经纪人，请联系您的服务管家!");
                }
                //2.用户进行经纪人咨询，如果已经咨询过，不插入数据，就直接成功
                if (Objects.nonNull(existPotentialCustomer)) {
                    log.info("手机号={}已存在经纪人线索", potentialCustomer.getMobile());
                    return true;
                }
                //3.查询user是否已认证过经纪人，已认证不能再咨询
                if (getAgentCertificationInfo(potentialCustomer)) {
                    throw new CustomerException(ReturnCode.用户端错误.getState(), "已经认证经纪人");
                }
                break;
            default:
                break;
        }

        //校验邀请人身份
        if (StringUtils.isNotBlank(potentialCustomer.getAgentUserId()) && checkPermissions.checkUser(potentialCustomer)) {
            potentialCustomer.setAgentUserId(null);
            potentialCustomer.setAgentUserName(null);
        }
        return false;
    }

    /**
     * 链接带参-邀请
     * <p>
     *
     * @return 是否被邀请 true-是  false-否
     */
    @Override
    public Boolean isBeInvited(PotentialCustomer potentialCustomer) {
        log.info("被邀请的用户,用户手机号={},邀请人userId={},邀请人名字={}", potentialCustomer.getMobile(), potentialCustomer.getAgentUserId(), potentialCustomer.getAgentUserName());
        //未被邀请
        if (StringUtils.isEmpty(potentialCustomer.getAgentUserId())) {
            return false;
        }
        potentialCustomer.setAllocation(Boolean.TRUE);
        //邀请经纪人不再区分管家类型，直接返回
        if (PotentialCustomerTypeEnum.CUSTOMER_TYPE_1.getCode().equals(potentialCustomer.getType())) {
            return true;
        }
        //邀请人不属于团队成员，不当成邀请。
        TeamUserVO teamUserVoInfo = userComponent.getTeamUserVOInfo(potentialCustomer.getAgentUserId());
        if (StringUtils.isEmpty(teamUserVoInfo.getUserId())) {
            potentialCustomer.setAgentUserId(null);
            potentialCustomer.setAgentUserName(null);
            potentialCustomer.setAllocation(Boolean.FALSE);
            return false;
        }
        //以离职或以注销 不当成邀请
        if (StringUtils.isNotEmpty(teamUserVoInfo.getUserFormalStateEnum()) && !TeamUserFormalStateEnum.在职.getDesc().equals(teamUserVoInfo.getUserFormalStateEnum())) {
            potentialCustomer.setAgentUserId(null);
            potentialCustomer.setAgentUserName(null);
            potentialCustomer.setAllocation(Boolean.FALSE);
            return false;
        }
        /*
          已分配
               绑定原经纪人
          未分配
               邀请人非团队成员  客户流入公海。不做分配和关联关系。
               邀请人是团队成员  根据经纪人类型 做分配或关联关系
         */
        Customer customerOne = customerService.getOne(new QueryWrapper<Customer>().lambda().eq(Customer::getMobile, potentialCustomer.getMobile()));
        //新用户直接返回。
        if (Objects.isNull(customerOne)) {
            return true;
        }
        //邀请人类型
        CustomerAgentTypeEnum customerAgentTypeEnum = CustomerAgentTypeEnum.getCustomerAgentTypeEnum(CustomerAgentTypeEnum.getValue(teamUserVoInfo.getType()));
        potentialCustomer.setCustomerAgentType(customerAgentTypeEnum.getValue());

        CustomerAgent customerAgent = new CustomerAgent();
        switch (customerAgentTypeEnum) {
            case 自营:
                customerAgent = customerAgentService.getOne(new QueryWrapper<CustomerAgent>().lambda().
                        eq(CustomerAgent::getCustomerId, customerOne.getId()).eq(CustomerAgent::getType, CustomerAgentTypeEnum.自营).eq(CustomerAgent::getState, CustomerAgentStateEnum.主关联));
                break;
            case 分销:
                customerAgent = customerAgentService.getOne(new QueryWrapper<CustomerAgent>().lambda().eq(CustomerAgent::getCustomerId, customerOne.getId()).
                        eq(CustomerAgent::getType, CustomerAgentTypeEnum.分销).eq(CustomerAgent::getAgentUserId, potentialCustomer.getAgentUserId()));
                break;
            default:
                break;
        }
        //已分配过经纪人， 绑定原有经纪人
        if (Objects.nonNull(customerAgent) && StringUtils.isNotEmpty(customerAgent.getAgentUserId())) {
            potentialCustomer.setAgentUserId(customerAgent.getAgentUserId());
            potentialCustomer.setAgentUserName(userComponent.getOneUserByUserId(customerAgent.getAgentUserId()).getName());
            potentialCustomer.setCustomerAgentType(customerAgent.getType().getValue());
        }
        return true;
    }

    /**
     * 分配管家
     */
    @Override
    public void allotSteward(PotentialCustomer potentialCustomer) {
        log.info("分配管家,type={},cluesPort={},mobile={},name={}", potentialCustomer.getType(), potentialCustomer.getCluesPort(), potentialCustomer.getMobile(), potentialCustomer.getName());
        if (PotentialCustomerTypeEnum.CUSTOMER_TYPE_1.getCode().equals(potentialCustomer.getType())) {
            distribution(potentialCustomer, UserTypeEnum.经纪人咨询.getTypeCode());
            return;
        }
        /**
         * TODO CRM 2.0 取消掉  1.校验是否开启自动分配。 2.指定地区code分配。  3.指定标签code分配   4.产品咨询默认不分配管家
         * 线索存在，客户一定存在。
         */
        Customer customerOne = customerService.getOne(new QueryWrapper<Customer>().lambda().eq(Customer::getMobile, potentialCustomer.getMobile()));
        //新用户直接返回。
        if (Objects.nonNull(customerOne)) {
            //老用户已分配经纪人  绑定原经纪人   未分配重新绑定
            CustomerAgent customerAgent = customerAgentService.getOne(new QueryWrapper<CustomerAgent>().lambda().eq(CustomerAgent::getCustomerId, customerOne.getId()).eq(CustomerAgent::getType, CustomerAgentTypeEnum.自营).eq(CustomerAgent::getState, CustomerAgentStateEnum.主关联));
            if (Objects.nonNull(customerAgent)) {
                potentialCustomer.setAgentUserId(customerAgent.getAgentUserId());
                potentialCustomer.setCustomerAgentType(customerAgent.getType().getValue());
                return;
            }
        }
        //端口来源分配指定管家
        if (StringUtils.isNotEmpty(potentialCustomer.getCluesPort())) {
            distribution(potentialCustomer, CluesPortEnum.getTypeCode(potentialCustomer.getCluesPort()).getTypeCode());
        }

        //查询历史咨询记录,已存在线索不再生成
        //PotentialCustomer existPotentialCustomer = potentialCustomerService.getOne(new LambdaQueryWrapper<PotentialCustomer>() {{
        //    eq(PotentialCustomer::getMobile, Convert.toStr(potentialCustomer.getMobile()));
        //    eq(PotentialCustomer::getType, Convert.toStr(potentialCustomer.getType())).orderByDesc(PotentialCustomer::getCreateDateTime).last("limit 1");
        //}});
        //if (Objects.nonNull(existPotentialCustomer)) {
        //    log.info("手机号{}已咨询过,不再重新分配管家", potentialCustomer.getMobile());
        //    potentialCustomer.setAgentUserName(existPotentialCustomer.getAgentUserName());
        //    potentialCustomer.setAgentUserId(existPotentialCustomer.getAgentUserId());
        //    potentialCustomer.setAgentGroupId(existPotentialCustomer.getAgentGroupId());
        //    potentialCustomer.setAgentUserType(existPotentialCustomer.getAgentUserType());
        //    return;
        //}

        //校验是否开启自动分配
        //if (!customerManagerRuleConfigurationService.isAssigned()) {
        //    return;
        //}

        //地区code不为空。获取地区绑定标签分配管家
        //if (StringUtils.isNotEmpty(potentialCustomer.getProvinceCode()) || StringUtils.isNotEmpty(potentialCustomer.getCityCode()) || StringUtils.isNotEmpty(potentialCustomer.getAreaCode())) {
        //    //获取地区标签
        //    Map<String, Object> map = new HashMap<>(3);
        //    if (StringUtils.isNotEmpty(potentialCustomer.getProvinceCode())) {
        //        map.put("provinceCode", potentialCustomer.getProvinceCode());
        //    }
        //    if (StringUtils.isNotEmpty(potentialCustomer.getCityCode())) {
        //        map.put("cityCode", potentialCustomer.getCityCode());
        //    }
        //    if (StringUtils.isNotEmpty(potentialCustomer.getAreaCode())) {
        //        map.put("areaCode", potentialCustomer.getAreaCode());
        //    }
        //    UserTypeValueRegionVO userTypeValueRegionVO = rpcComponent.rpcQueryInfo(map, RpcTypeEnum.GET_ONE_REGION_LABEL, UserTypeValueRegionVO.class);
        //    if (StringUtils.isNotEmpty(userTypeValueRegionVO.getUserTypeValueCode())) {
        //        distribution(potentialCustomer, Convert.toStr(userTypeValueRegionVO.getUserTypeValueCode()));
        //        //指定标签下没有管家   继续向下匹配
        //        if (StringUtils.isNotEmpty(potentialCustomer.getAgentUserId())) {
        //            return;
        //        }
        //    }
        //}
        //标签code不为空  分配指定标签下管家
        //if (StringUtils.isNotEmpty(potentialCustomer.getLabelCode())) {
        //    distribution(potentialCustomer, Convert.toStr(potentialCustomer.getLabelCode()));
        //    //指定标签下没有管家   继续向下匹配
        //    if (StringUtils.isNotEmpty(potentialCustomer.getAgentUserId())) {
        //        return;
        //    }
        //}
        //类型（0：产品咨询，1：经纪人咨询    分配指定管家
        //switch (potentialCustomer.getType()) {
        //    case 0:
        //        distribution(potentialCustomer, UserTypeEnum.用户咨询.getTypeCode());
        //        break;
        //    case 1:
        //        distribution(potentialCustomer, UserTypeEnum.经纪人咨询.getTypeCode());
        //    default:
        //        break;
        //}
    }

    /**
     * 保存线索
     *
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public Object saveCluesData(PotentialCustomer potentialCustomer, HttpServletRequest httpServletRequest) {
        log.info("官网来源新增线索,potentialCustomer={}", potentialCustomer);
        //绑定的经纪人信息
        UserExtendsVO agentUserExtendsVo = new UserExtendsVO();
        if (StringUtils.isNotEmpty(potentialCustomer.getAgentUserId())) {
            agentUserExtendsVo = userComponent.getUserExtendsVOByUserId(potentialCustomer.getAgentUserId());
        }
        setCity(potentialCustomer);
        potentialCustomer.setAgentUserName(agentUserExtendsVo.getName());
        potentialCustomer.setState(PotentialCustomerStateEnum.处理中.getCode());
        potentialCustomer.setSourceType(PotentialCustomerSourceTypeEnum.SOURCE_TYPE_1.getCode());
        //构建客户线索信息
        PotentialCustomerFactory.getBeanPotentialCustomer(potentialCustomer);
        boolean saveSuccess = potentialCustomerService.save(potentialCustomer);
        String token = StringUtils.EMPTY;

        //产品咨询  注册用户  生成客户，生成客户线索关联，生成客户经纪人
        if (saveSuccess & PotentialCustomerTypeEnum.CUSTOMER_TYPE_0.getCode().equals(potentialCustomer.getType())) {
            //预约成功注册用户
            token = quickLogin(potentialCustomer, httpServletRequest);
            //生成客户
            getCustomer(potentialCustomer);

        }
        //消息保存成功 发送短信和推送用户消息
        applicationEventPublisher.publishEvent(new CluesEvent(this, potentialCustomer.getId(), potentialCustomer.getAgentUserId(), potentialCustomer.getAgentUserType(), potentialCustomer.getMobile(), potentialCustomer.getType()));
        //推送云
        sendYunPromoteFormClues(potentialCustomer);
        //返回结果
        Map<String, Object> resultMap = new HashMap<>(2);
        resultMap.put("token", token);
        resultMap.put("agentUserMobile", Convert.toStr(agentUserExtendsVo.getMobile()));
        return resultMap;
    }

    /**
     * 向云同步 表单预约咨询单
     * 云单独配置的信息收集表单产生的咨询单才会推送
     *
     * @param potentialCustomer
     */
    private static void sendYunPromoteFormClues(PotentialCustomer potentialCustomer) {
        List<String> promoteFormIds = Arrays.asList(GongBaoConfig.promoteFormIds.split(","));
        log.debug("异步通知云表单,已配置的表单={}   需推送的表单={}", promoteFormIds, potentialCustomer.getPromoteFormId());
        if (StringUtils.isEmpty(potentialCustomer.getPromoteFormId()) || !promoteFormIds.contains(potentialCustomer.getPromoteFormId())) {
            return;
        }
        Map<String, Object> paramMap = Maps.newHashMap();
        paramMap.put("pageId", potentialCustomer.getPromoteFormId());
        paramMap.put("phone", potentialCustomer.getMobile());
        paramMap.put("reserveTime", potentialCustomer.getCreateDateTime().format(formatter));
        String jsonString = JSON.toJSONString(paramMap);
        try {
            CompletableFuture<String> completableMedalInfoFuture = CompletableFuture.supplyAsync(() -> {
                try {
                    log.debug("异步通知云表单,参数={},地址={}", jsonString, GongBaoConfig.promoteFormSendYunUrl);
                    return OkhttpUtils.send(new Request.Builder(), HttpWay.POST,
                            GongBaoConfig.promoteFormSendYunUrl,
                            jsonString, HttpType.JSON).string();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
            log.debug("异步通知云表单,结束,参数：{}   结果：{}", jsonString, completableMedalInfoFuture.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String priority() {
        return CluesPriorityEnum.GBW_WEB.getValue();
    }

    /**
     * 生成客户
     *
     * @param potentialCustomer 线索
     */
    private void getCustomer(PotentialCustomer potentialCustomer) {
        //查询客户手机号是否注册
        UserExtendsVO userExtendsVO = userComponent.getUserExtendsVOByMobile(potentialCustomer.getMobile());

        CustomerAssociatedTypeEnum customerAssociatedTypeEnum = CustomerAssociatedTypeEnum.副关联;
        Customer customer = customerService.getOne(new QueryWrapper<Customer>().lambda().eq(Customer::getMobile, potentialCustomer.getMobile()));
        //客户为空生成新客户。  不为空修改客户更新时间，和用户id
        if (Objects.isNull(customer)) {
            customer = new Customer();
            customer.setMobile(potentialCustomer.getMobile());
            customer.setName(potentialCustomer.getName());
            customer.setState(StringUtils.isNotEmpty(userExtendsVO.getUserId()) ? CustomerStateEnum.已注册.getValue() : CustomerStateEnum.未注册.getValue());
            customer.setUserId(Convert.toStr(userExtendsVO.getUserId()));
            customer.setCreateName(potentialCustomer.getCreateName());
            CustomerSource customerSource = customerSourceService.getOne(new QueryWrapper<CustomerSource>().lambda().eq(CustomerSource::getName, SourceTypeEnum.客户注册.getDesc()));
            if (Objects.nonNull(customerSource)) {
                customer.setCustomerSourceId(customerSource.getId());
            }
            customer.setAllocateDateTime(LocalDateTime.now());
            customerService.save(customer);
            customerAssociatedTypeEnum = CustomerAssociatedTypeEnum.主关联;
        } else {
            Customer customerUpdate = new Customer();
            customerUpdate.setId(customer.getId());
            customerUpdate.setModifyDateTime(LocalDateTime.now());
            //客户存在，用户id为空。绑定userId
            if (StringUtils.isEmpty(customer.getUserId())) {
                customerUpdate.setState(StringUtils.isNotEmpty(userExtendsVO.getUserId()) ? CustomerStateEnum.已注册.getValue() : CustomerStateEnum.未注册.getValue());
                customerUpdate.setUserId(Convert.toStr(userExtendsVO.getUserId()));
            }
            customerService.updateEnhance(customerUpdate);

        }
        //客户和线索关联
        customerAssociatedService.saveCustomerAssociated(potentialCustomer.getId(), customer.getId(), customerAssociatedTypeEnum);
        //有经纪人id   生成客户经纪人关系
        customerAgentService.saveCustomerAgent(customer.getId(), potentialCustomer.getAgentUserId(), potentialCustomer.getCustomerAgentType(), CustomerAgentOperateRecordSourceEnum.链接参数);
        //生成客户备注
        potentialCustomerService.consultCreateCustomerRemark(potentialCustomer, customer.getId());
    }

    /**
     * 分配管家
     *
     * @param potentialCustomer
     * @param groupId
     */
    private void distribution(PotentialCustomer potentialCustomer, String groupId) {
        UserGroupInfo userGroupInfo = rulesComponent.getUserGroupInfo(groupId);
        log.info("自动分配获取管家信息={},参数={}", userGroupInfo, groupId);
        // 查询此经纪人标签 获取经纪人类型
        potentialCustomer.setAllocation(true);
        potentialCustomer.setAgentUserId(userGroupInfo.getUserName());
        potentialCustomer.setAgentGroupId(userGroupInfo.getAgentGroupId());
        potentialCustomer.setAgentUserName(userGroupInfo.getRealName());
        potentialCustomer.setCustomerAgentType(CustomerAgentTypeEnum.自营.getValue());
    }

    /**
     * 快速登录
     * <p>
     * 预约时注册用户
     *
     * @param potentialCustomer
     */
    private String quickLogin(PotentialCustomer potentialCustomer, HttpServletRequest httpServletRequest) {
        String key = "advisoryValidation:" + potentialCustomer.getMobile();
        //查询缓存code
        String verCode = RedisUtils.get(stringRedisTemplate, key);
        HashMap<String, String> headerMap = Maps.newHashMap();
        headerMap.put(UniversalConstant.SOURCECODE, httpServletRequest.getHeader("sourceCode"));
        headerMap.put(UniversalConstant.SOURCE_VALUE_CODE, httpServletRequest.getHeader("sourceValueCode"));
        headerMap.put(UniversalConstant.BUSINESS_DETAILS, httpServletRequest.getHeader("businessDetails"));

        Map<String, Object> map = Maps.newHashMap();
        map.put("mobile", potentialCustomer.getMobile());
        map.put("smsCode", verCode);
        LinkedHashMap areaHash = rpcComponent.rpcQueryInfo(map, headerMap, RpcTypeEnum.QUICK_LOGIN, LinkedHashMap.class);
        log.debug("调用工保通快速登录,手机号={},结果={}", potentialCustomer.getMobile(), areaHash);
        return areaHash.get("obj").toString();
    }

    /**
     * 保存订单来源
     *
     * @param potentialCustomer
     */
    private void orderSource(PotentialCustomer potentialCustomer) {
        //推广表单id为空。不保存来源
        if (StringUtils.isEmpty(potentialCustomer.getPromoteFormId())) {
            return;
        }
        PromoteForm promoteForm = promoteFormService.getById(potentialCustomer.getPromoteFormId());
        if (Objects.isNull(promoteForm)) {
            log.debug("推广表单信息不存在,表单id={}", potentialCustomer.getPromoteFormId());
            return;
        }
        //判断经纪人id或标签code是否为空。来区分是运营投放还是经纪人分享
        String formSource = StringUtils.isNotEmpty(potentialCustomer.getAgentUserId()) ? "经纪人分享-" + potentialCustomer.getAgentUserId() : (StringUtils.isNotEmpty(potentialCustomer.getLabelCode()) ? "运营投放-" + potentialCustomer.getLabelCode() : StringUtils.EMPTY);
        StringBuilder result = new StringBuilder();
        //H5 + 表单类型 + 表单名称 + 经纪人分享_/运营投放_ + 经纪人id/标签code  修改：  字段 H5_{表单类型}_{表单ID}_{表单名称}_{表单来源}_{分享参数}
        result.append("H5-").append(promoteForm.getType().getDesc()).append("-").append(potentialCustomer.getPromoteFormId()).append("-").append(promoteForm.getName());
        if (StringUtils.isNotEmpty(formSource)) {
            result.append("-").append(formSource);
        }
        potentialCustomer.setDescription(result.toString());
    }

    /**
     * rpc调user查看经纪人认证信息
     *
     * @param potentialCustomer 经纪人线索
     * @return 是否认证
     */
    private boolean getAgentCertificationInfo(PotentialCustomer potentialCustomer) {
        //根据手机号获取用户信息
        Map<String, Object> userMap = new HashMap<>(1);
        userMap.put("mobile", potentialCustomer.getMobile());
        UserExtendsVO userExtendsVO = rpcComponent.rpcQueryInfo(userMap, RpcTypeEnum.GET_USER_EXTENDS_ONE, UserExtendsVO.class);

        //根据用户唯一标志获取经纪人认证信息
        Map<String, Object> map = new HashMap<>(1);
        map.put("userId", userExtendsVO.getUserId());
        UserAgentCertification userAgentCertification = rpcComponent.rpcQueryInfo(map, RpcTypeEnum.GET_USER_AGENT_CERTIFICATION, UserAgentCertification.class);
        if (CustomerDataTypeEnum.DATA_TYPE_1.getCode().equals(userAgentCertification.getState())) {
            return true;
        }
        return false;
    }

    /**
     * 根据code rpc获取省市区
     *
     * @param potentialCustomer
     */
    private void setCity(PotentialCustomer potentialCustomer) {
        Optional<Json> everyAreaResult;
        LinkedHashMap<String, Object> areaHash = new LinkedHashMap<>();
        try {
            everyAreaResult = productRpc.findByEveryArea(new HashMap<String, Object>(9) {{
                put("areaCode", Convert.toStr(potentialCustomer.getAreaCode()));
                put("cityCode", Convert.toStr(potentialCustomer.getCityCode()));
                put("provinceCode", Convert.toStr(potentialCustomer.getProvinceCode()));
            }});
            if (everyAreaResult.isPresent() && everyAreaResult.get().getSuccess()) {
                areaHash = (LinkedHashMap<String, Object>) everyAreaResult.get().getObj();
            }
        } catch (Exception e) {
            throw new BusinessException("根据code RPC获取 赋值省市区rpc--/product/area/findByEveryArea调用失败");
        }

        if (CollectionUtils.isEmpty(areaHash)) {
            return;
        }
        potentialCustomer.setAreaName(StringUtils.isNotBlank(Convert.toStr(areaHash.get("areaName"))) ? Convert.toStr(areaHash.get("areaName")) : StringUtils.EMPTY);
        potentialCustomer.setCityName(StringUtils.isNotBlank(Convert.toStr(areaHash.get("cityName"))) ? Convert.toStr(areaHash.get("cityName")) : StringUtils.EMPTY);
        potentialCustomer.setProvinceName(StringUtils.isNotBlank(Convert.toStr(areaHash.get("provinceName"))) ? Convert.toStr(areaHash.get("provinceName")) : StringUtils.EMPTY);
    }

}
