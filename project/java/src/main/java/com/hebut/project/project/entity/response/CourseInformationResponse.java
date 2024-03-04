package com.hebut.project.project.entity.response;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "课程信息返回实体类")
public class CourseInformationResponse {
    @ApiModelProperty(value = "课程号")
    private String CourseId;

    @ApiModelProperty(value = "课程名")
    private String name;

    @ApiModelProperty(value = "课程时间描述")
    private String date;
}