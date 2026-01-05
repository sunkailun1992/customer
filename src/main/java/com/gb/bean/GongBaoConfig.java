package com.gb.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 工保科技配置内容
 *
 * @author sunkailun
 * @DateTime 2021/3/17  11:04 上午
 * @email 376253703@qq.com
 * @phone 13777579028
 * @explain
 */
@Component
public class GongBaoConfig {
    /**
     * 咨询经纪人分组id。
     */
    public static String advisoryBrokerGroupId;

    /**
     * 服务名称
     */
    public static String serverName;
    /**
     * 险种分类id  险种分配判断去除，暂时用不到
     */
    public static String dangerPlantedCategoryId;
    /**
     * 险种id
     */
    public static String dangerPlantedId;

    /**
     * 投标险种分类id
     */
    public static String bidDangerPlantedCategoryId;
    /**
     * 投标险种分类id
     */
    public static String bidDangerPlantedId;

    /**
     * 前端域名地址
     */
    public static String frontDomainName;

    /**
     * 公海管理员id
     */
    public static String highSeasAdminUserId;

    /**
     * 消息通知默认用户id
     */
    public static String userMessageDefaultUserId;

    /**
     * 信息收集表单数组
     */
    public static String promoteFormIds;
    /**
     * 信息收集表单通知云地址
     */
    public static String promoteFormSendYunUrl;


    @Value("${gongbao.advisoryBrokerGroupId}")
    public void setAccessKeyId(String advisoryBrokerGroupId) {
        GongBaoConfig.advisoryBrokerGroupId = advisoryBrokerGroupId;
    }

    @Value("${spring.application.name}")
    public void setServerName(String serverName) {
        GongBaoConfig.serverName = serverName;
    }

    @Value("${gongbao.dangerPlantedCategoryId}")
    public void setDangerPlantedCategoryId(String dangerPlantedCategoryId) {
        GongBaoConfig.dangerPlantedCategoryId = dangerPlantedCategoryId;
    }

    @Value("${gongbao.dangerPlantedId}")
    public void setDangerPlantedId(String dangerPlantedId) {
        GongBaoConfig.dangerPlantedId = dangerPlantedId;
    }

    @Value("${gongbao.bidDangerPlantedCategoryId}")
    public void setBidDangerPlantedCategoryId(String bidDangerPlantedCategoryId) {
        GongBaoConfig.bidDangerPlantedCategoryId = bidDangerPlantedCategoryId;
    }

    @Value("${gongbao.bidDangerPlantedId}")
    public void setBidDangerPlantedId(String bidDangerPlantedId) {
        GongBaoConfig.bidDangerPlantedId = bidDangerPlantedId;
    }

    @Value("${gongbao.frontDomainName}")
    public void setFrontDomainName(String frontDomainName) {
        GongBaoConfig.frontDomainName = frontDomainName;
    }

    @Value("${gongbao.highSeasAdminUserId}")
    public void setHighSeasAdminUserId(String highSeasAdminUserId) {
        GongBaoConfig.highSeasAdminUserId = highSeasAdminUserId;
    }

    @Value("${gongbao.userMessageDefaultUserId}")
    public void setUserMessageDefaultUserId(String userMessageDefaultUserId) {
        GongBaoConfig.userMessageDefaultUserId = userMessageDefaultUserId;
    }

    @Value("${gongbao.promoteFormIds}")
    public void setPromoteFormIds(String promoteFormIds) {
        GongBaoConfig.promoteFormIds = promoteFormIds;
    }

    @Value("${gongbao.promoteFormSendYunUrl}")
    public void setPromoteFormSendYunUrl(String promoteFormSendYunUrl) {
        GongBaoConfig.promoteFormSendYunUrl = promoteFormSendYunUrl;
    }
}
