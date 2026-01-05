package com.gb.promoteform.entity.enums;

import com.baomidou.mybatisplus.annotation.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * TODO 推广表单微信类型枚举
 * 代码生成器
 *
 * @author lijh
 * @className PromoteFormWechatTypeEnum
 * @time 2022-07-04 10:49:05
 */
@Getter
@AllArgsConstructor
public enum PromoteFormWechatTypeEnum implements IEnum<Integer> {
    // 默认
    默认(0, "默认"),
    ;

    private Integer value;
    private String desc;


    /**
     * TODO 通过value获得枚举
     *
     * @param value
     * @return PromoteFormWechatTypeEnum
     * @author lijh
     * @methodName getPromoteFormWechatTypeEnum
     * @time 2022-07-04 10:49:05
     */
    public static PromoteFormWechatTypeEnum getPromoteFormWechatTypeEnum(Integer value) {
        for (PromoteFormWechatTypeEnum promoteFormWechatTypeEnum : PromoteFormWechatTypeEnum.values()) {
            if (promoteFormWechatTypeEnum.getValue().equals(value)) {
                return promoteFormWechatTypeEnum;
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
        for (PromoteFormWechatTypeEnum promoteFormWechatTypeEnum : PromoteFormWechatTypeEnum.values()) {
            if (promoteFormWechatTypeEnum.getValue().equals(value)) {
                return promoteFormWechatTypeEnum.getDesc();
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
        for (PromoteFormWechatTypeEnum promoteFormWechatTypeEnum : PromoteFormWechatTypeEnum.values()) {
            if (promoteFormWechatTypeEnum.getDesc().equals(desc)) {
                return promoteFormWechatTypeEnum.getValue();
            }
        }
        return null;
    }
}