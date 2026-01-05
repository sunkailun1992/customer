package com.gb.log.service.impl;

import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gb.customer.entity.PotentialCustomer;
import com.gb.customer.service.PotentialCustomerService;
import com.gb.log.entity.PotentialCustomerLog;
import com.gb.log.mapper.PotentialCustomerLogMapper;
import com.gb.log.menu.PotentialCustomerLogStateMenu;
import com.gb.log.service.PotentialCustomerLogService;
import com.gb.rpc.UserRpc;
import com.gb.rpc.entity.UserGroupInfo;
import com.gb.utils.RedisUtils;
import com.gb.utils.enumeration.UserTypeEnum;
import com.gb.utils.exception.BusinessException;
import lombok.Setter;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author wangyifei
 * @Description
 * @date 2021/3/12 10:20
 */
@Service
@Setter(onMethod_ = {@Autowired})
public class PotentialCustomerLogServiceImpl implements PotentialCustomerLogService {

    /**
     * 产品咨询操作日志
     */
    private PotentialCustomerLogMapper potentialCustomerLogMapper;

    /**
     * 潜在客户
     */
    private PotentialCustomerService potentialCustomerService;

    /**
     * RPC用户中心
     */
    private UserRpc userRpc;

    /**
     * redis操作
     */
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 注入MongDB
     */
    private MongoTemplate mongoTemplate;


    @Override
    public PotentialCustomerLog insert(PotentialCustomerLog potentialCustomerLog, HttpServletRequest httpServletRequest) {
        //获取北京时间（存储在mongodb中的时间是标准时间UTC +0:00，而中国时区是+8.00）
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        Date time = calendar.getTime();
        SimpleDateFormat data = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        potentialCustomerLog.setFinishedTime(data.format(time));

        //RPC调用  获取登录人
        String userId = "id";
        String name = "name";
        String token = "token";
        LinkedHashMap<String, Object> userMap;
        //判断如果有授权就直接取，否则就从集合中取出
        if (httpServletRequest.getHeader(token) != null) {
            userMap = (LinkedHashMap<String, Object>) RedisUtils.getToken(stringRedisTemplate, httpServletRequest.getHeader("token"));
        } else {
            //取出本地缓存用户相关等信息
            userMap = (LinkedHashMap<String, Object>) RedisUtils.getToken(stringRedisTemplate, httpServletRequest);
            if (MapUtils.isEmpty(userMap) || null == userMap.get(userId)) {
                throw new BusinessException("无效的token，请检查问题！");
            }
        }
        //设置 操作人name
        if (StringUtils.isNotBlank(Convert.toStr(userMap.get(name)))) {
            potentialCustomerLog.setOperator(Convert.toStr(userMap.get(name)));
        }
        //设置 操作人id
        if (StringUtils.isNotBlank(Convert.toStr(userMap.get(userId)))) {
            potentialCustomerLog.setOperatorId(Convert.toStr(userMap.get(userId)));
        }

        //获取表单数据
        PotentialCustomer potentialCustomer = new PotentialCustomer();
        String userSpuConsultingId = potentialCustomerLog.getPotentialCustomerId();
        if (StringUtils.isNotBlank(userSpuConsultingId)) {
            potentialCustomer = potentialCustomerService.getOneEnhance(new PotentialCustomer() {{
                setId(potentialCustomerLog.getPotentialCustomerId());
            }}, httpServletRequest);
        }
        //根据操作类型。赋值对应操作内容
        operationContent(potentialCustomerLog, potentialCustomer);
        //状态（-1：取消，0：待处理，1：处理中，2：已完成，3：关闭）
        HashMap<Integer, Object> potentialCustomerEnums = new HashMap(5) {{
            put(-1, -1);
            put(0, 0);
            put(1, 1);
            put(2, 2);
            put(3, 3);
        }};
        //设置 状态
        if (StringUtils.isNotBlank(Convert.toStr(potentialCustomer.getState()))) {
            if (potentialCustomerEnums.containsKey(potentialCustomer.getState())) {
                potentialCustomerLog.setState(Convert.toStr(PotentialCustomerLogStateMenu.getDesc(potentialCustomer.getState())));
            }
        }
        return potentialCustomerLogMapper.insert(potentialCustomerLog);
    }


    private void operationContent(PotentialCustomerLog potentialCustomerLog, PotentialCustomer potentialCustomer) {
        //获取 插入信息
        Integer genre = potentialCustomerLog.getGenre();
        if (Objects.nonNull(genre)) {
            //0:更换经纪人 1：关闭订单 2：备注订单
            switch (genre) {
                case 0:
                    //根据经纪人id 获取 经纪人名称
                    List<UserGroupInfo> realUserList = potentialCustomerService.getUserGroupList(UserTypeEnum.业务管家.getTypeCode())
                            .stream()
                            .filter(s -> potentialCustomerLog.getAgentUserId().equals(s.getUserName()))
                            .collect(Collectors.toList());
                    if (realUserList.size() > 0) {
                        UserGroupInfo realUser = realUserList.get(0);
                        potentialCustomerLog.setAgent(realUser.getRealName());
                    }

                    //修改    潜在客户表
                    potentialCustomerLog.setOperationContent(
                            "将经纪人" + Convert.toStr(potentialCustomer.getAgentUserName()) + "更换为" + Convert.toStr(potentialCustomerLog.getAgent()));
                    potentialCustomerService.updateEnhance(new PotentialCustomer() {{
                        setId(potentialCustomerLog.getPotentialCustomerId());
                        setAgentUserId(potentialCustomerLog.getAgentUserId());
                        setAgentUserName(potentialCustomerLog.getAgent());
                    }});
                    break;
                case 1:
                    potentialCustomerService.updateEnhance(new PotentialCustomer() {{
                        setId(potentialCustomerLog.getPotentialCustomerId());
                        setState(3);
                    }});

                    potentialCustomerLog.setOperationContent(Convert.toStr(potentialCustomerLog.getOperator()) + "关闭订单");
                    break;
                case 2:
                    //修改数据，增加备注
                    potentialCustomerService.updateEnhance(new PotentialCustomer() {{
                        setId(potentialCustomerLog.getPotentialCustomerId());
                        setResultsContent(potentialCustomerLog.getDescription());
                    }});

                    potentialCustomerLog.setOperationContent(Convert.toStr(potentialCustomerLog.getOperator()) + "备注订单:" + potentialCustomerLog.getDescription());
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public Page pageEnhance(PotentialCustomerLog potentialCustomerLog, int current, int size) {
        //定义条件
        Criteria criteria = Criteria.where("potentialCustomerId").is(potentialCustomerLog.getPotentialCustomerId());
        //定义条件
        Query query = new Query(criteria).skip((current - 1) * size).limit(size);
        //分页查询,先查询日志记录
        List<PotentialCustomerLog> logList = mongoTemplate.find(query, PotentialCustomerLog.class);
        //再查询总条数
        long total = mongoTemplate.count(new Query(criteria), PotentialCustomerLog.class);
        //塞到Page对象中去
        Page<PotentialCustomerLog> page = new Page<>();
        //设置记录
        page.setRecords(logList);
        //设置当前页
        page.setCurrent(current);
        //设置每页数量
        page.setSize(size);
        //设置总数
        page.setTotal(total);
        return page;
    }

    @Override
    public List<PotentialCustomerLog> listEnhance(PotentialCustomerLog potentialCustomerLog) {
        String potentialCustomerId = potentialCustomerLog.getPotentialCustomerId();
        if (StringUtils.isBlank(potentialCustomerId)) {
            return new ArrayList<>();
        }

        //模糊查询
        return potentialCustomerLogMapper.select(new Query(Criteria.where("potentialCustomerId").is(potentialCustomerId)));
    }
}
