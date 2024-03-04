package com.hebut.project.project.entity.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "重置密码请求实体类")
public class ResetPasswordRequest {

    @ApiModelProperty(value = "管理员账号")
    private String adminId;

    @ApiModelProperty(value = "目标账号")
    private String userId;
}
