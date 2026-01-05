package com.gb.promoteform.entity.enums;

import com.baomidou.mybatisplus.annotation.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * TODO 推广表单浮框险种产品类型枚举
 * 代码生成器
 *
 * @author lijh
 * @className PromoteFormFloatingWindowDangerPlantedSpuStateEnum
 * @time 2022-10-28 03:09:31
 */
@Getter
@AllArgsConstructor
public enum PromoteFormFloatingWindowDangerPlantedSpuStateEnum implements IEnum<Integer> {
    // 默认
    默认(0, "默认"),
    ;

    private Integer value;
    private String desc;


    /**
     * TODO 通过value获得枚举
     *
     * @param value
     * @return PromoteFormFloatingWindowDangerPlantedSpuStateEnum
     * @author lijh
     * @methodName getPromoteFormFloatingWindowDangerPlantedSpuStateEnum
     * @time 2022-10-28 03:09:31
     */
    public static PromoteFormFloatingWindowDangerPlantedSpuStateEnum getPromoteFormFloatingWindowDangerPlantedSpuStateEnum(Integer value) {
        for (PromoteFormFloatingWindowDangerPlantedSpuStateEnum promoteFormFloatingWindowDangerPlantedSpuStateEnum : PromoteFormFloatingWindowDangerPlantedSpuStateEnum.values()) {
            if (promoteFormFloatingWindowDangerPlantedSpuStateEnum.getValue().equals(value)) {
                return promoteFormFloatingWindowDangerPlantedSpuStateEnum;
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
        for (PromoteFormFloatingWindowDangerPlantedSpuStateEnum promoteFormFloatingWindowDangerPlantedSpuStateEnum : PromoteFormFloatingWindowDangerPlantedSpuStateEnum.values()) {
            if (promoteFormFloatingWindowDangerPlantedSpuStateEnum.getValue().equals(value)) {
                return promoteFormFloatingWindowDangerPlantedSpuStateEnum.getDesc();
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
        for (PromoteFormFloatingWindowDangerPlantedSpuStateEnum promoteFormFloatingWindowDangerPlantedSpuStateEnum : PromoteFormFloatingWindowDangerPlantedSpuStateEnum.values()) {
            if (promoteFormFloatingWindowDangerPlantedSpuStateEnum.getDesc().equals(desc)) {
                return promoteFormFloatingWindowDangerPlantedSpuStateEnum.getValue();
            }
        }
        return null;
    }
}