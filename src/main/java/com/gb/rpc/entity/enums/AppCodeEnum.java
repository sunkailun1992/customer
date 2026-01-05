package com.gb.rpc.entity.enums;

import com.gb.utils.exception.ParameterNullException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Optional;

/**
 * 系统编码枚举类
 * @author: ranyang
 * @Date: 2021/04/06 14:25
 * @descript:
 */
@Getter
@AllArgsConstructor
@SuppressWarnings("all")
public enum AppCodeEnum {
    NET_INS("net-ins", "工保前台保险公司"),
    NET_USER("net-user", "工保网前台普通用户"),
    NET_BACKEND("net-backend", "工保网后端管理端"),
    NET_AGENT("net-agent", "工保网前台经纪人"),
    NORMAL_USER("2", "普通用户"),
    AGENT("1", "经纪人"),
    BACKEND("net-backend", "后台"),
    INSURANCE_COMPANY("3", "保险公司用户");

    private String code;
    private String desc;

    /**
     * 根据平台编码获取平台枚举类
     *
     * @param code: 平台编码
     * @return AppCodeEnum
     * @author sunx
     * @since 2021/3/19  4:35 下午
     */
    public static AppCodeEnum getAppCodeEnum(String code) throws Exception {
        if(StringUtils.isBlank(code)){
            throw new ParameterNullException("平台编码不能为空!");
        }
        Optional<AppCodeEnum> codeEnum = Arrays.stream(values()).filter(x-> StringUtils.equals(code, x.getCode())).findFirst();
        return codeEnum.orElseThrow( ()-> new ParameterNullException("平台编码不存在!"));
    }
}
