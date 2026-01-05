package com.gb.rpc.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 投保表
 * </p>
 *
 * @author ranyang
 * @since 2021-03-12
 */
@Data
@Accessors(chain = true)
public class CastInsurance implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    @ApiModelProperty(value = "投保用户id")
    private String castInsuranceUserId;

    @ApiModelProperty(value = "投保内容id")
    private String contentId;

    @ApiModelProperty(value = "经纪人id")
    private String agentUserId;

    @ApiModelProperty(value = "保险企业id")
    private String insuranceEnterpriseId;

    @ApiModelProperty(value = "保险机构id")
    private String insuranceEnterpriseInstitutionsId;

    @ApiModelProperty(value = "险种分类id")
    private String dangerPlantedCategoryId;

    @ApiModelProperty(value = "险种id")
    private String dangerPlantedId;

    @ApiModelProperty(value = "方案类型id")
    private String planId;

    @ApiModelProperty(value = "方案类型值id")
    private String planValueId;

    @ApiModelProperty(value = "投保人类型id")
    private String policyHolderId;

    @ApiModelProperty(value = "投保人类型值id")
    private String policyHolderValueId;

    @ApiModelProperty(value = "产品id")
    private String spuId;

    @ApiModelProperty(value = "sku范围id")
    private String spuScopeId;

    @ApiModelProperty(value = "skuid")
    private String skuId;

    @ApiModelProperty(value = "省编码")
    private String provinceCode;

    @ApiModelProperty(value = "市编码")
    private String cityCode;

    @ApiModelProperty(value = "区编码")
    private String areaCode;

    @ApiModelProperty(value = "渠道id")
    private String channelId;

    @ApiModelProperty(value = "投保拆单规则id")
    private String splitCastInsuranceRulesId;

    @ApiModelProperty(value = "上笔投保拆单id")
    private String splitCastInsuranceId;

    @ApiModelProperty(value = "历史投保id")
    private String historyId;

    @ApiModelProperty(value = "保险金额")
    private BigDecimal insuranceMoney;

    @ApiModelProperty(value = "保险费率")
    private BigDecimal insuranceRate;

    @ApiModelProperty(value = "保费金额")
    private BigDecimal payMoney;

    @ApiModelProperty(value = "保险开始时间")
    @TableField("start_Insurance_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime startInsuranceTime;

    @ApiModelProperty(value = "保险结束时间")
    @TableField("end_Insurance_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime endInsuranceTime;

    @ApiModelProperty(value = "保险天数")
    private Integer insuranceDay;

    @ApiModelProperty(value = "流程（0：待提交，1：提交订单，2：审查审核，3：风控审核，4：保险审核，5：支付订单，6：保单出函，7：订单完成, 8: 订单已关闭）")
    private Integer process;

    @ApiModelProperty(value = "来源（0：未知，1：工保网，2：经纪人，3：试算，4：渠道 5: 工保网app）")
    private Integer source;

    @ApiModelProperty(value = "用户类型（0：用户端，1：经纪人，2：渠道）")
    private Integer userType;

    @ApiModelProperty(value = "保险审核状态（0：待审核，1：审核失败，2：审核成功）")
    private Integer insuranceAudit;

    @ApiModelProperty(value = "保险状态（0：待生效，1：保险中，2：待续保，3：保险过期，4：保险退保）")
    private Integer insuranceStatus;

    @ApiModelProperty(value = "是否支付（0：待支付，1：完成支付, 2: 取消支付）")
    private Integer payment;

    @ApiModelProperty(value = "发票状态（0：待申请，1：已申请，2：待开票，3：已开票，4：开票失败）")
    private Integer invoice;

    @ApiModelProperty(value = "是否发票前置（0：否，1：是）")
    private Boolean invoiceFront;

    @ApiModelProperty(value = "发票类型（0：电子，1：纸质）")
    private Integer invoiceType;

    @ApiModelProperty(value = "是否退款（0：否，1：是）")
    private Boolean refund;

    @ApiModelProperty(value = "是否理赔（0：未理赔，1：理赔）")
    private Boolean claims;

    @ApiModelProperty(value = "是否渠道（0：否，1：是）")
    private Boolean channelState;

    @ApiModelProperty(value = "渠道单号")
    private String channelNo;

    @ApiModelProperty(value = "续保（0：否，1：是）")
    private Boolean renewal;

    @ApiModelProperty(value = "是否续投（0：否，1：是）")
    private Boolean pitching;

    @ApiModelProperty(value = "拆单（0：否，1：是）")
    private Boolean apart;

    @ApiModelProperty(value = "是否结算（0：否，1：是）")
    private Boolean settlement;

    @ApiModelProperty(value = "是否修改（0：否，1：是）")
    private Boolean modify;

    @ApiModelProperty(value = "是否风控（0：否，1：是）")
    private Boolean riskControl;

    @ApiModelProperty(value = "是否人工服务（0：否，1：是）")
    private Boolean artificial;

    @ApiModelProperty(value = "是否线上（0：否，1：是）")
    private Boolean online;

    @ApiModelProperty(value = "是否设置支付方式（0：否，1：是）")
    private Boolean paymentMethod;

    @ApiModelProperty(value = "是否拒保后重投（0：否，1：是）")
    private Boolean reinsure;

    @ApiModelProperty(value = "保函地址")
    private String letterGuaranteeAddress;

    @ApiModelProperty(value = "投保单预地址")
    private String castInsuranceExpectAddress;

    @ApiModelProperty(value = "投保单地址")
    private String castInsuranceAddress;

    @ApiModelProperty(value = "反担保地址")
    private String counterGuaranteeAddress;

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

    @ApiModelProperty(value = "状态")
    private Integer state;

    @ApiModelProperty(value = "标签")
    private String label;

    @ApiModelProperty(value = "排序")
    private Integer sorting;
    
    @Version
    @ApiModelProperty(hidden = true,value = "版本号")
    private Integer version;

    @ApiModelProperty(value = "用户确认（0：未确认，1：待确认，3：已确认）")
    private Integer userConfirm;

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
    @ApiModelProperty(value = "排除流程（0：待提交，1：提交订单，2：审查审核，3：风控审核，4：保险审核，5：支付订单，6：保单出函，7：订单完成, 8: 订单已关闭）")
    private String processNoIn;


    @ApiModelProperty(hidden = true, value = "险种表(查询并赋值)")
    private Object productDangerPlantedName;


}
