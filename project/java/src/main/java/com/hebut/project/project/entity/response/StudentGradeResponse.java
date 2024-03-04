package com.hebut.project.project.entity.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "个人成绩返回实体类")
public class StudentGradeResponse {

    @ApiModelProperty(value = "课程名")
    private String courseName;

    @ApiModelProperty(value = "考试成绩")
    private double examGrade;

    @ApiModelProperty(value = "平时成绩")
    private double usualGrade;

    @ApiModelProperty(value = "总成绩")
    private double grade;
}
