package com.hyosakura.eduservice.service

import com.baomidou.mybatisplus.extension.service.IService
import com.hyosakura.eduservice.entity.EduSubject
import com.hyosakura.eduservice.entity.subject.OneSubject
import org.springframework.web.multipart.MultipartFile

/**
 * 课程科目 服务类
 *
 * @author LovesAsuna
 * @since 2021-10-31
 */
interface EduSubjectService : IService<EduSubject> {
    fun saveSubject(file: MultipartFile, service: EduSubjectService)

    fun getAllOneTwoSubject(): List<OneSubject>
}