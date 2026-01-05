package com.gb.customer.entity.enums;

import com.baomidou.mybatisplus.annotation.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * TODO 客户经纪人操作记录类型枚举
 * 代码生成器
 *
 * @author lijh
 * @className CustomerAgentOperateRecordOperationEnum
 * @time 2022-09-20 11:02:25
 */
@Getter
@AllArgsConstructor
public enum CustomerAgentOperateRecordOperationEnum implements IEnum<Integer> {
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
     * @return CustomerAgentOperateRecordOperationEnum
     * @author lijh
     * @methodName getCustomerAgentOperateRecordOperationEnum
     * @time 2022-09-20 11:02:25
     */
    public static CustomerAgentOperateRecordOperationEnum getCustomerAgentOperateRecordOperationEnum(Integer value) {
        for (CustomerAgentOperateRecordOperationEnum customerAgentOperateRecordOperationEnum : CustomerAgentOperateRecordOperationEnum.values()) {
            if (customerAgentOperateRecordOperationEnum.getValue().equals(value)) {
                return customerAgentOperateRecordOperationEnum;
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
        for (CustomerAgentOperateRecordOperationEnum customerAgentOperateRecordOperationEnum : CustomerAgentOperateRecordOperationEnum.values()) {
            if (customerAgentOperateRecordOperationEnum.getValue().equals(value)) {
                return customerAgentOperateRecordOperationEnum.getDesc();
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
        for (CustomerAgentOperateRecordOperationEnum customerAgentOperateRecordOperationEnum : CustomerAgentOperateRecordOperationEnum.values()) {
            if (customerAgentOperateRecordOperationEnum.getDesc().equals(desc)) {
                return customerAgentOperateRecordOperationEnum.getValue();
            }
        }
        return null;
    }
}