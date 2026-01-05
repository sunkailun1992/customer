package com.gb.mq.crm;

import com.gb.customer.entity.PotentialCustomer;
import com.gb.customer.enums.PotentialCustomerStateEnum;
import com.gb.customer.enums.SourceTypeEnum;
import lombok.Data;

/**
 * @author lijh
 * @Date 2021/9/6
 */
@Data
public class RegistUserEvent {
    /**
     * 用户id
     */
    private String userId;
    /**
     * 用户类型（0：用户，1：经纪人）
     */
    private Integer userType;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 姓名
     */
    private String name;

    /**
     * 经纪人 id
     */
    private String agentUserId;

//    /**
//     * 报价工具1.0.0-邀请好友id---通知MQ的时候
//     */
//    private String inviteUserId;
//
//    /**
//     * 报价工具1.0.0-报价表单id
//     */
//    private String businessDetails;

    public PotentialCustomer buildPotentialCustomer() {
        PotentialCustomer potentialCustomer = new PotentialCustomer();
        potentialCustomer.setUserId(userId);
        potentialCustomer.setUserName(name);
        potentialCustomer.setMobile(mobile);
        potentialCustomer.setType(userType);
        potentialCustomer.setPotentialCustomerSourceId(SourceTypeEnum.SOURCE_TYPE_1.getCode());
        potentialCustomer.setState(PotentialCustomerStateEnum.处理中.getCode());
        potentialCustomer.setAgentUserId(agentUserId);
        return potentialCustomer;
    }
}
