package com.gb.promoteform.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.gb.bean.EntityBase;
import com.gb.promoteform.entity.enums.PromoteFormButtonStateEnum;
import com.gb.promoteform.entity.enums.PromoteFormButtonStyleEnum;
import com.gb.promoteform.entity.enums.PromoteFormButtonTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


/**
 * TODO 推广表单按钮对象
 * 代码生成器
 *
 * @author lijh
 * @className PromoteFormButton
 * @time 2022-07-04 10:49:04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("`promote_form_button`")
@ApiModel(value = "PromoteFormButton对象", description = "推广表单按钮")
public class PromoteFormButton extends EntityBase {

    @ApiModelProperty(value = "推广表单id")
    private String promoteFormId;

    @ApiModelProperty(value = "编码")
    private String code;

    @ApiModelProperty(value = "名称")
    private String name;

    /**
     * {@link PromoteFormButtonStyleEnum}
     */
    @ApiModelProperty(value = "样式（0：普通按钮，1：焦点按钮）")
    private Integer style;

    @ApiModelProperty(value = "险种分类id")
    private String dangerPlantedCategoryId;

    @ApiModelProperty(value = "险种id")
    private String dangerPlantedId;

    /**
     * {@link PromoteFormButtonTypeEnum}
     */
    @ApiModelProperty(value = "类型（0：表单提交，1：投保链接，2：选择险种，3：选择产品）")
    private Integer type;

    /**
     * {@link PromoteFormButtonStateEnum}
     */
    @ApiModelProperty(value = "状态（0：按钮，1：底部浮窗，2：固底浮窗）")
    private Integer state;

    @ApiModelProperty(value = "标签")
    private String label;

    @TableField(exist = false)
    @ApiModelProperty(value = "险种名称")
    private String dangerPlantedName;
}
