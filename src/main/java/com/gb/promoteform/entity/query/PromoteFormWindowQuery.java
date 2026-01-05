package com.gb.promoteform.entity.query;


import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.gb.promoteform.entity.enums.PromoteFormWindowTypeEnum;
import com.gb.promoteform.entity.enums.PromoteFormWindowStateEnum;


/**
 * TODO 推广表单窗口查询
 * 代码生成器
 *
 * @author lijh
 * @className PromoteFormWindowQuery
 * @time 2022-07-04 10:49:05
 */
@Data
@ApiModel(value = "推广表单窗口查询")
public class PromoteFormWindowQuery implements Serializable {

    @ApiModelProperty(value = "序列")
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

    @ApiModelProperty(value = "类型（0：默认）")
    private PromoteFormWindowTypeEnum type;

    @ApiModelProperty(value = "状态（0：默认）")
    private PromoteFormWindowStateEnum state;

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


