package com.gb.customer.entity.query;


import com.gb.customer.entity.enums.CustomerAgentOperateRecordOperationEnum;
import com.gb.customer.entity.enums.CustomerAgentOperateRecordSourceEnum;
import com.gb.customer.entity.enums.CustomerAgentOperateRecordStateEnum;
import com.gb.customer.entity.enums.CustomerAgentOperateRecordTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


/**
 * TODO 客户经纪人操作记录查询
 * 代码生成器
 *
 * @author lijh
 * @className CustomerAgentOperateRecordQuery
 * @time 2022-09-20 11:02:25
 */
@Data
@ApiModel(value = "客户经纪人操作记录查询")
public class CustomerAgentOperateRecordQuery implements Serializable {

    @ApiModelProperty(value = "序列")
    private String id;

    @ApiModelProperty(value = "客户id")
    private String customerId;

    @ApiModelProperty(value = "经纪人id")
    private String agentUserId;

    @ApiModelProperty(value = "来源（0：公海分配，1：分销添加，2：自营添加，3：自营解绑，4：自营转交，5：链接参数）")
    private CustomerAgentOperateRecordSourceEnum source;

    @ApiModelProperty(value = "操作（0：关联，1：分配，2：解绑，3：转移）")
    private CustomerAgentOperateRecordOperationEnum operation;

    @ApiModelProperty(value = "备注")
    private String description;

    @ApiModelProperty(value = "类型（0：默认）")
    private CustomerAgentOperateRecordTypeEnum type;

    @ApiModelProperty(value = "状态（0：默认）")
    private CustomerAgentOperateRecordStateEnum state;

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
     * 查询分页方法
     */
    public interface Select{}

    /**
     * 查询方法
     */
    public interface SelectList{}

    /**
     * 单条查询
     */
    public interface SelectOne{}

    /**
     * 总数参数
     */
    public interface Count{}
}


