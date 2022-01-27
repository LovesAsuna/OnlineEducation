package com.hyosakura.eduservice.service

import com.baomidou.mybatisplus.extension.service.IService
import com.hyosakura.eduservice.entity.EduVideo

/**
 * 课程视频 服务类
 *
 * @author LovesAsuna
 * @since 2021-11-06
 */
interface EduVideoService : IService<EduVideo> {
    fun removeVideoByCourseId(id: String)
}