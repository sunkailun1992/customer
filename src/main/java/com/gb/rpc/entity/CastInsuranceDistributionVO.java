package com.gb.rpc.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * Created with IntelliJ IDEA.
 *
 * @author: 贰佰
 * @since: 2021-10-29 01:43:28
 * @description: TODO 投保分销关系渲染
 * @source: 代码生成器
 */
@Data
@ApiModel(value = "投保分销关系渲染")
public class CastInsuranceDistributionVO implements Serializable {

    @ApiModelProperty(value = "序列")
    private String id;

    @ApiModelProperty(value = "投保单id")
    private String castInsuranceId;

    @ApiModelProperty(value = "投保用户id")
    private String castUserId;

    @ApiModelProperty(value = "助理")
    private String assistantId;

    @ApiModelProperty(value = "助理名称")
    private String assistantName;

    @ApiModelProperty(value = "助理手机号")
    private String assistantMobile;

    @ApiModelProperty(value = "管家")
    private String housekeeperId;

    @ApiModelProperty(value = "管家名称")
    private String housekeeperName;

    @ApiModelProperty(value = "管家手机号")
    private String housekeeperMobile;

    @ApiModelProperty(value = "特殊管家")
    private String specialId;

    @ApiModelProperty(value = "特殊管家名称")
    private String specialName;

    @ApiModelProperty(value = "特殊管家手机")
    private String specialMobile;

    @ApiModelProperty(value = "外部经纪人")
    private String externalAgentId;

    @ApiModelProperty(value = "外部经纪人名称")
    private String externalAgentName;

    @ApiModelProperty(value = "外部经纪人手机")
    private String externalAgentMobile;

    @ApiModelProperty(value = "渠道经纪人")
    private String channelId;

    @ApiModelProperty(value = "渠道经纪人名称")
    private String channelName;

    @ApiModelProperty(value = "渠道经纪人手机")
    private String channelMobile;

    @ApiModelProperty(value = "投保类型（0：用户端，1：经纪人，2：渠道，3：业务管家，4：特别业务管家）")
    private Integer userType;

    @ApiModelProperty(value = "说明")
    private String description;

    @ApiModelProperty(value = "创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createDateTime;

    @ApiModelProperty(value = "创建人")
    private String createName;

    @ApiModelProperty(value = "修改时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime modifyDateTime;

    @ApiModelProperty(value = "修改人")
    private String modifyName;

    @ApiModelProperty(value = "删除状态")
    private Boolean isDelete;

    @ApiModelProperty(value = "0 自营  1 分销")
    private String type;

    @ApiModelProperty(value = "标签")
    private String label;

    @ApiModelProperty(value = "排序")
    private Integer sorting;

    @ApiModelProperty(value = "版本号")
    private Integer version;
}


