package com.hyosakura.eduservice.entity.vo

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import java.io.Serializable
import java.math.BigDecimal

@ApiModel(value = "课程基本信息", description = "编辑课程基本信息的表单对象")
data class CourseInfo(
    @field:ApiModelProperty("课程ID")
    var id: String? = null,

    @field:ApiModelProperty("课程讲师ID")
    var teacherId: String? = null,

    @field:ApiModelProperty("课程专业ID")
    var subjectId: String? = null,

    @field:ApiModelProperty("课程专业父级ID")
    var subjectParentId: String? = null,

    @field:ApiModelProperty("课程标题")
    var title: String? = null,

    @field:ApiModelProperty("课程销售价格，设置为0则可免费观看")
    var price: BigDecimal? = null,

    @field:ApiModelProperty("总课时")
    var lessonNum: Int? = null,

    @field:ApiModelProperty("课程封面图片路径")
    var cover: String? = null,

    @field:ApiModelProperty("课程简介")
    var description: String? = null,
) : Serializable {
    companion object {
        private const val serialVersionUID = 1L
    }
}
