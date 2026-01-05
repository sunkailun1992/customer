package com.gb.customer.entity.query;


import com.baomidou.mybatisplus.annotation.TableField;
import com.gb.customer.entity.enums.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;


/**
 * TODO 客户经纪人查询
 * 代码生成器
 *
 * @author lijh
 * @className CustomerAgentQuery
 * @time 2022-08-31 09:35:12
 */
@Data
@ApiModel(value = "客户经纪人查询")
public class CustomerAgentQuery implements Serializable {

    @ApiModelProperty(value = "序列")
    private String id;

    @ApiModelProperty(value = "客户id")
    private String customerId;

    @ApiModelProperty(value = "经纪人id")
    private String agentUserId;

    @ApiModelProperty(value = "姓名")
    private String name;

    /**
     * {@link CustomerAgentGenderEnum}
     */
    @ApiModelProperty(value = "性别（0：男，1：女）")
    private Integer gender;

    /**
     * {@link CustomerAgentIntentionEnum}
     */
    @ApiModelProperty(value = "意向（0：高，1：中，2：低，3：无）")
    private Integer intention;

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

    @ApiModelProperty(value = "备注")
    private String description;

    /**
     * {@link CustomerAgentTypeEnum }
     */
    @ApiModelProperty(value = "类型（0：自营，1：分销，2：无）")
    private CustomerAgentTypeEnum type;

    /**
     * {@link CustomerAgentStateEnum}
     */
    @ApiModelProperty(value = "状态（0：主关联，1：副关联，2：无）")
    private CustomerAgentStateEnum state;

    @ApiModelProperty(value = "标签")
    private String label;

    @ApiModelProperty(value = "排序")
    private Integer sorting;

    @ApiModelProperty(value = "追加")
    private Boolean assignment;

    @ApiModelProperty(value = "显示字段")
    private String fields;

    @ApiModelProperty(value = "排序规则(0:desc,1:asc)")
    private Boolean collation;

    @ApiModelProperty(value = "排序字段")
    private String collationFields = "create_date_time";

    @ApiModelProperty(value = "模糊查询")
    private String query;

    /**
     * 查询条件
     */

    @TableField(exist = false)
    @ApiModelProperty(value = "名称")
    private String nameQuery;

    @TableField(exist = false)
    @ApiModelProperty(value = "更新时间 起")
    private String modifyStartDateTime;

    @TableField(exist = false)
    @ApiModelProperty(value = "更新时间 止")
    private String modifyEndDateTime;

    @TableField(exist = false)
    @ApiModelProperty(value = "经纪人id")
    private String agentUserIdQuery;


    @TableField(exist = false)
    @ApiModelProperty(value = "手机号")
    private String mobileQuery;

    @TableField(exist = false)
    @ApiModelProperty(value = "注册时间")
    private String registerDateTime;

    @TableField(exist = false)
    @ApiModelProperty(value = "客户列表类型")
    private String customerListType;

    @TableField(exist = false)
    @ApiModelProperty(value = "状态（0：未注册，1：已注册）")
    private Integer registerQuery;

    @TableField(exist = false)
    @ApiModelProperty(value = "客户来源id")
    private String customerSourceIdQuery;

    @TableField(exist = false)
    @ApiModelProperty(value = "经纪人类型(0：自营，1：分销)")
    private Integer agentUserTypeQuery;

    @TableField(exist = false)
    @ApiModelProperty(value = "备注")
    private String descriptionQuery;


    @TableField(exist = false)
    @ApiModelProperty(value = "省code")
    private String provinceCodeQuery;

    @TableField(exist = false)
    @ApiModelProperty(value = "市code")
    private String cityCodeQuery;

    @TableField(exist = false)
    @ApiModelProperty(value = "区code")
    private String areaCodeQuery;

    @TableField(exist = false)
    @ApiModelProperty(value = "企业名称模糊查询")
    private String enterpriseNameQuery;

    @TableField(exist = false)
    @ApiModelProperty(value = "意向（0：高，1：中，2：低，3：无）")
    private Integer intentionQuery;

    @ApiModelProperty(value = "阶段（0：电话沟通，1：需求确认，2：方案/报价，3：成交，4：暂时搁置，5：谈判/合同）")
    private Integer stageQuery;

    @TableField(exist = false)
    @ApiModelProperty(value = "经纪人id")
    private List<String> agentUserIds;


    @TableField(exist = false)
    @ApiModelProperty(value = "客户id")
    private List<String> customerIds;

    @TableField(exist = false)
    @ApiModelProperty(value = "联系状态")
    private Boolean connectionState;

    @TableField(exist = false)
    @ApiModelProperty(value = "开始分配时间")
    private String startAllocateDateTime;

    @TableField(exist = false)
    @ApiModelProperty(value = "结束分配时间")
    private String endAllocateDateTime;


    /**
     * 查询分页方法
     */
    public interface Select {
    }

    /**
     * 查询方法
     */
    public interface SelectList {
    }

    /**
     * 单条查询
     */
    public interface SelectOne {
    }

    /**
     * 总数参数
     */
    public interface Count {
    }
}


