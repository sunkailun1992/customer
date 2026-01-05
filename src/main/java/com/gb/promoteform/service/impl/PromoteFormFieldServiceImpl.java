package com.gb.promoteform.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gb.bean.EntityBase;
import com.gb.promoteform.entity.PromoteFormField;
import com.gb.promoteform.entity.bo.PromoteFormBO;
import com.gb.promoteform.entity.bo.PromoteFormFieldBO;
import com.gb.promoteform.entity.query.PromoteFormFieldQuery;
import com.gb.promoteform.entity.vo.PromoteFormFieldVO;
import com.gb.promoteform.mapper.PromoteFormFieldMapper;
import com.gb.promoteform.service.PromoteFormFieldService;
import com.gb.promoteform.service.query.PromoteFormFieldServiceQuery;
import com.gb.promoteform.service.results.PromoteFormFieldServiceResults;
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
 * TODO 推广表单字段，Service服务实现层
 * 代码生成器
 *
 * @author lijh
 * @className PromoteFormFieldServiceImpl
 * @time 2022-07-04 10:49:04
 */
@Slf4j
@Service
@Setter(onMethod_ = {@Autowired})
public class PromoteFormFieldServiceImpl extends ServiceImpl<PromoteFormFieldMapper, PromoteFormField> implements PromoteFormFieldService {


    /**
     * 推广表单字段
     */
    private PromoteFormFieldMapper promoteFormFieldMapper;


    /**
     * 推广表单字段
     */
    private PromoteFormFieldServiceResults promoteFormFieldServiceResults;


    /**
     * 推广表单字段增强条件
     */
    private PromoteFormFieldServiceQuery promoteFormFieldServiceQuery;


    /**
     * TODO 集合
     *
     * @param promoteFormFieldQuery 推广表单字段
     * @return List<PromoteFormFieldVO>
     * @author lijh
     * @methodName listEnhance
     * @time 2022-07-04 10:49:04
     */
    @Override
    public List<PromoteFormFieldVO> listEnhance(PromoteFormFieldQuery promoteFormFieldQuery) {
        PromoteFormField promoteFormField = GeneralConvertor.convertor(promoteFormFieldQuery, PromoteFormField.class);
        QueryWrapper<PromoteFormField> queryWrapper = new QueryWrapper<>(promoteFormField);
        // TODO 自动生成查询，禁止手动写语句
        promoteFormFieldServiceQuery.query(promoteFormFieldQuery, queryWrapper);
        // TODO 人工查询条件
        queryArtificial(promoteFormFieldQuery, queryWrapper);
        // DO数据
        List<PromoteFormField> promoteFormFieldDO = promoteFormFieldMapper.selectList(queryWrapper);
        // VO数据
        List<PromoteFormFieldVO> promoteFormFieldVO = GeneralConvertor.convertor(promoteFormFieldDO, PromoteFormFieldVO.class);
        // 判断是否增强
        if (promoteFormFieldQuery.getAssignment() == null) {
            return promoteFormFieldServiceResults.assignment(promoteFormFieldVO);
        } else {
            return promoteFormFieldQuery.getAssignment() ? promoteFormFieldServiceResults.assignment(promoteFormFieldVO) : promoteFormFieldVO;
        }
    }


    /**
     * TODO 分页
     *
     * @param page
     * @param promoteFormFieldQuery 推广表单字段
     * @return Page<PromoteFormFieldVO>
     * @author lijh
     * @methodName pageEnhance
     * @time 2022-07-04 10:49:04
     */
    @Override
    public Page<PromoteFormFieldVO> pageEnhance(Page page, PromoteFormFieldQuery promoteFormFieldQuery) {
        PromoteFormField promoteFormField = GeneralConvertor.convertor(promoteFormFieldQuery, PromoteFormField.class);
        QueryWrapper<PromoteFormField> queryWrapper = new QueryWrapper<>(promoteFormField);
        // TODO 自动生成查询，禁止手动写语句
        promoteFormFieldServiceQuery.query(promoteFormFieldQuery, queryWrapper);
        // TODO 人工查询条件
        queryArtificial(promoteFormFieldQuery, queryWrapper);
        // DO数据
        Page<PromoteFormField> pageDO = promoteFormFieldMapper.selectPage(page, queryWrapper);
        // VO数据
        Page<PromoteFormFieldVO> pageVO = promoteFormFieldServiceResults.toPageVO(pageDO);
        // 判断是否增强
        if (promoteFormFieldQuery.getAssignment() == null) {
            return promoteFormFieldServiceResults.assignment(pageVO);
        } else {
            return promoteFormFieldQuery.getAssignment() ? promoteFormFieldServiceResults.assignment(pageVO) : pageVO;
        }
    }


    /**
     * TODO 单条
     *
     * @param promoteFormFieldQuery 推广表单字段
     * @return PromoteFormFieldVO
     * @author lijh
     * @methodName getOneEnhance
     * @time 2022-07-04 10:49:04
     */
    @Override
    public PromoteFormFieldVO getOneEnhance(PromoteFormFieldQuery promoteFormFieldQuery) {
        PromoteFormField promoteFormField = GeneralConvertor.convertor(promoteFormFieldQuery, PromoteFormField.class);
        QueryWrapper<PromoteFormField> queryWrapper = new QueryWrapper<>(promoteFormField);
        // TODO 自动生成查询，禁止手动写语句
        promoteFormFieldServiceQuery.query(promoteFormFieldQuery, queryWrapper);
        // TODO 人工查询条件
        queryArtificial(promoteFormFieldQuery, queryWrapper);
        // DO数据
        PromoteFormField promoteFormFieldDO = promoteFormFieldMapper.selectOne(queryWrapper);
        // VO数据
        PromoteFormFieldVO promoteFormFieldVO = GeneralConvertor.convertor(promoteFormFieldDO, PromoteFormFieldVO.class);
        // 判断是否增强
        if (promoteFormFieldQuery.getAssignment() == null) {
            return promoteFormFieldServiceResults.assignment(promoteFormFieldVO);
        } else {
            return promoteFormFieldQuery.getAssignment() ? promoteFormFieldServiceResults.assignment(promoteFormFieldVO) : promoteFormFieldVO;
        }
    }


    /**
     * TODO 总数
     *
     * @param promoteFormFieldQuery 推广表单字段
     * @return Integer
     * @author lijh
     * @methodName countEnhance
     * @time 2022-07-04 10:49:04
     */
    @Override
    public Long countEnhance(PromoteFormFieldQuery promoteFormFieldQuery) {
        PromoteFormField promoteFormField = GeneralConvertor.convertor(promoteFormFieldQuery, PromoteFormField.class);
        QueryWrapper<PromoteFormField> queryWrapper = new QueryWrapper<>(promoteFormField);
        // TODO 自动生成查询，禁止手动写语句
        promoteFormFieldServiceQuery.query(promoteFormFieldQuery, queryWrapper);
        // TODO 人工查询条件
        queryArtificial(promoteFormFieldQuery, queryWrapper);
        return promoteFormFieldMapper.selectCount(queryWrapper);
    }


    /**
     * TODO 新增
     *
     * @param promoteFormFieldBO 推广表单字段
     * @return String
     * @author lijh
     * @methodName saveEnhance
     * @time 2022-07-04 10:49:04
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public String saveEnhance(PromoteFormFieldBO promoteFormFieldBO) {
        PromoteFormField promoteFormField = GeneralConvertor.convertor(promoteFormFieldBO, PromoteFormField.class);
        promoteFormFieldMapper.insert(promoteFormField);
        return promoteFormField.getId();
    }


    /**
     * TODO 修改
     *
     * @param promoteFormFieldBO 推广表单字段
     * @return Boolean
     * @author lijh
     * @methodName updateEnhance
     * @time 2022-07-04 10:49:04
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public Boolean updateEnhance(PromoteFormFieldBO promoteFormFieldBO) {
        PromoteFormField promoteFormField = GeneralConvertor.convertor(promoteFormFieldBO, PromoteFormField.class);
        UpdateWrapper<PromoteFormField> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", promoteFormFieldBO.getId());
        Integer i = promoteFormFieldMapper.update(promoteFormField, updateWrapper);
        return i > 0 ? true : false;
    }


    /**
     * TODO 删除
     *
     * @param promoteFormFieldBO 推广表单字段
     * @return Boolean
     * @author lijh
     * @methodName removeEnhance
     * @time 2022-07-04 10:49:04
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public Boolean removeEnhance(PromoteFormFieldBO promoteFormFieldBO) {
        PromoteFormField promoteFormField = GeneralConvertor.convertor(promoteFormFieldBO, PromoteFormField.class);
        QueryWrapper<PromoteFormField> queryWrapper = new QueryWrapper<>(promoteFormField);
        Integer i = promoteFormFieldMapper.delete(queryWrapper);
        return i > 0 ? true : false;
    }

    @Override
    public void updateEnhanceByForm(PromoteFormBO promoteFormBO) {
        //要修改的产品信息为空。 删除数据库此表单下的产品
        if (CollectionUtils.isNotEmpty(promoteFormBO.getPromoteFormFieldList())) {
            List<String> collectIds = promoteFormBO.getPromoteFormFieldList().stream().map(EntityBase::getId).collect(Collectors.toList());
            //查询表单下所有产品
            List<String> fieldIdList = this.list(new QueryWrapper<PromoteFormField>().lambda().eq(PromoteFormField::getPromoteFormId, promoteFormBO.getId())).stream().map(EntityBase::getId).collect(Collectors.toList());
            //要修改的产品信息id不包含 数据库里产品id  就删除数据库里产品这条数据
            fieldIdList.forEach(id -> {
                if (!collectIds.contains(id)) {
                    this.removeById(id);
                }
            });
            promoteFormBO.getPromoteFormFieldList().forEach(formField -> {
                //数据库已有产品是否包含产品id  有就修改  无就新增
                if (fieldIdList.contains(formField.getId())) {
                    this.updateById(formField);
                } else {
                    formField.setPromoteFormId(promoteFormBO.getId());
                    this.save(formField);
                }
            });
        } else {
            this.remove(new QueryWrapper<PromoteFormField>().lambda().eq(PromoteFormField::getPromoteFormId, promoteFormBO.getId()));
        }
    }


    /**
     * TODO 人工查询条件
     *
     * @param promoteFormFieldQuery 推广表单字段
     * @return QueryWrapper
     * @author lijh
     * @methodName queryArtificial
     * @time 2022-07-04 10:49:04
     */
    private QueryWrapper queryArtificial(PromoteFormFieldQuery promoteFormFieldQuery, QueryWrapper<PromoteFormField> queryWrapper) {
        return queryWrapper;
    }
}