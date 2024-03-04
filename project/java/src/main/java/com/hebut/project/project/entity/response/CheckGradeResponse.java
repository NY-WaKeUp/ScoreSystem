package com.hebut.project.project.entity.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "个人成绩返回实体类")
public class CheckGradeResponse {


    @ApiModelProperty(value = "成绩单")
    private List<StudentGradeResponse> gradeList;

    @ApiModelProperty(value = "平均分")
    private double aveGrade;

    @ApiModelProperty(value = "总科目数")
    private int totalCourse;

    @ApiModelProperty(value = "及格科目")
    private int passCourse;

}
