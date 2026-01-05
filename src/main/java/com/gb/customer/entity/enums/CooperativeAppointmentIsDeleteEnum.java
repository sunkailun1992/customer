package com.gb.customer.entity.enums;

import com.baomidou.mybatisplus.annotation.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * TODO 云服合作预约类型枚举
 * 代码生成器
 *
 * @author ljh
 * @className CooperativeAppointmentIsDeleteEnum
 * @time 2023-01-09 02:56:41
 */
@Getter
@AllArgsConstructor
public enum CooperativeAppointmentIsDeleteEnum implements IEnum<Integer> {
    // 未删除
    未删除(0, "未删除"),
    // 删除
    删除(1, "删除"),
    ;

    private Integer value;
    private String desc;


    /**
     * TODO 通过value获得枚举
     *
     * @param value
     * @return CooperativeAppointmentIsDeleteEnum
     * @author ljh
     * @methodName getCooperativeAppointmentIsDeleteEnum
     * @time 2023-01-09 02:56:41
     */
    public static CooperativeAppointmentIsDeleteEnum getCooperativeAppointmentIsDeleteEnum(Integer value) {
        for (CooperativeAppointmentIsDeleteEnum cooperativeAppointmentIsDeleteEnum : CooperativeAppointmentIsDeleteEnum.values()) {
            if (cooperativeAppointmentIsDeleteEnum.getValue().equals(value)) {
                return cooperativeAppointmentIsDeleteEnum;
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
        for (CooperativeAppointmentIsDeleteEnum cooperativeAppointmentIsDeleteEnum : CooperativeAppointmentIsDeleteEnum.values()) {
            if (cooperativeAppointmentIsDeleteEnum.getValue().equals(value)) {
                return cooperativeAppointmentIsDeleteEnum.getDesc();
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
        for (CooperativeAppointmentIsDeleteEnum cooperativeAppointmentIsDeleteEnum : CooperativeAppointmentIsDeleteEnum.values()) {
            if (cooperativeAppointmentIsDeleteEnum.getDesc().equals(desc)) {
                return cooperativeAppointmentIsDeleteEnum.getValue();
            }
        }
        return null;
    }
}