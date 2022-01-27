package com.hyosakura.eduservice.service

import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import com.baomidou.mybatisplus.extension.service.IService
import com.hyosakura.eduservice.entity.EduTeacher

/**
 * 讲师 服务类
 *
 * @author LovesAsuna
 * @since 2021-10-14
 */
interface EduTeacherService : IService<EduTeacher> {
    fun getTeacherFrontList(pageTeacher: Page<EduTeacher>): MutableMap<String, Any>
}