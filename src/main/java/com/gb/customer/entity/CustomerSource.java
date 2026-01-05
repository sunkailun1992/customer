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
import com.gb.customer.entity.enums.CustomerSourceTypeEnum;
import com.gb.customer.entity.enums.CustomerSourceStateEnum;
import com.gb.bean.EntityBase;


/**
 * TODO 客户来源对象
 * 代码生成器
 *
 * @author lijh
 * @className CustomerSource
 * @time 2022-09-01 03:12:09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("`customer_source`")
@ApiModel(value = "CustomerSource对象", description = "客户来源")
public class CustomerSource extends EntityBase {

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "编码")
    private String code;

    @ApiModelProperty(value = "类型（0：默认）")
    private CustomerSourceTypeEnum type;

    @ApiModelProperty(value = "状态（0：默认）")
    private CustomerSourceStateEnum state;

    @ApiModelProperty(value = "标签")
    private String label;
}
