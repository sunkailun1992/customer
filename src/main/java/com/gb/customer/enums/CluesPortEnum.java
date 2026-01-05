package com.gb.customer.enums;

import com.gb.utils.enumeration.UserTypeEnum;
import com.gb.utils.exception.BusinessException;
import com.gb.utils.exception.ParameterNullException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Optional;

/**
 * @author ljh
 * @date 2022/4/28 4:46 下午
 */
@Slf4j
@Getter
@AllArgsConstructor
@SuppressWarnings("all")
public enum CluesPortEnum {

    SHU_GUANG("shu_guang", "数广", UserTypeEnum.数广团队长.getTypeCode()),
    GU_WEN("gu_wen", "企业顾问服务", UserTypeEnum.企业顾问服务.getTypeCode()),
    公海列表("1", "公海客户列表", null),
    分销列表("2", "分销客户列表", null),
    自营列表("3", "自营客户列表", null),
    线索管理("4", "线索管理", null);
    /**
     * 码值
     */
    private String code;

    /**
     * 含义
     */
    private String desc;

    /**
     * 标签code
     */
    private String typeCode;

    /**
     * 根据码值获取
     *
     * @param code 自定义code
     * @return
     */
    public static CluesPortEnum getTypeCode(String code) {
        if (StringUtils.isBlank(code)) {
            throw new BusinessException("码值code为空!");
        }
        Optional<CluesPortEnum> codeEnum = Arrays.stream(values()).filter(x -> code.equals(x.getCode())).findFirst();
        return codeEnum.orElseThrow(() -> new ParameterNullException("标签code不存在!"));
    }
}
