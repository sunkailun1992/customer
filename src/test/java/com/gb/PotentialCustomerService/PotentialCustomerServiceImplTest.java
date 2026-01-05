package com.gb.PotentialCustomerService;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gb.SpringTest;
import com.gb.aliyun.oss.OssUtils;
import com.gb.customer.entity.Customer;
import com.gb.customer.entity.CustomerAgent;
import com.gb.customer.entity.enums.CustomerAgentStateEnum;
import com.gb.customer.entity.enums.CustomerAgentTypeEnum;
import com.gb.customer.mapper.CustomerAgentMapper;
import com.gb.customer.service.*;
import com.gb.customer.template.CluesContainer;
import com.gb.log.entity.PotentialCustomerLog;
import com.gb.promoteform.service.PromoteFormService;
import com.gb.rpc.InsuranceRpc;
import com.gb.rpc.entity.CustomerMarketingInsuranceStatisticsQueryParam;
import com.gb.rpc.entity.CustomerMarketingQueryParam;
import com.gb.rpc.entity.InsuranceStatisticsVO;
import com.gb.rpc.entity.enums.RpcTypeEnum;
import com.gb.rpc.impl.RpcComponent;
import com.gb.util.LocalDateUtil;
import com.gb.utils.JsonUtil;
import com.gb.utils.OkhttpUtils;
import com.gb.utils.constants.UniversalConstant;
import com.gb.utils.enumeration.HttpType;
import com.gb.utils.enumeration.HttpWay;
import com.google.common.collect.Maps;
import okhttp3.Request;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangyifei
 * @Description
 * @date 2021/4/1420:49
 */
public class PotentialCustomerServiceImplTest extends SpringTest {

    @Resource
    private PotentialCustomerService potentialCustomerService;

    //注入MongDB
    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private InsuranceRpc castInsuranceRpc;
    @Autowired
    private CustomerCastInsuranceService customerCastInsuranceService;
    @Autowired
    private AssistantAssociatedService assistantAssociatedService;
    @Autowired
    private RpcComponent rpcComponent;
    @Autowired
    private AgentPeopleService agentPeopleService;
    @Autowired
    private CluesContainer cluesContainer;
    @Autowired
    private PromoteFormService promoteFormService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private CustomerAgentService customerAgentService;


    /**
     * 根据订单id查询订单的操作日志
     *
     * @return
     */
    @Test
    public void findOrderOperationLogByOrderId() {
        PotentialCustomerLog potentialCustomerLog = new PotentialCustomerLog() {{
            setPotentialCustomerId("1");
        }};
        Integer current = 1;
        Integer size = 5;


        //定义条件
        Criteria criteria = Criteria.where("potentialCustomerId").is(potentialCustomerLog.getPotentialCustomerId());
        //定义条件
        Query query = new Query(criteria).skip((current - 1) * size).limit(size);
        //分页查询,先查询日志记录
        List<PotentialCustomerLog> logList = mongoTemplate.find(query, PotentialCustomerLog.class);
        //再查询总条数
        long total = mongoTemplate.count(new Query(criteria), PotentialCustomerLog.class);
        //塞到Page对象中去
        Page page = new Page();
        //设置记录
        page.setRecords(logList);
        //设置当前页
        page.setCurrent(current);
        //设置每页数量
        page.setSize(size);
        //设置总数
        page.setTotal(total);

        System.out.println("-----------------------------");
        System.out.println("-----------------------------");
        System.out.println("-----------------------------");
        //返回
        System.err.println(page.getRecords());
    }

    @Test
    public void generateQrCodeTest() throws Exception {
        String content = "http://gbwtest.share.hzcngb.com/single_multi_insurance?id=1550427188245848066";
        String nowTime = new DateTime().toString(DatePattern.PURE_DATETIME_PATTERN);
        String fileName = "promote/" + nowTime + RandomUtil.randomNumbers(6) + ".png";
        byte[] bytes = cn.hutool.extra.qrcode.QrCodeUtil.generatePng(content, UniversalConstant.TWO_HUNDRED, UniversalConstant.TWO_HUNDRED);
        String url = OssUtils.uploadFileReturnUrl(bytes, fileName);
        System.out.println("-----" + url);
    }

    @Test
    public void getCustomerMarketingInsuranceStatisticsResults() {
        CustomerMarketingInsuranceStatisticsQueryParam param = new CustomerMarketingInsuranceStatisticsQueryParam();
        param.setDayStartTime(LocalDateUtil.getDayStartTimeStr());
        param.setDayEndTime(LocalDateUtil.getDayEndTimeStr());
        param.setMonthStartTime(LocalDateUtil.getMonthStartTime());
        param.setMonthEndTime(LocalDateUtil.getMonthEndTime());

        //获取统计保费
        List<String> agentUserIds = new ArrayList<>();
        agentUserIds.add("150681646");
        param.setAgentUserIds(agentUserIds);
        System.out.println("-----" + param);

//        CustomerMarketingInsuranceStatisticsQueryParam customerMarketingInsuranceStatisticsQueryParam = new CustomerMarketingInsuranceStatisticsQueryParam();
//        List<CustomerMarketingQueryParam> marketingQueryParams = new ArrayList<>();
//        //保费参数
//        CustomerMarketingQueryParam customerMarketingQueryParam = new CustomerMarketingQueryParam();
//        customerMarketingQueryParam.setCastInsuranceUserId("872247137");
//        customerMarketingQueryParam.setAgentUserId("150681646");
//        marketingQueryParams.add(customerMarketingQueryParam);
//        CustomerMarketingQueryParam customerMarketingQueryParam1 = new CustomerMarketingQueryParam();
//        customerMarketingQueryParam1.setCastInsuranceUserId("150681646");
//        customerMarketingQueryParam1.setAgentUserId("150266391");
//        marketingQueryParams.add(customerMarketingQueryParam1);
        //获取客户保费
//        customerMarketingInsuranceStatisticsQueryParam.setMarketingQueryParams(marketingQueryParams);
        HashMap<String, Object> hashMapRpc = new HashMap<>(1);
        hashMapRpc.put("marketingQueryParams", param);
        InsuranceStatisticsVO insuranceStatisticsVO = rpcComponent.rpcQueryInfo(hashMapRpc, RpcTypeEnum.GET_CUSTOMER_PREMIUM, InsuranceStatisticsVO.class);
        System.err.println("----------" + JsonUtil.json(insuranceStatisticsVO));
    }

    @Test
    public void getCustomerId() {
        String customerId = "1592769886505361409";
        String agentUserId = "840625778";
        List<CustomerAgent> list = customerAgentService.list(new QueryWrapper<CustomerAgent>().lambda().eq(CustomerAgent::getCustomerId, customerId).eq(CustomerAgent::getAgentUserId, agentUserId).
                and(broker -> broker.eq(CustomerAgent::getState, CustomerAgentStateEnum.主关联).eq(CustomerAgent::getType, CustomerAgentTypeEnum.自营).
                        or().eq(CustomerAgent::getType, CustomerAgentTypeEnum.分销))
        );
        System.out.println("-list------" + list);
    }

    @Test
    public void sendYunTest() {
        //推送云
        Map<String, Object> paramMap = Maps.newHashMap();
        paramMap.put("pageId", "1592715741223452673");
        paramMap.put("phone", "15136285435");
        paramMap.put("reserveTime", "2023-01-11 10:29:41");
        String send = StringUtils.EMPTY;
        try {
            send = OkhttpUtils.send(new Request.Builder(), HttpWay.POST,
                    "https://biaoxunyun-pre.gongbaojin.com/task/reserveOrder",
                    JSON.toJSONString(paramMap), HttpType.JSON).string();
            System.out.println("通知云,参数：" + JSON.toJSONString(paramMap) + "   结果：" + send);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("通知云,参数：" + JSON.toJSONString(paramMap) + "   结果：" + send);
        }
    }

}
