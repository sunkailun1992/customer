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
public enum CustomerManagerRuleConfigurationRuleStatusEnum implements IEnum<Integer> {
    /**
     * 规则配置类型
     */
    开启(0, "开启"),
    关闭(1, "关闭"),
    ;

    private Integer value;
    private String desc;


    /**
     * 通过value获得枚举
     *
     * @param value
     * @return
     */
    public static CustomerManagerRuleConfigurationRuleStatusEnum getCustomerManagerRuleConfigurationRuleStatusEnum(Integer value) {
        for (CustomerManagerRuleConfigurationRuleStatusEnum customerManagerRuleConfigurationRuleStatusEnum : CustomerManagerRuleConfigurationRuleStatusEnum.values()) {
            if (customerManagerRuleConfigurationRuleStatusEnum.getValue().equals(value)) {
                return customerManagerRuleConfigurationRuleStatusEnum;
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
        for (CustomerManagerRuleConfigurationRuleStatusEnum customerManagerRuleConfigurationRuleStatusEnum : CustomerManagerRuleConfigurationRuleStatusEnum.values()) {
            if (customerManagerRuleConfigurationRuleStatusEnum.getValue().equals(value)) {
                return customerManagerRuleConfigurationRuleStatusEnum.getDesc();
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
        for (CustomerManagerRuleConfigurationRuleStatusEnum customerManagerRuleConfigurationRuleStatusEnum : CustomerManagerRuleConfigurationRuleStatusEnum.values()) {
            if (customerManagerRuleConfigurationRuleStatusEnum.getDesc().equals(desc)) {
                return customerManagerRuleConfigurationRuleStatusEnum.getValue();
            }
        }
        return null;
    }
}