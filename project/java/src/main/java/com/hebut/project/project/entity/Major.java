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
public class Major implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 专业编号
     */
    private String id;

    /**
     * 专业名
     */
    private String name;

    /**
     * 学院编号
     */
    private String institute;

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
