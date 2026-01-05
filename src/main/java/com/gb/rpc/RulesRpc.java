package com.gb.rpc;

import com.gb.rpc.entity.UserGroupInfo;
import com.gb.rpc.impl.RulesRpcImpl;
import com.gb.rpc.impl.UserRpcImp;
import com.gb.utils.Json;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;
import java.util.Optional;

/**
 * 规则模块
 * @author lijinhao
 */
@FeignClient(value = "rules",fallbackFactory = RulesRpcImpl.class)
public interface RulesRpc {

    /**
     * 查询默认分配规则
     *
     * @param map
     *      groupId 二级标签id
     *      queryType  0所有管家，  1已认证管家
     * @return
     */
    @PostMapping(value = "/rules/getUserGroupInfoByConditions")
    Optional<Json> getUserGroupInfoByConditions(@RequestParam Map<String, Object> map);
}
