package com.gb.customer.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.baomidou.mybatisplus.annotation.*;
import com.gb.customer.entity.enums.CooperativeAppointmentRequirementTypeEnum;
import com.gb.customer.entity.enums.CooperativeAppointmentTypeEnum;
import com.gb.customer.entity.enums.CooperativeAppointmentStateEnum;
import com.gb.bean.EntityBase;


/**
 * TODO 云服合作预约对象
 * 代码生成器
 *
 * @author ljh
 * @className CooperativeAppointment
 * @time 2023-01-09 02:56:41
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("`cooperative_appointment`")
@ApiModel(value = "CooperativeAppointment对象", description = "云服合作预约")
public class CooperativeAppointment extends EntityBase {

    @ApiModelProperty(value = "编码")
    private String code;

    @ApiModelProperty(value = "合作需求（0：渠道合作，1：代理人合作，2：保险机构合作，3：生态资源合作）")
    private CooperativeAppointmentRequirementTypeEnum requirementType;

    @ApiModelProperty(value = "企业名称")
    private String enterpriseName;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "省id")
    private String provinceCode;

    @ApiModelProperty(value = "省name")
    private String provinceName;

    @ApiModelProperty(value = "市id")
    private String cityCode;

    @ApiModelProperty(value = "市name")
    private String cityName;

    @ApiModelProperty(value = "区id")
    private String areaCode;

    @ApiModelProperty(value = "区name")
    private String areaName;

    @ApiModelProperty(value = "来源（0：工保云服）")
    private CooperativeAppointmentTypeEnum type;

    /**
     * 无处理中的状态
     */
    @ApiModelProperty(value = "状态（0：待处理，1：处理中，2：已完成，3：已关闭）")
    private CooperativeAppointmentStateEnum state;

    @ApiModelProperty(value = "标签")
    private String label;
}
