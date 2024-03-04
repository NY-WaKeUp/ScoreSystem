package com.hebut.project.project.entity.response;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "汇总成绩返回实体类")
public class AggregateGradeResponse {

    @ApiModelProperty(value = "成绩单")
    private List<CourseGradeResponse> courseGradeResponseList;

}
