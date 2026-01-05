package com.gb.promoteform.entity.bo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gb.promoteform.entity.enums.PromoteFormFieldStateEnum;
import com.gb.promoteform.entity.enums.PromoteFormFieldTextBoxEnum;
import com.gb.promoteform.entity.enums.PromoteFormFieldTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * TODO 推广表单字段传输
 * 代码生成器
 *
 * @author lijh
 * @className PromoteFormFieldBO
 * @time 2022-07-04 10:49:04
 */
@Data
@ApiModel(value = "推广表单字段传输")
public class PromoteFormFieldBO implements Serializable {

    @ApiModelProperty(value = "序列")
    @NotBlank(groups = {PromoteFormFieldBO.Update.class, PromoteFormFieldBO.Remove.class}, message = "id不能为空")
    private String id;

    @ApiModelProperty(value = "推广表单id")
    private String promoteFormId;

    @ApiModelProperty(value = "编码")
    private String code;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "文本框（0：手机框，1：输入框，2：地区框）")
    private PromoteFormFieldTextBoxEnum textBox;

    @ApiModelProperty(value = "帮助提示信息")
    private String prompt;

    @ApiModelProperty(value = "placehoder提示")
    private String placehoder;

    @ApiModelProperty(value = "长度")
    private Integer length;

    @ApiModelProperty(value = "后缀单位")
    private String suffix;

    @ApiModelProperty(value = "必填")
    private Boolean mandatory;

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
     * {@link PromoteFormFieldTypeEnum}
     */
    @ApiModelProperty(value = "类型（0：企业名称，1：项目地区，2：联系方式）")
    private Integer type;

    @ApiModelProperty(value = "状态（0：默认）")
    private PromoteFormFieldStateEnum state;

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


