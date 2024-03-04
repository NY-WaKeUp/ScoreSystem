package com.hebut.project.project.entity.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "汇总课程班级成绩返回实体类")
public class AggregateClassGradeResponse {

    @ApiModelProperty(value = "成绩单")
    private List<KlassGradeResponse> klassGradeResponses;

    @ApiModelProperty(value = "班级")
    private String klass;

    @ApiModelProperty(value = "课程编号")
    private String courseId;

    @ApiModelProperty(value = "课程姓名")
    private String courseName;

    @ApiModelProperty(value = "课程人数")
    private int studentNum;

    @ApiModelProperty(value = "及格人数")
    private int passNum;

    @ApiModelProperty(value = "课程平均分")
    private double aveGrade;

    @ApiModelProperty(value = "课程最高分")
    private double maxGrade;

    @ApiModelProperty(value = "课程最低分")
    private double minGrade;
}
