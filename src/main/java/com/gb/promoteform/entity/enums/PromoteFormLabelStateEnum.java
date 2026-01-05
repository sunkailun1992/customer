package com.gb.promoteform.entity.enums;

import com.baomidou.mybatisplus.annotation.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * TODO 推广表单标签类型枚举
 * 代码生成器
 *
 * @author lijh
 * @className PromoteFormLabelStateEnum
 * @time 2022-07-04 10:49:04
 */
@Getter
@AllArgsConstructor
public enum PromoteFormLabelStateEnum implements IEnum<Integer> {
    // 默认
    默认(0, "默认"),
    ;

    private Integer value;
    private String desc;


    /**
     * TODO 通过value获得枚举
     *
     * @param value
     * @return PromoteFormLabelStateEnum
     * @author lijh
     * @methodName getPromoteFormLabelStateEnum
     * @time 2022-07-04 10:49:04
     */
    public static PromoteFormLabelStateEnum getPromoteFormLabelStateEnum(Integer value) {
        for (PromoteFormLabelStateEnum promoteFormLabelStateEnum : PromoteFormLabelStateEnum.values()) {
            if (promoteFormLabelStateEnum.getValue().equals(value)) {
                return promoteFormLabelStateEnum;
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
        for (PromoteFormLabelStateEnum promoteFormLabelStateEnum : PromoteFormLabelStateEnum.values()) {
            if (promoteFormLabelStateEnum.getValue().equals(value)) {
                return promoteFormLabelStateEnum.getDesc();
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
        for (PromoteFormLabelStateEnum promoteFormLabelStateEnum : PromoteFormLabelStateEnum.values()) {
            if (promoteFormLabelStateEnum.getDesc().equals(desc)) {
                return promoteFormLabelStateEnum.getValue();
            }
        }
        return null;
    }
}