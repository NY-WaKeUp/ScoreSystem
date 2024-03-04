package com.hebut.project.project.entity.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "汇总班级成绩请求实体类")
public class AggregateClassGradeRequest {

    @ApiModelProperty(value = "账号")
    private String userId;

    @ApiModelProperty(value = "课程号")
    private String courseId;

    @ApiModelProperty(value = "班级号")
    private String klass;

}
