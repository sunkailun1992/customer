package com.gb.customer.enums;

import com.baomidou.mybatisplus.annotation.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * TODO 客户状态枚举
 * 代码生成器
 *
 * @author lijh
 * @className CustomerAgentStateEnum
 * @time 2022-08-31 09:35:12
 */
@Getter
@AllArgsConstructor
public enum CustomerStateEnum implements IEnum<Integer> {
    // 未注册
    未注册(0, "未注册"),
    // 已注册
    已注册(1, "已注册"),
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
    public static CustomerStateEnum getCustomerAgentStateEnum(Integer value) {
        for (CustomerStateEnum customerAgentStateEnum : CustomerStateEnum.values()) {
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
        for (CustomerStateEnum customerAgentStateEnum : CustomerStateEnum.values()) {
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
        for (CustomerStateEnum customerAgentStateEnum : CustomerStateEnum.values()) {
            if (customerAgentStateEnum.getDesc().equals(desc)) {
                return customerAgentStateEnum.getValue();
            }
        }
        return null;
    }
}