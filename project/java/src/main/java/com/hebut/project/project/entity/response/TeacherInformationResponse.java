package com.hebut.project.project.entity.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "教师个人信息返回实体类")
public class TeacherInformationResponse {


    @ApiModelProperty(value = "账号")
    private String userId;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "性别")
    private String gender;

    @ApiModelProperty(value = "邮箱")
    private String Email;
}
