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
public enum PotentialCustomerStateEnum {
    取消(-1, "取消"),
    待处理(0, "待处理"),
    处理中(1, "处理中"),
    已完成(2, "已完成"),
    关闭(3, "关闭");

    /**
     * 码值
     */
    private Integer code;

    /**
     * 含义
     */
    private String desc;

    public static PotentialCustomerStateEnum getCustomerTypeEnum(Integer code) {
        if (null == code) {
            throw new BusinessException("线索类型code为空!");
        }
        Optional<PotentialCustomerStateEnum> codeEnum = Arrays.stream(values()).filter(x -> code.equals(x.getCode())).findFirst();
        return codeEnum.orElseThrow(() -> new ParameterNullException("线索类型不存在!"));
    }


}
