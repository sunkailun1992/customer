package com.gb.mq.enums;

import com.gb.utils.exception.ParameterNullException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

/**
 * 变更类型(1：新增， 2： 解绑)
 */
@Getter
@AllArgsConstructor
@SuppressWarnings("all")
public enum ChangeTypeEnum {
    新增(1, "新增"),
    解绑(2, "解绑");

    private Integer code;
    private String desc;


    public static ChangeTypeEnum getChangeTypeEnum(Integer code) throws Exception {
        if (Objects.isNull(code)) {
            throw new ParameterNullException("code不能为空!");
        }
        Optional<ChangeTypeEnum> codeEnum = Arrays.stream(values()).filter(x -> Objects.equals(code, x.getCode())).findFirst();
        return codeEnum.orElseThrow(() -> new ParameterNullException("变更类型不存在!"));
    }
}
