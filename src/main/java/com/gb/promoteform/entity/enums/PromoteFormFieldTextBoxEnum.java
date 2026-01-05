package com.gb.promoteform.entity.enums;

import com.baomidou.mybatisplus.annotation.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * TODO 推广表单字段类型枚举
 * 代码生成器
 *
 * @author lijh
 * @className PromoteFormFieldTextBoxEnum
 * @time 2022-07-04 10:49:04
 */
@Getter
@AllArgsConstructor
public enum PromoteFormFieldTextBoxEnum implements IEnum<Integer> {
    // 手机框
    手机框(0, "手机框"),
    // 输入框
    输入框(1, "输入框"),
    // 地区框
    地区框(2, "地区框"),
    ;

    private Integer value;
    private String desc;


    /**
     * TODO 通过value获得枚举
     *
     * @param value
     * @return PromoteFormFieldTextBoxEnum
     * @author lijh
     * @methodName getPromoteFormFieldTextBoxEnum
     * @time 2022-07-04 10:49:04
     */
    public static PromoteFormFieldTextBoxEnum getPromoteFormFieldTextBoxEnum(Integer value) {
        for (PromoteFormFieldTextBoxEnum promoteFormFieldTextBoxEnum : PromoteFormFieldTextBoxEnum.values()) {
            if (promoteFormFieldTextBoxEnum.getValue().equals(value)) {
                return promoteFormFieldTextBoxEnum;
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
     * @time 2022-07-04 10:49:04
     */
    public static String getDesc(Integer value) {
        for (PromoteFormFieldTextBoxEnum promoteFormFieldTextBoxEnum : PromoteFormFieldTextBoxEnum.values()) {
            if (promoteFormFieldTextBoxEnum.getValue().equals(value)) {
                return promoteFormFieldTextBoxEnum.getDesc();
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
     * @time 2022-07-04 10:49:04
     */
    public static Integer getDesc(String desc) {
        for (PromoteFormFieldTextBoxEnum promoteFormFieldTextBoxEnum : PromoteFormFieldTextBoxEnum.values()) {
            if (promoteFormFieldTextBoxEnum.getDesc().equals(desc)) {
                return promoteFormFieldTextBoxEnum.getValue();
            }
        }
        return null;
    }
}