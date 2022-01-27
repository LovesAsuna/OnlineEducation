package com.hyosakura.eduservice.service.impl

import com.alibaba.excel.EasyExcel
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import com.hyosakura.eduservice.mapper.EduSubjectMapper
import com.hyosakura.eduservice.entity.EduSubject
import com.hyosakura.eduservice.entity.excel.SubjectData
import com.hyosakura.eduservice.entity.subject.OneSubject
import com.hyosakura.eduservice.entity.subject.TwoSubject
import com.hyosakura.eduservice.listener.SubjectExcelListener
import com.hyosakura.eduservice.service.EduSubjectService
import org.springframework.beans.BeanUtils
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

/**
 * 课程科目 服务实现类
 *
 * @author LovesAsuna
 * @since 2021-10-31
 */
@Service
open class EduSubjectServiceImpl : ServiceImpl<EduSubjectMapper, EduSubject>(), EduSubjectService {

    override fun saveSubject(file: MultipartFile, service: EduSubjectService) {
        EasyExcel.read(file.inputStream, SubjectData::class.java, SubjectExcelListener(service)).sheet().doRead()
    }

    override fun getAllOneTwoSubject(): List<OneSubject> {
        val wrapperOne = QueryWrapper<EduSubject>()
        wrapperOne.eq("parent_id", "0")
        val oneSubjectList = baseMapper.selectList(wrapperOne)

        val wrapperTwo = QueryWrapper<EduSubject>()
        wrapperTwo.ne("parent_id", "0")
        val twoSubjectList = baseMapper.selectList(wrapperTwo)

        val finalSubjectList = mutableListOf<OneSubject>()
        oneSubjectList.forEach {one->
            val oneSubject = OneSubject()
            BeanUtils.copyProperties(one!!, oneSubject)
            finalSubjectList.add(oneSubject)
            twoSubjectList.forEach {two->
                if (two!!.parentId == one.id) {
                    val twoSubject = TwoSubject()
                    BeanUtils.copyProperties(two , twoSubject)
                    oneSubject.children.add(twoSubject)
                }
            }
        }

        return finalSubjectList
    }
}