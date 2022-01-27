package com.hyosakura.eduservice.controller

import com.hyosakura.eduservice.service.EduSubjectService
import com.hyosakura.util.R
import com.hyosakura.util.R.Companion.data
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

/**
 * 课程科目 前端控制器
 *
 * @author LovesAsuna
 * @since 2021-10-31
 */
@RestController
@RequestMapping("/eduservice/subject")
class EduSubjectController {
    @Autowired
    private lateinit var service: EduSubjectService

    @PostMapping("addSubject")
    fun addSubject(file: MultipartFile) : R {
        service.saveSubject(file, service)
        return R.success()
    }

    @GetMapping("getAllSubject")
    fun getAllSubject() : R {
        val list = service.getAllOneTwoSubject()
        return R.success().data("list", list)
    }
}