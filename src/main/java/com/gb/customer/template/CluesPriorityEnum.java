package com.gb.customer.template;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author ljh
 * @date 2022/3/10 9:34 上午
 */
@Getter
@AllArgsConstructor
@SuppressWarnings("all")
public enum CluesPriorityEnum {

    BI("biSource.xml", "BI"),
    QI_MO_YUN("qiMoYunSource.xml", "七陌云"),
    GBW_WEB("websiteSource.xml", "工保网");

    private String value;
    private String sourceClass;

}