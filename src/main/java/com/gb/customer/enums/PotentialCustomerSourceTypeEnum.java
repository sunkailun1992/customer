package com.gb.customer.enums;

import com.gb.utils.exception.BusinessException;
import com.gb.utils.exception.ParameterNullException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Optional;

/**
 * 客户线索来源类型（0：普通咨询，1：预约咨询，2：投保咨询，3：报价咨询，4：G端保单）
 *
 * @Auther lijh
 * @Date 2021/6/7
 */
@Getter
@AllArgsConstructor
@SuppressWarnings("all")
public enum PotentialCustomerSourceTypeEnum {
    SOURCE_TYPE_0(0, "普通咨询"),
    SOURCE_TYPE_1(1, "预约咨询"),
    SOURCE_TYPE_2(2, "投保咨询"),
    SOURCE_TYPE_3(3, "报价咨询"),
    SOURCE_TYPE_4(4, "G端保单");

    /**
     * 码值
     */
    private Integer code;

    /**
     * 含义
     */
    private String desc;

    /**
     * 根据码值获取
     *
     * @param code 自定义code
     * @return
     */
    public static PotentialCustomerSourceTypeEnum getTypeCode(String code) {
        if (StringUtils.isBlank(code)) {
            throw new BusinessException("码值code为空!");
        }
        Optional<PotentialCustomerSourceTypeEnum> codeEnum = Arrays.stream(values()).filter(x -> code.equals(x.getCode())).findFirst();
        return codeEnum.orElseThrow(() -> new ParameterNullException("线索来源类型code不存在!"));
    }
}
