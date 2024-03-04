package com.hebut.project.project.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author ningyv
 * @since 2022-11-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TeacherCourse implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 关系编号
     */
    private String id;

    /**
     * 教师工号
     */
    private String teacherId;

    /**
     * 课程号
     */
    private String courseId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 系统备注
     */
    private String note;


}
