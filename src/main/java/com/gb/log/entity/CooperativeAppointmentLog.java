package com.gb.log.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.gb.customer.entity.enums.CooperativeAppointmentRequirementTypeEnum;
import com.gb.customer.entity.enums.CooperativeAppointmentStateEnum;
import com.gb.customer.entity.enums.CooperativeAppointmentTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;


/**
 * 云服合作预约对象
 *
 * @author ljh
 * @className CooperativeAppointment
 * @time 2023-01-09 02:56:41
 */
@Data
@ApiModel(value = "CooperativeAppointment对象", description = "云服合作预约")
public class CooperativeAppointmentLog implements Serializable {

    private static final long serialVersionUID = 1627212396542757206L;

    @Id
    @ApiModelProperty(name = "_id", value = "序列")
    private ObjectId _id;

    @ApiModelProperty(required = true, name = "cooperativeAppointmentId", value = "合作预约表id")
    private String cooperativeAppointmentId;

    @ApiModelProperty(name = "operator", value = "操作人")
    private String operator;

    @ApiModelProperty(name = "operatorId", value = "操作人ID")
    private String operatorId;

    @ApiModelProperty(name = "state", value = "订单状态（0：待处理，1：处理中，2：已完成，3：已关闭）")
    private String state;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "创建时间")
    private Date createDateTime;

    @ApiModelProperty(value = "备注")
    private String description;
}
