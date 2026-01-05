package com.gb.promoteform.entity.enums;

import com.baomidou.mybatisplus.annotation.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * TODO 推广表单按钮类型枚举
 * 代码生成器
 *
 * @author lijh
 * @className PromoteFormButtonStateEnum
 * @time 2022-07-04 10:49:04
 */
@Getter
@AllArgsConstructor
public enum PromoteFormButtonStateEnum implements IEnum<Integer> {
    // 按钮
    按钮(0, "按钮"),
    // 固底
    底部浮窗(1, "底部浮窗"),
    // 底部
    固底浮窗(2, "固底浮窗"),
    ;

    private Integer value;
    private String desc;


    /**
     * TODO 通过value获得枚举
     *
     * @param value
     * @return PromoteFormButtonStateEnum
     * @author lijh
     * @methodName getPromoteFormButtonStateEnum
     * @time 2022-07-04 10:49:04
     */
    public static PromoteFormButtonStateEnum getPromoteFormButtonStateEnum(Integer value) {
        for (PromoteFormButtonStateEnum promoteFormButtonStateEnum : PromoteFormButtonStateEnum.values()) {
            if (promoteFormButtonStateEnum.getValue().equals(value)) {
                return promoteFormButtonStateEnum;
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
        for (PromoteFormButtonStateEnum promoteFormButtonStateEnum : PromoteFormButtonStateEnum.values()) {
            if (promoteFormButtonStateEnum.getValue().equals(value)) {
                return promoteFormButtonStateEnum.getDesc();
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
        for (PromoteFormButtonStateEnum promoteFormButtonStateEnum : PromoteFormButtonStateEnum.values()) {
            if (promoteFormButtonStateEnum.getDesc().equals(desc)) {
                return promoteFormButtonStateEnum.getValue();
            }
        }
        return null;
    }
}