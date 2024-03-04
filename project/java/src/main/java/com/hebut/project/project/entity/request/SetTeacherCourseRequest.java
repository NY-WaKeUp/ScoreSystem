package com.hebut.project.project.entity.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "为教师添加课程")
public class SetTeacherCourseRequest {

    @ApiModelProperty(value = "课程号")
    private String CourseId;

    @ApiModelProperty(value = "教师姓名")
    private String teacherName;

}
