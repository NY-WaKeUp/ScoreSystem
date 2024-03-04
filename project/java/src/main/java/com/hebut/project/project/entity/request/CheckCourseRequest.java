package com.hebut.project.project.entity.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "查询课程请求实体类")
public class CheckCourseRequest {

    @ApiModelProperty(value = "账号")
    private String userId;
}
