package com.gb.configuration.entity.enums;

import com.baomidou.mybatisplus.annotation.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * TODO 项目类型值类型枚举
 * 代码生成器
 *
 * @author lijh
 * @className ProjectTypeValueStateEnum
 * @time 2022-05-05 02:45:25
 */
@Getter
@AllArgsConstructor
public enum ProjectTypeValueStateEnum implements IEnum<Integer> {
    // 默认
    默认(0, "默认"),
    ;

    private Integer value;
    private String desc;


    /**
     * TODO 通过value获得枚举
     *
     * @param value
     * @return ProjectTypeValueStateEnum
     * @author lijh
     * @methodName getProjectTypeValueStateEnum
     * @time 2022-05-05 02:45:25
     */
    public static ProjectTypeValueStateEnum getProjectTypeValueStateEnum(Integer value) {
        for (ProjectTypeValueStateEnum projectTypeValueStateEnum : ProjectTypeValueStateEnum.values()) {
            if (projectTypeValueStateEnum.getValue().equals(value)) {
                return projectTypeValueStateEnum;
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
        for (ProjectTypeValueStateEnum projectTypeValueStateEnum : ProjectTypeValueStateEnum.values()) {
            if (projectTypeValueStateEnum.getValue().equals(value)) {
                return projectTypeValueStateEnum.getDesc();
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
        for (ProjectTypeValueStateEnum projectTypeValueStateEnum : ProjectTypeValueStateEnum.values()) {
            if (projectTypeValueStateEnum.getDesc().equals(desc)) {
                return projectTypeValueStateEnum.getValue();
            }
        }
        return null;
    }
}