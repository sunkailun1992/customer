package com.gb.promoteform.entity.bo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gb.promoteform.entity.*;
import com.gb.promoteform.entity.enums.PromoteFormDisplayTerminalEnum;
import com.gb.promoteform.entity.enums.PromoteFormStateEnum;
import com.gb.promoteform.entity.enums.PromoteFormTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;


/**
 * TODO 推广表单传输
 * 代码生成器
 *
 * @author lijh
 * @className PromoteFormBO
 * @time 2022-07-04 10:47:25
 */
@Data
@ApiModel(value = "推广表单传输")
public class PromoteFormBO implements Serializable {

    @ApiModelProperty(value = "序列")
    @NotBlank(groups = {PromoteFormBO.Update.class, PromoteFormBO.Remove.class}, message = "id不能为空")
    private String id;

    @NotBlank(groups = PromoteFormBO.Save.class, message = "名称不能为空")
    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "编码")
    private String code;

    @ApiModelProperty(value = "经纪人")
    private Boolean agent;

    @ApiModelProperty(value = "运营")
    private Boolean operating;

    @ApiModelProperty(value = "展现终端（0：h5，1：pc）")
    private PromoteFormDisplayTerminalEnum displayTerminal;

    @ApiModelProperty(value = "微信")
    private Boolean wechat;

    @NotBlank(groups = PromoteFormBO.Save.class, message = "标题不能为空")
    @ApiModelProperty(value = "标题")
    private String title;

    @NotBlank(groups = PromoteFormBO.Save.class, message = "页面色值不能为空")
    @ApiModelProperty(value = "页面色值")
    private String colorValue;

    @ApiModelProperty(value = "banner图")
    private String banner;

    @NotBlank(groups = PromoteFormBO.Save.class, message = "内容介绍不能为空")
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

    @NotBlank
    @ApiModelProperty(value = "类型（0：单险种信息收集，1：综合信息收集，2：单险种投保，3：综合投保）")
    private PromoteFormTypeEnum type;

    @ApiModelProperty(value = "状态（0：默认）")
    private PromoteFormStateEnum state;

    @ApiModelProperty(value = "标签")
    private String label;

    @ApiModelProperty(value = "排序")
    private Integer sorting;

    @ApiModelProperty(value = "版本号")
    private Integer version;

    @ApiModelProperty(value = "产品模块名称")
    private String productModuleName;

    @ApiModelProperty(value = "微信对象")
    private PromoteFormWechat promoteFormWechat;

    @ApiModelProperty(value = "弹窗对象")
    private PromoteFormWindow promoteFormWindow;

    @ApiModelProperty(value = "产品集合")
    private List<PromoteFormProduct> promoteFormProductList;

    @ApiModelProperty(value = "字段集合")
    private List<PromoteFormField> promoteFormFieldList;

    @ApiModelProperty(value = "按钮集合")
    private List<PromoteFormButton> promoteFormButtonList;
    @ApiModelProperty(value = "固底浮窗集合")
    private List<PromoteFormButton> promoteFormFixedBottomList;
    @ApiModelProperty(value = "底部浮窗集合")
    private List<PromoteFormButton> promoteFormBottomList;

    @ApiModelProperty(value = "底部浮框,险种选择样式")
    private PromoteFormFloatingWindow promoteFormFloatingWindow;


    /**
     * 新增
     */
    public interface Save {
    }

    /**
     * 修改
     */
    public interface Update {
    }

    /**
     * 删除
     */
    public interface Remove {
    }
}


