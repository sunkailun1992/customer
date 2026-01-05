package com.gb.rpc.component;

import com.gb.rpc.entity.*;
import com.gb.rpc.entity.enums.RpcTypeEnum;
import com.gb.rpc.impl.RpcComponent;
import com.gb.utils.exception.BusinessException;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ljh
 * @date 2022/9/6 4:02 PM
 */
@Component
@Slf4j
@Setter(onMethod_ = {@Autowired})
public class UserComponent {

    private RpcComponent rpcComponent;


    /**
     * 根据userid 获取团队成员信息
     *
     * @param userId
     * @return
     */
    public TeamUserVO getTeamUserVOInfo(String userId) {
        Map<String, Object> teamUserMap = new HashMap<>(1);
        teamUserMap.put("userId", userId);
        return rpcComponent.rpcQueryInfo(teamUserMap, RpcTypeEnum.GET_TEAM_USER, TeamUserVO.class);
    }

    /**
     * 根据userid 获取团队成员信息   无成员返回异常
     *
     * @param userId
     * @return
     */
    public TeamUserVO getTeamUserVO(String userId) {
        TeamUserVO teamUserVO = getTeamUserVOInfo(userId);
        //未配置团队人员  不返回经纪人
        if (StringUtils.isEmpty(teamUserVO.getUserId())) {
            throw new BusinessException("此经纪人不属于团队成员!");
        }
        return teamUserVO;
    }

    /**
     * 根据手机号获取用户信息
     *
     * @param mobile
     * @return
     */
    public UserVO getOneUserByMobile(String mobile) {
        Map<String, Object> map = new HashMap<>(1);
        map.put("mobile", mobile);
        return rpcComponent.rpcQueryInfo(map, RpcTypeEnum.GET_ONE_USER, UserVO.class);
    }

    /**
     * 根据登录账号获取用户信息
     *
     * @param userName
     * @return
     */
    public UserVO getOneUserByUserName(String userName) {
        Map<String, Object> map = new HashMap<>(1);
        map.put("userName", userName);
        return rpcComponent.rpcQueryInfo(map, RpcTypeEnum.GET_ONE_USER, UserVO.class);
    }

    /**
     * 根据用户id获取用户信息
     *
     * @param userId
     * @return
     */
    public UserVO getOneUserByUserId(String userId) {
        Map<String, Object> map = new HashMap<>(1);
        map.put("id", userId);
        return rpcComponent.rpcQueryInfo(map, RpcTypeEnum.GET_ONE_USER, UserVO.class);
    }

    /**
     * 根据用户手机号 获取用户扩展信息
     *
     * @param mobile
     * @return
     */
    public UserExtendsVO getUserExtendsVOByMobile(String mobile) {
        Map<String, Object> map = new HashMap<>(1);
        map.put("mobile", mobile);
        return rpcComponent.rpcQueryInfo(map, RpcTypeEnum.GET_USER_EXTENDS_ONE, UserExtendsVO.class);
    }

    /**
     * 根据用户id 获取用户扩展信息
     *
     * @param userId
     * @return
     */
    public UserExtendsVO getUserExtendsVOByUserId(String userId) {
        Map<String, Object> map = new HashMap<>(1);
        map.put("userId", userId);
        return rpcComponent.rpcQueryInfo(map, RpcTypeEnum.GET_USER_EXTENDS_ONE, UserExtendsVO.class);
    }

    /**
     * 父级团队查询
     *
     * @param teamId
     * @return
     */
    public TeamTreeNode getParentTeam(String teamId) {
        Map<String, Object> map = new HashMap<>(1);
        map.put("teamId", teamId);
        return rpcComponent.rpcQueryInfo(map, RpcTypeEnum.GET_PARENT_TEAM, TeamTreeNode.class);
    }

    /**
     * 获取外部系统平台集合
     *
     * @return
     */
    public List<TransformationExternalPlatformSystemVO> getPlatformSystemList() {
        HashMap<String, Object> hashMap = new HashMap<>(1);
        hashMap.put("assignment", Boolean.TRUE);
        //获取经纪人权限下的所有经纪人
        return rpcComponent.rpcQueryList(hashMap, null, RpcTypeEnum.GET_PLATFORM_SYSTEM_LIST, TransformationExternalPlatformSystemVO.class);
    }

    /**
     * 根据用户id集合 获取用户信息集合
     *
     * @param userIds
     * @return
     */
    public List<UserExtendsVO> getUserExtendsListByIds(List<String> userIds) {
        HashMap<String, Object> hashMap = new HashMap<>(1);
        hashMap.put("userIdList", userIds);
        //获取经纪人权限下的所有经纪人
        return rpcComponent.rpcQueryList(hashMap, null, RpcTypeEnum.GET_USER_EXTENDS_LIST, UserExtendsVO.class);
    }

}
