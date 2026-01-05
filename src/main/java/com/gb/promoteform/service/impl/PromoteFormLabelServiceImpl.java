package com.gb.promoteform.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gb.promoteform.entity.PromoteFormLabel;
import com.gb.promoteform.entity.bo.PromoteFormLabelBO;
import com.gb.promoteform.entity.query.PromoteFormLabelQuery;
import com.gb.promoteform.entity.vo.PromoteFormLabelVO;
import com.gb.promoteform.mapper.PromoteFormLabelMapper;
import com.gb.promoteform.service.PromoteFormLabelService;
import com.gb.promoteform.service.query.PromoteFormLabelServiceQuery;
import com.gb.promoteform.service.results.PromoteFormLabelServiceResults;
import com.gb.rpc.entity.UserLabelInfoVO;
import com.gb.rpc.entity.enums.RpcTypeEnum;
import com.gb.rpc.impl.RpcComponent;
import com.gb.utils.GeneralConvertor;
import com.gb.utils.exception.BusinessException;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * TODO 推广表单标签，Service服务实现层
 * 代码生成器
 *
 * @author lijh
 * @className PromoteFormLabelServiceImpl
 * @time 2022-07-04 10:49:04
 */
@Slf4j
@Service
@Setter(onMethod_ = {@Autowired})
public class PromoteFormLabelServiceImpl extends ServiceImpl<PromoteFormLabelMapper, PromoteFormLabel> implements PromoteFormLabelService {


    /**
     * 推广表单标签
     */
    private PromoteFormLabelMapper promoteFormLabelMapper;


    /**
     * 推广表单标签
     */
    private PromoteFormLabelServiceResults promoteFormLabelServiceResults;


    /**
     * 推广表单标签增强条件
     */
    private PromoteFormLabelServiceQuery promoteFormLabelServiceQuery;

    private RpcComponent rpcComponent;


    /**
     * TODO 集合
     *
     * @param promoteFormLabelQuery 推广表单标签
     * @return List<PromoteFormLabelVO>
     * @author lijh
     * @methodName listEnhance
     * @time 2022-07-04 10:49:04
     */
    @Override
    public List<PromoteFormLabelVO> listEnhance(PromoteFormLabelQuery promoteFormLabelQuery) {
        PromoteFormLabel promoteFormLabel = GeneralConvertor.convertor(promoteFormLabelQuery, PromoteFormLabel.class);
        QueryWrapper<PromoteFormLabel> queryWrapper = new QueryWrapper<>(promoteFormLabel);
        // TODO 自动生成查询，禁止手动写语句
        promoteFormLabelServiceQuery.query(promoteFormLabelQuery, queryWrapper);
        // TODO 人工查询条件
        queryArtificial(promoteFormLabelQuery, queryWrapper);
        // DO数据
        List<PromoteFormLabel> promoteFormLabelDO = promoteFormLabelMapper.selectList(queryWrapper);
        // VO数据
        List<PromoteFormLabelVO> promoteFormLabelVO = GeneralConvertor.convertor(promoteFormLabelDO, PromoteFormLabelVO.class);
        // 判断是否增强
        if (promoteFormLabelQuery.getAssignment() == null) {
            return promoteFormLabelServiceResults.assignment(promoteFormLabelVO);
        } else {
            return promoteFormLabelQuery.getAssignment() ? promoteFormLabelServiceResults.assignment(promoteFormLabelVO) : promoteFormLabelVO;
        }
    }


    /**
     * TODO 分页
     *
     * @param page
     * @param promoteFormLabelQuery 推广表单标签
     * @return Page<PromoteFormLabelVO>
     * @author lijh
     * @methodName pageEnhance
     * @time 2022-07-04 10:49:04
     */
    @Override
    public Page<PromoteFormLabelVO> pageEnhance(Page page, PromoteFormLabelQuery promoteFormLabelQuery) {
        PromoteFormLabel promoteFormLabel = GeneralConvertor.convertor(promoteFormLabelQuery, PromoteFormLabel.class);
        QueryWrapper<PromoteFormLabel> queryWrapper = new QueryWrapper<>(promoteFormLabel);
        // TODO 自动生成查询，禁止手动写语句
        promoteFormLabelServiceQuery.query(promoteFormLabelQuery, queryWrapper);
        // TODO 人工查询条件
        queryArtificial(promoteFormLabelQuery, queryWrapper);
        // DO数据
        Page<PromoteFormLabel> pageDO = promoteFormLabelMapper.selectPage(page, queryWrapper);
        // VO数据
        Page<PromoteFormLabelVO> pageVO = promoteFormLabelServiceResults.toPageVO(pageDO);
        // 判断是否增强
        if (promoteFormLabelQuery.getAssignment() == null) {
            return promoteFormLabelServiceResults.assignment(pageVO);
        } else {
            return promoteFormLabelQuery.getAssignment() ? promoteFormLabelServiceResults.assignment(pageVO) : pageVO;
        }
    }


    /**
     * TODO 单条
     *
     * @param promoteFormLabelQuery 推广表单标签
     * @return PromoteFormLabelVO
     * @author lijh
     * @methodName getOneEnhance
     * @time 2022-07-04 10:49:04
     */
    @Override
    public PromoteFormLabelVO getOneEnhance(PromoteFormLabelQuery promoteFormLabelQuery) {
        PromoteFormLabel promoteFormLabel = GeneralConvertor.convertor(promoteFormLabelQuery, PromoteFormLabel.class);
        QueryWrapper<PromoteFormLabel> queryWrapper = new QueryWrapper<>(promoteFormLabel);
        // TODO 自动生成查询，禁止手动写语句
        promoteFormLabelServiceQuery.query(promoteFormLabelQuery, queryWrapper);
        // TODO 人工查询条件
        queryArtificial(promoteFormLabelQuery, queryWrapper);
        // DO数据
        PromoteFormLabel promoteFormLabelDO = promoteFormLabelMapper.selectOne(queryWrapper);
        // VO数据
        PromoteFormLabelVO promoteFormLabelVO = GeneralConvertor.convertor(promoteFormLabelDO, PromoteFormLabelVO.class);
        // 判断是否增强
        if (promoteFormLabelQuery.getAssignment() == null) {
            return promoteFormLabelServiceResults.assignment(promoteFormLabelVO);
        } else {
            return promoteFormLabelQuery.getAssignment() ? promoteFormLabelServiceResults.assignment(promoteFormLabelVO) : promoteFormLabelVO;
        }
    }


    /**
     * TODO 总数
     *
     * @param promoteFormLabelQuery 推广表单标签
     * @return Integer
     * @author lijh
     * @methodName countEnhance
     * @time 2022-07-04 10:49:04
     */
    @Override
    public Long countEnhance(PromoteFormLabelQuery promoteFormLabelQuery) {
        PromoteFormLabel promoteFormLabel = GeneralConvertor.convertor(promoteFormLabelQuery, PromoteFormLabel.class);
        QueryWrapper<PromoteFormLabel> queryWrapper = new QueryWrapper<>(promoteFormLabel);
        // TODO 自动生成查询，禁止手动写语句
        promoteFormLabelServiceQuery.query(promoteFormLabelQuery, queryWrapper);
        // TODO 人工查询条件
        queryArtificial(promoteFormLabelQuery, queryWrapper);
        return promoteFormLabelMapper.selectCount(queryWrapper);
    }


    /**
     * TODO 新增
     *
     * @param promoteFormLabelBO 推广表单标签
     * @return String
     * @author lijh
     * @methodName saveEnhance
     * @time 2022-07-04 10:49:04
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public String saveEnhance(PromoteFormLabelBO promoteFormLabelBO) {
        PromoteFormLabel label = promoteFormLabelMapper.selectOne(new QueryWrapper<PromoteFormLabel>().lambda().eq(PromoteFormLabel::getLinkAddress, promoteFormLabelBO.getLinkAddress()));
        if (Objects.nonNull(label)){
            throw new BusinessException("此标签的投放链接已存在");
        }
        PromoteFormLabel promoteFormLabel = GeneralConvertor.convertor(promoteFormLabelBO, PromoteFormLabel.class);
        promoteFormLabelMapper.insert(promoteFormLabel);
        return promoteFormLabel.getId();
    }


    /**
     * TODO 修改
     *
     * @param promoteFormLabelBO 推广表单标签
     * @return Boolean
     * @author lijh
     * @methodName updateEnhance
     * @time 2022-07-04 10:49:04
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public Boolean updateEnhance(PromoteFormLabelBO promoteFormLabelBO) {
        PromoteFormLabel promoteFormLabel = GeneralConvertor.convertor(promoteFormLabelBO, PromoteFormLabel.class);
        UpdateWrapper<PromoteFormLabel> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", promoteFormLabelBO.getId());
        Integer i = promoteFormLabelMapper.update(promoteFormLabel, updateWrapper);
        return i > 0 ? true : false;
    }


    /**
     * TODO 删除
     *
     * @param promoteFormLabelBO 推广表单标签
     * @return Boolean
     * @author lijh
     * @methodName removeEnhance
     * @time 2022-07-04 10:49:04
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public Boolean removeEnhance(PromoteFormLabelBO promoteFormLabelBO) {
        PromoteFormLabel promoteFormLabel = GeneralConvertor.convertor(promoteFormLabelBO, PromoteFormLabel.class);
        QueryWrapper<PromoteFormLabel> queryWrapper = new QueryWrapper<>(promoteFormLabel);
        Integer i = promoteFormLabelMapper.delete(queryWrapper);
        return i > 0 ? true : false;
    }

    @Override
    public List<UserLabelInfoVO> getLabelList(String labelName) {
        HashMap<String, Object> hashMap = new HashMap<>(2);
        //是否已认证   1是0否
        hashMap.put("queryType", "1");
        //一级标签管家
        hashMap.put("userTypeCode", "4");
        List<UserLabelInfoVO> userLabelInfoVOList = rpcComponent.rpcQueryList(hashMap, null, RpcTypeEnum.GET_LABEL_USER_TYPE, UserLabelInfoVO.class);
        //一级标签经纪人
        hashMap.put("userTypeCode", "1");
        List<UserLabelInfoVO> agentLabelInfoVOList = rpcComponent.rpcQueryList(hashMap, null, RpcTypeEnum.GET_LABEL_USER_TYPE, UserLabelInfoVO.class);

        //合并集合
        List<UserLabelInfoVO> collect = Stream.of(userLabelInfoVOList, agentLabelInfoVOList).flatMap(Collection::stream).collect(Collectors.toList());
        if (StringUtils.isNotBlank(labelName)) {
            return collect.stream().filter(userLabelInfoVO -> userLabelInfoVO.getUserTypeValueName().contains(labelName)).collect(Collectors.toList());
        }
        return collect;
    }


    /**
     * TODO 人工查询条件
     *
     * @param promoteFormLabelQuery 推广表单标签
     * @return QueryWrapper
     * @author lijh
     * @methodName queryArtificial
     * @time 2022-07-04 10:49:04
     */
    private QueryWrapper queryArtificial(PromoteFormLabelQuery promoteFormLabelQuery, QueryWrapper<PromoteFormLabel> queryWrapper) {
        return queryWrapper;
    }
}