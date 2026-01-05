package com.gb.customer.entity.enums;

import com.baomidou.mybatisplus.annotation.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: lijh
 * @since: 2021-11-02 10:03:07
 * @description: TODO 助理关联类型枚举
 * @source: 代码生成器
 */
@Getter
@AllArgsConstructor
public enum AssistantAssociatedStateEnum implements IEnum<Integer> {
    /**
     * 助理状态
     */
    不启用(0, "不启用"),
    启用(1, "启用"),
    ;

    private Integer value;
    private String desc;


    /**
     * 通过value获得枚举
     *
     * @param value
     * @return
     */
    public static AssistantAssociatedStateEnum getAssistantAssociatedStateEnum(Integer value) {
        for (AssistantAssociatedStateEnum assistantAssociatedStateEnum : AssistantAssociatedStateEnum.values()) {
            if (assistantAssociatedStateEnum.getValue().equals(value)) {
                return assistantAssociatedStateEnum;
            }
        }
        return null;
    }


    /**
     * 获得备注
     *
     * @param value
     * @return
     */
    public static String getDesc(Integer value) {
        for (AssistantAssociatedStateEnum assistantAssociatedStateEnum : AssistantAssociatedStateEnum.values()) {
            if (assistantAssociatedStateEnum.getValue().equals(value)) {
                return assistantAssociatedStateEnum.getDesc();
            }
        }
        return null;
    }


    /**
     * 获得值
     *
     * @param desc
     * @return
     */
    public static Integer getDesc(String desc) {
        for (AssistantAssociatedStateEnum assistantAssociatedStateEnum : AssistantAssociatedStateEnum.values()) {
            if (assistantAssociatedStateEnum.getDesc().equals(desc)) {
                return assistantAssociatedStateEnum.getValue();
            }
        }
        return null;
    }
}