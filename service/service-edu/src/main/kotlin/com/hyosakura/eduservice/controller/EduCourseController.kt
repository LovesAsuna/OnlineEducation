package com.hyosakura.eduservice.controller

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import com.hyosakura.eduservice.entity.EduCourse
import com.hyosakura.eduservice.entity.EduTeacher
import com.hyosakura.eduservice.entity.vo.CourseInfo
import com.hyosakura.eduservice.entity.vo.CourseQuery
import com.hyosakura.eduservice.service.EduCourseService
import com.hyosakura.util.R
import com.hyosakura.util.R.Companion.data
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.util.ObjectUtils
import org.springframework.util.StringUtils
import org.springframework.web.bind.annotation.*

/**
 * 课程 前端控制器
 *
 * @author LovesAsuna
 * @since 2021-11-06
 */
@RestController
@RequestMapping("/eduservice/course")
class EduCourseController {
    @Autowired
    private lateinit var service: EduCourseService

    @PostMapping("pageCourseCondition/{current}/{limit}")
    fun pageCourseCondition(
        @PathVariable current: Long,
        @PathVariable limit: Long,
        @RequestBody(required = false) query: CourseQuery?
    ) : R {
        val pageCourse = Page<EduCourse>(current, limit)
        val wrapper = QueryWrapper<EduCourse>()
        if (query != null) {
            val title = query.title
            val status = query.status
            val lessonNum = query.lessonNum
            val begin = query.begin
            val end = query.end
            if (StringUtils.hasText(title)) {
                wrapper.like("title", title)
            }
            if (StringUtils.hasText(status)) {
                wrapper.like("status", status)
            }
            if (!ObjectUtils.isEmpty(lessonNum)) {
                wrapper.eq("lesson_Num", lessonNum)
            }
            if (StringUtils.hasText(begin)) {
                wrapper.ge("gmt_create", begin)
            }
            if (StringUtils.hasText(end)) {
                wrapper.le("gmt_create", end)
            }
        }
        wrapper.orderByDesc("gmt_create")
        service.page(pageCourse, wrapper)
        val total = pageCourse.total
        val records = pageCourse.records
        return R.success().data("total", total).data("list", records)
    }

    @PostMapping("addCourseInfo")
    fun addCourseInfo(@RequestBody courseInfo: CourseInfo): R {
        val id = service.saveCourseInfo(courseInfo)
        return R.success().data("courseId", id)
    }

    @GetMapping("getCourseInfo/{courseId}")
    fun getCourseInfo(@PathVariable courseId: String): R {
        val courseInfoVo = service.getCourseInfo(courseId)
        return R.success().data("courseInfoVo", courseInfoVo)
    }

    @PostMapping("updateCourseInfo")
    fun updateCourseInfo(@RequestBody courseInfo: CourseInfo): R {
        service.updateCourseInfo(courseInfo)
        return R.success()
    }

    @GetMapping("getPublishCourseInfo/{id}")
    fun getPublishCourseInfo(@PathVariable id: String): R {
        val coursePublishVo = service.getPublishCourseInfo(id)
        return R.success().data("publishCourse", coursePublishVo)
    }

    @PostMapping("publishCourse/{id}")
    fun publishCourse(@PathVariable id: String): R {
        val eduCourse = EduCourse()
        eduCourse.id = id
        eduCourse.status = "Normal"
        service.updateById(eduCourse)
        return R.success()
    }

    @DeleteMapping("{id}")
    fun deleteCourse(@PathVariable id: String): R {
        service.removeCourse(id)
        return R.success()
    }
}