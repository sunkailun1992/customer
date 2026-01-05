package com.gb.promoteform.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import com.gb.bean.EntityBase;
import com.gb.promoteform.entity.enums.PromoteFormWindowStateEnum;
import com.gb.promoteform.entity.enums.PromoteFormWindowTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


/**
 * TODO 推广表单窗口对象
 * 代码生成器
 *
 * @author lijh
 * @className PromoteFormWindow
 * @time 2022-07-04 10:49:05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("`promote_form_window`")
@ApiModel(value = "PromoteFormWindow对象", description = "推广表单窗口")
public class PromoteFormWindow extends EntityBase {

    @ApiModelProperty(value = "推广表单id")
    private String promoteFormId;

    @ApiModelProperty(value = "编码")
    private String code;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "文本")
    private String text;

    @ApiModelProperty(value = "联系人展示")
    private Boolean contactDisplay;

    @ApiModelProperty(value = "联系人文案")
    private String contactCopy;

    @ApiModelProperty(value = "按钮文案")
    private String buttonCopy;

    /**
     * {@link PromoteFormWindowTypeEnum}
     */
    @ApiModelProperty(value = "类型（0：默认）")
    private Integer type;

    /**
     * {@link PromoteFormWindowStateEnum}
     */
    @ApiModelProperty(value = "状态（0：默认）")
    private Integer state;

    @ApiModelProperty(value = "标签")
    private String label;
}
