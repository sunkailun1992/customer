package com.gb.customer.entity.query;


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
 * @description: TODO 助理关联查询
 * @source: 代码生成器
 */
@Data
@ApiModel(value = "助理关联查询")
public class AssistantAssociatedQuery implements Serializable {

    @ApiModelProperty(value = "序列")
    private String id;

    @ApiModelProperty(value = "助理id")
    private String assistantId;

    @ApiModelProperty(value = "管理id")
    private String housekeeperId;

    @ApiModelProperty(value = "说明")
    private String description;

    @ApiModelProperty(value = "类型（0：业务管家，1：特别业务管家）")
    private AssistantAssociatedTypeEnum type;

    @ApiModelProperty(value = "状态（0：不启用，1：启用）")
    private AssistantAssociatedStateEnum state;

    @ApiModelProperty(value = "标签")
    private String label;

    @ApiModelProperty(value = "排序")
    private Integer sorting;

    @ApiModelProperty(value = "显示字段")
    private String fields;

    @ApiModelProperty(value = "排序规则(0:desc,1:asc)")
    private Boolean collation;

    @ApiModelProperty(value = "排序字段")
    private String collationFields = "create_date_time";

    @ApiModelProperty(value = "模糊查询")
    private String query;

    @TableField(exist = false)
    private Integer type2 = (null == type ? null : type.getValue());

    /**
     * 查询分页方法
     */
    public interface Select {
    }

    /**
     * 查询方法
     */
    public interface SelectList {
    }

    /**
     * 单条查询
     */
    public interface SelectOne {
    }

    /**
     * 总数参数
     */
    public interface Count {
    }
}


