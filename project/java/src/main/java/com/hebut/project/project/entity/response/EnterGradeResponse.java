package com.hebut.project.project.entity.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "录入成绩返回实体类")
public class EnterGradeResponse {

    @ApiModelProperty(value = "学号")
    private String userId;

    @ApiModelProperty(value = "学生姓名")
    private String name;

    @ApiModelProperty(value = "专业")
    private String major;

    @ApiModelProperty(value = "班级")
    private String klass;

    @ApiModelProperty(value = "考试成绩")
    private double examGrade;

    @ApiModelProperty(value = "平时成绩")
    private double usualGrade;

    @ApiModelProperty(value = "总成绩")
    private double grade;
}
