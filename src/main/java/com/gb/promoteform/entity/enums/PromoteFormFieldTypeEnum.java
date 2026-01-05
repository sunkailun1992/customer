package com.gb.promoteform.entity.enums;

import com.baomidou.mybatisplus.annotation.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * TODO 推广表单字段类型枚举
 * 代码生成器
 *
 * @author lijh
 * @className PromoteFormFieldTypeEnum
 * @time 2022-07-04 10:49:04
 */
@Getter
@AllArgsConstructor
public enum PromoteFormFieldTypeEnum implements IEnum<Integer> {
    // 企业名称
    企业名称(0, "企业名称"),
    // 项目地区
    项目地区(1, "项目地区"),
    // 手机号码
    手机号码(2, "手机号码"),
    ;

    private Integer value;
    private String desc;


    /**
     * TODO 通过value获得枚举
     *
     * @param value
     * @return PromoteFormFieldTypeEnum
     * @author lijh
     * @methodName getPromoteFormFieldTypeEnum
     * @time 2022-07-04 10:49:04
     */
    public static PromoteFormFieldTypeEnum getPromoteFormFieldTypeEnum(Integer value) {
        for (PromoteFormFieldTypeEnum promoteFormFieldTypeEnum : PromoteFormFieldTypeEnum.values()) {
            if (promoteFormFieldTypeEnum.getValue().equals(value)) {
                return promoteFormFieldTypeEnum;
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
        for (PromoteFormFieldTypeEnum promoteFormFieldTypeEnum : PromoteFormFieldTypeEnum.values()) {
            if (promoteFormFieldTypeEnum.getValue().equals(value)) {
                return promoteFormFieldTypeEnum.getDesc();
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
        for (PromoteFormFieldTypeEnum promoteFormFieldTypeEnum : PromoteFormFieldTypeEnum.values()) {
            if (promoteFormFieldTypeEnum.getDesc().equals(desc)) {
                return promoteFormFieldTypeEnum.getValue();
            }
        }
        return null;
    }
}