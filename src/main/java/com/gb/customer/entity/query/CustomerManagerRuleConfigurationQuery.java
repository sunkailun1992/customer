package com.gb.customer.entity.query;


import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.gb.customer.entity.enums.CustomerManagerRuleConfigurationStateEnum;
import com.gb.customer.entity.enums.CustomerManagerRuleConfigurationTypeEnum;


/**
 * Created with IntelliJ IDEA.
 * @author:     	ljh
 * @since:   	    2021-09-07 02:27:46
 * @description:	TODO  客户管理规则配置表查询
 * @source:  	    代码生成器
 */
@Data
@ApiModel(value = "客户管理规则配置表查询")
public class CustomerManagerRuleConfigurationQuery implements Serializable {

    @ApiModelProperty(value = "序列")
    private String id;

    @ApiModelProperty(value = "规则状态（0：开启，1：关闭）")
    private Integer ruleStatus;

    @ApiModelProperty(value = "说明")
    private String description;

    @ApiModelProperty(value = "类型")
    private CustomerManagerRuleConfigurationTypeEnum type;

    @ApiModelProperty(value = "状态")
    private CustomerManagerRuleConfigurationStateEnum state;

    @ApiModelProperty(value = "标签")
    private String label;

    @ApiModelProperty(value = "排序")
    private Integer sorting;

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


