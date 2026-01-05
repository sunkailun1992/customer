package com.gb.promoteform.entity;


import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.gb.bean.EntityBase;
import com.gb.promoteform.entity.enums.PromoteFormProductStateEnum;
import com.gb.promoteform.entity.enums.PromoteFormProductTypeEnum;
import com.gb.promoteform.entity.vo.PromoteFormProductSpuVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;


/**
 * TODO 推广表单产品对象
 * 代码生成器
 *
 * @author lijh
 * @className PromoteFormProduct
 * @time 2022-07-04 10:49:04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("`promote_form_product`")
@ApiModel(value = "PromoteFormProduct对象", description = "推广表单产品")
public class PromoteFormProduct extends EntityBase {

    @ApiModelProperty(value = "推广表单id")
    private String promoteFormId;

    @ApiModelProperty(value = "编码")
    private String code;

    @ApiModelProperty(value = "icon图片")
    private String icon;

    @ApiModelProperty(value = "标题")
    private String title;

    @TableField(updateStrategy = FieldStrategy.IGNORED)
    @ApiModelProperty(value = "险种分类id")
    private String dangerPlantedCategoryId;

    @TableField(updateStrategy = FieldStrategy.IGNORED)
    @ApiModelProperty(value = "险种id")
    private String dangerPlantedId;

    @TableField(updateStrategy = FieldStrategy.IGNORED)
    @ApiModelProperty(value = "跳转推广表单id")
    private String insuredPromoteFormId;

    @ApiModelProperty(value = "投保链接地址")
    private String insuredLinkAddress;

    /**
     * {@link PromoteFormProductTypeEnum}
     */
    @ApiModelProperty(value = "类型（0：信息收集表单，1：投保链接）")
    private Integer type;

    /**
     * {@link PromoteFormProductStateEnum}
     */
    @ApiModelProperty(value = "状态（0：默认）")
    private Integer state;

    @ApiModelProperty(value = "标签")
    private String label;

    @TableField(exist = false)
    @ApiModelProperty(value = "险种名称")
    private String dangerPlantedName;

    @TableField(exist = false)
    @ApiModelProperty(value = "表单名称")
    private String formName;


    @TableField(exist = false)
    @ApiModelProperty(value = "推广表单产品集合")
    private List<PromoteFormProductSpu> promoteFormProductSpuList;
}
