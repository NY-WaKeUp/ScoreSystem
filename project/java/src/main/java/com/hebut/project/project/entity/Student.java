package com.hebut.project.project.entity;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
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
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 学号
     */
    @TableId
    private String sn;

    /**
     * 姓名
     */
    private String name;

    /**
     * 密码
     */
    private String password;

    /**
     * 专业编号
     */
    private String major;

    /**
     * 班级编号
     */
    private String klass;

    /**
     * 性别
     */
    private String gender;

    /**
     * 邮箱
     */
    @TableField("Email")
    private String Email;

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
