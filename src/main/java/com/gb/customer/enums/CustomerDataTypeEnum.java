package com.gb.customer.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 客户线索数据标签
 *
 * @Auther lijh
 * @Date 2021/6/7
 */
@Getter
@AllArgsConstructor
@SuppressWarnings("all")
public enum CustomerDataTypeEnum {
    DATA_TYPE_0(0, "系统", "客户线索列表", "待认证"),
    DATA_TYPE_1(1, "手动", "客户管理", "认证成功"),
    DATA_TYPE_2(2, "投保", "经纪人管理", "认证失败"),
    DATA_TYPE_3(3, "", "经纪人线索列表", "认证关闭");

    /**
     * 码值
     */
    private Integer code;

    /**
     * 含义
     */
    private String desc;

    /**
     * 分配管家来源位置
     */
    private String other;

    /**
     * 经纪人认证状态
     */
    private String agentCertificationState;


}
