package com.gb.configuration.entity.enums;

import com.baomidou.mybatisplus.annotation.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * TODO 项目类型值类型枚举
 * 代码生成器
 *
 * @author lijh
 * @className ProjectTypeValueTypeEnum
 * @time 2022-05-05 02:45:25
 */
@Getter
@AllArgsConstructor
public enum ProjectTypeValueTypeEnum implements IEnum<Integer> {
    // 默认
    默认(0, "默认"),
    ;

    private Integer value;
    private String desc;


    /**
     * TODO 通过value获得枚举
     *
     * @param value
     * @return ProjectTypeValueTypeEnum
     * @author lijh
     * @methodName getProjectTypeValueTypeEnum
     * @time 2022-05-05 02:45:25
     */
    public static ProjectTypeValueTypeEnum getProjectTypeValueTypeEnum(Integer value) {
        for (ProjectTypeValueTypeEnum projectTypeValueTypeEnum : ProjectTypeValueTypeEnum.values()) {
            if (projectTypeValueTypeEnum.getValue().equals(value)) {
                return projectTypeValueTypeEnum;
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
     * @time 2022-05-05 02:45:25
     */
    public static String getDesc(Integer value) {
        for (ProjectTypeValueTypeEnum projectTypeValueTypeEnum : ProjectTypeValueTypeEnum.values()) {
            if (projectTypeValueTypeEnum.getValue().equals(value)) {
                return projectTypeValueTypeEnum.getDesc();
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
     * @time 2022-05-05 02:45:25
     */
    public static Integer getDesc(String desc) {
        for (ProjectTypeValueTypeEnum projectTypeValueTypeEnum : ProjectTypeValueTypeEnum.values()) {
            if (projectTypeValueTypeEnum.getDesc().equals(desc)) {
                return projectTypeValueTypeEnum.getValue();
            }
        }
        return null;
    }
}