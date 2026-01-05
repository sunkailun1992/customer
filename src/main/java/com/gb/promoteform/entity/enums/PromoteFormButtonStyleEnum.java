package com.gb.promoteform.entity.enums;

import com.baomidou.mybatisplus.annotation.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * TODO 推广表单按钮类型枚举
 * 代码生成器
 *
 * @author lijh
 * @className PromoteFormButtonStyleEnum
 * @time 2022-07-04 10:49:04
 */
@Getter
@AllArgsConstructor
public enum PromoteFormButtonStyleEnum implements IEnum<Integer> {
    // 普通按钮
    普通按钮(0, "普通按钮"),
    // 焦点按钮
    焦点按钮(1, "焦点按钮"),
    ;

    private Integer value;
    private String desc;


    /**
     * TODO 通过value获得枚举
     *
     * @param value
     * @return PromoteFormButtonStyleEnum
     * @author lijh
     * @methodName getPromoteFormButtonStyleEnum
     * @time 2022-07-04 10:49:04
     */
    public static PromoteFormButtonStyleEnum getPromoteFormButtonStyleEnum(Integer value) {
        for (PromoteFormButtonStyleEnum promoteFormButtonStyleEnum : PromoteFormButtonStyleEnum.values()) {
            if (promoteFormButtonStyleEnum.getValue().equals(value)) {
                return promoteFormButtonStyleEnum;
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
        for (PromoteFormButtonStyleEnum promoteFormButtonStyleEnum : PromoteFormButtonStyleEnum.values()) {
            if (promoteFormButtonStyleEnum.getValue().equals(value)) {
                return promoteFormButtonStyleEnum.getDesc();
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
        for (PromoteFormButtonStyleEnum promoteFormButtonStyleEnum : PromoteFormButtonStyleEnum.values()) {
            if (promoteFormButtonStyleEnum.getDesc().equals(desc)) {
                return promoteFormButtonStyleEnum.getValue();
            }
        }
        return null;
    }
}