package com.hyosakura.eduservice.controller

import com.hyosakura.eduservice.client.VodClient
import com.hyosakura.eduservice.entity.EduChapter
import com.hyosakura.eduservice.entity.EduVideo
import com.hyosakura.eduservice.service.EduChapterService
import com.hyosakura.eduservice.service.EduVideoService
import com.hyosakura.util.R
import com.hyosakura.util.R.Companion.data
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.util.StringUtils
import org.springframework.web.bind.annotation.*

/**
 * 课程视频 前端控制器
 *
 * @author LovesAsuna
 * @since 2021-11-06
 */
@RestController
@RequestMapping("/eduservice/video")
class EduVideoController {
    @Autowired
    private lateinit var service: EduVideoService

    @Autowired
    private lateinit var vodClient: VodClient

    @PostMapping("addVideo")
    fun addVideo(@RequestBody eduVideo: EduVideo): R {
        service.save(eduVideo)
        return R.success()
    }

    @GetMapping("getVideoInfo/{videoId}")
    fun getVideoInfo(@PathVariable videoId: String): R {
        val video = service.getById(videoId)
        return R.success().data("video", video)
    }

    @PostMapping("updateVideo")
    fun updateChapter(@RequestBody eduVideo: EduVideo): R {
        service.updateById(eduVideo)
        return R.success()
    }

    @DeleteMapping("{id}")
    fun deleteVideo(@PathVariable id: String): R {
        val video = service.getById(id)
        val videoSourceId = video.videoSourceId
        if (StringUtils.hasText(videoSourceId)) {
            val result = vodClient.removeVideo(videoSourceId!!)
            if (result.code == 20001) {
                throw RuntimeException("熔断器: ${result.message}")
            }
        }
        service.removeById(id)
        return R.success()
    }
}