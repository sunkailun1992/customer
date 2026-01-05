package com.gb.promoteform.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.gb.bean.EntityBase;
import com.gb.promoteform.entity.enums.PromoteFormFloatingWindowStateEnum;
import com.gb.promoteform.entity.enums.PromoteFormFloatingWindowTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;


/**
 * TODO 推广表单浮框对象
 * 代码生成器
 *
 * @author lijh
 * @className PromoteFormFloatingWindow
 * @time 2022-07-04 10:49:04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("`promote_form_floating_window`")
@ApiModel(value = "PromoteFormFloatingWindow对象", description = "推广表单浮框")
public class PromoteFormFloatingWindow extends EntityBase {

    @ApiModelProperty(value = "推广表单id")
    private String promoteFormId;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "编码")
    private String code;

    /**
     * {@link PromoteFormFloatingWindowTypeEnum}
     */
    @ApiModelProperty(value = "类型（0：默认）")
    private Integer type;

    /**
     * {@link PromoteFormFloatingWindowStateEnum}
     */
    @ApiModelProperty(value = "状态（0：默认）")
    private Integer state;

    @ApiModelProperty(value = "标签")
    private String label;

    @TableField(exist = false)
    @ApiModelProperty(value = "浮框险种集合")
    private List<PromoteFormFloatingWindowDangerPlanted> windowDangerPlantedList;
}
