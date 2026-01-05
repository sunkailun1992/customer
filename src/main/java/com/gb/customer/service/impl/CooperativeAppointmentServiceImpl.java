package com.gb.customer.service.impl;

import com.gb.customer.entity.enums.CooperativeAppointmentStateEnum;
import com.gb.customer.entity.query.CooperativeAppointmentQuery;
import com.gb.customer.entity.vo.CooperativeAppointmentVO;
import com.gb.customer.entity.bo.CooperativeAppointmentBO;
import com.gb.customer.entity.CooperativeAppointment;
import com.gb.customer.mapper.CooperativeAppointmentMapper;
import com.gb.customer.service.CooperativeAppointmentService;
import com.gb.customer.service.query.CooperativeAppointmentServiceQuery;
import com.gb.customer.service.results.CooperativeAppointmentServiceResults;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gb.log.entity.CooperativeAppointmentLog;
import com.gb.log.service.CooperativeAppointmentLogService;
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
 * TODO 云服合作预约，Service服务实现层
 * 代码生成器
 *
 * @author ljh
 * @className CooperativeAppointmentServiceImpl
 * @time 2023-01-09 02:56:41
 */
@Slf4j
@Service
@Setter(onMethod_ = {@Autowired})
public class CooperativeAppointmentServiceImpl extends ServiceImpl<CooperativeAppointmentMapper, CooperativeAppointment> implements CooperativeAppointmentService {


    /**
     * 云服合作预约
     */
    private CooperativeAppointmentMapper cooperativeAppointmentMapper;


    /**
     * 云服合作预约
     */
    private CooperativeAppointmentServiceResults cooperativeAppointmentServiceResults;


    /**
     * 云服合作预约增强条件
     */
    private CooperativeAppointmentServiceQuery cooperativeAppointmentServiceQuery;

    /**
     * 云服合作预约操作日志
     */
    private CooperativeAppointmentLogService cooperativeAppointmentLogService;


    /**
     * TODO 集合
     *
     * @param cooperativeAppointmentQuery 云服合作预约
     * @return List<CooperativeAppointmentVO>
     * @author ljh
     * @methodName listEnhance
     * @time 2023-01-09 02:56:41
     */
    @Override
    public List<CooperativeAppointmentVO> listEnhance(CooperativeAppointmentQuery cooperativeAppointmentQuery) {
        CooperativeAppointment cooperativeAppointment = GeneralConvertor.convertor(cooperativeAppointmentQuery, CooperativeAppointment.class);
        QueryWrapper<CooperativeAppointment> queryWrapper = new QueryWrapper<>(cooperativeAppointment);
        // TODO 自动生成查询，禁止手动写语句
        cooperativeAppointmentServiceQuery.query(cooperativeAppointmentQuery, queryWrapper);
        // TODO 人工查询条件
        queryArtificial(cooperativeAppointmentQuery, queryWrapper);
        // DO数据
        List<CooperativeAppointment> cooperativeAppointmentDO = cooperativeAppointmentMapper.selectList(queryWrapper);
        // VO数据
        List<CooperativeAppointmentVO> cooperativeAppointmentVO = GeneralConvertor.convertor(cooperativeAppointmentDO, CooperativeAppointmentVO.class);
        // 判断是否增强
        if (cooperativeAppointmentQuery.getAssignment() == null) {
            return cooperativeAppointmentServiceResults.assignment(cooperativeAppointmentVO);
        } else {
            return cooperativeAppointmentQuery.getAssignment() ? cooperativeAppointmentServiceResults.assignment(cooperativeAppointmentVO) : cooperativeAppointmentVO;
        }
    }


    /**
     * TODO 分页
     *
     * @param page
     * @param cooperativeAppointmentQuery 云服合作预约
     * @return Page<CooperativeAppointmentVO>
     * @author ljh
     * @methodName pageEnhance
     * @time 2023-01-09 02:56:41
     */
    @Override
    public Page<CooperativeAppointmentVO> pageEnhance(Page page, CooperativeAppointmentQuery cooperativeAppointmentQuery) {
        CooperativeAppointment cooperativeAppointment = GeneralConvertor.convertor(cooperativeAppointmentQuery, CooperativeAppointment.class);
        QueryWrapper<CooperativeAppointment> queryWrapper = new QueryWrapper<>(cooperativeAppointment);
        // TODO 自动生成查询，禁止手动写语句
        cooperativeAppointmentServiceQuery.query(cooperativeAppointmentQuery, queryWrapper);
        // TODO 人工查询条件
        queryArtificial(cooperativeAppointmentQuery, queryWrapper);
        // DO数据
        Page<CooperativeAppointment> pageDO = cooperativeAppointmentMapper.selectPage(page, queryWrapper);
        // VO数据
        Page<CooperativeAppointmentVO> pageVO = cooperativeAppointmentServiceResults.toPageVO(pageDO);
        // 判断是否增强
        if (cooperativeAppointmentQuery.getAssignment() == null) {
            return cooperativeAppointmentServiceResults.assignment(pageVO);
        } else {
            return cooperativeAppointmentQuery.getAssignment() ? cooperativeAppointmentServiceResults.assignment(pageVO) : pageVO;
        }
    }


    /**
     * TODO 单条
     *
     * @param cooperativeAppointmentQuery 云服合作预约
     * @return CooperativeAppointmentVO
     * @author ljh
     * @methodName getOneEnhance
     * @time 2023-01-09 02:56:41
     */
    @Override
    public CooperativeAppointmentVO getOneEnhance(CooperativeAppointmentQuery cooperativeAppointmentQuery) {
        CooperativeAppointment cooperativeAppointment = GeneralConvertor.convertor(cooperativeAppointmentQuery, CooperativeAppointment.class);
        QueryWrapper<CooperativeAppointment> queryWrapper = new QueryWrapper<>(cooperativeAppointment);
        // TODO 自动生成查询，禁止手动写语句
        cooperativeAppointmentServiceQuery.query(cooperativeAppointmentQuery, queryWrapper);
        // TODO 人工查询条件
        queryArtificial(cooperativeAppointmentQuery, queryWrapper);
        // DO数据
        CooperativeAppointment cooperativeAppointmentDO = cooperativeAppointmentMapper.selectOne(queryWrapper);
        // VO数据
        CooperativeAppointmentVO cooperativeAppointmentVO = GeneralConvertor.convertor(cooperativeAppointmentDO, CooperativeAppointmentVO.class);
        // 判断是否增强
        if (cooperativeAppointmentQuery.getAssignment() == null) {
            return cooperativeAppointmentServiceResults.assignment(cooperativeAppointmentVO);
        } else {
            return cooperativeAppointmentQuery.getAssignment() ? cooperativeAppointmentServiceResults.assignment(cooperativeAppointmentVO) : cooperativeAppointmentVO;
        }
    }


    /**
     * TODO 总数
     *
     * @param cooperativeAppointmentQuery 云服合作预约
     * @return Integer
     * @author ljh
     * @methodName countEnhance
     * @time 2023-01-09 02:56:41
     */
    @Override
    public Long countEnhance(CooperativeAppointmentQuery cooperativeAppointmentQuery) {
        CooperativeAppointment cooperativeAppointment = GeneralConvertor.convertor(cooperativeAppointmentQuery, CooperativeAppointment.class);
        QueryWrapper<CooperativeAppointment> queryWrapper = new QueryWrapper<>(cooperativeAppointment);
        // TODO 自动生成查询，禁止手动写语句
        cooperativeAppointmentServiceQuery.query(cooperativeAppointmentQuery, queryWrapper);
        // TODO 人工查询条件
        queryArtificial(cooperativeAppointmentQuery, queryWrapper);
        return cooperativeAppointmentMapper.selectCount(queryWrapper);
    }


    /**
     * TODO 新增
     *
     * @param cooperativeAppointmentBO 云服合作预约
     * @return String
     * @author ljh
     * @methodName saveEnhance
     * @time 2023-01-09 02:56:41
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public String saveEnhance(CooperativeAppointmentBO cooperativeAppointmentBO) {
        CooperativeAppointment cooperativeAppointment = GeneralConvertor.convertor(cooperativeAppointmentBO, CooperativeAppointment.class);
        cooperativeAppointment.setState(CooperativeAppointmentStateEnum.待处理);
        cooperativeAppointmentMapper.insert(cooperativeAppointment);
        //保存操作日志
        cooperativeAppointmentOperationLog(cooperativeAppointment.getId(), cooperativeAppointment.getName(), null,
                cooperativeAppointment.getState().getDesc(), null);
        return cooperativeAppointment.getId();
    }


    /**
     * TODO 修改
     *
     * @param cooperativeAppointmentBO 云服合作预约
     * @return Boolean
     * @author ljh
     * @methodName updateEnhance
     * @time 2023-01-09 02:56:41
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public Boolean updateEnhance(CooperativeAppointmentBO cooperativeAppointmentBO) {
        CooperativeAppointment cooperativeAppointment = GeneralConvertor.convertor(cooperativeAppointmentBO, CooperativeAppointment.class);
        UpdateWrapper<CooperativeAppointment> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", cooperativeAppointmentBO.getId());
        int i = cooperativeAppointmentMapper.update(cooperativeAppointment, updateWrapper);
        return i > 0;
    }


    /**
     * TODO 删除
     *
     * @param cooperativeAppointmentBO 云服合作预约
     * @return Boolean
     * @author ljh
     * @methodName removeEnhance
     * @time 2023-01-09 02:56:41
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public Boolean removeEnhance(CooperativeAppointmentBO cooperativeAppointmentBO) {
        CooperativeAppointment cooperativeAppointment = GeneralConvertor.convertor(cooperativeAppointmentBO, CooperativeAppointment.class);
        QueryWrapper<CooperativeAppointment> queryWrapper = new QueryWrapper<>(cooperativeAppointment);
        int i = cooperativeAppointmentMapper.delete(queryWrapper);
        return i > 0;
    }

    /**
     * 修改预约订单状态
     *
     * @param cooperativeAppointmentBO: id,state
     * @return
     */
    @Override
    public Boolean updateAppointmentState(CooperativeAppointmentBO cooperativeAppointmentBO) {
        CooperativeAppointment cooperativeAppointment = GeneralConvertor.convertor(cooperativeAppointmentBO, CooperativeAppointment.class);
        boolean result = cooperativeAppointmentMapper.updateById(cooperativeAppointment) > 0;
        //保存操作日志
        cooperativeAppointmentOperationLog(cooperativeAppointment.getId(), cooperativeAppointmentBO.getOperator(), cooperativeAppointmentBO.getOperatorId(),
                cooperativeAppointment.getState().getDesc(), cooperativeAppointmentBO.getLogDescription());
        return result;
    }

    /**
     * TODO 人工查询条件
     *
     * @param cooperativeAppointmentQuery 云服合作预约
     * @return QueryWrapper
     * @author ljh
     * @methodName queryArtificial
     * @time 2023-01-09 02:56:41
     */
    private QueryWrapper queryArtificial(CooperativeAppointmentQuery cooperativeAppointmentQuery, QueryWrapper<CooperativeAppointment> queryWrapper) {
        /**
         * 提交时间 区间查询
         */
        String startTime = cooperativeAppointmentQuery.getStartTime();
        String endTime = cooperativeAppointmentQuery.getEndTime();
        if (StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)) {
            queryWrapper.between("create_date_time", startTime, endTime);
        }

        /**
         * 模糊查询 企业名称
         */
        if (StringUtils.isNotBlank(cooperativeAppointmentQuery.getEnterpriseNameQuery())) {
            queryWrapper.likeRight("enterprise_name", cooperativeAppointmentQuery.getEnterpriseNameQuery());
        }
        return queryWrapper;
    }

    /**
     * 合作预约操作日志保存
     *
     * @param cooperativeAppointmentId 合作预约表id
     * @param operator                 操作人姓名
     * @param operatorId               操作人id
     * @param state                    订单状态（0：待处理，1：处理中，2：已完成，3：已关闭）
     * @param description              备注
     */
    public void cooperativeAppointmentOperationLog(String cooperativeAppointmentId, String operator, String operatorId, String state, String description) {
        CooperativeAppointmentLog cooperativeAppointmentLog = new CooperativeAppointmentLog();
        cooperativeAppointmentLog.setCooperativeAppointmentId(cooperativeAppointmentId);
        cooperativeAppointmentLog.setOperator(operator);
        cooperativeAppointmentLog.setOperatorId(operatorId);
        cooperativeAppointmentLog.setState(state);
        cooperativeAppointmentLog.setDescription(description);
        cooperativeAppointmentLogService.insert(cooperativeAppointmentLog);
    }
}