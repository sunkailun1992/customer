package com.gb.promoteform.entity.enums;

import com.baomidou.mybatisplus.annotation.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * TODO 推广表单浮框类型枚举
 * 代码生成器
 *
 * @author lijh
 * @className PromoteFormFloatingWindowTypeEnum
 * @time 2022-07-04 10:49:04
 */
@Getter
@AllArgsConstructor
public enum PromoteFormFloatingWindowTypeEnum implements IEnum<Integer> {
    // 默认
    默认(0, "默认"),
    ;

    private Integer value;
    private String desc;


    /**
     * TODO 通过value获得枚举
     *
     * @param value
     * @return PromoteFormFloatingWindowTypeEnum
     * @author lijh
     * @methodName getPromoteFormFloatingWindowTypeEnum
     * @time 2022-07-04 10:49:04
     */
    public static PromoteFormFloatingWindowTypeEnum getPromoteFormFloatingWindowTypeEnum(Integer value) {
        for (PromoteFormFloatingWindowTypeEnum promoteFormFloatingWindowTypeEnum : PromoteFormFloatingWindowTypeEnum.values()) {
            if (promoteFormFloatingWindowTypeEnum.getValue().equals(value)) {
                return promoteFormFloatingWindowTypeEnum;
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
        for (PromoteFormFloatingWindowTypeEnum promoteFormFloatingWindowTypeEnum : PromoteFormFloatingWindowTypeEnum.values()) {
            if (promoteFormFloatingWindowTypeEnum.getValue().equals(value)) {
                return promoteFormFloatingWindowTypeEnum.getDesc();
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
        for (PromoteFormFloatingWindowTypeEnum promoteFormFloatingWindowTypeEnum : PromoteFormFloatingWindowTypeEnum.values()) {
            if (promoteFormFloatingWindowTypeEnum.getDesc().equals(desc)) {
                return promoteFormFloatingWindowTypeEnum.getValue();
            }
        }
        return null;
    }
}