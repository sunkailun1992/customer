package com.gb.rpc.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * <p>
 * 用户扩展信息
 * </p>
 *
 * @author lijh
 * @since 2021-12-20
 */
@Data
public class UserExtendsVO {

    @ApiModelProperty(value = "序列")
    private String id;

    @ApiModelProperty(value = "用户id")
    private String userId;

    @ApiModelProperty(value = "分销上层用户id")
    private String distributionUserId;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "别名")
    private String alias;

    @ApiModelProperty(value = "员工编码")
    private String coding;

    @ApiModelProperty(value = "生日")
    private String birthday;

    @ApiModelProperty(value = "性别（0：男，1：女）")
    private Integer sex;

    @ApiModelProperty(value = "身份证号码")
    private String idCard;

    @ApiModelProperty(value = "籍贯")
    private String nativePlace;

    @ApiModelProperty(value = "省编码")
    private String provinceCode;

    @ApiModelProperty(value = "省名称")
    private String provinceName;

    @ApiModelProperty(value = "市编码")
    private String cityCode;

    @ApiModelProperty(value = "市名称")
    private String cityName;

    @ApiModelProperty(value = "区编码")
    private String areaCode;

    @ApiModelProperty(value = "区名称")
    private String areaName;

    @ApiModelProperty(value = "住址")
    private String address;

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "电话")
    private String phone;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "QQ")
    private String qq;

    @ApiModelProperty(value = "微信")
    private String wechat;

    @ApiModelProperty(value = "紧急联系人")
    private String emergencyContactName;

    @ApiModelProperty(value = "紧急联系人手机")
    private String emergencyContactMobile;

    @ApiModelProperty(value = "入职时间")
    private String onboardingDateTime;

    @ApiModelProperty(value = "银行卡号")
    private String bankCardNumber;

    @ApiModelProperty(value = "所属银行")
    private String bank;

    @ApiModelProperty(value = "银行卡开户地址")
    private String openAccountAddress;

    @ApiModelProperty(value = "银行卡开户名")
    private String openAccountName;

    @ApiModelProperty(value = "说明")
    private String description;

    @ApiModelProperty(value = "创建时间")
    private String createDateTime;

    @ApiModelProperty(value = "创建人")
    private String createName;

    @ApiModelProperty(value = "修改时间")
    private String modifyDateTime;

    @ApiModelProperty(value = "修改人")
    private String modifyName;

    @ApiModelProperty(value = "删除状态")
    private Boolean isDelete;

    @ApiModelProperty(value = "标签")
    private String label;

    @ApiModelProperty(value = "排序")
    private Integer sorting;

    @ApiModelProperty(value = "版本号")
    private Integer version;
}