package com.gb.promoteform.entity.query;


import com.gb.promoteform.entity.enums.PromoteFormButtonStateEnum;
import com.gb.promoteform.entity.enums.PromoteFormButtonStyleEnum;
import com.gb.promoteform.entity.enums.PromoteFormButtonTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


/**
 * TODO 推广表单按钮查询
 * 代码生成器
 *
 * @author lijh
 * @className PromoteFormButtonQuery
 * @time 2022-07-04 10:49:04
 */
@Data
@ApiModel(value = "推广表单按钮查询")
public class PromoteFormButtonQuery implements Serializable {

    @ApiModelProperty(value = "序列")
    private String id;

    @ApiModelProperty(value = "推广表单id")
    private String promoteFormId;

    @ApiModelProperty(value = "编码")
    private String code;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "样式（0：普通按钮，1：焦点按钮）")
    private PromoteFormButtonStyleEnum style;

    @ApiModelProperty(value = "险种分类id")
    private String dangerPlantedCategoryId;

    @ApiModelProperty(value = "险种id")
    private String dangerPlantedId;

    @ApiModelProperty(value = "险种名称")
    private String dangerPlantedName;

    @ApiModelProperty(value = "说明")
    private String description;

    @ApiModelProperty(value = "类型（0：表单提交，1：投保链接，2：选择险种）")
    private PromoteFormButtonTypeEnum type;

    @ApiModelProperty(value = "状态（0：按钮，1：底部浮窗，2：固底浮窗）")
    private PromoteFormButtonStateEnum state;

    @ApiModelProperty(value = "标签")
    private String label;

    @ApiModelProperty(value = "排序")
    private Integer sorting;

    @ApiModelProperty(value = "追加")
    private Boolean assignment;

    @ApiModelProperty(value = "显示字段")
    private String fields;

    @ApiModelProperty(value = "排序规则(0:desc,1:asc)")
    private Boolean collation;

    @ApiModelProperty(value = "排序字段")
    private String collationFields = "create_date_time";

    @ApiModelProperty(value = "模糊查询")
    private String query;

    /**
     * 查询分页方法
     */
    public interface Select{}

    /**
     * 查询方法
     */
    public interface SelectList{}

    /**
     * 单条查询
     */
    public interface SelectOne{}

    /**
     * 总数参数
     */
    public interface Count{}
}


