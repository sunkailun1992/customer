package com.gb.promoteform.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gb.bean.EntityBase;
import com.gb.promoteform.entity.PromoteFormButton;
import com.gb.promoteform.entity.bo.PromoteFormBO;
import com.gb.promoteform.entity.bo.PromoteFormButtonBO;
import com.gb.promoteform.entity.query.PromoteFormButtonQuery;
import com.gb.promoteform.entity.vo.PromoteFormButtonVO;
import com.gb.promoteform.mapper.PromoteFormButtonMapper;
import com.gb.promoteform.service.PromoteFormButtonService;
import com.gb.promoteform.service.query.PromoteFormButtonServiceQuery;
import com.gb.promoteform.service.results.PromoteFormButtonServiceResults;
import com.gb.utils.GeneralConvertor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * TODO 推广表单按钮，Service服务实现层
 * 代码生成器
 *
 * @author lijh
 * @className PromoteFormButtonServiceImpl
 * @time 2022-07-04 10:49:04
 */
@Slf4j
@Service
@Setter(onMethod_ = {@Autowired})
public class PromoteFormButtonServiceImpl extends ServiceImpl<PromoteFormButtonMapper, PromoteFormButton> implements PromoteFormButtonService {


    /**
     * 推广表单按钮
     */
    private PromoteFormButtonMapper promoteFormButtonMapper;


    /**
     * 推广表单按钮
     */
    private PromoteFormButtonServiceResults promoteFormButtonServiceResults;


    /**
     * 推广表单按钮增强条件
     */
    private PromoteFormButtonServiceQuery promoteFormButtonServiceQuery;


    /**
     * TODO 集合
     *
     * @param promoteFormButtonQuery 推广表单按钮
     * @return List<PromoteFormButtonVO>
     * @author lijh
     * @methodName listEnhance
     * @time 2022-07-04 10:49:04
     */
    @Override
    public List<PromoteFormButtonVO> listEnhance(PromoteFormButtonQuery promoteFormButtonQuery) {
        PromoteFormButton promoteFormButton = GeneralConvertor.convertor(promoteFormButtonQuery, PromoteFormButton.class);
        QueryWrapper<PromoteFormButton> queryWrapper = new QueryWrapper<>(promoteFormButton);
        // TODO 自动生成查询，禁止手动写语句
        promoteFormButtonServiceQuery.query(promoteFormButtonQuery, queryWrapper);
        // TODO 人工查询条件
        queryArtificial(promoteFormButtonQuery, queryWrapper);
        // DO数据
        List<PromoteFormButton> promoteFormButtonDO = promoteFormButtonMapper.selectList(queryWrapper);
        // VO数据
        List<PromoteFormButtonVO> promoteFormButtonVO = GeneralConvertor.convertor(promoteFormButtonDO, PromoteFormButtonVO.class);
        // 判断是否增强
        if (promoteFormButtonQuery.getAssignment() == null) {
            return promoteFormButtonServiceResults.assignment(promoteFormButtonVO);
        } else {
            return promoteFormButtonQuery.getAssignment() ? promoteFormButtonServiceResults.assignment(promoteFormButtonVO) : promoteFormButtonVO;
        }
    }


    /**
     * TODO 分页
     *
     * @param page
     * @param promoteFormButtonQuery 推广表单按钮
     * @return Page<PromoteFormButtonVO>
     * @author lijh
     * @methodName pageEnhance
     * @time 2022-07-04 10:49:04
     */
    @Override
    public Page<PromoteFormButtonVO> pageEnhance(Page page, PromoteFormButtonQuery promoteFormButtonQuery) {
        PromoteFormButton promoteFormButton = GeneralConvertor.convertor(promoteFormButtonQuery, PromoteFormButton.class);
        QueryWrapper<PromoteFormButton> queryWrapper = new QueryWrapper<>(promoteFormButton);
        // TODO 自动生成查询，禁止手动写语句
        promoteFormButtonServiceQuery.query(promoteFormButtonQuery, queryWrapper);
        // TODO 人工查询条件
        queryArtificial(promoteFormButtonQuery, queryWrapper);
        // DO数据
        Page<PromoteFormButton> pageDO = promoteFormButtonMapper.selectPage(page, queryWrapper);
        // VO数据
        Page<PromoteFormButtonVO> pageVO = promoteFormButtonServiceResults.toPageVO(pageDO);
        // 判断是否增强
        if (promoteFormButtonQuery.getAssignment() == null) {
            return promoteFormButtonServiceResults.assignment(pageVO);
        } else {
            return promoteFormButtonQuery.getAssignment() ? promoteFormButtonServiceResults.assignment(pageVO) : pageVO;
        }
    }


    /**
     * TODO 单条
     *
     * @param promoteFormButtonQuery 推广表单按钮
     * @return PromoteFormButtonVO
     * @author lijh
     * @methodName getOneEnhance
     * @time 2022-07-04 10:49:04
     */
    @Override
    public PromoteFormButtonVO getOneEnhance(PromoteFormButtonQuery promoteFormButtonQuery) {
        PromoteFormButton promoteFormButton = GeneralConvertor.convertor(promoteFormButtonQuery, PromoteFormButton.class);
        QueryWrapper<PromoteFormButton> queryWrapper = new QueryWrapper<>(promoteFormButton);
        // TODO 自动生成查询，禁止手动写语句
        promoteFormButtonServiceQuery.query(promoteFormButtonQuery, queryWrapper);
        // TODO 人工查询条件
        queryArtificial(promoteFormButtonQuery, queryWrapper);
        // DO数据
        PromoteFormButton promoteFormButtonDO = promoteFormButtonMapper.selectOne(queryWrapper);
        // VO数据
        PromoteFormButtonVO promoteFormButtonVO = GeneralConvertor.convertor(promoteFormButtonDO, PromoteFormButtonVO.class);
        // 判断是否增强
        if (promoteFormButtonQuery.getAssignment() == null) {
            return promoteFormButtonServiceResults.assignment(promoteFormButtonVO);
        } else {
            return promoteFormButtonQuery.getAssignment() ? promoteFormButtonServiceResults.assignment(promoteFormButtonVO) : promoteFormButtonVO;
        }
    }


    /**
     * TODO 总数
     *
     * @param promoteFormButtonQuery 推广表单按钮
     * @return Integer
     * @author lijh
     * @methodName countEnhance
     * @time 2022-07-04 10:49:04
     */
    @Override
    public Long countEnhance(PromoteFormButtonQuery promoteFormButtonQuery) {
        PromoteFormButton promoteFormButton = GeneralConvertor.convertor(promoteFormButtonQuery, PromoteFormButton.class);
        QueryWrapper<PromoteFormButton> queryWrapper = new QueryWrapper<>(promoteFormButton);
        // TODO 自动生成查询，禁止手动写语句
        promoteFormButtonServiceQuery.query(promoteFormButtonQuery, queryWrapper);
        // TODO 人工查询条件
        queryArtificial(promoteFormButtonQuery, queryWrapper);
        return promoteFormButtonMapper.selectCount(queryWrapper);
    }


    /**
     * TODO 新增
     *
     * @param promoteFormButtonBO 推广表单按钮
     * @return String
     * @author lijh
     * @methodName saveEnhance
     * @time 2022-07-04 10:49:04
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public String saveEnhance(PromoteFormButtonBO promoteFormButtonBO) {
        PromoteFormButton promoteFormButton = GeneralConvertor.convertor(promoteFormButtonBO, PromoteFormButton.class);
        promoteFormButtonMapper.insert(promoteFormButton);
        return promoteFormButton.getId();
    }


    /**
     * TODO 修改
     *
     * @param promoteFormButtonBO 推广表单按钮
     * @return Boolean
     * @author lijh
     * @methodName updateEnhance
     * @time 2022-07-04 10:49:04
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public Boolean updateEnhance(PromoteFormButtonBO promoteFormButtonBO) {
        PromoteFormButton promoteFormButton = GeneralConvertor.convertor(promoteFormButtonBO, PromoteFormButton.class);
        UpdateWrapper<PromoteFormButton> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", promoteFormButtonBO.getId());
        Integer i = promoteFormButtonMapper.update(promoteFormButton, updateWrapper);
        return i > 0 ? true : false;
    }


    /**
     * TODO 删除
     *
     * @param promoteFormButtonBO 推广表单按钮
     * @return Boolean
     * @author lijh
     * @methodName removeEnhance
     * @time 2022-07-04 10:49:04
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public Boolean removeEnhance(PromoteFormButtonBO promoteFormButtonBO) {
        PromoteFormButton promoteFormButton = GeneralConvertor.convertor(promoteFormButtonBO, PromoteFormButton.class);
        QueryWrapper<PromoteFormButton> queryWrapper = new QueryWrapper<>(promoteFormButton);
        Integer i = promoteFormButtonMapper.delete(queryWrapper);
        return i > 0 ? true : false;
    }

    @Override
    public void updateEnhanceByForm(PromoteFormBO promoteFormBO) {
        //合并list
        List<PromoteFormButton> promoteFormButtonList = Stream.of(CollectionUtils.isNotEmpty(promoteFormBO.getPromoteFormButtonList()) ? promoteFormBO.getPromoteFormButtonList() : new ArrayList<PromoteFormButton>(),
                CollectionUtils.isNotEmpty(promoteFormBO.getPromoteFormBottomList()) ? promoteFormBO.getPromoteFormBottomList() : new ArrayList<PromoteFormButton>(),
                CollectionUtils.isNotEmpty(promoteFormBO.getPromoteFormFixedBottomList()) ? promoteFormBO.getPromoteFormFixedBottomList() : new ArrayList<PromoteFormButton>()).
                flatMap(Collection::stream).collect(Collectors.toList());

        if (CollectionUtils.isNotEmpty(promoteFormButtonList)) {
            List<String> collectIds = promoteFormButtonList.stream().map(EntityBase::getId).collect(Collectors.toList());
            //查询表单下所有按钮
            List<String> buttonIdList = this.list(new QueryWrapper<PromoteFormButton>().lambda().eq(PromoteFormButton::getPromoteFormId, promoteFormBO.getId())).stream().map(EntityBase::getId).collect(Collectors.toList());
            //参数的按钮id不包含 库里按钮id  就删除
            buttonIdList.forEach(id -> {
                if (!collectIds.contains(id)) {
                    this.removeById(id);
                }
            });
            promoteFormButtonList.forEach(formButton -> {
                if (buttonIdList.contains(formButton.getId())) {
                    this.updateById(formButton);
                } else {
                    formButton.setPromoteFormId(promoteFormBO.getId());
                    this.save(formButton);
                }
            });
        } else {
            this.remove(new QueryWrapper<PromoteFormButton>().lambda().eq(PromoteFormButton::getPromoteFormId, promoteFormBO.getId()));
        }
    }


    /**
     * TODO 人工查询条件
     *
     * @param promoteFormButtonQuery 推广表单按钮
     * @return QueryWrapper
     * @author lijh
     * @methodName queryArtificial
     * @time 2022-07-04 10:49:04
     */
    private QueryWrapper queryArtificial(PromoteFormButtonQuery promoteFormButtonQuery, QueryWrapper<PromoteFormButton> queryWrapper) {
        return queryWrapper;
    }
}