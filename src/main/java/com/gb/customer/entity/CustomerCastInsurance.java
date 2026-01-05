package com.gb.customer.entity;

import com.baomidou.mybatisplus.annotation.IdType;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.baomidou.mybatisplus.annotation.*;

/**
 * <p>
 * 客户投保订单关联
 * </p>
 *
 * @author lijh
 * @since 2021-06-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("`customer_cast_insurance`")
@ApiModel(value = "CustomerCastInsurance对象", description = "客户投保订单关联")
public class CustomerCastInsurance implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "序列")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty(value = "客户id")
    private String customerId;

    @ApiModelProperty(value = "投保单id")
    private String castInsuranceId;

    @ApiModelProperty(value = "经纪人id")
    private String agentUserId;

    @ApiModelProperty(value = "经纪人名称")
    private String agentUserName;

    @ApiModelProperty(value = "经纪人组id")
    private String agentGroupId;

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

    @ApiModelProperty(value = "类型(0：客户投保，1：经纪人投保)")
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
    @ApiModelProperty(value = "提交时间（开始时间）")
    private String startTime;

    @TableField(exist = false)
    @ApiModelProperty(value = "提交时间（结束时间）")
    private String endTime;

    @TableField(exist = false)
    @ApiModelProperty(value = "订单号")
    private String castInsuranceIdQuery;

    @TableField(exist = false)
    @ApiModelProperty(value = "姓名")
    private String nameQuery;

    @TableField(exist = false)
    @ApiModelProperty(value = "手机号")
    private String mobileQuery;

    @TableField(exist = false)
    @ApiModelProperty(value = "产品")
    private String spuName;

    @TableField(exist = false)
    @ApiModelProperty(value = "用户id查找客户id")
    private String userId;

    @TableField(exist = false)
    @ApiModelProperty(value = "业务管家查询")
    private String businessStewardQuery;

    @TableField(exist = false)
    @ApiModelProperty(value = "服务管家查询")
    private String serviceStewardQuery;

    @TableField(exist = false)
    @ApiModelProperty(value = "客户id数组")
    private List<String> customerIdList;

}
