package com.gb.customer.entity.enums;

import com.baomidou.mybatisplus.annotation.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * TODO 客户经纪人类型枚举
 * 代码生成器
 *
 * @author lijh
 * @className CustomerAgentStateEnum
 * @time 2022-08-31 09:35:12
 */
@Getter
@AllArgsConstructor
public enum CustomerAgentStateEnum implements IEnum<Integer> {
    // 主关联
    主关联(0, "主关联"),
    // 副关联
    副关联(1, "副关联"),
    无(2, "无"),
    ;

    private Integer value;
    private String desc;


    /**
     * TODO 通过value获得枚举
     *
     * @param value
     * @return CustomerAgentStateEnum
     * @author lijh
     * @methodName getCustomerAgentStateEnum
     * @time 2022-08-31 09:35:12
     */
    public static CustomerAgentStateEnum getCustomerAgentStateEnum(Integer value) {
        for (CustomerAgentStateEnum customerAgentStateEnum : CustomerAgentStateEnum.values()) {
            if (customerAgentStateEnum.getValue().equals(value)) {
                return customerAgentStateEnum;
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
        for (CustomerAgentStateEnum customerAgentStateEnum : CustomerAgentStateEnum.values()) {
            if (customerAgentStateEnum.getValue().equals(value)) {
                return customerAgentStateEnum.getDesc();
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
        for (CustomerAgentStateEnum customerAgentStateEnum : CustomerAgentStateEnum.values()) {
            if (customerAgentStateEnum.getDesc().equals(desc)) {
                return customerAgentStateEnum.getValue();
            }
        }
        return null;
    }
}