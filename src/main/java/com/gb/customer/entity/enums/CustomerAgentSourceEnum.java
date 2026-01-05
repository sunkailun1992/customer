package com.gb.customer.entity.enums;

import com.baomidou.mybatisplus.annotation.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * TODO 客户经纪人类型枚举
 * 代码生成器
 *
 * @author lijh
 * @className CustomerAgentSourceEnum
 * @time 2022-08-31 09:35:12
 */
@Getter
@AllArgsConstructor
public enum CustomerAgentSourceEnum implements IEnum<Integer> {
    // 公海分配
    公海分配(0, "公海分配"),
    // 分销添加
    分销添加(1, "分销添加"),
    // 自营添加
    自营添加(2, "自营添加"),
    // 自营解绑
    自营解绑(3, "自营解绑"),
    // 自营转交
    自营转交(4, "自营转交"),
    // 链接参数
    链接参数(5, "链接参数"),
    ;

    private Integer value;
    private String desc;


    /**
     * TODO 通过value获得枚举
     *
     * @param value
     * @return CustomerAgentSourceEnum
     * @author lijh
     * @methodName getCustomerAgentSourceEnum
     * @time 2022-08-31 09:35:12
     */
    public static CustomerAgentSourceEnum getCustomerAgentSourceEnum(Integer value) {
        for (CustomerAgentSourceEnum customerAgentSourceEnum : CustomerAgentSourceEnum.values()) {
            if (customerAgentSourceEnum.getValue().equals(value)) {
                return customerAgentSourceEnum;
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
        for (CustomerAgentSourceEnum customerAgentSourceEnum : CustomerAgentSourceEnum.values()) {
            if (customerAgentSourceEnum.getValue().equals(value)) {
                return customerAgentSourceEnum.getDesc();
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
        for (CustomerAgentSourceEnum customerAgentSourceEnum : CustomerAgentSourceEnum.values()) {
            if (customerAgentSourceEnum.getDesc().equals(desc)) {
                return customerAgentSourceEnum.getValue();
            }
        }
        return null;
    }
}