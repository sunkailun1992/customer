package com.gb.promoteform.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.gb.bean.EntityBase;
import com.gb.promoteform.entity.enums.PromoteFormFloatingWindowDangerPlantedStateEnum;
import com.gb.promoteform.entity.enums.PromoteFormFloatingWindowDangerPlantedTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;


/**
 * TODO 推广表单浮框险种对象
 * 代码生成器
 *
 * @author lijh
 * @className PromoteFormFloatingWindowDangerPlanted
 * @time 2022-07-04 10:49:04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("`promote_form_floating_window_danger_planted`")
@ApiModel(value = "PromoteFormFloatingWindowDangerPlanted对象", description = "推广表单浮框险种")
public class PromoteFormFloatingWindowDangerPlanted extends EntityBase {

    @ApiModelProperty(value = "推广表单浮框id")
    private String promoteFormFloatingWindowId;

    @ApiModelProperty(value = "编码")
    private String code;

    @ApiModelProperty(value = "险种分类id")
    private String dangerPlantedCategoryId;

    @ApiModelProperty(value = "险种id")
    private String dangerPlantedId;

    /**
     * {@link PromoteFormFloatingWindowDangerPlantedTypeEnum}
     */
    @ApiModelProperty(value = "类型（0：默认）")
    private Integer type;

    /**
     * {@link PromoteFormFloatingWindowDangerPlantedStateEnum}
     */
    @ApiModelProperty(value = "状态（0：默认）")
    private Integer state;

    @ApiModelProperty(value = "标签")
    private String label;

    @TableField(exist = false)
    @ApiModelProperty(value = "险种名称")
    private String dangerPlantedName;

    @TableField(exist = false)
    @ApiModelProperty(value = "浮框险种产品集合")
    private List<PromoteFormFloatingWindowDangerPlantedSpu> windowDangerPlantedSpuList;
}
