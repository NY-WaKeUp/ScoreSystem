package com.hebut.project.project.service;

import com.hebut.project.project.entity.Grade;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hebut.project.project.entity.request.AggregateClassGradeRequest;
import com.hebut.project.project.entity.request.CheckRequest;
import com.hebut.project.project.entity.request.EnterGradeRequest;
import com.hebut.project.project.entity.response.AggregateClassGradeResponse;
import com.hebut.project.project.entity.response.AggregateGradeResponse;
import com.hebut.project.project.entity.response.CheckGradeResponse;
import com.hebut.project.project.entity.response.EnterGradeResponse;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author ningyv
 * @since 2022-11-21
 */
public interface IGradeService extends IService<Grade> {

    EnterGradeResponse enterGrade(EnterGradeRequest enterGradeRequest);

    CheckGradeResponse checkGrade(CheckRequest checkRequest);

    AggregateGradeResponse aggregateGrade(CheckRequest checkRequest);

    AggregateClassGradeResponse aggregateClassGrade(AggregateClassGradeRequest aggregateClassGradeRequest);
}
