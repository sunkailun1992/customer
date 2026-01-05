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
public enum CustomerManagerRuleConfigurationTypeEnum implements IEnum<Integer> {
    /**
     * 规则配置类型
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
    public static CustomerManagerRuleConfigurationTypeEnum getCustomerManagerRuleConfigurationTypeEnum(Integer value) {
        for (CustomerManagerRuleConfigurationTypeEnum customerManagerRuleConfigurationTypeEnum : CustomerManagerRuleConfigurationTypeEnum.values()) {
            if (customerManagerRuleConfigurationTypeEnum.getValue().equals(value)) {
                return customerManagerRuleConfigurationTypeEnum;
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
        for (CustomerManagerRuleConfigurationTypeEnum customerManagerRuleConfigurationTypeEnum : CustomerManagerRuleConfigurationTypeEnum.values()) {
            if (customerManagerRuleConfigurationTypeEnum.getValue().equals(value)) {
                return customerManagerRuleConfigurationTypeEnum.getDesc();
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
        for (CustomerManagerRuleConfigurationTypeEnum customerManagerRuleConfigurationTypeEnum : CustomerManagerRuleConfigurationTypeEnum.values()) {
            if (customerManagerRuleConfigurationTypeEnum.getDesc().equals(desc)) {
                return customerManagerRuleConfigurationTypeEnum.getValue();
            }
        }
        return null;
    }
}