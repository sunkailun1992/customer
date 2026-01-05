package com.gb.customer.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.*;
import com.gb.customer.entity.enums.AssistantAssociatedTypeEnum;
import com.gb.customer.entity.enums.AssistantAssociatedStateEnum;
import com.gb.bean.EntityBase;

/**
 * 废弃
 * <p>
 * Created with IntelliJ IDEA.
 *
 * @author: lijh
 * @since: 2021-11-02 10:03:07
 * @description: TODO 助理关联
 * @source: 代码生成器
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("`assistant_associated`")
@ApiModel(value = "AssistantAssociated对象", description = "助理关联")
public class AssistantAssociated extends EntityBase {

    @ApiModelProperty(value = "助理id")
    private String assistantId;

    @ApiModelProperty(value = "管理id")
    private String housekeeperId;

    @ApiModelProperty(value = "类型（0：业务管家，1：特别业务管家）")
    private AssistantAssociatedTypeEnum type;

    @ApiModelProperty(value = "状态（0：不启用，1：启用）")
    private AssistantAssociatedStateEnum state;

    @ApiModelProperty(value = "标签")
    private String label;
}
