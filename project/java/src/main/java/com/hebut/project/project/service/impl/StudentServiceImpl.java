package com.hebut.project.project.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.hebut.project.common.Enum.ErrorEnum;
import com.hebut.project.common.exception.CustomException;
import com.hebut.project.project.entity.*;
import com.hebut.project.project.entity.request.ChangePasswordRequest;
import com.hebut.project.project.entity.request.CheckCourseRequest;
import com.hebut.project.project.entity.request.InformationRequest;
import com.hebut.project.project.entity.request.LoginRequest;
import com.hebut.project.project.entity.response.AdminInformationResponse;
import com.hebut.project.project.entity.response.CourseInformationResponse;
import com.hebut.project.project.entity.response.LoginResponse;
import com.hebut.project.project.entity.response.StudentInformationResponse;
import com.hebut.project.project.mapper.CourseMapper;
import com.hebut.project.project.mapper.MajorCourseMapper;
import com.hebut.project.project.mapper.MajorMapper;
import com.hebut.project.project.mapper.StudentMapper;
import com.hebut.project.project.service.IStudentService;
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
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements IStudentService {

    @Resource
    StudentMapper studentMapper;
    @Resource
    MajorMapper majorMapper;
    @Resource
    MajorCourseMapper majorCourseMapper;
    @Resource
    CourseMapper courseMapper;



    @Override
    public StudentInformationResponse information(InformationRequest informationRequest) {
        Student student = studentMapper.selectOne(
                Wrappers.lambdaQuery(Student.class)
                        .eq(Student::getSn, informationRequest.getUserId())
        );
        if (student == null) {
            throw new CustomException(ErrorEnum.NOT_FOUND_OBJECT.getCode(), "用户不存在");
        }
        StudentInformationResponse studentInformationResponse = new StudentInformationResponse();
        studentInformationResponse.setUserId(student.getSn());
        studentInformationResponse.setPassword(student.getPassword());
        studentInformationResponse.setName(student.getName());
        studentInformationResponse.set_class(student.getKlass());
        studentInformationResponse.setGender(student.getGender());
        studentInformationResponse.setEmail(student.getEmail());
        Major major = majorMapper.selectOne(
                Wrappers.lambdaQuery(Major.class)
                        .eq(Major::getId, student.getMajor())
        );
        studentInformationResponse.setMajor(major.getName());
        return studentInformationResponse;
    }

    @Override
    public void changePassword(ChangePasswordRequest changePasswordRequest) {
        if (changePasswordRequest.getNewPassword() == null) {
            throw new CustomException(ErrorEnum.INPUT_NULL.getCode(), "新密码为空！");
        }
        Student student = studentMapper.selectOne(
                Wrappers.lambdaQuery(Student.class)
                        .eq(Student::getSn, changePasswordRequest.getUserId())
        );
        student.setPassword(changePasswordRequest.getNewPassword());
        studentMapper.updateById(student);
    }

    @Override
    public List<CourseInformationResponse> checkCourse(CheckCourseRequest checkCourseRequest)
    {
        Student student = studentMapper.selectOne(
                Wrappers.lambdaQuery(Student.class).eq(
                        Student::getSn, checkCourseRequest.getUserId()
                )
        );

        List<MajorCourse> courseList = majorCourseMapper.selectList(
                Wrappers.lambdaQuery(MajorCourse.class).eq(
                        MajorCourse::getMajorId, student.getMajor()
                )
        );

        List<CourseInformationResponse> courseInformationResponsesList = new ArrayList<>();

        for(MajorCourse majorCourse : courseList)
        {
            Course course = courseMapper.selectOne(
                    Wrappers.lambdaQuery(Course.class).eq(
                            Course::getId, majorCourse.getCourseId()
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
}
