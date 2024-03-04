package com.hebut.project.project.entity.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "删除用户请求实体类")
public class DeleteUserRequest {

    @ApiModelProperty(value = "账号")
    private String userId;

    @ApiModelProperty(value = "角色")
    private String role;
}
