package com.gb.customer.entity.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * TODO 云服合作预约类型枚举
 * 代码生成器
 *
 * @author ljh
 * @className CooperativeAppointmentTypeEnum
 * @time 2023-01-09 02:56:41
 */
@Getter
@AllArgsConstructor
public enum CooperativeAppointmentTypeEnum implements IEnum<Integer> {
    // 工保云服
    工保云服(0, "工保云服"),
    ;

    private Integer value;
    private String desc;


    /**
     * TODO 通过value获得枚举
     *
     * @param value
     * @return CooperativeAppointmentTypeEnum
     * @author ljh
     * @methodName getCooperativeAppointmentTypeEnum
     * @time 2023-01-09 02:56:41
     */
    public static CooperativeAppointmentTypeEnum getCooperativeAppointmentTypeEnum(Integer value) {
        for (CooperativeAppointmentTypeEnum cooperativeAppointmentTypeEnum : CooperativeAppointmentTypeEnum.values()) {
            if (cooperativeAppointmentTypeEnum.getValue().equals(value)) {
                return cooperativeAppointmentTypeEnum;
            }
        }
        return null;
    }


    /**
     * TODO 获得备注
     *
     * @param value
     * @return String
     * @author ljh
     * @methodName getDesc
     * @time 2023-01-09 02:56:41
     */
    public static String getDesc(Integer value) {
        for (CooperativeAppointmentTypeEnum cooperativeAppointmentTypeEnum : CooperativeAppointmentTypeEnum.values()) {
            if (cooperativeAppointmentTypeEnum.getValue().equals(value)) {
                return cooperativeAppointmentTypeEnum.getDesc();
            }
        }
        return null;
    }


    /**
     * TODO 获得值
     *
     * @param desc
     * @return Integer
     * @author ljh
     * @methodName getDesc
     * @time 2023-01-09 02:56:41
     */
    public static Integer getDesc(String desc) {
        for (CooperativeAppointmentTypeEnum cooperativeAppointmentTypeEnum : CooperativeAppointmentTypeEnum.values()) {
            if (cooperativeAppointmentTypeEnum.getDesc().equals(desc)) {
                return cooperativeAppointmentTypeEnum.getValue();
            }
        }
        return null;
    }
}