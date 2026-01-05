package com.gb.rpc.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 用户收货地址
 * </p>
 *
 * @author sunx
 * @since 2021-05-25
 */
@Data
public class UserShippingAddress {

    @ApiModelProperty(value = "用户id")
    private String userId;

    @ApiModelProperty(value = "省份代码")
    private String provinceCode;

    @ApiModelProperty(value = "市代码")
    private String cityCode;

    @ApiModelProperty(value = "区编码")
    private String areaCode;

    @ApiModelProperty(value = "收件地址")
    private String receiverAddress;

    @ApiModelProperty(value = "收件人")
    private String receiverName;

    @ApiModelProperty(value = "收件手机号")
    private String receiverMobile;

    @ApiModelProperty(value = "邮政编码")
    private String zipCode;

}
