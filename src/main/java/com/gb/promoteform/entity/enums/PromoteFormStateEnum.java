package com.gb.promoteform.entity.enums;

import com.baomidou.mybatisplus.annotation.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * TODO 推广表单类型枚举
 * 代码生成器
 *
 * @author lijh
 * @className PromoteFormStateEnum
 * @time 2022-07-04 10:47:25
 */
@Getter
@AllArgsConstructor
public enum PromoteFormStateEnum implements IEnum<Integer> {
    // 默认
    默认(0, "默认"),
    ;

    private Integer value;
    private String desc;


    /**
     * TODO 通过value获得枚举
     *
     * @param value
     * @return PromoteFormStateEnum
     * @author lijh
     * @methodName getPromoteFormStateEnum
     * @time 2022-07-04 10:47:25
     */
    public static PromoteFormStateEnum getPromoteFormStateEnum(Integer value) {
        for (PromoteFormStateEnum promoteFormStateEnum : PromoteFormStateEnum.values()) {
            if (promoteFormStateEnum.getValue().equals(value)) {
                return promoteFormStateEnum;
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
     * @time 2022-07-04 10:47:25
     */
    public static String getDesc(Integer value) {
        for (PromoteFormStateEnum promoteFormStateEnum : PromoteFormStateEnum.values()) {
            if (promoteFormStateEnum.getValue().equals(value)) {
                return promoteFormStateEnum.getDesc();
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
     * @time 2022-07-04 10:47:25
     */
    public static Integer getDesc(String desc) {
        for (PromoteFormStateEnum promoteFormStateEnum : PromoteFormStateEnum.values()) {
            if (promoteFormStateEnum.getDesc().equals(desc)) {
                return promoteFormStateEnum.getValue();
            }
        }
        return null;
    }
}