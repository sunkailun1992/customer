package com.gb.customer.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.gb.bean.EntityBase;
import com.gb.customer.entity.enums.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;


/**
 * TODO 客户经纪人对象
 * 代码生成器
 *
 * @author lijh
 * @className CustomerAgent
 * @time 2022-08-31 09:35:12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("`customer_agent`")
@ApiModel(value = "CustomerAgent对象", description = "客户经纪人")
public class CustomerAgent extends EntityBase {

    @ApiModelProperty(value = "客户id")
    private String customerId;

    @ApiModelProperty(value = "经纪人id")
    private String agentUserId;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "性别（0：男，1：女）")
    private CustomerAgentGenderEnum gender;

    @ApiModelProperty(value = "意向（0：高，1：中，2：低，3：空）")
    private CustomerAgentIntentionEnum intention;

    @ApiModelProperty(value = "阶段（0：电话沟通，1：需求确认，2：方案/报价，3：成交，4：暂时搁置，5：谈判/合同）")
    private CustomerAgentStageEnum stage;

    @ApiModelProperty(value = "企业名称")
    private String enterpriseName;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "省code")
    private String provinceCode;

    @ApiModelProperty(value = "市code")
    private String cityCode;

    @ApiModelProperty(value = "区code")
    private String areaCode;

    @ApiModelProperty(value = "详细地址")
    private String address;

    @ApiModelProperty(value = "类型（0：自营，1：分销，2：无）")
    private CustomerAgentTypeEnum type;

    @ApiModelProperty(value = "状态（0：主关联，1：副关联，2：无）")
    private CustomerAgentStateEnum state;

    @ApiModelProperty(value = "标签")
    private String label;

    @TableField(exist = false)
    @ApiModelProperty(value = "分配时间")
    private LocalDateTime allocateDateTime;

    @TableField(exist = false)
    @ApiModelProperty(value = "省name")
    private String provinceName;

    @TableField(exist = false)
    @ApiModelProperty(value = "市name")
    private String cityName;

    @TableField(exist = false)
    @ApiModelProperty(value = "区name")
    private String areaName;

    @TableField(exist = false)
    @ApiModelProperty(value = "客户手机号")
    private String customerMobile;

    @TableField(exist = false)
    @ApiModelProperty(value = "客户姓名")
    private String customerName;

    @TableField(exist = false)
    @ApiModelProperty(value = "当前登录账号id")
    private String loginAccountId;

    /**
     * 复制备注
     *
     * @param customerAgent
     */
    public void copyAgentRemark(CustomerAgent customerAgent) {
        customerAgent.setCustomerId(customerId);
        customerAgent.setAgentUserId(agentUserId);
        customerAgent.setName(name);
        customerAgent.setIntention(intention);
        customerAgent.setGender(gender);
        customerAgent.setAddress(address);
        customerAgent.setStage(stage);
        customerAgent.setEnterpriseName(enterpriseName);
        customerAgent.setProvinceCode(provinceCode);
        customerAgent.setCityCode(cityCode);
        customerAgent.setAreaCode(areaCode);
        customerAgent.setEmail(email);
        customerAgent.setType(type);
        customerAgent.setState(state);
        customerAgent.setStage(stage);
    }
}
