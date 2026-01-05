package com.gb.promoteform.entity.enums;

import com.baomidou.mybatisplus.annotation.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * TODO 推广表单微信类型枚举
 * 代码生成器
 *
 * @author lijh
 * @className PromoteFormWechatStateEnum
 * @time 2022-07-04 10:49:05
 */
@Getter
@AllArgsConstructor
public enum PromoteFormWechatStateEnum implements IEnum<Integer> {
    // 默认
    默认(0, "默认"),
    ;

    private Integer value;
    private String desc;


    /**
     * TODO 通过value获得枚举
     *
     * @param value
     * @return PromoteFormWechatStateEnum
     * @author lijh
     * @methodName getPromoteFormWechatStateEnum
     * @time 2022-07-04 10:49:05
     */
    public static PromoteFormWechatStateEnum getPromoteFormWechatStateEnum(Integer value) {
        for (PromoteFormWechatStateEnum promoteFormWechatStateEnum : PromoteFormWechatStateEnum.values()) {
            if (promoteFormWechatStateEnum.getValue().equals(value)) {
                return promoteFormWechatStateEnum;
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
     * @time 2022-07-04 10:49:05
     */
    public static String getDesc(Integer value) {
        for (PromoteFormWechatStateEnum promoteFormWechatStateEnum : PromoteFormWechatStateEnum.values()) {
            if (promoteFormWechatStateEnum.getValue().equals(value)) {
                return promoteFormWechatStateEnum.getDesc();
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
     * @time 2022-07-04 10:49:05
     */
    public static Integer getDesc(String desc) {
        for (PromoteFormWechatStateEnum promoteFormWechatStateEnum : PromoteFormWechatStateEnum.values()) {
            if (promoteFormWechatStateEnum.getDesc().equals(desc)) {
                return promoteFormWechatStateEnum.getValue();
            }
        }
        return null;
    }
}