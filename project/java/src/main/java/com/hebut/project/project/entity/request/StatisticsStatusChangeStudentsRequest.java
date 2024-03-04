package com.hebut.project.project.entity.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "统计学籍变动学生实体类")
public class StatisticsStatusChangeStudentsRequest {

    @ApiModelProperty(value = "专业号")
    private String majorId;
}