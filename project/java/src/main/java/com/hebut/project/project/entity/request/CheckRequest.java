package com.hebut.project.project.entity.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "查询请求实体类")
public class CheckRequest {

    @ApiModelProperty(value = "账号")
    private String userId;
}
