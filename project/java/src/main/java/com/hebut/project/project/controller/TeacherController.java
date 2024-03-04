package com.hebut.project.project.controller;


import com.hebut.project.common.ApiResponse;
import com.hebut.project.project.entity.request.*;
import com.hebut.project.project.entity.response.*;
import com.hebut.project.project.service.IStudentService;
import com.hebut.project.project.service.ITeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author ningyv
 * @since 2022-11-21
 */
@RestController
@RequestMapping("/project/teacher")
@CrossOrigin
@Api(tags = "教师相关")
public class TeacherController {

    @Resource
    ITeacherService iTeacherService;


    @PostMapping(value = "teacherInformation")
    @ApiOperation(value = "教师查看个人信息", tags = "个人信息功能")
    public ApiResponse<TeacherInformationResponse> information(
            @RequestBody InformationRequest informationRequest
    ) {
        TeacherInformationResponse teacherInformationResponse = iTeacherService.information(informationRequest);
        return ApiResponse.success(teacherInformationResponse);
    }

    @PostMapping(value = "/TeacherChangePassword")
    @ApiOperation(value = "教师修改密码", tags = "个人信息功能")
    public ApiResponse changePassword(
            @RequestBody ChangePasswordRequest changePasswordRequest
    ) {
        iTeacherService.changePassword(changePasswordRequest);
        return ApiResponse.success();
    }

    @PostMapping(value = "/teacherCheckCourse")
    @ApiOperation(value = "教师查看课程", tags = "个人信息功能")
    public ApiResponse<List<CourseInformationResponse>> checkCourse(
            @RequestBody CheckCourseRequest checkCourseRequest
    ) {
        List<CourseInformationResponse> courseInformationResponseList = iTeacherService.checkCourse(checkCourseRequest);
        return ApiResponse.success(courseInformationResponseList);
    }


    @PostMapping(value = "/teacherCheckCourseStudent")
    @ApiOperation(value = "教师查看课程学生", tags = "个人信息功能")
    public ApiResponse<List<CourseStudentResponse>> checkCourseStudent(
            @RequestBody CheckCourseStudentRequest checkCourseStudentRequest
    ) {
        List<CourseStudentResponse> studentInformationResponses = iTeacherService.checkCourseStudent(checkCourseStudentRequest);
        return ApiResponse.success(studentInformationResponses);
    }

}
