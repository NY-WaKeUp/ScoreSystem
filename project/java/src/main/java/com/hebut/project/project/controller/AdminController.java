package com.hebut.project.project.controller;


import com.hebut.project.common.ApiResponse;
import com.hebut.project.project.entity.request.*;
import com.hebut.project.project.entity.response.*;
import com.hebut.project.project.service.IAdminService;
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
@RequestMapping("/project/admin")
@CrossOrigin
@Api(tags = "管理员相关")
public class AdminController {

    @Resource
    IAdminService iAdminService;

    @PostMapping(value = "/Login")
    @ApiOperation(value = "登录", tags = "登录注册功能")
    public ApiResponse<LoginResponse> login(
            @RequestBody LoginRequest loginRequest
    ) {
        LoginResponse loginResponse = iAdminService.login(loginRequest);
        return ApiResponse.success(loginResponse);
    }

    @PostMapping(value = "/adminInformation")
    @ApiOperation(value = "管理员查看个人信息", tags = "个人信息功能")
    public ApiResponse<AdminInformationResponse> information(
            @RequestBody InformationRequest informationRequest
    ) {
        AdminInformationResponse adminInformationResponse = iAdminService.information(informationRequest);
        return ApiResponse.success(adminInformationResponse);
    }

    @PostMapping(value = "/adminChangePassword")
    @ApiOperation(value = "管理员修改密码", tags = "个人信息功能")
    public ApiResponse changePassword(
            @RequestBody ChangePasswordRequest changePasswordRequest
    ) {
        iAdminService.changePassword(changePasswordRequest);
        return ApiResponse.success();
    }

    @PostMapping(value = "/addStudent")
    @ApiOperation(value = "增加学生", tags = "用户管理功能")
    public ApiResponse addStudent(
            @RequestBody AddStudentRequest addStudentRequest
    ) {
        iAdminService.addStudent(addStudentRequest);
        return ApiResponse.success();
    }

    @PostMapping(value = "/addTeacher")
    @ApiOperation(value = "增加教师", tags = "用户管理功能")
    public ApiResponse addTeacher(
            @RequestBody AddTeacherRequest addTeacherRequest
    ) {
        iAdminService.addTeacher(addTeacherRequest);
        return ApiResponse.success();
    }

    @PostMapping(value = "/setTeacherCourse")
    @ApiOperation(value = "设置教师课程", tags = "用户管理功能")
    public ApiResponse setTeacherCourse(
            @RequestBody SetTeacherCourseRequest setTeacherCourseRequest
    ) {
        iAdminService.setTeacherCourse(setTeacherCourseRequest);
        return ApiResponse.success();
    }

    @PostMapping(value = "/deleteUser")
    @ApiOperation(value = "删除用户", tags = "用户管理功能")
    public ApiResponse deleteUser(
            @RequestBody DeleteUserRequest deleteUserRequest
    ) {
        iAdminService.deleteUser(deleteUserRequest);
        return ApiResponse.success();
    }

    @PostMapping(value = "/updateStudent")
    @ApiOperation(value = "修改学生信息", tags = "用户管理功能")
    public ApiResponse updateStudent(
            @RequestBody AddStudentRequest addStudentRequest
    ) {
        iAdminService.updateStudent(addStudentRequest);
        return ApiResponse.success();
    }

    @PostMapping(value = "/updateTeacher")
    @ApiOperation(value = "修改教师信息", tags = "用户管理功能")
    public ApiResponse updateTeacher(
            @RequestBody AddTeacherRequest addTeacherRequest
    ) {
        iAdminService.updateTeacher(addTeacherRequest);
        return ApiResponse.success();
    }

    @PostMapping(value = "/checkStudent")
    @ApiOperation(value = "查看学生列表", tags = "用户管理功能")
    public ApiResponse<List<StudentInformationResponse>> checkStudent(
    ) {
        List<StudentInformationResponse> studentInformationResponsePage = iAdminService.checkStudent();
        return ApiResponse.success(studentInformationResponsePage);
    }

    @PostMapping(value = "/searchStudent")
    @ApiOperation(value = "按姓名搜索学生列表", tags = "用户管理功能")
    public ApiResponse<List<StudentInformationResponse>> searchStudent(
            @RequestBody SearchNameRequest searchNameRequest
    ) {
        List<StudentInformationResponse> studentInformationResponsePage = iAdminService.searchStudent(searchNameRequest);
        return ApiResponse.success(studentInformationResponsePage);
    }

    @PostMapping(value = "/checkTeacher")
    @ApiOperation(value = "查看教师列表", tags = "用户管理功能")
    public ApiResponse<List<TeacherInformationResponse>> checkTeacher(
    ) {
        List<TeacherInformationResponse> teacherInformationResponsePage = iAdminService.checkTeacher();
        return ApiResponse.success(teacherInformationResponsePage);
    }

    @PostMapping(value = "/searchTeacher")
    @ApiOperation(value = "按姓名搜索教师列表", tags = "用户管理功能")
    public ApiResponse<List<TeacherInformationResponse>> searchTeacher(
            @RequestBody SearchNameRequest searchNameRequest
    ) {
        List<TeacherInformationResponse> teacherInformationResponsePage = iAdminService.searchTeacher(searchNameRequest);
        return ApiResponse.success(teacherInformationResponsePage);
    }

    @PostMapping(value = "/searchCourse")
    @ApiOperation(value = "查看全部课程", tags = "个人信息功能")
    public ApiResponse<List<CourseInformationResponse>> searchCourse(
            @RequestBody CheckCourseRequest checkCourseRequest
    ) {
        List<CourseInformationResponse> courseInformationResponseList = iAdminService.searchCourse(checkCourseRequest);
        return ApiResponse.success(courseInformationResponseList);
    }

    @PostMapping(value = "/statisticRetrainingStudents")
    @ApiOperation(value = "重修学生统计", tags = "用户管理功能")
    public ApiResponse<List<CourseStudentResponse>> statisticRetrainingStudents(
            @RequestBody StatisticRetrainingStudentsRequest statisticRetrainingStudentsRequest
    ) {
        List<CourseStudentResponse> studentInformationResponsePage = iAdminService.statisticRetrainingStudents(statisticRetrainingStudentsRequest);
        return ApiResponse.success(studentInformationResponsePage);
    }

    @PostMapping(value = "/statisticsStatusChangeStudents")
    @ApiOperation(value = "学籍变动学生统计", tags = "用户管理功能")
    public ApiResponse<List<CourseStudentResponse>> statisticsStatusChangeStudents(
            @RequestBody StatisticsStatusChangeStudentsRequest statisticsStatusChangeStudentsRequest
    ) {
        List<CourseStudentResponse> studentInformationResponsePage = iAdminService.statisticsStatusChangeStudents(statisticsStatusChangeStudentsRequest);
        return ApiResponse.success(studentInformationResponsePage);
    }

}
