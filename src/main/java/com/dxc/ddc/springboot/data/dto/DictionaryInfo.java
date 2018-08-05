package com.dxc.ddc.springboot.data.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 *
 * @author Huanfeng, cai
 * @since JDK 1.8
 */
@Data
public class DictionaryInfo implements Serializable {
    
    private static final long serialVersionUID = 8908839324958850248L;
    
    @JsonIgnore
    @ApiModelProperty(value = "ID", required = true)
    private Long id;
    
    @ApiModelProperty(value = "类别", required = true)
    private String field;
    
    @ApiModelProperty(value = "代码标识", required = true)
    private String code;
    
    @ApiModelProperty(value = "代码标识名称", required = true)
    private String value;
    
    @JsonIgnore
    @ApiModelProperty(value = "创建时间", required = false)
    private LocalDateTime createDate;
    
    @JsonIgnore
    @ApiModelProperty(value = "创建人", required = true)
    private String creatorId;
    
    // @Convert(converter = LocalDateTimeConverter.class)
    @JsonIgnore
    @ApiModelProperty(value = "更新时间", required = false)
    private LocalDateTime updateDate;
    
    @JsonIgnore
    @ApiModelProperty(value = "更新人", required = true)
    private String updateId;
}