package com.hebut.project.project.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.hebut.project.common.Enum.ErrorEnum;
import com.hebut.project.common.exception.CustomException;
import com.hebut.project.project.entity.*;
import com.hebut.project.project.entity.request.*;
import com.hebut.project.project.entity.response.*;
import com.hebut.project.project.mapper.*;
import com.hebut.project.project.service.ITeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author ningyv
 * @since 2022-11-21
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements ITeacherService {

    @Resource
    TeacherMapper teacherMapper;
    @Resource
    TeacherCourseMapper teacherCourseMapper;
    @Resource
    CourseMapper courseMapper;
    @Resource
    MajorCourseMapper majorCourseMapper;
    @Resource
    StudentMapper studentMapper;

    @Override
    public TeacherInformationResponse information(InformationRequest informationRequest) {
        Teacher teacher = teacherMapper.selectOne(
                Wrappers.lambdaQuery(Teacher.class)
                        .eq(Teacher::getSn, informationRequest.getUserId())
        );
        if (teacher == null) {
            throw new CustomException(ErrorEnum.NOT_FOUND_OBJECT.getCode(), "用户不存在");
        }
        TeacherInformationResponse teacherInformationResponse = new TeacherInformationResponse();
        teacherInformationResponse.setUserId(teacher.getSn());
        teacherInformationResponse.setPassword(teacher.getPassword());
        teacherInformationResponse.setName(teacher.getName());
        teacherInformationResponse.setGender(teacher.getGender());
        teacherInformationResponse.setEmail(teacher.getEmail());
        return teacherInformationResponse;
    }

    @Override
    public void changePassword(ChangePasswordRequest changePasswordRequest) {
        if (changePasswordRequest.getNewPassword() == null) {
            throw new CustomException(ErrorEnum.INPUT_NULL.getCode(), "新密码为空！");
        }
        Teacher teacher = teacherMapper.selectOne(
                Wrappers.lambdaQuery(Teacher.class)
                        .eq(Teacher::getSn, changePasswordRequest.getUserId())
        );
        teacher.setPassword(changePasswordRequest.getNewPassword());
        teacherMapper.updateById(teacher);
    }

    @Override
    public List<CourseInformationResponse> checkCourse(CheckCourseRequest checkCourseRequest) {
        Teacher teacher = teacherMapper.selectOne(
                Wrappers.lambdaQuery(Teacher.class).eq(
                        Teacher::getSn, checkCourseRequest.getUserId()
                )
        );

        List<TeacherCourse> courseList = teacherCourseMapper.selectList(
                Wrappers.lambdaQuery(TeacherCourse.class).eq(
                        TeacherCourse::getTeacherId, teacher.getSn()
                )
        );

        List<CourseInformationResponse> courseInformationResponsesList = new ArrayList<>();

        for(TeacherCourse teacherCourse : courseList)
        {
            Course course = courseMapper.selectOne(
                    Wrappers.lambdaQuery(Course.class).eq(
                            Course::getId, teacherCourse.getCourseId()
                    )
            );

            CourseInformationResponse courseInformationResponse = new CourseInformationResponse();
            courseInformationResponse.setCourseId(course.getId());
            courseInformationResponse.setName(course.getName());
            courseInformationResponse.setDate(course.getCourseDate());
            courseInformationResponsesList.add(courseInformationResponse);
        }

        return courseInformationResponsesList;
    }

    @Override
    public List<CourseStudentResponse> checkCourseStudent(CheckCourseStudentRequest checkCourseStudentRequest)
    {
        Teacher teacher = teacherMapper.selectOne(
                Wrappers.lambdaQuery(Teacher.class).eq(
                        Teacher::getSn, checkCourseStudentRequest.getUserId()
                )
        );

        MajorCourse majorCourse = majorCourseMapper.selectOne(
                Wrappers.lambdaQuery(MajorCourse.class).eq(
                        MajorCourse::getCourseId, checkCourseStudentRequest.getCourseId()
                )
        );

        List<Student> studentList = studentMapper.selectList(
                Wrappers.lambdaQuery(Student.class).eq(
                        Student::getMajor, majorCourse.getMajorId()
                ).eq(
                        Student::getKlass, checkCourseStudentRequest.getKlassId()
                )
        );

        List<CourseStudentResponse> studentInformationResponseList = new ArrayList<>();

        for(Student stu : studentList)
        {
            CourseStudentResponse studentInformationResponse = new CourseStudentResponse();
            studentInformationResponse.setEmail(stu.getEmail());
            studentInformationResponse.setName(stu.getName());
            studentInformationResponse.set_class(stu.getKlass());
            studentInformationResponse.setGender(stu.getGender());
            studentInformationResponse.setMajor(majorCourse.getMajorId());
            studentInformationResponseList.add(studentInformationResponse);
        }

        return studentInformationResponseList;
    }
}
