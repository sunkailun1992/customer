package com.gb.promoteform.entity.enums;

import com.baomidou.mybatisplus.annotation.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * TODO 推广表单浮框险种产品类型枚举
 * 代码生成器
 *
 * @author lijh
 * @className PromoteFormFloatingWindowDangerPlantedSpuTypeEnum
 * @time 2022-10-28 03:09:31
 */
@Getter
@AllArgsConstructor
public enum PromoteFormFloatingWindowDangerPlantedSpuTypeEnum implements IEnum<Integer> {
    // 默认
    默认(0, "默认"),
    ;

    private Integer value;
    private String desc;


    /**
     * TODO 通过value获得枚举
     *
     * @param value
     * @return PromoteFormFloatingWindowDangerPlantedSpuTypeEnum
     * @author lijh
     * @methodName getPromoteFormFloatingWindowDangerPlantedSpuTypeEnum
     * @time 2022-10-28 03:09:31
     */
    public static PromoteFormFloatingWindowDangerPlantedSpuTypeEnum getPromoteFormFloatingWindowDangerPlantedSpuTypeEnum(Integer value) {
        for (PromoteFormFloatingWindowDangerPlantedSpuTypeEnum promoteFormFloatingWindowDangerPlantedSpuTypeEnum : PromoteFormFloatingWindowDangerPlantedSpuTypeEnum.values()) {
            if (promoteFormFloatingWindowDangerPlantedSpuTypeEnum.getValue().equals(value)) {
                return promoteFormFloatingWindowDangerPlantedSpuTypeEnum;
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
     * @time 2022-10-28 03:09:31
     */
    public static String getDesc(Integer value) {
        for (PromoteFormFloatingWindowDangerPlantedSpuTypeEnum promoteFormFloatingWindowDangerPlantedSpuTypeEnum : PromoteFormFloatingWindowDangerPlantedSpuTypeEnum.values()) {
            if (promoteFormFloatingWindowDangerPlantedSpuTypeEnum.getValue().equals(value)) {
                return promoteFormFloatingWindowDangerPlantedSpuTypeEnum.getDesc();
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
     * @time 2022-10-28 03:09:31
     */
    public static Integer getDesc(String desc) {
        for (PromoteFormFloatingWindowDangerPlantedSpuTypeEnum promoteFormFloatingWindowDangerPlantedSpuTypeEnum : PromoteFormFloatingWindowDangerPlantedSpuTypeEnum.values()) {
            if (promoteFormFloatingWindowDangerPlantedSpuTypeEnum.getDesc().equals(desc)) {
                return promoteFormFloatingWindowDangerPlantedSpuTypeEnum.getValue();
            }
        }
        return null;
    }
}