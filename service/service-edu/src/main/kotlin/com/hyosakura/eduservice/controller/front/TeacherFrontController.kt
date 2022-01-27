package com.hyosakura.eduservice.controller.front

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import com.hyosakura.eduservice.entity.EduCourse
import com.hyosakura.eduservice.entity.EduTeacher
import com.hyosakura.eduservice.service.EduCourseService
import com.hyosakura.eduservice.service.EduTeacherService
import com.hyosakura.util.R
import com.hyosakura.util.R.Companion.data
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

/**
 * @author LovesAsuna
 **/
@RestController
@RequestMapping("/eduservice/teacherfront")
class TeacherFrontController {
    @Autowired
    private lateinit var teacherService: EduTeacherService

    @Autowired
    private lateinit var courseService: EduCourseService

    @PostMapping("getTeacherFrontList/{page}/{limit}")
    fun getTeacherFrontList(@PathVariable page: Long,@PathVariable limit: Long): R {
        val pageTeacher = Page<EduTeacher>(page, limit)
        val map = teacherService.getTeacherFrontList(pageTeacher)
        return R.success().data(map)
    }

    @GetMapping("getTeacherFrontInfo/{id}")
    fun getTeacherFrontInfo(@PathVariable id: String): R {
        val teacher = teacherService.getById(id)
        val wrapper = QueryWrapper<EduCourse>()
        wrapper.eq("teacher_id", id)
        val courseList = courseService.list(wrapper)
        return R.success().data("teacher", teacher).data("courseList", courseList)
    }
}