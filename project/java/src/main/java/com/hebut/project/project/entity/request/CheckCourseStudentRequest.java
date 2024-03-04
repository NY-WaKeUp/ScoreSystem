package com.hebut.project.project.entity.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "查看课程下班级学生请求类")
public class CheckCourseStudentRequest {

    @ApiModelProperty(value = "账号")
    private String userId;

    @ApiModelProperty(value = "课程号")
    private String CourseId;

    @ApiModelProperty(value = "班级号")
    private String klassId;
}
