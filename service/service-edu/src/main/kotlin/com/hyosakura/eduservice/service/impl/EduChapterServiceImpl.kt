package com.hyosakura.eduservice.service.impl

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import com.hyosakura.eduservice.entity.EduChapter
import com.hyosakura.eduservice.entity.EduVideo
import com.hyosakura.eduservice.entity.chapter.ChapterVo
import com.hyosakura.eduservice.entity.chapter.VideoVo
import com.hyosakura.eduservice.mapper.EduChapterMapper
import com.hyosakura.eduservice.service.EduChapterService
import com.hyosakura.eduservice.service.EduVideoService
import org.springframework.beans.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * 课程 服务实现类
 *
 * @author LovesAsuna
 * @since 2021-11-06
 */
@Service
open class EduChapterServiceImpl : ServiceImpl<EduChapterMapper, EduChapter>(), EduChapterService {
    @Autowired
    private lateinit var service: EduVideoService

    override fun getChapterVideoByCourseId(courseId: String): List<ChapterVo> {
        val wrapperChapter = QueryWrapper<EduChapter>()
        wrapperChapter.eq("course_id", courseId)
        val eduChapterList = baseMapper.selectList(wrapperChapter)
        val wrapperVideo = QueryWrapper<EduVideo>()
        wrapperVideo.eq("course_id", courseId)
        val eduVideoList = service.list(wrapperVideo)
        val finalList = mutableListOf<ChapterVo>()
        for (chapter in eduChapterList) {
            val chapterVo = ChapterVo()
            BeanUtils.copyProperties(chapter, chapterVo)
            finalList.add(chapterVo)
            for (video in eduVideoList) {
                if (video.chapterId == chapter.id) {
                    val videoVo = VideoVo()
                    BeanUtils.copyProperties(video, videoVo)
                    chapterVo.children.add(videoVo)
                }
            }
        }
        return finalList
    }

    override fun deleteChapter(chapterId: String): Boolean {
        val wrapper = QueryWrapper<EduVideo>()
        wrapper.eq("chapter_id", chapterId)
        val count = service.count(wrapper)
        if (count > 0) {
            throw RuntimeException("不能删除")
        } else {
            val result = baseMapper.deleteById(chapterId)
            return result > 0
        }
    }

    override fun removeChapterByCourseId(id: String) {
        val wrapper = QueryWrapper<EduChapter>()
        wrapper.eq("course_id", id)
        baseMapper.delete(wrapper)
    }
}