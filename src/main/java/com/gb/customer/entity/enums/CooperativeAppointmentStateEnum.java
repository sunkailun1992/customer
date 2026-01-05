package com.gb.customer.entity.enums;

import com.baomidou.mybatisplus.annotation.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * TODO 云服合作预约类型枚举
 * 代码生成器
 *
 * @author ljh
 * @className CooperativeAppointmentStateEnum
 * @time 2023-01-09 02:56:41
 */
@Getter
@AllArgsConstructor
public enum CooperativeAppointmentStateEnum implements IEnum<Integer> {
    // 待处理
    待处理(0, "待处理"),
    // 处理中
    处理中(1, "处理中"),
    // 已完成
    已完成(2, "已完成"),
    // 已关闭
    已关闭(3, "已关闭"),
    ;

    private Integer value;
    private String desc;


    /**
     * TODO 通过value获得枚举
     *
     * @param value
     * @return CooperativeAppointmentStateEnum
     * @author ljh
     * @methodName getCooperativeAppointmentStateEnum
     * @time 2023-01-09 02:56:41
     */
    public static CooperativeAppointmentStateEnum getCooperativeAppointmentStateEnum(Integer value) {
        for (CooperativeAppointmentStateEnum cooperativeAppointmentStateEnum : CooperativeAppointmentStateEnum.values()) {
            if (cooperativeAppointmentStateEnum.getValue().equals(value)) {
                return cooperativeAppointmentStateEnum;
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
        for (CooperativeAppointmentStateEnum cooperativeAppointmentStateEnum : CooperativeAppointmentStateEnum.values()) {
            if (cooperativeAppointmentStateEnum.getValue().equals(value)) {
                return cooperativeAppointmentStateEnum.getDesc();
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
        for (CooperativeAppointmentStateEnum cooperativeAppointmentStateEnum : CooperativeAppointmentStateEnum.values()) {
            if (cooperativeAppointmentStateEnum.getDesc().equals(desc)) {
                return cooperativeAppointmentStateEnum.getValue();
            }
        }
        return null;
    }
}