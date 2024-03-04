package com.hebut.project.project.entity.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "班级成绩返回实体类")
public class KlassGradeResponse {

    @ApiModelProperty(value = "学号")
    private String studentId;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "成绩")
    private double grade;
}
