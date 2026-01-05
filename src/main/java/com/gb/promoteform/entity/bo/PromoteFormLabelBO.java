package com.gb.promoteform.entity.bo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gb.promoteform.entity.enums.PromoteFormLabelStateEnum;
import com.gb.promoteform.entity.enums.PromoteFormLabelTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * TODO 推广表单标签传输
 * 代码生成器
 *
 * @author lijh
 * @className PromoteFormLabelBO
 * @time 2022-07-04 10:49:04
 */
@Data
@ApiModel(value = "推广表单标签传输")
public class PromoteFormLabelBO implements Serializable {

    @ApiModelProperty(value = "序列")
    @NotBlank(groups = {PromoteFormLabelBO.Update.class, PromoteFormLabelBO.Remove.class}, message = "id不能为空")
    private String id;

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

    @ApiModelProperty(value = "类型（0：默认）")
    private PromoteFormLabelTypeEnum type;

    @ApiModelProperty(value = "状态（0：默认）")
    private PromoteFormLabelStateEnum state;

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


