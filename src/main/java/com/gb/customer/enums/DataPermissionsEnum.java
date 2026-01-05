package com.gb.customer.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 接口权限枚举
 *
 * @Auther lijh
 * @Date 2021/8/17
 */
@Getter
@AllArgsConstructor
@SuppressWarnings("all")
public enum DataPermissionsEnum {

    AGENT_PEOPLE_SELECT("agent_people_select", "经纪人集合查询"),
    CUSTOMER_CAST_INSURANCE_SELECT("customer_cast_insurance_select", "客户投保订单关联集合查询"),
    CUSTOMER_SELECT("customer_select", "客户表集合查询"),
    CUSTOMER_ENTERPRISE_SELECT("customer_enterprise_select", "客户企业集合查询"),
    POTENTIAL_CUSTOMEER_SELECT_0("potential_customer_select_0", "潜在客户表集合查询-客户线索"),
    POTENTIAL_CUSTOMEER_SELECT_1("potential_customer_select_1", "潜在客户表集合查询-经纪人线索"),
    POTENTIAL_CUSTOMEER_GETSTEWARDLIST("potential_customer_getStewardList", "获取管家列表"),
    POTENTIAL_CUSTOMEER_QUERYSTEWARDBYPAGE("potential_customer_queryStewardByPage", "管家分页列表");

    /**
     * 码值
     */
    private String code;

    /**
     * 含义
     */
    private String desc;

}
