package com.gb.promoteform.entity.enums;

import com.baomidou.mybatisplus.annotation.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * TODO 推广表单窗口类型枚举
 * 代码生成器
 *
 * @author lijh
 * @className PromoteFormWindowStateEnum
 * @time 2022-07-04 10:49:05
 */
@Getter
@AllArgsConstructor
public enum PromoteFormWindowStateEnum implements IEnum<Integer> {
    // 默认
    默认(0, "默认"),
    ;

    private Integer value;
    private String desc;


    /**
     * TODO 通过value获得枚举
     *
     * @param value
     * @return PromoteFormWindowStateEnum
     * @author lijh
     * @methodName getPromoteFormWindowStateEnum
     * @time 2022-07-04 10:49:05
     */
    public static PromoteFormWindowStateEnum getPromoteFormWindowStateEnum(Integer value) {
        for (PromoteFormWindowStateEnum promoteFormWindowStateEnum : PromoteFormWindowStateEnum.values()) {
            if (promoteFormWindowStateEnum.getValue().equals(value)) {
                return promoteFormWindowStateEnum;
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
     * @time 2022-07-04 10:49:05
     */
    public static String getDesc(Integer value) {
        for (PromoteFormWindowStateEnum promoteFormWindowStateEnum : PromoteFormWindowStateEnum.values()) {
            if (promoteFormWindowStateEnum.getValue().equals(value)) {
                return promoteFormWindowStateEnum.getDesc();
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
     * @time 2022-07-04 10:49:05
     */
    public static Integer getDesc(String desc) {
        for (PromoteFormWindowStateEnum promoteFormWindowStateEnum : PromoteFormWindowStateEnum.values()) {
            if (promoteFormWindowStateEnum.getDesc().equals(desc)) {
                return promoteFormWindowStateEnum.getValue();
            }
        }
        return null;
    }
}