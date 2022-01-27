package com.hyosakura.eduservice.service.impl

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import com.hyosakura.eduservice.client.VodClient
import com.hyosakura.eduservice.entity.EduVideo
import com.hyosakura.eduservice.mapper.EduVideoMapper
import com.hyosakura.eduservice.service.EduVideoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * 课程视频 服务实现类
 *
 * @author LovesAsuna
 * @since 2021-11-06
 */
@Service
open class EduVideoServiceImpl : ServiceImpl<EduVideoMapper, EduVideo>(), EduVideoService {
    @Autowired
    private lateinit var vodClient: VodClient

    override fun removeVideoByCourseId(id: String) {
        val wrapperVideo = QueryWrapper<EduVideo>()
        wrapperVideo.eq("course_id", id)
        wrapperVideo.select("video_source_id")
        val videoList = baseMapper.selectList(wrapperVideo)
        val videoIds = videoList.stream().filter {
            it.videoSourceId != null
        }.map {
            it.videoSourceId!!
        }.toList()
        if (videoIds.isNotEmpty()) {
            vodClient.deleteBatch(videoIds)
        }
        val wrapper = QueryWrapper<EduVideo>()
        wrapper.eq("course_id", id)
        baseMapper.delete(wrapper)
    }
}