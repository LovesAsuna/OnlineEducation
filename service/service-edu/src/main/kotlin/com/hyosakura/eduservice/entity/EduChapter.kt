package com.hyosakura.eduservice.entity

import com.baomidou.mybatisplus.annotation.FieldFill
import com.baomidou.mybatisplus.annotation.TableField
import com.baomidou.mybatisplus.annotation.TableName
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import java.io.Serializable
import java.time.LocalDateTime

/**
 *
 *
 * 课程
 *
 *
 * @author LovesAsuna
 * @since 2021-11-06
 */
@TableName("edu_chapter")
@ApiModel(value = "EduChapter对象", description = "课程")
data class EduChapter(
    @field:ApiModelProperty("章节ID")
    var id: String? = null,

    @field:ApiModelProperty("课程ID")
    var courseId: String? = null,

    @field:ApiModelProperty("章节名称")
    var title: String? = null,

    @field:ApiModelProperty("显示排序")
    var sort: Int? = null,

    @field:ApiModelProperty("创建时间")
    @TableField(fill = FieldFill.INSERT)
    var gmtCreate: LocalDateTime? = null,

    @field:ApiModelProperty("更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    var gmtModified: LocalDateTime? = null
) : Serializable {
    override fun toString(): String {
        return "EduChapter{" +
                "id=" + id +
                ", courseId=" + courseId +
                ", title=" + title +
                ", sort=" + sort +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                "}"
    }

    companion object {
        private const val serialVersionUID = 1L
    }
}