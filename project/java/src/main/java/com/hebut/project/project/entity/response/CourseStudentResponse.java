package com.hebut.project.project.entity.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "参与课程学生信息返回实体类")
public class CourseStudentResponse {
    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "专业")
    private String major;

    @ApiModelProperty(value = "班级")
    private String _class;

    @ApiModelProperty(value = "性别")
    private String gender;

    @ApiModelProperty(value = "邮箱")
    private String Email;
}