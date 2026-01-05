package com.gb.customer.service.results;

import cn.hutool.core.convert.Convert;
import com.gb.customer.entity.AssistantAssociated;
import com.gb.customer.entity.enums.AssistantAssociatedTypeEnum;
import com.gb.customer.entity.vo.AssistantAssociatedVO;
import com.gb.customer.entity.bo.AssistantAssociatedBO;
import com.gb.rpc.UserRpc;
import com.gb.rpc.entity.UserExtendsVO;
import com.gb.rpc.entity.enums.RpcTypeEnum;
import com.gb.rpc.impl.RpcComponent;
import com.gb.utils.Json;
import com.gb.utils.exception.BusinessException;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import com.gb.utils.GeneralConvertor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;

import java.util.*;


/**
 * Created with IntelliJ IDEA.
 *
 * @author: lijh
 * @since: 2021-11-02 10:03:07
 * @description: TODO 助理关联,Service返回实现
 * @source: 代码生成器
 */
@Slf4j
@Service
@Setter(onMethod_ = {@Autowired})
public class AssistantAssociatedServiceResults {

    private UserRpc userRpc;

    private RpcComponent rpcComponent;


    /**
     * 单条，增强返回参数追加
     *
     * @param assistantAssociatedVO 助理关联
     * @return AssistantAssociatedVO
     * @author lijh
     * @since 2021-11-02 10:03:07
     */
    public AssistantAssociatedVO assignment(AssistantAssociatedVO assistantAssociatedVO) {
        if (assistantAssociatedVO != null) {
            return assistantAssociatedVO;
        } else {
            return assistantAssociatedVO;
        }
    }


    /**
     * 分页，增强返回参数追加
     *
     * @param assistantAssociatedVOList 助理关联
     * @return Page<AssistantAssociatedVO>
     * @author lijh
     * @since 2021-11-02 10:03:07
     */
    public Page<AssistantAssociatedVO> assignment(Page<AssistantAssociatedVO> assistantAssociatedVOList) {
        assistantAssociatedVOList.getRecords().forEach(assistantAssociatedVO -> {
            try {
                //获取助理信息
                Map<String, Object> map = new HashMap<>(1);
                map.put("userId", assistantAssociatedVO.getAssistantId());
                UserExtendsVO userExtendsVO = rpcComponent.rpcQueryInfo(map, RpcTypeEnum.GET_USER_EXTENDS_ONE, UserExtendsVO.class);
                log.info("---请求用户中心接口获取助理信息={}", userExtendsVO);
                assistantAssociatedVO.setAssistantPhone(Convert.toStr(userExtendsVO.getMobile()));
                assistantAssociatedVO.setAssistantRealName(Convert.toStr(userExtendsVO.getName()));
                //获取管理信息
                map.put("userId", assistantAssociatedVO.getHousekeeperId());
                UserExtendsVO housekeeperExtendsVO = rpcComponent.rpcQueryInfo(map, RpcTypeEnum.GET_USER_EXTENDS_ONE, UserExtendsVO.class);
                log.info("---请求用户中心接口获取管理信息={}", housekeeperExtendsVO);
                assistantAssociatedVO.setHousekeeperPhone(Convert.toStr(housekeeperExtendsVO.getMobile()));
                assistantAssociatedVO.setHousekeeperRealName(Convert.toStr(housekeeperExtendsVO.getName()));
            } catch (Exception e) {
                log.info("获取用户信息有异常抛出，可能是未获取到。");
            }
        });
        return assistantAssociatedVOList;
    }


    /**
     * 集合，增强返回参数追加
     *
     * @param assistantAssociatedVOList 助理关联
     * @return List<AssistantAssociatedVO>
     * @author lijh
     * @since 2021-11-02 10:03:07
     */
    public List<AssistantAssociatedVO> assignment(List<AssistantAssociatedVO> assistantAssociatedVOList) {
        assistantAssociatedVOList.forEach(assistantAssociatedVO -> {
            try {
                //获取助理信息
                Map<String, Object> map = new HashMap<>(1);
                map.put("userId", assistantAssociatedVO.getAssistantId());
                UserExtendsVO userExtendsVO = rpcComponent.rpcQueryInfo(map, RpcTypeEnum.GET_USER_EXTENDS_ONE, UserExtendsVO.class);
                log.info("---请求用户中心接口获取助理信息={}", userExtendsVO);
                assistantAssociatedVO.setAssistantPhone(Convert.toStr(userExtendsVO.getMobile()));
                assistantAssociatedVO.setAssistantRealName(Convert.toStr(userExtendsVO.getName()));
            } catch (Exception e) {
                log.info("获取用户信息有异常抛出，可能是未获取到。");
            }
        });
        return assistantAssociatedVOList;
    }


    /**
     * DO转化VO
     *
     * @param pageDO 助理关联
     * @return com.baomidou.mybatisplus.extension.plugins.pagination.Page
     * @author lijh
     * @since 2021-11-02 10:03:07
     */
    public Page<AssistantAssociatedVO> toPageVO(Page<AssistantAssociated> pageDO) {
        Page<AssistantAssociatedVO> pageVO = new Page<AssistantAssociatedVO>();
        pageVO.setRecords(GeneralConvertor.convertor(pageDO.getRecords(), AssistantAssociatedVO.class));
        pageVO.setCurrent(pageDO.getCurrent());
        pageVO.setPages(pageDO.getPages());
        pageVO.setSize(pageDO.getSize());
        pageVO.setTotal(pageDO.getTotal());
        return pageVO;
    }


}