package com.gb.customer.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 客户来源
 *
 * @Auther lijh
 * @Date 2021/6/7
 */
@Getter
@AllArgsConstructor
@SuppressWarnings("all")
public enum SourceTypeEnum {
    /**
     * 线索来源
     */
    SOURCE_TYPE_1("1", "工保网"),
    SOURCE_TYPE_15("15", "无来源"),
    SOURCE_TYPE_16("16", "BI系统"),
    SOURCE_TYPE_17("17", "手动添加"),
    SOURCE_TYPE_2("2", "内荐"),

    /**
     * 客户来源
     */
    分销添加("1565239500969308162", "分销添加"),
    自营添加("1565239545290518530", "自营添加"),
    公海添加("1565239580652695553", "公海添加"),
    BI系统("1565239621727514626", "BI系统"),
    七陌云("1565239648621391873", "七陌云"),
    客户注册("1565239678057017345", "客户注册");

    /**
     * 码值
     */
    private String code;

    /**
     * 含义
     */
    private String desc;

}
