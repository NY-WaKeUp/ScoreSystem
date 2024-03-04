package com.hebut.project.project.entity.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "删除课程请求实体类")
public class DeleteCourseRequest {

    @ApiModelProperty(value = "课程号")
    private String CourseId;
}
