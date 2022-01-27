package com.hyosakura.eduservice.service.impl

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import com.hyosakura.eduservice.entity.EduTeacher
import com.hyosakura.eduservice.mapper.EduTeacherMapper
import com.hyosakura.eduservice.service.EduTeacherService
import org.springframework.stereotype.Service

/**
 * 讲师 服务实现类
 *
 * @author LovesAsuna
 * @since 2021-10-14
 */
@Service
open class EduTeacherServiceImpl : ServiceImpl<EduTeacherMapper, EduTeacher>(), EduTeacherService {
    override fun getTeacherFrontList(pageTeacher: Page<EduTeacher>): MutableMap<String, Any> {
        val wrapper = QueryWrapper<EduTeacher>()
        wrapper.orderByDesc("id")
        this.baseMapper.selectPage(pageTeacher, wrapper)
        val records = pageTeacher.records
        val current = pageTeacher.current
        val pages = pageTeacher.pages
        val size = pageTeacher.size
        val total = pageTeacher.total
        val hasNext = pageTeacher.hasNext()
        val hasPrevious = pageTeacher.hasPrevious()
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
}