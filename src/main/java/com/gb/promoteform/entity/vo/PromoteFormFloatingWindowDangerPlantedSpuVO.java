package com.gb.promoteform.entity.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.gb.rpc.entity.ProductSpu;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.gb.promoteform.entity.enums.PromoteFormFloatingWindowDangerPlantedSpuTypeEnum;
import com.gb.promoteform.entity.enums.PromoteFormFloatingWindowDangerPlantedSpuStateEnum;


/**
 * TODO 推广表单浮框险种产品渲染
 * 代码生成器
 *
 * @author lijh
 * @className PromoteFormFloatingWindowDangerPlantedSpuVO
 * @time 2022-10-28 03:09:31
 */
@Data
@ApiModel(value = "推广表单浮框险种产品渲染")
public class PromoteFormFloatingWindowDangerPlantedSpuVO implements Serializable {

    @ApiModelProperty(value = "序列")
    private String id;

    @ApiModelProperty(value = "推广表单浮框id")
    private String promoteFormFloatingWindowDangerPlantedId;

    @ApiModelProperty(value = "产品id")
    private String spuId;

    @ApiModelProperty(value = "编码")
    private String code;

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

    @ApiModelProperty(value = "类型（0：默认）")
    private PromoteFormFloatingWindowDangerPlantedSpuTypeEnum type;

    @ApiModelProperty(value = "状态（0：默认）")
    private PromoteFormFloatingWindowDangerPlantedSpuStateEnum state;

    @ApiModelProperty(value = "标签")
    private String label;

    @ApiModelProperty(value = "排序")
    private Integer sorting;

    @ApiModelProperty(value = "版本号")
    private Integer version;

    @ApiModelProperty(value = "产品详情")
    private ProductSpu productSpu;
}


