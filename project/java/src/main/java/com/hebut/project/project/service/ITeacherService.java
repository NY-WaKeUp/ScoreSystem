package com.hebut.project.project.service;

import com.hebut.project.project.entity.Teacher;
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
public interface ITeacherService extends IService<Teacher> {


    TeacherInformationResponse information(InformationRequest informationRequest);

    void changePassword(ChangePasswordRequest changePasswordRequest);

    List<CourseInformationResponse> checkCourse(CheckCourseRequest checkCourseRequest);

    List<CourseStudentResponse> checkCourseStudent(CheckCourseStudentRequest checkCourseStudentRequest);
}
