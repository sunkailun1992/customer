package com.gb.promoteform.entity.enums;

import com.baomidou.mybatisplus.annotation.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * TODO 推广表单浮框险种类型枚举
 * 代码生成器
 *
 * @author lijh
 * @className PromoteFormFloatingWindowDangerPlantedTypeEnum
 * @time 2022-07-04 10:49:04
 */
@Getter
@AllArgsConstructor
public enum PromoteFormFloatingWindowDangerPlantedTypeEnum implements IEnum<Integer> {
    // 默认
    默认(0, "默认"),
    ;

    private Integer value;
    private String desc;


    /**
     * TODO 通过value获得枚举
     *
     * @param value
     * @return PromoteFormFloatingWindowDangerPlantedTypeEnum
     * @author lijh
     * @methodName getPromoteFormFloatingWindowDangerPlantedTypeEnum
     * @time 2022-07-04 10:49:04
     */
    public static PromoteFormFloatingWindowDangerPlantedTypeEnum getPromoteFormFloatingWindowDangerPlantedTypeEnum(Integer value) {
        for (PromoteFormFloatingWindowDangerPlantedTypeEnum promoteFormFloatingWindowDangerPlantedTypeEnum : PromoteFormFloatingWindowDangerPlantedTypeEnum.values()) {
            if (promoteFormFloatingWindowDangerPlantedTypeEnum.getValue().equals(value)) {
                return promoteFormFloatingWindowDangerPlantedTypeEnum;
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
        for (PromoteFormFloatingWindowDangerPlantedTypeEnum promoteFormFloatingWindowDangerPlantedTypeEnum : PromoteFormFloatingWindowDangerPlantedTypeEnum.values()) {
            if (promoteFormFloatingWindowDangerPlantedTypeEnum.getValue().equals(value)) {
                return promoteFormFloatingWindowDangerPlantedTypeEnum.getDesc();
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
        for (PromoteFormFloatingWindowDangerPlantedTypeEnum promoteFormFloatingWindowDangerPlantedTypeEnum : PromoteFormFloatingWindowDangerPlantedTypeEnum.values()) {
            if (promoteFormFloatingWindowDangerPlantedTypeEnum.getDesc().equals(desc)) {
                return promoteFormFloatingWindowDangerPlantedTypeEnum.getValue();
            }
        }
        return null;
    }
}