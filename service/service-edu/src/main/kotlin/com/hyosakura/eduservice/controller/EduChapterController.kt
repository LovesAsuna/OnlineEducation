package com.hyosakura.eduservice.controller

import com.hyosakura.eduservice.entity.EduChapter
import com.hyosakura.eduservice.service.EduChapterService
import com.hyosakura.util.R
import com.hyosakura.util.R.Companion.data
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

/**
 * 课程 前端控制器
 *
 * @author LovesAsuna
 * @since 2021-11-06
 */
@RestController
@RequestMapping("/eduservice/chapter")
class EduChapterController {
    @Autowired
    private lateinit var service: EduChapterService

    @GetMapping("getChapterVideo/{courseId}")
    fun getChapterVideo(@PathVariable courseId: String): R {
        val list = service.getChapterVideoByCourseId(courseId)
        return R.success().data("allChapterVideo", list)
    }

    @PostMapping("addChapter")
    fun addChapter(@RequestBody chapter: EduChapter): R {
        service.save(chapter)
        return R.success()
    }

    @GetMapping("getChapterInfo/{chapterId}")
    fun getChapterInfo(@PathVariable chapterId: String): R {
        val chapter = service.getById(chapterId)
        return R.success().data("chapter", chapter)
    }

    @PostMapping("updateChapter")
    fun updateChapter(@RequestBody chapter: EduChapter): R {
        service.updateById(chapter)
        return R.success()
    }

    @DeleteMapping("{chapterId}")
    fun deleteChapter(@PathVariable chapterId: String): R {
        val flag = service.deleteChapter(chapterId)
        return if (flag) {
            R.success()
        } else {
            R.error()
        }
    }
}