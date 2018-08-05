package com.dxc.ddc.springboot.data.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 *
 * @author Huanfeng, cai
 * @since JDK 1.8
 */
@Data
public class UserInfo implements Serializable {

	private static final long serialVersionUID = -2960504381232673984L;

    @ApiModelProperty(value = "ID", required = true)
    private String id;

    @ApiModelProperty(value = "登录ID", required = true)
    private String loginId;

    @ApiModelProperty(value = "登录密码", required = true)
    private String loginPwd;

    @ApiModelProperty(value = "状态 0:无效,1:有效", required = true)
	private byte status;

    @ApiModelProperty(value = "状态 0:无效,1:有效", required = true)
	private String statusName;

    @ApiModelProperty(value = "显示的名字", required = true)
    private String displayName;

    @ApiModelProperty(value = "描述", required = false)
    private String description;

    @ApiModelProperty(value = "创建时间", required = false)
    private LocalDateTime createDate;

    @NotNull
    @ApiModelProperty(value = "创建人", required = true)
    private String creatorId;

    // @Convert(converter = LocalDateTimeConverter.class)
    @ApiModelProperty(value = "更新时间", required = false)
    private LocalDateTime updateDate;

    @NotNull
    @ApiModelProperty(value = "更新人", required = true)
    private String updateId;
}