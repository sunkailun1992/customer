package com.gb.rpc.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

/**
 * @author: syRoin
 * @Date: 2022/3/14 16:41
 * @descript:
 */
@Getter
@AllArgsConstructor
public enum InsuranceSourceEnum {
    //来源0：未知，1：工保网，2：经纪人，3：试算，4：渠道,  5: 工保网app

    UNKNOWN(0, "未知"),
    GBW(1, "工保网"),
    AGENT(2, "经纪人"),
    TRIAL(3, "试算"),
    CHANNEL(4, "渠道"),
    GBW_APP(5, "工保网app"),
    NULL(99, "不存在"),
    ;

    private int code;
    private String desc;


    public static InsuranceSourceEnum getByCode(Integer code) {
        Optional<InsuranceSourceEnum> enumValue = Arrays.stream(InsuranceSourceEnum.values())
                .filter(x -> code.equals(x.getCode()))
                .findFirst();
        return enumValue.orElse(NULL);
    }
}
