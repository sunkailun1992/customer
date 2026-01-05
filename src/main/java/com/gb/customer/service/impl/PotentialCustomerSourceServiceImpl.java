package com.gb.customer.service.impl;

import com.gb.customer.entity.PotentialCustomerSource;
import com.gb.customer.enums.SourceTypeEnum;
import com.gb.customer.mapper.PotentialCustomerSourceMapper;
import com.gb.customer.service.PotentialCustomerSourceService;
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

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 潜在客户来源表 服务实现类
 * </p>
 *
 * @author 王一飞
 * @since 2021-04-13
 */
@Slf4j
@Service
@Setter(onMethod_ = {@Autowired})
public class PotentialCustomerSourceServiceImpl extends ServiceImpl<PotentialCustomerSourceMapper, PotentialCustomerSource> implements PotentialCustomerSourceService {


    /**
     * 潜在客户来源表
     */
    private PotentialCustomerSourceMapper potentialCustomerSourceMapper;


    /**
     * 集合条件查询
     *
     * @param potentialCustomerSource:
     * @return java.util.List<com.entity.PotentialCustomerSource>
     * @author 王一飞
     * @since 2021-04-13
     */
    @Override
    public List<PotentialCustomerSource> listEnhance(PotentialCustomerSource potentialCustomerSource) {
        QueryWrapper<PotentialCustomerSource> queryWrapper = new QueryWrapper<>(potentialCustomerSource);
        query(potentialCustomerSource, queryWrapper);
        return assignment(potentialCustomerSourceMapper.selectList(queryWrapper));
    }


    /**
     * 分页条件查询
     *
     * @param potentialCustomerSource:
     * @param page:
     * @return java.util.List<com.entity.PotentialCustomerSource>
     * @author 王一飞
     * @since 2021-04-13
     */
    @Override
    public IPage pageEnhance(Page page, PotentialCustomerSource potentialCustomerSource) {
        QueryWrapper<PotentialCustomerSource> queryWrapper = new QueryWrapper<>(potentialCustomerSource);
        query(potentialCustomerSource, queryWrapper);
        return assignment(potentialCustomerSourceMapper.selectPage(page, queryWrapper));
    }


    /**
     * 单条条件查询
     *
     * @param potentialCustomerSource:
     * @return java.util.List<com.entity.PotentialCustomerSource>
     * @author 王一飞
     * @since 2021-04-13
     */
    @Override
    public PotentialCustomerSource getOneEnhance(PotentialCustomerSource potentialCustomerSource) {
        QueryWrapper<PotentialCustomerSource> queryWrapper = new QueryWrapper<>(potentialCustomerSource);
        query(potentialCustomerSource, queryWrapper);
        return assignment(potentialCustomerSourceMapper.selectOne(queryWrapper));
    }


    /**
     * 总数
     *
     * @param potentialCustomerSource:
     * @return java.util.List<com.entity.PotentialCustomerSource>
     * @author 王一飞
     * @since 2021-04-13
     */
    @Override
    public Long countEnhance(PotentialCustomerSource potentialCustomerSource) {
        QueryWrapper<PotentialCustomerSource> queryWrapper = new QueryWrapper<>(potentialCustomerSource);
        query(potentialCustomerSource, queryWrapper);
        return potentialCustomerSourceMapper.selectCount(queryWrapper);
    }


    /**
     * 新增
     *
     * @param potentialCustomerSource:
     * @return java.util.List<com.entity.PotentialCustomerSource>
     * @author 王一飞
     * @since 2021-04-13
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public Boolean saveEnhance(PotentialCustomerSource potentialCustomerSource) {
        return potentialCustomerSourceMapper.insert(potentialCustomerSource) > 0;
    }


    /**
     * 修改
     *
     * @param potentialCustomerSource:
     * @return java.util.List<com.entity.PotentialCustomerSource>
     * @author 王一飞
     * @since 2021-04-13
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public Boolean updateEnhance(PotentialCustomerSource potentialCustomerSource) {
        UpdateWrapper<PotentialCustomerSource> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", potentialCustomerSource.getId());
        return potentialCustomerSourceMapper.update(potentialCustomerSource, updateWrapper) > 0;
    }


    /**
     * 删除
     *
     * @param potentialCustomerSource:
     * @return java.util.List<com.entity.PotentialCustomerSource>
     * @author 王一飞
     * @since 2021-04-13
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public Boolean removeEnhance(PotentialCustomerSource potentialCustomerSource) {
        QueryWrapper<PotentialCustomerSource> queryWrapper = new QueryWrapper<>(potentialCustomerSource);
        return potentialCustomerSourceMapper.delete(queryWrapper) > 0;
    }


    /**
     * 增强查询条件
     *
     * @param potentialCustomerSource:
     * @param queryWrapper:
     * @return void
     * @author 王一飞
     * @since 2021-04-13
     */
    private void query(PotentialCustomerSource potentialCustomerSource, QueryWrapper<PotentialCustomerSource> queryWrapper) {
        /**
         * 排序
         */
        if (potentialCustomerSource.getCollation() != null && StringUtils.isNotBlank(potentialCustomerSource.getCollationFields())) {
            if (potentialCustomerSource.getCollation()) {
                queryWrapper.orderByAsc(potentialCustomerSource.getCollationFields());
            } else {
                queryWrapper.orderByDesc(potentialCustomerSource.getCollationFields());
            }
        } else {
            queryWrapper.orderByDesc(potentialCustomerSource.getCollationFields());
        }

        /**
         * 显示字段
         */
        if (StringUtils.isNotBlank(potentialCustomerSource.getFields())) {
            queryWrapper.select(potentialCustomerSource.getFields());
        }

        /**
         * 直接匹配
         */
        if (StringUtils.isNotBlank(potentialCustomerSource.getName())) {
            queryWrapper.likeRight("name", potentialCustomerSource.getName());
        }

        if (Objects.nonNull(potentialCustomerSource.getListType())) {
            if (0 == potentialCustomerSource.getListType()) {
                queryWrapper.notIn("name", SourceTypeEnum.SOURCE_TYPE_2.getDesc());
            }
            if (1 == potentialCustomerSource.getListType()) {
                queryWrapper.in("name", SourceTypeEnum.SOURCE_TYPE_1.getDesc()).
                        or().in("name", SourceTypeEnum.SOURCE_TYPE_17.getDesc());
            }
        }
    }


    /**
     * 单条，增强返回参数追加
     *
     * @param potentialCustomerSource:
     * @return PotentialCustomerSource
     * @author 王一飞
     * @since 2021-04-13
     */
    private PotentialCustomerSource assignment(PotentialCustomerSource potentialCustomerSource) {
        return potentialCustomerSource;
    }

    /**
     * 分页,增强返回参数追加
     *
     * @param potentialCustomerSourceList:
     * @return PotentialCustomerSource
     * @author 王一飞
     * @since 2021-04-13
     */
    private IPage assignment(IPage<PotentialCustomerSource> potentialCustomerSourceList) {
        potentialCustomerSourceList.getRecords().forEach(potentialCustomerSource -> {
        });
        return potentialCustomerSourceList;
    }


    /**
     * 集合,增强返回参数追加
     *
     * @param potentialCustomerSourceList:
     * @return PotentialCustomerSource
     * @author 王一飞
     * @since 2021-04-13
     */
    private List<PotentialCustomerSource> assignment(List<PotentialCustomerSource> potentialCustomerSourceList) {
        potentialCustomerSourceList.forEach(potentialCustomerSource -> {
        });
        return potentialCustomerSourceList;
    }
}
