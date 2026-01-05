package com.gb.customer.entity.bo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.gb.customer.entity.enums.AssistantAssociatedStateEnum;
import com.gb.customer.entity.enums.AssistantAssociatedTypeEnum;


/**
 * Created with IntelliJ IDEA.
 * @author:     	lijh
 * @since:   	    2021-11-02 10:03:07
 * @description:	TODO  助理关联传输
 * @source:  	    代码生成器
 */
@Data
@ApiModel(value = "助理关联传输")
public class AssistantAssociatedBO implements Serializable {

    @ApiModelProperty(value = "序列")
    @NotBlank(groups = {AssistantAssociatedBO.Update.class,AssistantAssociatedBO.Remove.class},message = "id不能为空")
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


