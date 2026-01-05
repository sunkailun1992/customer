package com.gb.promoteform.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gb.bean.EntityBase;
import com.gb.promoteform.entity.PromoteFormProduct;
import com.gb.promoteform.entity.bo.PromoteFormBO;
import com.gb.promoteform.entity.bo.PromoteFormProductBO;
import com.gb.promoteform.entity.query.PromoteFormProductQuery;
import com.gb.promoteform.entity.vo.PromoteFormProductVO;
import com.gb.promoteform.mapper.PromoteFormProductMapper;
import com.gb.promoteform.service.PromoteFormProductService;
import com.gb.promoteform.service.PromoteFormProductSpuService;
import com.gb.promoteform.service.query.PromoteFormProductServiceQuery;
import com.gb.promoteform.service.results.PromoteFormProductServiceResults;
import com.gb.utils.GeneralConvertor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


/**
 * TODO 推广表单产品，Service服务实现层
 * 代码生成器
 *
 * @author lijh
 * @className PromoteFormProductServiceImpl
 * @time 2022-07-04 10:49:04
 */
@Slf4j
@Service
@Setter(onMethod_ = {@Autowired})
public class PromoteFormProductServiceImpl extends ServiceImpl<PromoteFormProductMapper, PromoteFormProduct> implements PromoteFormProductService {


    /**
     * 推广表单产品
     */
    private PromoteFormProductMapper promoteFormProductMapper;


    /**
     * 推广表单产品
     */
    private PromoteFormProductServiceResults promoteFormProductServiceResults;


    /**
     * 推广表单产品增强条件
     */
    private PromoteFormProductServiceQuery promoteFormProductServiceQuery;

    private PromoteFormProductSpuService promoteFormProductSpuService;


    /**
     * TODO 集合
     *
     * @param promoteFormProductQuery 推广表单产品
     * @return List<PromoteFormProductVO>
     * @author lijh
     * @methodName listEnhance
     * @time 2022-07-04 10:49:04
     */
    @Override
    public List<PromoteFormProductVO> listEnhance(PromoteFormProductQuery promoteFormProductQuery) {
        PromoteFormProduct promoteFormProduct = GeneralConvertor.convertor(promoteFormProductQuery, PromoteFormProduct.class);
        QueryWrapper<PromoteFormProduct> queryWrapper = new QueryWrapper<>(promoteFormProduct);
        // TODO 自动生成查询，禁止手动写语句
        promoteFormProductServiceQuery.query(promoteFormProductQuery, queryWrapper);
        // TODO 人工查询条件
        queryArtificial(promoteFormProductQuery, queryWrapper);
        // DO数据
        List<PromoteFormProduct> promoteFormProductDO = promoteFormProductMapper.selectList(queryWrapper);
        // VO数据
        List<PromoteFormProductVO> promoteFormProductVO = GeneralConvertor.convertor(promoteFormProductDO, PromoteFormProductVO.class);
        // 判断是否增强
        if (promoteFormProductQuery.getAssignment() == null) {
            return promoteFormProductServiceResults.assignment(promoteFormProductVO);
        } else {
            return promoteFormProductQuery.getAssignment() ? promoteFormProductServiceResults.assignment(promoteFormProductVO) : promoteFormProductVO;
        }
    }


    /**
     * TODO 分页
     *
     * @param page
     * @param promoteFormProductQuery 推广表单产品
     * @return Page<PromoteFormProductVO>
     * @author lijh
     * @methodName pageEnhance
     * @time 2022-07-04 10:49:04
     */
    @Override
    public Page<PromoteFormProductVO> pageEnhance(Page page, PromoteFormProductQuery promoteFormProductQuery) {
        PromoteFormProduct promoteFormProduct = GeneralConvertor.convertor(promoteFormProductQuery, PromoteFormProduct.class);
        QueryWrapper<PromoteFormProduct> queryWrapper = new QueryWrapper<>(promoteFormProduct);
        // TODO 自动生成查询，禁止手动写语句
        promoteFormProductServiceQuery.query(promoteFormProductQuery, queryWrapper);
        // TODO 人工查询条件
        queryArtificial(promoteFormProductQuery, queryWrapper);
        // DO数据
        Page<PromoteFormProduct> pageDO = promoteFormProductMapper.selectPage(page, queryWrapper);
        // VO数据
        Page<PromoteFormProductVO> pageVO = promoteFormProductServiceResults.toPageVO(pageDO);
        // 判断是否增强
        if (promoteFormProductQuery.getAssignment() == null) {
            return promoteFormProductServiceResults.assignment(pageVO);
        } else {
            return promoteFormProductQuery.getAssignment() ? promoteFormProductServiceResults.assignment(pageVO) : pageVO;
        }
    }


    /**
     * TODO 单条
     *
     * @param promoteFormProductQuery 推广表单产品
     * @return PromoteFormProductVO
     * @author lijh
     * @methodName getOneEnhance
     * @time 2022-07-04 10:49:04
     */
    @Override
    public PromoteFormProductVO getOneEnhance(PromoteFormProductQuery promoteFormProductQuery) {
        PromoteFormProduct promoteFormProduct = GeneralConvertor.convertor(promoteFormProductQuery, PromoteFormProduct.class);
        QueryWrapper<PromoteFormProduct> queryWrapper = new QueryWrapper<>(promoteFormProduct);
        // TODO 自动生成查询，禁止手动写语句
        promoteFormProductServiceQuery.query(promoteFormProductQuery, queryWrapper);
        // TODO 人工查询条件
        queryArtificial(promoteFormProductQuery, queryWrapper);
        // DO数据
        PromoteFormProduct promoteFormProductDO = promoteFormProductMapper.selectOne(queryWrapper);
        // VO数据
        PromoteFormProductVO promoteFormProductVO = GeneralConvertor.convertor(promoteFormProductDO, PromoteFormProductVO.class);
        // 判断是否增强
        if (promoteFormProductQuery.getAssignment() == null) {
            return promoteFormProductServiceResults.assignment(promoteFormProductVO);
        } else {
            return promoteFormProductQuery.getAssignment() ? promoteFormProductServiceResults.assignment(promoteFormProductVO) : promoteFormProductVO;
        }
    }


    /**
     * TODO 总数
     *
     * @param promoteFormProductQuery 推广表单产品
     * @return Integer
     * @author lijh
     * @methodName countEnhance
     * @time 2022-07-04 10:49:04
     */
    @Override
    public Long countEnhance(PromoteFormProductQuery promoteFormProductQuery) {
        PromoteFormProduct promoteFormProduct = GeneralConvertor.convertor(promoteFormProductQuery, PromoteFormProduct.class);
        QueryWrapper<PromoteFormProduct> queryWrapper = new QueryWrapper<>(promoteFormProduct);
        // TODO 自动生成查询，禁止手动写语句
        promoteFormProductServiceQuery.query(promoteFormProductQuery, queryWrapper);
        // TODO 人工查询条件
        queryArtificial(promoteFormProductQuery, queryWrapper);
        return promoteFormProductMapper.selectCount(queryWrapper);
    }


    /**
     * TODO 新增
     *
     * @param promoteFormProductBO 推广表单产品
     * @return String
     * @author lijh
     * @methodName saveEnhance
     * @time 2022-07-04 10:49:04
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public String saveEnhance(PromoteFormProductBO promoteFormProductBO) {
        PromoteFormProduct promoteFormProduct = GeneralConvertor.convertor(promoteFormProductBO, PromoteFormProduct.class);
        promoteFormProductMapper.insert(promoteFormProduct);
        return promoteFormProduct.getId();
    }


    /**
     * TODO 修改
     *
     * @param promoteFormProductBO 推广表单产品
     * @return Boolean
     * @author lijh
     * @methodName updateEnhance
     * @time 2022-07-04 10:49:04
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public Boolean updateEnhance(PromoteFormProductBO promoteFormProductBO) {
        PromoteFormProduct promoteFormProduct = GeneralConvertor.convertor(promoteFormProductBO, PromoteFormProduct.class);
        UpdateWrapper<PromoteFormProduct> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", promoteFormProductBO.getId());
        Integer i = promoteFormProductMapper.update(promoteFormProduct, updateWrapper);
        return i > 0 ? true : false;
    }


    /**
     * TODO 删除
     *
     * @param promoteFormProductBO 推广表单产品
     * @return Boolean
     * @author lijh
     * @methodName removeEnhance
     * @time 2022-07-04 10:49:04
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public Boolean removeEnhance(PromoteFormProductBO promoteFormProductBO) {
        PromoteFormProduct promoteFormProduct = GeneralConvertor.convertor(promoteFormProductBO, PromoteFormProduct.class);
        QueryWrapper<PromoteFormProduct> queryWrapper = new QueryWrapper<>(promoteFormProduct);
        Integer i = promoteFormProductMapper.delete(queryWrapper);
        return i > 0 ? true : false;
    }

    @Override
    public void updateEnhanceByForm(PromoteFormBO promoteFormBO) {
        if (CollectionUtils.isNotEmpty(promoteFormBO.getPromoteFormProductList())) {
            List<String> collectIds = promoteFormBO.getPromoteFormProductList().stream().map(EntityBase::getId).collect(Collectors.toList());
            //查询表单下所有产品
            List<String> productIdList = this.list(new QueryWrapper<PromoteFormProduct>().lambda().eq(PromoteFormProduct::getPromoteFormId, promoteFormBO.getId())).stream().map(EntityBase::getId).collect(Collectors.toList());
            //修改信息的产品id不包含 库里产品id  就删除这条产品
            productIdList.forEach(id -> {
                if (!collectIds.contains(id)) {
                    this.removeById(id);
                }
            });
            promoteFormBO.getPromoteFormProductList().forEach(formProduct -> {
                //数据库已有产品是否包含产品id  有就修改  无就新增
                if (productIdList.contains(formProduct.getId())) {
                    //投保链接地址 和 跳转链接地址 只能存在一个
                    this.updateById(formProduct);
                } else {
                    formProduct.setPromoteFormId(promoteFormBO.getId());
                    this.save(formProduct);
                }
                promoteFormProductSpuService.updateEnhanceByFormProduct(formProduct);
            });
        } else {
            this.remove(new QueryWrapper<PromoteFormProduct>().lambda().eq(PromoteFormProduct::getPromoteFormId, promoteFormBO.getId()));
        }
    }


    /**
     * TODO 人工查询条件
     *
     * @param promoteFormProductQuery 推广表单产品
     * @return QueryWrapper
     * @author lijh
     * @methodName queryArtificial
     * @time 2022-07-04 10:49:04
     */
    private QueryWrapper queryArtificial(PromoteFormProductQuery promoteFormProductQuery, QueryWrapper<PromoteFormProduct> queryWrapper) {
        return queryWrapper;
    }
}