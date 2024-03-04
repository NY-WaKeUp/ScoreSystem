package com.hebut.project.project.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.hebut.project.common.Enum.ErrorEnum;
import com.hebut.project.common.exception.CustomException;
import com.hebut.project.project.entity.*;
import com.hebut.project.project.entity.request.AddCourseRequest;
import com.hebut.project.project.entity.request.DeleteCourseRequest;
import com.hebut.project.project.mapper.CourseMapper;
import com.hebut.project.project.mapper.GradeMapper;
import com.hebut.project.project.mapper.MajorCourseMapper;
import com.hebut.project.project.mapper.MajorMapper;
import com.hebut.project.project.service.ICourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements ICourseService {

    @Resource
    CourseMapper courseMapper;
    @Resource
    MajorCourseMapper majorCourseMapper;
    @Resource
    GradeMapper gradeMapper;

    @Override
    public void addCourse(AddCourseRequest addCourseRequest) {
        if (addCourseRequest.getCourseId() == null || addCourseRequest.getName() == null || addCourseRequest.getDate() == null) {
            throw new CustomException(ErrorEnum.INPUT_NULL.getCode(), "信息不全！");
        }
        Course c = courseMapper.selectOne(
                Wrappers.lambdaQuery(Course.class)
                        .eq(Course::getId, addCourseRequest.getCourseId())
        );
        if (c != null) {
            throw new CustomException(ErrorEnum.EXISTED_OBJECT.getCode(), "课程已存在！");
        }
        Course course = new Course();
        course.setId(addCourseRequest.getCourseId());
        course.setName(addCourseRequest.getName());
        course.setCourseDate(addCourseRequest.getDate());
        courseMapper.insert(course);
    }

    @Override
    public void deleteCourse(DeleteCourseRequest deleteCourseRequest) {
        Course course = courseMapper.selectOne(
                Wrappers.lambdaQuery(Course.class)
                        .eq(Course::getId, deleteCourseRequest.getCourseId())
        );
        List<MajorCourse> majorCourseList = majorCourseMapper.selectList(
                Wrappers.lambdaQuery(MajorCourse.class)
                        .eq(MajorCourse::getCourseId, course.getId())
        );
        List<Grade> gradeList = gradeMapper.selectList(
                Wrappers.lambdaQuery(Grade.class)
                        .eq(Grade::getCourseId,deleteCourseRequest.getCourseId())
        );
        if(gradeList.size() > 0)
        {
            throw new CustomException(ErrorEnum.INNER_ERROR.getCode(),"请先解除课程与学生成绩的关系！");
        }
        for (MajorCourse majorCourse : majorCourseList) {
            majorCourseMapper.deleteById(majorCourse);
        }
        courseMapper.deleteById(course);

    }

    @Override
    public void updateCourse(AddCourseRequest addCourseRequest) {
        if (addCourseRequest.getCourseId() == null || addCourseRequest.getName() == null || addCourseRequest.getName() == null) {
            throw new CustomException(ErrorEnum.INPUT_NULL.getCode(), "信息不全！");
        }
        Course course = courseMapper.selectOne(
                Wrappers.lambdaQuery(Course.class)
                        .eq(Course::getId, addCourseRequest.getCourseId())
        );
        if(course == null)
        {
            throw new CustomException(ErrorEnum.NOT_FOUND_OBJECT.getCode(), "课程不存在！");
        }
        course.setId(addCourseRequest.getCourseId());
        course.setName(addCourseRequest.getName());
        course.setCourseDate(addCourseRequest.getDate());
        courseMapper.updateById(course);
    }
}
