package com.gb.configuration.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.baomidou.mybatisplus.annotation.*;
import com.gb.configuration.entity.enums.ProjectTypeValueTypeEnum;
import com.gb.configuration.entity.enums.ProjectTypeValueStateEnum;
import com.gb.bean.EntityBase;


/**
 * TODO 项目类型值对象
 * 代码生成器
 *
 * @author lijh
 * @className ProjectTypeValue
 * @time 2022-05-05 02:45:25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("`project_type_value`")
@ApiModel(value = "ProjectTypeValue对象", description = "项目类型值")
public class ProjectTypeValue extends EntityBase {

    @ApiModelProperty(value = "项目类型id")
    private String projectTypeId;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "编码")
    private String code;

    @ApiModelProperty(value = "类型（0：默认）")
    private ProjectTypeValueTypeEnum type;

    @ApiModelProperty(value = "状态（0：默认）")
    private ProjectTypeValueStateEnum state;

    @ApiModelProperty(value = "标签")
    private String label;
}
