package com.gb.customer.entity.bo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.gb.customer.entity.enums.CustomerManagerRuleConfigurationStateEnum;
import com.gb.customer.entity.enums.CustomerManagerRuleConfigurationTypeEnum;


/**
 * Created with IntelliJ IDEA.
 * @author:     	ljh
 * @since:   	    2021-09-07 02:27:46
 * @description:	TODO  客户管理规则配置表传输
 * @source:  	    代码生成器
 */
@Data
@ApiModel(value = "客户管理规则配置表传输")
public class CustomerManagerRuleConfigurationBO implements Serializable {

    @ApiModelProperty(value = "序列")
    @NotBlank(groups = {CustomerManagerRuleConfigurationBO.Update.class,CustomerManagerRuleConfigurationBO.Remove.class},message = "id不能为空")
    private String id;

    @ApiModelProperty(value = "规则状态（0：开启，1：关闭）")
    private Integer ruleStatus;

    @ApiModelProperty(value = "说明")
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

    @ApiModelProperty(value = "删除状态")
    private Boolean isDelete;

    @ApiModelProperty(value = "类型")
    private CustomerManagerRuleConfigurationTypeEnum type;

    @ApiModelProperty(value = "状态")
    private CustomerManagerRuleConfigurationStateEnum state;

    @ApiModelProperty(value = "标签")
    private String label;

    @ApiModelProperty(value = "排序")
    private Integer sorting;

    @ApiModelProperty(value = "版本号")
    private Integer version;


    /**
    * 新增
    */
    public interface Save{}

    /**
    * 修改
    */
    public interface Update{}

    /**
    * 删除
    */
    public interface Remove{}
}


