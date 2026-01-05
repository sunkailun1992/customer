package com.gb.promoteform.entity.enums;

import com.baomidou.mybatisplus.annotation.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * TODO 推广表单标签类型枚举
 * 代码生成器
 *
 * @author lijh
 * @className PromoteFormLabelTypeEnum
 * @time 2022-07-04 10:49:04
 */
@Getter
@AllArgsConstructor
public enum PromoteFormLabelTypeEnum implements IEnum<Integer> {
    // 默认
    默认(0, "默认"),
    ;

    private Integer value;
    private String desc;


    /**
     * TODO 通过value获得枚举
     *
     * @param value
     * @return PromoteFormLabelTypeEnum
     * @author lijh
     * @methodName getPromoteFormLabelTypeEnum
     * @time 2022-07-04 10:49:04
     */
    public static PromoteFormLabelTypeEnum getPromoteFormLabelTypeEnum(Integer value) {
        for (PromoteFormLabelTypeEnum promoteFormLabelTypeEnum : PromoteFormLabelTypeEnum.values()) {
            if (promoteFormLabelTypeEnum.getValue().equals(value)) {
                return promoteFormLabelTypeEnum;
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
        for (PromoteFormLabelTypeEnum promoteFormLabelTypeEnum : PromoteFormLabelTypeEnum.values()) {
            if (promoteFormLabelTypeEnum.getValue().equals(value)) {
                return promoteFormLabelTypeEnum.getDesc();
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
        for (PromoteFormLabelTypeEnum promoteFormLabelTypeEnum : PromoteFormLabelTypeEnum.values()) {
            if (promoteFormLabelTypeEnum.getDesc().equals(desc)) {
                return promoteFormLabelTypeEnum.getValue();
            }
        }
        return null;
    }
}