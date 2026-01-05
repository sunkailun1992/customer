package com.gb.promoteform.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gb.promoteform.entity.enums.PromoteFormButtonStateEnum;
import com.gb.promoteform.entity.enums.PromoteFormButtonStyleEnum;
import com.gb.promoteform.entity.enums.PromoteFormButtonTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * TODO 推广表单按钮渲染
 * 代码生成器
 *
 * @author lijh
 * @className PromoteFormButtonVO
 * @time 2022-07-04 10:49:04
 */
@Data
@ApiModel(value = "推广表单按钮渲染")
public class PromoteFormButtonVO implements Serializable {

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

    /**
     * {@link PromoteFormButtonTypeEnum}
     */
    @ApiModelProperty(value = "类型（0：表单提交，1：投保链接，2：选择险种）")
    private Integer type;

    /**
     * {@link PromoteFormButtonStateEnum}
     */
    @ApiModelProperty(value = "状态（0：按钮，1：底部浮窗，2：固底浮窗）")
    private Integer state;

    @ApiModelProperty(value = "标签")
    private String label;

    @ApiModelProperty(value = "排序")
    private Integer sorting;

    @ApiModelProperty(value = "版本号")
    private Integer version;
}


