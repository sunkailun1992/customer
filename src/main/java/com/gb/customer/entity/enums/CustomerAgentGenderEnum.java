package com.gb.customer.entity.enums;

import com.baomidou.mybatisplus.annotation.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * TODO 客户经纪人类型枚举
 * 代码生成器
 *
 * @author lijh
 * @className CustomerAgentGenderEnum
 * @time 2022-08-31 09:35:12
 */
@Getter
@AllArgsConstructor
public enum CustomerAgentGenderEnum implements IEnum<Integer> {
    // 男
    男(0, "男"),
    // 女
    女(1, "女"),
    ;

    private Integer value;
    private String desc;


    /**
     * TODO 通过value获得枚举
     *
     * @param value
     * @return CustomerAgentGenderEnum
     * @author lijh
     * @methodName getCustomerAgentGenderEnum
     * @time 2022-08-31 09:35:12
     */
    public static CustomerAgentGenderEnum getCustomerAgentGenderEnum(Integer value) {
        for (CustomerAgentGenderEnum customerAgentGenderEnum : CustomerAgentGenderEnum.values()) {
            if (customerAgentGenderEnum.getValue().equals(value)) {
                return customerAgentGenderEnum;
            }
        }
        return null;
    }


    /**
     * TODO 获得备注
     *
     * @param value
     * @return String
     * @author lijh
     * @methodName getDesc
     * @time 2022-08-31 09:35:12
     */
    public static String getDesc(Integer value) {
        for (CustomerAgentGenderEnum customerAgentGenderEnum : CustomerAgentGenderEnum.values()) {
            if (customerAgentGenderEnum.getValue().equals(value)) {
                return customerAgentGenderEnum.getDesc();
            }
        }
        return null;
    }


    /**
     * TODO 获得值
     *
     * @param desc
     * @return Integer
     * @author lijh
     * @methodName getDesc
     * @time 2022-08-31 09:35:12
     */
    public static Integer getDesc(String desc) {
        for (CustomerAgentGenderEnum customerAgentGenderEnum : CustomerAgentGenderEnum.values()) {
            if (customerAgentGenderEnum.getDesc().equals(desc)) {
                return customerAgentGenderEnum.getValue();
            }
        }
        return null;
    }
}