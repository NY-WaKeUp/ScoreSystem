package com.hebut.project.project.entity;

import java.time.LocalDateTime;
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
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Data
public class Admin implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 账号
     */
    @TableId
    private String sn;

    /**
     * 密码
     */
    private String password;

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
