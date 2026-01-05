package com.gb.promoteform.entity.enums;

import com.baomidou.mybatisplus.annotation.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * TODO 推广表单产品id类型枚举
 * 代码生成器
 *
 * @author lijh
 * @className PromoteFormProductSpuStateEnum
 * @time 2022-10-28 03:09:32
 */
@Getter
@AllArgsConstructor
public enum PromoteFormProductSpuStateEnum implements IEnum<Integer> {
    // 默认
    默认(0, "默认"),
    ;

    private Integer value;
    private String desc;


    /**
     * TODO 通过value获得枚举
     *
     * @param value
     * @return PromoteFormProductSpuStateEnum
     * @author lijh
     * @methodName getPromoteFormProductSpuStateEnum
     * @time 2022-10-28 03:09:32
     */
    public static PromoteFormProductSpuStateEnum getPromoteFormProductSpuStateEnum(Integer value) {
        for (PromoteFormProductSpuStateEnum promoteFormProductSpuStateEnum : PromoteFormProductSpuStateEnum.values()) {
            if (promoteFormProductSpuStateEnum.getValue().equals(value)) {
                return promoteFormProductSpuStateEnum;
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
        for (PromoteFormProductSpuStateEnum promoteFormProductSpuStateEnum : PromoteFormProductSpuStateEnum.values()) {
            if (promoteFormProductSpuStateEnum.getValue().equals(value)) {
                return promoteFormProductSpuStateEnum.getDesc();
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
        for (PromoteFormProductSpuStateEnum promoteFormProductSpuStateEnum : PromoteFormProductSpuStateEnum.values()) {
            if (promoteFormProductSpuStateEnum.getDesc().equals(desc)) {
                return promoteFormProductSpuStateEnum.getValue();
            }
        }
        return null;
    }
}