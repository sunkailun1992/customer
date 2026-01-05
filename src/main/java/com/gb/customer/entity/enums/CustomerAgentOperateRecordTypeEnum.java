package com.gb.customer.entity.enums;

import com.baomidou.mybatisplus.annotation.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * TODO 客户经纪人操作记录类型枚举
 * 代码生成器
 *
 * @author lijh
 * @className CustomerAgentOperateRecordTypeEnum
 * @time 2022-09-20 11:02:25
 */
@Getter
@AllArgsConstructor
public enum CustomerAgentOperateRecordTypeEnum implements IEnum<Integer> {
    // 默认
    默认(0, "默认"),
    ;

    private Integer value;
    private String desc;


    /**
     * TODO 通过value获得枚举
     *
     * @param value
     * @return CustomerAgentOperateRecordTypeEnum
     * @author lijh
     * @methodName getCustomerAgentOperateRecordTypeEnum
     * @time 2022-09-20 11:02:25
     */
    public static CustomerAgentOperateRecordTypeEnum getCustomerAgentOperateRecordTypeEnum(Integer value) {
        for (CustomerAgentOperateRecordTypeEnum customerAgentOperateRecordTypeEnum : CustomerAgentOperateRecordTypeEnum.values()) {
            if (customerAgentOperateRecordTypeEnum.getValue().equals(value)) {
                return customerAgentOperateRecordTypeEnum;
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
     * @time 2022-09-20 11:02:25
     */
    public static String getDesc(Integer value) {
        for (CustomerAgentOperateRecordTypeEnum customerAgentOperateRecordTypeEnum : CustomerAgentOperateRecordTypeEnum.values()) {
            if (customerAgentOperateRecordTypeEnum.getValue().equals(value)) {
                return customerAgentOperateRecordTypeEnum.getDesc();
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
     * @time 2022-09-20 11:02:25
     */
    public static Integer getDesc(String desc) {
        for (CustomerAgentOperateRecordTypeEnum customerAgentOperateRecordTypeEnum : CustomerAgentOperateRecordTypeEnum.values()) {
            if (customerAgentOperateRecordTypeEnum.getDesc().equals(desc)) {
                return customerAgentOperateRecordTypeEnum.getValue();
            }
        }
        return null;
    }
}