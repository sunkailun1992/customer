package com.gb.customer.service.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.lang.Validator;
import cn.hutool.core.map.MapUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gb.bean.GongBaoConfig;
import com.gb.customer.dto.CustomerLabelDto;
import com.gb.customer.dto.OfflineCastInsuranceCustomerDto;
import com.gb.customer.entity.*;
import com.gb.customer.entity.enums.*;
import com.gb.customer.entity.query.CustomerAgentQuery;
import com.gb.customer.entity.vo.CustomerVO;
import com.gb.customer.enums.*;
import com.gb.customer.factory.PotentialCustomerFactory;
import com.gb.customer.mapper.CustomerAgentMapper;
import com.gb.customer.mapper.CustomerCastInsuranceMapper;
import com.gb.customer.mapper.CustomerMapper;
import com.gb.customer.service.*;
import com.gb.customer.service.results.CustomerServiceResults;
import com.gb.customer.template.impl.UserMessageService;
import com.gb.mq.crm.BindUserEvent;
import com.gb.mq.crm.InsuredCustomerEvent;
import com.gb.mq.crm.RegistUserEvent;
import com.gb.mq.enums.ChangeTypeEnum;
import com.gb.mq.enums.OperationSourceTypeEnum;
import com.gb.mq.production.InsuranceService;
import com.gb.rpc.component.ProductComponent;
import com.gb.rpc.component.UserComponent;
import com.gb.rpc.entity.*;
import com.gb.rpc.entity.enums.AppCodeEnum;
import com.gb.rpc.entity.enums.RpcTypeEnum;
import com.gb.rpc.entity.enums.TeamUserFormalStateEnum;
import com.gb.rpc.impl.RpcComponent;
import com.gb.util.LocalDateUtil;
import com.gb.utils.Json;
import com.gb.utils.JsonUtil;
import com.gb.utils.RedisUtils;
import com.gb.utils.enumeration.UserTypeEnum;
import com.gb.utils.excel.ExcelExportUtil;
import com.gb.utils.exception.BusinessException;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import io.swagger.annotations.ApiModelProperty;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * <p>
 * 客户表 服务实现类
 * </p>
 *
 * @author lijh
 * @since 2021-06-08
 */
@Slf4j
@Service
@Setter(onMethod_ = {@Autowired})
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements CustomerService {

    /**
     * 客户表
     */
    private CustomerMapper customerMapper;

    private CustomerCastInsuranceMapper customerCastInsuranceMapper;

    private PotentialCustomerService potentialCustomerService;

    private CustomerAssociatedService customerAssociatedService;

    private AgentPeopleService agentPeopleService;

    private RpcComponent rpcComponent;

    private UserMessageService userMessageService;

    private CustomerAgentService customerAgentService;

    private CustomerAgentMapper customerAgentMapper;

    private CustomerSourceService customerSourceService;

    private UserComponent userComponent;


    private CustomerAgentOperateRecordService customerAgentOperateRecordService;

    private ProductComponent productComponent;

    private InsuranceService insuranceService;

    private StringRedisTemplate stringRedisTemplate;

    private CustomerServiceResults customerServiceResults;

    /**
     * 追加code
     */
    private final String additionalCode = "000000";


    /**
     * 集合条件查询
     *
     * @param customer:
     * @return java.util.List<com.entity.Customer>
     * @author lijh
     * @since 2021-06-08
     */
    @Override
    public List<Customer> listEnhance(Customer customer) {
        QueryWrapper<Customer> queryWrapper = new QueryWrapper<>(customer);
        query(customer, queryWrapper);
        return customerServiceResults.assignment(customerMapper.selectList(queryWrapper));
    }


    /**
     * 分页条件查询
     *
     * @param customer:
     * @param page:
     * @return java.util.List<com.entity.Customer>
     * @author lijh
     * @since 2021-06-08
     */
    @Override
    public IPage pageEnhance(Page page, Customer customer) {
        QueryWrapper<Customer> queryWrapper = new QueryWrapper<>(customer);
        //筛选企业名称  省市区code 查询符合条件的客户id( 经纪人备注的信息和线索咨询单的信息 )
        screeningExtend(customer);
        query(customer, queryWrapper);
        //查询list
        Page<Customer> pageDo = customerMapper.selectPage(page, queryWrapper);
        //转VO数据
        Page<CustomerVO> pageVO = customerServiceResults.toPageVO(pageDo);
        return customerServiceResults.assignment(customer, pageVO);
    }

    /**
     * 条件筛选扩展
     * 查询备注表 和 线索表 符合条件的客户
     *
     * @param customer
     */
    private void screeningExtend(Customer customer) {
        if (StringUtils.isNotBlank(customer.getEnterpriseName()) || StringUtils.isNotBlank(customer.getProvinceCode())) {
            List<String> customerIds = new ArrayList<>();
            customerIds.add("99999999");
            CustomerAgentQuery customerAgentQuery = new CustomerAgentQuery() {{
                setProvinceCode(customer.getProvinceCode());
                setCityCode(customer.getCityCode());
                setAreaCode(customer.getAreaCode());
                setEnterpriseName(customer.getEnterpriseName());
                setAgentUserIdQuery(customer.getLoginAccountId());
                setType(CustomerAgentTypeEnum.无);
            }};
            //备注信息的客户id
            List<String> caIdList = customerAgentService.selectCustomerIds(customerAgentQuery);
            customerIds.addAll(caIdList);
            PotentialCustomer potentialCustomer = new PotentialCustomer() {{
                setEnterpriseNameQuery(customer.getEnterpriseName());
                setProvinceCodeQuery(customer.getProvinceCode());
                setCityCodeQuery(customer.getCityCode());
                setAreaCodeQuery(customer.getAreaCode());
                setType(PotentialCustomerTypeEnum.CUSTOMER_TYPE_0.getCode());
            }};
            //客户线索绑定的客户id
            List<String> pcIds = potentialCustomerService.selectCustomerIds(potentialCustomer);
            customerIds.addAll(pcIds);
            customer.setCustomerIds(customerIds);
        }
    }


    /**
     * 单条条件查询
     *
     * @param customer:
     * @return java.util.List<com.entity.Customer>
     * @author lijh
     * @since 2021-06-08
     */
    @Override
    public Customer getOneEnhance(Customer customer) {
        QueryWrapper<Customer> queryWrapper = new QueryWrapper<>(customer);
        query(customer, queryWrapper);
        Customer customer1 = customerMapper.selectOne(queryWrapper);
        if (Objects.nonNull(customer1)) {
            return customer1;
        }
        //客户不存在，则用参数手机号查询生成过的线索咨询单
        PotentialCustomer one = potentialCustomerService.getOne(new QueryWrapper<PotentialCustomer>().lambda().eq(PotentialCustomer::getMobile, customer.getMobile()).eq(PotentialCustomer::getType, PotentialCustomerTypeEnum.CUSTOMER_TYPE_0.getCode()).orderByAsc(PotentialCustomer::getCreateDateTime).last("limit 1"));
        if (Objects.nonNull(one) && StringUtils.isNotBlank(one.getAgentUserId())) {
            customer.setAgentUserId(one.getAgentUserId());
            customer.setAgentUserName(one.getAgentUserName());
            customer.setAgentGroupId(one.getAgentGroupId());
            customer.setAgentUserType(one.getAgentUserType());
            return customer;
        }
        return null;
    }


    /**
     * 总数
     *
     * @param customer:
     * @return java.util.List<com.entity.Customer>
     * @author lijh
     * @since 2021-06-08
     */
    @Override
    public Long countEnhance(Customer customer) {
        QueryWrapper<Customer> queryWrapper = new QueryWrapper<>(customer);
        query(customer, queryWrapper);
        return customerMapper.selectCount(queryWrapper);
    }


    /**
     * 新增
     *
     * @param customer:
     * @return java.util.List<com.entity.Customer>
     * @author lijh
     * @since 2021-06-08
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public Boolean saveEnhance(Customer customer) {
        CustomerSource customerSource = customerSourceService.getOne(new QueryWrapper<CustomerSource>().lambda().eq(CustomerSource::getName, SourceTypeEnum.公海添加.getDesc()));
        if (Objects.isNull(customerSource)) {
            throw new BusinessException("未配置基础来源信息!");
        }
        //获取分配的经纪人类型
        if (StringUtils.isNotEmpty(customer.getAgentUserIdQuery())) {
            TeamUserVO teamUserVO = userComponent.getTeamUserVO(customer.getAgentUserIdQuery());
            //注意点  团队人员里的经纪人类型和用户经纪人的类型  枚举是相反的。   使用名字匹配经纪人类型
            customer.setAgentUserTypeQuery(CustomerAgentTypeEnum.getValue(teamUserVO.getType()));
        }
        //校验是否注册
        checkRegister(customer);

        customer.setCustomerSourceId(customerSource.getId());
        Customer customerOne = customerMapper.selectOne(new QueryWrapper<Customer>().lambda().eq(Customer::getMobile, customer.getMobile()));
        if (Objects.nonNull(customerOne)) {
            //查询此手机号是否分配过经纪人
            CustomerAgent customerAgent = customerAgentService.getOne(new QueryWrapper<CustomerAgent>().lambda().eq(CustomerAgent::getCustomerId, customerOne.getId()).eq(CustomerAgent::getType, CustomerAgentTypeEnum.自营).eq(CustomerAgent::getState, CustomerAgentStateEnum.主关联));
            if (Objects.nonNull(customerAgent)) {
                throw new BusinessException("该手机号码已分配经纪人，请勿重复添加！");
            }
            throw new BusinessException("手机号码已在公海，可在页面直接分配经纪人！");
        }
        customer.setCustomerAgentOperateRecordSourceEnum(CustomerAgentOperateRecordSourceEnum.公海分配);
        customer.setCustomerAgentOperateRecordOperationEnum(CustomerAgentOperateRecordOperationEnum.分配);
        customer.setAllocateDateTime(LocalDateTime.now());
        //生成客户
        customerMapper.insert(customer);
        return saveCustomerRelatedInfo(customer);
    }


    /**
     * 校验客户是否注册
     *
     * @param customer
     */
    private void checkRegister(Customer customer) {
        //查询此手机号是否注册过
        UserVO userVO = userComponent.getOneUserByMobile(customer.getMobile());
        if (Objects.nonNull(userVO) && StringUtils.isNotEmpty(userVO.getId())) {
            //校验已注册的用户标签是否包含经纪人
            List<String> userTypeIds = userVO.getValueList().stream().map(UserTypeValueVO::getUserTypeId).collect(Collectors.toList());
            if (!userTypeIds.contains(AppCodeEnum.NORMAL_USER.getCode())) {
                throw new BusinessException("该手机号码为工作人员账号，请检查输入是否正确！");
            }
            //绑定用户id
            customer.setUserId(userVO.getId());
            customer.setState(CustomerStateEnum.已注册.getValue());
        }
    }


    /**
     * 生成客户相关信息
     *
     * @param customer
     * @return
     */
    private boolean saveCustomerRelatedInfo(Customer customer, List<Map<String, String>> mistake) {
        //todo 根据经纪人和客户id和type查看是否存在
        List<CustomerAgent> customerAgents = customerAgentMapper.selectList(new LambdaQueryWrapper<CustomerAgent>().eq(CustomerAgent::getAgentUserId, customer.getAgentUserIdQuery()).eq(CustomerAgent::getCustomerId, customer.getId()).eq(CustomerAgent::getType, CustomerAgentTypeEnum.无));
        if (org.apache.commons.collections.CollectionUtils.isNotEmpty(customerAgents)) {
            log.error("该用户已经存在经济人!");
            mistake.add(MapUtil.builder(new HashMap<String, String>()).put("mobile", customer.getMobile()).put("mistake", "该用户已经存在经济人!").build());
            return false;
        }

        //添加客户经纪人绑定关系   公海添加客户 只分配自营经纪（防止变动，校验是否为自营经纪人，不是当做分销处理）
        if (StringUtils.isNotEmpty(customer.getAgentUserIdQuery())) {
            CustomerAgent customerAgent = new CustomerAgent();
            customerAgent.setCustomerId(customer.getId());
            customerAgent.setAgentUserId(customer.getAgentUserIdQuery());
            customerAgent.setType(CustomerAgentTypeEnum.getCustomerAgentTypeEnum(customer.getAgentUserTypeQuery()));
            //为自营类型，设置状态为主关联，其他为副关联
            customerAgent.setState(CustomerAgentTypeEnum.自营.equals(customerAgent.getType()) ? CustomerAgentStateEnum.主关联 : CustomerAgentStateEnum.副关联);
            customerAgent.setCreateName(customer.getCreateName());
            customerAgentService.save(customerAgent);
            //操作记录
            customerAgentOperateRecordService.operateRecord(customerAgent.getCustomerId(), customerAgent.getAgentUserId(), customer.getCustomerAgentOperateRecordSourceEnum(), customer.getCustomerAgentOperateRecordOperationEnum(), customer.getCreateName());
        }
        //添加客户备注信息
        CustomerAgent customerAgentRemark = new CustomerAgent();
        customerAgentRemark.setCustomerId(customer.getId());
        customerAgentRemark.setAgentUserId(customer.getLoginAccountId());
        customerAgentRemark.setName(customer.getNameQuery());
        customerAgentRemark.setEnterpriseName(customer.getEnterpriseName());
        customerAgentRemark.setProvinceCode(customer.getProvinceCode());
        customerAgentRemark.setCityCode(customer.getCityCode());
        customerAgentRemark.setAreaCode(customer.getAreaCode());
        customerAgentRemark.setState(CustomerAgentStateEnum.无);
        customerAgentRemark.setType(CustomerAgentTypeEnum.无);
        customerAgentRemark.setDescription(customer.getDescription());
        return customerAgentService.save(customerAgentRemark);
    }


    /**
     * 生成客户相关信息
     *
     * @param customer
     * @return
     */
    private boolean saveCustomerRelatedInfo(Customer customer) {
        //todo 根据经纪人和客户id和type查看是否存在
        List<CustomerAgent> customerAgents = customerAgentMapper.selectList(new LambdaQueryWrapper<CustomerAgent>().eq(CustomerAgent::getAgentUserId, customer.getAgentUserIdQuery()).eq(CustomerAgent::getCustomerId, customer.getId()).eq(CustomerAgent::getType, CustomerAgentTypeEnum.无));
        if (org.apache.commons.collections.CollectionUtils.isNotEmpty(customerAgents)) {
            throw new BusinessException("该用户已经存在经济人");
        }

        //添加客户经纪人绑定关系   公海添加客户 只分配自营经纪（防止变动，校验是否为自营经纪人，不是当做分销处理）
        if (StringUtils.isNotEmpty(customer.getAgentUserIdQuery())) {
            CustomerAgent customerAgent = new CustomerAgent();
            customerAgent.setCustomerId(customer.getId());
            customerAgent.setAgentUserId(customer.getAgentUserIdQuery());
            customerAgent.setType(CustomerAgentTypeEnum.getCustomerAgentTypeEnum(customer.getAgentUserTypeQuery()));
            //为自营类型，设置状态为主关联，其他为副关联
            customerAgent.setState(CustomerAgentTypeEnum.自营.equals(customerAgent.getType()) ? CustomerAgentStateEnum.主关联 : CustomerAgentStateEnum.副关联);
            customerAgent.setCreateName(customer.getCreateName());
            customerAgentService.save(customerAgent);
            //操作记录
            customerAgentOperateRecordService.operateRecord(customerAgent.getCustomerId(), customerAgent.getAgentUserId(), customer.getCustomerAgentOperateRecordSourceEnum(), customer.getCustomerAgentOperateRecordOperationEnum(), customer.getCreateName());
        }
        //添加客户备注信息
        CustomerAgent customerAgentRemark = new CustomerAgent();
        customerAgentRemark.setCustomerId(customer.getId());
        customerAgentRemark.setAgentUserId(customer.getLoginAccountId());
        customerAgentRemark.setName(customer.getNameQuery());
        customerAgentRemark.setEnterpriseName(customer.getEnterpriseName());
        customerAgentRemark.setProvinceCode(customer.getProvinceCode());
        customerAgentRemark.setCityCode(customer.getCityCode());
        customerAgentRemark.setAreaCode(customer.getAreaCode());
        customerAgentRemark.setState(CustomerAgentStateEnum.无);
        customerAgentRemark.setType(CustomerAgentTypeEnum.无);
        customerAgentRemark.setDescription(customer.getDescription());
        return customerAgentService.save(customerAgentRemark);
    }


    /**
     * 分销客户新增
     *
     * @param customer
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public Boolean saveDistributionCustomer(Customer customer) {
        CustomerSource customerSource = customerSourceService.getOne(new QueryWrapper<CustomerSource>().lambda().eq(CustomerSource::getName, SourceTypeEnum.分销添加.getDesc()));
        if (Objects.isNull(customerSource)) {
            throw new BusinessException("未配置基础来源信息!");
        }
        TeamUserVO teamUserVO = userComponent.getTeamUserVO(customer.getAgentUserIdQuery());
        //注意点  团队人员里的经纪人类型和用户经纪人的类型  枚举是相反的。   使用名字匹配经纪人类型
        Integer agentUserTypeQuery = CustomerAgentTypeEnum.getValue(teamUserVO.getType());
        customer.setAgentUserTypeQuery(agentUserTypeQuery);
        if (!Objects.equals(CustomerAgentTypeEnum.分销.getValue(), agentUserTypeQuery)) {
            throw new BusinessException("当前经纪人销售类型不可在此列表添加客户!");
        }
        //校验是否注册
        checkRegister(customer);

        customer.setCustomerSourceId(customerSource.getId());
        List<Customer> customers = customerMapper.selectList(new QueryWrapper<Customer>().lambda().eq(Customer::getMobile, customer.getMobile()));
        //不存在生成客户
        if (CollectionUtils.isNotEmpty(customers)) {
            Customer customerOne = customers.get(0);
            //查询此手机号是否关联过此经纪人
            CustomerAgent customerAgent = customerAgentService.getOne(new QueryWrapper<CustomerAgent>().lambda().eq(CustomerAgent::getCustomerId, customerOne.getId()).eq(CustomerAgent::getAgentUserId, customer.getAgentUserIdQuery()).eq(CustomerAgent::getType, CustomerAgentTypeEnum.分销).eq(CustomerAgent::getState, CustomerAgentStateEnum.副关联));
            if (Objects.nonNull(customerAgent)) {
                throw new BusinessException("该客户已存在列表，请误重复添加！");
            }
            customer.setId(customerOne.getId());
        } else {
            customer.setAllocateDateTime(LocalDateTime.now());
            customerMapper.insert(customer);
        }
        customer.setCustomerAgentOperateRecordSourceEnum(CustomerAgentOperateRecordSourceEnum.分销添加);
        customer.setCustomerAgentOperateRecordOperationEnum(CustomerAgentOperateRecordOperationEnum.关联);
        //添加客户
        return saveCustomerRelatedInfo(customer);
    }

    @Override
    public String importDistributionCustomer(MultipartFile file, HttpServletRequest httpServletRequest, Map<String, Object> user) throws IOException {
        /**
         * 错误数据集合
         */
        List<Map<String, String>> mistake = Lists.newArrayList();
        /**
         * 获得xls数据
         */
        Assert.isTrue(!file.isEmpty() && file.getSize() > 0, "请先上传excel文件");
        ExcelReader excelReader = ExcelUtil.getReader(file.getInputStream());
        //读取为Map列表，默认第一行为标题行，Map中的key为标题，value为标题对应的单元格值。
        List<Map<String, Object>> list = excelReader.readAll();
        if (list.size() > 500) {
            throw new BusinessException("xls数据超过500条!");
        }
        /**
         * xls数据循环
         */
        for (Map<String, Object> map : list) {
            //xls数据
            String name = Convert.toStr(map.get("姓名"));
            String mobile = Convert.toStr(map.get("手机号"));
            String enterpriseName = Convert.toStr(map.get("企业名称"));
            String remark = Convert.toStr(map.get("备注"));
            //客户对象
            Customer customer = new Customer();
            //赋值初始值
            customer.setCreateName(user.get("name") + "-" + user.get("id"));
            customer.setLoginAccountId((String) user.get("id"));
            customer.setAgentUserIdQuery((String) user.get("id"));
            customer.setMobile(mobile);
            customer.setNameQuery(name);
            customer.setEnterpriseName(enterpriseName);
            customer.setDescription(remark);
            //异常数据处理
            if (!Validator.isMobile(mobile)) {
                log.error("手机号不正确!");
                mistake.add(MapUtil.builder(new HashMap<String, String>()).put("mobile", mobile).put("mistake", "手机号不正确!").build());
                continue;
            }
            CustomerSource customerSource = customerSourceService.getOne(new QueryWrapper<CustomerSource>().lambda().eq(CustomerSource::getName, SourceTypeEnum.分销添加.getDesc()));
            if (Objects.isNull(customerSource)) {
                log.error("未配置基础来源信息!");
                mistake.add(MapUtil.builder(new HashMap<String, String>()).put("mobile", mobile).put("mistake", "未配置基础来源信息!").build());
                continue;
            }
            TeamUserVO teamUserVO = userComponent.getTeamUserVO(customer.getAgentUserIdQuery());
            //注意点  团队人员里的经纪人类型和用户经纪人的类型  枚举是相反的。   使用名字匹配经纪人类型
            Integer agentUserTypeQuery = CustomerAgentTypeEnum.getValue(teamUserVO.getType());
            customer.setAgentUserTypeQuery(agentUserTypeQuery);
            if (!Objects.equals(CustomerAgentTypeEnum.分销.getValue(), agentUserTypeQuery)) {
                log.error("当前经纪人销售类型不可在此列表添加客户!");
                mistake.add(MapUtil.builder(new HashMap<String, String>()).put("mobile", mobile).put("mistake", "当前经纪人销售类型不可在此列表添加客户!").build());
                continue;
            }
            //校验是否注册
            if (checkRegister(customer, mistake)) {
                continue;
            }

            customer.setCustomerSourceId(customerSource.getId());
            List<Customer> customers = customerMapper.selectList(new QueryWrapper<Customer>().lambda().eq(Customer::getMobile, customer.getMobile()));
            //不存在生成客户
            if (CollectionUtils.isNotEmpty(customers)) {
                Customer customerOne = customers.get(0);
                //查询此手机号是否关联过此经纪人
                CustomerAgent customerAgent = customerAgentService.getOne(new QueryWrapper<CustomerAgent>().lambda().eq(CustomerAgent::getCustomerId, customerOne.getId()).eq(CustomerAgent::getAgentUserId, customer.getAgentUserIdQuery()).eq(CustomerAgent::getType, CustomerAgentTypeEnum.分销).eq(CustomerAgent::getState, CustomerAgentStateEnum.副关联));
                if (Objects.nonNull(customerAgent)) {
                    log.error("该手机号码已有服务经纪人!");
                    mistake.add(MapUtil.builder(new HashMap<String, String>()).put("mobile", mobile).put("mistake", "该手机号码已有服务经纪人!").build());
                    continue;
                }
                customer.setId(customerOne.getId());
            } else {
                customer.setAllocateDateTime(LocalDateTime.now());
                customerMapper.insert(customer);
            }
            customer.setCustomerAgentOperateRecordSourceEnum(CustomerAgentOperateRecordSourceEnum.分销添加);
            customer.setCustomerAgentOperateRecordOperationEnum(CustomerAgentOperateRecordOperationEnum.关联);

            //添加客户
            Boolean b = saveCustomerRelatedInfo(customer, mistake);
            //异常数据处理
            if (!b) {
                continue;
            }
        }
        if (mistake.size() != 0) {
            /**
             * 错误数据导出
             */
            String title = "分销客户导入错误" + "-" + user.get("name") + "-" + user.get("id") + "-" + new DateTime().toString(DatePattern.PURE_DATETIME_PATTERN);
            String[] header = {"手机号", "错误"};
            String[] columnName = {"mobile", "mistake"};
            Map<String, String> map = new HashMap<>();
            map.put("customer_mistake", "customer_mistake");
            String ossUrl = ExcelExportUtil.excelExportByOos(mistake, title, header, columnName, "xls/customer_mistake/", 50, map);
            return ossUrl;
        } else {
            return null;
        }
    }

    /**
     * 自营客户新增
     *
     * @param customer
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public Boolean saveProprietaryCustomer(Customer customer) {
        CustomerSource customerSource = customerSourceService.getOne(new QueryWrapper<CustomerSource>().lambda().eq(CustomerSource::getName, SourceTypeEnum.自营添加.getDesc()));
        if (Objects.isNull(customerSource)) {
            throw new BusinessException("未配置基础来源信息!");
        }
        TeamUserVO teamUserVO = userComponent.getTeamUserVO(customer.getAgentUserIdQuery());
        //注意点  团队人员里的经纪人类型和用户经纪人的类型  枚举是相反的。   使用名字匹配经纪人类型
        Integer agentUserTypeQuery = CustomerAgentTypeEnum.getValue(teamUserVO.getType());
        customer.setAgentUserTypeQuery(agentUserTypeQuery);
        if (!Objects.equals(CustomerAgentTypeEnum.自营.getValue(), agentUserTypeQuery)) {
            throw new BusinessException("当前经纪人销售类型不可在此列表添加客户!");
        }
        //校验是否注册
        checkRegister(customer);

        customer.setCustomerSourceId(customerSource.getId());
        Customer customerOne = customerMapper.selectOne(new QueryWrapper<Customer>().lambda().eq(Customer::getMobile, customer.getMobile()));
        if (Objects.nonNull(customerOne)) {
            //查询此手机号是否分配过经纪人
            CustomerAgent customerAgent = customerAgentService.getOne(new QueryWrapper<CustomerAgent>().lambda().eq(CustomerAgent::getCustomerId, customerOne.getId()).eq(CustomerAgent::getType, CustomerAgentTypeEnum.自营).eq(CustomerAgent::getState, CustomerAgentStateEnum.主关联));
            if (Objects.nonNull(customerAgent)) {
                throw new BusinessException("该手机号码已有服务经纪人！");
            }
            customer.setId(customerOne.getId());
        } else {
            customer.setAllocateDateTime(LocalDateTime.now());
            customerMapper.insert(customer);
        }
        customer.setCustomerAgentOperateRecordSourceEnum(CustomerAgentOperateRecordSourceEnum.自营添加);
        customer.setCustomerAgentOperateRecordOperationEnum(CustomerAgentOperateRecordOperationEnum.分配);

        //添加客户
        return saveCustomerRelatedInfo(customer);
    }


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
    @Override
    public String importProprietaryCustomer(MultipartFile file, HttpServletRequest httpServletRequest, Map<String, Object> user) throws IOException {
        /**
         * 错误数据集合
         */
        List<Map<String, String>> mistake = Lists.newArrayList();
        /**
         * 获得xls数据
         */
        Assert.isTrue(!file.isEmpty() && file.getSize() > 0, "请先上传excel文件");
        ExcelReader excelReader = ExcelUtil.getReader(file.getInputStream());
        //读取为Map列表，默认第一行为标题行，Map中的key为标题，value为标题对应的单元格值。
        List<Map<String, Object>> list = excelReader.readAll();
        if (list.size() > 500) {
            throw new BusinessException("xls数据超过500条!");
        }
        /**
         * xls数据循环
         */
        for (Map<String, Object> map : list) {
            //xls数据
            String name = Convert.toStr(map.get("姓名"));
            String mobile = Convert.toStr(map.get("手机号"));
            String enterpriseName = Convert.toStr(map.get("企业名称"));
            String remark = Convert.toStr(map.get("备注"));
            //客户对象
            Customer customer = new Customer();
            //赋值初始值
            customer.setCreateName(user.get("name") + "-" + user.get("id"));
            customer.setLoginAccountId((String) user.get("id"));
            customer.setAgentUserIdQuery((String) user.get("id"));
            customer.setMobile(mobile);
            customer.setNameQuery(name);
            customer.setEnterpriseName(enterpriseName);
            customer.setDescription(remark);
            //异常数据处理
            if (!Validator.isMobile(mobile)) {
                log.error("手机号不正确!");
                mistake.add(MapUtil.builder(new HashMap<String, String>()).put("mobile", mobile).put("mistake", "手机号不正确!").build());
                continue;
            }
            CustomerSource customerSource = customerSourceService.getOne(new QueryWrapper<CustomerSource>().lambda().eq(CustomerSource::getName, SourceTypeEnum.自营添加.getDesc()));
            //异常数据处理
            if (Objects.isNull(customerSource)) {
                log.error("未配置基础来源信息!");
                mistake.add(MapUtil.builder(new HashMap<String, String>()).put("mobile", mobile).put("mistake", "未配置基础来源信息!").build());
                continue;
            }

            TeamUserVO teamUserVO = userComponent.getTeamUserVO(customer.getAgentUserIdQuery());
            //注意点  团队人员里的经纪人类型和用户经纪人的类型  枚举是相反的。   使用名字匹配经纪人类型
            Integer agentUserTypeQuery = CustomerAgentTypeEnum.getValue(teamUserVO.getType());
            customer.setAgentUserTypeQuery(agentUserTypeQuery);
            //异常数据处理
            if (!Objects.equals(CustomerAgentTypeEnum.自营.getValue(), agentUserTypeQuery)) {
                log.error("当前经纪人销售类型不可在此列表添加客户!");
                mistake.add(MapUtil.builder(new HashMap<String, String>()).put("mobile", mobile).put("mistake", "当前经纪人销售类型不可在此列表添加客户!").build());
                continue;
            }

            //校验是否注册
            if (checkRegister(customer, mistake)) {
                continue;
            }

            customer.setCustomerSourceId(customerSource.getId());
            Customer customerOne = customerMapper.selectOne(new QueryWrapper<Customer>().lambda().eq(Customer::getMobile, customer.getMobile()));
            if (Objects.nonNull(customerOne)) {
                //查询此手机号是否分配过经纪人
                CustomerAgent customerAgent = customerAgentService.getOne(new QueryWrapper<CustomerAgent>().lambda().eq(CustomerAgent::getCustomerId, customerOne.getId()).eq(CustomerAgent::getType, CustomerAgentTypeEnum.自营).eq(CustomerAgent::getState, CustomerAgentStateEnum.主关联));
                //异常数据处理
                if (Objects.nonNull(customerAgent)) {
                    log.error("该手机号码已有服务经纪人!");
                    mistake.add(MapUtil.builder(new HashMap<String, String>()).put("mobile", mobile).put("mistake", "该手机号码已有服务经纪人!").build());
                    continue;
                }
                customer.setId(customerOne.getId());
            } else {
                customer.setAllocateDateTime(LocalDateTime.now());
                customerMapper.insert(customer);
            }
            customer.setCustomerAgentOperateRecordSourceEnum(CustomerAgentOperateRecordSourceEnum.自营添加);
            customer.setCustomerAgentOperateRecordOperationEnum(CustomerAgentOperateRecordOperationEnum.分配);
            //添加客户
            Boolean b = saveCustomerRelatedInfo(customer, mistake);
            //异常数据处理
            if (!b) {
                continue;
            }
        }
        if (mistake.size() != 0) {
            /**
             * 错误数据导出
             */
            String title = "自营客户导入错误" + "-" + user.get("name") + "-" + user.get("id") + "-" + new DateTime().toString(DatePattern.PURE_DATETIME_PATTERN);
            String[] header = {"手机号", "错误"};
            String[] columnName = {"mobile", "mistake"};
            Map<String, String> map = new HashMap<>();
            map.put("customer_mistake", "customer_mistake");
            String ossUrl = ExcelExportUtil.excelExportByOos(mistake, title, header, columnName, "xls/customer_mistake/", 50, map);
            return ossUrl;
        } else {
            return null;
        }
    }

    private boolean checkRegister(Customer customer, List<Map<String, String>> mistake) {
        //查询此手机号是否注册过
        UserVO userVO = userComponent.getOneUserByMobile(customer.getMobile());
        //异常数据处理
        if (Objects.nonNull(userVO) && StringUtils.isNotEmpty(userVO.getId())) {
            //校验已注册的用户标签是否包含经纪人
            List<String> userTypeIds = userVO.getValueList().stream().map(UserTypeValueVO::getUserTypeId).collect(Collectors.toList());
            if (!userTypeIds.contains(AppCodeEnum.NORMAL_USER.getCode())) {
                log.error("该手机号码为工作人员账号，请检查输入是否正确!");
                mistake.add(MapUtil.builder(new HashMap<String, String>()).put("mobile", customer.getMobile()).put("mistake", "该手机号码为工作人员账号，请检查输入是否正确!").build());
                return true;
            }
            //绑定用户id
            customer.setUserId(userVO.getId());
            customer.setState(CustomerStateEnum.已注册.getValue());
        }
        return false;
    }


    /**
     * 修改
     *
     * @param customer:
     * @return java.util.List<com.entity.Customer>
     * @author lijh
     * @since 2021-06-08
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public Boolean updateEnhance(Customer customer) {
        UpdateWrapper<Customer> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", customer.getId());
        int i = customerMapper.update(customer, updateWrapper);
        return i > 0;
    }


    /**
     * 删除
     *
     * @param customer:
     * @return java.util.List<com.entity.Customer>
     * @author lijh
     * @since 2021-06-08
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public Boolean removeEnhance(Customer customer) {
        QueryWrapper<Customer> queryWrapper = new QueryWrapper<>(customer);
        int i = customerMapper.delete(queryWrapper);
        return i > 0;
    }

    /**
     * 直接投保
     *
     * @param insuredCustomerEvent
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public void saveCustomerInsuredMq(InsuredCustomerEvent insuredCustomerEvent) {
        switch (InsuredUserTypeEnum.getInsuredUserType(insuredCustomerEvent.getUserType())) {
            //用户投保
            case INSURED_USER_TYPE_0:
                Customer customer = baseMapper.selectOne(new QueryWrapper<Customer>().lambda().eq(Customer::getUserId, insuredCustomerEvent.getCastInsuranceUserId()));
                //生成客户信息
                if (Objects.isNull(customer)) {
                    //改动: TODO 客户一定存在
                    log.error("投保生成订单异常->>> 客户不存在  投保用户id={}", insuredCustomerEvent.getCastInsuranceUserId());
                    return;
                }
                insuredCustomerEvent.setCustomerId(customer.getId());
                break;
            //经纪人
            case INSURED_USER_TYPE_1:
            case INSURED_USER_TYPE_3:
                AgentPeople agentPeople = saveAgentPeople(insuredCustomerEvent, insuredCustomerEvent.getAgentUserId());
                insuredCustomerEvent.setCustomerId(agentPeople.getId());
                break;
            default:
                break;
        }
        //生成客户订单关联信息
        saveCustomerCastInsurance(insuredCustomerEvent);
    }

    /**
     * 保存投保  生成线索，客户
     * 客户信息存在，userId可能为空。 所以用投保人手机号查客户信息。并修改userId
     *
     * @param insuredCustomerEvent
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public void saveInsuredCustomerEvent(InsuredCustomerEvent insuredCustomerEvent) {
        switch (InsuredUserTypeEnum.getInsuredUserType(insuredCustomerEvent.getUserType())) {
            //用户投保
            case INSURED_USER_TYPE_0:
                Customer customer = baseMapper.selectOne(new QueryWrapper<Customer>().lambda().eq(Customer::getMobile, insuredCustomerEvent.getMobile()));
                //生成客户信息
                if (Objects.isNull(customer)) {
                    customer = saveInsuredCreateCustomer(insuredCustomerEvent);
                } else {
                    Customer customerUpdate = new Customer();
                    customerUpdate.setId(customer.getId());
                    customerUpdate.setModifyDateTime(LocalDateTime.now());
                    customerUpdate.setState(CustomerStateEnum.已注册.getValue());
                    //客户存在，用户id为空。绑定userId
                    if (StringUtils.isEmpty(customer.getUserId())) {
                        customerUpdate.setUserId(Convert.toStr(insuredCustomerEvent.getCastInsuranceUserId()));
                    }
                    this.updateEnhance(customerUpdate);
                }
                insuredCustomerEvent.setCustomerId(customer.getId());
                //生成客户线索
                savePotentialCustomer(insuredCustomerEvent);
                //生成经纪人信息
                if (StringUtils.isNotEmpty(insuredCustomerEvent.getAgentUserId())) {
                    //生成客户经纪人
                    customerAgentService.saveCustomerAgent(customer.getId(), insuredCustomerEvent.getAgentUserId(), insuredCustomerEvent.getType(), CustomerAgentOperateRecordSourceEnum.链接参数);
                    saveAgentPeople(insuredCustomerEvent, insuredCustomerEvent.getAgentUserId());
                }
                break;
            //经纪人投保
            case INSURED_USER_TYPE_1:
            case INSURED_USER_TYPE_3:
                //生成经纪人线索
                PotentialCustomer potentialCustomer = potentialCustomerService.getOne(new QueryWrapper<PotentialCustomer>().lambda().eq(PotentialCustomer::getMobile, insuredCustomerEvent.getMobile()).eq(PotentialCustomer::getType, PotentialCustomerTypeEnum.CUSTOMER_TYPE_1.getCode()).last("limit 1"));
                if (Objects.nonNull(potentialCustomer)) {
                    return;
                }
                potentialCustomer = getPotentialCustomer(insuredCustomerEvent);
                //生成经纪人信息
                insuredCustomerEvent.setPotentialCustomerId(potentialCustomer.getId());
                saveAgentPeople(insuredCustomerEvent, insuredCustomerEvent.getCastInsuranceUserId());
                break;
            default:
                break;
        }
    }

    /**
     * 生成客户信息
     *
     * @param insuredCustomerEvent
     * @return
     */
    private Customer saveInsuredCreateCustomer(InsuredCustomerEvent insuredCustomerEvent) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(insuredCustomerEvent, customer);
        //客户绑定经纪人暂不取消
        customer.setUserId(insuredCustomerEvent.getCastInsuranceUserId());
        customer.setState(CustomerStateEnum.已注册.getValue());
        CustomerSource customerSource = customerSourceService.getOne(new QueryWrapper<CustomerSource>().lambda().eq(CustomerSource::getName, SourceTypeEnum.客户注册.getDesc()));
        if (Objects.nonNull(customerSource)) {
            customer.setCustomerSourceId(customerSource.getId());
        }
        baseMapper.insert(customer);
        return customer;
    }

    /**
     * 渠道订单生成业务
     *
     * @param insuredCustomerEvent
     * @throws Exception
     */
    @Override
    public void saveGbjCustomerInsuredMq(InsuredCustomerEvent insuredCustomerEvent) {
        log.debug("渠道投保订单生成信息开始...:" + insuredCustomerEvent);
        switch (InsuredUserTypeEnum.getInsuredUserType(insuredCustomerEvent.getUserType())) {
            case INSURED_USER_TYPE_2:
                //渠道普通用户投保
                //渠道经纪人id一定不为空，生成经纪人信息，用户绑定经纪人，再给经纪人分配mq接收的业务管家
                if (StringUtils.isNotEmpty(insuredCustomerEvent.getChannelAgentUserId())) {
                    saveAgentPeople(insuredCustomerEvent, insuredCustomerEvent.getChannelAgentUserId());
                }
                Customer customer = baseMapper.selectOne(new QueryWrapper<Customer>().lambda().eq(Customer::getUserId, insuredCustomerEvent.getCastInsuranceUserId()));
                //生成客户信息
                if (Objects.isNull(customer)) {
                    customer = saveInsuredCreateCustomer(insuredCustomerEvent);
                } else {
                    Customer customerUpdate = new Customer();
                    //客户存在，用户id为空。绑定userId
                    if (StringUtils.isEmpty(customer.getUserId())) {
                        customerUpdate.setUserId(Convert.toStr(insuredCustomerEvent.getCastInsuranceUserId()));
                    }
                    customerUpdate.setState(CustomerStateEnum.已注册.getValue());
                    customerUpdate.setModifyDateTime(LocalDateTime.now());
                    customerUpdate.setId(customer.getId());
                    this.updateEnhance(customerUpdate);
                }
                customerAgentService.saveCustomerAgent(customer.getId(), insuredCustomerEvent.getAgentUserId(), null, CustomerAgentOperateRecordSourceEnum.链接参数);
                insuredCustomerEvent.setCustomerId(customer.getId());
                //生成客户订单关联信息
                saveCustomerCastInsurance(insuredCustomerEvent);
                break;
            case INSURED_USER_TYPE_5:
                // 渠道经纪人投保
                //生成经纪人信息
                AgentPeople agentPeople1 = saveAgentPeople(insuredCustomerEvent, insuredCustomerEvent.getCastInsuranceUserId());
                insuredCustomerEvent.setCustomerId(agentPeople1.getId());
                //生成订单关联
                saveCustomerCastInsurance(insuredCustomerEvent);
                break;
            default:
                break;

        }
    }

    /**
     * 获取客户分配的自营经纪人
     *
     * @param userId: 用户id
     * @param mobile: 客户手机号
     * @return
     */
    @Override
    public Customer selectAgentUser(String userId, String mobile) {
        //根据用户id获取 客户信息
        Customer customer = getOneEnhance(new Customer() {{
            setUserId(userId).setMobile(mobile);
        }});
        if (Objects.isNull(customer)) {
            return null;
        }
        //查询客户分配的经纪人
        CustomerAgent customerAgent = customerAgentService.getOne(new QueryWrapper<CustomerAgent>().lambda().eq(CustomerAgent::getCustomerId, customer.getId()).eq(CustomerAgent::getType, CustomerAgentTypeEnum.自营.getValue()).eq(CustomerAgent::getState, CustomerAgentStateEnum.主关联.getValue()));
        if (Objects.isNull(customerAgent)) {
            return null;
        }
        customer.setAgentUserId(customerAgent.getAgentUserId());
        customer.setAgentUserTypeQuery(customerAgent.getType().getValue());
        Map<String, Object> map = new HashMap<>(1);
        map.put("userId", customerAgent.getAgentUserId());
        UserAgentCertification userAgentCertification = rpcComponent.rpcQueryInfo(map, RpcTypeEnum.GET_USER_AGENT_CERTIFICATION, UserAgentCertification.class);
        customer.setCertificateCode(userAgentCertification.getCertificateCode());
        customer.setAgentUserMobile(userAgentCertification.getMobile());
        customer.setAgentUserName(userAgentCertification.getName());
        return customer;
    }

    /**
     * 查看公海客户详情
     *
     * @param customerId 客户id
     * @param accountId  当前账号id
     * @return
     */
    @Override
    public Map selectOneCustomer(String customerId, String accountId) {
        log.info("经纪人查看公海客户详情，customerId:{},  accountId:{}", customerId, accountId);
        Customer customer = baseMapper.selectById(customerId);
        if (Objects.isNull(customer)) {
            throw new BusinessException("客户信息不存在");
        }
        //返回结果集
        Map<String, Object> resultMap = new HashMap<>(3);
        //用户id
        String userId = customer.getUserId();
        UserVO userVO = new UserVO();
        List<UserInvoice> userInvoiceList = new ArrayList<>();
        List<UserShippingAddress> userShippingAddressList = new ArrayList<>();
        if (StringUtils.isNotEmpty(userId)) {
            //用户基础信息
            userVO = userComponent.getOneUserByUserId(userId);
            //获取地区名称
            getRegion(userVO);
            //用户发票
            Map<String, Object> invoiceMap = new HashMap<>(1);
            invoiceMap.put("userId", userId);
            userInvoiceList = rpcComponent.rpcQueryList(invoiceMap, null, RpcTypeEnum.GET_USER_INVOICE, UserInvoice.class);

            //用户收件地址
            Map<String, Object> shippingAddressMap = new HashMap<>(1);
            shippingAddressMap.put("userId", userId);
            userShippingAddressList = rpcComponent.rpcQueryList(shippingAddressMap, null, RpcTypeEnum.GET_USER_SHIPPING_ADDRESS, UserShippingAddress.class);
        }
        resultMap.put("userInvoiceList", userInvoiceList);
        resultMap.put("userShippingAddressList", userShippingAddressList);
        resultMap.put("userVO", userVO);
        return resultMap;
    }

    /**
     * 获取客户详情信息头部
     *
     * @param customerId 客户id
     * @param accountId
     * @return
     */
    @Override
    public Map selectOneCustomerInfoHead(String customerId, String agentUserId, String accountId) {
        log.info("经纪人查看客户详情头部信息，customerId:{}, agentUserId:{}, accountId:{}", customerId, agentUserId, accountId);
        Customer customer = baseMapper.selectById(customerId);
        if (Objects.isNull(customer)) {
            throw new BusinessException("客户信息不存在");
        }
        //返回结果集
        Map<String, Object> resultMap = new HashMap<>(18);

        //用户基础信息
        UserVO userVO = userComponent.getOneUserByUserId(customer.getUserId());
        resultMap.put("userName", userVO.getName());
        //查询当前账号对此客户的备注信息
        String intentionValue = null;
        String customerStage = null;
        String intentionAgentUserId = accountId;

        //经纪人id不为空  获取指定经纪人对客户的备注( 姓名和客户意向 )
        if (StringUtils.isNotEmpty(agentUserId)) {
            intentionAgentUserId = agentUserId;
        }
        CustomerAgent customerAgent = customerAgentService.getOne(new QueryWrapper<CustomerAgent>().lambda().eq(CustomerAgent::getCustomerId, customerId).eq(CustomerAgent::getAgentUserId, intentionAgentUserId).eq(CustomerAgent::getType, CustomerAgentTypeEnum.无));
        if (Objects.nonNull(customerAgent)) {
            intentionValue = Objects.nonNull(customerAgent.getIntention()) ? customerAgent.getIntention().getDesc() : Strings.EMPTY;
            customerStage = Objects.nonNull(customerAgent.getStage()) ? customerAgent.getStage().getDesc() : Strings.EMPTY;
            resultMap.put("userName", customerAgent.getName());
        }

        //查询客户的分配经纪人
        String allotAgentUser = null;
        String allotAgentUserId = null;
        CustomerAgent one = customerAgentService.getOne(new QueryWrapper<CustomerAgent>().lambda().eq(CustomerAgent::getCustomerId, customerId).eq(CustomerAgent::getState, CustomerAgentStateEnum.主关联).eq(CustomerAgent::getType, CustomerAgentTypeEnum.自营));
        if (Objects.nonNull(one)) {
            UserExtendsVO userExtendsVO = userComponent.getUserExtendsVOByUserId(one.getAgentUserId());
            allotAgentUser = userExtendsVO.getName();
            allotAgentUserId = one.getAgentUserId();
        }

        //查询此客户所有的备注关联信息  获取关联经纪人
        StringBuffer associatedAgent = new StringBuffer();
        List<CustomerAgent> customerAgentList = customerAgentService.list(new QueryWrapper<CustomerAgent>().lambda().eq(CustomerAgent::getCustomerId, customerId).eq(CustomerAgent::getType, CustomerAgentTypeEnum.分销.getValue()));
        if (CollectionUtils.isNotEmpty(customerAgentList)) {
            Map<String, Object> userMap = new HashMap<>(1);
            //拼接关联的经纪人名称
            customerAgentList.forEach(agent -> {
                userMap.put("userId", agent.getAgentUserId());
                UserExtendsVO userExtendsVO = rpcComponent.rpcQueryInfo(userMap, RpcTypeEnum.GET_USER_EXTENDS_ONE, UserExtendsVO.class);
                if (StringUtils.isNotEmpty(userExtendsVO.getName())) {
                    associatedAgent.append(userExtendsVO.getName()).append(",");
                }
            });
            if (StringUtils.isNotEmpty(associatedAgent)) {
                associatedAgent.deleteCharAt(associatedAgent.length() - 1);
            }
        }

        //获取关联的经纪人姓名
        if (StringUtils.isNotEmpty(agentUserId)) {
            UserVO agentUserVo = userComponent.getOneUserByUserId(agentUserId);
            resultMap.put("associatedAgentUserName", agentUserVo.getName());
            resultMap.put("associatedAgentUserId", agentUserId);
        }

        resultMap.put("customerSourceName", customer.getCustomerSourceName());
        resultMap.put("userId", customer.getUserId());
        resultMap.put("customerMobile", customer.getMobile());
        resultMap.put("avatarUrl", userVO.getAvatarUrl());
        resultMap.put("userSource", userVO.getUserSource());
        resultMap.put("userCreateDateTime", userVO.getCreateDateTime());
        resultMap.put("state", customer.getState());
        resultMap.put("customerCreateDateTime", customer.getCreateDateTime());
        resultMap.put("associatedAgent", associatedAgent.toString());
        //备注
        resultMap.put("intentionValue", intentionValue);
        //分配的经纪人
        resultMap.put("allotAgentUser", allotAgentUser);
        resultMap.put("allotAgentUserId", allotAgentUserId);
        resultMap.put("label", customer.getLabel());
        resultMap.put("customerStage", customerStage);

        return resultMap;
    }

    /**
     * 获取客户分配的经纪人
     * <p>
     * 1.带参经纪人：配置团队人员，分销类型，返回分销经纪人。  自营类型，以客户绑定为准
     * 未配置团队人员。或配置了但人员不在职 以客户绑定为准
     * 2.不带参经纪人： 以客户绑定为准
     * 客户绑定就返回经纪人   未绑定返回空
     * 同时返回经纪人类型区分自营和分销
     *
     * @param customer
     * @return
     */
    @Override
    public Customer selectBoundAgentUser(Customer customer) {
        //参数经纪人不为空  查经纪人类型 自营或分销
        if (StringUtils.isNotEmpty(customer.getAgentUserIdQuery())) {
            TeamUserVO teamUserVO = userComponent.getTeamUserVOInfo(customer.getAgentUserIdQuery());
            //配置团队人员   并且人员在职
            if (StringUtils.isNotEmpty(teamUserVO.getType()) && TeamUserFormalStateEnum.在职.getDesc().equals(teamUserVO.getUserFormalStateEnum())) {
                CustomerAgentTypeEnum customerAgentTypeEnum = CustomerAgentTypeEnum.getCustomerAgentTypeEnum(CustomerAgentTypeEnum.getValue(teamUserVO.getType()));
                customer.setAgentUserTypeQuery(customerAgentTypeEnum.getValue());
                //参数经纪人为分销类型  直接返回
                if (customerAgentTypeEnum.getValue().equals(CustomerAgentTypeEnum.分销.getValue())) {
                    return customer;
                }
            } else {
                //未配置或不在职  当不带参处理
                customer.setAgentUserIdQuery(null);
                customer.setAgentUserTypeQuery(null);
            }

        }
        //无参数经纪人，自营类型，未配置团队成员  几种情况 以客户绑定的经纪人为准   未绑定返回参数经纪人
        Customer customerOne = baseMapper.selectOne(new QueryWrapper<Customer>().lambda().eq(Customer::getUserId, customer.getUserId()));
        //客户不存在  直接返回
        if (Objects.isNull(customerOne)) {
            return customer;
        }
        //查询此客户有无分配经纪人  有分配经纪人直接返回
        CustomerAgent customerAgent = customerAgentService.getOne(new QueryWrapper<CustomerAgent>().lambda().eq(CustomerAgent::getCustomerId, customerOne.getId()).eq(CustomerAgent::getType, CustomerAgentTypeEnum.自营.getValue()).eq(CustomerAgent::getState, CustomerAgentStateEnum.主关联.getValue()));
        if (Objects.nonNull(customerAgent)) {
            customerOne.setAgentUserIdQuery(customerAgent.getAgentUserId());
            customerOne.setAgentUserTypeQuery(customerAgent.getType().getValue());
            return customerOne;
        }
        return customer;
    }

    /**
     * 经纪人工作台数据
     *
     * @param agentUserId 经纪人id
     * @return
     */
    @Override
    public Map agentWorkbench(String agentUserId) {
        Map<String, Object> map = new HashMap<>(5);
        TeamUserVO teamUserVoInfo = userComponent.getTeamUserVOInfo(agentUserId);
        //不属于团队成员
        if (StringUtils.isEmpty(teamUserVoInfo.getTeamId())) {
            return map;
        }
        //获取所有父级含本级团队名称
        StringBuffer teamName = new StringBuffer();
        TeamTreeNode parentTeam = userComponent.getParentTeam(teamUserVoInfo.getTeamId());
        if (CollectionUtils.isNotEmpty(parentTeam.getChildren())) {
            getTeamTreeNodeList(parentTeam, teamName);
        }
        //拼接岗位
        teamName.append(teamUserVoInfo.getTeamGroupName()).append("/").append(teamUserVoInfo.getTeamGroupValueName()).append("/");
        if (StringUtils.isNotEmpty(teamName)) {
            teamName.deleteCharAt(teamName.length() - 1);
        } else {
            teamName.append(parentTeam.getName()).append("/");
        }
        map.put("teamName", teamName.toString());
        map.put("personal", teamUserVoInfo.getPersonal());

        //获取统计保费
        List<String> agentUserIds = new ArrayList<>();
        agentUserIds.add(agentUserId);

        CustomerMarketingInsuranceStatisticsQueryParam param = new CustomerMarketingInsuranceStatisticsQueryParam();
        param.setDayStartTime(LocalDateUtil.getDayStartTimeStr());
        param.setDayEndTime(LocalDateUtil.getDayEndTimeStr());
        param.setMonthStartTime(LocalDateUtil.getMonthStartTime());
        param.setMonthEndTime(LocalDateUtil.getMonthEndTime());
        param.setAgentUserIds(agentUserIds);
        HashMap<String, Object> hashMapRpc = new HashMap<>(1);
        hashMapRpc.put("marketingQueryParams", param);
        InsuranceStatisticsVO insuranceStatisticsVO = rpcComponent.rpcQueryInfo(hashMapRpc, RpcTypeEnum.GET_AGENT_STATISTICAL, InsuranceStatisticsVO.class);

        map.put("todayInsurancePremium", insuranceStatisticsVO.getTodayInsurancePremium());
        map.put("monthInsurancePremium", insuranceStatisticsVO.getMonthInsurancePremium());
        map.put("insurancePremiumTotal", insuranceStatisticsVO.getInsurancePremiumTotal());

        map.put("insuranceSettlementTotal", insuranceStatisticsVO.getInsuranceSettlementTotal());
        map.put("todaySettlementPremium", insuranceStatisticsVO.getTodaySettlementPremium());
        map.put("monthSettlementPremium", insuranceStatisticsVO.getMonthSettlementPremium());


        return map;
    }

    /**
     * 批量更改用户标签
     *
     * @param customerLabelDtoList
     */
    @Override
    public void updateCustomerLabel(List<CustomerLabelDto> customerLabelDtoList, boolean batch, boolean clean) {
        if (clean) {
            UpdateWrapper<Customer> updateWrapper = new UpdateWrapper<>();
            updateWrapper.in("label", Lists.newArrayList("活跃客户")).set("label", "流失客户");
            this.update(updateWrapper);
        } else {
            if (batch) {
                String label = customerLabelDtoList.get(0).getLabel();
                List<String> userIdList = customerLabelDtoList.stream().map(CustomerLabelDto::getUserId).collect(Collectors.toList());
                UpdateWrapper<Customer> updateWrapper = new UpdateWrapper<>();
                updateWrapper.in("user_id", userIdList).set("label", label);
                this.update(updateWrapper);
            } else {
                for (CustomerLabelDto customerLabelDto : customerLabelDtoList) {
                    UpdateWrapper<Customer> updateWrapper = new UpdateWrapper<>();
                    updateWrapper.eq("user_id", customerLabelDto.getUserId()).set("label", customerLabelDto.getLabel());
                    this.update(updateWrapper);
                }
            }
        }
    }


    /**
     * 获取拼接所有父级团队名称
     *
     * @param teamTreeNode
     * @param teamName
     * @return
     */
    private TeamTreeNode getTeamTreeNodeList(TeamTreeNode teamTreeNode, StringBuffer teamName) {
        teamName.append(teamTreeNode.getName()).append("/");
        if (CollectionUtils.isNotEmpty(teamTreeNode.getChildren())) {
            return getTeamTreeNodeList(teamTreeNode.getChildren().get(0), teamName);
        } else {
            return teamTreeNode;
        }

    }

    /**
     * 注册生成客户线索
     *
     * @param potentialCustomer
     */
    public void generatePotentialCustomer(PotentialCustomer potentialCustomer) {
        //已咨询过，注册不再生成咨询单
        List<PotentialCustomer> list = potentialCustomerService.list(new QueryWrapper<PotentialCustomer>().lambda().eq(PotentialCustomer::getMobile, potentialCustomer.getMobile()).eq(PotentialCustomer::getType, potentialCustomer.getType()));
        if (CollectionUtils.isNotEmpty(list)) {
            return;
        }
        if (StringUtils.isNotEmpty(potentialCustomer.getAgentUserId())) {
            potentialCustomer.setAgentUserName(userComponent.getOneUserByUserId(potentialCustomer.getAgentUserId()).getName());
        }
        potentialCustomer.setDescription("PC端注册生成");
        potentialCustomer.setState(PotentialCustomerStateEnum.处理中.getCode());
        PotentialCustomerFactory.getBeanPotentialCustomer(potentialCustomer);
        potentialCustomerService.save(potentialCustomer);
        //客户和线索关联
        if (StringUtils.isNotEmpty(potentialCustomer.getCustomerId())) {
            customerAssociatedService.saveCustomerAssociated(potentialCustomer.getId(), potentialCustomer.getCustomerId(), CustomerAssociatedTypeEnum.副关联);
        }
    }

    /**
     * 注册客户
     * <p>
     * 客户存在，判断客户是否绑定自营经纪人，已绑定。线索跟随绑定。 未绑定，查看是否有邀请人。判断邀请人身份，进行绑定或关联。
     * 客户不存在  查看是否有邀请人。判断邀请人身份，进行绑定或关联。
     *
     * @param registUserEvent
     */
    @Override
    public void registerCustomer(RegistUserEvent registUserEvent) {
        PotentialCustomer potentialCustomer = registUserEvent.buildPotentialCustomer();
        //类型（0：用户，1：经纪人
        switch (registUserEvent.getUserType()) {
            case 0:
                //生成客户
                Customer customerOne = this.getOne(new QueryWrapper<Customer>().lambda().eq(Customer::getMobile, potentialCustomer.getMobile()));
                if (Objects.nonNull(customerOne)) {
                    Customer customerUpdate = new Customer();
                    customerUpdate.setState(CustomerStateEnum.已注册.getValue());
                    customerUpdate.setUserId(registUserEvent.getUserId());
                    customerUpdate.setModifyDateTime(LocalDateTime.now());
                    customerUpdate.setId(customerOne.getId());
                    this.updateById(customerUpdate);
                } else {
                    customerOne = new Customer();
                    customerOne.setMobile(potentialCustomer.getMobile());
                    customerOne.setState(CustomerStateEnum.已注册.getValue());
                    customerOne.setUserId(registUserEvent.getUserId());
                    customerOne.setName(potentialCustomer.getName());
                    CustomerSource customerSource = customerSourceService.getOne(new QueryWrapper<CustomerSource>().lambda().eq(CustomerSource::getName, SourceTypeEnum.客户注册.getDesc()));
                    if (Objects.nonNull(customerSource)) {
                        customerOne.setCustomerSourceId(customerSource.getId());
                    }
                    customerOne.setAllocateDateTime(LocalDateTime.now());
                    this.save(customerOne);
                }
                //有经纪人id   生成客户经纪人关系
                customerAgentService.saveCustomerAgent(customerOne.getId(), potentialCustomer.getAgentUserId(), null, CustomerAgentOperateRecordSourceEnum.链接参数);
                potentialCustomer.setCustomerId(customerOne.getId());
                //生成客户线索
                generatePotentialCustomer(potentialCustomer);
                break;
            case 1:
                //经纪人
                generatePotentialCustomer(potentialCustomer);
                break;
            default:
                break;
        }
        //推送用户消息
        userMessageService.sendUserMessage(potentialCustomer.getId(), potentialCustomer.getAgentUserId(), potentialCustomer.getType());
    }

    /**
     * 生成经纪人信息
     *
     * @param insuredCustomerEvent mq接收参数
     * @param userId               用户id
     */
    private AgentPeople saveAgentPeople(InsuredCustomerEvent insuredCustomerEvent, String userId) {
        log.debug("生成经纪人信息,userId={},接收mq数据={}", userId, insuredCustomerEvent);
        AgentPeople agentPeople = agentPeopleService.getOne(new QueryWrapper<AgentPeople>().lambda().eq(AgentPeople::getUserId, userId));
        //为空 生成经纪人信息
        if (Objects.nonNull(agentPeople)) {
            //修改经纪人id为自己
            if (StringUtils.isEmpty(agentPeople.getAgentUserId())) {
                agentPeople.setAgentUserId(Convert.toStr(insuredCustomerEvent.getAgentUserId()));
                agentPeople.setAgentUserName(Convert.toStr(insuredCustomerEvent.getAgentUserName()));
                agentPeople.setAgentGroupId(insuredCustomerEvent.getAgentGroupId());
                agentPeopleService.updateById(agentPeople);
            }
            return agentPeople;
        }
        agentPeople = new AgentPeople();
        InsuredUserTypeEnum insuredUserType = InsuredUserTypeEnum.getInsuredUserType(insuredCustomerEvent.getUserType());
        //根据投保类型生成经纪人信息
        switch (insuredUserType) {
            case INSURED_USER_TYPE_0:
                //普通用户投保
                if (StringUtils.isNotEmpty(insuredCustomerEvent.getAgentUserId())) {
                    agentPeople.setAgentUserId(insuredCustomerEvent.getAgentUserId()).setAgentUserName(insuredCustomerEvent.getAgentUserName()).setUserId(insuredCustomerEvent.getAgentUserId()).setName(insuredCustomerEvent.getAgentUserName()).setMobile(insuredCustomerEvent.getAgentUserPhone()).setDescription("普通用户投保时生成");
                    agentPeopleService.saveEnhance(agentPeople);
                }
                break;
            case INSURED_USER_TYPE_1:
                //经纪人投保
                BeanUtils.copyProperties(insuredCustomerEvent, agentPeople);
                agentPeople.setUserId(insuredCustomerEvent.getCastInsuranceUserId());
                agentPeople.setDescription("经纪人投保时生成");
                agentPeopleService.saveEnhance(agentPeople);
                break;
            case INSURED_USER_TYPE_2:
                //渠道普通用户投保      渠道经纪人id为空就生成经纪人信息，并分配业务管家
                BeanUtils.copyProperties(insuredCustomerEvent, agentPeople);
                agentPeople.setUserId(insuredCustomerEvent.getChannelAgentUserId()).setName(insuredCustomerEvent.getChannelAgentUserName()).setMobile(insuredCustomerEvent.getChannelAgentUserPhone()).setDescription("渠道普通用户投保时生成");
                agentPeopleService.saveEnhance(agentPeople);
                break;
            case INSURED_USER_TYPE_5:
                //渠道经纪人投保
                BeanUtils.copyProperties(insuredCustomerEvent, agentPeople);
                agentPeople.setUserId(insuredCustomerEvent.getCastInsuranceUserId());
                agentPeople.setDescription("渠道经纪人投保时生成");
                agentPeopleService.saveEnhance(agentPeople);
                break;
            default:
                return agentPeople;
        }
        return agentPeople;
    }

    /**
     * 保存客户线索
     */
    private void savePotentialCustomer(InsuredCustomerEvent insuredCustomerEvent) {
        //获取此客户所有线索
        List<CustomerAssociated> customerAssociatedList = new ArrayList<>();
        List<PotentialCustomer> potentialCustomers = potentialCustomerService.list(new QueryWrapper<PotentialCustomer>().lambda().eq(PotentialCustomer::getMobile, insuredCustomerEvent.getMobile()).eq(PotentialCustomer::getType, PotentialCustomerTypeEnum.CUSTOMER_TYPE_0.getCode()));
        //生成客户线索
        PotentialCustomer potentialCustomer = getPotentialCustomer(insuredCustomerEvent);
        //线索和客户做关联
        CustomerAssociated mainCustomerAssociated = new CustomerAssociated();
        mainCustomerAssociated.setPotentialCustomerId(potentialCustomer.getId());
        mainCustomerAssociated.setCustomerId(insuredCustomerEvent.getCustomerId());
        mainCustomerAssociated.setType(CustomerAssociatedTypeEnum.主关联.getValue());
        CustomerAssociated ca = customerAssociatedService.getOne(new QueryWrapper<CustomerAssociated>().lambda().eq(CustomerAssociated::getPotentialCustomerId, potentialCustomer.getId()).eq(CustomerAssociated::getCustomerId, insuredCustomerEvent.getCustomerId()).eq(CustomerAssociated::getType, CustomerAssociatedTypeEnum.主关联.getValue()));
        if (Objects.nonNull(ca)) {
            mainCustomerAssociated.setType(CustomerAssociatedTypeEnum.副关联.getValue());
        }
        customerAssociatedList.add(mainCustomerAssociated);

        List<CustomerAssociated> list = customerAssociatedService.list(new QueryWrapper<CustomerAssociated>().lambda().eq(CustomerAssociated::getCustomerId, insuredCustomerEvent.getCustomerId()));
        List<String> collect = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(list)) {
            collect = list.stream().map(CustomerAssociated::getPotentialCustomerId).collect(Collectors.toList());
        }
        //线索和客户建立绑定
        if (CollectionUtils.isNotEmpty(potentialCustomers)) {
            log.debug("用户投保更改此用户之前所有线索绑定的管家,potentialCustomers={},insuredCustomerEvent={}", potentialCustomers, insuredCustomerEvent);
            for (PotentialCustomer pc : potentialCustomers) {
                //此线索未绑定客户信息，将进行绑定
                if (!collect.contains(pc.getId())) {
                    CustomerAssociated customerAssociated = new CustomerAssociated();
                    customerAssociated.setCustomerId(insuredCustomerEvent.getCustomerId());
                    customerAssociated.setPotentialCustomerId(pc.getId());
                    customerAssociated.setType(CustomerAssociatedTypeEnum.副关联.getValue());
                    customerAssociatedList.add(customerAssociated);
                }
            }
        }
        customerAssociatedService.saveBatch(customerAssociatedList);
        //生成客户备注
        potentialCustomerService.consultCreateCustomerRemark(potentialCustomer, insuredCustomerEvent.getCustomerId());
    }

    /**
     * 生成线索
     *
     * @param insuredCustomerEvent
     * @return
     */
    private PotentialCustomer getPotentialCustomer(InsuredCustomerEvent insuredCustomerEvent) {
        //获取用户类型
        Integer userType = InsuredUserTypeEnum.getUserType(insuredCustomerEvent.getUserType());
        PotentialCustomer potentialCustomer = PotentialCustomerFactory.getBeanPotentialCustomer(new PotentialCustomer());
        BeanUtils.copyProperties(insuredCustomerEvent, potentialCustomer);
        potentialCustomer.setType(userType).setDataType(CustomerDataTypeEnum.DATA_TYPE_2.getCode()).setAllocation(true).setPotentialCustomerSourceId(SourceTypeEnum.SOURCE_TYPE_1.getCode()).setState(PotentialCustomerStateEnum.已完成.getCode()).setDescription(insuredCustomerEvent.getSource() + "_直接投保生成").setSourceType(PotentialCustomerSourceTypeEnum.SOURCE_TYPE_2.getCode());
        potentialCustomerService.save(potentialCustomer);
        //推送用户消息
        userMessageService.sendUserMessageIsDefault(potentialCustomer.getId(), potentialCustomer.getAgentUserId(), potentialCustomer.getType());
        return potentialCustomer;
    }

    /**
     * 生成订单关联信息
     *
     * @param insuredCustomerEvent 接收mq信息
     */
    private void saveCustomerCastInsurance(InsuredCustomerEvent insuredCustomerEvent) {
        //普通用户投保，默认为true。其他均为false      true: 订单type类型设置为客户投保   false: 订单type类型设置为经纪人投保
        boolean userTypeBoolean = InsuredUserTypeEnum.INSURED_USER_TYPE_0.getCode().equals(insuredCustomerEvent.getUserType()) || InsuredUserTypeEnum.INSURED_USER_TYPE_2.getCode().equals(insuredCustomerEvent.getUserType());
        //生成订单 服务管家不做分配逻辑，以mq推送为准
        CustomerCastInsurance customerCastInsurance = new CustomerCastInsurance();
        customerCastInsurance.setCastInsuranceId(insuredCustomerEvent.getCastInsuranceId());
        customerCastInsurance.setCustomerId(insuredCustomerEvent.getCustomerId());
        customerCastInsurance.setType(userTypeBoolean ? PotentialCustomerTypeEnum.CUSTOMER_TYPE_0.getCode() : PotentialCustomerTypeEnum.CUSTOMER_TYPE_1.getCode());
        customerCastInsurance.setAgentUserId(insuredCustomerEvent.getServiceStewardId());
        customerCastInsurance.setAgentUserName(insuredCustomerEvent.getServiceStewardName());
        int ci = customerCastInsuranceMapper.insert(customerCastInsurance);
        if (ci < 1) {
            log.error("保存投保订单关联表失败,订单号={}", insuredCustomerEvent.getCastInsuranceId());
            throw new BusinessException("保存投保订单关联表失败");
        }
    }

    /**
     * 增强查询条件
     *
     * @param customer:
     * @param queryWrapper:
     * @return void
     * @author lijh
     * @since 2021-06-08
     */
    private void query(Customer customer, QueryWrapper<Customer> queryWrapper) {
        /**
         * 排序
         */
        if (customer.getCollation() != null && StringUtils.isNotBlank(customer.getCollationFields())) {
            if (customer.getCollation()) {
                queryWrapper.orderByAsc(customer.getCollationFields());
            } else {
                queryWrapper.orderByDesc(customer.getCollationFields());
            }
        } else {
            queryWrapper.orderByDesc(customer.getCollationFields());
        }

        /**
         * 显示字段
         */
        if (StringUtils.isNotBlank(customer.getFields())) {
            queryWrapper.select(customer.getFields());
        }


        /**
         * 提交时间 区间查询
         */
        String startTime = customer.getStartTime();
        String endTime = customer.getEndTime();
        if (StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)) {
            queryWrapper.between("create_date_time", startTime, endTime);
        }

        /**
         * 手机号
         */
        if (StringUtils.isNotBlank(customer.getMobile())) {
            queryWrapper.eq("mobile", customer.getMobile());
        }
        /**
         * 姓名
         */
        if (StringUtils.isNotBlank(customer.getName())) {
            queryWrapper.eq("name", customer.getName());
        }
        /**
         * 经纪人id
         */
        if (StringUtils.isNotBlank(customer.getUserIdQuery())) {
            queryWrapper.eq("agent_user_id", customer.getUserIdQuery());
        }

        /**
         * 用户id
         */
        if (StringUtils.isNotBlank(customer.getUserId())) {
            queryWrapper.eq("user_id", customer.getUserId());
        }

        /* ---------------------------------- 公海客户列表 --------------------------------------------- */

        /**
         * 公海客户列表筛选
         * 客户经纪人表有主关联的数据-  客户表id notIn (查到有主关联的客户id)  排除掉有主关联的客户
         */
        if (CluesPortEnum.公海列表.getCode().equals(customer.getCustomerListType())) {
            queryWrapper.notInSql("id", "select distinct `customer_id` from `customer_agent` where `type` = 0 and `state` = 0 and `is_delete` = false ");
        }

        /**
         * 是否新客户: 从来未分配过经纪人的客户为新客户
         */
        if (Objects.nonNull(customer.getIsNewCustomer()) && customer.getIsNewCustomer()) {
            queryWrapper.notInSql("id", "select distinct `customer_id` from `customer_agent` where `is_delete` = false and `type` = 0 ");
        }

        /**
         * 更新时间 区间查询
         */
        String modifyStartDateTime = customer.getModifyStartDateTime();
        String modifyEndDateTime = customer.getModifyEndDateTime();
        if (StringUtils.isNotBlank(modifyStartDateTime) && StringUtils.isNotBlank(modifyEndDateTime)) {
            queryWrapper.between("modify_date_time", modifyStartDateTime, modifyEndDateTime);
        }
        /**
         * 客户意向
         */
        if (Objects.nonNull(customer.getIntention())) {
            queryWrapper.inSql("id", "select distinct `customer_id` from `customer_agent` where `type` = 2 and `intention` = " + customer.getIntention() + " and `is_delete` = false and agent_user_id = " + customer.getLoginAccountId());
        }
        /**
         * 是否注册
         */
        if (Objects.nonNull(customer.getStateQuery())) {
            queryWrapper.eq("state", customer.getStateQuery());
        }
        /**
         * 客户来源
         */
        if (Objects.nonNull(customer.getCustomerSourceIdQuery())) {
            queryWrapper.eq("customer_source_id", customer.getCustomerSourceIdQuery());
        }

        /**
         * 手机号
         * 优先获取注册用户
         * 1：已注册就拿userId匹配
         * 2：未注册查客户信息的手机号
         */
        if (Objects.nonNull(customer.getMobileQuery())) {
            //根据手机号获取用户信息
            UserVO userVO = userComponent.getOneUserByUserName(customer.getMobileQuery());
            //已注册
            if (Objects.nonNull(userVO) && StringUtils.isNotBlank(userVO.getId())) {
                queryWrapper.eq("user_id", userVO.getId());
            } else {
                queryWrapper.eq("mobile", customer.getMobileQuery());
            }
        }

        /**
         * 客户id数组
         */
        if (CollectionUtils.isNotEmpty(customer.getCustomerIds())) {
            queryWrapper.in("id", customer.getCustomerIds());
        }

        /**
         * 用户来源code
         */
        if (StringUtils.isNotBlank(customer.getUserSourceCode())) {
            String userId = "";
            Map<String, Object> userMap = new HashMap<>(1);
            userMap.put("sourceCode", customer.getUserSourceCode());
            String redis = customer.getUserSourceCode().equals("GONG_BAO_EGUARANTEE") ? RedisUtils.get(stringRedisTemplate, "USER_ID_GONG_BAO_EGUARANTEE") : null;
            if (StringUtils.isBlank(redis)) {
                List<UserVO> userVOList = rpcComponent.rpcQueryList(userMap, null, RpcTypeEnum.GET_USER_LIST, UserVO.class);
                for (UserVO userVO : userVOList) {
                    userId = com.gb.utils.StringUtils.getJoining(userId, userVO.getId());
                }
            } else {
                userId = redis;
            }
            if (customer.getUserSourceCode().equals("GONG_BAO_EGUARANTEE")) {
                //设置缓存8小时
                RedisUtils.add(stringRedisTemplate, "USER_ID_GONG_BAO_EGUARANTEE", userId, 10, TimeUnit.HOURS);
            }
            if (StringUtils.isNotBlank(userId)) {
                queryWrapper.inSql("user_id", com.gb.utils.StringUtils.in(userId));
            } else {
                queryWrapper.inSql("user_id", "'1'");
            }
        }


        /**
         * 用户注册时间区间查询
         */
        if (StringUtils.isNotBlank(customer.getStartUserCreationTime()) && StringUtils.isNotBlank(customer.getEndUserCreationTime())) {
            String userId = "";
            Map<String, Object> userMap = new HashMap<>();
            userMap.put("createDateTimeStart", customer.getStartUserCreationTime());
            userMap.put("createDateTimeEnd", customer.getEndUserCreationTime());
            List<UserVO> userVOList = rpcComponent.rpcQueryList(userMap, null, RpcTypeEnum.GET_USER_LIST, UserVO.class);
            for (UserVO userVO : userVOList) {
                userId = com.gb.utils.StringUtils.getJoining(userId, userVO.getId());
            }
            if (StringUtils.isNotBlank(userId)) {
                queryWrapper.inSql("user_id", com.gb.utils.StringUtils.in(userId));
            } else {
                queryWrapper.inSql("user_id", "'1'");
            }
        }

        /**
         * 沟通状态
         */
        if (Objects.nonNull(customer.getCommunication())) {
            if (customer.getCommunication()) {
                queryWrapper.inSql("`id`", "select distinct `customer_id` from `customer_communication` where `is_delete` = false");
            }
        }
    }

    /**
     * 用户注销或离职
     *
     * @param bindUserEvent
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public void untieCustomerInfo(BindUserEvent bindUserEvent) {
        TeamUserFormalStateEnum teamUserStateEnum = TeamUserFormalStateEnum.getTeamUserStateEnum(bindUserEvent.getUserFormalStateValue());
        /**
         * 注销/离职：
         *      经纪人：解绑经纪人下的客户，和未支付的订单。
         *      客户：  删除客户信息，线索，绑定关系。
         */
        switch (Objects.requireNonNull(teamUserStateEnum)) {
            //注销操作 newUserName不为空   修改操作 newUserName 为空
            case 注销:
                //普通用户
                if (bindUserEvent.getUserTypeValueCode().contains(UserTypeEnum.前端用户.getTypeCode())) {
                    potentialCustomerService.remove(new QueryWrapper<PotentialCustomer>().eq("mobile", bindUserEvent.getMobile()));
                    Customer customer = this.getOne(new QueryWrapper<Customer>().eq("user_id", bindUserEvent.getUserId()));
                    if (Objects.nonNull(customer)) {
                        this.remove(new QueryWrapper<Customer>().eq("user_id", bindUserEvent.getUserId()));
                        customerAgentService.remove(new QueryWrapper<CustomerAgent>().eq("customer_id", customer.getId()));
                    }
                }
                //经纪人
                if (bindUserEvent.getUserTypeValueCode().contains(UserTypeEnum.经纪人.getTypeCode())) {
                    //解绑客户
                    List<String> customerIds = unbindCustomer(bindUserEvent);
                    insuranceService.sendInsurance(customerIds, bindUserEvent.getUserId(), 0, ChangeTypeEnum.解绑.getCode(), OperationSourceTypeEnum.注销);
                }
                break;
            case 修改:
                //修改线索
                PotentialCustomer potentialCustomer = new PotentialCustomer();
                potentialCustomer.setMobile(bindUserEvent.getNewMobile());
                potentialCustomerService.update(potentialCustomer, new UpdateWrapper<PotentialCustomer>().eq("mobile", bindUserEvent.getMobile()));

                //修改客户信息
                Customer customer = new Customer();
                customer.setMobile(bindUserEvent.getNewMobile());
                this.update(customer, new UpdateWrapper<Customer>().eq("mobile", bindUserEvent.getMobile()).eq("user_id", bindUserEvent.getUserId()));

                //修改经纪人信息
                AgentPeople agentPeople = new AgentPeople();
                agentPeople.setMobile(bindUserEvent.getNewMobile());
                agentPeopleService.update(agentPeople, new UpdateWrapper<AgentPeople>().eq("mobile", bindUserEvent.getMobile()).eq("user_id", bindUserEvent.getUserId()));
                break;
            case 离职:
                //解绑客户
                List<String> customerIds = unbindCustomer(bindUserEvent);
                insuranceService.sendInsurance(customerIds, bindUserEvent.getUserId(), 0, ChangeTypeEnum.解绑.getCode(), OperationSourceTypeEnum.离职);
                break;
            default:
                break;
        }
    }

    /**
     * 修改客户更新时间
     *
     * @param customerId
     */
    @Override
    public void updateModifyDateTime(String customerId) {
        UpdateWrapper<Customer> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("modify_date_time", LocalDateTime.now());
        updateWrapper.eq("id", customerId);
        this.update(updateWrapper);
    }

    /**
     * 批量修改客户更新时间
     *
     * @param customerIds
     */
    @Override
    public void updateModifyDateTime(List<String> customerIds) {
        UpdateWrapper<Customer> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("modify_date_time", LocalDateTime.now());
        updateWrapper.in("id", customerIds);
        this.update(updateWrapper);
    }

    /**
     * 修改客户分配时间
     *
     * @param customerId
     */
    @Override
    public void updateAllocateDateTime(String customerId) {
        UpdateWrapper<Customer> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("allocate_date_time", LocalDateTime.now());
        updateWrapper.set("connection_state", false);
        updateWrapper.eq("id", customerId);
        this.update(updateWrapper);
    }

    /**
     * 批量修改客户分配时间
     *
     * @param customerIds
     */
    @Override
    public void updateAllocateDateTime(List<String> customerIds) {
        UpdateWrapper<Customer> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("allocate_date_time", LocalDateTime.now());
        updateWrapper.set("connection_state", false);
        updateWrapper.in("id", customerIds);
        this.update(updateWrapper);
    }

//    /**
//     * 转化线下单数据为客户
//     *
//     * @param offlineCastInsuranceCustomerDto
//     */
//    @Override
//    public void transformationOfflineCastInsuranceCustomer(OfflineCastInsuranceCustomerDto offlineCastInsuranceCustomerDto) {
//
//    }

    /**
     * 解绑客户
     *
     * @param bindUserEvent
     */
    private List<String> unbindCustomer(BindUserEvent bindUserEvent) {
        List<CustomerAgent> customerAgentList = customerAgentService.list(new QueryWrapper<CustomerAgent>().eq("agent_user_id", bindUserEvent.getUserId()).eq("type", CustomerAgentTypeEnum.自营.getValue()).eq("state", CustomerAgentStateEnum.主关联));
        List<String> customerIds = new ArrayList<>();
        //删除分销关系关联的客户
        customerAgentService.remove(new QueryWrapper<CustomerAgent>().eq("agent_user_id", bindUserEvent.getUserId()).eq("type", CustomerAgentTypeEnum.分销.getValue()));
        List<CustomerAgentOperateRecord> list = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(customerAgentList)) {
            customerAgentList.forEach(customerAgent -> {
                Customer customer = this.getById(customerAgent.getCustomerId());
                customerIds.add(customer.getUserId());
                customerAgent.setState(CustomerAgentStateEnum.副关联);
                //添加操作记录
                CustomerAgentOperateRecord customerAgentOperateRecord = new CustomerAgentOperateRecord();
                customerAgentOperateRecord.setCustomerId(customerAgent.getCustomerId());
                customerAgentOperateRecord.setAgentUserId(customerAgent.getAgentUserId());
                customerAgentOperateRecord.setSource(CustomerAgentOperateRecordSourceEnum.自营解绑);
                customerAgentOperateRecord.setOperation(CustomerAgentOperateRecordOperationEnum.解绑);
                customerAgentOperateRecord.setCreateName("系统生成");
                list.add(customerAgentOperateRecord);
            });
            //修改自营关系绑定的客户  为副关联。客户流入公海
            customerAgentService.updateBatchById(customerAgentList);
            //添加操作记录
            customerAgentOperateRecordService.saveBatch(list);
            //批量修改客户更新时间
            List<String> customerIdList = customerAgentList.stream().map(CustomerAgent::getCustomerId).collect(Collectors.toList());
            this.updateModifyDateTime(customerIdList);
        }
        agentPeopleService.remove(new UpdateWrapper<AgentPeople>().eq("mobile", bindUserEvent.getMobile()).eq("user_id", bindUserEvent.getUserId()));
        return customerIds;
    }


    /**
     * 清洗客户
     * <p>
     * 所有线索存在的手机号转化为客户
     * 区分客户是否注册
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public Object cleaningCustomer(String sourceId) {
        Map<String, Object> userMap = new HashMap<>(1);
        //只查询二级标签为前端用户的数据
        userMap.put("typeValueCode", "average_user");
        List<UserVO> userVOList = rpcComponent.rpcQueryList(userMap, null, RpcTypeEnum.GET_USER_LIST, UserVO.class);

//        List<UserExtendsVO> userExtendsVOS = rpcComponent.rpcQueryList(new HashMap<>(), null, RpcTypeEnum.GET_USER_EXTENDS_LIST, UserExtendsVO.class);
        List<String> userMobiles = userVOList.stream().map(UserVO::getMobile).collect(Collectors.toList());
        log.info("获取所有注册的用户，数量:{}", userVOList.size());
        /**
         * 获取总数
         * 暂时只查询 工保网和400电话线索
         */
//        Page pageTotalQuery = new Page(1, 10);
//        pageTotalQuery.setSearchCount(true);
//        pageTotalQuery.setSize(0);
//        QueryWrapper<PotentialCustomer> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("is_delete", false).eq("type", 0).isNotNull("mobile").eq("potential_customer_source_id", sourceId);
//        queryWrapper.groupBy("mobile");
//        queryWrapper.notInSql("mobile", "select mobile from customer where `is_delete` = false and mobile != '' ").select("mobile");
//        //总数
//        int total = (int) potentialCustomerService.page(pageTotalQuery, queryWrapper).getTotal();
//        //每页查询个数
//        int pageSize = 500;
//        //计算页数
//        int page = (total - 1) / pageSize + 1;
//        log.debug("一共分为" + page + "页");
//        for (int i = 1; i <= page; i++) {
//            List<Customer> customerList = new ArrayList<>();
//            log.debug("已执行到第" + i + "页");
//            Page page1 = new Page(i, pageSize);
//            List<PotentialCustomer> potentialCustomerList = potentialCustomerService.page(page1, queryWrapper).getRecords();
//            for (PotentialCustomer potentialCustomer : potentialCustomerList) {
//                Customer customer = new Customer();
//                customer.setMobile(potentialCustomer.getMobile());
//                //写死指定线索已绑定过经纪人 生成客户时重新分配给 指定经纪人辛志鹏
//                customer.setAgentUserId("698124145");
//                customer.setAgentUserType(0);
//                if (SourceTypeEnum.SOURCE_TYPE_1.getCode().equals(potentialCustomer.getPotentialCustomerSourceId())) {
//                    customer.setCustomerSourceId("1565239678057017345");
//                } else if (SourceTypeEnum.SOURCE_TYPE_16.getCode().equals(potentialCustomer.getPotentialCustomerSourceId())) {
//                    customer.setCustomerSourceId("1565239621727514626");
//                } else {
//                    customer.setCustomerSourceId("1565239648621391873");
//                }
//                if (userMobiles.contains(potentialCustomer.getMobile())) {
//                    customer.setState(1);
//                    UserVO userVO = userVOList.stream().filter(user -> Objects.equals(user.getMobile(), potentialCustomer.getMobile())).findAny().get();
//                    customer.setUserId(userVO.getId());
//                }
//                customerList.add(customer);
//            }
//            customerService.saveBatch(customerList);
//            log.info("需要新增的客户->>>i页: {}", customerList.size());
//            customerList.clear();
//        }

        /**
         * 已注册用户但未生成客户
         */
        List<Customer> list = new ArrayList<>();
        List<String> listMobile = this.list(new QueryWrapper<Customer>().isNotNull("mobile").select("mobile")).stream().map(Customer::getMobile).collect(Collectors.toList());
        for (UserVO userVO : userVOList) {
            if (!listMobile.contains(userVO.getMobile())) {
                Customer customer = new Customer();
                customer.setMobile(userVO.getMobile());
                customer.setCustomerSourceId("1565239678057017345");
                customer.setState(1);
                customer.setUserId(userVO.getId());
                list.add(customer);
            }
        }
        this.saveBatch(list);

        return list.size();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public void cleaningCustomerAssociated() {
        /**
         * 客户总数
         */
        Page pageTotalQuery = new Page(1, 10);
        pageTotalQuery.setSearchCount(Boolean.TRUE);
        pageTotalQuery.setSize(0);
        QueryWrapper<Customer> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_delete", Boolean.FALSE);
        //总数
        int total = (int) this.page(pageTotalQuery, queryWrapper).getTotal();
        //每页查询个数
        int pageSize = 100;
        //计算页数
        int page = (total - 1) / pageSize + 1;
        log.debug("客户查询一共分为" + page + "页");
        for (int i = 1; i <= page; i++) {
            List<CustomerAssociated> customerAssociatedList = new ArrayList<>();

            Page page1 = new Page(i, pageSize);
            List<Customer> customerList = this.page(page1, queryWrapper).getRecords();
            for (Customer customer : customerList) {
                QueryWrapper<PotentialCustomer> pcQueryWrapper = new QueryWrapper<>();
                pcQueryWrapper.eq("is_delete", Boolean.FALSE).eq("type", 0).isNotNull("mobile").eq("mobile", customer.getMobile());
                pcQueryWrapper.notInSql("id", "SELECT DISTINCT potential_customer_id FROM customer_associated where customer_id =" + customer.getId());
                List<PotentialCustomer> list = potentialCustomerService.list(pcQueryWrapper);
                if (CollectionUtils.isNotEmpty(list)) {
                    for (PotentialCustomer potentialCustomer : list) {
                        CustomerAssociated customerAssociated = new CustomerAssociated();
                        customerAssociated.setCustomerId(customer.getId());
                        customerAssociated.setPotentialCustomerId(potentialCustomer.getId());
                        customerAssociated.setType(0);
                        customerAssociatedList.add(customerAssociated);
                    }
                }
            }
            customerAssociatedService.saveBatch(customerAssociatedList);
            log.info("需要新增的客户线索关联->>>第{}页:存储数量{}", i, customerAssociatedList.size());
            customerAssociatedList.clear();
        }
    }

    /**
     * 清洗 填充客户经纪人表
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public void cleaningCustomerAgent(Integer agentUserType) {
        //分配记录
        List<CustomerAgentOperateRecord> operateRecordList = new ArrayList<>();

        if (agentUserType.equals(1)) {
            //渠道经纪人客户 改为分销类型
            List<Customer> customerList1 = baseMapper.selectList(new QueryWrapper<Customer>().lambda().eq(Customer::getAgentUserType, AgentUserTypeEnum.AGENT_PEOPLE_TYPE_2.getCode()).isNotNull(Customer::getAgentUserId));
            if (CollectionUtils.isNotEmpty(customerList1)) {
                List<CustomerAgent> list = new ArrayList<>();
                for (Customer customer : customerList1) {
                    CustomerAgent customerAgent = new CustomerAgent();
                    customerAgent.setCustomerId(customer.getId());
                    customerAgent.setAgentUserId(customer.getAgentUserId());
                    customerAgent.setType(CustomerAgentTypeEnum.分销);
                    customerAgent.setState(CustomerAgentStateEnum.副关联);
                    list.add(customerAgent);

                    CustomerAgentOperateRecord customerAgentOperateRecord = new CustomerAgentOperateRecord();
                    customerAgentOperateRecord.setCustomerId(customer.getId());
                    customerAgentOperateRecord.setAgentUserId(customer.getAgentUserId());
                    customerAgentOperateRecord.setSource(CustomerAgentOperateRecordSourceEnum.链接参数);
                    customerAgentOperateRecord.setOperation(CustomerAgentOperateRecordOperationEnum.关联);
                    customerAgentOperateRecord.setCreateName("系统生成");
                    operateRecordList.add(customerAgentOperateRecord);
                }
                List<String> collect = list.stream().map(CustomerAgent::getCustomerId).collect(Collectors.toList());
                customerAgentService.saveBatch(list);
                log.info("清洗客户经纪人-分销类型数据-客户id：{}", collect);
            }
        }
        Integer type = 2;
        if (agentUserType.equals(type)) {
            //非渠道经纪人客户 改为自营关系 TODO 暂时只绑定渠道经纪人的绑定关系
            List<Customer> customerList2 = baseMapper.selectList(new QueryWrapper<Customer>().lambda().ne(Customer::getAgentUserType, AgentUserTypeEnum.AGENT_PEOPLE_TYPE_2.getCode()).isNotNull(Customer::getAgentUserId));
            if (CollectionUtils.isNotEmpty(customerList2)) {
                List<CustomerAgent> list = new ArrayList<>();
                for (Customer customer : customerList2) {
                    CustomerAgent customerAgent = new CustomerAgent();
                    customerAgent.setCustomerId(customer.getId());
                    customerAgent.setAgentUserId(customer.getAgentUserId());
                    customerAgent.setType(CustomerAgentTypeEnum.自营);
                    customerAgent.setState(CustomerAgentStateEnum.主关联);
                    list.add(customerAgent);

                    CustomerAgentOperateRecord customerAgentOperateRecord = new CustomerAgentOperateRecord();
                    customerAgentOperateRecord.setCustomerId(customer.getId());
                    customerAgentOperateRecord.setAgentUserId(customer.getAgentUserId());
                    customerAgentOperateRecord.setSource(CustomerAgentOperateRecordSourceEnum.链接参数);
                    customerAgentOperateRecord.setOperation(CustomerAgentOperateRecordOperationEnum.分配);
                    customerAgentOperateRecord.setCreateName("系统生成");
                    operateRecordList.add(customerAgentOperateRecord);
                }
                List<String> collect = list.stream().map(CustomerAgent::getCustomerId).collect(Collectors.toList());
                customerAgentService.saveBatch(list);
                log.info("清洗客户经纪人-自营类型数据-客户id：{}", collect);
            }
        }
        //分配记录
        customerAgentOperateRecordService.saveBatch(operateRecordList);
    }

    /**
     * 已注册改为未注册
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public void updateCustomerState() {
        Map<String, Object> userMap = new HashMap<>(1);
        //只查询二级标签为前端用户的数据
        userMap.put("typeValueCode", "average_user");
        List<UserVO> userVOList = rpcComponent.rpcQueryList(userMap, null, RpcTypeEnum.GET_USER_LIST, UserVO.class);

        List<String> userIds = userVOList.stream().map(UserVO::getId).collect(Collectors.toList());

        List<Customer> list = new ArrayList<>();
        List<Customer> customerList = baseMapper.selectList(new QueryWrapper<Customer>().lambda().eq(Customer::getState, CustomerStateEnum.已注册.getValue()));
        if (CollectionUtils.isNotEmpty(customerList)) {
            for (Customer customer : customerList) {
                if (!userIds.contains(customer.getUserId())) {
                    Customer customer1 = new Customer();
                    customer1.setState(0);
                    customer1.setUserId(null);
                    customer1.setId(customer.getId());
                    list.add(customer1);
                }
            }
        }

        /**
         * 未注册  改为已注册
         */
        List<Customer> customerList2 = baseMapper.selectList(new QueryWrapper<Customer>().lambda().eq(Customer::getState, CustomerStateEnum.未注册.getValue()));
        if (CollectionUtils.isNotEmpty(customerList2)) {
            for (Customer customer : customerList2) {
                if (userIds.contains(customer.getUserId())) {
                    Customer customer1 = new Customer();
                    customer1.setState(1);
                    UserVO userVO = userVOList.stream().filter(user -> Objects.equals(user.getId(), customer.getUserId())).findAny().get();
                    customer1.setUserId(userVO.getId());
                    customer1.setId(customer.getId());
                    list.add(customer1);
                }
            }
        }
        List<String> collect = list.stream().map(Customer::getId).collect(Collectors.toList());
        this.updateBatchById(list);
        log.info("清洗客户注册状态-客户id：{}", collect);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public void updateCustomerStateByMobile() {
        Map<String, Object> userMap = new HashMap<>(1);
        //只查询二级标签为前端用户的数据
        userMap.put("typeValueCode", "average_user");
        List<UserVO> userVOList = rpcComponent.rpcQueryList(userMap, null, RpcTypeEnum.GET_USER_LIST, UserVO.class);

        List<String> mobiles = userVOList.stream().map(UserVO::getMobile).collect(Collectors.toList());
        List<Customer> list = new ArrayList<>();
        List<Customer> customerList = baseMapper.selectList(new QueryWrapper<Customer>().lambda().isNull(Customer::getUserId));
        if (CollectionUtils.isNotEmpty(customerList)) {
            for (Customer customer : customerList) {
                if (mobiles.contains(customer.getMobile())) {
                    Customer customer1 = new Customer();
                    customer1.setState(1);
                    UserVO userVO = userVOList.stream().filter(user -> Objects.equals(user.getMobile(), customer.getMobile())).findAny().get();
                    customer1.setUserId(userVO.getId());
                    customer1.setId(customer.getId());
                    list.add(customer1);
                }
            }
        }
        List<String> collect = list.stream().map(Customer::getId).collect(Collectors.toList());
        this.updateBatchById(list);
        log.info("清洗已注册,userId为空的客户注册状态-客户id：{}", collect);
    }

    /**
     * 清洗 经纪人对客户的备注
     * 根据客户手机号和 分配/关联的经纪人id 查询生成的咨询单里的客户信息   填充到备注信息
     */
    @Override
    public void updateCustomerRemark() {
        List<CustomerAgent> listRemark = new ArrayList<>();
        List<CustomerAgent> list = customerAgentService.list(new QueryWrapper<CustomerAgent>().ne("type", CustomerAgentTypeEnum.无).select("customer_id", "agent_user_id", "(select c.mobile  from customer c where c.id = customer_agent.customer_id) as customerMobile"));
        for (CustomerAgent customerAgent : list) {
            //以备注过 不再新增
            CustomerAgent one = customerAgentService.getOne(new QueryWrapper<CustomerAgent>().lambda().eq(CustomerAgent::getAgentUserId, customerAgent.getAgentUserId()).eq(CustomerAgent::getCustomerId, customerAgent.getCustomerId()).eq(CustomerAgent::getType, CustomerAgentTypeEnum.无));
            if (Objects.isNull(one)) {

                PotentialCustomer potentialCustomers = potentialCustomerService.getOne(new QueryWrapper<PotentialCustomer>().eq("agent_user_id", customerAgent.getAgentUserId()).eq("mobile", customerAgent.getCustomerMobile()).orderByDesc("create_date_time").last("limit 1"));
                if (Objects.nonNull(potentialCustomers)) {
                    CustomerAgent remark = new CustomerAgent();
                    remark.setType(CustomerAgentTypeEnum.无);
                    remark.setState(CustomerAgentStateEnum.无);
                    remark.setAgentUserId(potentialCustomers.getAgentUserId());
                    remark.setCustomerId(customerAgent.getCustomerId());
                    remark.setName(potentialCustomers.getName());
                    remark.setProvinceCode(potentialCustomers.getProvinceCode());
                    remark.setCityCode(potentialCustomers.getCityCode());
                    remark.setAreaCode(potentialCustomers.getAreaCode());
                    remark.setEnterpriseName(potentialCustomers.getEnterpriseName());
                    StringBuilder dangerPlantedName = new StringBuilder();
                    if (StringUtils.isNotEmpty(potentialCustomers.getDangerPlantedName())) {
                        dangerPlantedName.append("险种：").append(potentialCustomers.getDangerPlantedName()).append(";");
                    }
                    if (StringUtils.isNotEmpty(potentialCustomers.getProjectName())) {
                        dangerPlantedName.append("项目名称：").append(potentialCustomers.getProjectName()).append(";");
                    }
                    remark.setDescription(dangerPlantedName.toString());
                    listRemark.add(remark);
                }
            }
        }
        if (CollectionUtils.isNotEmpty(listRemark)) {
            customerAgentService.saveBatch(listRemark);
        }
    }

    @Override
    public void cleaningOfflineCastInsurance(String agentUserId, String startTime, String endTime, Integer current, Integer size) {
        pullOfflineCastInsurance(agentUserId, startTime, endTime, current, size);
    }


    /**
     * 根据客户线索生成 客户经纪人备注
     *
     * @param potentialCustomerId
     */
    @Override
    public void generateCustomer(String potentialCustomerId) {
        PotentialCustomer potentialCustomer = potentialCustomerService.getById(potentialCustomerId);
        if (Objects.isNull(potentialCustomer)) {
            throw new BusinessException("无效线索");
        }
        CustomerAssociated customerAssociated = customerAssociatedService.getOne(new QueryWrapper<CustomerAssociated>().lambda().eq(CustomerAssociated::getPotentialCustomerId, potentialCustomerId));
        if (Objects.isNull(customerAssociated)) {
            throw new BusinessException("未查询到客户");
        }
        //建立客户和经纪人关系
        customerAgentService.saveCustomerAgent(customerAssociated.getCustomerId(), potentialCustomer.getAgentUserId(), null, CustomerAgentOperateRecordSourceEnum.生成客户);
        this.updateModifyDateTime(customerAssociated.getCustomerId());
        //生成客户经纪人备注信息
        CustomerAgent agentRemarkCustomer = customerAgentService.getAgentRemarkCustomer(customerAssociated.getCustomerId(), potentialCustomer.getAgentUserId());
        if (Objects.nonNull(agentRemarkCustomer)) {
            throw new BusinessException("已生成过客户，请勿重复生成！");
        }
        CustomerAgent remark = new CustomerAgent();
        remark.setType(CustomerAgentTypeEnum.无);
        remark.setState(CustomerAgentStateEnum.无);
        remark.setAgentUserId(potentialCustomer.getAgentUserId());
        remark.setCustomerId(customerAssociated.getCustomerId());
        remark.setName(potentialCustomer.getName());
        remark.setProvinceCode(potentialCustomer.getProvinceCode());
        remark.setCityCode(potentialCustomer.getCityCode());
        remark.setAreaCode(potentialCustomer.getAreaCode());
        remark.setEnterpriseName(potentialCustomer.getEnterpriseName());
        String description = StringUtils.EMPTY;
        if (StringUtils.isNotBlank(potentialCustomer.getDangerPlantedName())) {
            description += "险种：" + potentialCustomer.getDangerPlantedName() + ";";
        }
        if (StringUtils.isNotBlank(potentialCustomer.getProjectName())) {
            description += "项目名称：" + potentialCustomer.getProjectName() + ";";
        }
        remark.setDescription(StringUtils.isNotBlank(description) ? description.substring(0, description.length() - 1) : StringUtils.EMPTY);
        customerAgentService.save(remark);
    }


    /**
     * 拉取线下保单数据   一体化和电子保函客户
     *
     * @param agentUserId 经纪人id
     * @param startTime   开始时间
     * @param endTime     结束时间
     * @param current     1
     * @param size        5000
     */
    @Override
    public void pullOfflineCastInsurance(String agentUserId, String startTime, String endTime, Integer current, Integer size) {
        //redis里的省市区
        Map regionCodeList = getRegionCodeList();
        List<String> provinceCodeList = (List<String>) regionCodeList.get("provinceCodeList");
        List<String> cityCodeList = (List<String>) regionCodeList.get("cityCodeList");
        List<String> areaCodeList = (List<String>) regionCodeList.get("areaCodeList");
        //已分配经纪人的客户
        List<String> customerAgentMobileList = customerAgentService.getCustomerMobileAlreadyAllotAgent();
        //获取线下单
        HashMap<String, Object> hashMap = new HashMap<>(5);
        hashMap.put("typeIn", "1,2");
        hashMap.put("submitMobileIsNotNull", Boolean.TRUE);
        hashMap.put("selectSql", Boolean.TRUE);
        if (StringUtils.isNotEmpty(startTime) && StringUtils.isNotEmpty(endTime)) {
            hashMap.put("startTime", startTime);
            hashMap.put("endTime", endTime);
        }
        List<Customer> saveCustomerList = Lists.newArrayList();
        List<CustomerAgent> saveCustomerAgentList = Lists.newArrayList();

        int resultData = 1;
        //未处理的客户
        List<String> failureCustomerList = new ArrayList<>();

        while (resultData != 0) {
            //已存在的客户
            List<Customer> customers = this.list(new QueryWrapper<Customer>().isNotNull("mobile").select("id", "mobile"));
            List<String> mobileList = customers.stream().map(Customer::getMobile).collect(Collectors.toList());

            hashMap.put("current", current);
            hashMap.put("size", size);
            LinkedHashMap map = rpcComponent.rpcQueryInfo(hashMap, RpcTypeEnum.GET_OFFLINE_CAST_INSURANCE, LinkedHashMap.class);
            List<OfflineCastInsuranceDataVO> dataVOList = JSONObject.parseArray(JSONObject.toJSONString(map.get("records")), OfflineCastInsuranceDataVO.class);
            resultData = dataVOList.size();
            log.debug("->>> 查询线下保单第{}页，返回{}条====", current, resultData);
            if (CollectionUtils.isNotEmpty(dataVOList)) {
                for (OfflineCastInsuranceDataVO offlineCastInsuranceDataVO : dataVOList) {
                    //省code为空  或者 省code获取字典表未获取到   此条客户不处理
                    if (StringUtils.isEmpty(offlineCastInsuranceDataVO.getProvinceCode()) || !provinceCodeList.contains(offlineCastInsuranceDataVO.getProvinceCode() + additionalCode)) {
                        failureCustomerList.add(offlineCastInsuranceDataVO.getId());
                        continue;
                    }
                    String customerId;
                    //已存在客户 在公海：创建备注
                    if (mobileList.contains(offlineCastInsuranceDataVO.getSubmitMobile()) && !customerAgentMobileList.contains(offlineCastInsuranceDataVO.getSubmitMobile())) {
                        Customer c = customers.stream().filter(customer -> Objects.equals(customer.getMobile(), offlineCastInsuranceDataVO.getSubmitMobile())).findAny().get();
                        customerId = c.getId();
                    } else {
                        //不存在客户: 创建客户  创建备注。标注来源
                        Customer customer = new Customer();
                        customer.setMobile(offlineCastInsuranceDataVO.getSubmitMobile());
                        customer.setCustomerSourceId("电子保函".equals(offlineCastInsuranceDataVO.getType()) ? "1584721073755660290" : "1584721138524102658");
                        customerId = String.valueOf(IdWorker.getId(customer));
                        customer.setId(customerId);
                        saveCustomerList.add(customer);
                    }
                    //生成备注信息
                    saveCustomerRemark(customerId, saveCustomerAgentList, offlineCastInsuranceDataVO, agentUserId, provinceCodeList, cityCodeList, areaCodeList);
                }
            }
            this.saveBatch(saveCustomerList, 2000);
            customerAgentService.saveBatch(saveCustomerAgentList, 2000);
            current++;
            saveCustomerList.clear();
            saveCustomerAgentList.clear();
        }
        log.info("" + failureCustomerList);
    }

    /**
     * 清洗线下单客户备注-更改地区
     */
    @Override
    public void cleaningOfflineCastInsuranceCustomer() {
        List<CustomerAgent> list = customerAgentService.list(new QueryWrapper<CustomerAgent>().lambda().eq(CustomerAgent::getType, CustomerAgentTypeEnum.无).inSql(CustomerAgent::getCustomerId, "select id from customer where customer_source_id  = '1584721138524102658' or customer_source_id  = '1584721073755660290' and is_delete = '0' "));
        if (CollectionUtils.isEmpty(list)) {
            return;
        }
        //redis里的省市区
        Map regionCodeList = getRegionCodeList();
        List<String> provinceCodeList = (List<String>) regionCodeList.get("provinceCodeList");
        List<String> cityCodeList = (List<String>) regionCodeList.get("cityCodeList");
        List<String> areaCodeList = (List<String>) regionCodeList.get("areaCodeList");

        log.info("====需修改数量===" + list.size());
        for (CustomerAgent customerAgent : list) {
            Map<String, String> map = correctRegionCode(customerAgent.getProvinceCode(), customerAgent.getCityCode(), customerAgent.getAreaCode(), provinceCodeList, cityCodeList, areaCodeList);
            customerAgent.setProvinceCode(map.get("provinceCode"));
            customerAgent.setCityCode(map.get("cityCode"));
            customerAgent.setAreaCode(map.get("areaCode"));
        }
        customerAgentService.updateBatchById(list, 2000);
    }

    /**
     * 生成备注
     *
     * @param customerId
     * @param saveCustomerAgentList
     * @param offlineCastInsuranceDataVO
     */
    private void saveCustomerRemark(String customerId, List<CustomerAgent> saveCustomerAgentList, OfflineCastInsuranceDataVO offlineCastInsuranceDataVO, String agentUserId, List<String> provinceCodeList, List<String> cityCodeList, List<String> areaCodeList) {
        //以备注过 不再新增
        CustomerAgent one = customerAgentService.getOne(new QueryWrapper<CustomerAgent>().lambda().eq(CustomerAgent::getAgentUserId, agentUserId).eq(CustomerAgent::getCustomerId, customerId).eq(CustomerAgent::getType, CustomerAgentTypeEnum.无));
        if (Objects.isNull(one)) {
            CustomerAgent remark = new CustomerAgent();
            remark.setType(CustomerAgentTypeEnum.无);
            remark.setState(CustomerAgentStateEnum.无);
            remark.setAgentUserId(agentUserId);
            remark.setCustomerId(customerId);
            remark.setName(offlineCastInsuranceDataVO.getSubmitName());
            Map<String, String> map = correctRegionCode(offlineCastInsuranceDataVO.getProvinceCode(), offlineCastInsuranceDataVO.getCityCode(), offlineCastInsuranceDataVO.getAreaCode(), provinceCodeList, cityCodeList, areaCodeList);
            remark.setProvinceCode(map.get("provinceCode"));
            remark.setCityCode(map.get("cityCode"));
            remark.setAreaCode(map.get("areaCode"));
            remark.setEnterpriseName(offlineCastInsuranceDataVO.getCastEntepriseName());
            StringBuilder dangerPlantedName = new StringBuilder();
            if (StringUtils.isNotEmpty(offlineCastInsuranceDataVO.getDangerPlantedName())) {
                dangerPlantedName.append("险种：").append(offlineCastInsuranceDataVO.getDangerPlantedName()).append(";");
            }
            if (StringUtils.isNotEmpty(offlineCastInsuranceDataVO.getProjectName())) {
                dangerPlantedName.append("项目名称：").append(offlineCastInsuranceDataVO.getProjectName()).append(";");
            }
            remark.setDescription(dangerPlantedName.toString());
            saveCustomerAgentList.add(remark);
        }
    }

    /**
     * 根据工保网地区字典表  纠正地区code
     *
     * @param provinceCode
     * @param cityCode
     * @param areaCode
     * @param provinceCodeList
     * @param cityCodeList
     * @param areaCodeList
     */
    private Map<String, String> correctRegionCode(String provinceCode, String cityCode, String areaCode, List<String> provinceCodeList, List<String> cityCodeList, List<String> areaCodeList) {
        //线下单地区code是6位数   默认追加6个0
        Map<String, String> resultMap = new HashMap<>(3);
        resultMap.put("provinceCode", provinceCode + additionalCode);
        resultMap.put("cityCode", cityCode + additionalCode);
        resultMap.put("areaCode", areaCode + additionalCode);
        // 省和市一样：市和区默认为 全部市全部区
        if (Objects.equals(provinceCode, cityCode)) {
            String proSub = provinceCode.substring(0, 2);
            resultMap.put("cityCode", proSub + "9900" + additionalCode);
            resultMap.put("areaCode", proSub + "9999" + additionalCode);
            return resultMap;
        } else {
            //不一样：根据市code获取字典表，获取到返回。未获取到默认全部市全部区
            String cCode = cityCodeList.contains(cityCode + additionalCode) ? cityCode + additionalCode : cityCode.substring(0, 2) + "9900" + additionalCode;
            resultMap.put("cityCode", cCode);
        }

        // 市和区一样：区默认为全部区
        if (Objects.equals(cityCode, areaCode)) {
            String areaSub = cityCode.substring(0, 4);
            resultMap.put("areaCode", areaSub + "99" + additionalCode);
            return resultMap;
        } else {
            //不一样：根据市code获取字典表，获取到返回。未获取到默认全部区
            String aCode = areaCodeList.contains(areaCode + additionalCode) ? areaCode + additionalCode : areaCode.substring(0, 4) + "99" + additionalCode;
            resultMap.put("areaCode", aCode);
        }
        return resultMap;
    }

    /**
     * 获取redis里 省 市  区的 code数组
     *
     * @return 省：provinceCodeList   市：cityCodeList  区：areaCodeList
     */
    private Map getRegionCodeList() {
        Map<String, List<String>> regionCodeList = new HashMap(3);
        List<String> provinceCodeList = new ArrayList<>();
        List<String> cityCodeList = new ArrayList<>();
        List<String> areaCodeList = new ArrayList<>();
        //redis里的省市区
        List<Map<String, Object>> regionList = JsonUtil.bean(stringRedisTemplate.opsForValue().get("provinceList"), List.class);
        for (Map<String, Object> province : regionList) {
            provinceCodeList.add((String) province.get("value"));
            List<Map<String, Object>> cityList = (List<Map<String, Object>>) province.get("children");
            for (Map<String, Object> city : cityList) {
                cityCodeList.add((String) city.get("value"));
                List<Map<String, Object>> areaList = (List<Map<String, Object>>) city.get("children");
                for (Map<String, Object> area : areaList) {
                    areaCodeList.add((String) area.get("value"));
                }
            }
        }
        regionCodeList.put("provinceCodeList", provinceCodeList);
        regionCodeList.put("cityCodeList", cityCodeList);
        regionCodeList.put("areaCodeList", areaCodeList);
        return regionCodeList;
    }

    /**
     * 获取对应的省市区名称
     *
     * @param userVO
     */
    private void getRegion(UserVO userVO) {
        if (Objects.isNull(userVO) || Objects.isNull(userVO.getUserExtends())) {
            return;
        }
        UserExtendsVO userExtendsVO = userVO.getUserExtends();
        Map<String, String> region = productComponent.getRegion(userExtendsVO.getAreaCode(), userExtendsVO.getCityCode(), userExtendsVO.getProvinceCode());
        if (CollectionUtils.isNotEmpty(region)) {
            userExtendsVO.setAreaName(region.get("areaName"));
            userExtendsVO.setCityName(region.get("cityName"));
            userExtendsVO.setProvinceName(region.get("provinceName"));
        }
    }

}
