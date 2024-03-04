package com.hebut.project.project.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hebut.project.project.entity.Admin;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hebut.project.project.entity.request.*;
import com.hebut.project.project.entity.response.*;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author ningyv
 * @since 2022-11-21
 */
public interface IAdminService extends IService<Admin> {

    LoginResponse login(LoginRequest loginRequest);

    AdminInformationResponse information(InformationRequest informationRequest);

    void changePassword(ChangePasswordRequest changePasswordRequest);

    void addStudent(AddStudentRequest addStudentRequest);

    void addTeacher(AddTeacherRequest addTeacherRequest);

    void setTeacherCourse(SetTeacherCourseRequest setTeacherCourseRequest);

    void deleteUser(DeleteUserRequest deleteUserRequest);

    void updateStudent(AddStudentRequest addStudentRequest);

    void updateTeacher(AddTeacherRequest addTeacherRequest);

    List<StudentInformationResponse> checkStudent();

    List<TeacherInformationResponse> checkTeacher();

    List<StudentInformationResponse> searchStudent(SearchNameRequest searchNameRequest);

    List<TeacherInformationResponse> searchTeacher(SearchNameRequest searchNameRequest);

    List<CourseInformationResponse> searchCourse(CheckCourseRequest checkCourseRequest);

    List<CourseStudentResponse> statisticRetrainingStudents(StatisticRetrainingStudentsRequest statisticRetrainingStudentsRequest);

    List<CourseStudentResponse> statisticsStatusChangeStudents(StatisticsStatusChangeStudentsRequest statisticRetrainingStudentsRequest);
}
