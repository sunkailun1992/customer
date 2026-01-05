package com.gb.promoteform.entity.enums;

import com.baomidou.mybatisplus.annotation.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * TODO 推广表单浮框类型枚举
 * 代码生成器
 *
 * @author lijh
 * @className PromoteFormFloatingWindowStateEnum
 * @time 2022-07-04 10:49:04
 */
@Getter
@AllArgsConstructor
public enum PromoteFormFloatingWindowStateEnum implements IEnum<Integer> {
    // 默认
    默认(0, "默认"),
    ;

    private Integer value;
    private String desc;


    /**
     * TODO 通过value获得枚举
     *
     * @param value
     * @return PromoteFormFloatingWindowStateEnum
     * @author lijh
     * @methodName getPromoteFormFloatingWindowStateEnum
     * @time 2022-07-04 10:49:04
     */
    public static PromoteFormFloatingWindowStateEnum getPromoteFormFloatingWindowStateEnum(Integer value) {
        for (PromoteFormFloatingWindowStateEnum promoteFormFloatingWindowStateEnum : PromoteFormFloatingWindowStateEnum.values()) {
            if (promoteFormFloatingWindowStateEnum.getValue().equals(value)) {
                return promoteFormFloatingWindowStateEnum;
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
        for (PromoteFormFloatingWindowStateEnum promoteFormFloatingWindowStateEnum : PromoteFormFloatingWindowStateEnum.values()) {
            if (promoteFormFloatingWindowStateEnum.getValue().equals(value)) {
                return promoteFormFloatingWindowStateEnum.getDesc();
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
        for (PromoteFormFloatingWindowStateEnum promoteFormFloatingWindowStateEnum : PromoteFormFloatingWindowStateEnum.values()) {
            if (promoteFormFloatingWindowStateEnum.getDesc().equals(desc)) {
                return promoteFormFloatingWindowStateEnum.getValue();
            }
        }
        return null;
    }
}