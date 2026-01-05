package com.gb.rpc.entity.enums;

import com.baomidou.mybatisplus.annotation.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * TODO 团队人员常规状态枚举
 * 代码生成器
 *
 * @author sunx
 * @className TeamUserStateEnum
 * @time 2022-08-31 11:01:59
 */
@Getter
@AllArgsConstructor
public enum TeamUserFormalStateEnum implements IEnum<Integer> {
    // 默认
    在职(0, "在职"),

    注销(1, "注销"),

    离职(2, "离职"),

    修改(3, "修改"),
    ;

    private Integer value;
    private String desc;


    /**
     * TODO 通过value获得枚举
     *
     * @param value
     * @return TeamUserStateEnum
     * @author sunx
     * @methodName getTeamUserStateEnum
     * @time 2022-08-31 11:01:59
     */
    public static TeamUserFormalStateEnum getTeamUserStateEnum(Integer value) {
        for (TeamUserFormalStateEnum teamUserStateEnum : TeamUserFormalStateEnum.values()) {
            if (teamUserStateEnum.getValue().equals(value)) {
                return teamUserStateEnum;
            }
        }
        return null;
    }


    /**
     * TODO 获得备注
     *
     * @param value
     * @return String
     * @author sunx
     * @methodName getDesc
     * @time 2022-08-31 11:01:59
     */
    public static String getDesc(Integer value) {
        for (TeamUserFormalStateEnum teamUserStateEnum : TeamUserFormalStateEnum.values()) {
            if (teamUserStateEnum.getValue().equals(value)) {
                return teamUserStateEnum.getDesc();
            }
        }
        return null;
    }


    /**
     * TODO 获得值
     *
     * @param desc
     * @return Integer
     * @author sunx
     * @methodName getDesc
     * @time 2022-08-31 11:01:59
     */
    public static Integer getDesc(String desc) {
        for (TeamUserFormalStateEnum teamUserStateEnum : TeamUserFormalStateEnum.values()) {
            if (teamUserStateEnum.getDesc().equals(desc)) {
                return teamUserStateEnum.getValue();
            }
        }
        return null;
    }
}