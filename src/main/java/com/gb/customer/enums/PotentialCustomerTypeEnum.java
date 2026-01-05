package com.gb.customer.enums;

import com.gb.utils.exception.BusinessException;
import com.gb.utils.exception.ParameterNullException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

/**
 * 线索类型
 *
 * @Auther lijh
 * @Date 2021/6/7
 */
@Getter
@AllArgsConstructor
@SuppressWarnings("all")
public enum PotentialCustomerTypeEnum {
    CUSTOMER_TYPE_0(0, "客户咨询", "您有新的投保咨询服务单%s，需尽快处理。", 1000),
    CUSTOMER_TYPE_1(1, "经纪人咨询", "您有新的经纪人咨询服务单%s，需尽快处理。", 1001);

    /**
     * 码值
     */
    private Integer code;

    /**
     * 含义
     */
    private String desc;

    /**
     * 用户通知消息
     */
    private String msg;

    /**
     * 用户通知消息类型
     */
    private Integer msgType;

    public static PotentialCustomerTypeEnum getCustomerTypeEnum(Integer code) {
        if (null == code) {
            throw new BusinessException("线索类型code为空!");
        }
        Optional<PotentialCustomerTypeEnum> codeEnum = Arrays.stream(values()).filter(x -> code.equals(x.getCode())).findFirst();
        return codeEnum.orElseThrow(() -> new ParameterNullException("线索类型不存在!"));
    }

}
