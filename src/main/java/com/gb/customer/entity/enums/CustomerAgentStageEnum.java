package com.gb.customer.entity.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * TODO 客户经纪人阶段枚举
 * 代码生成器
 *
 * @author lijh
 * @className CustomerAgentIntentionEnum
 * @time 2022-08-31 09:35:12
 */
@Getter
@AllArgsConstructor
public enum CustomerAgentStageEnum implements IEnum<Integer> {

    /**
     * 阶段枚举
     */
    电话沟通(0, "电话沟通"),
    需求确认(1, "需求确认"),
    方案报价(2, "方案/报价"),
    成交(3, "成交"),
    暂时搁置(4, "暂时搁置"),
    谈判合同(5, "谈判/合同");

    private Integer value;

    @EnumValue
    @JsonValue
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
    public static CustomerAgentStageEnum getCustomerAgentStageEnum(Integer value) {
        for (CustomerAgentStageEnum customerAgentIntentionEnum : CustomerAgentStageEnum.values()) {
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
        for (CustomerAgentStageEnum customerAgentIntentionEnum : CustomerAgentStageEnum.values()) {
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
        for (CustomerAgentStageEnum customerAgentIntentionEnum : CustomerAgentStageEnum.values()) {
            if (customerAgentIntentionEnum.getDesc().equals(desc)) {
                return customerAgentIntentionEnum.getValue();
            }
        }
        return null;
    }
}