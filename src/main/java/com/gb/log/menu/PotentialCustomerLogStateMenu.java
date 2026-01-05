package com.gb.log.menu;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: wangyifei
 * @Date: 2021/3/15 10:41
 * @descript:
 */
@Getter
public enum PotentialCustomerLogStateMenu {
    //状态（-1：取消，0：待处理，1：处理中，2：已完成，3：关闭）
    CANCEL(-1, "取消"),
    PENDING(0, "待处理"),
    BEING_PROCESSED(1, "处理中"),
    COMPLETED(2, "已完成"),
    CLOSE(3, "关闭");

    private static final Map<Integer, PotentialCustomerLogStateMenu> LOG_MAP;

    static {
        LOG_MAP = new HashMap<>(PotentialCustomerLogStateMenu.values().length);
        for (PotentialCustomerLogStateMenu menu : PotentialCustomerLogStateMenu.values()) {
            LOG_MAP.put(menu.code, menu);
        }
    }

    private Integer code;
    private String desc;

    PotentialCustomerLogStateMenu(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * 在类加载的时候就put到Map了，然后进行获取。
     *
     * @param code
     * @return
     */
    public static String getDesc(Integer code) {
        PotentialCustomerLogStateMenu menu = LOG_MAP.get(code);
        if (null == menu) {
            return null;
        }
        return menu.desc;
    }
}
