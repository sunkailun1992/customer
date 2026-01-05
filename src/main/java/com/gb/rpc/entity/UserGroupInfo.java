package com.gb.rpc.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 用户组信息(经纪人列表)
 *
 * @author lijinhao
 * @Date 2021/6/11
 */
@Data
public class UserGroupInfo {

    /**
     * 唯一标识(user新改版userName是登录账号，userId或id才是唯一标识。  但是这里不改动，牵涉地方太多。  接收时userid映射到username上)
     */
    private String userName;

    private String realName;

    private int consultingCount;

    private String userId;

    /**
     * 职位名称
     */
    private String nickName;

    /**
     * 状态 NORMAL ：正常 BAN：禁用
     */
    private String status;
    /**
     * 手机号
     */
    private String phone;

    private String createDate;

    private String stewards;

    /**
     * (标记位-经纪人)用户进行经纪人咨询，如果已经咨询过，不插入数据，就直接成功
     */
    private Boolean everConsultingFlag;
    /**
     * (标记位) 抛出异常(不传token情况下)
     */
    private String exceptionTextFlag;

    private String agentGroupId;
}
