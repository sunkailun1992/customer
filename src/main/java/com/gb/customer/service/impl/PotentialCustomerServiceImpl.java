package com.gb.customer.service.impl;

import cn.hutool.core.convert.Convert;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gb.customer.entity.*;
import com.gb.customer.entity.enums.CustomerAgentGenderEnum;
import com.gb.customer.entity.enums.CustomerAgentStateEnum;
import com.gb.customer.entity.enums.CustomerAgentTypeEnum;
import com.gb.customer.enums.*;
import com.gb.customer.factory.CustomerRemarkFactory;
import com.gb.customer.mapper.*;
import com.gb.customer.service.CustomerAgentService;
import com.gb.customer.service.CustomerAssociatedService;
import com.gb.customer.service.PotentialCustomerService;
import com.gb.rpc.ProductRpc;
import com.gb.rpc.component.ProductComponent;
import com.gb.rpc.component.UserComponent;
import com.gb.rpc.entity.*;
import com.gb.rpc.entity.enums.RpcTypeEnum;
import com.gb.rpc.impl.RpcComponent;
import com.gb.utils.Json;
import com.gb.utils.RedisUtils;
import com.gb.utils.enumeration.ReturnCode;
import com.gb.utils.enumeration.UserTypeEnum;
import com.gb.utils.exception.BusinessException;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 潜在客户表 服务实现类
 * </p>
 *
 * @author 王一飞
 * @since 2021-04-13
 */
@Slf4j
@Service
@Setter(onMethod_ = {@Autowired})
public class PotentialCustomerServiceImpl extends ServiceImpl<PotentialCustomerMapper, PotentialCustomer> implements PotentialCustomerService {


    /**
     * 潜在客户表
     */
    private PotentialCustomerMapper potentialCustomerMapper;
    /**
     * 产品RPC
     */
    private ProductRpc productRpc;

    private CustomerMapper customerMapper;

    private AgentPeopleMapper agentPeopleMapper;
    /**
     * redis操作
     */
    private StringRedisTemplate stringRedisTemplate;

    private CustomerCastInsuranceMapper customerCastInsuranceMapper;

    private PotentialCustomerSourceMapper potentialCustomerSourceMapper;

    private CheckPermissions checkPermissions;

    private RpcComponent rpcComponent;

    private CustomerAgentService customerAgentService;

    private CustomerAssociatedService customerAssociatedService;

    private UserComponent userComponent;

    private ProductComponent productComponent;


    /**
     * 集合条件查询
     *
     * @param potentialCustomer:
     * @return java.util.List<com.entity.PotentialCustomer>
     * @author 王一飞
     * @since 2021-04-13
     */
    @Override
    public List<PotentialCustomer> listEnhance(PotentialCustomer potentialCustomer) {
        QueryWrapper<PotentialCustomer> queryWrapper = new QueryWrapper<>(potentialCustomer);
        query(potentialCustomer, queryWrapper);
        return assignment(potentialCustomerMapper.selectList(queryWrapper));
    }

    /**
     * 分页条件查询
     *
     * @param potentialCustomer:
     * @param page:
     * @return java.util.List<com.entity.PotentialCustomer>
     * @author 王一飞
     * @since 2021-04-13
     */
    @Override
    public IPage pageEnhance(Page page, PotentialCustomer potentialCustomer) {
        QueryWrapper<PotentialCustomer> queryWrapper = new QueryWrapper<>(potentialCustomer);
        query(potentialCustomer, queryWrapper);
        return assignment(potentialCustomer, potentialCustomerMapper.selectPage(page, queryWrapper));
    }

    /**
     * 单条条件查询
     *
     * @param potentialCustomer:
     * @return java.util.List<com.entity.PotentialCustomer>
     * @author 王一飞
     * @since 2021-04-13
     */
    @Override
    public PotentialCustomer getOneEnhance(PotentialCustomer potentialCustomer, HttpServletRequest httpServletRequest) {
//        QueryWrapper<PotentialCustomer> queryWrapper = new QueryWrapper<>(potentialCustomer);
//        query(potentialCustomer, queryWrapper);
        return assignment(potentialCustomerMapper.selectById(potentialCustomer.getId()));
    }

    /**
     * 总数
     *
     * @param potentialCustomer:
     * @return java.util.List<com.entity.PotentialCustomer>
     * @author 王一飞
     * @since 2021-04-13
     */
    @Override
    public Long countEnhance(PotentialCustomer potentialCustomer) {
        QueryWrapper<PotentialCustomer> queryWrapper = new QueryWrapper<>(potentialCustomer);
        query(potentialCustomer, queryWrapper);
        return potentialCustomerMapper.selectCount(queryWrapper);
    }

    /**
     * 新增
     *
     * @param potentialCustomer:
     * @param httpServletRequest
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public Json<String> saveEnhance(PotentialCustomer potentialCustomer, HttpServletRequest httpServletRequest) {
        potentialCustomerMapper.insert(potentialCustomer);
        return new Json(ReturnCode.成功, potentialCustomer.getId());
    }

    /**
     * 修改
     *
     * @param potentialCustomer:
     * @return java.util.List<com.entity.PotentialCustomer>
     * @author 王一飞
     * @since 2021-04-13
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public Boolean updateEnhance(PotentialCustomer potentialCustomer) {
        UpdateWrapper<PotentialCustomer> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", potentialCustomer.getId());
        return potentialCustomerMapper.update(potentialCustomer, updateWrapper) > 0;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public Boolean allotSteward(PotentialCustomer potentialCustomer, HttpServletRequest httpServletRequest) {
        //缓存取出用户
        Map<String, Object> u = RedisUtils.getToken(stringRedisTemplate, httpServletRequest);
        log.debug("[分配管家],分配信息={},当前用户信息={},", potentialCustomer, u);
        if (u != null) {
            potentialCustomer.setModifyName(u.get("name") + "-" + u.get("id"));
        }
        //分配客户线索，同步分配手机号对应的客户信息
        if (PotentialCustomerTypeEnum.CUSTOMER_TYPE_0.getCode().equals(potentialCustomer.getType())) {
            Customer customer = customerMapper.selectOne(new QueryWrapper<Customer>().lambda().eq(Customer::getMobile, potentialCustomer.getMobile()).last("limit 1"));
            if (Objects.nonNull(customer)) {
                Customer newCustomer = new Customer();
                newCustomer.setId(customer.getId());
                newCustomer.setAgentUserId(potentialCustomer.getAgentUserId());
                newCustomer.setAgentUserName(potentialCustomer.getAgentUserName());
                newCustomer.setAgentGroupId(potentialCustomer.getAgentGroupId());
                customerMapper.updateById(newCustomer);
            }
        }
        //此客户所有线索都跟着分配到同一个管家下
        potentialCustomer.setState(1);
        //0：未分配分配经纪人，1：已分配分配经纪人
        potentialCustomer.setAllocation(true);
        potentialCustomer.setProcessingTime(LocalDateTime.now());
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.eq("mobile", potentialCustomer.getMobile());
        updateWrapper.eq("type", potentialCustomer.getType());
        return baseMapper.update(potentialCustomer, updateWrapper) > 0;
    }

    /**
     * 删除
     *
     * @param potentialCustomer:
     * @return java.util.List<com.entity.PotentialCustomer>
     * @author 王一飞
     * @since 2021-04-13
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public Boolean removeEnhance(PotentialCustomer potentialCustomer) {
        QueryWrapper<PotentialCustomer> queryWrapper = new QueryWrapper<>(potentialCustomer);
        return potentialCustomerMapper.delete(queryWrapper) > 0;
    }


    /**
     * @description: 完成咨询
     * 　* @author wangyifei
     * 　* @date 2021/4/25
     */
    @Override
    public Boolean completeConsulting(PotentialCustomer potentialCustomer) {
        //状态（-1：取消，0：待处理，1：处理中，2：已完成，3：关闭）
        potentialCustomer.setState(PotentialCustomerStateEnum.已完成.getCode());
        potentialCustomer.setCompleteTime(LocalDateTime.now());

        UpdateWrapper<PotentialCustomer> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", potentialCustomer.getId());
        return potentialCustomerMapper.update(potentialCustomer, updateWrapper) > 0;
    }


    /**
     * @description: 关闭咨询
     * 　* @author wangyifei
     * 　* @date 2021/4/25
     */
    @Override
    public Boolean closeConsulting(PotentialCustomer potentialCustomer) {
        //状态（-1：取消，0：待处理，1：处理中，2：已完成，3：关闭）
        potentialCustomer.setState(PotentialCustomerStateEnum.关闭.getCode());

        UpdateWrapper<PotentialCustomer> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", potentialCustomer.getId());
        return potentialCustomerMapper.update(potentialCustomer, updateWrapper) > 0;
    }


    /**
     * @description: 取消咨询
     * 　* @author wangyifei
     * 　* @date 2021/4/25
     */
    @Override
    public Object cancelConsulting(PotentialCustomer potentialCustomer) {
        //状态（-1：取消，0：待处理，1：处理中，2：已完成，3：关闭）
        potentialCustomer.setState(PotentialCustomerStateEnum.取消.getCode());

        UpdateWrapper<PotentialCustomer> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", potentialCustomer.getId());
        return potentialCustomerMapper.update(potentialCustomer, updateWrapper) > 0;
    }


    /**
     * @description: 经纪人查询（过滤当前 投保咨询经纪人）
     * 　* @author wangyifei
     * 　* @date 2021/4/25
     */
    @Override
    public Object selectAgentAndFilterCurrentAgent(PotentialCustomer potentialCustomer, HttpServletRequest httpServletRequest) {
        if (StringUtils.isBlank(potentialCustomer.getId())) {
            throw new BusinessException("潜在客户表Id为空！");
        }
        //首先获取 当前信息的 经纪人
        PotentialCustomer customer = getOneEnhance(new PotentialCustomer() {{
            setId(potentialCustomer.getId());
        }}, null);

        if (StringUtils.isBlank(customer.getAgentUserId())) {
            throw new BusinessException("潜在客户表经纪人为空！");
        }

        //调用RPC，获取经纪人
        if (StringUtils.isNotBlank(customer.getAgentUserId()) && Objects.nonNull(customer.getAgentUserId())) {
            //过滤当前 投保咨询经纪人
            return getUserGroupList(UserTypeEnum.业务管家.getTypeCode()).stream().filter(p -> !customer.getAgentUserId().equals(p.getUserName())).collect(Collectors.toList());
        }
        return null;
    }


    /**
     * 增强查询条件
     *
     * @param potentialCustomer:
     * @param queryWrapper:
     * @return void
     * @author 王一飞
     * @since 2021-04-13
     */
    private void query(PotentialCustomer potentialCustomer, QueryWrapper<PotentialCustomer> queryWrapper) {
        /**
         * 排序
         */
        if (potentialCustomer.getCollation() != null && StringUtils.isNotBlank(potentialCustomer.getCollationFields())) {
            if (potentialCustomer.getCollation()) {
                queryWrapper.orderByAsc(potentialCustomer.getCollationFields());
            } else {
                queryWrapper.orderByDesc(potentialCustomer.getCollationFields());
            }
        } else {
            queryWrapper.orderByDesc(potentialCustomer.getCollationFields());
        }

        /**
         * 显示字段
         */
        if (StringUtils.isNotBlank(potentialCustomer.getFields())) {
            queryWrapper.select(potentialCustomer.getFields());
        }

        /**
         * 模糊查询 订单号
         */
        if (StringUtils.isNotBlank(potentialCustomer.getQuery())) {
            queryWrapper.likeRight("id", potentialCustomer.getQuery());
        }

        /**
         * 直接匹配 经纪人
         */
        if (StringUtils.isNotBlank(potentialCustomer.getAgentUserNameQuery())) {
            queryWrapper.likeRight("agent_user_name", potentialCustomer.getAgentUserNameQuery());
        }

        /**
         * 提交时间 区间查询
         */
        String startTime = potentialCustomer.getStartTime();
        String endTime = potentialCustomer.getEndTime();
        if (StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)) {
            queryWrapper.between("create_date_time", startTime, endTime);
        }

        /**
         * 状态（-1：取消，0：待处理，1：处理中，2：已完成，3：关闭）
         */
        Integer state = potentialCustomer.getStateQuery();
        if (Objects.nonNull(state)) {
            //业务 ： 根据状态筛选时，取消的订单归属到已关闭   isCrm区分前后台
            boolean stateCode = PotentialCustomerStateEnum.取消.getCode().equals(state) || PotentialCustomerStateEnum.关闭.getCode().equals(state);
            if (Objects.isNull(potentialCustomer.getIsCrm()) && stateCode) {
                queryWrapper.and(potentialCustomerQueryWrapper -> potentialCustomerQueryWrapper.eq("state", -1).or().eq("state", 3));
            } else {
                queryWrapper.eq("state", state);
            }
        }

        /**
         * 工保网后台和前台个人中心 使用
         *
         * differentCharacterPerspectives: 角色端点（0: 客户端  1:经济端）
         * type（0：产品咨询，1：经纪人咨询）
         * userIdQuery : 咨询用户ID
         */
        if (Objects.nonNull(potentialCustomer.getType()) && Objects.nonNull(potentialCustomer.getDifferentCharacterPerspectives())) {
            //"角色端点（0: 客户端  1:经济端）
            switch (potentialCustomer.getDifferentCharacterPerspectives()) {
                case 0:
                    queryWrapper.eq("mobile", potentialCustomer.getMobileQuery());
                    break;
                case 1:
                    if (StringUtils.isNotBlank(potentialCustomer.getMobileQuery()) || StringUtils.isNotBlank(potentialCustomer.getUserIdQuery())) {
                        queryWrapper.and(broker -> broker.eq("agent_user_id", potentialCustomer.getUserIdQuery()).or().eq("mobile", potentialCustomer.getMobileQuery()));
                    }
                    break;
                default:
                    break;
            }
        }

        if (Objects.nonNull(potentialCustomer.getType())) {
            queryWrapper.eq("type", potentialCustomer.getType());
        }

        //省code
        if (StringUtils.isNotBlank(potentialCustomer.getProvinceCodeQuery())) {
            queryWrapper.eq("province_code", potentialCustomer.getProvinceCodeQuery());
        }
        //市code
        if (StringUtils.isNotBlank(potentialCustomer.getCityCodeQuery())) {
            queryWrapper.eq("city_code", potentialCustomer.getCityCodeQuery());
        }
        //区code
        if (StringUtils.isNotBlank(potentialCustomer.getAreaCodeQuery())) {
            queryWrapper.eq("area_code", potentialCustomer.getAreaCodeQuery());
        }
        //手机号
        if (StringUtils.isNotBlank(potentialCustomer.getMobile())) {
            queryWrapper.eq("mobile", potentialCustomer.getMobile());
        }
        //姓名
        if (StringUtils.isNotBlank(potentialCustomer.getNameQuery())) {
            queryWrapper.likeRight("name", potentialCustomer.getNameQuery());
        }
        //线索来源
        if (StringUtils.isNotBlank(potentialCustomer.getSourceQuery())) {
            queryWrapper.eq("potential_customer_source_id", potentialCustomer.getSourceQuery());
        }

        //模糊查询 企业名称
        if (StringUtils.isNotBlank(potentialCustomer.getEnterpriseNameQuery())) {
            queryWrapper.likeRight("enterprise_name", potentialCustomer.getEnterpriseNameQuery());
        }

        /**
         * 工保网产品咨询/经纪人咨询列表  获取指定线索来源的数据
         */
        if (Objects.isNull(potentialCustomer.getIsCrm())) {
            queryWrapper.and(broker -> broker.eq("potential_customer_source_id", "1").or().eq("potential_customer_source_id", "18").or().eq("potential_customer_source_id", "19"));
        }

        /*------------------------    暂时  crm 2.0    ---------------------------------- */

        /**
         * 客户id
         * 查询此客户下所有线索
         */
        if (StringUtils.isNotEmpty(potentialCustomer.getCustomerId())) {
            List<CustomerAssociated> list = customerAssociatedService.list(new QueryWrapper<CustomerAssociated>().lambda().eq(CustomerAssociated::getCustomerId, potentialCustomer.getCustomerId()));
            if (CollectionUtils.isNotEmpty(list)) {
                List<String> potentialCustomerIds = list.stream().map(CustomerAssociated::getPotentialCustomerId).collect(Collectors.toList());
                queryWrapper.in("id", potentialCustomerIds);
            } else {
                queryWrapper.in("id", "999999");
            }
        }

        /**
         * 指定列表查询
         *
         * 分销列表详情 只展示 客户和经纪人的线索
         * 自营列表详情 展示 客户和经纪人的线索，没分配经纪人的线索
         */
        if (StringUtils.isNotEmpty(potentialCustomer.getCluesPort()) && StringUtils.isNotEmpty(potentialCustomer.getAgentUserIdQuery())) {
            if (CluesPortEnum.分销列表.getCode().equals(potentialCustomer.getCluesPort())) {
                queryWrapper.eq("agent_user_id", potentialCustomer.getAgentUserIdQuery());
            }
            if (CluesPortEnum.自营列表.getCode().equals(potentialCustomer.getCluesPort())) {
                queryWrapper.and(broker -> broker.eq("agent_user_id", potentialCustomer.getAgentUserIdQuery()).or().isNull("agent_user_id"));
            }
            if (CluesPortEnum.线索管理.getCode().equals(potentialCustomer.getCluesPort())) {
                queryWrapper.eq("agent_user_id", potentialCustomer.getAgentUserIdQuery());
            }
        }

        /**
         * 根据经纪人类型 和 个人或团队权限
         * 查询指定经纪人下的线索
         */
        if (CluesPortEnum.线索管理.getCode().equals(potentialCustomer.getCluesPort()) &&
                Objects.nonNull(potentialCustomer.getCustomerAgentType()) && CollectionUtils.isNotEmpty(potentialCustomer.getAgentUserIds())) {
//            //分销: 可见团队和个人下的咨询单
//            if (CustomerAgentTypeEnum.分销.getValue().equals(potentialCustomer.getCustomerAgentType())) {
//                queryWrapper.in("agent_user_id", potentialCustomer.getAgentUserIds());
//            }
//            //自营：可见团队和个人下的咨询单，没有绑定经纪人的咨询单。
//            if (CustomerAgentTypeEnum.自营.getValue().equals(potentialCustomer.getCustomerAgentType())) {
//                queryWrapper.and(broker -> broker.in("agent_user_id", potentialCustomer.getAgentUserIds()).or().isNull("agent_user_id"));
//            }
            // 分销/自营: 可见团队和个人下的咨询单
            queryWrapper.in("agent_user_id", potentialCustomer.getAgentUserIds());
        }
        /*------------------------    暂时  crm 2.0    ---------------------------------- */
    }


    /**
     * 单条，增强返回参数追加
     *
     * @param potentialCustomer:
     * @return PotentialCustomer
     * @author 王一飞
     * @since 2021-04-13
     */
    private PotentialCustomer assignment(PotentialCustomer potentialCustomer) {
        setObjectStatement(potentialCustomer);
        return potentialCustomer;
    }

    /**
     * 分页,增强返回参数追加
     *
     * @param pc
     * @param potentialCustomerList:
     * @return PotentialCustomer
     * @author 王一飞
     * @since 2021-04-13
     */
    private IPage assignment(PotentialCustomer pc, IPage<PotentialCustomer> potentialCustomerList) {
        List<String> agentUserIds = Lists.newArrayList();
        for (PotentialCustomer potentialCustomer : potentialCustomerList.getRecords()) {
            if (StringUtils.isNotBlank(potentialCustomer.getAgentUserId())) {
                agentUserIds.add(potentialCustomer.getAgentUserId());
            }
            potentialCustomer.setCustomerId(pc.getCustomerId());
            //获取产品
            getSpu(potentialCustomer);
            //获取列表标题
            getListTitleName(potentialCustomer);
            //获取省市区名称
            getRegion(potentialCustomer);
        }
        //批量获取经纪人姓名
        getAgentUserName(potentialCustomerList, agentUserIds);
        /**
         * 参数客户id不为空。无需查询对应客户绑定的经纪人
         */
        if (StringUtils.isEmpty(pc.getCustomerId())) {
            for (PotentialCustomer potentialCustomer : potentialCustomerList.getRecords()) {
                //获取线索客户绑定的经纪人
                getClueBindingAgent(potentialCustomer);
            }
        }
        return potentialCustomerList;
    }


    /**
     * 集合,增强返回参数追加
     *
     * @param potentialCustomerList:
     * @return PotentialCustomer
     * @author 王一飞
     * @since 2021-04-13
     */
    private List<PotentialCustomer> assignment(List<PotentialCustomer> potentialCustomerList) {
        for (PotentialCustomer potentialCustomer : potentialCustomerList) {
            //获取产品
            getSpu(potentialCustomer);
            //获取列表标题
            getListTitleName(potentialCustomer);
        }
        return potentialCustomerList;
    }


    /**
     * 手动添加线索
     *
     * @param potentialCustomer
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public Boolean saveCustomerClues(PotentialCustomer potentialCustomer) {
        //校验是否获取到操作人的userid
        String agentUserId = potentialCustomer.getAgentUserId();
        if (StringUtils.isBlank(agentUserId)) {
            throw new BusinessException("所需必要参数缺少");
        }
        //判断此手机号是否已存在
        PotentialCustomer pcEntity = potentialCustomerMapper.selectOne(new QueryWrapper<PotentialCustomer>().lambda().eq(PotentialCustomer::getMobile, potentialCustomer.getMobile()).eq(PotentialCustomer::getType, potentialCustomer.getType()).last("limit 1"));
        if (null != pcEntity) {
            throw new BusinessException("该线索已存在，可联系管理员进行手动分配");
        }
        //经纪人咨询
        if (PotentialCustomerTypeEnum.CUSTOMER_TYPE_1.getCode().equals(potentialCustomer.getType())) {
            AgentPeople agentPeople = agentPeopleMapper.selectOne(new QueryWrapper<AgentPeople>().lambda().eq(AgentPeople::getMobile, potentialCustomer.getMobile()));
            if (null != agentPeople) {
                throw new BusinessException("该手机号已是公司经纪人");
            }
        }
        //默认来源为  手动添加
        String sourceId = SourceTypeEnum.SOURCE_TYPE_17.getCode();
        PotentialCustomerSource potentialCustomerSource = potentialCustomerSourceMapper.selectOne(new QueryWrapper<PotentialCustomerSource>().lambda().in(PotentialCustomerSource::getName, "手动添加"));
        if (null != potentialCustomerSource) {
            sourceId = potentialCustomerSource.getId();
        }
        //获取操作人所在的标签
        String agentGroupId = null;
        List<String> userPermissions = checkPermissions.queryUserGroupByUserId(agentUserId);
        if (CollectionUtils.isNotEmpty(userPermissions)) {
            agentGroupId = userPermissions.get(0);
        }
        //分配allocation分配（0：未分配分配经纪人，1：已分配分配经纪人）
        potentialCustomer.setAllocation(true);
        //状态（-1：取消，0：待处理，1：处理中，2：已完成，3：关闭）
        potentialCustomer.setState(1);
        potentialCustomer.setProcessingTime(LocalDateTime.now());
        potentialCustomer.setSubmitTime(LocalDateTime.now());
        potentialCustomer.setDataType(CustomerDataTypeEnum.DATA_TYPE_1.getCode());
        potentialCustomer.setPotentialCustomerSourceId(sourceId);
        potentialCustomer.setAgentGroupId(agentGroupId);

        return potentialCustomerMapper.insert(potentialCustomer) > 0;
    }

    @Override
    public List<UserGroupInfo> getUserGroupList(String groupId) {
        log.info("[获取用户组信息]groupId={}" + groupId);
        HashMap<String, Object> hashMap = new HashMap<>(2);
        hashMap.put("userTypeValueCode", groupId);
        //除业务管家标签外，其他默认查询已认证的管家
        hashMap.put("queryType", UserTypeEnum.业务管家.getTypeCode().equals(groupId) ? 0 : 1);
        List<UserGroupInfo> list = new ArrayList<>();
        try {
            List<UserLabelInfoVO> userLabelInfos = rpcComponent.rpcQueryList(hashMap, null, RpcTypeEnum.GET_LABEL_USER_TYPE, UserLabelInfoVO.class);
            log.info("RPC获取用户组信息为:" + userLabelInfos);
            if (CollectionUtils.isEmpty(userLabelInfos) || CollectionUtils.isEmpty(userLabelInfos.get(0).getUserBasicInfoVOList())) {
                return list;
            }
            List<UserBasicInfoVO> userBasicInfoVOList = userLabelInfos.get(0).getUserBasicInfoVOList();
            for (UserBasicInfoVO userBasicInfoVO : userBasicInfoVOList) {
                UserGroupInfo userGroupInfo = new UserGroupInfo() {{
                    setUserName(userBasicInfoVO.getUserId());
                    setRealName(userBasicInfoVO.getName());
                    setAgentGroupId(groupId);
                }};
                list.add(userGroupInfo);
            }
            return list;
        } catch (Exception e) {
            throw new BusinessException("RPC获取用户组信息列表异常");
        }
    }

    @Override
    public List<Object> getStewardList(Integer type, String userId) {
        switch (type) {
            case 0:
                QueryWrapper<PotentialCustomer> queryWrapper = new QueryWrapper();
                queryWrapper.select("DISTINCT agent_user_id,agent_user_name").eq("type", 0).isNotNull("agent_user_id").eq(StringUtils.isNotBlank(userId), "agent_user_id", userId).isNotNull("agent_user_name");
                return Collections.singletonList(baseMapper.selectList(queryWrapper));
            case 1:
                QueryWrapper<PotentialCustomer> queryWrapper1 = new QueryWrapper();
                queryWrapper1.select("DISTINCT agent_user_id,agent_user_name").eq("type", 1).eq(StringUtils.isNotBlank(userId), "agent_user_id", userId).isNotNull("agent_user_id").isNotNull("agent_user_name");
                return Collections.singletonList(baseMapper.selectList(queryWrapper1));
            case 2:
                QueryWrapper<Customer> customerQueryWrapper = new QueryWrapper();
                customerQueryWrapper.select("DISTINCT agent_user_id,agent_user_name").eq(StringUtils.isNotBlank(userId), "agent_user_id", userId).isNotNull("agent_user_id").isNotNull("agent_user_name");
                return Collections.singletonList(customerMapper.selectList(customerQueryWrapper));
            case 3:
                QueryWrapper<AgentPeople> agentPeopleQueryWrapper = new QueryWrapper();
                agentPeopleQueryWrapper.select("DISTINCT agent_user_id,agent_user_name").eq(StringUtils.isNotBlank(userId), "agent_user_id", userId).isNotNull("agent_user_id").isNotNull("agent_user_name");
                return Collections.singletonList(agentPeopleMapper.selectList(agentPeopleQueryWrapper));
            case 4:
                QueryWrapper<CustomerCastInsurance> customerCastInsuranceQueryWrapper = new QueryWrapper();
                customerCastInsuranceQueryWrapper.select("DISTINCT agent_user_id,agent_user_name").eq(StringUtils.isNotBlank(userId), "agent_user_id", userId).isNotNull("agent_user_id").isNotNull("agent_user_name");
                return Collections.singletonList(customerCastInsuranceMapper.selectList(customerCastInsuranceQueryWrapper));
            default:
                break;
        }
        return null;
    }

    /**
     * 组装管家信息
     * 获取一级标签下的 所有已认证的经纪人
     *
     * @return
     */
    private List<UserGroupInfo> assemblyStewardList() {
        HashMap<String, Object> hashMap = new HashMap<>(2);
        //一级标签 管家
        hashMap.put("userTypeCode", "4");
        //是否已认证   1是0否
        hashMap.put("queryType", "1");
        List<UserLabelInfoVO> userLabelInfoVOList = rpcComponent.rpcQueryList(hashMap, null, RpcTypeEnum.GET_LABEL_USER_TYPE, UserLabelInfoVO.class);
        //自定义管家数组返回
        List<UserGroupInfo> userGroupInfoList = new ArrayList<>();
        //所有用户放入同一集合
        List<UserLabelInfoVO> userLabelInfos = new ArrayList<>();
        for (UserLabelInfoVO userLabelInfo : userLabelInfoVOList) {
            if (UserTypeEnum.业务管家.getTypeCode().equals(userLabelInfo.getUserTypeValueCode()) || UserTypeEnum.业务管家主管.getTypeCode().equals(userLabelInfo.getUserTypeValueCode()) || UserTypeEnum.业务助理管家.getTypeCode().equals(userLabelInfo.getUserTypeValueCode()) || UserTypeEnum.服务管家主管.getTypeCode().equals(userLabelInfo.getUserTypeValueCode()) || UserTypeEnum.渠道经纪人.getTypeCode().equals(userLabelInfo.getUserTypeValueCode())) {
                userLabelInfos.add(userLabelInfo);
            }
        }
        for (UserLabelInfoVO userLabelInfo : userLabelInfos) {
            //用户信息可能为null  或者 []
            if (CollectionUtils.isNotEmpty(userLabelInfo.getUserBasicInfoVOList())) {
                for (UserBasicInfoVO userBasicInfoVO : userLabelInfo.getUserBasicInfoVOList()) {
                    UserGroupInfo userGroupInfo = new UserGroupInfo() {{
                        setUserName(userBasicInfoVO.getUserId());
                        setRealName(userBasicInfoVO.getName());
                        setPhone(userBasicInfoVO.getMobile());
                        setStewards(userLabelInfo.getUserTypeValueName());
                        setCreateDate(Convert.toStr(userBasicInfoVO.getCreateDateTime()));
                    }};
                    userGroupInfoList.add(userGroupInfo);
                }
            }
        }
        return userGroupInfoList;
    }

    @Override
    public List<UserGroupInfo> queryStewardList(String phone, String name) {
        //获取一级标签为经纪人下的所有已认证的经纪人
        List<UserGroupInfo> list = assemblyStewardList();
        List<UserGroupInfo> newList = new ArrayList<>();
        list.parallelStream().collect(Collectors.groupingBy(UserGroupInfo::getUserName, Collectors.toList())).forEach((id, userGroupInfos) -> {
            userGroupInfos.stream().reduce((a, b) -> new UserGroupInfo() {{
                setUserName(a.getUserName());
                setRealName(a.getRealName());
                setPhone(a.getPhone());
                setStewards(a.getStewards() + "," + b.getStewards());
                setCreateDate(a.getCreateDate());
            }}).ifPresent(newList::add);
        });
        if (StringUtils.isNotBlank(name)) {
            return newList.stream().filter(user -> Objects.equals(user.getRealName(), name)).collect(Collectors.toList());
        }
        if (StringUtils.isNotBlank(phone)) {
            return newList.stream().filter(user -> Objects.equals(user.getPhone(), phone)).collect(Collectors.toList());
        }
        return newList;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public Boolean certificationAgentPeople(AgentPeople agentPeopleBO) {
        //获取要分配的管家
        Map<String, Object> map = new HashMap<>(1);
        map.put("queryType", CustomerDataTypeEnum.DATA_TYPE_1.getCode());
        UserGroupInfo userGroupInfo = rpcComponent.rpcQueryInfo(map, RpcTypeEnum.GET_USER_GROUP_CONDITIONS, UserGroupInfo.class);

        //生成经纪人线索
        PotentialCustomer potentialCustomer = this.getOne(new QueryWrapper<PotentialCustomer>().lambda().eq(PotentialCustomer::getMobile, agentPeopleBO.getMobile()).eq(PotentialCustomer::getType, PotentialCustomerTypeEnum.CUSTOMER_TYPE_1.getCode()).last("limit 1"));
        if (Objects.isNull(potentialCustomer)) {
            potentialCustomer = new PotentialCustomer();
            potentialCustomer.setUserId(agentPeopleBO.getUserId());
            potentialCustomer.setName(agentPeopleBO.getName());
            potentialCustomer.setMobile(agentPeopleBO.getMobile());
            if (Objects.nonNull(userGroupInfo) && Objects.nonNull(userGroupInfo.getUserName())) {
                //分配allocation分配（0：未分配分配经纪人，1：已分配分配经纪人）
                potentialCustomer.setAllocation(true).setAgentUserId(userGroupInfo.getUserName()).setAgentUserName(userGroupInfo.getRealName()).setAgentGroupId(userGroupInfo.getAgentGroupId()).setAgentUserType(AgentUserTypeEnum.AGENT_PEOPLE_TYPE_0.getCode()).setType(PotentialCustomerTypeEnum.CUSTOMER_TYPE_1.getCode()).setPotentialCustomerSourceId(SourceTypeEnum.SOURCE_TYPE_1.getCode()).setDescription("此线索为认证时生成");
            }
            potentialCustomerMapper.insert(potentialCustomer);
        }
        AgentPeople agentPeopleInfo = agentPeopleMapper.selectOne(new QueryWrapper<AgentPeople>().lambda().eq(AgentPeople::getUserId, agentPeopleBO.getUserId()));
        if (Objects.nonNull(agentPeopleInfo)) {
            log.info("[认证经纪人]用户{}已经是经纪人,经纪人信息={}", agentPeopleBO.getUserId(), agentPeopleInfo);
            //已有的经纪人未分配业务管家。随机分配一个
            if (StringUtils.isEmpty(agentPeopleInfo.getAgentUserId())) {
                agentPeopleInfo.setAgentUserId(potentialCustomer.getAgentUserId());
                agentPeopleInfo.setAgentUserName(potentialCustomer.getAgentUserName());
                agentPeopleInfo.setAgentGroupId(potentialCustomer.getAgentGroupId());
                agentPeopleMapper.updateById(agentPeopleInfo);
            }
            return false;
        }
        log.info("[认证经纪人]经纪人线索信息=" + potentialCustomer);
        //生成经纪人信息
        AgentPeople agentPeople = new AgentPeople();
        agentPeople.setUserId(agentPeopleBO.getUserId()).setName(StringUtils.isNotBlank(agentPeopleBO.getName()) ? agentPeopleBO.getName() : potentialCustomer.getName()).setMobile(potentialCustomer.getMobile()).setPotentialCustomerId(potentialCustomer.getId()).setAgentUserId(potentialCustomer.getAgentUserId()).setAgentUserName(potentialCustomer.getAgentUserName()).setAgentGroupId(potentialCustomer.getAgentGroupId());
        return agentPeopleMapper.insert(agentPeople) == 1;
    }

    /**
     * 根据条件查询 客户id
     *
     * @param potentialCustomer
     * @return
     */
    @Override
    public List<String> selectCustomerIds(PotentialCustomer potentialCustomer) {
        QueryWrapper<PotentialCustomer> pcQueryWrapper = new QueryWrapper<>();
        pcQueryWrapper.eq("type", potentialCustomer.getType());
        //企业名称查询
        if (StringUtils.isNotBlank(potentialCustomer.getEnterpriseNameQuery())) {
            pcQueryWrapper.likeRight("enterprise_name", potentialCustomer.getEnterpriseNameQuery());
        }
        //省市区
        if (StringUtils.isNotBlank(potentialCustomer.getProvinceCodeQuery())) {
            pcQueryWrapper.eq("province_code", potentialCustomer.getProvinceCodeQuery());
        }
        if (StringUtils.isNotBlank(potentialCustomer.getCityCodeQuery())) {
            pcQueryWrapper.eq("city_code", potentialCustomer.getCityCodeQuery());
        }
        if (StringUtils.isNotBlank(potentialCustomer.getAreaCodeQuery())) {
            pcQueryWrapper.eq("area_code", potentialCustomer.getAreaCodeQuery());
        }

        List<PotentialCustomer> potentialCustomerList = this.list(pcQueryWrapper);
        //符合条件的线索数组
        if (CollectionUtils.isNotEmpty(potentialCustomerList)) {
            List<String> pcIdList = potentialCustomerList.stream().map(PotentialCustomer::getId).collect(Collectors.toList());
            CustomerAssociated customerAssociated = new CustomerAssociated() {{
                setPotentialCustomerIds(pcIdList);
            }};
            //查询线索客户关联表 获取客户id
            List<CustomerAssociated> customerAssociatedList = customerAssociatedService.listEnhance(customerAssociated);
            if (CollectionUtils.isNotEmpty(customerAssociatedList)) {
                return customerAssociatedList.stream().map(CustomerAssociated::getCustomerId).collect(Collectors.toList());
            }
        }
        return new ArrayList<>();
    }

    /**
     * 咨询生成客户备注信息
     *
     * @param potentialCustomer 咨询单
     * @param customerId        客户id
     */
    @Override
    public void consultCreateCustomerRemark(PotentialCustomer potentialCustomer, String customerId) {
        //判断此客户是否第一次生成咨询单   超过1条不是第一次生成
        QueryWrapper<PotentialCustomer> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mobile", potentialCustomer.getMobile());
        queryWrapper.eq("type", potentialCustomer.getType());
        queryWrapper = StringUtils.isNotEmpty(potentialCustomer.getAgentUserId()) ? queryWrapper.eq("agent_user_id", potentialCustomer.getAgentUserId()) : queryWrapper.isNull("agent_user_id");
        long count = this.count(queryWrapper);
        if (1 == count) {
            //生成备注
            CustomerAgent beanCustomerAgentRemark = CustomerRemarkFactory.getBeanCustomerAgentRemark(customerId, potentialCustomer.getAgentUserId(), potentialCustomer.getName(), CustomerAgentGenderEnum.getDesc(potentialCustomer.getSex()),
                    potentialCustomer.getEnterpriseName(), potentialCustomer.getProvinceCode(), potentialCustomer.getCityCode(), potentialCustomer.getAreaCode(), potentialCustomer.getAddress());
            customerAgentService.saveCustomerRemark(beanCustomerAgentRemark);
        }
    }

    /**
     * 追加返回参数（分页和查询）关联：产品表(product_spu)
     *
     * @param potentialCustomer
     */
    private void setObjectStatement(PotentialCustomer potentialCustomer) {
        if (Objects.isNull(potentialCustomer)) {
            return;
        }
        //获取产品
        getSpu(potentialCustomer);
        //获取省市区名称
        getRegion(potentialCustomer);
    }

    /**
     * 获取列表标题
     * 按照产品名称、咨询险种、项目名称、企业名称 按照此优先级选取非空值，如果4个字段都为空，兜底文案“客户咨询”
     *
     * @param potentialCustomer
     */
    private void getListTitleName(PotentialCustomer potentialCustomer) {
        String listTitleName = StringUtils.EMPTY;
        if (Objects.nonNull(potentialCustomer.getProductSpu())) {
            listTitleName = JSON.parseObject(JSON.toJSONString(potentialCustomer.getProductSpu()), Map.class).get("name").toString();
        }
        if (StringUtils.isEmpty(listTitleName)) {
            if (StringUtils.isNotEmpty(potentialCustomer.getDangerPlantedName())) {
                listTitleName = potentialCustomer.getDangerPlantedName();
            } else if (StringUtils.isNotEmpty(potentialCustomer.getProjectName())) {
                listTitleName = potentialCustomer.getProjectName();
            } else if (StringUtils.isNotEmpty(potentialCustomer.getEnterpriseName())) {
                listTitleName = potentialCustomer.getEnterpriseName();
            } else {
                listTitleName = "客户咨询";
            }
        }
        potentialCustomer.setListTitleName(listTitleName);
    }

    /**
     * 获取spu信息
     *
     * @param potentialCustomer
     */
    private void getSpu(PotentialCustomer potentialCustomer) {
        String spuId = potentialCustomer.getSpuId();
        if (StringUtils.isNotBlank(spuId)) {
            try {
                HashMap<String, String> hashMap = new HashMap<>(1);
                hashMap.put("id", spuId);
                Json json = productRpc.productSpuSelectOne(hashMap);
                if (Objects.nonNull(json.getObj())) {
                    potentialCustomer.setProductSpu(json.getObj());
                }
            } catch (Exception e) {
                throw new BusinessException("RPC产品表单条查询失败！");
            }
        }
    }

    /**
     * 获取此线索的客户 绑定的经纪人
     *
     * @param potentialCustomer
     */
    private void getClueBindingAgent(PotentialCustomer potentialCustomer) {
        potentialCustomer.setIsRelatedAgent(Boolean.FALSE);
        if (StringUtils.isEmpty(potentialCustomer.getAgentUserId())) {
            return;
        }
        //查询此线索客户和经纪人 是否建立关系
        //TODO 临时修改  可能会查出多条，后续改掉出现多条的问题
        List<CustomerAssociated> list = customerAssociatedService.list(new QueryWrapper<CustomerAssociated>().lambda().eq(CustomerAssociated::getPotentialCustomerId, potentialCustomer.getId()).last("limit 1"));
        if (CollectionUtils.isNotEmpty(list)) {
            CustomerAssociated customerAssociated = list.get(0);
            potentialCustomer.setCustomerId(customerAssociated.getCustomerId());
            //查询客户分配的经纪人
            CustomerAgent customerAgent = customerAgentService.getOne(new QueryWrapper<CustomerAgent>().lambda().eq(CustomerAgent::getCustomerId, customerAssociated.getCustomerId()).
                    eq(CustomerAgent::getType, CustomerAgentTypeEnum.自营).eq(CustomerAgent::getState, CustomerAgentStateEnum.主关联));
            if (Objects.nonNull(customerAgent)) {
                potentialCustomer.setAllocationAgent(customerAgent.getAgentUserId());
            }
            //查询客户和咨询单绑定的经纪人 是否有分配关系或关联关系   有：true   无：false
            long count = customerAgentService.count(new QueryWrapper<CustomerAgent>().lambda().eq(CustomerAgent::getCustomerId, customerAssociated.getCustomerId()).eq(CustomerAgent::getAgentUserId, potentialCustomer.getAgentUserId()).
                    and(broker -> broker.eq(CustomerAgent::getState, CustomerAgentStateEnum.主关联).eq(CustomerAgent::getType, CustomerAgentTypeEnum.自营).
                            or().eq(CustomerAgent::getType, CustomerAgentTypeEnum.分销))
            );
            potentialCustomer.setIsRelatedAgent(count > 0);
        }
        //获取经纪人标签
        TeamUserVO teamUserVOInfo = userComponent.getTeamUserVOInfo(potentialCustomer.getAgentUserId());
        potentialCustomer.setAgentUserTypeLabel(StringUtils.isNotEmpty(teamUserVOInfo.getType()) ? teamUserVOInfo.getType() : StringUtils.EMPTY);
    }


    /**
     * 批量获取经纪人名称
     *
     * @param potentialCustomerList 线索集合
     * @param agentUserIds          经纪人id集合
     */
    private void getAgentUserName(IPage<PotentialCustomer> potentialCustomerList, List<String> agentUserIds) {
        if (CollectionUtils.isEmpty(agentUserIds)) {
            return;
        }
        List<UserExtendsVO> userExtendsListByIds = userComponent.getUserExtendsListByIds(agentUserIds);
        if (CollectionUtils.isNotEmpty(userExtendsListByIds)) {
            Map<String, List<UserExtendsVO>> map = userExtendsListByIds.stream().collect(Collectors.groupingBy(UserExtendsVO::getUserId));
            for (PotentialCustomer potentialCustomer : potentialCustomerList.getRecords()) {
                if (CollectionUtils.isNotEmpty(map.get(potentialCustomer.getAgentUserId()))) {
                    UserExtendsVO userExtendsVO = map.get(potentialCustomer.getAgentUserId()).get(0);
                    potentialCustomer.setAgentUserName(userExtendsVO.getName());
                }
            }
        }
    }

    /**
     * 获取对应的省市区名称
     *
     * @param potentialCustomer
     */
    private void getRegion(PotentialCustomer potentialCustomer) {
        if (Objects.isNull(potentialCustomer) || Objects.isNull(potentialCustomer.getProvinceCode())) {
            return;
        }
        Map<String, String> region = productComponent.getRegion(potentialCustomer.getAreaCode(), potentialCustomer.getCityCode(), potentialCustomer.getProvinceCode());
        if (CollectionUtils.isNotEmpty(region)) {
            potentialCustomer.setAreaName(region.get("areaName"));
            potentialCustomer.setCityName(region.get("cityName"));
            potentialCustomer.setProvinceName(region.get("provinceName"));
        }
    }
}
