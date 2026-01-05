package com.gb.customer.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.gb.customer.entity.PotentialCustomer;
import com.gb.customer.enums.PotentialCustomerTypeEnum;
import com.gb.customer.enums.DataPermissionsEnum;
import com.gb.rpc.component.UserComponent;
import com.gb.rpc.entity.TeamGroupUserVO;
import com.gb.rpc.entity.TeamUserVO;
import com.gb.rpc.entity.UserTypeValueVO;
import com.gb.rpc.entity.enums.RpcTypeEnum;
import com.gb.rpc.impl.RpcComponent;
import com.gb.utils.enumeration.UserTypeEnum;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author lijinhao
 * @Date 2021/6/21
 */
@Slf4j
@Service
@Setter(onMethod_ = {@Autowired})
public class CheckPermissions {

    private RpcComponent rpcComponent;

    private UserComponent userComponent;

    /**
     * 根据当前登录账号 获取团队权限下的所有经纪人id
     *
     * @param loginAccountId
     * @return
     */
    public List<String> getAgentTeam(String loginAccountId) {
        TeamUserVO teamUserVO = getTeamUserVoInfo(loginAccountId);
        return getAgentTeamByTeamUser(teamUserVO);
    }

    /**
     * 根据人员信息 获取团队权限下的所有经纪人id
     *
     * @param teamUserVO 人员信息
     * @return
     */
    public List<String> getAgentTeamByTeamUser(TeamUserVO teamUserVO) {
        List<String> list = new ArrayList<>();
        list.add(teamUserVO.getUserId());
        if (Objects.nonNull(teamUserVO.getPersonal()) && teamUserVO.getPersonal()) {
            return list;
        }
        HashMap<String, Object> hashMap = new HashMap<>(1);
        hashMap.put("authUserId", teamUserVO.getUserId());
        hashMap.put("backResultGroup", Boolean.TRUE);
        //获取经纪人权限下的所有经纪人
        List<TeamGroupUserVO> teamGroupUserVOList = rpcComponent.rpcQueryList(hashMap, null, RpcTypeEnum.GET_TEAM_AUTH_BROKER_LIST, TeamGroupUserVO.class);
        if (CollectionUtils.isEmpty(teamGroupUserVOList)) {
            return list;
        }
        //获取所有经纪人id
        for (TeamGroupUserVO teamGroupUserVO : teamGroupUserVOList) {
            if (CollectionUtils.isNotEmpty(teamGroupUserVO.getTeamUserList())) {
                for (TeamUserVO userVO : teamGroupUserVO.getTeamUserList()) {
                    list.add(userVO.getUserId());
                }
            }
        }
        log.debug("经纪人{} 权限团队下的经纪人={}", teamUserVO.getUserId(), list);
        return list;
    }


    /**
     * 根据登录信息获取当前用户所在用户组
     *
     * @param token 缓存
     * @return
     */
    public List<String> checkUserPermissions(Map<String, Object> token) {
        log.info("校验用户所在组,用户token=" + token);
        if (CollectionUtils.isEmpty(token)) {
            return new ArrayList<>();
        }
        //返回用户所在的所有组code
        return queryUserGroupByUserId(token.get("id").toString());
    }

    /**
     * 是否为外部经纪人
     *
     * @param userName
     * @return
     */
    public Boolean isExternalAgentPeople(String userName) {
        List<String> userPermissions = queryUserGroupByUserId(userName);
        if (CollectionUtils.isNotEmpty(userPermissions)) {
            if (userPermissions.contains(UserTypeEnum.业务管家.getTypeCode()) &&
                    userPermissions.contains(UserTypeEnum.业务助理管家.getTypeCode())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 校验数据操作权限
     *
     * @param dataPermissionsEnum 接口
     * @param groupCodes             所属组
     * @return
     */
    public Boolean checkDataPermissions(DataPermissionsEnum dataPermissionsEnum, List<String> groupCodes) {
        //为管理员组
        if (groupCodes.contains(UserTypeEnum.管理员.getTypeCode())) {
            return false;
        }
        switch (dataPermissionsEnum) {
            case AGENT_PEOPLE_SELECT:
                if (!groupCodes.contains(UserTypeEnum.业务管家主管.getTypeCode()) &&
                        !groupCodes.contains(UserTypeEnum.互联网管家主管.getTypeCode())) {
                    return true;
                }
            case CUSTOMER_CAST_INSURANCE_SELECT:
                if (!groupCodes.contains(UserTypeEnum.业务管家主管.getTypeCode()) &&
                        !groupCodes.contains(UserTypeEnum.服务管家主管.getTypeCode()) &&
                        !groupCodes.contains(UserTypeEnum.互联网管家主管.getTypeCode())) {
                    return true;
                }
            case CUSTOMER_SELECT:
                if (!groupCodes.contains(UserTypeEnum.业务管家主管.getTypeCode()) &&
                        !groupCodes.contains(UserTypeEnum.商务管家主管.getTypeCode()) &&
                        !groupCodes.contains(UserTypeEnum.互联网管家主管.getTypeCode())) {
                    return true;
                }
            case CUSTOMER_ENTERPRISE_SELECT:
                if (!groupCodes.contains(UserTypeEnum.业务管家主管.getTypeCode()) &
                        !groupCodes.contains(UserTypeEnum.互联网管家主管.getTypeCode())) {
                    return true;
                }
            case POTENTIAL_CUSTOMEER_SELECT_0:
                if (!groupCodes.contains(UserTypeEnum.业务管家主管.getTypeCode()) &&
                        !groupCodes.contains(UserTypeEnum.商务管家主管.getTypeCode()) &&
                        !groupCodes.contains(UserTypeEnum.互联网管家主管.getTypeCode())) {
                    return true;
                }
            case POTENTIAL_CUSTOMEER_SELECT_1:
                if (!groupCodes.contains(UserTypeEnum.业务管家主管.getTypeCode()) &&
                        !groupCodes.contains(UserTypeEnum.互联网管家主管.getTypeCode())) {
                    return true;
                }
            case POTENTIAL_CUSTOMEER_GETSTEWARDLIST:
                if (!groupCodes.contains(UserTypeEnum.业务管家主管.getTypeCode()) &&
                        !groupCodes.contains(UserTypeEnum.服务管家主管.getTypeCode()) &&
                        !groupCodes.contains(UserTypeEnum.互联网管家主管.getTypeCode()) &&
                        !groupCodes.contains(UserTypeEnum.商务管家主管.getTypeCode())) {
                    return true;
                }
            case POTENTIAL_CUSTOMEER_QUERYSTEWARDBYPAGE:
                if (!groupCodes.contains(UserTypeEnum.业务管家主管.getTypeCode()) &&
                        !groupCodes.contains(UserTypeEnum.服务管家主管.getTypeCode()) &&
                        !groupCodes.contains(UserTypeEnum.互联网管家主管.getTypeCode()) &&
                        !groupCodes.contains(UserTypeEnum.商务管家主管.getTypeCode())) {
                    return true;
                }
            default:
                break;
        }
        return false;
    }

    /**
     * 用户预约。邀请人不能是用户。
     * 经纪人预约。邀请人只能是业务管家
     *
     * @param potentialCustomer
     * @return
     */
    public Boolean checkUser(PotentialCustomer potentialCustomer) {
        //邀请人的标签
        List<String> groupCodeList = queryUserGroupByUserId(potentialCustomer.getAgentUserId());
        log.debug("[校验用户身份]groupCodeList={}", groupCodeList);
        //用户预约
        if (PotentialCustomerTypeEnum.CUSTOMER_TYPE_0.getCode().equals(potentialCustomer.getType())) {
            return groupCodeList.size() == 1 && groupCodeList.contains(UserTypeEnum.前端用户.getTypeCode());
        }
        //经纪人预约
        return !groupCodeList.contains(UserTypeEnum.业务管家.getTypeCode());
    }

    /**
     * 根据用户唯一标识获取所在的所有用户组
     *
     * @param userId 唯一标识
     * @return
     */
    public List<String> queryUserGroupByUserId(String userId) {
        HashMap<String, Object> hashMap = new HashMap<>(1);
        hashMap.put("userId", userId);
        List<UserTypeValueVO> userTypeValueVOList = rpcComponent.rpcQueryList(hashMap, null, RpcTypeEnum.GET_USER_GROUP_BY_USERID, UserTypeValueVO.class);
        log.info("RPC获取用户{}所在组的信息为={}", userId, userTypeValueVOList);
        //返回用户所在的所有组code
        return userTypeValueVOList.stream().map(UserTypeValueVO::getCode).collect(Collectors.toList());
    }


    /**
     * RPC获取   根据用户id获取人员信息
     *
     * @param userId 用户id
     * @return
     */
    public TeamUserVO getTeamUserVoInfo(String userId) {
        return userComponent.getTeamUserVO(userId);
    }
}
