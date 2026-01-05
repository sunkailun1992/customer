package com.gb.rpc.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;


/**
 * TODO 团队人员渲染
 * 代码生成器
 *
 * @author sunx
 * @className TeamUserVO
 * @time 2022-08-31 11:01:59
 */
@Data
@ApiModel(value = "团队人员渲染")
public class TeamUserVO implements Serializable {

    @ApiModelProperty(value = "序列")
    private String id;

    @ApiModelProperty(value = "团队id")
    private String teamId;

    @ApiModelProperty(value = "团队名称")
    private String teamName;

    @ApiModelProperty(value = "用户id")
    private String userId;

    @ApiModelProperty(value = "用户姓名")
    private String userName;

    @ApiModelProperty(value = "助理id")
    private String assistantUserId;

    @ApiModelProperty(value = "助理姓名")
    private String assistantUserName;

    @ApiModelProperty(value = "助理手机号")
    private String assistantUserMobile;

    @ApiModelProperty(value = "团队组别id")
    private String teamGroupId;

    @ApiModelProperty(value = "团队组别名称")
    private String teamGroupName;

    @ApiModelProperty(value = "团队组别值id")
    private String teamGroupValueId;

    @ApiModelProperty(value = "团队组别值名称")
    private String teamGroupValueName;

    @ApiModelProperty(value = "团队组别值限制id")
    private String teamGroupValueLimitId;

    @ApiModelProperty(value = "团队组别值限制数量")
    private Integer teamGroupValueLimitNum;

    @ApiModelProperty(value = "个人")
    private Boolean personal;

    @ApiModelProperty(value = "渠道")
    private Boolean channel;

    @ApiModelProperty(value = "说明")
    private String description;

    @ApiModelProperty(value = "创建时间")
    private String createDateTime;

    @ApiModelProperty(value = "创建人")
    private String createName;

    @ApiModelProperty(value = "修改时间")
    private String modifyDateTime;

    @ApiModelProperty(value = "修改人")
    private String modifyName;

    @ApiModelProperty(value = "删除状态")
    private Boolean isDelete;

    @ApiModelProperty(value = "类型（0：分销，1：自营）")
    private String type;

    @ApiModelProperty(value = "状态（0：默认）")
    private String state;

    @ApiModelProperty(value = "标签")
    private String label;

    @ApiModelProperty(value = "排序")
    private Integer sorting;

    @ApiModelProperty(value = "版本号")
    private Integer version;

    @ApiModelProperty(value = "团队人员常规状态（0：在职，1：注销，2：离职）")
    private String userFormalStateEnum;

    @ApiModelProperty(value = "平台系统列表")
    private List<TransformationExternalPlatformSystemUserVO> externalPlatformSystemList;
}


