package com.gb.customer.service;

import com.gb.customer.entity.PotentialCustomer;
import com.gb.customer.entity.query.CooperativeAppointmentQuery;
import com.gb.customer.entity.vo.CooperativeAppointmentVO;
import com.gb.customer.entity.bo.CooperativeAppointmentBO;
import com.gb.customer.entity.CooperativeAppointment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;


/**
 * TODO 云服合作预约，Service服务接口层
 * 代码生成器
 *
 * @author ljh
 * @className CooperativeAppointmentService
 * @time 2023-01-09 02:56:41
 */
public interface CooperativeAppointmentService extends IService<CooperativeAppointment> {


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
    Page<CooperativeAppointmentVO> pageEnhance(Page page, CooperativeAppointmentQuery cooperativeAppointmentQuery);


    /**
     * TODO 集合
     *
     * @param cooperativeAppointmentQuery 云服合作预约
     * @return List<CooperativeAppointmentVO>
     * @author ljh
     * @methodName listEnhance
     * @time 2023-01-09 02:56:41
     */
    List<CooperativeAppointmentVO> listEnhance(CooperativeAppointmentQuery cooperativeAppointmentQuery);


    /**
     * TODO 单条
     *
     * @param cooperativeAppointmentQuery 云服合作预约
     * @return CooperativeAppointmentVO
     * @author ljh
     * @methodName getOneEnhance
     * @time 2023-01-09 02:56:41
     */
    CooperativeAppointmentVO getOneEnhance(CooperativeAppointmentQuery cooperativeAppointmentQuery);


    /**
     * TODO 总数
     *
     * @param cooperativeAppointmentQuery 云服合作预约
     * @return Long
     * @author ljh
     * @methodName countEnhance
     * @time 2023-01-09 02:56:41
     */
    Long countEnhance(CooperativeAppointmentQuery cooperativeAppointmentQuery);


    /**
     * TODO 新增
     *
     * @param cooperativeAppointmentBO 云服合作预约
     * @return String
     * @author ljh
     * @methodName saveEnhance
     * @time 2023-01-09 02:56:41
     */
    String saveEnhance(CooperativeAppointmentBO cooperativeAppointmentBO);


    /**
     * TODO 修改
     *
     * @param cooperativeAppointmentBO 云服合作预约
     * @return Boolean
     * @author ljh
     * @methodName updateEnhance
     * @time 2023-01-09 02:56:41
     */
    Boolean updateEnhance(CooperativeAppointmentBO cooperativeAppointmentBO);


    /**
     * TODO 删除
     *
     * @param cooperativeAppointmentBO 云服合作预约
     * @return Boolean
     * @author ljh
     * @methodName removeEnhance
     * @time 2023-01-09 02:56:41
     */
    Boolean removeEnhance(CooperativeAppointmentBO cooperativeAppointmentBO);

    /**
     * 修改预约订单状态
     *
     * @param cooperativeAppointmentBO: id,state
     * @return
     */
    Boolean updateAppointmentState(CooperativeAppointmentBO cooperativeAppointmentBO);

}
