package com.hebut.project.project.entity.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "增加教师请求实体类")
public class AddTeacherRequest {

    @ApiModelProperty(value = "账号")
    private String userId;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "密码")
    private String Password;

    @ApiModelProperty(value = "性别")
    private String gender;

    @ApiModelProperty(value = "邮箱")
    private String Email;
}
