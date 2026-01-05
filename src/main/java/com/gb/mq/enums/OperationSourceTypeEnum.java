package com.gb.mq.enums;

import com.gb.utils.exception.ParameterNullException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

/**
 * 操作来源类型
 */
@Getter
@AllArgsConstructor
@SuppressWarnings("all")
public enum OperationSourceTypeEnum {
    移入公海(1, "移入公海"),
    分配(2, "分配"),
    批量分配(3, "批量分配"),
    转交经纪人(4, "转交经纪人"),
    注销(5, "注销"),
    离职(6, "离职");

    private Integer code;
    private String desc;


    public static OperationSourceTypeEnum getChangeTypeEnum(Integer code) throws Exception {
        if (Objects.isNull(code)) {
            throw new ParameterNullException("code不能为空!");
        }
        Optional<OperationSourceTypeEnum> codeEnum = Arrays.stream(values()).filter(x -> Objects.equals(code, x.getCode())).findFirst();
        return codeEnum.orElseThrow(() -> new ParameterNullException("变更类型不存在!"));
    }
}
