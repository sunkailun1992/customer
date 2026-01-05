package com.gb.customer.entity.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.gb.customer.entity.enums.AssistantAssociatedStateEnum;
import com.gb.customer.entity.enums.AssistantAssociatedTypeEnum;


/**
 * Created with IntelliJ IDEA.
 *
 * @author: lijh
 * @since: 2021-11-02 10:03:07
 * @description: TODO 助理关联渲染
 * @source: 代码生成器
 */
@Data
@ApiModel(value = "助理关联渲染")
public class AssistantAssociatedVO implements Serializable {

    @ApiModelProperty(value = "序列")
    private String id;

    @ApiModelProperty(value = "助理id")
    private String assistantId;

    @ApiModelProperty(value = "管理id")
    private String housekeeperId;

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

    @ApiModelProperty(value = "类型（0：业务管家，1：特别业务管家）")
    private AssistantAssociatedTypeEnum type;

    @ApiModelProperty(value = "状态（0：不启用，1：启用）")
    private AssistantAssociatedStateEnum state;

    @ApiModelProperty(value = "标签")
    private String label;

    @ApiModelProperty(value = "排序")
    private Integer sorting;

    @ApiModelProperty(value = "版本号")
    private Integer version;

    @TableField(exist = false)
    @ApiModelProperty(value = "助理真实姓名")
    private String assistantRealName;

    @TableField(exist = false)
    @ApiModelProperty(value = "助理手机号")
    private String assistantPhone;

    @TableField(exist = false)
    @ApiModelProperty(value = "管理手机号")
    private String housekeeperPhone;

    @TableField(exist = false)
    @ApiModelProperty(value = "管理真实姓名")
    private String housekeeperRealName;

    private String type2;

    public Integer getType2() {
        return (null == type ? null : type.getValue());
    }
}


