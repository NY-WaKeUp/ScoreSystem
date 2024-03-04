package com.hebut.project.project.entity.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "按姓名搜索请求实体类")
public class SearchNameRequest {

    @ApiModelProperty(value = "姓名")
    private String name;
}
