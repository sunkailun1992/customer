package com.gb.promoteform.entity.query;


import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.gb.promoteform.entity.enums.PromoteFormFieldTextBoxEnum;
import com.gb.promoteform.entity.enums.PromoteFormFieldTypeEnum;
import com.gb.promoteform.entity.enums.PromoteFormFieldStateEnum;


/**
 * TODO 推广表单字段查询
 * 代码生成器
 *
 * @author lijh
 * @className PromoteFormFieldQuery
 * @time 2022-07-04 10:49:04
 */
@Data
@ApiModel(value = "推广表单字段查询")
public class PromoteFormFieldQuery implements Serializable {

    @ApiModelProperty(value = "序列")
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

    @ApiModelProperty(value = "类型（0：企业名称，1：项目地区，2：联系方式）")
    private PromoteFormFieldTypeEnum type;

    @ApiModelProperty(value = "状态（0：默认）")
    private PromoteFormFieldStateEnum state;

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


