package com.hyosakura.eduservice.service

import com.baomidou.mybatisplus.extension.service.IService
import com.hyosakura.eduservice.entity.EduChapter
import com.hyosakura.eduservice.entity.chapter.ChapterVo

/**
 * 课程 服务类
 *
 * @author LovesAsuna
 * @since 2021-11-06
 */
interface EduChapterService : IService<EduChapter> {
    fun getChapterVideoByCourseId(courseId: String): List<ChapterVo>

    fun deleteChapter(chapterId: String): Boolean

    fun removeChapterByCourseId(id: String)
}