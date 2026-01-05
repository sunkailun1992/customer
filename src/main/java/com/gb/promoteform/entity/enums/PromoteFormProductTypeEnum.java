package com.gb.promoteform.entity.enums;

import com.baomidou.mybatisplus.annotation.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * TODO 推广表单产品类型枚举
 * 代码生成器
 *
 * @author lijh
 * @className PromoteFormProductTypeEnum
 * @time 2022-07-04 10:49:04
 */
@Getter
@AllArgsConstructor
public enum PromoteFormProductTypeEnum implements IEnum<Integer> {
    // 信息收集表单
    信息收集表单(0, "信息收集表单"),
    // 投保链接
    投保链接(1, "投保链接"),
    ;

    private Integer value;
    private String desc;


    /**
     * TODO 通过value获得枚举
     *
     * @param value
     * @return PromoteFormProductTypeEnum
     * @author lijh
     * @methodName getPromoteFormProductTypeEnum
     * @time 2022-07-04 10:49:04
     */
    public static PromoteFormProductTypeEnum getPromoteFormProductTypeEnum(Integer value) {
        for (PromoteFormProductTypeEnum promoteFormProductTypeEnum : PromoteFormProductTypeEnum.values()) {
            if (promoteFormProductTypeEnum.getValue().equals(value)) {
                return promoteFormProductTypeEnum;
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
        for (PromoteFormProductTypeEnum promoteFormProductTypeEnum : PromoteFormProductTypeEnum.values()) {
            if (promoteFormProductTypeEnum.getValue().equals(value)) {
                return promoteFormProductTypeEnum.getDesc();
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
        for (PromoteFormProductTypeEnum promoteFormProductTypeEnum : PromoteFormProductTypeEnum.values()) {
            if (promoteFormProductTypeEnum.getDesc().equals(desc)) {
                return promoteFormProductTypeEnum.getValue();
            }
        }
        return null;
    }
}