package com.hyosakura.eduservice.controller.front

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import com.hyosakura.eduservice.entity.EduCourse
import com.hyosakura.eduservice.entity.EduTeacher
import com.hyosakura.eduservice.service.EduCourseService
import com.hyosakura.eduservice.service.EduTeacherService
import com.hyosakura.util.R
import com.hyosakura.util.R.Companion.data
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @author LovesAsuna
 **/
@RestController
@RequestMapping("/eduservice/indexfront")
class IndexFrontController {
    @Autowired
    private lateinit var courseService: EduCourseService

    @Autowired
    private lateinit var teacherService: EduTeacherService

    @GetMapping("index")
    fun index(): R {
        val courseWrapper = QueryWrapper<EduCourse>()
        courseWrapper.orderByDesc("id")
        courseWrapper.last("limit 8")
        val courseList = courseService.list(courseWrapper)

        val teacherWrapper = QueryWrapper<EduTeacher>()
        teacherWrapper.orderByDesc("id")
        teacherWrapper.last("limit 4")
        val teacherList = teacherService.list(teacherWrapper)
        return R.success().data("courseList", courseList).data("teacherList", teacherList)
    }
}