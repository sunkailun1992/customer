package com.gb.customer.service.results;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gb.customer.entity.CustomerAgentOperateRecord;
import com.gb.customer.entity.vo.CustomerAgentOperateRecordVO;
import com.gb.rpc.entity.UserVO;
import com.gb.rpc.entity.enums.RpcTypeEnum;
import com.gb.rpc.impl.RpcComponent;
import com.gb.utils.GeneralConvertor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * TODO 客户经纪人操作记录,Service返回实现
 * 代码生成器
 *
 * @author lijh
 * @className CustomerAgentOperateRecordServiceResults
 * @time 2022-09-20 11:02:25
 */
@Slf4j
@Service
@Setter(onMethod_ = {@Autowired})
public class CustomerAgentOperateRecordServiceResults {

    private RpcComponent rpcComponent;


    /**
     * TODO 单条，增强返回参数追加
     *
     * @param customerAgentOperateRecordVO 客户经纪人操作记录
     * @return CustomerAgentOperateRecordVO
     * @author lijh
     * @methodName assignment
     * @time 2022-09-20 11:02:25
     */
    public CustomerAgentOperateRecordVO assignment(CustomerAgentOperateRecordVO customerAgentOperateRecordVO) {
        if (customerAgentOperateRecordVO != null) {
            return customerAgentOperateRecordVO;
        } else {
            return customerAgentOperateRecordVO;
        }
    }


    /**
     * TODO 分页，增强返回参数追加
     *
     * @param customerAgentOperateRecordVOList 客户经纪人操作记录
     * @return Page<CustomerAgentOperateRecordVO>
     * @author lijh
     * @methodName assignment
     * @time 2022-09-20 11:02:25
     */
    public Page<CustomerAgentOperateRecordVO> assignment(Page<CustomerAgentOperateRecordVO> customerAgentOperateRecordVOList) {
        if (CollectionUtils.isNotEmpty(customerAgentOperateRecordVOList.getRecords())) {
            //用户基础信息
            Map<String, Object> map = new HashMap<>(1);
            customerAgentOperateRecordVOList.getRecords().forEach(operateRecordVO -> {
                map.put("id", operateRecordVO.getAgentUserId());
                UserVO userVO = rpcComponent.rpcQueryInfo(map, RpcTypeEnum.GET_ONE_USER, UserVO.class);
                operateRecordVO.setAgentUserName(userVO.getName());
            });
        }
        return customerAgentOperateRecordVOList;
    }


    /**
     * TODO 集合，增强返回参数追加
     *
     * @param customerAgentOperateRecordVOList 客户经纪人操作记录
     * @return List<CustomerAgentOperateRecordVO>
     * @author lijh
     * @methodName assignment
     * @time 2022-09-20 11:02:25
     */
    public List<CustomerAgentOperateRecordVO> assignment(List<CustomerAgentOperateRecordVO> customerAgentOperateRecordVOList) {
        customerAgentOperateRecordVOList.forEach(customerAgentOperateRecordVO -> {
        });
        return customerAgentOperateRecordVOList;
    }


    /**
     * TODO DO转化VO
     *
     * @param pageDO 客户经纪人操作记录
     * @return Page<CustomerAgentOperateRecordVO>
     * @author lijh
     * @methodName toPageVO
     * @time 2022-09-20 11:02:25
     */
    public Page<CustomerAgentOperateRecordVO> toPageVO(Page<CustomerAgentOperateRecord> pageDO) {
        Page<CustomerAgentOperateRecordVO> pageVO = new Page<CustomerAgentOperateRecordVO>();
        pageVO.setRecords(GeneralConvertor.convertor(pageDO.getRecords(), CustomerAgentOperateRecordVO.class));
        pageVO.setCurrent(pageDO.getCurrent());
        pageVO.setPages(pageDO.getPages());
        pageVO.setSize(pageDO.getSize());
        pageVO.setTotal(pageDO.getTotal());
        return pageVO;
    }
}