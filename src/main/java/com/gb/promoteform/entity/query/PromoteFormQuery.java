package com.gb.promoteform.entity.query;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


/**
 * TODO 推广表单查询
 * 代码生成器
 *
 * @author lijh
 * @className PromoteFormQuery
 * @time 2022-07-04 10:47:25
 */
@Data
@ApiModel(value = "推广表单查询")
public class PromoteFormQuery implements Serializable {

    @ApiModelProperty(value = "序列")
    private String id;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "编码")
    private String code;

    @ApiModelProperty(value = "经纪人")
    private Boolean agent;

    @ApiModelProperty(value = "运营")
    private Boolean operating;

    @ApiModelProperty(value = "展现终端（0：h5，1：pc）")
    private Integer displayTerminal;

    @ApiModelProperty(value = "微信")
    private Boolean wechat;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "页面色值")
    private String colorValue;

    @ApiModelProperty(value = "banner图")
    private String banner;

    @ApiModelProperty(value = "内容介绍")
    private String contentIntroduce;

    @ApiModelProperty(value = "底部介绍")
    private String bottomIntroduce;

    @ApiModelProperty(value = "启用")
    private Boolean enable;

    @ApiModelProperty(value = "链接地址")
    private String linkAddress;

    @ApiModelProperty(value = "链接地址二维码")
    private String linkAddressCode;

    @ApiModelProperty(value = "说明")
    private String description;

    @ApiModelProperty(value = "类型（0：单险种信息收集，1：综合信息收集，2：单险种投保，3：综合投保）")
    private Integer type;

    @ApiModelProperty(value = "状态（0：默认）")
    private Integer state;

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

    @ApiModelProperty(value = "标题模糊查询")
    private String queryTitle;

    @ApiModelProperty(value = "产品模块名称")
    private String productModuleName;

    @ApiModelProperty(value = "搜索类型  1:标题  2:表单名称 3: 表单类型")
    private Integer queryType;

    @ApiModelProperty(value = "模糊搜索字段")
    private String queryField;

    @ApiModelProperty(value = "app 模糊搜索字段")
    private String queryNameField;

    @ApiModelProperty(value = "公众号 模糊搜索字段")
    private String queryThePublicNameField;


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


