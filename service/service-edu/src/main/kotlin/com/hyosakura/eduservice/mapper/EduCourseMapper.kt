package com.hyosakura.eduservice.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.hyosakura.eduservice.entity.EduCourse
import com.hyosakura.eduservice.entity.frontvo.CourseWebVo
import com.hyosakura.eduservice.entity.vo.CoursePublishVo

/**
 * 课程 Mapper 接口
 *
 * @author LovesAsuna
 * @since 2021-11-06
 */
interface EduCourseMapper : BaseMapper<EduCourse> {
    fun getPublishCourseInfo(courseId: String): CoursePublishVo

    fun getBaseCourseInfo(courseId: String): CourseWebVo
}