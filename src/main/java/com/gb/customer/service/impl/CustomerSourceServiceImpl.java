package com.gb.customer.service.impl;

import com.gb.customer.entity.query.CustomerSourceQuery;
import com.gb.customer.entity.vo.CustomerSourceVO;
import com.gb.customer.entity.bo.CustomerSourceBO;
import com.gb.customer.entity.CustomerSource;
import com.gb.customer.mapper.CustomerSourceMapper;
import com.gb.customer.service.CustomerSourceService;
import com.gb.customer.service.query.CustomerSourceServiceQuery;
import com.gb.customer.service.results.CustomerSourceServiceResults;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Setter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import com.gb.utils.GeneralConvertor;

import java.util.List;


/**
 * TODO 客户来源，Service服务实现层
 * 代码生成器
 *
 * @author lijh
 * @className CustomerSourceServiceImpl
 * @time 2022-09-01 03:12:09
 */
@Slf4j
@Service
@Setter(onMethod_ = {@Autowired})
public class CustomerSourceServiceImpl extends ServiceImpl<CustomerSourceMapper, CustomerSource> implements CustomerSourceService {


    /**
     * 客户来源
     */
    private CustomerSourceMapper customerSourceMapper;


    /**
     * 客户来源
     */
    private CustomerSourceServiceResults customerSourceServiceResults;


    /**
     * 客户来源增强条件
     */
    private CustomerSourceServiceQuery customerSourceServiceQuery;


    /**
     * TODO 集合
     *
     * @param customerSourceQuery 客户来源
     * @return List<CustomerSourceVO>
     * @author lijh
     * @methodName listEnhance
     * @time 2022-09-01 03:12:09
     */
    @Override
    public List<CustomerSourceVO> listEnhance(CustomerSourceQuery customerSourceQuery) {
        CustomerSource customerSource = GeneralConvertor.convertor(customerSourceQuery, CustomerSource.class);
        QueryWrapper<CustomerSource> queryWrapper = new QueryWrapper<>(customerSource);
        // TODO 自动生成查询，禁止手动写语句
        customerSourceServiceQuery.query(customerSourceQuery, queryWrapper);
        // TODO 人工查询条件
        queryArtificial(customerSourceQuery, queryWrapper);
        // DO数据
        List<CustomerSource> customerSourceDO = customerSourceMapper.selectList(queryWrapper);
        // VO数据
        List<CustomerSourceVO> customerSourceVO = GeneralConvertor.convertor(customerSourceDO, CustomerSourceVO.class);
        // 判断是否增强
        if (customerSourceQuery.getAssignment() == null) {
            return customerSourceServiceResults.assignment(customerSourceVO);
        } else {
            return customerSourceQuery.getAssignment() ? customerSourceServiceResults.assignment(customerSourceVO) : customerSourceVO;
        }
    }


    /**
     * TODO 分页
     *
     * @param page
     * @param customerSourceQuery 客户来源
     * @return Page<CustomerSourceVO>
     * @author lijh
     * @methodName pageEnhance
     * @time 2022-09-01 03:12:09
     */
    @Override
    public Page<CustomerSourceVO> pageEnhance(Page page, CustomerSourceQuery customerSourceQuery) {
        CustomerSource customerSource = GeneralConvertor.convertor(customerSourceQuery, CustomerSource.class);
        QueryWrapper<CustomerSource> queryWrapper = new QueryWrapper<>(customerSource);
        // TODO 自动生成查询，禁止手动写语句
        customerSourceServiceQuery.query(customerSourceQuery, queryWrapper);
        // TODO 人工查询条件
        queryArtificial(customerSourceQuery, queryWrapper);
        // DO数据
        Page<CustomerSource> pageDO = customerSourceMapper.selectPage(page, queryWrapper);
        // VO数据
        Page<CustomerSourceVO> pageVO = customerSourceServiceResults.toPageVO(pageDO);
        // 判断是否增强
        if (customerSourceQuery.getAssignment() == null) {
            return customerSourceServiceResults.assignment(pageVO);
        } else {
            return customerSourceQuery.getAssignment() ? customerSourceServiceResults.assignment(pageVO) : pageVO;
        }
    }


    /**
     * TODO 单条
     *
     * @param customerSourceQuery 客户来源
     * @return CustomerSourceVO
     * @author lijh
     * @methodName getOneEnhance
     * @time 2022-09-01 03:12:09
     */
    @Override
    public CustomerSourceVO getOneEnhance(CustomerSourceQuery customerSourceQuery) {
        CustomerSource customerSource = GeneralConvertor.convertor(customerSourceQuery, CustomerSource.class);
        QueryWrapper<CustomerSource> queryWrapper = new QueryWrapper<>(customerSource);
        // TODO 自动生成查询，禁止手动写语句
        customerSourceServiceQuery.query(customerSourceQuery, queryWrapper);
        // TODO 人工查询条件
        queryArtificial(customerSourceQuery, queryWrapper);
        // DO数据
        CustomerSource customerSourceDO = customerSourceMapper.selectOne(queryWrapper);
        // VO数据
        CustomerSourceVO customerSourceVO = GeneralConvertor.convertor(customerSourceDO, CustomerSourceVO.class);
        // 判断是否增强
        if (customerSourceQuery.getAssignment() == null) {
            return customerSourceServiceResults.assignment(customerSourceVO);
        } else {
            return customerSourceQuery.getAssignment() ? customerSourceServiceResults.assignment(customerSourceVO) : customerSourceVO;
        }
    }


    /**
     * TODO 总数
     *
     * @param customerSourceQuery 客户来源
     * @return Integer
     * @author lijh
     * @methodName countEnhance
     * @time 2022-09-01 03:12:09
     */
    @Override
    public Long countEnhance(CustomerSourceQuery customerSourceQuery) {
        CustomerSource customerSource = GeneralConvertor.convertor(customerSourceQuery, CustomerSource.class);
        QueryWrapper<CustomerSource> queryWrapper = new QueryWrapper<>(customerSource);
        // TODO 自动生成查询，禁止手动写语句
        customerSourceServiceQuery.query(customerSourceQuery, queryWrapper);
        // TODO 人工查询条件
        queryArtificial(customerSourceQuery, queryWrapper);
        return customerSourceMapper.selectCount(queryWrapper);
    }


    /**
     * TODO 新增
     *
     * @param customerSourceBO 客户来源
     * @return String
     * @author lijh
     * @methodName saveEnhance
     * @time 2022-09-01 03:12:09
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public String saveEnhance(CustomerSourceBO customerSourceBO) {
        CustomerSource customerSource = GeneralConvertor.convertor(customerSourceBO, CustomerSource.class);
        customerSourceMapper.insert(customerSource);
        return customerSource.getId();
    }


    /**
     * TODO 修改
     *
     * @param customerSourceBO 客户来源
     * @return Boolean
     * @author lijh
     * @methodName updateEnhance
     * @time 2022-09-01 03:12:09
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public Boolean updateEnhance(CustomerSourceBO customerSourceBO) {
        CustomerSource customerSource = GeneralConvertor.convertor(customerSourceBO, CustomerSource.class);
        UpdateWrapper<CustomerSource> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", customerSourceBO.getId());
        Integer i = customerSourceMapper.update(customerSource, updateWrapper);
        return i > 0 ? true : false;
    }


    /**
     * TODO 删除
     *
     * @param customerSourceBO 客户来源
     * @return Boolean
     * @author lijh
     * @methodName removeEnhance
     * @time 2022-09-01 03:12:09
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public Boolean removeEnhance(CustomerSourceBO customerSourceBO) {
        CustomerSource customerSource = GeneralConvertor.convertor(customerSourceBO, CustomerSource.class);
        QueryWrapper<CustomerSource> queryWrapper = new QueryWrapper<>(customerSource);
        Integer i = customerSourceMapper.delete(queryWrapper);
        return i > 0 ? true : false;
    }


    /**
     * TODO 人工查询条件
     *
     * @param customerSourceQuery 客户来源
     * @return QueryWrapper
     * @author lijh
     * @methodName queryArtificial
     * @time 2022-09-01 03:12:09
     */
    private QueryWrapper queryArtificial(CustomerSourceQuery customerSourceQuery, QueryWrapper<CustomerSource> queryWrapper) {
        return queryWrapper;
    }
}