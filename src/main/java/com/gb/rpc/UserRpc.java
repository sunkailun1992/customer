package com.gb.rpc;

import com.gb.rpc.impl.UserRpcImp;
import com.gb.utils.Json;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;
import java.util.Optional;

/**
 * Created with IntelliJ IDEA.
 *
 * @author sunkailun
 * @DateTime 2020/1/8  11:04 上午
 * @email 376253703@qq.com
 * @phone 13777579028
 * @explain
 */
@FeignClient(value = "user", fallbackFactory = UserRpcImp.class)
public interface UserRpc {

    /**
     * 根据标签code查询用户组信息
     *
     * @param map
     * @return
     */
    @RequestMapping(value = "/userQuery/queryLabelInUserByTypeParams", method = RequestMethod.GET)
    Optional<Json> queryLabelInUserByTypeParams(@RequestParam Map<String, Object> map);

    /**
     * 用户类型值表集合查询，根据userid查询
     *
     * @param map
     * @return
     */
    @RequestMapping(value = "/userTypeValue/selectList", method = RequestMethod.GET)
    Optional<Json> queryUserGroupByUserId(@RequestParam Map<String, Object> map);

    /**
     * 根据用户唯一标识查用户信息(userId)
     *
     * @param map userId
     * @return
     */
    @RequestMapping(value = "/userExtends/selectOne", method = RequestMethod.GET)
    Optional<Json> queryUserInfoByUserId(@RequestParam Map<String, Object> map);

    /**
     * 查询用户信息单条
     *
     * @param map
     * @return
     */
    @RequestMapping(value = "/user/selectOne", method = RequestMethod.GET)
    Optional<Json> getOneUserInfo(@RequestParam Map<String, Object> map);

    /**
     * 根据用户唯一标志获取经纪人认证信息
     *
     * @param map
     * @return
     */
    @RequestMapping(value = "/user-agent-certification/selectOne", method = RequestMethod.GET)
    Optional<Json> selectOneAgentCertificationByUserId(@RequestParam Map<String, Object> map);

    /**
     * 保存用户消息
     *
     * @param map
     * @return
     */
    @RequestMapping(value = "/user-message/save", method = RequestMethod.POST)
    Optional<Json> saveUserMessage(@RequestParam Map<String, Object> map);

    /**
     * 查询地区绑定的标签
     *
     * @param map 地区code
     * @return
     */
    @RequestMapping(value = "/user-type-value-region/selectOne", method = RequestMethod.GET)
    Optional<Json> selectOneRegionLabel(@RequestParam Map<String, Object> map);

    /**
     * 用户发送验证码
     *
     * @param map
     * @param headerMap
     * @return
     */
    @RequestMapping(value = "/sendCode", method = RequestMethod.POST)
    Optional<Json> sendUserCode(@RequestParam Map<String, Object> map, @RequestHeader Map<String, String> headerMap);

    /**
     * 快速登录
     *
     * @param map
     * @param headerMap
     * @return
     */
    @RequestMapping(value = "/quickLogin", method = RequestMethod.POST)
    Optional<Json> userQuickLogin(@RequestParam Map<String, Object> map, @RequestHeader Map<String, String> headerMap);

    /**
     * 用户发票表集合查询
     *
     * @param map
     * @return
     */
    @RequestMapping(value = "/user-invoice/select", method = RequestMethod.GET)
    Optional<Json> getUserInvoice(@RequestParam Map<String, Object> map);

    /**
     * 用户收货地址集合查询
     *
     * @param map
     * @return
     */
    @RequestMapping(value = "/user-shipping-address/select", method = RequestMethod.GET)
    Optional<Json> getUserShippingAddress(@RequestParam Map<String, Object> map);

    /**
     * 查询用户信息集合
     * nameQuery ：名称模糊查询
     *
     * @param map
     * @return
     */
    @RequestMapping(value = "/user/selectList", method = RequestMethod.GET)
    Optional<Json> getUserList(@RequestParam Map<String, Object> map);

    /**
     * 获取团队人员信息
     *
     * @param map 人员id
     * @return
     */
    @RequestMapping(value = "/team-user/selectOne", method = RequestMethod.GET)
    Optional<Json> getTeamUser(@RequestParam Map<String, Object> map);

    /**
     * 用户扩展信息集合
     *
     * @param map
     * @return
     */
    @RequestMapping(value = "/userExtends/selectList", method = RequestMethod.GET)
    Optional<Json> getUserExtendsList(@RequestParam Map<String, Object> map);

    /**
     * 获取父级团队
     *
     * @param map
     * @return
     */
    @RequestMapping(value = "/team/queryParentTeam", method = RequestMethod.GET)
    Optional<Json> getParentTeam(@RequestParam Map<String, Object> map);

    /**
     * 获取用户权限团队下的 所有经纪人
     *
     * @param map
     * @return
     */
    @RequestMapping(value = "/team-user/teamAuthBrokerSelectList", method = RequestMethod.GET)
    Optional<Json> getTeamAuthBrokerList(@RequestParam Map<String, Object> map);

    /**
     * 获取外部系统平台list
     *
     * @param map
     * @return
     */
    @RequestMapping(value = "/transformation-external-platform-system/selectList", method = RequestMethod.GET)
    Optional<Json> getPlatformSystemList(@RequestParam Map<String, Object> map);
}
