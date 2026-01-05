package com.gb.rpc.entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ljh
 * @date 2021/12/7 1:59 下午
 */
@Data
public class UserInfoVo {

    private List<RoleVO> roleList;


    @Data
    public static class RoleVO {

        /**
         * 序列
         */
        private String id;

        /**
         * 系统id
         */
        private String systemId;

        /**
         * 名称
         */
        private String name;

        /**
         * 角色值
         */
        private String value;

        private LocalDateTime createDateTime;

        /**
         * 删除状态
         */
        private Boolean isDelete;

        /**
         * 系统名称
         */
        private String systemName;
    }

    public List<String> getRoleSystemIdList() {
        return roleList.stream().map(RoleVO::getSystemId).collect(Collectors.toList());
    }
}
