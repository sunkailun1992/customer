package com.gb.promoteform.entity.enums;

import com.baomidou.mybatisplus.annotation.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * TODO 推广表单产品id类型枚举
 * 代码生成器
 *
 * @author lijh
 * @className PromoteFormProductSpuTypeEnum
 * @time 2022-10-28 03:09:32
 */
@Getter
@AllArgsConstructor
public enum PromoteFormProductSpuTypeEnum implements IEnum<Integer> {
    // 默认
    默认(0, "默认"),
    ;

    private Integer value;
    private String desc;


    /**
     * TODO 通过value获得枚举
     *
     * @param value
     * @return PromoteFormProductSpuTypeEnum
     * @author lijh
     * @methodName getPromoteFormProductSpuTypeEnum
     * @time 2022-10-28 03:09:32
     */
    public static PromoteFormProductSpuTypeEnum getPromoteFormProductSpuTypeEnum(Integer value) {
        for (PromoteFormProductSpuTypeEnum promoteFormProductSpuTypeEnum : PromoteFormProductSpuTypeEnum.values()) {
            if (promoteFormProductSpuTypeEnum.getValue().equals(value)) {
                return promoteFormProductSpuTypeEnum;
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
     * @time 2022-10-28 03:09:32
     */
    public static String getDesc(Integer value) {
        for (PromoteFormProductSpuTypeEnum promoteFormProductSpuTypeEnum : PromoteFormProductSpuTypeEnum.values()) {
            if (promoteFormProductSpuTypeEnum.getValue().equals(value)) {
                return promoteFormProductSpuTypeEnum.getDesc();
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
     * @time 2022-10-28 03:09:32
     */
    public static Integer getDesc(String desc) {
        for (PromoteFormProductSpuTypeEnum promoteFormProductSpuTypeEnum : PromoteFormProductSpuTypeEnum.values()) {
            if (promoteFormProductSpuTypeEnum.getDesc().equals(desc)) {
                return promoteFormProductSpuTypeEnum.getValue();
            }
        }
        return null;
    }
}