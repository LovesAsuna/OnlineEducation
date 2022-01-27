package com.hyosakura.eduservice.service.impl

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import com.hyosakura.eduservice.entity.EduCourseDescription
import com.hyosakura.eduservice.mapper.EduCourseDescriptionMapper
import com.hyosakura.eduservice.service.EduCourseDescriptionService
import org.springframework.stereotype.Service

/**
 * 课程简介 服务实现类
 *
 * @author LovesAsuna
 * @since 2021-11-06
 */
@Service
open class EduCourseDescriptionServiceImpl : ServiceImpl<EduCourseDescriptionMapper, EduCourseDescription>(),
    EduCourseDescriptionService