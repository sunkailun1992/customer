package com.gb.customer.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 客户沟通表
 * </p>
 *
 * @author lijh
 * @since 2021-06-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("`customer_communication`")
@ApiModel(value = "CustomerCommunication对象", description = "客户沟通表")
public class CustomerCommunication implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "序列")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty(value = "客户id")
    private String customerId;

    @ApiModelProperty(value = "客户沟通id   废弃")
    private String customerCommunicationId;

    @ApiModelProperty(value = "沟通内容")
    private String content;

    @ApiModelProperty(value = "回访（0：不回访，0：未回访，1：已回访）  废弃")
    private Integer visit;

    @ApiModelProperty(value = "预约回访时间    废弃 ")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime appointmentVisitDateTime;

    @ApiModelProperty(value = "意向（0：高，1：中，2：低）")
    private Integer intention;

    @ApiModelProperty(value = "联系人")
    private String contactPerson;

    @ApiModelProperty(value = "联系电话")
    private String contactPersonPhone;

    @ApiModelProperty(value = "下次时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime nextDataTime;

    @ApiModelProperty(value = "下次内容")
    private String nextContent;

    @ApiModelProperty(value = "创建用户id")
    private String userId;

    @ApiModelProperty(value = "时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime dataTime;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "说明")
    private String description;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createDateTime;

    @ApiModelProperty(value = "创建人")
    private String createName;

    @ApiModelProperty(value = "修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime modifyDateTime;

    @ApiModelProperty(value = "修改人")
    private String modifyName;

    @ApiModelProperty(value = "删除状态（0：未删除，1：删除）")
    @TableLogic
    private Boolean isDelete;

    @ApiModelProperty(value = "类型（0：电话沟通，1：见面拜访，2：微信聊天）")
    private Integer type;

    @ApiModelProperty(value = "状态")
    private Integer state;

    @ApiModelProperty(value = "标签")
    private String label;

    @ApiModelProperty(value = "排序")
    private Integer sorting;

    @Version
    @ApiModelProperty(hidden = true, value = "版本号")
    private Integer version;

    @TableField(exist = false)
    @ApiModelProperty(value = "显示字段")
    private String fields;

    @TableField(exist = false)
    @ApiModelProperty(value = "排序规则(0:desc,1:asc)")
    private Boolean collation;

    @TableField(exist = false)
    @ApiModelProperty(value = "排序字段")
    private String collationFields = "create_date_time";

    @TableField(exist = false)
    @ApiModelProperty(value = "模糊查询")
    private String query;

    @TableField(exist = false)
    @ApiModelProperty(value = "标题")
    private String titleQuery;

    @TableField(exist = false)
    @ApiModelProperty(value = "创建时间 起")
    private String dataTimeStart;

    @TableField(exist = false)
    @ApiModelProperty(value = "创建时间 止")
    private String dateTimeEnd;

    @TableField(exist = false)
    @ApiModelProperty(value = "跟进id")
    private String idQuery;

    @TableField(exist = false)
    @ApiModelProperty(value = "经纪人姓名")
    private String agentUserNameQuery;

    @TableField(exist = false)
    @ApiModelProperty(value = "跟进时间 起")
    private String nextDataTimeStart;

    @TableField(exist = false)
    @ApiModelProperty(value = "跟进时间 止")
    private String nextDataTimeEnd;
}
