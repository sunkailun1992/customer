package com.gb.customer.entity.enums;

import com.baomidou.mybatisplus.annotation.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created with IntelliJ IDEA.
 * @author:     	lijh
 * @since:   	    2021-11-02 10:03:07
 * @description:	TODO  助理关联类型枚举
 * @source:  	    代码生成器
 */
@Getter
@AllArgsConstructor
public enum AssistantAssociatedTypeEnum implements IEnum<Integer> {
    /**
     * 助理关联类型
     */
    业务管家(0, "业务管家"),
    特别业务管家(1, "特别业务管家"),
    ;

    private Integer value;
    private String desc;


    /**
     * 通过value获得枚举
     *
     * @param value
     * @return
     */
    public static AssistantAssociatedTypeEnum getAssistantAssociatedTypeEnum(Integer value) {
        for (AssistantAssociatedTypeEnum assistantAssociatedTypeEnum : AssistantAssociatedTypeEnum.values()) {
            if (assistantAssociatedTypeEnum.getValue().equals(value)) {
                return assistantAssociatedTypeEnum;
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
        for (AssistantAssociatedTypeEnum assistantAssociatedTypeEnum : AssistantAssociatedTypeEnum.values()) {
            if (assistantAssociatedTypeEnum.getValue().equals(value)) {
                return assistantAssociatedTypeEnum.getDesc();
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
        for (AssistantAssociatedTypeEnum assistantAssociatedTypeEnum : AssistantAssociatedTypeEnum.values()) {
            if (assistantAssociatedTypeEnum.getDesc().equals(desc)) {
                return assistantAssociatedTypeEnum.getValue();
            }
        }
        return null;
    }
}