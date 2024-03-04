package com.hebut.project.project.service;

import com.hebut.project.project.entity.Student;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hebut.project.project.entity.request.ChangePasswordRequest;
import com.hebut.project.project.entity.request.CheckCourseRequest;
import com.hebut.project.project.entity.request.InformationRequest;
import com.hebut.project.project.entity.request.LoginRequest;
import com.hebut.project.project.entity.response.CourseInformationResponse;
import com.hebut.project.project.entity.response.LoginResponse;
import com.hebut.project.project.entity.response.StudentInformationResponse;
import com.hebut.project.project.mapper.AdminMapper;
import com.hebut.project.project.mapper.StudentMapper;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author ningyv
 * @since 2022-11-21
 */
public interface IStudentService extends IService<Student> {


    StudentInformationResponse information(InformationRequest informationRequest);

    void changePassword(ChangePasswordRequest changePasswordRequest);

    List<CourseInformationResponse> checkCourse(CheckCourseRequest checkCourseRequest);
}
