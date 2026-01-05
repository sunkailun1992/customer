package com.gb.rpc.entity.enums;

import com.gb.utils.exception.ParameterNullException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Optional;

/**
 * 管家枚举类
 * @author: lijh
 * @descript:
 */
@Getter
@AllArgsConstructor
@SuppressWarnings("all")
public enum GroupIdEnum {
    BUSINESS_STEWARD_214("214", "业务管家"),
    SERVICE_STEWARD_215("215", "服务管家"),
    INTERNET_STEWARD_216("216", "互联网管家"),
    BUSINESS_STEWARD_217("227", "商户管家");

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
    public static GroupIdEnum getAppCodeEnum(String code) throws Exception {
        if(StringUtils.isBlank(code)){
            throw new ParameterNullException("平台编码不能为空!");
        }
        Optional<GroupIdEnum> codeEnum = Arrays.stream(values()).filter(x-> StringUtils.equals(code, x.getCode())).findFirst();
        return codeEnum.orElseThrow( ()-> new ParameterNullException("平台编码不存在!"));
    }
}
