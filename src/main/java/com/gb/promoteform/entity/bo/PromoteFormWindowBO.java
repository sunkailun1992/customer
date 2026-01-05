package com.gb.promoteform.entity.bo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.gb.promoteform.entity.enums.PromoteFormWindowTypeEnum;
import com.gb.promoteform.entity.enums.PromoteFormWindowStateEnum;


/**
 * TODO 推广表单窗口传输
 * 代码生成器
 *
 * @author lijh
 * @className PromoteFormWindowBO
 * @time 2022-07-04 10:49:05
 */
@Data
@ApiModel(value = "推广表单窗口传输")
public class PromoteFormWindowBO implements Serializable {

    @ApiModelProperty(value = "序列")
    @NotBlank(groups = {PromoteFormWindowBO.Update.class, PromoteFormWindowBO.Remove.class}, message = "id不能为空")
    private String id;

    @ApiModelProperty(value = "推广表单id")
    private String promoteFormId;

    @ApiModelProperty(value = "编码")
    private String code;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "文本")
    private String text;

    @ApiModelProperty(value = "联系人展示")
    private Boolean contactDisplay;

    @ApiModelProperty(value = "联系人文案")
    private String contactCopy;

    @ApiModelProperty(value = "按钮文案")
    private String buttonCopy;

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
    private PromoteFormWindowTypeEnum type;

    @ApiModelProperty(value = "状态（0：默认）")
    private PromoteFormWindowStateEnum state;

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


