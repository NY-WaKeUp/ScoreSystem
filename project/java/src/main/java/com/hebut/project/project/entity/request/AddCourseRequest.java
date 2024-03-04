package com.hebut.project.project.entity.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "增加课程请求实体类")
public class AddCourseRequest {

    @ApiModelProperty(value = "课程号")
    private String CourseId;

    @ApiModelProperty(value = "课程名")
    private String name;

    @ApiModelProperty(value = "课程时间描述")
    private String date;

}
