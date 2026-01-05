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
import com.gb.customer.entity.enums.CustomerAgentOperateRecordSourceEnum;
import com.gb.customer.entity.enums.CustomerAgentOperateRecordOperationEnum;
import com.gb.customer.entity.enums.CustomerAgentOperateRecordTypeEnum;
import com.gb.customer.entity.enums.CustomerAgentOperateRecordStateEnum;
import com.gb.bean.EntityBase;


/**
 * TODO 客户经纪人操作记录对象
 * 代码生成器
 *
 * @author lijh
 * @className CustomerAgentOperateRecord
 * @time 2022-09-20 11:02:25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("`customer_agent_operate_record`")
@ApiModel(value = "CustomerAgentOperateRecord对象", description = "客户经纪人操作记录")
public class CustomerAgentOperateRecord extends EntityBase {

    @ApiModelProperty(value = "客户id")
    private String customerId;

    @ApiModelProperty(value = "经纪人id")
    private String agentUserId;

    @ApiModelProperty(value = "来源（0：公海分配，1：分销添加，2：自营添加，3：自营解绑，4：自营转交，5：链接参数）")
    private CustomerAgentOperateRecordSourceEnum source;

    @ApiModelProperty(value = "操作（0：关联，1：分配，2：解绑，3：转移）")
    private CustomerAgentOperateRecordOperationEnum operation;

    @ApiModelProperty(value = "类型（0：默认）")
    private CustomerAgentOperateRecordTypeEnum type;

    @ApiModelProperty(value = "状态（0：默认）")
    private CustomerAgentOperateRecordStateEnum state;

    @ApiModelProperty(value = "标签")
    private String label;
}
