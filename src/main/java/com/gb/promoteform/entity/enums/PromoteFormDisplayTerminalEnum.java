package com.gb.promoteform.entity.enums;

import com.baomidou.mybatisplus.annotation.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * TODO 推广表单类型枚举
 * 代码生成器
 *
 * @author lijh
 * @className PromoteFormDisplayTerminalEnum
 * @time 2022-07-04 10:47:25
 */
@Getter
@AllArgsConstructor
public enum PromoteFormDisplayTerminalEnum implements IEnum<Integer> {
    // h5
    h5(0, "h5"),
    // pc
    pc(1, "pc"),
    ;

    private Integer value;
    private String desc;


    /**
     * TODO 通过value获得枚举
     *
     * @param value
     * @return PromoteFormDisplayTerminalEnum
     * @author lijh
     * @methodName getPromoteFormDisplayTerminalEnum
     * @time 2022-07-04 10:47:25
     */
    public static PromoteFormDisplayTerminalEnum getPromoteFormDisplayTerminalEnum(Integer value) {
        for (PromoteFormDisplayTerminalEnum promoteFormDisplayTerminalEnum : PromoteFormDisplayTerminalEnum.values()) {
            if (promoteFormDisplayTerminalEnum.getValue().equals(value)) {
                return promoteFormDisplayTerminalEnum;
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
        for (PromoteFormDisplayTerminalEnum promoteFormDisplayTerminalEnum : PromoteFormDisplayTerminalEnum.values()) {
            if (promoteFormDisplayTerminalEnum.getValue().equals(value)) {
                return promoteFormDisplayTerminalEnum.getDesc();
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
        for (PromoteFormDisplayTerminalEnum promoteFormDisplayTerminalEnum : PromoteFormDisplayTerminalEnum.values()) {
            if (promoteFormDisplayTerminalEnum.getDesc().equals(desc)) {
                return promoteFormDisplayTerminalEnum.getValue();
            }
        }
        return null;
    }
}