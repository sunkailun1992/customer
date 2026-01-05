package com.gb.promoteform.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gb.promoteform.entity.enums.PromoteFormProductStateEnum;
import com.gb.promoteform.entity.enums.PromoteFormProductTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * TODO 推广表单产品渲染
 * 代码生成器
 *
 * @author lijh
 * @className PromoteFormProductVO
 * @time 2022-07-04 10:49:04
 */
@Data
@ApiModel(value = "推广表单产品渲染")
public class PromoteFormProductVO implements Serializable {

    @ApiModelProperty(value = "序列")
    private String id;

    @ApiModelProperty(value = "推广表单id")
    private String promoteFormId;

    @ApiModelProperty(value = "编码")
    private String code;

    @ApiModelProperty(value = "icon图片")
    private String icon;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "险种分类id")
    private String dangerPlantedCategoryId;

    @ApiModelProperty(value = "险种id")
    private String dangerPlantedId;

    @ApiModelProperty(value = "险种名称")
    private String dangerPlantedName;

    @ApiModelProperty(value = "跳转推广表单id")
    private String insuredPromoteFormId;

    @ApiModelProperty(value = "投保链接地址")
    private String insuredLinkAddress;

    @ApiModelProperty(value = "描述")
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

    @ApiModelProperty(value = "排序")
    private Integer sorting;

    @ApiModelProperty(value = "版本号")
    private Integer version;
}


