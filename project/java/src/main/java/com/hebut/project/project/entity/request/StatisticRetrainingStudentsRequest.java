package com.hebut.project.project.entity.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "统计重修学生实体类")
public class StatisticRetrainingStudentsRequest {

    @ApiModelProperty(value = "课程号")
    private String CourseId;
}

