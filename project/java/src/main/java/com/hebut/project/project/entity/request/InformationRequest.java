package com.hebut.project.project.entity.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "个人信息请求实体类")
public class InformationRequest {

    @ApiModelProperty(value = "账号")
    private String userId;
}
