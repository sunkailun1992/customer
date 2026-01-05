package com.gb.configuration.entity.bo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.gb.configuration.entity.enums.ProjectTypeValueTypeEnum;
import com.gb.configuration.entity.enums.ProjectTypeValueStateEnum;


/**
 * TODO 项目类型值传输
 * 代码生成器
 *
 * @author lijh
 * @className ProjectTypeValueBO
 * @time 2022-05-05 02:45:25
 */
@Data
@ApiModel(value = "项目类型值传输")
public class ProjectTypeValueBO implements Serializable {

    @ApiModelProperty(value = "序列")
    @NotBlank(groups = {ProjectTypeValueBO.Update.class, ProjectTypeValueBO.Remove.class}, message = "id不能为空")
    private String id;

    @ApiModelProperty(value = "项目类型id")
    private String projectTypeId;

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
    private ProjectTypeValueTypeEnum type;

    @ApiModelProperty(value = "状态（0：默认）")
    private ProjectTypeValueStateEnum state;

    @ApiModelProperty(value = "标签")
    private String label;

    @ApiModelProperty(value = "排序")
    private Integer sorting;

    @ApiModelProperty(value = "版本号")
    private Integer version;


    /**
     * 新增
     */
    public interface Save{}

    /**
     * 修改
     */
    public interface Update{}

    /**
     * 删除
     */
    public interface Remove{}
}


