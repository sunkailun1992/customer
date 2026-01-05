package com.gb.promoteform.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.gb.bean.EntityBase;
import com.gb.promoteform.entity.PromoteFormProduct;
import com.gb.promoteform.entity.query.PromoteFormProductSpuQuery;
import com.gb.promoteform.entity.vo.PromoteFormProductSpuVO;
import com.gb.promoteform.entity.bo.PromoteFormProductSpuBO;
import com.gb.promoteform.entity.PromoteFormProductSpu;
import com.gb.promoteform.mapper.PromoteFormProductSpuMapper;
import com.gb.promoteform.service.PromoteFormProductSpuService;
import com.gb.promoteform.service.query.PromoteFormProductSpuServiceQuery;
import com.gb.promoteform.service.results.PromoteFormProductSpuServiceResults;
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
import java.util.stream.Collectors;


/**
 * TODO 推广表单产品id，Service服务实现层
 * 代码生成器
 *
 * @author lijh
 * @className PromoteFormProductSpuServiceImpl
 * @time 2022-10-28 03:09:32
 */
@Slf4j
@Service
@Setter(onMethod_ = {@Autowired})
public class PromoteFormProductSpuServiceImpl extends ServiceImpl<PromoteFormProductSpuMapper, PromoteFormProductSpu> implements PromoteFormProductSpuService {


    /**
     * 推广表单产品id
     */
    private PromoteFormProductSpuMapper promoteFormProductSpuMapper;


    /**
     * 推广表单产品id
     */
    private PromoteFormProductSpuServiceResults promoteFormProductSpuServiceResults;


    /**
     * 推广表单产品id增强条件
     */
    private PromoteFormProductSpuServiceQuery promoteFormProductSpuServiceQuery;


    /**
     * TODO 集合
     *
     * @param promoteFormProductSpuQuery 推广表单产品id
     * @return List<PromoteFormProductSpuVO>
     * @author lijh
     * @methodName listEnhance
     * @time 2022-10-28 03:09:32
     */
    @Override
    public List<PromoteFormProductSpuVO> listEnhance(PromoteFormProductSpuQuery promoteFormProductSpuQuery) {
        PromoteFormProductSpu promoteFormProductSpu = GeneralConvertor.convertor(promoteFormProductSpuQuery, PromoteFormProductSpu.class);
        QueryWrapper<PromoteFormProductSpu> queryWrapper = new QueryWrapper<>(promoteFormProductSpu);
        // TODO 自动生成查询，禁止手动写语句
        promoteFormProductSpuServiceQuery.query(promoteFormProductSpuQuery, queryWrapper);
        // TODO 人工查询条件
        queryArtificial(promoteFormProductSpuQuery, queryWrapper);
        // DO数据
        List<PromoteFormProductSpu> promoteFormProductSpuDO = promoteFormProductSpuMapper.selectList(queryWrapper);
        // VO数据
        List<PromoteFormProductSpuVO> promoteFormProductSpuVO = GeneralConvertor.convertor(promoteFormProductSpuDO, PromoteFormProductSpuVO.class);
        // 判断是否增强
        if (promoteFormProductSpuQuery.getAssignment() == null) {
            return promoteFormProductSpuServiceResults.assignment(promoteFormProductSpuVO);
        } else {
            return promoteFormProductSpuQuery.getAssignment() ? promoteFormProductSpuServiceResults.assignment(promoteFormProductSpuVO) : promoteFormProductSpuVO;
        }
    }


    /**
     * TODO 分页
     *
     * @param page
     * @param promoteFormProductSpuQuery 推广表单产品id
     * @return Page<PromoteFormProductSpuVO>
     * @author lijh
     * @methodName pageEnhance
     * @time 2022-10-28 03:09:32
     */
    @Override
    public Page<PromoteFormProductSpuVO> pageEnhance(Page page, PromoteFormProductSpuQuery promoteFormProductSpuQuery) {
        PromoteFormProductSpu promoteFormProductSpu = GeneralConvertor.convertor(promoteFormProductSpuQuery, PromoteFormProductSpu.class);
        QueryWrapper<PromoteFormProductSpu> queryWrapper = new QueryWrapper<>(promoteFormProductSpu);
        // TODO 自动生成查询，禁止手动写语句
        promoteFormProductSpuServiceQuery.query(promoteFormProductSpuQuery, queryWrapper);
        // TODO 人工查询条件
        queryArtificial(promoteFormProductSpuQuery, queryWrapper);
        // DO数据
        Page<PromoteFormProductSpu> pageDO = promoteFormProductSpuMapper.selectPage(page, queryWrapper);
        // VO数据
        Page<PromoteFormProductSpuVO> pageVO = promoteFormProductSpuServiceResults.toPageVO(pageDO);
        // 判断是否增强
        if (promoteFormProductSpuQuery.getAssignment() == null) {
            return promoteFormProductSpuServiceResults.assignment(pageVO);
        } else {
            return promoteFormProductSpuQuery.getAssignment() ? promoteFormProductSpuServiceResults.assignment(pageVO) : pageVO;
        }
    }


    /**
     * TODO 单条
     *
     * @param promoteFormProductSpuQuery 推广表单产品id
     * @return PromoteFormProductSpuVO
     * @author lijh
     * @methodName getOneEnhance
     * @time 2022-10-28 03:09:32
     */
    @Override
    public PromoteFormProductSpuVO getOneEnhance(PromoteFormProductSpuQuery promoteFormProductSpuQuery) {
        PromoteFormProductSpu promoteFormProductSpu = GeneralConvertor.convertor(promoteFormProductSpuQuery, PromoteFormProductSpu.class);
        QueryWrapper<PromoteFormProductSpu> queryWrapper = new QueryWrapper<>(promoteFormProductSpu);
        // TODO 自动生成查询，禁止手动写语句
        promoteFormProductSpuServiceQuery.query(promoteFormProductSpuQuery, queryWrapper);
        // TODO 人工查询条件
        queryArtificial(promoteFormProductSpuQuery, queryWrapper);
        // DO数据
        PromoteFormProductSpu promoteFormProductSpuDO = promoteFormProductSpuMapper.selectOne(queryWrapper);
        // VO数据
        PromoteFormProductSpuVO promoteFormProductSpuVO = GeneralConvertor.convertor(promoteFormProductSpuDO, PromoteFormProductSpuVO.class);
        // 判断是否增强
        if (promoteFormProductSpuQuery.getAssignment() == null) {
            return promoteFormProductSpuServiceResults.assignment(promoteFormProductSpuVO);
        } else {
            return promoteFormProductSpuQuery.getAssignment() ? promoteFormProductSpuServiceResults.assignment(promoteFormProductSpuVO) : promoteFormProductSpuVO;
        }
    }


    /**
     * TODO 总数
     *
     * @param promoteFormProductSpuQuery 推广表单产品id
     * @return Integer
     * @author lijh
     * @methodName countEnhance
     * @time 2022-10-28 03:09:32
     */
    @Override
    public Long countEnhance(PromoteFormProductSpuQuery promoteFormProductSpuQuery) {
        PromoteFormProductSpu promoteFormProductSpu = GeneralConvertor.convertor(promoteFormProductSpuQuery, PromoteFormProductSpu.class);
        QueryWrapper<PromoteFormProductSpu> queryWrapper = new QueryWrapper<>(promoteFormProductSpu);
        // TODO 自动生成查询，禁止手动写语句
        promoteFormProductSpuServiceQuery.query(promoteFormProductSpuQuery, queryWrapper);
        // TODO 人工查询条件
        queryArtificial(promoteFormProductSpuQuery, queryWrapper);
        return promoteFormProductSpuMapper.selectCount(queryWrapper);
    }


    /**
     * TODO 新增
     *
     * @param promoteFormProductSpuBO 推广表单产品id
     * @return String
     * @author lijh
     * @methodName saveEnhance
     * @time 2022-10-28 03:09:32
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public String saveEnhance(PromoteFormProductSpuBO promoteFormProductSpuBO) {
        PromoteFormProductSpu promoteFormProductSpu = GeneralConvertor.convertor(promoteFormProductSpuBO, PromoteFormProductSpu.class);
        promoteFormProductSpuMapper.insert(promoteFormProductSpu);
        return promoteFormProductSpu.getId();
    }


    /**
     * TODO 修改
     *
     * @param promoteFormProductSpuBO 推广表单产品id
     * @return Boolean
     * @author lijh
     * @methodName updateEnhance
     * @time 2022-10-28 03:09:32
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public Boolean updateEnhance(PromoteFormProductSpuBO promoteFormProductSpuBO) {
        PromoteFormProductSpu promoteFormProductSpu = GeneralConvertor.convertor(promoteFormProductSpuBO, PromoteFormProductSpu.class);
        UpdateWrapper<PromoteFormProductSpu> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", promoteFormProductSpuBO.getId());
        Integer i = promoteFormProductSpuMapper.update(promoteFormProductSpu, updateWrapper);
        return i > 0 ? true : false;
    }


    /**
     * TODO 删除
     *
     * @param promoteFormProductSpuBO 推广表单产品id
     * @return Boolean
     * @author lijh
     * @methodName removeEnhance
     * @time 2022-10-28 03:09:32
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public Boolean removeEnhance(PromoteFormProductSpuBO promoteFormProductSpuBO) {
        PromoteFormProductSpu promoteFormProductSpu = GeneralConvertor.convertor(promoteFormProductSpuBO, PromoteFormProductSpu.class);
        QueryWrapper<PromoteFormProductSpu> queryWrapper = new QueryWrapper<>(promoteFormProductSpu);
        Integer i = promoteFormProductSpuMapper.delete(queryWrapper);
        return i > 0 ? true : false;
    }

    @Override
    public List<PromoteFormProductSpu> listByFormProductId(String promoteFormProductId) {
        List<PromoteFormProductSpu> promoteFormProductSpuDO = promoteFormProductSpuMapper.selectList(new QueryWrapper<PromoteFormProductSpu>().lambda().eq(PromoteFormProductSpu::getPromoteFormProductId, promoteFormProductId));
        return promoteFormProductSpuServiceResults.assignmentDo(promoteFormProductSpuDO);
    }

    @Override
    public void updateEnhanceByFormProduct(PromoteFormProduct promoteFormProduct) {
        if (CollectionUtils.isNotEmpty(promoteFormProduct.getPromoteFormProductSpuList())) {
            List<String> collectIds = promoteFormProduct.getPromoteFormProductSpuList().stream().map(EntityBase::getId).collect(Collectors.toList());
            //查询产品对象下的  产品id
            List<String> productSpuIdList = this.list(new QueryWrapper<PromoteFormProductSpu>().lambda().eq(PromoteFormProductSpu::getPromoteFormProductId, promoteFormProduct.getId())).stream().map(EntityBase::getId).collect(Collectors.toList());
            //修改信息的产品id不包含 库里产品id  就删除这条产品
            productSpuIdList.forEach(id -> {
                if (!collectIds.contains(id)) {
                    this.removeById(id);
                }
            });
            promoteFormProduct.getPromoteFormProductSpuList().forEach(formProductSpu -> {
                //数据库已有产品是否包含产品id  有就修改  无就新增
                if (productSpuIdList.contains(formProductSpu.getId())) {
                    //投保链接地址 和 跳转链接地址 只能存在一个
                    this.updateById(formProductSpu);
                } else {
                    formProductSpu.setPromoteFormProductId(promoteFormProduct.getId());
                    this.save(formProductSpu);
                }
            });
        } else {
            this.remove(new QueryWrapper<PromoteFormProductSpu>().lambda().eq(PromoteFormProductSpu::getPromoteFormProductId, promoteFormProduct.getId()));
        }
    }


    /**
     * TODO 人工查询条件
     *
     * @param promoteFormProductSpuQuery 推广表单产品id
     * @return QueryWrapper
     * @author lijh
     * @methodName queryArtificial
     * @time 2022-10-28 03:09:32
     */
    private QueryWrapper queryArtificial(PromoteFormProductSpuQuery promoteFormProductSpuQuery, QueryWrapper<PromoteFormProductSpu> queryWrapper) {
        return queryWrapper;
    }
}