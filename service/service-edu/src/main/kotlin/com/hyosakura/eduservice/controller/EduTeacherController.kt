package com.hyosakura.eduservice.controller

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import com.hyosakura.eduservice.entity.EduTeacher
import com.hyosakura.eduservice.entity.vo.TeacherQuery
import com.hyosakura.eduservice.service.EduTeacherService
import com.hyosakura.util.R
import com.hyosakura.util.R.Companion.data
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.util.ObjectUtils
import org.springframework.util.StringUtils
import org.springframework.web.bind.annotation.*

/**
 * 讲师 前端控制器
 *
 * @author LovesAsuna
 * @since 2021-10-14
 */
@Api(description = "讲师管理")
@RestController
@RequestMapping("/eduservice/teacher")
class EduTeacherController {
    @Autowired
    private lateinit var service: EduTeacherService

    @ApiOperation("所有讲师列表")
    @GetMapping("findAll")
    fun findAllTeachers(): R {
        return R.success().data("item", service.list())
    }

    @ApiOperation("逻辑删除讲师")
    @DeleteMapping("{id}")
    fun deleteTeacher(
        @ApiParam(name = "id", value = "讲师ID", required = true)
        @PathVariable
        id: Long
    ): R {
        val flag = service.removeById(id)
        return if (flag) {
            R.success()
        } else {
            R.error()
        }
    }

    @ApiOperation("分页查询讲师")
    @GetMapping("pageTeacher/{current}/{limit}")
    fun pageListTeacher(@PathVariable current: Long, @PathVariable limit: Long): R {
        val pageTeacher = Page<EduTeacher>(current, limit)
        service.page(pageTeacher)
        val total = pageTeacher.total
        val records = pageTeacher.records
        return R.success().data("total", total).data("rows", records)
    }

    @ApiOperation("分页条件查询讲师")
    @PostMapping("pageTeacherCondition/{current}/{limit}")
    fun pageTeacherCondition(
        @PathVariable current: Long,
        @PathVariable limit: Long,
        @RequestBody(required = false) query: TeacherQuery?
    ): R {
        val pageTeacher = Page<EduTeacher>(current, limit)
        val wrapper = QueryWrapper<EduTeacher>()
        if (query != null) {
            val name = query.name
            val level = query.level
            val begin = query.begin
            val end = query.end
            if (StringUtils.hasText(name)) {
                wrapper.like("name", name)
            }
            if (!ObjectUtils.isEmpty(level)) {
                wrapper.eq("level", level)
            }
            if (StringUtils.hasText(begin)) {
                wrapper.ge("gmt_create", begin)
            }
            if (StringUtils.hasText(end)) {
                wrapper.le("gmt_create", end)
            }
        }
        wrapper.orderByDesc("gmt_create")
        service.page(pageTeacher, wrapper)
        val total = pageTeacher.total
        val records = pageTeacher.records
        return R.success().data("total", total).data("rows", records)
    }

    @ApiOperation("添加讲师")
    @PostMapping("addTeacher")
    fun addTeacher(@RequestBody teacher: EduTeacher): R {
        val save = service.save(teacher)
        return if (save) {
            R.success()
        } else {
            R.error()
        }
    }

    @ApiOperation("根据id查询讲师")
    @GetMapping("getTeacher/{id}")
    fun getTeacher(
        @ApiParam(name = "id", value = "讲师ID", required = true)
        @PathVariable
        id: String
    ): R {
        val teacher = service.getById(id)
        return R.success().data("teacher", teacher)
    }

    @ApiOperation("根据id修改讲师")
    @PostMapping("updateTeacher")
    fun updateTeacher(
        @ApiParam(name = "teacher", value = "讲师对象", required = true)
        @RequestBody
        teacher: EduTeacher
    ): R {
        val flag = service.updateById(teacher)
        return if (flag) {
            R.success()
        } else {
            R.error()
        }
    }
}
