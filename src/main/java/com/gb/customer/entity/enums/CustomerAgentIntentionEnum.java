package com.gb.customer.entity.enums;

import com.baomidou.mybatisplus.annotation.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * TODO 客户经纪人类型枚举
 * 代码生成器
 *
 * @author lijh
 * @className CustomerAgentIntentionEnum
 * @time 2022-08-31 09:35:12
 */
@Getter
@AllArgsConstructor
public enum CustomerAgentIntentionEnum implements IEnum<Integer> {
    // 高
    高(0, "高"),
    // 中
    中(1, "中"),
    // 低
    低(2, "低");

    private Integer value;
    private String desc;


    /**
     * TODO 通过value获得枚举
     *
     * @param value
     * @return CustomerAgentIntentionEnum
     * @author lijh
     * @methodName getCustomerAgentIntentionEnum
     * @time 2022-08-31 09:35:12
     */
    public static CustomerAgentIntentionEnum getCustomerAgentIntentionEnum(Integer value) {
        for (CustomerAgentIntentionEnum customerAgentIntentionEnum : CustomerAgentIntentionEnum.values()) {
            if (customerAgentIntentionEnum.getValue().equals(value)) {
                return customerAgentIntentionEnum;
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
        for (CustomerAgentIntentionEnum customerAgentIntentionEnum : CustomerAgentIntentionEnum.values()) {
            if (customerAgentIntentionEnum.getValue().equals(value)) {
                return customerAgentIntentionEnum.getDesc();
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
        for (CustomerAgentIntentionEnum customerAgentIntentionEnum : CustomerAgentIntentionEnum.values()) {
            if (customerAgentIntentionEnum.getDesc().equals(desc)) {
                return customerAgentIntentionEnum.getValue();
            }
        }
        return null;
    }
}