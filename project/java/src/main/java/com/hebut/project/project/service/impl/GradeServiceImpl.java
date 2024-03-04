package com.hebut.project.project.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.hebut.project.common.Enum.ErrorEnum;
import com.hebut.project.common.exception.CustomException;
import com.hebut.project.project.entity.*;
import com.hebut.project.project.entity.request.AggregateClassGradeRequest;
import com.hebut.project.project.entity.request.CheckRequest;
import com.hebut.project.project.entity.request.EnterGradeRequest;
import com.hebut.project.project.entity.response.*;
import com.hebut.project.project.mapper.*;
import com.hebut.project.project.service.IGradeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.WeakHashMap;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author ningyv
 * @since 2022-11-21
 */
@Service
public class GradeServiceImpl extends ServiceImpl<GradeMapper, Grade> implements IGradeService {

    @Resource
    TeacherMapper teacherMapper;
    @Resource
    MajorCourseMapper majorCourseMapper;
    @Resource
    StudentMapper studentMapper;
    @Resource
    GradeMapper gradeMapper;
    @Resource
    MajorMapper majorMapper;
    @Resource
    CourseMapper courseMapper;
    @Resource
    AdminMapper adminMapper;
    @Resource
    TeacherCourseMapper teacherCourseMapper;

    @Override
    public EnterGradeResponse enterGrade(EnterGradeRequest enterGradeRequest) {
        Teacher teacher = teacherMapper.selectOne(
                Wrappers.lambdaQuery(Teacher.class)
                        .eq(Teacher::getSn, enterGradeRequest.getUserId())
        );
        if (teacher == null) {
            throw new CustomException(ErrorEnum.LOGIN_ERROR.getCode(), "只有教师用户可以录入成绩！");
        }
        Student student = studentMapper.selectOne(
                Wrappers.lambdaQuery(Student.class)
                        .eq(Student::getSn, enterGradeRequest.getStuNo())
        );
        if (student == null) {
            throw new CustomException(ErrorEnum.LOGIN_ERROR.getCode(), "学生不存在！");
        }
        MajorCourse majorCourse = majorCourseMapper.selectOne(
                Wrappers.lambdaQuery(MajorCourse.class)
                        .eq(MajorCourse::getCourseId, enterGradeRequest.getCourseId())
                        .eq(MajorCourse::getMajorId, student.getMajor())
        );
        if (majorCourse == null) {
            throw new CustomException(ErrorEnum.NOT_FOUND_OBJECT.getCode(), "该学生没有该课程的信息");
        }
        Grade grade = gradeMapper.selectOne(
                Wrappers.lambdaQuery(Grade.class)
                        .eq(Grade::getStudentId, enterGradeRequest.getStuNo())
                        .eq(Grade::getCourseId, enterGradeRequest.getCourseId())
        );
        int gradeId = gradeMapper.selectCount(
                Wrappers.lambdaQuery(Grade.class)
        );
        EnterGradeResponse enterGradeResponse = new EnterGradeResponse();
        if (grade == null) {
            Grade gradeNew = new Grade();
            gradeNew.setId(Integer.toString(gradeId + 1));
            gradeNew.setStudentId(student.getSn());
            gradeNew.setCourseId(enterGradeRequest.getCourseId());
            gradeNew.setExamGrade(enterGradeRequest.getExamGrade());
            gradeNew.setUsualGrade(enterGradeRequest.getUsualGrade());
            gradeNew.setGrade(gradeNew.getExamGrade() + gradeNew.getUsualGrade());
            gradeMapper.insert(gradeNew);
            enterGradeResponse.setExamGrade(gradeNew.getExamGrade());
            enterGradeResponse.setUsualGrade(gradeNew.getUsualGrade());
            enterGradeResponse.setGrade(gradeNew.getGrade());
        } else {
            grade.setExamGrade(enterGradeRequest.getExamGrade());
            grade.setUsualGrade(enterGradeRequest.getUsualGrade());
            grade.setGrade(grade.getExamGrade() + grade.getUsualGrade());
            gradeMapper.updateById(grade);
            enterGradeResponse.setExamGrade(grade.getExamGrade());
            enterGradeResponse.setUsualGrade(grade.getUsualGrade());
            enterGradeResponse.setGrade(grade.getGrade());
        }
        Major major = majorMapper.selectOne(
                Wrappers.lambdaQuery(Major.class)
                        .eq(Major::getId, majorCourse.getMajorId())
        );
        enterGradeResponse.setUserId(student.getSn());
        enterGradeResponse.setName(student.getName());
        enterGradeResponse.setMajor(major.getName());
        enterGradeResponse.setKlass(student.getKlass());
        return enterGradeResponse;

    }

    @Override
    public CheckGradeResponse checkGrade(CheckRequest checkRequest) {
        CheckGradeResponse checkGradeResponse = new CheckGradeResponse();
        List<StudentGradeResponse> studentGradeResponseList = new ArrayList<>();
        Course course = new Course();
        int pass = 0;
        double sum = 0.0;
        Student student = studentMapper.selectOne(
                Wrappers.lambdaQuery(Student.class)
                        .eq(Student::getSn, checkRequest.getUserId())
        );
        List<Grade> grades = gradeMapper.selectList(
                Wrappers.lambdaQuery(Grade.class)
                        .eq(Grade::getStudentId, student.getSn())
        );
        for (Grade grade : grades) {
            StudentGradeResponse studentGradeResponse = new StudentGradeResponse();
            if (grade.getGrade() >= 60) pass++;
            course = courseMapper.selectOne(
                    Wrappers.lambdaQuery(Course.class)
                            .eq(Course::getId, grade.getCourseId())
            );
            sum += grade.getGrade();
            studentGradeResponse.setCourseName(course.getName());
            studentGradeResponse.setExamGrade(grade.getExamGrade());
            studentGradeResponse.setUsualGrade(grade.getUsualGrade());
            studentGradeResponse.setGrade(grade.getGrade());
            studentGradeResponseList.add(studentGradeResponse);
        }
        int total = studentGradeResponseList.size();
        checkGradeResponse.setGradeList(studentGradeResponseList);
        checkGradeResponse.setAveGrade(sum / total);
        checkGradeResponse.setTotalCourse(total);
        checkGradeResponse.setPassCourse(pass);
        return checkGradeResponse;
    }

    @Override
    public AggregateGradeResponse aggregateGrade(CheckRequest checkRequest) {
        Admin admin = adminMapper.selectOne(
                Wrappers.lambdaQuery(Admin.class)
                        .eq(Admin::getSn, checkRequest.getUserId())
        );
        if (admin == null) {
            throw new CustomException(ErrorEnum.TOKEN_WRONG.getCode(), "您没有权限！");
        }
        List<Course> courses = courseMapper.selectList(
                Wrappers.lambdaQuery(Course.class)
        );
        AggregateGradeResponse aggregateGradeResponse = new AggregateGradeResponse();
        List<CourseGradeResponse> courseGradeResponseList = new ArrayList<>();
        int sum = 0, pass = 0;
        double max = 0.0, min = 100.0, total = 0.0;
        for (Course course : courses) {
            CourseGradeResponse courseGradeResponse = new CourseGradeResponse();
            List<Grade> grades = gradeMapper.selectList(
                    Wrappers.lambdaQuery(Grade.class)
                            .eq(Grade::getCourseId, course.getId())
            );
            if (grades.size() == 0) {
                throw new CustomException(ErrorEnum.NOT_FOUND_OBJECT.getCode(), "有课程还没有录入成绩！");
            }
            sum = gradeMapper.selectCount(
                    Wrappers.lambdaQuery(Grade.class)
                            .eq(Grade::getCourseId, course.getId())
            );
            pass = gradeMapper.selectCount(
                    Wrappers.lambdaQuery(Grade.class)
                            .eq(Grade::getCourseId, course.getId())
                            .ge(Grade::getGrade, 60)
            );
            courseGradeResponse.setCourseId(course.getId());
            courseGradeResponse.setCourseName(course.getName());
            courseGradeResponse.setStudentNum(sum);
            courseGradeResponse.setPassNum(pass);
            for (Grade grade : grades) {
                if (grade.getGrade() < min) min = grade.getGrade();
                if (grade.getGrade() > max) max = grade.getGrade();
                total = total + grade.getGrade();
            }
            courseGradeResponse.setMaxGrade(max);
            courseGradeResponse.setMinGrade(min);
            courseGradeResponse.setAveGrade(total / sum);
            courseGradeResponseList.add(courseGradeResponse);
            max = 0.0; min = 100.0; total = 0.0;
        }
        aggregateGradeResponse.setCourseGradeResponseList(courseGradeResponseList);
        return aggregateGradeResponse;
    }

    @Override
    public AggregateClassGradeResponse aggregateClassGrade(AggregateClassGradeRequest aggregateClassGradeRequest) {
        Admin admin = adminMapper.selectOne(
                Wrappers.lambdaQuery(Admin.class)
                        .eq(Admin::getSn, aggregateClassGradeRequest.getUserId())
        );
        Teacher teacher = teacherMapper.selectOne(
                Wrappers.lambdaQuery(Teacher.class)
                        .eq(Teacher::getSn, aggregateClassGradeRequest.getUserId())
        );
        List<KlassGradeResponse> klassGradeResponseList = new ArrayList<>();
        AggregateClassGradeResponse aggregateClassGradeResponse = new AggregateClassGradeResponse();
        if (teacher != null) {
            TeacherCourse teacherCourse = teacherCourseMapper.selectOne(
                    Wrappers.lambdaQuery(TeacherCourse.class)
                            .eq(TeacherCourse::getTeacherId, teacher.getSn())
                            .eq(TeacherCourse::getCourseId, aggregateClassGradeRequest.getCourseId())
            );
            if (teacherCourse == null) {
                throw new CustomException(ErrorEnum.TOKEN_WRONG.getCode(), "你只能查看您教授课程班级的成绩单！");
            }
        } else if (admin == null) {
            throw new CustomException(ErrorEnum.TOKEN_WRONG.getCode(), "您没有权限！");
        }
        int sum = 0, pass = 0;
        double max = 0.0, min = 100.0, total = 0.0;
        List<Grade> gradeList = gradeMapper.selectList(
                Wrappers.lambdaQuery(Grade.class)
                        .eq(Grade::getCourseId, aggregateClassGradeRequest.getCourseId())
        );
        for (Grade grade : gradeList) {
            KlassGradeResponse klassGradeResponse = new KlassGradeResponse();
            Student student = studentMapper.selectOne(
                    Wrappers.lambdaQuery(Student.class)
                            .eq(Student::getSn, grade.getStudentId())
                            .eq(Student::getKlass, aggregateClassGradeRequest.getKlass())
            );
            if (student == null) continue;
            if (grade.getGrade() < min) min = grade.getGrade();
            if (grade.getGrade() > max) max = grade.getGrade();
            total = total + grade.getGrade();
            sum++;
            if (grade.getGrade() >= 60) pass++;
            klassGradeResponse.setStudentId(student.getSn());
            klassGradeResponse.setName(student.getName());
            klassGradeResponse.setGrade(grade.getGrade());
            klassGradeResponseList.add(klassGradeResponse);
        }
        Course course = courseMapper.selectOne(
                Wrappers.lambdaQuery(Course.class)
                        .eq(Course::getId, aggregateClassGradeRequest.getCourseId())
        );
        aggregateClassGradeResponse.setKlassGradeResponses(klassGradeResponseList);
        aggregateClassGradeResponse.setMaxGrade(max);
        aggregateClassGradeResponse.setMinGrade(min);
        aggregateClassGradeResponse.setAveGrade(total / sum);
        aggregateClassGradeResponse.setKlass(aggregateClassGradeRequest.getKlass());
        aggregateClassGradeResponse.setCourseId(course.getId());
        aggregateClassGradeResponse.setCourseName(course.getName());
        aggregateClassGradeResponse.setPassNum(pass);
        aggregateClassGradeResponse.setStudentNum(sum);
        return aggregateClassGradeResponse;
    }
}
