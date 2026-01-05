package com.gb.promoteform.entity.enums;

import com.baomidou.mybatisplus.annotation.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * TODO 推广表单字段类型枚举
 * 代码生成器
 *
 * @author lijh
 * @className PromoteFormFieldStateEnum
 * @time 2022-07-04 10:49:04
 */
@Getter
@AllArgsConstructor
public enum PromoteFormFieldStateEnum implements IEnum<Integer> {
    // 默认
    默认(0, "默认"),
    ;

    private Integer value;
    private String desc;


    /**
     * TODO 通过value获得枚举
     *
     * @param value
     * @return PromoteFormFieldStateEnum
     * @author lijh
     * @methodName getPromoteFormFieldStateEnum
     * @time 2022-07-04 10:49:04
     */
    public static PromoteFormFieldStateEnum getPromoteFormFieldStateEnum(Integer value) {
        for (PromoteFormFieldStateEnum promoteFormFieldStateEnum : PromoteFormFieldStateEnum.values()) {
            if (promoteFormFieldStateEnum.getValue().equals(value)) {
                return promoteFormFieldStateEnum;
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
        for (PromoteFormFieldStateEnum promoteFormFieldStateEnum : PromoteFormFieldStateEnum.values()) {
            if (promoteFormFieldStateEnum.getValue().equals(value)) {
                return promoteFormFieldStateEnum.getDesc();
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
        for (PromoteFormFieldStateEnum promoteFormFieldStateEnum : PromoteFormFieldStateEnum.values()) {
            if (promoteFormFieldStateEnum.getDesc().equals(desc)) {
                return promoteFormFieldStateEnum.getValue();
            }
        }
        return null;
    }
}