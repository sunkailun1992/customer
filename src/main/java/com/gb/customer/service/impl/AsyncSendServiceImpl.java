package com.gb.customer.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.gb.bean.GongBaoConfig;
import com.gb.customer.dto.OfflineCastInsuranceCustomerDto;
import com.gb.customer.entity.Customer;
import com.gb.customer.entity.CustomerAgent;
import com.gb.customer.entity.CustomerAssociated;
import com.gb.customer.entity.PotentialCustomer;
import com.gb.customer.entity.enums.CustomerAgentStateEnum;
import com.gb.customer.entity.enums.CustomerAgentTypeEnum;
import com.gb.customer.enums.CustomerStateEnum;
import com.gb.customer.enums.PotentialCustomerSourceTypeEnum;
import com.gb.customer.enums.PotentialCustomerTypeEnum;
import com.gb.customer.enums.SourceTypeEnum;
import com.gb.customer.factory.PotentialCustomerFactory;
import com.gb.customer.mapper.CustomerCastInsuranceMapper;
import com.gb.customer.mapper.CustomerMapper;
import com.gb.customer.service.*;
import com.gb.customer.template.impl.UserMessageService;
import com.gb.rpc.component.UserComponent;
import com.gb.rpc.entity.TransformationExternalPlatformSystemVO;
import com.gb.rpc.impl.RpcComponent;
import com.gb.utils.OkhttpUtils;
import com.gb.utils.enumeration.HttpType;
import com.gb.utils.enumeration.HttpWay;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Request;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @title: AsyncSendServiceImpl
 * @author: lijh
 * @date: 2023/3/10 14:29
 * @description:
 */
@Slf4j
@Service
public class AsyncSendServiceImpl implements AsyncSendService {

    /**
     * 客户表
     */
    @Autowired
    private CustomerService customerService;
    @Autowired
    private PotentialCustomerService potentialCustomerService;
    @Autowired
    private CustomerAssociatedService customerAssociatedService;
    @Autowired
    private CustomerAgentService customerAgentService;
    @Autowired
    private UserComponent userComponent;



    private final DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    @Async
    public String asyncSendYunPromoteForm(PotentialCustomer potentialCustomer) throws Exception {
        List<String> promoteFormIds = Arrays.asList(GongBaoConfig.promoteFormIds.split(","));
        log.debug("异步通知云表单,已配置的表单={}   需推送的表单={}", promoteFormIds, potentialCustomer.getPromoteFormId());
        if (StringUtils.isEmpty(potentialCustomer.getPromoteFormId()) || !promoteFormIds.contains(potentialCustomer.getPromoteFormId())) {
            return null;
        }
        Map<String, Object> paramMap = Maps.newHashMap();
        paramMap.put("pageId", potentialCustomer.getPromoteFormId());
        paramMap.put("phone", potentialCustomer.getMobile());
        paramMap.put("reserveTime", potentialCustomer.getCreateDateTime().format(formatter));
        String jsonString = JSON.toJSONString(paramMap);
        log.debug("异步通知云表单,参数={},地址={}", jsonString, GongBaoConfig.promoteFormSendYunUrl);
        String result = OkhttpUtils.send(new Request.Builder(), HttpWay.POST,
                GongBaoConfig.promoteFormSendYunUrl,
                jsonString, HttpType.JSON).string();
        log.debug("异步通知云表单,结束,参数：{}   结果：{}", jsonString, result);
        return result;


    }


    /**
     * 转化线下单数据为客户
     *
     * @param offlineCastInsuranceCustomerDto
     */
    @Override
    @Async
    public void transformationOfflineCastInsuranceCustomer(List<OfflineCastInsuranceCustomerDto> offlineCastInsuranceCustomerDto) {
        log.debug("转化线下单数据为客户接收到数量={},结构={}", offlineCastInsuranceCustomerDto.size(), JSON.toJSONString(offlineCastInsuranceCustomerDto));
        //已分配经纪人的客户
        List<String> customerAgentMobileList = customerAgentService.getCustomerMobileAlreadyAllotAgent();
        //已存在的客户
        List<Customer> customers = customerService.list(new QueryWrapper<Customer>().isNotNull("mobile").select("id", "mobile"));
        List<String> mobileList = customers.stream().map(Customer::getMobile).collect(Collectors.toList());
        //外部系统平台集合
        List<TransformationExternalPlatformSystemVO> platformSystemList = userComponent.getPlatformSystemList();
        List<String> externalSystemCodes = platformSystemList.stream().map(TransformationExternalPlatformSystemVO::getExternalSystemCode).collect(Collectors.toList());
        //所有已生成的备注
        List<String> customerRemarkList = Lists.newArrayList();
        List<CustomerAgent> remarkList = customerAgentService.list(new QueryWrapper<CustomerAgent>().lambda().eq(CustomerAgent::getAgentUserId, "298563535").eq(CustomerAgent::getType, CustomerAgentTypeEnum.无));
        if (CollectionUtils.isNotEmpty(remarkList)) {
            customerRemarkList = remarkList.stream().map(CustomerAgent::getCustomerId).collect(Collectors.toList());
        }
        //最终保存的数据
        List<Customer> saveCustomerList = Lists.newArrayList();
        List<CustomerAgent> saveCustomerAgentList = Lists.newArrayList();
        List<PotentialCustomer> savePotentialCustomerList = Lists.newArrayList();
        List<CustomerAssociated> saveCustomerAssociatedList = Lists.newArrayList();

        List<String> mobile = Lists.newArrayList();
        for (OfflineCastInsuranceCustomerDto castInsuranceCustomerDto : offlineCastInsuranceCustomerDto) {
            //校验此数据是否有重复手机号或手机号为空
            if (org.apache.commons.lang3.StringUtils.isEmpty(castInsuranceCustomerDto.getSubmitMobile()) || mobile.contains(castInsuranceCustomerDto.getSubmitMobile())) {
                continue;
            }
            //工保网类型不做操作
            if ("工保网".equals(castInsuranceCustomerDto.getType())) {
                continue;
            }
            //userId为空不做操作
            if (org.apache.commons.lang3.StringUtils.isEmpty(castInsuranceCustomerDto.getSubmitUserId())) {
                continue;
            }
            mobile.add(castInsuranceCustomerDto.getSubmitMobile());
            String customerId;
            //已存在客户 在公海：创建备注
            if (mobileList.contains(castInsuranceCustomerDto.getSubmitMobile())) {
                Customer c = customers.stream().filter(customer -> Objects.equals(customer.getMobile(), castInsuranceCustomerDto.getSubmitMobile())).findAny().get();
                customerId = c.getId();
                //客户在公海 创建备注
                if (!customerAgentMobileList.contains(castInsuranceCustomerDto.getSubmitMobile())) {
                    //生成备注信息
                    saveCustomerRemark(customerId, saveCustomerAgentList, castInsuranceCustomerDto, customerRemarkList);
                }
            } else {
                //不存在客户: 创建客户  创建备注。标注来源
                Customer customer = new Customer();
                customer.setMobile(castInsuranceCustomerDto.getSubmitMobile());
                customer.setCustomerSourceId("电子保函".equals(castInsuranceCustomerDto.getType()) ? "1584721073755660290" : "1584721138524102658");
                customerId = String.valueOf(IdWorker.getId(customer));
                customer.setId(customerId);
                customer.setUserId(castInsuranceCustomerDto.getSubmitUserId());
                customer.setState(CustomerStateEnum.已注册.getValue());
                saveCustomerList.add(customer);
                //生成备注信息
                saveCustomerRemark(customerId, saveCustomerAgentList, castInsuranceCustomerDto, customerRemarkList);
            }
            //生成线索
            savePotentialCustomer(platformSystemList, externalSystemCodes, savePotentialCustomerList, castInsuranceCustomerDto, saveCustomerAssociatedList, customerId);
        }
        List<String> customerIds = saveCustomerList.stream().map(Customer::getId).collect(Collectors.toList());
        customerService.saveBatch(saveCustomerList, 2000);
        customerAgentService.saveBatch(saveCustomerAgentList, 2000);
        potentialCustomerService.saveBatch(savePotentialCustomerList, 2000);
        customerAssociatedService.saveBatch(saveCustomerAssociatedList, 2000);
        log.debug("转化线下单数据为客户-客户数量={},客户ids={}", customerIds.size(), customerIds);
    }

    /**
     * 生成备注
     *
     * @param customerId
     * @param saveCustomerAgentList
     * @param castInsuranceCustomerDto
     */
    private void saveCustomerRemark(String customerId, List<CustomerAgent> saveCustomerAgentList, OfflineCastInsuranceCustomerDto castInsuranceCustomerDto, List<String> customerRemarkList) {
//        CustomerAgent one = customerAgentService.getOne(new QueryWrapper<CustomerAgent>().lambda().eq(CustomerAgent::getAgentUserId, "298563535").
//                eq(CustomerAgent::getCustomerId, customerId).eq(CustomerAgent::getType, CustomerAgentTypeEnum.无));
        //以备注过 不再新增
        if (!customerRemarkList.contains(customerId)) {
            CustomerAgent remark = new CustomerAgent();
            remark.setType(CustomerAgentTypeEnum.无);
            remark.setState(CustomerAgentStateEnum.无);
            remark.setAgentUserId(GongBaoConfig.highSeasAdminUserId);
            remark.setCustomerId(customerId);
            remark.setName(castInsuranceCustomerDto.getSubmitName());
            remark.setProvinceCode(castInsuranceCustomerDto.getProvinceCode());
            remark.setCityCode(castInsuranceCustomerDto.getCityCode());
            remark.setAreaCode(castInsuranceCustomerDto.getAreaCode());
            remark.setEnterpriseName(castInsuranceCustomerDto.getCastEnterpriseName());
            StringBuilder dangerPlantedName = new StringBuilder();
            if (org.apache.commons.lang3.StringUtils.isNotEmpty(castInsuranceCustomerDto.getDangerPlantedName())) {
                dangerPlantedName.append("险种：").append(castInsuranceCustomerDto.getDangerPlantedName()).append(";");
            }
            if (org.apache.commons.lang3.StringUtils.isNotEmpty(castInsuranceCustomerDto.getProjectName())) {
                dangerPlantedName.append("项目名称：").append(castInsuranceCustomerDto.getProjectName()).append(";");
            }
            remark.setDescription(dangerPlantedName.toString());
            saveCustomerAgentList.add(remark);
        }
    }

    /**
     * 生成线索
     * 根据平台code获取对应平台下的所有经纪人。来生成相关线索数据
     *
     * @param platformSystemList         外部系统数据
     * @param externalSystemCodes        外部系统code
     * @param savePotentialCustomerList  最终保存的集合
     * @param castInsuranceCustomerDto   线下单数据
     * @param saveCustomerAssociatedList 客户线索关联集合
     * @param customerId                 客户id
     */
    private void savePotentialCustomer(List<TransformationExternalPlatformSystemVO> platformSystemList, List<String> externalSystemCodes, List<PotentialCustomer> savePotentialCustomerList,
                                       OfflineCastInsuranceCustomerDto castInsuranceCustomerDto, List<CustomerAssociated> saveCustomerAssociatedList, String customerId) {
        if (externalSystemCodes.contains(castInsuranceCustomerDto.getExternalSystemCode())) {
            TransformationExternalPlatformSystemVO platformSystem = platformSystemList.stream().filter(platformSystemVO -> Objects.equals(platformSystemVO.getExternalSystemCode(), castInsuranceCustomerDto.getExternalSystemCode())).findAny().get();
            List<Map<String, Object>> teamUserVOList = platformSystem.getTeamUserVOList();
            if (CollectionUtils.isEmpty(teamUserVOList)) {
                return;
            }
            for (Map<String, Object> teamUser : teamUserVOList) {
                //开启了生成线索
                if ((Boolean) teamUser.get("clue")) {
                    PotentialCustomer potentialCustomer = new PotentialCustomer();
                    String id = String.valueOf(IdWorker.getId(potentialCustomer));
                    PotentialCustomerFactory.getBeanPotentialCustomer(potentialCustomer);
                    potentialCustomer.setId(id);
                    potentialCustomer.setUserId(castInsuranceCustomerDto.getSubmitUserId());
                    potentialCustomer.setMobile(castInsuranceCustomerDto.getSubmitMobile());
                    potentialCustomer.setAgentUserId((String) teamUser.get("userId"));
                    potentialCustomer.setAgentUserName((String) teamUser.get("userName"));
                    potentialCustomer.setSubmitTime(LocalDateTime.parse(castInsuranceCustomerDto.getSubmitTime(), df));
                    potentialCustomer.setName(castInsuranceCustomerDto.getSubmitName());
                    potentialCustomer.setProvinceCode(castInsuranceCustomerDto.getProvinceCode());
                    potentialCustomer.setCityCode(castInsuranceCustomerDto.getCityCode());
                    potentialCustomer.setAreaCode(castInsuranceCustomerDto.getAreaCode());
                    potentialCustomer.setEnterpriseName(castInsuranceCustomerDto.getCastEnterpriseName());
                    potentialCustomer.setDangerPlantedName(castInsuranceCustomerDto.getDangerPlantedName());
                    potentialCustomer.setType(PotentialCustomerTypeEnum.CUSTOMER_TYPE_0.getCode());
                    potentialCustomer.setSourceType(PotentialCustomerSourceTypeEnum.SOURCE_TYPE_4.getCode());
                    potentialCustomer.setProjectName(castInsuranceCustomerDto.getProjectName());
                    potentialCustomer.setPotentialCustomerSourceId(SourceTypeEnum.SOURCE_TYPE_16.getCode());
                    potentialCustomer.setAllocation(Boolean.TRUE);
                    savePotentialCustomerList.add(potentialCustomer);

                    //客户和线索关联数据
                    CustomerAssociated customerAssociated = new CustomerAssociated();
                    customerAssociated.setCustomerId(customerId);
                    customerAssociated.setPotentialCustomerId(id);
                    saveCustomerAssociatedList.add(customerAssociated);
                }
            }
        }
    }


}
