package com.gb.customer.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.baomidou.mybatisplus.annotation.*;
import com.gb.customer.entity.enums.CustomerManagerRuleConfigurationRuleStatusEnum;
import com.gb.customer.entity.enums.CustomerManagerRuleConfigurationRuleStatusEnum;
import com.gb.bean.EntityBase;
/**
 * Created with IntelliJ IDEA.
 * @author:     	ljh
 * @since:   	    2021-09-07 02:27:46
 * @description:	TODO  客户管理规则配置表
 * @source:  	    代码生成器
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("`customer_manager_rule_configuration`")
@ApiModel(value="CustomerManagerRuleConfiguration对象", description="客户管理规则配置表")
public class CustomerManagerRuleConfiguration extends EntityBase {

    @ApiModelProperty(value = "规则状态（0：开启，1：关闭）")
    private CustomerManagerRuleConfigurationRuleStatusEnum ruleStatus;

    @ApiModelProperty(value = "类型")
    private Integer type;

    @ApiModelProperty(value = "状态")
    private Integer state;

    @ApiModelProperty(value = "标签")
    private String label;
}
