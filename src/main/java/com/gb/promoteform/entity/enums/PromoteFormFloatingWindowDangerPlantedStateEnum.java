package com.gb.promoteform.entity.enums;

import com.baomidou.mybatisplus.annotation.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * TODO 推广表单浮框险种类型枚举
 * 代码生成器
 *
 * @author lijh
 * @className PromoteFormFloatingWindowDangerPlantedStateEnum
 * @time 2022-07-04 10:49:04
 */
@Getter
@AllArgsConstructor
public enum PromoteFormFloatingWindowDangerPlantedStateEnum implements IEnum<Integer> {
    // 默认
    默认(0, "默认"),
    ;

    private Integer value;
    private String desc;


    /**
     * TODO 通过value获得枚举
     *
     * @param value
     * @return PromoteFormFloatingWindowDangerPlantedStateEnum
     * @author lijh
     * @methodName getPromoteFormFloatingWindowDangerPlantedStateEnum
     * @time 2022-07-04 10:49:04
     */
    public static PromoteFormFloatingWindowDangerPlantedStateEnum getPromoteFormFloatingWindowDangerPlantedStateEnum(Integer value) {
        for (PromoteFormFloatingWindowDangerPlantedStateEnum promoteFormFloatingWindowDangerPlantedStateEnum : PromoteFormFloatingWindowDangerPlantedStateEnum.values()) {
            if (promoteFormFloatingWindowDangerPlantedStateEnum.getValue().equals(value)) {
                return promoteFormFloatingWindowDangerPlantedStateEnum;
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
        for (PromoteFormFloatingWindowDangerPlantedStateEnum promoteFormFloatingWindowDangerPlantedStateEnum : PromoteFormFloatingWindowDangerPlantedStateEnum.values()) {
            if (promoteFormFloatingWindowDangerPlantedStateEnum.getValue().equals(value)) {
                return promoteFormFloatingWindowDangerPlantedStateEnum.getDesc();
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
        for (PromoteFormFloatingWindowDangerPlantedStateEnum promoteFormFloatingWindowDangerPlantedStateEnum : PromoteFormFloatingWindowDangerPlantedStateEnum.values()) {
            if (promoteFormFloatingWindowDangerPlantedStateEnum.getDesc().equals(desc)) {
                return promoteFormFloatingWindowDangerPlantedStateEnum.getValue();
            }
        }
        return null;
    }
}