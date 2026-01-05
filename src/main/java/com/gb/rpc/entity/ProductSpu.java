package com.gb.rpc.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 产品表
 * </p>
 *
 * @author 孙凯伦
 * @since 2021-02-06
 */
@Data
public class ProductSpu implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "序列")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty(value = "保险企业id")
    private String insuranceEnterpriseId;

    @ApiModelProperty(value = "险种分类id")
    private String dangerPlantedCategoryId;

    @ApiModelProperty(value = "险种id")
    private String dangerPlantedId;

    @ApiModelProperty(value = "方案类型id")
    private String planId;

    @ApiModelProperty(value = "方案类型值id")
    private String planValueId;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "图片地址")
    private String picture;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createDateTime;

    @ApiModelProperty(value = "类型（0：B端，1：G端）")
    private Integer type;

    @ApiModelProperty(value = "状态（0：待审核，1：通过，2：未通过）")
    private Integer state;

    @ApiModelProperty(value = "省")
    private String provinceCode;

    @ApiModelProperty(value = "市")
    private String cityCode;

    @ApiModelProperty(value = "区")
    private String areaCode;

    @ApiModelProperty(value = "险种分类名称")
    private String dangerPlantedCategoryName;

    @ApiModelProperty(value = "险种名称")
    private String dangerPlantedName;

    @ApiModelProperty(value = "险种简称")
    private String dangerPlantedAbbreviation;

    @ApiModelProperty(value = "险种描述")
    private String dangerPlantedDescription;

    @ApiModelProperty(value = "保险企业code")
    private String insuranceEnterpriseCode;

    @ApiModelProperty(value = "保险企业名称")
    private String insuranceEnterpriseCompany;

    @ApiModelProperty(value = "保险企业简称")
    private String insuranceEnterpriseName;

    @ApiModelProperty(value = "保险企业图片地址")
    private String insuranceEnterprisePortrait;

}
