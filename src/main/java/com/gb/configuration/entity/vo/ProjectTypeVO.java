package com.gb.configuration.entity.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.gb.configuration.entity.enums.ProjectTypeTypeEnum;
import com.gb.configuration.entity.enums.ProjectTypeStateEnum;


/**
 * TODO 项目类型渲染
 * 代码生成器
 *
 * @author lijh
 * @className ProjectTypeVO
 * @time 2022-05-05 02:45:25
 */
@Data
@ApiModel(value = "项目类型渲染")
public class ProjectTypeVO implements Serializable {

    @ApiModelProperty(value = "序列")
    private String id;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "编码")
    private String code;

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

    @ApiModelProperty(value = "删除")
    private Boolean isDelete;

    @ApiModelProperty(value = "类型（0：默认）")
    private ProjectTypeTypeEnum type;

    @ApiModelProperty(value = "状态（0：默认）")
    private ProjectTypeStateEnum state;

    @ApiModelProperty(value = "标签")
    private String label;

    @ApiModelProperty(value = "排序")
    private Integer sorting;

    @ApiModelProperty(value = "版本号")
    private Integer version;

    @TableField(exist = false)
    @ApiModelProperty(hidden = true, value = "项目类型值集合")
    private Object projectTypeValueList;
}


