package com.gb.promoteform.entity;


import com.gb.rpc.entity.ProductSpu;
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
import com.gb.promoteform.entity.enums.PromoteFormProductSpuTypeEnum;
import com.gb.promoteform.entity.enums.PromoteFormProductSpuStateEnum;
import com.gb.bean.EntityBase;


/**
 * TODO 推广表单产品id对象
 * 代码生成器
 *
 * @author lijh
 * @className PromoteFormProductSpu
 * @time 2022-10-28 03:09:32
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("`promote_form_product_spu`")
@ApiModel(value = "PromoteFormProductSpu对象", description = "推广表单产品id")
public class PromoteFormProductSpu extends EntityBase {

    @ApiModelProperty(value = "推广表单产品对象id")
    private String promoteFormProductId;

    @ApiModelProperty(value = "产品id")
    private String spuId;

    @ApiModelProperty(value = "编码")
    private String code;

    @ApiModelProperty(value = "类型（0：默认）")
    private PromoteFormProductSpuTypeEnum type;

    @ApiModelProperty(value = "状态（0：默认）")
    private PromoteFormProductSpuStateEnum state;

    @ApiModelProperty(value = "标签")
    private String label;

    @TableField(exist = false)
    @ApiModelProperty(value = "产品详情")
    private ProductSpu productSpu;
}
