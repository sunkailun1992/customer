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
import com.gb.promoteform.entity.enums.PromoteFormFloatingWindowDangerPlantedSpuTypeEnum;
import com.gb.promoteform.entity.enums.PromoteFormFloatingWindowDangerPlantedSpuStateEnum;
import com.gb.bean.EntityBase;


/**
 * TODO 推广表单浮框险种产品对象
 * 代码生成器
 *
 * @author lijh
 * @className PromoteFormFloatingWindowDangerPlantedSpu
 * @time 2022-10-28 03:09:31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("`promote_form_floating_window_danger_planted_spu`")
@ApiModel(value = "PromoteFormFloatingWindowDangerPlantedSpu对象", description = "推广表单浮框险种产品")
public class PromoteFormFloatingWindowDangerPlantedSpu extends EntityBase {

    @ApiModelProperty(value = "推广表单浮框险种id")
    private String promoteFormFloatingWindowDangerPlantedId;

    @ApiModelProperty(value = "产品id")
    private String spuId;

    @ApiModelProperty(value = "编码")
    private String code;

    @ApiModelProperty(value = "类型（0：默认）")
    private PromoteFormFloatingWindowDangerPlantedSpuTypeEnum type;

    @ApiModelProperty(value = "状态（0：默认）")
    private PromoteFormFloatingWindowDangerPlantedSpuStateEnum state;

    @ApiModelProperty(value = "标签")
    private String label;

    @TableField(exist = false)
    @ApiModelProperty(value = "产品详情")
    private ProductSpu productSpu;
}
