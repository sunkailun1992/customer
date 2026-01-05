package com.gb.customer.entity.enums;

import com.baomidou.mybatisplus.annotation.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * TODO 客户经纪人操作记录来源枚举
 * 代码生成器
 *
 * @author lijh
 * @className CustomerAgentOperateRecordSourceEnum
 * @time 2022-09-20 11:02:25
 */
@Getter
@AllArgsConstructor
public enum CustomerAgentOperateRecordSourceEnum implements IEnum<Integer> {
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

    生成客户(6, "生成客户"),
    ;

    private Integer value;
    private String desc;


    /**
     * TODO 通过value获得枚举
     *
     * @param value
     * @return CustomerAgentOperateRecordSourceEnum
     * @author lijh
     * @methodName getCustomerAgentOperateRecordSourceEnum
     * @time 2022-09-20 11:02:25
     */
    public static CustomerAgentOperateRecordSourceEnum getCustomerAgentOperateRecordSourceEnum(Integer value) {
        for (CustomerAgentOperateRecordSourceEnum customerAgentOperateRecordSourceEnum : CustomerAgentOperateRecordSourceEnum.values()) {
            if (customerAgentOperateRecordSourceEnum.getValue().equals(value)) {
                return customerAgentOperateRecordSourceEnum;
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
        for (CustomerAgentOperateRecordSourceEnum customerAgentOperateRecordSourceEnum : CustomerAgentOperateRecordSourceEnum.values()) {
            if (customerAgentOperateRecordSourceEnum.getValue().equals(value)) {
                return customerAgentOperateRecordSourceEnum.getDesc();
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
        for (CustomerAgentOperateRecordSourceEnum customerAgentOperateRecordSourceEnum : CustomerAgentOperateRecordSourceEnum.values()) {
            if (customerAgentOperateRecordSourceEnum.getDesc().equals(desc)) {
                return customerAgentOperateRecordSourceEnum.getValue();
            }
        }
        return null;
    }
}