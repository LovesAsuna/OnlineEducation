package com.hyosakura.eduservice.service

import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import com.baomidou.mybatisplus.extension.service.IService
import com.hyosakura.eduservice.entity.EduCourse
import com.hyosakura.eduservice.entity.frontvo.CourseFrontVo
import com.hyosakura.eduservice.entity.frontvo.CourseWebVo
import com.hyosakura.eduservice.entity.vo.CourseInfo
import com.hyosakura.eduservice.entity.vo.CoursePublishVo

/**
 * 课程 服务类
 *
 * @author LovesAsuna
 * @since 2021-11-06
 */
interface EduCourseService : IService<EduCourse> {
    fun saveCourseInfo(courseInfo: CourseInfo): String

    fun getCourseInfo(courseId: String): CourseInfo

    fun updateCourseInfo(courseInfo: CourseInfo)

    fun getPublishCourseInfo(id: String): CoursePublishVo

    fun removeCourse(id: String)

    fun getCourseFrontList(pageCourse: Page<EduCourse>, courseFrontVo: CourseFrontVo?): MutableMap<String, Any>

    fun getBaseCourseInfo(courseId: String): CourseWebVo
}