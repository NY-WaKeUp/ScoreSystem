package com.hebut.project.project.entity.response;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "管理员个人信息返回实体类")
public class AdminInformationResponse {

    @ApiModelProperty(value = "身份")
    private String role;

    @ApiModelProperty(value = "账号")
    private String userId;

}
