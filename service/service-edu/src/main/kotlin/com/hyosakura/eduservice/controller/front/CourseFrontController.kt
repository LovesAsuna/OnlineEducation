package com.hyosakura.eduservice.controller.front

import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import com.hyosakura.eduservice.client.OrderClient
import com.hyosakura.eduservice.entity.EduCourse
import com.hyosakura.eduservice.entity.frontvo.CourseFrontVo
import com.hyosakura.eduservice.service.EduChapterService
import com.hyosakura.eduservice.service.EduCourseService
import com.hyosakura.util.JwtUtil
import com.hyosakura.util.R
import com.hyosakura.util.R.Companion.data
import com.hyosakura.util.ordervo.CourseWebVoOrder
import org.springframework.beans.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest

/**
 * @author LovesAsuna
 **/
@RestController
@RequestMapping("/eduservice/coursefront")
class CourseFrontController {
    @Autowired
    private lateinit var courseService: EduCourseService

    @Autowired
    private lateinit var chapterService: EduChapterService

    @Autowired
    private lateinit var orderClient: OrderClient

    @PostMapping("getFrontCourseList/{page}/{limit}")
    fun getFrontCourseList(
        @PathVariable page: Long,
        @PathVariable limit: Long,
        @RequestBody(required = false) courseFrontVo: CourseFrontVo?
    ): R {
        val pageCourse = Page<EduCourse>(page, limit)
        val map = courseService.getCourseFrontList(pageCourse, courseFrontVo)
        return R.success().data(map)
    }

    @GetMapping("getFrontCourseInfo/{courseId}")
    fun getFrontCourseInfo(@PathVariable courseId: String, request: HttpServletRequest): R {
        val courseInfo = courseService.getBaseCourseInfo(courseId)
        val chapterVideoList = chapterService.getChapterVideoByCourseId(courseId)
        val isBuy = orderClient.isBuyCourse(courseId, JwtUtil.getMemberIdByJwtToken(request))
        return R.success().data("courseInfo", courseInfo).data("chapterVideoList", chapterVideoList).data("isBuy", isBuy)
    }

    @PostMapping("getCourseInfoOrder/{id}")
    fun getCourseInfoOrder(@PathVariable id: String): CourseWebVoOrder {
        val courseInfo = courseService.getBaseCourseInfo(id)
        val courseWebVoOrder = CourseWebVoOrder()
        BeanUtils.copyProperties(courseInfo, courseWebVoOrder)
        return courseWebVoOrder
    }
}