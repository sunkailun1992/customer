package com.gb.customer.entity.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.gb.customer.entity.enums.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import mybatis.mate.annotation.FieldSensitive;
import mybatis.mate.strategy.SensitiveType;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;


/**
 * TODO 客户经纪人渲染
 * 代码生成器
 *
 * @author lijh
 * @className CustomerAgentVO
 * @time 2022-08-31 09:35:12
 */
@Data
@ApiModel(value = "客户经纪人渲染")
public class CustomerAgentVO implements Serializable {

    @ApiModelProperty(value = "序列")
    private String id;

    @ApiModelProperty(value = "客户id")
    private String customerId;

    @ApiModelProperty(value = "经纪人id")
    private String agentUserId;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "性别（0：男，1：女）")
    private CustomerAgentGenderEnum gender;

    @ApiModelProperty(value = "意向（0：高，1：中，2：低）")
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

    @ApiModelProperty(value = "省名称")
    private String provinceName;

    @ApiModelProperty(value = "市名称")
    private String cityName;

    @ApiModelProperty(value = "区名称")
    private String areaName;

    @ApiModelProperty(value = "详细地址")
    private String address;

    @ApiModelProperty(value = "分配时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime allocateDateTime;

    @ApiModelProperty(value = "备注")
    private String description;

    @ApiModelProperty(value = "创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createDateTime;

    @ApiModelProperty(value = "创建人")
    private String createName;

    @ApiModelProperty(value = "修改时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime modifyDateTime;

    @ApiModelProperty(value = "修改人")
    private String modifyName;

    @ApiModelProperty(value = "删除")
    private Boolean isDelete;

    @ApiModelProperty(value = "类型（0：自营，1：分销，2：无）")
    private CustomerAgentTypeEnum type;

    @ApiModelProperty(value = "状态（0：主关联，1：副关联，2：无）")
    private CustomerAgentStateEnum state;

    @ApiModelProperty(value = "标签")
    private String label;

    @ApiModelProperty(value = "排序")
    private Integer sorting;

    @ApiModelProperty(value = "版本号")
    private Integer version;

    @FieldSensitive(SensitiveType.mobile)
    @TableField(exist = false)
    @ApiModelProperty(value = "手机号")
    private String customerMobile;

    @TableField(exist = false)
    @ApiModelProperty(value = "经纪人名称")
    private String agentUserName;

    @TableField(exist = false)
    @ApiModelProperty(value = "注册时间")
    private String registerDateTime;

    @TableField(exist = false)
    @ApiModelProperty(value = "客户创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime customerCreateDateTime;

    @TableField(exist = false)
    @ApiModelProperty(value = "客户更新时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime customerModifyDateTime;

    @TableField(exist = false)
    @ApiModelProperty(value = "保费")
    private BigDecimal premium;

    @TableField(exist = false)
    @ApiModelProperty(value = "用户id")
    private String userId;
}


