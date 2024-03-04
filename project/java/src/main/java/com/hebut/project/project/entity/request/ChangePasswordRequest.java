package com.hebut.project.project.entity.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "修改密码请求实体类")
public class ChangePasswordRequest {

    @ApiModelProperty(value = "账号")
    private String userId;

    @ApiModelProperty(value = "新密码")
    private String newPassword;

}
