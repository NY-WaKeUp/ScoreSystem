package com.hebut.project.project.controller;


import com.hebut.project.common.ApiResponse;
import com.hebut.project.project.entity.Student;
import com.hebut.project.project.entity.request.ChangePasswordRequest;
import com.hebut.project.project.entity.request.CheckCourseRequest;
import com.hebut.project.project.entity.request.InformationRequest;
import com.hebut.project.project.entity.request.LoginRequest;
import com.hebut.project.project.entity.response.AdminInformationResponse;
import com.hebut.project.project.entity.response.CourseInformationResponse;
import com.hebut.project.project.entity.response.LoginResponse;
import com.hebut.project.project.entity.response.StudentInformationResponse;
import com.hebut.project.project.service.IAdminService;
import com.hebut.project.project.service.IStudentService;
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
@RequestMapping("/project/student")
@CrossOrigin
@Api(tags = "学生相关")
public class StudentController {

    @Resource
    IStudentService iStudentService;


    @PostMapping(value = "/studentInformation")
    @ApiOperation(value = "学生查看个人信息", tags = "个人信息功能")
    public ApiResponse<StudentInformationResponse> information(
            @RequestBody InformationRequest informationRequest
    ) {
        StudentInformationResponse studentInformationResponse = iStudentService.information(informationRequest);
        return ApiResponse.success(studentInformationResponse);
    }

    @PostMapping(value = "/StudentChangePassword")
    @ApiOperation(value = "学生修改密码", tags = "个人信息功能")
    public ApiResponse changePassword(
            @RequestBody ChangePasswordRequest changePasswordRequest
    ) {
        iStudentService.changePassword(changePasswordRequest);
        return ApiResponse.success();
    }

    @PostMapping(value = "/studentCheckCourse")
    @ApiOperation(value = "学生查看课程", tags = "个人信息功能")
    public ApiResponse<List<CourseInformationResponse>> checkCourse(
            @RequestBody CheckCourseRequest checkCourseRequest
    ) {
        List<CourseInformationResponse> courseInformationResponseList = iStudentService.checkCourse(checkCourseRequest);
        return ApiResponse.success(courseInformationResponseList);
    }

}
