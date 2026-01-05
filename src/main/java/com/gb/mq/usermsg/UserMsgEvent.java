package com.gb.mq.usermsg;

import lombok.Data;

/**
 * @author ljh
 * @date 2022/6/2 2:54 下午
 */
@Data
public class UserMsgEvent {

    /**
     * 消息内容
     */
    private String content;
    /**
     * 用户id(username)
     */
    private String userId;

    /**
     * 消息类型
     */
    private Integer msgType;

    /**
     * 用户类型
     */
    private Integer userType;

    /**
     * 产生时间
     */
    private String createDate;

}
