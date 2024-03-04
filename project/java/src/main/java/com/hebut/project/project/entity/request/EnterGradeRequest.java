package com.hebut.project.project.entity.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "录入成绩实体类")
public class EnterGradeRequest {

    @ApiModelProperty(value = "账号")
    private String userId;

    @ApiModelProperty(value = "学号")
    private String stuNo;

    @ApiModelProperty(value = "课程号")
    private String courseId;

    @ApiModelProperty(value = "考试成绩")
    private double examGrade;

    @ApiModelProperty(value = "平时成绩")
    private double usualGrade;

}
