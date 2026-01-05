package com.gb.configuration.entity.enums;

import com.baomidou.mybatisplus.annotation.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * TODO 项目类型类型枚举
 * 代码生成器
 *
 * @author lijh
 * @className ProjectTypeStateEnum
 * @time 2022-05-05 02:45:25
 */
@Getter
@AllArgsConstructor
public enum ProjectTypeStateEnum implements IEnum<Integer> {
    // 默认
    默认(0, "默认"),
    ;

    private Integer value;
    private String desc;


    /**
     * TODO 通过value获得枚举
     *
     * @param value
     * @return ProjectTypeStateEnum
     * @author lijh
     * @methodName getProjectTypeStateEnum
     * @time 2022-05-05 02:45:25
     */
    public static ProjectTypeStateEnum getProjectTypeStateEnum(Integer value) {
        for (ProjectTypeStateEnum projectTypeStateEnum : ProjectTypeStateEnum.values()) {
            if (projectTypeStateEnum.getValue().equals(value)) {
                return projectTypeStateEnum;
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
        for (ProjectTypeStateEnum projectTypeStateEnum : ProjectTypeStateEnum.values()) {
            if (projectTypeStateEnum.getValue().equals(value)) {
                return projectTypeStateEnum.getDesc();
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
        for (ProjectTypeStateEnum projectTypeStateEnum : ProjectTypeStateEnum.values()) {
            if (projectTypeStateEnum.getDesc().equals(desc)) {
                return projectTypeStateEnum.getValue();
            }
        }
        return null;
    }
}