package com.hyosakura.eduservice.service.impl

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import com.hyosakura.eduservice.entity.EduCourse
import com.hyosakura.eduservice.entity.EduCourseDescription
import com.hyosakura.eduservice.entity.frontvo.CourseFrontVo
import com.hyosakura.eduservice.entity.frontvo.CourseWebVo
import com.hyosakura.eduservice.entity.vo.CourseInfo
import com.hyosakura.eduservice.entity.vo.CoursePublishVo
import com.hyosakura.eduservice.mapper.EduCourseMapper
import com.hyosakura.eduservice.service.EduChapterService
import com.hyosakura.eduservice.service.EduCourseDescriptionService
import com.hyosakura.eduservice.service.EduCourseService
import com.hyosakura.eduservice.service.EduVideoService
import org.springframework.beans.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.util.StringUtils

/**
 * 课程 服务实现类
 *
 * @author LovesAsuna
 * @since 2021-11-06
 */
@Service
open class EduCourseServiceImpl : ServiceImpl<EduCourseMapper, EduCourse>(), EduCourseService {
    @Autowired
    private lateinit var descriptionService: EduCourseDescriptionService

    @Autowired
    private lateinit var videoService : EduVideoService

    @Autowired
    private lateinit var chapterService : EduChapterService

    override fun saveCourseInfo(courseInfo: CourseInfo): String {
        val course = EduCourse()
        BeanUtils.copyProperties(courseInfo, course)
        val insert = baseMapper.insert(course)
        if (insert <= 0) {
            throw RuntimeException("添加课程信息失败")
        }
        val cid = course.id
        val description = EduCourseDescription(id = cid, description = courseInfo.description)
        descriptionService.save(description)
        return cid!!
    }

    override fun getCourseInfo(courseId: String): CourseInfo {
        val eduCourse = baseMapper.selectById(courseId)
        val courseInfo = CourseInfo()
        BeanUtils.copyProperties(eduCourse, courseInfo)
        val courseDescription = descriptionService.getById(courseId)
        courseInfo.description = courseDescription.description
        return courseInfo
    }

    override fun updateCourseInfo(courseInfo: CourseInfo) {
        val eduCourse = EduCourse()
        BeanUtils.copyProperties(courseInfo, eduCourse)
        val update = baseMapper.updateById(eduCourse)
        if (update == 0) {
            throw RuntimeException("修改课程信息失败")
        }
        val description = EduCourseDescription()
        description.id = courseInfo.id
        description.description = courseInfo.description
        descriptionService.updateById(description)
    }

    override fun getPublishCourseInfo(id: String): CoursePublishVo {
        return baseMapper.getPublishCourseInfo(id)
    }

    override fun removeCourse(id: String) {
        videoService.removeVideoByCourseId(id)
        chapterService.removeChapterByCourseId(id)
        descriptionService.removeById(id)
        val result = baseMapper.deleteById(id)
        if (result == 0) {
            throw RuntimeException("删除课程失败")
        }
    }

    override fun getCourseFrontList(
        pageCourse: Page<EduCourse>,
        courseFrontVo: CourseFrontVo?
    ): MutableMap<String, Any> {
        val wrapper = QueryWrapper<EduCourse>()
        if (courseFrontVo != null) {
            if (StringUtils.hasText(courseFrontVo.subjectParentId)) {
                wrapper.eq("subject_parent_id", courseFrontVo.subjectParentId)
            }
            if (StringUtils.hasText(courseFrontVo.subjectId)) {
                wrapper.eq("subject_id", courseFrontVo.subjectId)
            }
            if (StringUtils.hasText(courseFrontVo.buyCountSort)) {
                wrapper.orderByDesc("buy_count")
            }
            if (StringUtils.hasText(courseFrontVo.gmtCreateSort)) {
                wrapper.orderByDesc("gmt_create")
            }
            if (StringUtils.hasText(courseFrontVo.priceSort)) {
                wrapper.orderByDesc("price")
            }
        }
        baseMapper.selectPage(pageCourse, wrapper)
        val records = pageCourse.records
        val current = pageCourse.current
        val pages = pageCourse.pages
        val size = pageCourse.size
        val total = pageCourse.total
        val hasNext = pageCourse.hasNext()
        val hasPrevious = pageCourse.hasPrevious()
        val map = mutableMapOf<String, Any>()
        map["items"] = records
        map["current"] = current
        map["pages"] = pages
        map["size"] = size
        map["total"] = total
        map["hasNext"] = hasNext
        map["hasPrevious"] = hasPrevious
        return map
    }

    override fun getBaseCourseInfo(courseId: String): CourseWebVo {
        return baseMapper.getBaseCourseInfo(courseId)
    }
}