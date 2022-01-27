package com.hyosakura.eduservice.listener

import com.alibaba.excel.context.AnalysisContext
import com.alibaba.excel.event.AnalysisEventListener
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import com.hyosakura.eduservice.entity.EduSubject
import com.hyosakura.eduservice.entity.excel.SubjectData
import com.hyosakura.eduservice.service.EduSubjectService


/**
 * @author LovesAsuna
 **/
class SubjectExcelListener(val service: EduSubjectService? = null) : AnalysisEventListener<SubjectData>() {
    override fun invoke(data: SubjectData?, context: AnalysisContext?) {
        if (data == null) {
            throw RuntimeException("文件数据为空")
        }
        var oneSubject = existOneSubject(service!!, data.oneSubjectName!!)
        if (oneSubject == null) {
            oneSubject = EduSubject()
            oneSubject.parentId = "0"
            oneSubject.title = data.oneSubjectName!!
            service.save(oneSubject)
        }

        val pid = oneSubject.id
        var twoSubject = existTwoSubject(service, data.twoSubjectName!!, pid!!)
        if (twoSubject == null) {
            twoSubject = EduSubject()
            twoSubject.parentId = pid
            twoSubject.title = data.twoSubjectName!!
            service.save(twoSubject)
        }
    }

    private fun existOneSubject(service: EduSubjectService, name: String) : EduSubject? {
        val wrapper = QueryWrapper<EduSubject>()
        wrapper.eq("title", name)
        wrapper.eq("parent_id", "0")
        return service.getOne(wrapper)
    }

    private fun existTwoSubject(service: EduSubjectService, name: String, pid : String) : EduSubject? {
        val wrapper = QueryWrapper<EduSubject>()
        wrapper.eq("title", name)
        wrapper.eq("parent_id", pid)
        return service.getOne(wrapper)
    }

    override fun doAfterAllAnalysed(context: AnalysisContext?) {

    }
}