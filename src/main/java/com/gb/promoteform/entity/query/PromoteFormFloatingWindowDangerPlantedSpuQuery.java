package com.gb.promoteform.entity.query;


import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.gb.promoteform.entity.enums.PromoteFormFloatingWindowDangerPlantedSpuTypeEnum;
import com.gb.promoteform.entity.enums.PromoteFormFloatingWindowDangerPlantedSpuStateEnum;


/**
 * TODO 推广表单浮框险种产品查询
 * 代码生成器
 *
 * @author lijh
 * @className PromoteFormFloatingWindowDangerPlantedSpuQuery
 * @time 2022-10-28 03:09:31
 */
@Data
@ApiModel(value = "推广表单浮框险种产品查询")
public class PromoteFormFloatingWindowDangerPlantedSpuQuery implements Serializable {

    @ApiModelProperty(value = "序列")
    private String id;

    @ApiModelProperty(value = "推广表单浮框id")
    private String promoteFormFloatingWindowDangerPlantedId;

    @ApiModelProperty(value = "产品id")
    private String spuId;

    @ApiModelProperty(value = "编码")
    private String code;

    @ApiModelProperty(value = "说明")
    private String description;

    @ApiModelProperty(value = "类型（0：默认）")
    private PromoteFormFloatingWindowDangerPlantedSpuTypeEnum type;

    @ApiModelProperty(value = "状态（0：默认）")
    private PromoteFormFloatingWindowDangerPlantedSpuStateEnum state;

    @ApiModelProperty(value = "标签")
    private String label;

    @ApiModelProperty(value = "排序")
    private Integer sorting;

    @ApiModelProperty(value = "追加")
    private Boolean assignment;

    @ApiModelProperty(value = "显示字段")
    private String fields;

    @ApiModelProperty(value = "排序规则(0:desc,1:asc)")
    private Boolean collation;

    @ApiModelProperty(value = "排序字段")
    private String collationFields = "create_date_time";

    @ApiModelProperty(value = "模糊查询")
    private String query;

    /**
     * 查询分页方法
     */
    public interface Select{}

    /**
     * 查询方法
     */
    public interface SelectList{}

    /**
     * 单条查询
     */
    public interface SelectOne{}

    /**
     * 总数参数
     */
    public interface Count{}
}


