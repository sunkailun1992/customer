package com.gb.customer.service;

import com.gb.customer.entity.enums.CustomerAgentOperateRecordOperationEnum;
import com.gb.customer.entity.enums.CustomerAgentOperateRecordSourceEnum;
import com.gb.customer.entity.query.CustomerAgentOperateRecordQuery;
import com.gb.customer.entity.vo.CustomerAgentOperateRecordVO;
import com.gb.customer.entity.bo.CustomerAgentOperateRecordBO;
import com.gb.customer.entity.CustomerAgentOperateRecord;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;


/**
 * TODO 客户经纪人操作记录，Service服务接口层
 * 代码生成器
 *
 * @author lijh
 * @className CustomerAgentOperateRecordService
 * @time 2022-09-20 11:02:25
 */
public interface CustomerAgentOperateRecordService extends IService<CustomerAgentOperateRecord> {


    /**
     * TODO 分页
     *
     * @param page
     * @param customerAgentOperateRecordQuery 客户经纪人操作记录
     * @return Page<CustomerAgentOperateRecordVO>
     * @author lijh
     * @methodName pageEnhance
     * @time 2022-09-20 11:02:25
     */
    Page<CustomerAgentOperateRecordVO> pageEnhance(Page page, CustomerAgentOperateRecordQuery customerAgentOperateRecordQuery);


    /**
     * TODO 集合
     *
     * @param customerAgentOperateRecordQuery 客户经纪人操作记录
     * @return List<CustomerAgentOperateRecordVO>
     * @author lijh
     * @methodName listEnhance
     * @time 2022-09-20 11:02:25
     */
    List<CustomerAgentOperateRecordVO> listEnhance(CustomerAgentOperateRecordQuery customerAgentOperateRecordQuery);


    /**
     * TODO 单条
     *
     * @param customerAgentOperateRecordQuery 客户经纪人操作记录
     * @return CustomerAgentOperateRecordVO
     * @author lijh
     * @methodName getOneEnhance
     * @time 2022-09-20 11:02:25
     */
    CustomerAgentOperateRecordVO getOneEnhance(CustomerAgentOperateRecordQuery customerAgentOperateRecordQuery);


    /**
     * TODO 总数
     *
     * @param customerAgentOperateRecordQuery 客户经纪人操作记录
     * @return Long
     * @author lijh
     * @methodName countEnhance
     * @time 2022-09-20 11:02:25
     */
    Long countEnhance(CustomerAgentOperateRecordQuery customerAgentOperateRecordQuery);


    /**
     * TODO 新增
     *
     * @param customerAgentOperateRecordBO 客户经纪人操作记录
     * @return String
     * @author lijh
     * @methodName saveEnhance
     * @time 2022-09-20 11:02:25
     */
    String saveEnhance(CustomerAgentOperateRecordBO customerAgentOperateRecordBO);


    /**
     * TODO 修改
     *
     * @param customerAgentOperateRecordBO 客户经纪人操作记录
     * @return Boolean
     * @author lijh
     * @methodName updateEnhance
     * @time 2022-09-20 11:02:25
     */
    Boolean updateEnhance(CustomerAgentOperateRecordBO customerAgentOperateRecordBO);


    /**
     * TODO 删除
     *
     * @param customerAgentOperateRecordBO 客户经纪人操作记录
     * @return Boolean
     * @author lijh
     * @methodName removeEnhance
     * @time 2022-09-20 11:02:25
     */
    Boolean removeEnhance(CustomerAgentOperateRecordBO customerAgentOperateRecordBO);

    /**
     * 操作记录
     *
     * @param customerId                              客户id
     * @param agentUserId                             经纪人id
     * @param customerAgentOperateRecordSourceEnum    操作来源
     * @param customerAgentOperateRecordOperationEnum 操作类型
     * @param createName                              操作人
     */
    void operateRecord(String customerId, String agentUserId, CustomerAgentOperateRecordSourceEnum customerAgentOperateRecordSourceEnum,
                       CustomerAgentOperateRecordOperationEnum customerAgentOperateRecordOperationEnum, String createName);
}
