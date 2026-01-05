package com.gb.log.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;

/**
 * @author 王一飞
 * @description: 产品咨询操作日志
 * @since 2021/3/12  9:36
 */
@Data
@ApiModel(value = "产品咨询操作日志实体类")
public class PotentialCustomerLog implements Serializable {

    @Id
    @ApiModelProperty(name = "_id", value = "序列")
    private ObjectId _id;

    @ApiModelProperty(required = true,name = "potentialCustomerId", value = "潜在客户表id")
    private String potentialCustomerId;

    /**
     * 用户名称
     */
    @ApiModelProperty(name = "operator", value = "操作人")
    private String operator;

    /**
     * 用户id
     */
    @ApiModelProperty(name = "operator", value = "操作人ID")
    private String operatorId;

    @ApiModelProperty(name = "finishedTime", value = "操作时间")
    private String finishedTime;

    @ApiModelProperty(name = "state", value = "订单状态")
    private String state;

    @ApiModelProperty(name = "operationContent", value = "操作内容")
    private String operationContent;

    @ApiModelProperty(name = "agent", value = "新经纪人")
    private String agent;

    @ApiModelProperty(name = "genre", value = "0:更换经纪人 1：关闭订单 2：备注订单")
    private Integer genre;

    /**
     * 分页和排序
     */
    @ApiModelProperty(name = "pageable", value = "分页和排序")
    private Pageable pageable;

    @ApiModelProperty(value = "经纪人id(目前是针对某一类的经纪人轮流循环分配)")
    private String agentUserId;

    @ApiModelProperty(value = "备注")
    private String description;
}
