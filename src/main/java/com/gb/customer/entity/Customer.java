package com.gb.customer.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.gb.customer.entity.enums.CustomerAgentOperateRecordOperationEnum;
import com.gb.customer.entity.enums.CustomerAgentOperateRecordSourceEnum;
import com.gb.customer.enums.CustomerStateEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 客户表
 * </p>
 *
 * @author lijh
 * @since 2021-06-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("`customer`")
@ApiModel(value = "Customer对象", description = "客户表")
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "序列")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty(value = "客户来源id")
    private String customerSourceId;

    @ApiModelProperty(value = "用户id")
    private String userId;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "分配时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime allocateDateTime;

    @ApiModelProperty(value = "联系状态")
    private Boolean connectionState;

    @ApiModelProperty(value = "经纪人id   废弃")
    private String agentUserId;

    @ApiModelProperty(value = "经纪人名称  废弃")
    private String agentUserName;

    @ApiModelProperty(value = "经纪人组id   废弃")
    private String agentGroupId;

    @ApiModelProperty(value = "经纪人类型（0：业务管家，1：外部经纪人，2：渠道经纪人，3：服务管家，4：普通经纪人）")
    private Integer agentUserType;

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

    @ApiModelProperty(value = "类型")
    private Integer type;

    /**
     * {@link CustomerStateEnum}
     */
    @ApiModelProperty(value = "状态（0：未注册，1：已注册）")
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

    @TableField("(select content from customer_communication where customer_id = customer.id order by `create_date_time` desc limit 1)")
    @ApiModelProperty(value = "最近一次备注")
    private String content;

    @TableField(exist = false)
    @ApiModelProperty(value = "提交时间（开始时间）")
    private String startTime;

    @TableField(exist = false)
    @ApiModelProperty(value = "提交时间（结束时间）")
    private String endTime;

    @TableField(exist = false)
    @ApiModelProperty(value = "管家id查询")
    private String userIdQuery;

    @TableField(exist = false)
    @ApiModelProperty(value = "经纪人证书编号")
    private String certificateCode;

    @TableField(exist = false)
    @ApiModelProperty(value = "经纪人手机号")
    private String agentUserMobile;

    @TableField("(select `name` from `customer_source` where `customer`.`customer_source_id` = `customer_source`.`id`)")
    @ApiModelProperty(value = "客户来源名字")
    private String customerSourceName;

    /*---------------------------  新增  crm2.0------------------------------------ */

    @TableField(exist = false)
    @ApiModelProperty(value = "更新时间 起")
    private String modifyStartDateTime;

    @TableField(exist = false)
    @ApiModelProperty(value = "更新时间 止")
    private String modifyEndDateTime;

    @TableField(exist = false)
    @ApiModelProperty(value = "手机号")
    private String mobileQuery;

    @TableField(exist = false)
    @ApiModelProperty(value = "名称")
    private String nameQuery;

    @TableField(exist = false)
    @ApiModelProperty(value = "注册时间")
    private String registerDateTime;

    @TableField(exist = false)
    @ApiModelProperty(value = "客户列表类型")
    private String customerListType;

    @TableField(exist = false)
    @ApiModelProperty(value = "意向（0：高，1：中，2：低）")
    private Integer intention;

    @TableField(exist = false)
    @ApiModelProperty(value = "状态（0：未注册，1：已注册）")
    private Integer stateQuery;

    @TableField(exist = false)
    @ApiModelProperty(value = "客户来源id")
    private String customerSourceIdQuery;

    @TableField(exist = false)
    @ApiModelProperty(value = "经纪人id")
    private String agentUserIdQuery;

    @TableField(exist = false)
    @ApiModelProperty(value = "经纪人类型(0：自营，1：分销)")
    private Integer agentUserTypeQuery;

    @TableField(exist = false)
    @ApiModelProperty(value = "经纪人姓名")
    private String agentUserNameQuery;

    @TableField(exist = false)
    @ApiModelProperty(value = "企业名称")
    private String enterpriseName;

    @TableField(exist = false)
    @ApiModelProperty(value = "备注")
    private String descriptionQuery;

    @TableField(exist = false)
    @ApiModelProperty(value = "省id")
    private String provinceCode;

    @TableField(exist = false)
    @ApiModelProperty(value = "省name")
    private String provinceName;

    @TableField(exist = false)
    @ApiModelProperty(value = "市id")
    private String cityCode;

    @TableField(exist = false)
    @ApiModelProperty(value = "市name")
    private String cityName;

    @TableField(exist = false)
    @ApiModelProperty(value = "区id")
    private String areaCode;

    @TableField(exist = false)
    @ApiModelProperty(value = "区name")
    private String areaName;


    @TableField(exist = false)
    @ApiModelProperty(value = "当前登录账号id")
    private String loginAccountId;

    @TableField(exist = false)
    @ApiModelProperty(value = "是否新客户")
    private Boolean isNewCustomer;

    @TableField(exist = false)
    @ApiModelProperty(value = "客户操作记录来源")
    private CustomerAgentOperateRecordSourceEnum customerAgentOperateRecordSourceEnum;

    @TableField(exist = false)
    @ApiModelProperty(value = "客户操作记录类型")
    private CustomerAgentOperateRecordOperationEnum customerAgentOperateRecordOperationEnum;

    @TableField(exist = false)
    @ApiModelProperty(value = "客户集合id")
    private List<String> customerIds;

    @TableField(exist = false)
    @ApiModelProperty(value = "用户来源code")
    private String userSourceCode;

    @TableField(exist = false)
    @ApiModelProperty(value = "开始用户注册时间")
    private String startUserCreationTime;

    @TableField(exist = false)
    @ApiModelProperty(value = "结束用户注册时间")
    private String endUserCreationTime;

    @TableField(exist = false)
    @ApiModelProperty(value = "沟通状态")
    private Boolean communication;

    /*---------------------------  crm2.0 ------------------------------------- */

}
