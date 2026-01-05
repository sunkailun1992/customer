package com.gb.customer.entity.enums;

import com.baomidou.mybatisplus.annotation.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * TODO 客户经纪人操作记录类型枚举
 * 代码生成器
 *
 * @author lijh
 * @className CustomerAgentOperateRecordStateEnum
 * @time 2022-09-20 11:02:25
 */
@Getter
@AllArgsConstructor
public enum CustomerAgentOperateRecordStateEnum implements IEnum<Integer> {
    // 默认
    默认(0, "默认"),
    ;

    private Integer value;
    private String desc;


    /**
     * TODO 通过value获得枚举
     *
     * @param value
     * @return CustomerAgentOperateRecordStateEnum
     * @author lijh
     * @methodName getCustomerAgentOperateRecordStateEnum
     * @time 2022-09-20 11:02:25
     */
    public static CustomerAgentOperateRecordStateEnum getCustomerAgentOperateRecordStateEnum(Integer value) {
        for (CustomerAgentOperateRecordStateEnum customerAgentOperateRecordStateEnum : CustomerAgentOperateRecordStateEnum.values()) {
            if (customerAgentOperateRecordStateEnum.getValue().equals(value)) {
                return customerAgentOperateRecordStateEnum;
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
        for (CustomerAgentOperateRecordStateEnum customerAgentOperateRecordStateEnum : CustomerAgentOperateRecordStateEnum.values()) {
            if (customerAgentOperateRecordStateEnum.getValue().equals(value)) {
                return customerAgentOperateRecordStateEnum.getDesc();
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
        for (CustomerAgentOperateRecordStateEnum customerAgentOperateRecordStateEnum : CustomerAgentOperateRecordStateEnum.values()) {
            if (customerAgentOperateRecordStateEnum.getDesc().equals(desc)) {
                return customerAgentOperateRecordStateEnum.getValue();
            }
        }
        return null;
    }
}