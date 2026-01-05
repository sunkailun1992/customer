package com.gb.customer.entity.enums;

import com.baomidou.mybatisplus.annotation.IEnum;
import com.gb.utils.enumeration.ReturnCode;
import com.gb.utils.exception.BusinessException;
import com.gb.utils.exception.CustomerException;
import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * TODO 客户经纪人类型枚举
 * 代码生成器
 *
 * @author lijh
 * @className CustomerAgentTypeEnum
 * @time 2022-08-31 09:35:12
 */
@Getter
@AllArgsConstructor
public enum CustomerAgentTypeEnum implements IEnum<Integer> {
    // 自营
    自营(0, "自营"),
    // 分销
    分销(1, "分销"),
    无(2, "无"),
    ;

    private Integer value;
    private String desc;


    /**
     * TODO 通过value获得枚举
     *
     * @param value
     * @return CustomerAgentTypeEnum
     * @author lijh
     * @methodName getCustomerAgentTypeEnum
     * @time 2022-08-31 09:35:12
     */
    public static CustomerAgentTypeEnum getCustomerAgentTypeEnum(Integer value) {
        for (CustomerAgentTypeEnum customerAgentTypeEnum : CustomerAgentTypeEnum.values()) {
            if (customerAgentTypeEnum.getValue().equals(value)) {
                return customerAgentTypeEnum;
            }
        }
        throw new CustomerException(ReturnCode.用户端错误.getState(), "不存在客户经纪人类型!  value：" + value);
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
        for (CustomerAgentTypeEnum customerAgentTypeEnum : CustomerAgentTypeEnum.values()) {
            if (customerAgentTypeEnum.getValue().equals(value)) {
                return customerAgentTypeEnum.getDesc();
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
    public static Integer getValue(String desc) {
        for (CustomerAgentTypeEnum customerAgentTypeEnum : CustomerAgentTypeEnum.values()) {
            if (customerAgentTypeEnum.getDesc().equals(desc)) {
                return customerAgentTypeEnum.getValue();
            }
        }
        return null;
    }
}