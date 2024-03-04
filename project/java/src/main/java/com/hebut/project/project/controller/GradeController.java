package com.hebut.project.project.controller;


import com.hebut.project.common.ApiResponse;
import com.hebut.project.project.entity.request.AggregateClassGradeRequest;
import com.hebut.project.project.entity.request.CheckRequest;
import com.hebut.project.project.entity.request.EnterGradeRequest;
import com.hebut.project.project.entity.request.LoginRequest;
import com.hebut.project.project.entity.response.*;
import com.hebut.project.project.service.IAdminService;
import com.hebut.project.project.service.IGradeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author ningyv
 * @since 2022-11-21
 */
@RestController
@RequestMapping("/project/grade")
@CrossOrigin
@Api(tags = "成绩相关")
public class GradeController {

    @Resource
    IGradeService iGradeService;

    @PostMapping(value = "/enterGrade")
    @ApiOperation(value = "录入成绩", tags = "教师成绩功能")
    public ApiResponse<EnterGradeResponse> enterGrade(
            @RequestBody EnterGradeRequest enterGradeRequest
    ) {
        EnterGradeResponse enterGradeResponse = iGradeService.enterGrade(enterGradeRequest);
        return ApiResponse.success(enterGradeResponse);
    }

    @PostMapping(value = "/checkGrade")
    @ApiOperation(value = "查看个人成绩单", tags = "学生成绩功能")
    public ApiResponse<CheckGradeResponse> checkGrade(
            @RequestBody CheckRequest checkRequest
    ) {
        CheckGradeResponse checkGradeResponse = iGradeService.checkGrade(checkRequest);
        return ApiResponse.success(checkGradeResponse);
    }

    @PostMapping(value = "/aggregateGrade")
    @ApiOperation(value = "汇总成绩", tags = "管理员成绩功能")
    public ApiResponse<AggregateGradeResponse> aggregateGrade(
            @RequestBody CheckRequest checkRequest
    ) {
        AggregateGradeResponse aggregateGradeResponse = iGradeService.aggregateGrade(checkRequest);
        return ApiResponse.success(aggregateGradeResponse);
    }

    @PostMapping(value = "/aggregateClassGrade")
    @ApiOperation(value = "班级单科成绩汇总", tags = "教师成绩功能")
    public ApiResponse<AggregateClassGradeResponse> aggregateClassGrade(
            @RequestBody AggregateClassGradeRequest aggregateClassGradeRequest
    ) {
        AggregateClassGradeResponse aggregateClassGradeResponse = iGradeService.aggregateClassGrade(aggregateClassGradeRequest);
        return ApiResponse.success(aggregateClassGradeResponse);
    }

}
