package com.gb.promoteform.entity;


import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.gb.bean.EntityBase;
import com.gb.promoteform.entity.enums.PromoteFormFieldTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


/**
 * TODO 推广表单字段对象
 * 代码生成器
 *
 * @author lijh
 * @className PromoteFormField
 * @time 2022-07-04 10:49:04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("`promote_form_field`")
@ApiModel(value = "PromoteFormField对象", description = "推广表单字段")
public class PromoteFormField extends EntityBase {

    @ApiModelProperty(value = "推广表单id")
    private String promoteFormId;

    @ApiModelProperty(value = "编码")
    private String code;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "文本框（0：手机框，1：输入框，2：地区框）")
    private Integer textBox;

    @ApiModelProperty(value = "帮助提示信息")
    private String prompt;

    @ApiModelProperty(value = "placehoder提示")
    private String placehoder;

    @TableField(updateStrategy = FieldStrategy.IGNORED)
    @ApiModelProperty(value = "长度")
    private Integer length;

    @ApiModelProperty(value = "后缀单位")
    private String suffix;

    @ApiModelProperty(value = "必填")
    private Boolean mandatory;

    /**
     * {@link PromoteFormFieldTypeEnum}
     */
    @ApiModelProperty(value = "类型（0：企业名称，1：项目地区，2：手机号码）")
    private Integer type;

    @ApiModelProperty(value = "状态（0：默认）")
    private Integer state;

    @ApiModelProperty(value = "标签")
    private String label;

    @TableField(exist = false)
    @ApiModelProperty(value = "类型名称")
    private String typeName;
}
