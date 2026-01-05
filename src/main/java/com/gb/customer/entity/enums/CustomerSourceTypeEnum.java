package com.gb.customer.entity.enums;

import com.baomidou.mybatisplus.annotation.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * TODO 客户来源类型枚举
 * 代码生成器
 *
 * @author lijh
 * @className CustomerSourceTypeEnum
 * @time 2022-09-01 03:12:09
 */
@Getter
@AllArgsConstructor
public enum CustomerSourceTypeEnum implements IEnum<Integer> {
    // 默认
    默认(0, "默认"),
    ;

    private Integer value;
    private String desc;


    /**
     * TODO 通过value获得枚举
     *
     * @param value
     * @return CustomerSourceTypeEnum
     * @author lijh
     * @methodName getCustomerSourceTypeEnum
     * @time 2022-09-01 03:12:09
     */
    public static CustomerSourceTypeEnum getCustomerSourceTypeEnum(Integer value) {
        for (CustomerSourceTypeEnum customerSourceTypeEnum : CustomerSourceTypeEnum.values()) {
            if (customerSourceTypeEnum.getValue().equals(value)) {
                return customerSourceTypeEnum;
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
        for (CustomerSourceTypeEnum customerSourceTypeEnum : CustomerSourceTypeEnum.values()) {
            if (customerSourceTypeEnum.getValue().equals(value)) {
                return customerSourceTypeEnum.getDesc();
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
        for (CustomerSourceTypeEnum customerSourceTypeEnum : CustomerSourceTypeEnum.values()) {
            if (customerSourceTypeEnum.getDesc().equals(desc)) {
                return customerSourceTypeEnum.getValue();
            }
        }
        return null;
    }
}