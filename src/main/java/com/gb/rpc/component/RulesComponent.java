package com.gb.rpc.component;

import com.gb.customer.enums.CustomerDataTypeEnum;
import com.gb.rpc.entity.UserGroupInfo;
import com.gb.rpc.entity.enums.RpcTypeEnum;
import com.gb.rpc.impl.RpcComponent;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ljh
 * @date 2022/9/9 9:44 AM
 */
@Component
@Slf4j
@Setter(onMethod_ = {@Autowired})
public class RulesComponent {

    private RpcComponent rpcComponent;

    /**
     * 根据组id获取默认分配规则   查所有经纪人
     *
     * @param groupId
     * @return
     */
    public UserGroupInfo getUserGroupInfo(String groupId) {
        Map<String, Object> map = new HashMap<>(2);
        // queryType=0 ：所有管家
        map.put("queryType", CustomerDataTypeEnum.DATA_TYPE_0.getCode());
        map.put("groupId", groupId);
        return rpcComponent.rpcQueryInfo(map, RpcTypeEnum.GET_USER_GROUP_CONDITIONS, UserGroupInfo.class);
    }
}
