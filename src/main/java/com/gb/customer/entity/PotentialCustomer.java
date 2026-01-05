package com.gb.customer.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.gb.customer.enums.CustomerDataTypeEnum;
import com.gb.customer.enums.PotentialCustomerSourceTypeEnum;
import com.gb.customer.enums.PotentialCustomerTypeEnum;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 潜在客户表
 * </p>
 *
 * @author 王一飞
 * @since 2021-04-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("`potential_customer`")
@ApiModel(value = "PotentialCustomer对象", description = "潜在客户表")
public class PotentialCustomer implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "序列")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty(value = "来源id")
    private String potentialCustomerSourceId;

    @ApiModelProperty(value = "咨询用户id")
    private String userId;

    @ApiModelProperty(value = "咨询用户名称")
    private String userName;

    @ApiModelProperty(value = "经纪人id")
    private String agentUserId;

    @ApiModelProperty(value = "经纪人名称")
    private String agentUserName;

    @ApiModelProperty(value = "经纪人组id")
    private String agentGroupId;

    @ApiModelProperty(value = "经纪人类型（0：业务管家，1：外部经纪人，2：渠道经纪人，3：服务管家，4：特别经纪人）")
    private Integer agentUserType;

    @ApiModelProperty(value = "险种分类id")
    private String dangerPlantedCategoryId;

    @ApiModelProperty(value = "险种分类名称")
    private String dangerPlantedCategoryName;

    @ApiModelProperty(value = "险种id")
    private String dangerPlantedId;

    @ApiModelProperty(value = "险种名称")
    private String dangerPlantedName;

    @ApiModelProperty(value = "咨询产品id")
    private String spuId;

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

    @ApiModelProperty(value = "地址")
    private String address;

    @ApiModelProperty(value = "企业名称")
    private String enterpriseName;

    @ApiModelProperty(value = "项目名称")
    private String projectName;

    @ApiModelProperty(value = "项目类型id")
    private String projectTypeId;

    @ApiModelProperty(value = "项目类型值id")
    private String projectTypeValueId;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "性别")
    private String sex;

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "电话")
    private String phone;

    @ApiModelProperty(value = "生日")
    private String birthday;

    @ApiModelProperty(value = "年龄")
    private String age;

    @ApiModelProperty(value = "联系（0：未联系，1：已联系）")
    private Boolean contact;

    @ApiModelProperty(value = "预约联系时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date appointmentContactDateTime;

    @ApiModelProperty(value = "联系时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime contactDateTime;

    @ApiModelProperty(value = "现场到访（0：未到访，1：已到访）")
    private Boolean siteVisit;

    @ApiModelProperty(value = "预约现场到访时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime appointmentSiteVisitDateTime;

    @ApiModelProperty(value = "到访时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime siteVisitDateTime;

    @ApiModelProperty(value = "分配（0：未分配，1：已分配）")
    private Boolean allocation;

    @ApiModelProperty(value = "分配时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime allocationDateTime;

    @ApiModelProperty(value = "成单（0：未成单，1：已成单）")
    private Boolean clinchDeal;

    @ApiModelProperty(value = "结果内容")
    private String resultsContent;

    /**
     * {@link CustomerDataTypeEnum}
     */
    @ApiModelProperty(value = "数据标签（0：系统，1：手动，2：投保）")
    private Integer dataType;

    /**
     * {@link PotentialCustomerSourceTypeEnum}
     */
    @ApiModelProperty(value = "来源类型（0：普通咨询，1：预约咨询，2：投保咨询，3：报价咨询，4：G端保单）")
    private Integer sourceType;

    @ApiModelProperty(value = "说明")
    private String description;

    @ApiModelProperty(value = "提交时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime submitTime;

    @ApiModelProperty(value = "处理时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime processingTime;

    @ApiModelProperty(value = "完成时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime completeTime;

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

    /**
     * {@link PotentialCustomerTypeEnum}
     */
    @ApiModelProperty(value = "类型（0：产品咨询，1：经纪人咨询）")
    private Integer type;

    @ApiModelProperty(value = "状态（-1：取消，0：待处理，1：处理中，2：已完成，3：关闭）")
    private Integer state;

    @ApiModelProperty(value = "标签（0: 上午，1：下午）")
    private Integer label;

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
    @ApiModelProperty(hidden = true, value = "咨询经纪人(查询并赋值)")
    private Object agentUser;

    @TableField(exist = false)
    @ApiModelProperty(hidden = true, value = "咨询用户(查询并赋值)")
    private Object user;

    @TableField("(select `name` from `potential_customer_source` where `potential_customer`.`potential_customer_source_id` = `potential_customer_source`.`id`)")
    @ApiModelProperty(hidden = true, value = "来源表(查询并赋值)")
    private Object potentialCustomerSourceName;

    @TableField("(select `content` from `potential_customer_communication` where `potential_customer_communication`.`potential_customer_id` = `potential_customer`.`id` order by `potential_customer_communication`.`create_date_time` DESC limit 1)")
    @ApiModelProperty(hidden = true, value = "最近一次备注")
    private Object content;


    @TableField("(select `name` from `project_type` where `potential_customer`.`project_type_id` = `project_type`.`id`)")
    @ApiModelProperty(hidden = true, value = "项目类型名称(查询并赋值)")
    private Object projectTypeName;

    @TableField("(select `name` from `project_type_value` where `potential_customer`.`project_type_value_id` = `project_type_value`.`id`)")
    @ApiModelProperty(hidden = true, value = "项目类型值名称(查询并赋值)")
    private Object projectTypeValueName;

    @ApiModelProperty(hidden = true, value = "产品表(查询并赋值)")
    @TableField(exist = false)
    private Object productSpu;

    @ApiModelProperty(value = "经纪人名称查询")
    @TableField(exist = false)
    private String agentUserNameQuery;

    @ApiModelProperty(hidden = true, value = "状态（-1：取消，0：待处理，1：处理中，2：已完成，3：关闭）")
    @TableField(exist = false)
    private Integer stateQuery;

    @TableField(exist = false)
    @ApiModelProperty(value = "咨询用户id")
    private String userIdQuery;

    @TableField(exist = false)
    @ApiModelProperty(value = "角色端点（0: 客户端  1:经济端）")
    private Integer differentCharacterPerspectives;

    @TableField(exist = false)
    @ApiModelProperty(value = "模糊查询/省Code")
    private String provinceCodeQuery;

    @TableField(exist = false)
    @ApiModelProperty(value = "模糊查询/市Code")
    private String cityCodeQuery;

    @TableField(exist = false)
    @ApiModelProperty(value = "模糊查询/区Code")
    private String areaCodeQuery;

    @TableField(exist = false)
    @ApiModelProperty(value = "手机号查询")
    private String mobileQuery;

    @TableField(exist = false)
    @ApiModelProperty(value = "姓名查询")
    private String nameQuery;

    @TableField(exist = false)
    @ApiModelProperty(value = "数据来源")
    private String sourceQuery;

    @TableField(exist = false)
    @ApiModelProperty(value = "数据状态")
    private String statusQuery;

    @TableField(exist = false)
    @ApiModelProperty(value = "区分是否来源CRM")
    private Integer isCrm;

    /**
     * {@link com.gb.customer.enums.CluesPortEnum}
     */
    @TableField(exist = false)
    @ApiModelProperty(value = "线索端口")
    private String cluesPort;

    @TableField(exist = false)
    @ApiModelProperty(value = "标签code")
    private String labelCode;

    @TableField(exist = false)
    @ApiModelProperty(value = "推广表单id")
    private String promoteFormId;

    @TableField(exist = false)
    @ApiModelProperty(value = "经纪人手机号")
    private String agentUserMobile;

    @TableField(exist = false)
    @ApiModelProperty(value = "列表标题(使用于公众号)")
    private String listTitleName;

    /*------------------------    暂时  crm 2.0    ---------------------------------- */
    /**
     * 线索列表使用
     */
    @TableField(exist = false)
    @ApiModelProperty(value = "客户id")
    private String customerId;

    /**
     * 线索列表使用
     */
    @TableField(exist = false)
    @ApiModelProperty(value = "企业名称")
    private String enterpriseNameQuery;

    /**
     * 经纪人类型标签  自营和分销
     */
    @TableField(exist = false)
    @ApiModelProperty(value = "经纪人类型标签")
    private String agentUserTypeLabel;

    /**
     * 经纪人ids
     */
    @TableField(exist = false)
    @ApiModelProperty(value = "经纪人ids")
    private List<String> agentUserIds;

    /**
     * 客户经纪人类型
     */
    @TableField(exist = false)
    @ApiModelProperty(value = "客户经纪人类型 类型（0：自营，1：分销，2：无）")
    private Integer customerAgentType;

    /**
     * 经纪人id
     */
    @TableField(exist = false)
    @ApiModelProperty(value = "经纪人id")
    private String agentUserIdQuery;


    /**
     * 此手机号客户是否和经纪人有关系
     */
    @TableField(exist = false)
    @ApiModelProperty(value = "是否有关")
    private Boolean isRelatedAgent;

    /**
     * 客户分配的经纪人
     */
    @TableField(exist = false)
    @ApiModelProperty(value = "分配的经纪人")
    private String allocationAgent;

    /*------------------------    暂时  crm 2.0    ---------------------------------- */
}
