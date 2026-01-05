package com.gb.customer.service.impl;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.LocalDateTimeUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gb.customer.entity.Customer;
import com.gb.customer.entity.CustomerCommunication;
import com.gb.customer.mapper.CustomerCommunicationMapper;
import com.gb.customer.service.CustomerCommunicationService;
import com.gb.customer.service.CustomerService;
import com.gb.rpc.component.UserComponent;
import com.gb.rpc.entity.UserVO;
import com.gb.rpc.entity.enums.RpcTypeEnum;
import com.gb.rpc.impl.RpcComponent;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 客户沟通表 服务实现类
 * </p>
 *
 * @author lijh
 * @since 2021-06-08
 */
@Slf4j
@Service
@Setter(onMethod_ = {@Autowired})
public class CustomerCommunicationServiceImpl extends ServiceImpl<CustomerCommunicationMapper, CustomerCommunication> implements CustomerCommunicationService {


    /**
     * 客户沟通表
     */
    private CustomerCommunicationMapper customerCommunicationMapper;

    private RpcComponent rpcComponent;

    private UserComponent userComponent;

    private CustomerService customerService;


    /**
     * 集合条件查询
     *
     * @param customerCommunication:
     * @return java.util.List<com.entity.CustomerCommunication>
     * @author lijh
     * @since 2021-06-08
     */
    @Override
    public List<CustomerCommunication> listEnhance(CustomerCommunication customerCommunication) {
        QueryWrapper<CustomerCommunication> queryWrapper = new QueryWrapper<>(customerCommunication);
        query(customerCommunication, queryWrapper);
        return assignment(customerCommunicationMapper.selectList(queryWrapper));
    }


    /**
     * 分页条件查询
     *
     * @param customerCommunication:
     * @param page:
     * @return java.util.List<com.entity.CustomerCommunication>
     * @author lijh
     * @since 2021-06-08
     */
    @Override
    public IPage pageEnhance(Page page, CustomerCommunication customerCommunication) {
        QueryWrapper<CustomerCommunication> queryWrapper = new QueryWrapper<>(customerCommunication);
        query(customerCommunication, queryWrapper);
        return assignment(customerCommunicationMapper.selectPage(page, queryWrapper));
    }


    /**
     * 单条条件查询
     *
     * @param customerCommunication:
     * @return java.util.List<com.entity.CustomerCommunication>
     * @author lijh
     * @since 2021-06-08
     */
    @Override
    public CustomerCommunication getOneEnhance(CustomerCommunication customerCommunication) {
        QueryWrapper<CustomerCommunication> queryWrapper = new QueryWrapper<>(customerCommunication);
        query(customerCommunication, queryWrapper);
        return assignment(customerCommunicationMapper.selectOne(queryWrapper));
    }


    /**
     * 总数
     *
     * @param customerCommunication:
     * @return java.util.List<com.entity.CustomerCommunication>
     * @author lijh
     * @since 2021-06-08
     */
    @Override
    public Long countEnhance(CustomerCommunication customerCommunication) {
        QueryWrapper<CustomerCommunication> queryWrapper = new QueryWrapper<>(customerCommunication);
        query(customerCommunication, queryWrapper);
        return customerCommunicationMapper.selectCount(queryWrapper);
    }


    /**
     * 新增
     *
     * @param customerCommunication:
     * @return java.util.List<com.entity.CustomerCommunication>
     * @author lijh
     * @since 2021-06-08
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public Boolean saveEnhance(CustomerCommunication customerCommunication) {
        Customer customer = new Customer();
        customer.setId(customerCommunication.getCustomerId());
        customer.setModifyDateTime(LocalDateTime.now());
        //新增沟通记录
        Integer i = customerCommunicationMapper.insert(customerCommunication);
        /**
         * 跟进状态判断
         */
        //判断是否存在下次跟进时间，不存在默认设置到已跟进
        if (customerCommunication.getNextDataTime() != null) {
            //取出最新一条沟通记录
            List<CustomerCommunication> list = pageEnhance(new Page(1, 1), new CustomerCommunication() {{
                setCustomerId(customerCommunication.getCustomerId());
            }}).getRecords();
            //为空判断
            if (list.size() != 0) {
                CustomerCommunication c = list.get(0);
                if (c.getNextDataTime() != null) {
                    //时间比较，最新的下次跟进时间，是否大于当前时间
                    Duration between = LocalDateTimeUtil.between(LocalDateTimeUtil.beginOfDay(LocalDateTime.now()), c.getNextDataTime());
                    //取出时间差值
                    Long x = between.toDays();
                    if (x <= 0) {
                        customer.setConnectionState(false);
                    } else {
                        customer.setConnectionState(true);
                    }
                }
            } else {
                //时间比较，最新的下次跟进时间，是否大于当前时间
                Duration between = LocalDateTimeUtil.between(LocalDateTimeUtil.beginOfDay(LocalDateTime.now()), customerCommunication.getNextDataTime());
                //取出时间差值
                Long x = between.toDays();
                if (x <= 0) {
                    customer.setConnectionState(false);
                } else {
                    customer.setConnectionState(true);
                }
            }
        } else {
            customer.setConnectionState(true);
        }
        //修改客户
        customerService.updateEnhance(customer);
        return i > 0 ? true : false;
    }


    /**
     * 修改
     *
     * @param customerCommunication:
     * @return java.util.List<com.entity.CustomerCommunication>
     * @author lijh
     * @since 2021-06-08
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public Boolean updateEnhance(CustomerCommunication customerCommunication) {
        UpdateWrapper<CustomerCommunication> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", customerCommunication.getId());
        Integer i = customerCommunicationMapper.update(customerCommunication, updateWrapper);
        return i > 0 ? true : false;
    }


    /**
     * 删除
     *
     * @param customerCommunication:
     * @return java.util.List<com.entity.CustomerCommunication>
     * @author lijh
     * @since 2021-06-08
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public Boolean removeEnhance(CustomerCommunication customerCommunication) {
        QueryWrapper<CustomerCommunication> queryWrapper = new QueryWrapper<>(customerCommunication);
        Integer i = customerCommunicationMapper.delete(queryWrapper);
        return i > 0 ? true : false;
    }


    /**
     * 增强查询条件
     *
     * @param customerCommunication:
     * @param queryWrapper:
     * @return void
     * @author lijh
     * @since 2021-06-08
     */
    private void query(CustomerCommunication customerCommunication, QueryWrapper<CustomerCommunication> queryWrapper) {
        /**
         * 排序
         */
        if (customerCommunication.getCollation() != null && StringUtils.isNotBlank(customerCommunication.getCollationFields())) {
            if (customerCommunication.getCollation()) {
                queryWrapper.orderByAsc(customerCommunication.getCollationFields());
            } else {
                queryWrapper.orderByDesc(customerCommunication.getCollationFields());
            }
        } else {
            queryWrapper.orderByDesc(customerCommunication.getCollationFields());
        }

        /**
         * 显示字段
         */
        if (StringUtils.isNotBlank(customerCommunication.getFields())) {
            queryWrapper.select(customerCommunication.getFields());
        }

        /**
         * 标题模糊搜索
         */
        if (StringUtils.isNotBlank(customerCommunication.getTitleQuery())) {
            queryWrapper.like("title", customerCommunication.getTitleQuery());
        }

        /**
         * 跟进记录id模糊搜索
         */
        if (StringUtils.isNotBlank(customerCommunication.getIdQuery())) {
            queryWrapper.like("id", customerCommunication.getIdQuery());
        }

        /**
         * 跟进时间 区间查询
         */
        String startTime = customerCommunication.getDataTimeStart();
        String endTime = customerCommunication.getDateTimeEnd();
        if (StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)) {
            queryWrapper.between("data_time", startTime, endTime);
        }

        /**
         * 下次跟进时间区间查询
         */
        if (StringUtils.isNotBlank(customerCommunication.getNextDataTimeStart()) && StringUtils.isNotBlank(customerCommunication.getNextDataTimeEnd())) {
            queryWrapper.between("next_data_time", customerCommunication.getNextDataTimeStart(), customerCommunication.getNextDataTimeEnd());
        }

        /**
         * 跟进人姓名模糊查
         */
        if (StringUtils.isNotBlank(customerCommunication.getAgentUserNameQuery())) {
            //根据用户名称 模糊 获取用户信息
            Map<String, Object> userMap = new HashMap<>(1);
            userMap.put("nameQuery", customerCommunication.getAgentUserNameQuery());
            List<UserVO> userVOList = rpcComponent.rpcQueryList(userMap, null, RpcTypeEnum.GET_USER_LIST, UserVO.class);
            //匹配所有符合名字的userId   没有设置默认值不查询
            if (CollectionUtils.isNotEmpty(userVOList)) {
                List<String> userList = userVOList.stream().map(UserVO::getId).collect(Collectors.toList());
                queryWrapper.in("user_id", userList);
            } else {
                queryWrapper.eq("user_id", "9999");
            }
        }

    }


    /**
     * 单条，增强返回参数追加
     *
     * @param customerCommunication:
     * @return CustomerCommunication
     * @author lijh
     * @since 2021-06-08
     */
    private CustomerCommunication assignment(CustomerCommunication customerCommunication) {
        return customerCommunication;
    }

    /**
     * 分页,增强返回参数追加
     *
     * @param customerCommunicationList:
     * @return CustomerCommunication
     * @author lijh
     * @since 2021-06-08
     */
    private IPage assignment(IPage<CustomerCommunication> customerCommunicationList) {
        if (CollectionUtils.isNotEmpty(customerCommunicationList.getRecords())) {
            //查询所有跟进人名称
            customerCommunicationList.getRecords().forEach(customerCommunication -> {
                //用户基础信息
                UserVO userVO = userComponent.getOneUserByUserId(customerCommunication.getUserId());
                customerCommunication.setAgentUserNameQuery(userVO.getName());
            });
        }
        return customerCommunicationList;
    }


    /**
     * 集合,增强返回参数追加
     *
     * @param customerCommunicationList:
     * @return CustomerCommunication
     * @author lijh
     * @since 2021-06-08
     */
    private List<CustomerCommunication> assignment(List<CustomerCommunication> customerCommunicationList) {
        customerCommunicationList.forEach(customerCommunication -> {
        });
        return customerCommunicationList;
    }
}
