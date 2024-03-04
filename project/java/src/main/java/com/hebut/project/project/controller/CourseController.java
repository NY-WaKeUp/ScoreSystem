package com.hebut.project.project.controller;


import com.hebut.project.common.ApiResponse;
import com.hebut.project.project.entity.request.AddCourseRequest;
import com.hebut.project.project.entity.request.AddStudentRequest;
import com.hebut.project.project.entity.request.DeleteCourseRequest;
import com.hebut.project.project.service.ICourseService;
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
@RequestMapping("/project/course")
@CrossOrigin
@Api(tags = "课程相关")
public class CourseController {

    @Resource
    ICourseService iCourseService;

    @PostMapping(value = "/addCourse")
    @ApiOperation(value = "增加课程", tags = "课程管理功能")
    public ApiResponse addCourse(
            @RequestBody AddCourseRequest addCourseRequest
    ) {
        iCourseService.addCourse(addCourseRequest);
        return ApiResponse.success();
    }

    @PostMapping(value = "/deleteCourse")
    @ApiOperation(value = "删除课程", tags = "课程管理功能")
    public ApiResponse deleteCourse(
            @RequestBody DeleteCourseRequest deleteCourseRequest
    ) {
        iCourseService.deleteCourse(deleteCourseRequest);
        return ApiResponse.success();
    }

    @PostMapping(value = "/updateCourse")
    @ApiOperation(value = "修改课程", tags = "课程管理功能")
    public ApiResponse updateCourse(
            @RequestBody AddCourseRequest addCourseRequest
    ) {
        iCourseService.updateCourse(addCourseRequest);
        return ApiResponse.success();
    }

}
