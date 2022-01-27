package com.hyosakura.eduservice.entity

import com.baomidou.mybatisplus.annotation.*
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import java.io.Serializable
import java.time.LocalDateTime

/**
 *
 *
 * 课程简介
 *
 *
 * @author LovesAsuna
 * @since 2021-11-06
 */
@TableName("edu_course_description")
@ApiModel(value = "EduCourseDescription对象", description = "课程简介")
data class EduCourseDescription(
    @field:ApiModelProperty("课程ID")
    @TableId("id", type = IdType.INPUT)
    var id: String? = null,

    @field:ApiModelProperty("课程简介")
    var description: String? = null,

    @field:ApiModelProperty("创建时间")
    @field:TableField(fill = FieldFill.INSERT)
    var gmtCreate: LocalDateTime? = null,

    @field:ApiModelProperty("更新时间")
    @field:TableField(fill = FieldFill.INSERT_UPDATE)
    var gmtModified: LocalDateTime? = null
) : Serializable {
    override fun toString(): String {
        return "EduCourseDescription{" +
                "id=" + id +
                ", description=" + description +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                "}"
    }

    companion object {
        private const val serialVersionUID = 1L
    }
}