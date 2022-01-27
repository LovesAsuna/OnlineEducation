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
 * 课程科目
 *
 *
 * @author LovesAsuna
 * @since 2021-10-31
 */
@TableName("edu_subject")
@ApiModel(value = "EduSubject对象", description = "课程科目")
data class EduSubject(
    @field:ApiModelProperty("课程类别ID")
    var id: String? = null,

    @field:ApiModelProperty("类别名称")
    var title: String? = null,

    @field:ApiModelProperty("父ID")
    var parentId: String? = null,

    @field:ApiModelProperty("排序字段")
    var sort: Int? = null,

    @field:ApiModelProperty("创建时间")
    @TableField(fill = FieldFill.INSERT)
    var gmtCreate: LocalDateTime? = null,

    @field:ApiModelProperty("更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    var gmtModified: LocalDateTime? = null
) : Serializable {
    override fun toString(): String {
        return "EduSubject{" +
                "id=" + id +
                ", title=" + title +
                ", parentId=" + parentId +
                ", sort=" + sort +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                "}"
    }

    companion object {
        private const val serialVersionUID = 1L
    }
}