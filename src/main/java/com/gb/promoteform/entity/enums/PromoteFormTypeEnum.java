package com.gb.promoteform.entity.enums;

import com.baomidou.mybatisplus.annotation.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;


/**
 * TODO 推广表单类型枚举
 * 代码生成器
 *
 * @author lijh
 * @className PromoteFormTypeEnum
 * @time 2022-07-04 10:47:25
 */
@Getter
@AllArgsConstructor
public enum PromoteFormTypeEnum implements IEnum<Integer> {
    // 单险种信息收集
    单险种信息收集(0, "信息收集-单险种"),
    // 综合信息收集
    综合信息收集(1, "信息收集-多险种"),
    // 单险种投保
    单险种投保(2, "投保-单险种"),
    // 综合投保
    综合投保(3, "投保-多险种"),
    ;

    private Integer value;
    private String desc;


    /**
     * TODO 通过value获得枚举
     *
     * @param value
     * @return PromoteFormTypeEnum
     * @author lijh
     * @methodName getPromoteFormTypeEnum
     * @time 2022-07-04 10:47:25
     */
    public static PromoteFormTypeEnum getPromoteFormTypeEnum(Integer value) {
        for (PromoteFormTypeEnum promoteFormTypeEnum : PromoteFormTypeEnum.values()) {
            if (promoteFormTypeEnum.getValue().equals(value)) {
                return promoteFormTypeEnum;
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
     * @time 2022-07-04 10:47:25
     */
    public static String getDesc(Integer value) {
        for (PromoteFormTypeEnum promoteFormTypeEnum : PromoteFormTypeEnum.values()) {
            if (promoteFormTypeEnum.getValue().equals(value)) {
                return promoteFormTypeEnum.getDesc();
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
     * @time 2022-07-04 10:47:25
     */
    public static Integer getDesc(String desc) {
        for (PromoteFormTypeEnum promoteFormTypeEnum : PromoteFormTypeEnum.values()) {
            if (promoteFormTypeEnum.getDesc().equals(desc)) {
                return promoteFormTypeEnum.getValue();
            }
        }
        return null;
    }

    /**
     * TODO 模糊获得值
     *
     * @param desc
     * @return Integer
     * @author lijh
     * @methodName getDesc
     * @time 2022-07-04 10:47:25
     */
    public static List<Integer> vagueGetDesc(String desc) {
        List<Integer> valueList = new ArrayList<>();
        for (PromoteFormTypeEnum promoteFormTypeEnum : PromoteFormTypeEnum.values()) {
            if (promoteFormTypeEnum.getDesc().contains(desc)) {
                valueList.add(promoteFormTypeEnum.getValue());
            }
        }
        return valueList;
    }
}