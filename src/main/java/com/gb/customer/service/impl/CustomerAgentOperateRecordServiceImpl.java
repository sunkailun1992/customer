package com.gb.customer.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gb.customer.entity.CustomerAgentOperateRecord;
import com.gb.customer.entity.bo.CustomerAgentOperateRecordBO;
import com.gb.customer.entity.enums.CustomerAgentOperateRecordOperationEnum;
import com.gb.customer.entity.enums.CustomerAgentOperateRecordSourceEnum;
import com.gb.customer.entity.query.CustomerAgentOperateRecordQuery;
import com.gb.customer.entity.vo.CustomerAgentOperateRecordVO;
import com.gb.customer.mapper.CustomerAgentOperateRecordMapper;
import com.gb.customer.service.CustomerAgentOperateRecordService;
import com.gb.customer.service.query.CustomerAgentOperateRecordServiceQuery;
import com.gb.customer.service.results.CustomerAgentOperateRecordServiceResults;
import com.gb.utils.GeneralConvertor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * TODO 客户经纪人操作记录，Service服务实现层
 * 代码生成器
 *
 * @author lijh
 * @className CustomerAgentOperateRecordServiceImpl
 * @time 2022-09-20 11:02:25
 */
@Slf4j
@Service
@Setter(onMethod_ = {@Autowired})
public class CustomerAgentOperateRecordServiceImpl extends ServiceImpl<CustomerAgentOperateRecordMapper, CustomerAgentOperateRecord> implements CustomerAgentOperateRecordService {


    /**
     * 客户经纪人操作记录
     */
    private CustomerAgentOperateRecordMapper customerAgentOperateRecordMapper;


    /**
     * 客户经纪人操作记录
     */
    private CustomerAgentOperateRecordServiceResults customerAgentOperateRecordServiceResults;


    /**
     * 客户经纪人操作记录增强条件
     */
    private CustomerAgentOperateRecordServiceQuery customerAgentOperateRecordServiceQuery;


    /**
     * TODO 集合
     *
     * @param customerAgentOperateRecordQuery 客户经纪人操作记录
     * @return List<CustomerAgentOperateRecordVO>
     * @author lijh
     * @methodName listEnhance
     * @time 2022-09-20 11:02:25
     */
    @Override
    public List<CustomerAgentOperateRecordVO> listEnhance(CustomerAgentOperateRecordQuery customerAgentOperateRecordQuery) {
        CustomerAgentOperateRecord customerAgentOperateRecord = GeneralConvertor.convertor(customerAgentOperateRecordQuery, CustomerAgentOperateRecord.class);
        QueryWrapper<CustomerAgentOperateRecord> queryWrapper = new QueryWrapper<>(customerAgentOperateRecord);
        // TODO 自动生成查询，禁止手动写语句
        customerAgentOperateRecordServiceQuery.query(customerAgentOperateRecordQuery, queryWrapper);
        // TODO 人工查询条件
        queryArtificial(customerAgentOperateRecordQuery, queryWrapper);
        // DO数据
        List<CustomerAgentOperateRecord> customerAgentOperateRecordDO = customerAgentOperateRecordMapper.selectList(queryWrapper);
        // VO数据
        List<CustomerAgentOperateRecordVO> customerAgentOperateRecordVO = GeneralConvertor.convertor(customerAgentOperateRecordDO, CustomerAgentOperateRecordVO.class);
        // 判断是否增强
        if (customerAgentOperateRecordQuery.getAssignment() == null) {
            return customerAgentOperateRecordServiceResults.assignment(customerAgentOperateRecordVO);
        } else {
            return customerAgentOperateRecordQuery.getAssignment() ? customerAgentOperateRecordServiceResults.assignment(customerAgentOperateRecordVO) : customerAgentOperateRecordVO;
        }
    }


    /**
     * TODO 分页
     *
     * @param page
     * @param customerAgentOperateRecordQuery 客户经纪人操作记录
     * @return Page<CustomerAgentOperateRecordVO>
     * @author lijh
     * @methodName pageEnhance
     * @time 2022-09-20 11:02:25
     */
    @Override
    public Page<CustomerAgentOperateRecordVO> pageEnhance(Page page, CustomerAgentOperateRecordQuery customerAgentOperateRecordQuery) {
        CustomerAgentOperateRecord customerAgentOperateRecord = GeneralConvertor.convertor(customerAgentOperateRecordQuery, CustomerAgentOperateRecord.class);
        QueryWrapper<CustomerAgentOperateRecord> queryWrapper = new QueryWrapper<>(customerAgentOperateRecord);
        // TODO 自动生成查询，禁止手动写语句
        customerAgentOperateRecordServiceQuery.query(customerAgentOperateRecordQuery, queryWrapper);
        // TODO 人工查询条件
        queryArtificial(customerAgentOperateRecordQuery, queryWrapper);
        // DO数据
        Page<CustomerAgentOperateRecord> pageDO = customerAgentOperateRecordMapper.selectPage(page, queryWrapper);
        // VO数据
        Page<CustomerAgentOperateRecordVO> pageVO = customerAgentOperateRecordServiceResults.toPageVO(pageDO);
        // 判断是否增强
        if (customerAgentOperateRecordQuery.getAssignment() == null) {
            return customerAgentOperateRecordServiceResults.assignment(pageVO);
        } else {
            return customerAgentOperateRecordQuery.getAssignment() ? customerAgentOperateRecordServiceResults.assignment(pageVO) : pageVO;
        }
    }


    /**
     * TODO 单条
     *
     * @param customerAgentOperateRecordQuery 客户经纪人操作记录
     * @return CustomerAgentOperateRecordVO
     * @author lijh
     * @methodName getOneEnhance
     * @time 2022-09-20 11:02:25
     */
    @Override
    public CustomerAgentOperateRecordVO getOneEnhance(CustomerAgentOperateRecordQuery customerAgentOperateRecordQuery) {
        CustomerAgentOperateRecord customerAgentOperateRecord = GeneralConvertor.convertor(customerAgentOperateRecordQuery, CustomerAgentOperateRecord.class);
        QueryWrapper<CustomerAgentOperateRecord> queryWrapper = new QueryWrapper<>(customerAgentOperateRecord);
        // TODO 自动生成查询，禁止手动写语句
        customerAgentOperateRecordServiceQuery.query(customerAgentOperateRecordQuery, queryWrapper);
        // TODO 人工查询条件
        queryArtificial(customerAgentOperateRecordQuery, queryWrapper);
        // DO数据
        CustomerAgentOperateRecord customerAgentOperateRecordDO = customerAgentOperateRecordMapper.selectOne(queryWrapper);
        // VO数据
        CustomerAgentOperateRecordVO customerAgentOperateRecordVO = GeneralConvertor.convertor(customerAgentOperateRecordDO, CustomerAgentOperateRecordVO.class);
        // 判断是否增强
        if (customerAgentOperateRecordQuery.getAssignment() == null) {
            return customerAgentOperateRecordServiceResults.assignment(customerAgentOperateRecordVO);
        } else {
            return customerAgentOperateRecordQuery.getAssignment() ? customerAgentOperateRecordServiceResults.assignment(customerAgentOperateRecordVO) : customerAgentOperateRecordVO;
        }
    }


    /**
     * TODO 总数
     *
     * @param customerAgentOperateRecordQuery 客户经纪人操作记录
     * @return Integer
     * @author lijh
     * @methodName countEnhance
     * @time 2022-09-20 11:02:25
     */
    @Override
    public Long countEnhance(CustomerAgentOperateRecordQuery customerAgentOperateRecordQuery) {
        CustomerAgentOperateRecord customerAgentOperateRecord = GeneralConvertor.convertor(customerAgentOperateRecordQuery, CustomerAgentOperateRecord.class);
        QueryWrapper<CustomerAgentOperateRecord> queryWrapper = new QueryWrapper<>(customerAgentOperateRecord);
        // TODO 自动生成查询，禁止手动写语句
        customerAgentOperateRecordServiceQuery.query(customerAgentOperateRecordQuery, queryWrapper);
        // TODO 人工查询条件
        queryArtificial(customerAgentOperateRecordQuery, queryWrapper);
        return customerAgentOperateRecordMapper.selectCount(queryWrapper);
    }


    /**
     * TODO 新增
     *
     * @param customerAgentOperateRecordBO 客户经纪人操作记录
     * @return String
     * @author lijh
     * @methodName saveEnhance
     * @time 2022-09-20 11:02:25
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public String saveEnhance(CustomerAgentOperateRecordBO customerAgentOperateRecordBO) {
        CustomerAgentOperateRecord customerAgentOperateRecord = GeneralConvertor.convertor(customerAgentOperateRecordBO, CustomerAgentOperateRecord.class);
        customerAgentOperateRecordMapper.insert(customerAgentOperateRecord);
        return customerAgentOperateRecord.getId();
    }


    /**
     * TODO 修改
     *
     * @param customerAgentOperateRecordBO 客户经纪人操作记录
     * @return Boolean
     * @author lijh
     * @methodName updateEnhance
     * @time 2022-09-20 11:02:25
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public Boolean updateEnhance(CustomerAgentOperateRecordBO customerAgentOperateRecordBO) {
        CustomerAgentOperateRecord customerAgentOperateRecord = GeneralConvertor.convertor(customerAgentOperateRecordBO, CustomerAgentOperateRecord.class);
        UpdateWrapper<CustomerAgentOperateRecord> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", customerAgentOperateRecordBO.getId());
        Integer i = customerAgentOperateRecordMapper.update(customerAgentOperateRecord, updateWrapper);
        return i > 0;
    }


    /**
     * TODO 删除
     *
     * @param customerAgentOperateRecordBO 客户经纪人操作记录
     * @return Boolean
     * @author lijh
     * @methodName removeEnhance
     * @time 2022-09-20 11:02:25
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public Boolean removeEnhance(CustomerAgentOperateRecordBO customerAgentOperateRecordBO) {
        CustomerAgentOperateRecord customerAgentOperateRecord = GeneralConvertor.convertor(customerAgentOperateRecordBO, CustomerAgentOperateRecord.class);
        QueryWrapper<CustomerAgentOperateRecord> queryWrapper = new QueryWrapper<>(customerAgentOperateRecord);
        Integer i = customerAgentOperateRecordMapper.delete(queryWrapper);
        return i > 0;
    }

    /**
     * 新增分配记录
     *
     * @param customerId                              客户id
     * @param agentUserId                             经纪人id
     * @param customerAgentOperateRecordSourceEnum    操作来源
     * @param customerAgentOperateRecordOperationEnum 操作类型
     * @param createName                              操作人
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public void operateRecord(String customerId, String agentUserId, CustomerAgentOperateRecordSourceEnum customerAgentOperateRecordSourceEnum,
                              CustomerAgentOperateRecordOperationEnum customerAgentOperateRecordOperationEnum, String createName) {
        CustomerAgentOperateRecord customerAgentOperateRecord = new CustomerAgentOperateRecord();
        customerAgentOperateRecord.setCustomerId(customerId);
        customerAgentOperateRecord.setAgentUserId(agentUserId);
        customerAgentOperateRecord.setSource(customerAgentOperateRecordSourceEnum);
        customerAgentOperateRecord.setOperation(customerAgentOperateRecordOperationEnum);
        customerAgentOperateRecord.setCreateName(createName);
        customerAgentOperateRecordMapper.insert(customerAgentOperateRecord);
    }


    /**
     * TODO 人工查询条件
     *
     * @param customerAgentOperateRecordQuery 客户经纪人操作记录
     * @return QueryWrapper
     * @author lijh
     * @methodName queryArtificial
     * @time 2022-09-20 11:02:25
     */
    private QueryWrapper queryArtificial(CustomerAgentOperateRecordQuery customerAgentOperateRecordQuery, QueryWrapper<CustomerAgentOperateRecord> queryWrapper) {
        return queryWrapper;
    }
}