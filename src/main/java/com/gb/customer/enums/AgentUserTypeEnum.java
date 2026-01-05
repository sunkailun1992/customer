package com.gb.customer.enums;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.gb.utils.enumeration.UserTypeEnum;
import com.gb.utils.exception.BusinessException;
import com.gb.utils.exception.ParameterNullException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * 经纪人类型
 *
 * @author ljh
 * @date 2021/10/26 9:37 上午
 */
@Getter
@AllArgsConstructor
@SuppressWarnings("all")
public enum AgentUserTypeEnum {

    AGENT_PEOPLE_TYPE_0(0, "业务管家", UserTypeEnum.业务管家.getTypeCode()),
    //需生成经纪人信息的统称外部经纪人,获取业务管家需查经纪人表绑定的管家
    AGENT_PEOPLE_TYPE_1(1, "外部经纪人", "1"),
    AGENT_PEOPLE_TYPE_2(2, "渠道经纪人", UserTypeEnum.渠道经纪人.getTypeCode()),
    AGENT_PEOPLE_TYPE_3(3, "业务助理", UserTypeEnum.业务助理管家.getTypeCode()),
    AGENT_PEOPLE_TYPE_4(4, "特别经纪人", UserTypeEnum.特别业务管家.getTypeCode());

    /**
     * 码值
     */
    private Integer code;

    /**
     * 含义
     */
    private String desc;

    private String groupId;

    public static AgentUserTypeEnum getAgentUserTypeEnum(Integer code) {
        if (null == code) {
            throw new BusinessException("经纪人类型code为空!");
        }
        Optional<AgentUserTypeEnum> codeEnum = Arrays.stream(values()).filter(x -> code.equals(x.getCode())).findFirst();
        return codeEnum.orElseThrow(() -> new ParameterNullException("经纪人类型不存在!"));
    }

    /**
     * 根据组id 获取内部定义经纪人类型
     *
     * @param groupId
     * @return
     */
    public static AgentUserTypeEnum getAgentUserTypeEnumByGroupId(String groupId) {
        if (StringUtils.isEmpty(groupId)) {
            throw new BusinessException("管家组id为空!");
        }
        if (UserTypeEnum.业务管家.getTypeCode().equals(groupId)) {
            return AgentUserTypeEnum.AGENT_PEOPLE_TYPE_0;
        }
        if (UserTypeEnum.渠道经纪人.getTypeCode().equals(groupId)) {
            return AgentUserTypeEnum.AGENT_PEOPLE_TYPE_2;
        }
        if (UserTypeEnum.业务助理管家.getTypeCode().equals(groupId)) {
            return AgentUserTypeEnum.AGENT_PEOPLE_TYPE_3;
        }
        if (UserTypeEnum.特别业务管家.getTypeCode().equals(groupId)) {
            return AgentUserTypeEnum.AGENT_PEOPLE_TYPE_4;
        }
        return AgentUserTypeEnum.AGENT_PEOPLE_TYPE_1;
    }

    /**
     * 产品说了：业务管家 -> 经纪人 -> 业务助理 -> 业务管家
     * 根据用户所有组匹配 内部定义经纪人类型
     *
     * @param userPermissions
     * @return
     */
    public static AgentUserTypeEnum getAgentUserTypeByGroups(List<String> userPermissions) {
        if (CollectionUtils.isEmpty(userPermissions)) {
            throw new BusinessException("管家组id为空!");
        }
        if (userPermissions.contains(UserTypeEnum.业务管家.getTypeCode())) {
            return AgentUserTypeEnum.AGENT_PEOPLE_TYPE_0;
        }
        if (userPermissions.contains(UserTypeEnum.经纪人.getTypeCode())) {
            return AgentUserTypeEnum.AGENT_PEOPLE_TYPE_1;
        }
        if (userPermissions.contains(UserTypeEnum.业务助理管家.getTypeCode())) {
            return AgentUserTypeEnum.AGENT_PEOPLE_TYPE_3;
        }
        return AgentUserTypeEnum.AGENT_PEOPLE_TYPE_0;
    }

}
