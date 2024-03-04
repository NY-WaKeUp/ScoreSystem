package com.hebut.project.project.service;

import com.hebut.project.project.entity.Course;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hebut.project.project.entity.request.AddCourseRequest;
import com.hebut.project.project.entity.request.DeleteCourseRequest;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author ningyv
 * @since 2022-11-21
 */
public interface ICourseService extends IService<Course> {

    void addCourse(AddCourseRequest addCourseRequest);

    void deleteCourse(DeleteCourseRequest deleteCourseRequest);

    void updateCourse(AddCourseRequest addCourseRequest);
}
