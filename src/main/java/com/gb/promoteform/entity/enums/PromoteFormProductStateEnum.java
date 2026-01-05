package com.gb.promoteform.entity.enums;

import com.baomidou.mybatisplus.annotation.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * TODO 推广表单产品类型枚举
 * 代码生成器
 *
 * @author lijh
 * @className PromoteFormProductStateEnum
 * @time 2022-07-04 10:49:04
 */
@Getter
@AllArgsConstructor
public enum PromoteFormProductStateEnum implements IEnum<Integer> {
    // 默认
    默认(0, "默认"),
    ;

    private Integer value;
    private String desc;


    /**
     * TODO 通过value获得枚举
     *
     * @param value
     * @return PromoteFormProductStateEnum
     * @author lijh
     * @methodName getPromoteFormProductStateEnum
     * @time 2022-07-04 10:49:04
     */
    public static PromoteFormProductStateEnum getPromoteFormProductStateEnum(Integer value) {
        for (PromoteFormProductStateEnum promoteFormProductStateEnum : PromoteFormProductStateEnum.values()) {
            if (promoteFormProductStateEnum.getValue().equals(value)) {
                return promoteFormProductStateEnum;
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
        for (PromoteFormProductStateEnum promoteFormProductStateEnum : PromoteFormProductStateEnum.values()) {
            if (promoteFormProductStateEnum.getValue().equals(value)) {
                return promoteFormProductStateEnum.getDesc();
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
        for (PromoteFormProductStateEnum promoteFormProductStateEnum : PromoteFormProductStateEnum.values()) {
            if (promoteFormProductStateEnum.getDesc().equals(desc)) {
                return promoteFormProductStateEnum.getValue();
            }
        }
        return null;
    }
}