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
import com.gb.configuration.entity.enums.ProjectTypeTypeEnum;
import com.gb.configuration.entity.enums.ProjectTypeStateEnum;
import com.gb.bean.EntityBase;


/**
 * TODO 项目类型对象
 * 代码生成器
 *
 * @author lijh
 * @className ProjectType
 * @time 2022-05-05 02:45:25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("`project_type`")
@ApiModel(value = "ProjectType对象", description = "项目类型")
public class ProjectType extends EntityBase {

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "编码")
    private String code;

    @ApiModelProperty(value = "类型（0：默认）")
    private ProjectTypeTypeEnum type;

    @ApiModelProperty(value = "状态（0：默认）")
    private ProjectTypeStateEnum state;

    @ApiModelProperty(value = "标签")
    private String label;
}
