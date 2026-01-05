package com.gb.customer.entity.enums;

import com.baomidou.mybatisplus.annotation.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * TODO 客户来源类型枚举
 * 代码生成器
 *
 * @author lijh
 * @className CustomerSourceStateEnum
 * @time 2022-09-01 03:12:09
 */
@Getter
@AllArgsConstructor
public enum CustomerSourceStateEnum implements IEnum<Integer> {
    // 默认
    默认(0, "默认"),
    ;

    private Integer value;
    private String desc;


    /**
     * TODO 通过value获得枚举
     *
     * @param value
     * @return CustomerSourceStateEnum
     * @author lijh
     * @methodName getCustomerSourceStateEnum
     * @time 2022-09-01 03:12:09
     */
    public static CustomerSourceStateEnum getCustomerSourceStateEnum(Integer value) {
        for (CustomerSourceStateEnum customerSourceStateEnum : CustomerSourceStateEnum.values()) {
            if (customerSourceStateEnum.getValue().equals(value)) {
                return customerSourceStateEnum;
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
     * @time 2022-09-01 03:12:09
     */
    public static String getDesc(Integer value) {
        for (CustomerSourceStateEnum customerSourceStateEnum : CustomerSourceStateEnum.values()) {
            if (customerSourceStateEnum.getValue().equals(value)) {
                return customerSourceStateEnum.getDesc();
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
     * @time 2022-09-01 03:12:09
     */
    public static Integer getDesc(String desc) {
        for (CustomerSourceStateEnum customerSourceStateEnum : CustomerSourceStateEnum.values()) {
            if (customerSourceStateEnum.getDesc().equals(desc)) {
                return customerSourceStateEnum.getValue();
            }
        }
        return null;
    }
}