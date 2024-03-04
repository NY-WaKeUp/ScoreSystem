package com.hebut.project.project.entity.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "登录返回实体类")
public class LoginResponse {

    @ApiModelProperty(value = "账号")
    private String userId;
}
