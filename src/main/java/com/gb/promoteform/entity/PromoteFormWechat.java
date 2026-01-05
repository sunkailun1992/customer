package com.gb.promoteform.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import com.gb.bean.EntityBase;
import com.gb.promoteform.entity.enums.PromoteFormWechatStateEnum;
import com.gb.promoteform.entity.enums.PromoteFormWechatTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


/**
 * TODO 推广表单微信对象
 * 代码生成器
 *
 * @author lijh
 * @className PromoteFormWechat
 * @time 2022-07-04 10:49:05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("`promote_form_wechat`")
@ApiModel(value = "PromoteFormWechat对象", description = "推广表单微信")
public class PromoteFormWechat extends EntityBase {

    @ApiModelProperty(value = "推广表单id")
    private String promoteFormId;

    @ApiModelProperty(value = "编码")
    private String code;

    @ApiModelProperty(value = "icon图片")
    private String icon;

    @ApiModelProperty(value = "标题")
    private String title;

    /**
     * {@link PromoteFormWechatTypeEnum}
     */
    @ApiModelProperty(value = "类型（0：默认）")
    private Integer type;

    /**
     * {@link PromoteFormWechatStateEnum}
     */
    @ApiModelProperty(value = "状态（0：默认）")
    private Integer state;

    @ApiModelProperty(value = "标签")
    private String label;
}
