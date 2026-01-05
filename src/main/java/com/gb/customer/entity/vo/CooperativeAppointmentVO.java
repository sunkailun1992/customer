package com.gb.customer.entity.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.gb.customer.entity.enums.CooperativeAppointmentRequirementTypeEnum;
import com.gb.customer.entity.enums.CooperativeAppointmentIsDeleteEnum;
import com.gb.customer.entity.enums.CooperativeAppointmentTypeEnum;
import com.gb.customer.entity.enums.CooperativeAppointmentStateEnum;


/**
 * TODO 云服合作预约渲染
 * 代码生成器
 *
 * @author ljh
 * @className CooperativeAppointmentVO
 * @time 2023-01-09 02:56:41
 */
@Data
@ApiModel(value = "云服合作预约渲染")
public class CooperativeAppointmentVO implements Serializable {

    @ApiModelProperty(value = "序列")
    private String id;

    @ApiModelProperty(value = "编码")
    private String code;

    @ApiModelProperty(value = "合作需求（0：渠道合作，1：代理人合作，2：保险机构合作，3：生态资源合作）")
    private CooperativeAppointmentRequirementTypeEnum requirementType;

    @ApiModelProperty(value = "企业名称")
    private String enterpriseName;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "省id")
    private String provinceCode;

    @ApiModelProperty(value = "省name")
    private String provinceName;

    @ApiModelProperty(value = "市id")
    private String cityCode;

    @ApiModelProperty(value = "市name")
    private String cityName;

    @ApiModelProperty(value = "区id")
    private String areaCode;

    @ApiModelProperty(value = "区name")
    private String areaName;

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

    @ApiModelProperty(value = "删除状态（0：未删除，1：删除）")
    private Boolean isDelete;

    @ApiModelProperty(value = "来源（0：工保云服）")
    private CooperativeAppointmentTypeEnum type;

    @ApiModelProperty(value = "状态（0：待处理，1：处理中，2：已完成，3：已关闭）")
    private CooperativeAppointmentStateEnum state;

    @ApiModelProperty(value = "标签")
    private String label;

    @ApiModelProperty(value = "排序")
    private Integer sorting;

    @ApiModelProperty(value = "版本号")
    private Integer version;
}


