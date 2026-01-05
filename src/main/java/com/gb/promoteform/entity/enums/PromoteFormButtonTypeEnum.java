package com.gb.promoteform.entity.enums;

import com.baomidou.mybatisplus.annotation.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * TODO 推广表单按钮类型枚举
 * 代码生成器
 *
 * @author lijh
 * @className PromoteFormButtonTypeEnum
 * @time 2022-07-04 10:49:04
 */
@Getter
@AllArgsConstructor
public enum PromoteFormButtonTypeEnum implements IEnum<Integer> {
    // 表单提交
    表单提交(0, "表单提交"),
    // 投保链接
    投保链接(1, "投保链接"),
    // 选择险种
    选择险种(2, "选择险种"),
    // 选择产品
    选择产品(3, "选择产品");

    private Integer value;
    private String desc;


    /**
     * TODO 通过value获得枚举
     *
     * @param value
     * @return PromoteFormButtonTypeEnum
     * @author lijh
     * @methodName getPromoteFormButtonTypeEnum
     * @time 2022-07-04 10:49:04
     */
    public static PromoteFormButtonTypeEnum getPromoteFormButtonTypeEnum(Integer value) {
        for (PromoteFormButtonTypeEnum promoteFormButtonTypeEnum : PromoteFormButtonTypeEnum.values()) {
            if (promoteFormButtonTypeEnum.getValue().equals(value)) {
                return promoteFormButtonTypeEnum;
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
        for (PromoteFormButtonTypeEnum promoteFormButtonTypeEnum : PromoteFormButtonTypeEnum.values()) {
            if (promoteFormButtonTypeEnum.getValue().equals(value)) {
                return promoteFormButtonTypeEnum.getDesc();
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
        for (PromoteFormButtonTypeEnum promoteFormButtonTypeEnum : PromoteFormButtonTypeEnum.values()) {
            if (promoteFormButtonTypeEnum.getDesc().equals(desc)) {
                return promoteFormButtonTypeEnum.getValue();
            }
        }
        return null;
    }
}