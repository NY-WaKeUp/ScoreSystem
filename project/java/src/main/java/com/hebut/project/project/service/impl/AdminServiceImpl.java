package com.hebut.project.project.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hebut.project.common.Enum.ErrorEnum;
import com.hebut.project.common.Enum.UserEnum;
import com.hebut.project.common.exception.CustomException;
import com.hebut.project.project.entity.*;
import com.hebut.project.project.entity.request.*;
import com.hebut.project.project.entity.response.*;
import com.hebut.project.project.mapper.*;
import com.hebut.project.project.service.IAdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.constraints.Negative;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author ningyv
 * @since 2022-11-21
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {

    @Resource
    AdminMapper adminMapper;
    @Resource
    MajorMapper majorMapper;
    @Resource
    StudentMapper studentMapper;
    @Resource
    TeacherMapper teacherMapper;
    @Resource
    TeacherCourseMapper teacherCourseMapper;
    @Resource
    GradeMapper gradeMapper;
    @Resource
    CourseMapper courseMapper;
    @Resource
    MajorCourseMapper majorCourseMapper;

    private int passline = 60;


    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        if (loginRequest.getUserId() == null || loginRequest.getPassword() == null) {
            throw new CustomException(ErrorEnum.INPUT_NULL.getCode(), "账号密码为空！");
        }
        LoginResponse loginResponse = new LoginResponse();
        if (loginRequest.getLevel() == UserEnum.ADMIN.getCode()) {
            Admin admin = adminMapper.selectOne(
                    Wrappers.lambdaQuery(Admin.class)
                            .eq(Admin::getSn, loginRequest.getUserId())
                            .eq(Admin::getPassword, loginRequest.getPassword())
            );
            if (admin == null) {
                throw new CustomException(ErrorEnum.LOGIN_ERROR.getCode(), "登陆错误");
            }
            loginResponse.setUserId(admin.getSn());
        } else if (loginRequest.getLevel() == UserEnum.TEACHER.getCode()) {
            Teacher teacher = teacherMapper.selectOne(
                    Wrappers.lambdaQuery(Teacher.class)
                            .eq(Teacher::getSn, loginRequest.getUserId())
                            .eq(Teacher::getPassword, loginRequest.getPassword())
            );
            if (teacher == null) {
                throw new CustomException(ErrorEnum.LOGIN_ERROR.getCode(), "登陆错误");
            }
            loginResponse.setUserId(teacher.getSn());
        } else if (loginRequest.getLevel() == UserEnum.STUDENT.getCode()) {
            Student student = studentMapper.selectOne(
                    Wrappers.lambdaQuery(Student.class)
                            .eq(Student::getSn, loginRequest.getUserId())
                            .eq(Student::getPassword, loginRequest.getPassword())
            );
            if (student == null) {
                throw new CustomException(ErrorEnum.LOGIN_ERROR.getCode(), "登陆错误");
            }
            loginResponse.setUserId(student.getSn());
        }
        return loginResponse;
    }

    @Override
    public AdminInformationResponse information(InformationRequest informationRequest) {
        Admin admin = adminMapper.selectOne(
                Wrappers.lambdaQuery(Admin.class)
                        .eq(Admin::getSn, informationRequest.getUserId())
        );
        if (admin == null) {
            throw new CustomException(ErrorEnum.NOT_FOUND_OBJECT.getCode(), "用户不存在");
        }
        AdminInformationResponse adminInformationResponse = new AdminInformationResponse();
        adminInformationResponse.setRole("管理员");
        adminInformationResponse.setUserId(admin.getSn());
        return adminInformationResponse;
    }

    @Override
    public void changePassword(ChangePasswordRequest changePasswordRequest) {
        if (changePasswordRequest.getNewPassword() == null) {
            throw new CustomException(ErrorEnum.INPUT_NULL.getCode(), "新密码为空！");
        }
        Admin admin = adminMapper.selectOne(
                Wrappers.lambdaQuery(Admin.class)
                        .eq(Admin::getSn, changePasswordRequest.getUserId())
        );
        admin.setPassword(changePasswordRequest.getNewPassword());
        adminMapper.updateById(admin);
    }

    @Override
    public void addStudent(AddStudentRequest addStudentRequest) {
        if (addStudentRequest.getUserId() == null || addStudentRequest.getPassword() == null ||
                addStudentRequest.getMajor() == null || addStudentRequest.getGender() == null ||
                addStudentRequest.getKlass() == null || addStudentRequest.getEmail() == null) {
            throw new CustomException(ErrorEnum.INPUT_NULL.getCode(), "信息不全！");
        }
        Student s = studentMapper.selectOne(
                Wrappers.lambdaQuery(Student.class)
                        .eq(Student::getSn, addStudentRequest.getUserId())
        );
        if (s != null) {
            throw new CustomException(ErrorEnum.EXISTED_OBJECT.getCode(), "学生已存在！");
        }
        Student student = new Student();
        student.setSn(addStudentRequest.getUserId());
        student.setName(addStudentRequest.getName());
        student.setPassword(addStudentRequest.getPassword());
        student.setKlass(addStudentRequest.getKlass());
        student.setEmail(addStudentRequest.getEmail());
        student.setGender(addStudentRequest.getGender());
        Major major = majorMapper.selectOne(
                Wrappers.lambdaQuery(Major.class)
                        .eq(Major::getId, addStudentRequest.getMajor())
        );
        if (major == null) {
            throw new CustomException(ErrorEnum.NOT_FOUND_OBJECT.getCode(), "专业不存在！");
        }
        student.setMajor(addStudentRequest.getMajor());
        studentMapper.insert(student);
    }

    @Override
    public void addTeacher(AddTeacherRequest addTeacherRequest) {
        if (addTeacherRequest.getUserId() == null || addTeacherRequest.getPassword() == null ||
                addTeacherRequest.getGender() == null || addTeacherRequest.getEmail() == null) {
            throw new CustomException(ErrorEnum.INPUT_NULL.getCode(), "信息不全！");
        }
        Teacher s = teacherMapper.selectOne(
                Wrappers.lambdaQuery(Teacher.class)
                        .eq(Teacher::getSn, addTeacherRequest.getUserId())
        );
        if (s != null) {
            throw new CustomException(ErrorEnum.EXISTED_OBJECT.getCode(), "教师已存在！");
        }
        Teacher teacher = new Teacher();
        teacher.setSn(addTeacherRequest.getUserId());
        teacher.setName(addTeacherRequest.getName());
        teacher.setPassword(addTeacherRequest.getPassword());
        teacher.setEmail(addTeacherRequest.getEmail());
        teacher.setGender(addTeacherRequest.getGender());
        teacherMapper.insert(teacher);
    }

    @Override
    public void setTeacherCourse(SetTeacherCourseRequest setTeacherCourseRequest)
    {
        if(setTeacherCourseRequest.getTeacherName() == null || setTeacherCourseRequest.getCourseId() == null)
        {
            throw new CustomException(ErrorEnum.INPUT_NULL.getCode(), "信息不全！");
        }

        Teacher teacher = teacherMapper.selectOne(
                Wrappers.lambdaQuery(Teacher.class)
                        .eq(Teacher::getName, setTeacherCourseRequest.getTeacherName())
        );
        if(teacher == null)
        {
            throw new CustomException(ErrorEnum.INPUT_NULL.getCode(), "教师不存在！");
        }
        TeacherCourse teacherCourse = new TeacherCourse();
        teacherCourse.setCourseId(setTeacherCourseRequest.getCourseId());
        teacherCourse.setTeacherId(teacher.getSn());
        teacherCourseMapper.insert(teacherCourse);
    }

    @Override
    public void deleteUser(DeleteUserRequest deleteUserRequest) {
        if (Objects.equals(deleteUserRequest.getRole(), "学生")) {
            Student student = studentMapper.selectOne(
                    Wrappers.lambdaQuery(Student.class)
                            .eq(Student::getSn, deleteUserRequest.getUserId())
            );
            if (student == null) {
                throw new CustomException(ErrorEnum.NOT_FOUND_OBJECT.getCode(), "用户不存在！");
            }
            int count = gradeMapper.selectCount(
                    Wrappers.lambdaQuery(Grade.class)
                            .eq(Grade::getStudentId,student.getSn())
            );
            if(count != 0)
            {
                throw new CustomException(ErrorEnum.DELETE_WRONG.getCode(), "不能删除学生，因为该学生的成绩信息还未情空！");
            }
            studentMapper.deleteById(student);
        } else if (Objects.equals(deleteUserRequest.getRole(), "教师")) {
            Teacher teacher = teacherMapper.selectOne(
                    Wrappers.lambdaQuery(Teacher.class)
                            .eq(Teacher::getSn, deleteUserRequest.getUserId())
            );
            if (teacher == null) {
                throw new CustomException(ErrorEnum.NOT_FOUND_OBJECT.getCode(), "用户不存在！");
            }
            int count = teacherCourseMapper.selectCount(
                    Wrappers.lambdaQuery(TeacherCourse.class)
                            .eq(TeacherCourse::getTeacherId,teacher.getSn())
            );
            if(count != 0)
            {
                throw new CustomException(ErrorEnum.DELETE_WRONG.getCode(), "不能删除教师，因为有课程是该教师教授的！");
            }
            studentMapper.deleteById(teacher);
        }
    }

    @Override
    public void updateStudent(AddStudentRequest addStudentRequest) {
        if (addStudentRequest.getUserId() == null || addStudentRequest.getPassword() == null ||
                addStudentRequest.getMajor() == null || addStudentRequest.getGender() == null ||
                addStudentRequest.getKlass() == null || addStudentRequest.getEmail() == null) {
            throw new CustomException(ErrorEnum.INPUT_NULL.getCode(), "信息不全！");
        }
        Student student = studentMapper.selectOne(
                Wrappers.lambdaQuery(Student.class)
                        .eq(Student::getSn, addStudentRequest.getUserId())
        );
        student.setSn(addStudentRequest.getUserId());
        student.setPassword(addStudentRequest.getPassword());
        student.setKlass(addStudentRequest.getKlass());
        student.setEmail(addStudentRequest.getEmail());
        student.setGender(addStudentRequest.getGender());
        Major major = majorMapper.selectOne(
                Wrappers.lambdaQuery(Major.class)
                        .eq(Major::getId, addStudentRequest.getMajor())
        );
        if (major == null) {
            throw new CustomException(ErrorEnum.NOT_FOUND_OBJECT.getCode(), "专业不存在！");
        }
        student.setMajor(addStudentRequest.getMajor());
        studentMapper.updateById(student);
    }

    @Override
    public void updateTeacher(AddTeacherRequest addTeacherRequest) {
        if (addTeacherRequest.getUserId() == null || addTeacherRequest.getPassword() == null ||
                addTeacherRequest.getGender() == null || addTeacherRequest.getEmail() == null) {
            throw new CustomException(ErrorEnum.INPUT_NULL.getCode(), "信息不全！");
        }
        Teacher teacher = teacherMapper.selectOne(
                Wrappers.lambdaQuery(Teacher.class)
                        .eq(Teacher::getSn, addTeacherRequest.getUserId())
        );
        teacher.setSn(addTeacherRequest.getUserId());
        teacher.setPassword(addTeacherRequest.getPassword());
        teacher.setEmail(addTeacherRequest.getEmail());
        teacher.setGender(addTeacherRequest.getGender());
        teacherMapper.updateById(teacher);
    }

    @Override
    public List<StudentInformationResponse> checkStudent() {
        List<StudentInformationResponse> studentInformationResponseList = new ArrayList<>();
        List<Student> students = studentMapper.selectList(
                Wrappers.lambdaQuery(Student.class)
        );
        for (Student student : students) {
            StudentInformationResponse studentInformationResponse = new StudentInformationResponse();
            studentInformationResponse.setEmail(student.getEmail());
            studentInformationResponse.setUserId(student.getSn());
            studentInformationResponse.setName(student.getName());
            studentInformationResponse.set_class(student.getKlass());
            studentInformationResponse.setGender(student.getGender());
            Major major = majorMapper.selectOne(
                    Wrappers.lambdaQuery(Major.class)
                            .eq(Major::getId, student.getMajor())
            );
            studentInformationResponse.setMajor(major.getName());
            studentInformationResponseList.add(studentInformationResponse);
        }
        return studentInformationResponseList;
    }

    @Override
    public List<TeacherInformationResponse> checkTeacher() {
        List<TeacherInformationResponse> teacherInformationResponses = new ArrayList<>();
        List<Teacher> teachers = teacherMapper.selectList(
                Wrappers.lambdaQuery(Teacher.class)
        );
        for (Teacher teacher : teachers) {
            TeacherInformationResponse teacherInformationResponse = new TeacherInformationResponse();
            teacherInformationResponse.setUserId(teacher.getSn());
            teacherInformationResponse.setName(teacher.getName());
            teacherInformationResponse.setGender(teacher.getGender());
            teacherInformationResponse.setEmail(teacher.getEmail());
            teacherInformationResponses.add(teacherInformationResponse);
        }
        return teacherInformationResponses;
    }

    @Override
    public List<StudentInformationResponse> searchStudent(SearchNameRequest searchNameRequest) {
        List<StudentInformationResponse> studentInformationResponseList = new ArrayList<>();
        List<Student> students = studentMapper.selectList(
                Wrappers.lambdaQuery(Student.class)
                        .like(Student::getName,searchNameRequest.getName())
        );
        for (Student student : students) {
            StudentInformationResponse studentInformationResponse = new StudentInformationResponse();
            studentInformationResponse.setEmail(student.getEmail());
            studentInformationResponse.setUserId(student.getSn());
            studentInformationResponse.setName(student.getName());
            studentInformationResponse.set_class(student.getKlass());
            studentInformationResponse.setGender(student.getGender());
            Major major = majorMapper.selectOne(
                    Wrappers.lambdaQuery(Major.class)
                            .eq(Major::getId, student.getMajor())
            );
            studentInformationResponse.setMajor(major.getName());
            studentInformationResponseList.add(studentInformationResponse);
        }
        return studentInformationResponseList;
    }

    @Override
    public List<TeacherInformationResponse> searchTeacher(SearchNameRequest searchNameRequest) {
        List<TeacherInformationResponse> teacherInformationResponses = new ArrayList<>();
        List<Teacher> teachers = teacherMapper.selectList(
                Wrappers.lambdaQuery(Teacher.class)
                        .like(Teacher::getName,searchNameRequest.getName())
        );
        for (Teacher teacher : teachers) {
            TeacherInformationResponse teacherInformationResponse = new TeacherInformationResponse();
            teacherInformationResponse.setUserId(teacher.getSn());
            teacherInformationResponse.setName(teacher.getName());
            teacherInformationResponse.setGender(teacher.getGender());
            teacherInformationResponse.setEmail(teacher.getEmail());
            teacherInformationResponses.add(teacherInformationResponse);
        }
        return teacherInformationResponses;
    }

    @Override
    public List<CourseInformationResponse> searchCourse(CheckCourseRequest checkCourseRequest)
    {

        List<Course> courseList = courseMapper.selectList(null);

        List<CourseInformationResponse> courseInformationResponsesList = new ArrayList<>();

        for(Course course : courseList)
        {
            CourseInformationResponse courseInformationResponse = new CourseInformationResponse();
            courseInformationResponse.setCourseId(course.getId());
            courseInformationResponse.setName(course.getName());
            courseInformationResponse.setDate(course.getCourseDate());
            courseInformationResponsesList.add(courseInformationResponse);
        }

        return courseInformationResponsesList;
    }
    @Override
    public List<CourseStudentResponse> statisticRetrainingStudents(StatisticRetrainingStudentsRequest statisticRetrainingStudentsRequest)
    {
        List<CourseStudentResponse> studentInformationResponseList = new ArrayList<>();


        List<Grade> gradeStuList = gradeMapper.selectList(
                Wrappers.lambdaQuery(Grade.class).eq(
                        Grade::getCourseId ,statisticRetrainingStudentsRequest.getCourseId()
                ).lt(Grade::getGrade, passline)
        );

        MajorCourse majorCourse = majorCourseMapper.selectOne(
                Wrappers.lambdaQuery(MajorCourse.class).eq(
                        MajorCourse::getCourseId, statisticRetrainingStudentsRequest.getCourseId()
                )
        );

        List<Student> studentList = studentMapper.selectList(
                Wrappers.lambdaQuery(Student.class).eq(
                        Student::getMajor, majorCourse.getMajorId()
                )
        );

        for(int i = 0; i < gradeStuList.size(); i++)
        {
            for(int j = 0; j < studentList.size(); j++)
            {
                Student student = studentList.get(j);
                Grade grade = gradeStuList.get(i);
                if(grade.getStudentId().equals(student.getSn()))
                {
                    CourseStudentResponse studentInformationResponse = new CourseStudentResponse();
                    studentInformationResponse.setEmail(student.getEmail());
                    studentInformationResponse.setName(student.getName());
                    studentInformationResponse.set_class(student.getKlass());
                    studentInformationResponse.setGender(student.getGender());
                    studentInformationResponse.setMajor(majorCourse.getMajorId());
                    studentInformationResponseList.add(studentInformationResponse);
                    break;
                }
            }
        }

        return studentInformationResponseList;
    }

    @Override
    public List<CourseStudentResponse> statisticsStatusChangeStudents(StatisticsStatusChangeStudentsRequest statisticRetrainingStudentsRequest)
    {
        List<CourseStudentResponse> studentInformationResponseList = new ArrayList<>();

        List<Student> studentList = studentMapper.selectList(
                Wrappers.lambdaQuery(Student.class).eq(
                        Student::getMajor, statisticRetrainingStudentsRequest.getMajorId()
                )
        );
        List<Grade> gradeList = gradeMapper.selectList(null);

        boolean shouldChangeStatus = true;
        for(Student student : studentList)
        {
            for(Grade grade : gradeList)
            {
                if(student.getSn().equals(grade.getStudentId()))
                {
                    if(grade.getGrade() >= passline)
                    {
                        shouldChangeStatus = false;
                        continue;
                    }
                }
            }

            if(shouldChangeStatus)
            {
                CourseStudentResponse studentInformationResponse = new CourseStudentResponse();
                studentInformationResponse.setEmail(student.getEmail());
                studentInformationResponse.setName(student.getName());
                studentInformationResponse.set_class(student.getKlass());
                studentInformationResponse.setGender(student.getGender());
                studentInformationResponse.setMajor(statisticRetrainingStudentsRequest.getMajorId());
                studentInformationResponseList.add(studentInformationResponse);
            }
            shouldChangeStatus = true;
        }

        return studentInformationResponseList;
    }
}
