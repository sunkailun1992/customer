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
 * @className CooperativeAppointmentRequirementTypeEnum
 * @time 2023-01-09 02:56:41
 */
@Getter
@AllArgsConstructor
public enum CooperativeAppointmentRequirementTypeEnum implements IEnum<Integer> {
    // 渠道合作
    渠道合作(0, "渠道合作"),
    // 代理人合作
    代理人合作(1, "代理人合作"),
    // 保险机构合作
    保险机构合作(2, "保险机构合作"),
    // 生态资源合作
    生态资源合作(3, "生态资源合作"),
    ;
    /**
     * 在需要保存到数据库的值上面加上注解
     */
    private Integer value;
    /**
     * 需要在前端展示哪个值就在哪个属性上加上该注解
     */
    private String desc;


    /**
     * TODO 通过value获得枚举
     *
     * @param value
     * @return CooperativeAppointmentRequirementTypeEnum
     * @author ljh
     * @methodName getCooperativeAppointmentRequirementTypeEnum
     * @time 2023-01-09 02:56:41
     */
    public static CooperativeAppointmentRequirementTypeEnum getCooperativeAppointmentRequirementTypeEnum(Integer value) {
        for (CooperativeAppointmentRequirementTypeEnum cooperativeAppointmentRequirementTypeEnum : CooperativeAppointmentRequirementTypeEnum.values()) {
            if (cooperativeAppointmentRequirementTypeEnum.getValue().equals(value)) {
                return cooperativeAppointmentRequirementTypeEnum;
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
        for (CooperativeAppointmentRequirementTypeEnum cooperativeAppointmentRequirementTypeEnum : CooperativeAppointmentRequirementTypeEnum.values()) {
            if (cooperativeAppointmentRequirementTypeEnum.getValue().equals(value)) {
                return cooperativeAppointmentRequirementTypeEnum.getDesc();
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
        for (CooperativeAppointmentRequirementTypeEnum cooperativeAppointmentRequirementTypeEnum : CooperativeAppointmentRequirementTypeEnum.values()) {
            if (cooperativeAppointmentRequirementTypeEnum.getDesc().equals(desc)) {
                return cooperativeAppointmentRequirementTypeEnum.getValue();
            }
        }
        return null;
    }
}