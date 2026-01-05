package com.gb.promoteform.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import com.gb.bean.EntityBase;
import com.gb.promoteform.entity.enums.PromoteFormDisplayTerminalEnum;
import com.gb.promoteform.entity.enums.PromoteFormStateEnum;
import com.gb.promoteform.entity.enums.PromoteFormTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


/**
 * TODO 推广表单对象
 * 代码生成器
 *
 * @author lijh
 * @className PromoteForm
 * @time 2022-07-04 10:47:25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("`promote_form`")
@ApiModel(value = "PromoteForm对象", description = "推广表单")
public class PromoteForm extends EntityBase {

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "编码")
    private String code;

    @ApiModelProperty(value = "经纪人")
    private Boolean agent;

    @ApiModelProperty(value = "运营")
    private Boolean operating;

    /**
     * {@link PromoteFormDisplayTerminalEnum}
     */
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

    @ApiModelProperty(value = "类型（0：单险种信息收集，1：综合信息收集，2：单险种投保，3：综合投保）")
    private PromoteFormTypeEnum type;

    @ApiModelProperty(value = "状态（0：默认）")
    private PromoteFormStateEnum state;

    @ApiModelProperty(value = "标签")
    private String label;

    @ApiModelProperty(value = "产品模块名称")
    private String productModuleName;
}
