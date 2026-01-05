package com.gb.customer.entity.enums;

import com.baomidou.mybatisplus.annotation.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created with IntelliJ IDEA.
 * @author:     	ljh
 * @since:   	    2021-09-07 02:46:30
 * @description:	TODO  客户管理规则配置表类型枚举
 * @source:  	    代码生成器
 */
@Getter
@AllArgsConstructor
public enum CustomerManagerRuleConfigurationStateEnum implements IEnum<Integer> {
    /**
     * 规则配置状态
     */
    默认(0, "默认"),
    ;

    private Integer value;
    private String desc;


    /**
     * 通过value获得枚举
     *
     * @param value
     * @return
     */
    public static CustomerManagerRuleConfigurationStateEnum getCustomerManagerRuleConfigurationStateEnum(Integer value) {
        for (CustomerManagerRuleConfigurationStateEnum customerManagerRuleConfigurationStateEnum : CustomerManagerRuleConfigurationStateEnum.values()) {
            if (customerManagerRuleConfigurationStateEnum.getValue().equals(value)) {
                return customerManagerRuleConfigurationStateEnum;
            }
        }
        return null;
    }


    /**
     * 获得备注
     *
     * @param value
     * @return
     */
    public static String getDesc(Integer value) {
        for (CustomerManagerRuleConfigurationStateEnum customerManagerRuleConfigurationStateEnum : CustomerManagerRuleConfigurationStateEnum.values()) {
            if (customerManagerRuleConfigurationStateEnum.getValue().equals(value)) {
                return customerManagerRuleConfigurationStateEnum.getDesc();
            }
        }
        return null;
    }


    /**
     * 获得值
     *
     * @param desc
     * @return
     */
    public static Integer getDesc(String desc) {
        for (CustomerManagerRuleConfigurationStateEnum customerManagerRuleConfigurationStateEnum : CustomerManagerRuleConfigurationStateEnum.values()) {
            if (customerManagerRuleConfigurationStateEnum.getDesc().equals(desc)) {
                return customerManagerRuleConfigurationStateEnum.getValue();
            }
        }
        return null;
    }
}