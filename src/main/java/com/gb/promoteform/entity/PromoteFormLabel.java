package com.gb.promoteform.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import com.gb.bean.EntityBase;
import com.gb.promoteform.entity.enums.PromoteFormLabelStateEnum;
import com.gb.promoteform.entity.enums.PromoteFormLabelTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


/**
 * TODO 推广表单标签对象
 * 代码生成器
 *
 * @author lijh
 * @className PromoteFormLabel
 * @time 2022-07-04 10:49:04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("`promote_form_label`")
@ApiModel(value = "PromoteFormLabel对象", description = "推广表单标签")
public class PromoteFormLabel extends EntityBase {

    @ApiModelProperty(value = "推广表单id")
    private String promoteFormId;

    @ApiModelProperty(value = "编码")
    private String code;

    @ApiModelProperty(value = "用户类型值id")
    private String userTypeValueId;

    @ApiModelProperty(value = "用户类型值名称")
    private String userTypeValueName;

    @ApiModelProperty(value = "链接地址")
    private String linkAddress;

    /**
     * {@link PromoteFormLabelTypeEnum}
     */
    @ApiModelProperty(value = "类型（0：默认）")
    private Integer type;

    /**
     * {@link PromoteFormLabelStateEnum}
     */
    @ApiModelProperty(value = "状态（0：默认）")
    private Integer state;

    @ApiModelProperty(value = "标签")
    private String label;
}
