package com.gb.customer.service.impl;

import com.gb.customer.mapper.PotentialCustomerCommunicationMapper;
import com.gb.customer.entity.PotentialCustomerCommunication;
import com.gb.customer.service.PotentialCustomerCommunicationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * <p>
 * 潜在客户沟通表 服务实现类
 * </p>
 *
 * @author 王一飞
 * @since 2021-04-13
 */
@Slf4j
@Service
@Setter(onMethod_ = {@Autowired})
public class PotentialCustomerCommunicationServiceImpl extends ServiceImpl<PotentialCustomerCommunicationMapper, PotentialCustomerCommunication> implements PotentialCustomerCommunicationService {


    /**
     * 潜在客户沟通表
     */
    private PotentialCustomerCommunicationMapper potentialCustomerCommunicationMapper;


    /**
     * 集合条件查询
     * @author 王一飞
     * @since 2021-04-13
     * @param potentialCustomerCommunication:
     * @return      java.util.List<com.entity.PotentialCustomerCommunication>
     */
    @Override
    public List<PotentialCustomerCommunication> listEnhance(PotentialCustomerCommunication potentialCustomerCommunication) {
        QueryWrapper<PotentialCustomerCommunication> queryWrapper = new QueryWrapper<>(potentialCustomerCommunication);
        query(potentialCustomerCommunication, queryWrapper);
        return assignment(potentialCustomerCommunicationMapper.selectList(queryWrapper));
    }


    /**
     * 分页条件查询
     * @author 王一飞
     * @since 2021-04-13
     * @param potentialCustomerCommunication:
     * @param page:
     * @return      java.util.List<com.entity.PotentialCustomerCommunication>
     */
    @Override
    public IPage pageEnhance(Page page, PotentialCustomerCommunication potentialCustomerCommunication) {
        QueryWrapper<PotentialCustomerCommunication> queryWrapper = new QueryWrapper<>(potentialCustomerCommunication);
        query(potentialCustomerCommunication, queryWrapper);
        return assignment(potentialCustomerCommunicationMapper.selectPage(page, queryWrapper));
    }


    /**
     * 单条条件查询
     * @author 王一飞
     * @since 2021-04-13
     * @param potentialCustomerCommunication:
     * @return      java.util.List<com.entity.PotentialCustomerCommunication>
     */
    @Override
    public PotentialCustomerCommunication getOneEnhance(PotentialCustomerCommunication potentialCustomerCommunication) {
        QueryWrapper<PotentialCustomerCommunication> queryWrapper = new QueryWrapper<>(potentialCustomerCommunication);
        query(potentialCustomerCommunication, queryWrapper);
        return assignment(potentialCustomerCommunicationMapper.selectOne(queryWrapper));
    }


    /**
     * 总数
     * @author 王一飞
     * @since 2021-04-13
     * @param potentialCustomerCommunication:
     * @return      java.util.List<com.entity.PotentialCustomerCommunication>
     */
    @Override
    public Long countEnhance(PotentialCustomerCommunication potentialCustomerCommunication) {
        QueryWrapper<PotentialCustomerCommunication> queryWrapper = new QueryWrapper<>(potentialCustomerCommunication);
        query(potentialCustomerCommunication, queryWrapper);
        return potentialCustomerCommunicationMapper.selectCount(queryWrapper);
    }


    /**
     * 新增
     * @author 王一飞
     * @since 2021-04-13
     * @param potentialCustomerCommunication:
     * @return      java.util.List<com.entity.PotentialCustomerCommunication>
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public Boolean saveEnhance(PotentialCustomerCommunication potentialCustomerCommunication) {
        return         potentialCustomerCommunicationMapper.insert(potentialCustomerCommunication) > 0;
    }


    /**
     * 修改
     * @author 王一飞
     * @since 2021-04-13
     * @param potentialCustomerCommunication:
     * @return      java.util.List<com.entity.PotentialCustomerCommunication>
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public Boolean updateEnhance(PotentialCustomerCommunication potentialCustomerCommunication) {
        UpdateWrapper<PotentialCustomerCommunication > updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", potentialCustomerCommunication.getId());
        return potentialCustomerCommunicationMapper.update(potentialCustomerCommunication, updateWrapper) > 0;
    }


    /**
     * 删除
     * @author 王一飞
     * @since 2021-04-13
     * @param potentialCustomerCommunication:
     * @return      java.util.List<com.entity.PotentialCustomerCommunication>
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public Boolean removeEnhance(PotentialCustomerCommunication potentialCustomerCommunication) {
        QueryWrapper<PotentialCustomerCommunication> queryWrapper = new QueryWrapper<>(potentialCustomerCommunication);
        return potentialCustomerCommunicationMapper.delete(queryWrapper) > 0;
    }


    /**
     * 增强查询条件
     *
     * @author 王一飞
     * @since 2021-04-13
     * @param potentialCustomerCommunication:
     * @param queryWrapper:
     * @return      void
     */
    private void query(PotentialCustomerCommunication potentialCustomerCommunication, QueryWrapper<PotentialCustomerCommunication> queryWrapper) {
        /**
         * 排序
         */
        if(potentialCustomerCommunication.getCollation() != null && StringUtils.isNotBlank(potentialCustomerCommunication.getCollationFields())){
            if(potentialCustomerCommunication.getCollation()){
                queryWrapper.orderByAsc(potentialCustomerCommunication.getCollationFields());
            }else{
                queryWrapper.orderByDesc(potentialCustomerCommunication.getCollationFields());
            }
        }else{
            queryWrapper.orderByDesc(potentialCustomerCommunication.getCollationFields());
        }

        /**
        * 显示字段
        */
        if(StringUtils.isNotBlank(potentialCustomerCommunication.getFields())){
            queryWrapper.select(potentialCustomerCommunication.getFields());
        }
    }


    /**
     * 单条，增强返回参数追加
     *
     * @author 王一飞
     * @since 2021-04-13
     * @param potentialCustomerCommunication:
     *
     * @return      PotentialCustomerCommunication
     */
    private PotentialCustomerCommunication assignment(PotentialCustomerCommunication potentialCustomerCommunication) {
        return potentialCustomerCommunication;
    }

    /**
     * 分页,增强返回参数追加
     *
     * @author 王一飞
     * @since 2021-04-13
     * @param potentialCustomerCommunicationList:
     *
     * @return      PotentialCustomerCommunication
     */
    private IPage assignment(IPage<PotentialCustomerCommunication> potentialCustomerCommunicationList) {
        potentialCustomerCommunicationList.getRecords().forEach(potentialCustomerCommunication -> {
        });
        return potentialCustomerCommunicationList;
    }


    /**
     * 集合,增强返回参数追加
     *
     * @author 王一飞
     * @since 2021-04-13
     * @param potentialCustomerCommunicationList:
     *
     * @return      PotentialCustomerCommunication
     */
    private List<PotentialCustomerCommunication> assignment(List<PotentialCustomerCommunication> potentialCustomerCommunicationList) {
        potentialCustomerCommunicationList.forEach(potentialCustomerCommunication -> {
        });
        return potentialCustomerCommunicationList;
    }
}
