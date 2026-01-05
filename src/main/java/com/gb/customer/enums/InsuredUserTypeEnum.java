package com.gb.customer.enums;

import com.gb.utils.exception.BusinessException;
import com.gb.utils.exception.ParameterNullException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

/**
 * 投保用户类型
 *
 * @Auther lijh
 * @Date 2021/11/7
 */
@Getter
@AllArgsConstructor
@SuppressWarnings("all")
public enum InsuredUserTypeEnum {
    INSURED_USER_TYPE_0(0, "用户端"),
    INSURED_USER_TYPE_1(1, "经纪人"),
    INSURED_USER_TYPE_2(2, "渠道普通用户"),
    INSURED_USER_TYPE_3(3, "业务管家"),
    INSURED_USER_TYPE_4(4, "特别业务管家"),
    INSURED_USER_TYPE_5(5, "渠道经纪人");

    /**
     * 码值
     */
    private Integer code;

    /**
     * 含义
     */
    private String desc;

    public static InsuredUserTypeEnum getInsuredUserType(Integer code) {
        if (null == code) {
            throw new BusinessException("投保用户类型code为空!");
        }
        Optional<InsuredUserTypeEnum> codeEnum = Arrays.stream(values()).filter(x -> code.equals(x.getCode())).findFirst();
        return codeEnum.orElseThrow(() -> new ParameterNullException("投保用户类型不存在!"));
    }

    /**
     * 获取用户类型
     *
     * @param code 0 用户  1 经纪人
     * @return
     */
    public static Integer getUserType(Integer code) {
        if (null == code) {
            throw new BusinessException("投保用户类型code为空!");
        }
        Integer type = InsuredUserTypeEnum.INSURED_USER_TYPE_0.code;
        switch (InsuredUserTypeEnum.getInsuredUserType(code)) {
            case INSURED_USER_TYPE_0:
            case INSURED_USER_TYPE_2:
                type = InsuredUserTypeEnum.INSURED_USER_TYPE_0.getCode();
                break;
            case INSURED_USER_TYPE_1:
            case INSURED_USER_TYPE_3:
            case INSURED_USER_TYPE_4:
            case INSURED_USER_TYPE_5:
                type = InsuredUserTypeEnum.INSURED_USER_TYPE_1.getCode();
        }
        return type;
    }

}
