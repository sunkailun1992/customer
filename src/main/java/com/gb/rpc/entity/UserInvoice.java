package com.gb.rpc.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 用户发票表
 * </p>
 *
 * @author sunx
 * @since 2021-05-25
 */
@Data
public class UserInvoice {

    @ApiModelProperty(value = "用户id")
    private String userId;

    @ApiModelProperty(value = "发票抬头")
    private String invoiceLookedUp;

    @ApiModelProperty(value = "发票税号")
    private String invoiceTaxCoding;

    @ApiModelProperty(value = "发票收件邮箱")
    private String invoiceEmail;

    @ApiModelProperty(hidden = true, value = "类型（0：普票，1：专票）")
    private Integer type = null;

}
