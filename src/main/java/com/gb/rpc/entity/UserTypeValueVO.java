package com.gb.rpc.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * <p>
 * 用户类型值
 * </p>
 *
 * @author lijh
 * @since 2022-01-07
 */
@Data
public class UserTypeValueVO {

    @ApiModelProperty(value = "序列")
    private String id;

    @ApiModelProperty(value = "用户类型id")
    private String userTypeId;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "编码")
    private String code;

    @ApiModelProperty(value = "说明")
    private String description;

    @ApiModelProperty(value = "标签")
    private String label;

    @ApiModelProperty(value = "排序")
    private Integer sorting;

    @ApiModelProperty(value = "版本号")
    private Integer version;

    @ApiModelProperty(value = "用户类型名称")
    private String userTypeName;

    @ApiModelProperty(value = "用户类型码值")
    private String userTypeCode;
}


