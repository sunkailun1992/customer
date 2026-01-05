package com.gb.rpc.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 用户基本信息
 * </p>
 *
 * @author lijh
 * @since 2021-11-29
 */
@Data
@Accessors(chain = true)
public class UserLabelInfoVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户标签值序列")
    private String userTypeValueId;

    @ApiModelProperty(value = "用户标签值码值")
    private String userTypeValueCode;

    @ApiModelProperty(value = "用户标签值名称")
    private String userTypeValueName;

    @ApiModelProperty(value = "用户基本信息列表")
    private List<UserBasicInfoVO> userBasicInfoVOList;
}
