package com.hyosakura.eduservice.entity.excel

import com.alibaba.excel.annotation.ExcelProperty

data class SubjectData(
    @field:ExcelProperty(index = 0)
    var oneSubjectName: String? = null,
    @field:ExcelProperty(index = 1)
    var twoSubjectName: String? = null,
)
