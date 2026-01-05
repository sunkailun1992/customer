package com.gb.customer.entity.enums;

import com.baomidou.mybatisplus.annotation.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * TODO 客户经纪人类型枚举
 * 代码生成器
 *
 * @author lijh
 * @className CustomerAgentOperationEnum
 * @time 2022-08-31 09:35:12
 */
@Getter
@AllArgsConstructor
public enum CustomerAgentOperationEnum implements IEnum<Integer> {
    // 关联
    关联(0, "关联"),
    // 分配
    分配(1, "分配"),
    // 解绑
    解绑(2, "解绑"),
    // 转移
    转移(3, "转移"),
    ;

    private Integer value;
    private String desc;


    /**
     * TODO 通过value获得枚举
     *
     * @param value
     * @return CustomerAgentOperationEnum
     * @author lijh
     * @methodName getCustomerAgentOperationEnum
     * @time 2022-08-31 09:35:12
     */
    public static CustomerAgentOperationEnum getCustomerAgentOperationEnum(Integer value) {
        for (CustomerAgentOperationEnum customerAgentOperationEnum : CustomerAgentOperationEnum.values()) {
            if (customerAgentOperationEnum.getValue().equals(value)) {
                return customerAgentOperationEnum;
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
        for (CustomerAgentOperationEnum customerAgentOperationEnum : CustomerAgentOperationEnum.values()) {
            if (customerAgentOperationEnum.getValue().equals(value)) {
                return customerAgentOperationEnum.getDesc();
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
        for (CustomerAgentOperationEnum customerAgentOperationEnum : CustomerAgentOperationEnum.values()) {
            if (customerAgentOperationEnum.getDesc().equals(desc)) {
                return customerAgentOperationEnum.getValue();
            }
        }
        return null;
    }
}