package com.hyosakura.eduservice.entity

import com.baomidou.mybatisplus.annotation.*
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import java.io.Serializable
import java.time.LocalDateTime

/**
 * 讲师
 *
 * @author LovesAsuna
 * @since 2021-10-14
 */
@TableName("edu_teacher")
@ApiModel(value = "EduTeacher对象", description = "讲师")
data class EduTeacher(
    @field:ApiModelProperty("讲师ID")
    @field:TableId(type = IdType.ASSIGN_ID)
    var id: String? = null,

    @field:ApiModelProperty("讲师姓名")
    var name: String? = null,

    @field:ApiModelProperty("讲师简介")
    var intro: String? = null,

    @field:ApiModelProperty("讲师资历,一句话说明讲师")
    var career: String? = null,

    @field:ApiModelProperty("头衔 1高级讲师 2首席讲师")
    var level: Int? = null,

    @field:ApiModelProperty("讲师头像")
    var avatar: String? = null,

    @field:ApiModelProperty("排序")
    var sort: Int? = null,

    @field:ApiModelProperty("逻辑删除 1（true）已删除， 0（false）未删除")
    @field:TableLogic
    var isDeleted: Boolean? = null,

    @field:ApiModelProperty("创建时间")
    @field:TableField(fill = FieldFill.INSERT)
    var gmtCreate: LocalDateTime? = null,

    @field:ApiModelProperty("更新时间")
    @field:TableField(fill = FieldFill.INSERT_UPDATE)
    var gmtModified: LocalDateTime? = null
) : Serializable {
    override fun toString(): String {
        return "EduTeacher{" +
                "id=" + id +
                ", name=" + name +
                ", intro=" + intro +
                ", career=" + career +
                ", level=" + level +
                ", avatar=" + avatar +
                ", sort=" + sort +
                ", isDeleted=" + isDeleted +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                "}"
    }

    companion object {
        private const val serialVersionUID = 1L
    }
}