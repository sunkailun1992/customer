package com.gb.customer.entity.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.gb.customer.entity.enums.CustomerAgentOperateRecordOperationEnum;
import com.gb.customer.entity.enums.CustomerAgentOperateRecordSourceEnum;
import com.gb.customer.entity.enums.CustomerAgentOperateRecordStateEnum;
import com.gb.customer.entity.enums.CustomerAgentOperateRecordTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * TODO 客户经纪人操作记录渲染
 * 代码生成器
 *
 * @author lijh
 * @className CustomerAgentOperateRecordVO
 * @time 2022-09-20 11:02:25
 */
@Data
@ApiModel(value = "客户经纪人操作记录渲染")
public class CustomerAgentOperateRecordVO implements Serializable {

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

    @ApiModelProperty(value = "创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createDateTime;

    @ApiModelProperty(value = "创建人")
    private String createName;

    @ApiModelProperty(value = "修改时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime modifyDateTime;

    @ApiModelProperty(value = "修改人")
    private String modifyName;

    @ApiModelProperty(value = "删除")
    private Boolean isDelete;

    @ApiModelProperty(value = "类型（0：默认）")
    private CustomerAgentOperateRecordTypeEnum type;

    @ApiModelProperty(value = "状态（0：默认）")
    private CustomerAgentOperateRecordStateEnum state;

    @ApiModelProperty(value = "标签")
    private String label;

    @ApiModelProperty(value = "排序")
    private Integer sorting;

    @ApiModelProperty(value = "版本号")
    private Integer version;

    @TableField(exist = false)
    @ApiModelProperty(value = "经纪人名称")
    private String agentUserName;
}


