package com.hyosakura.eduservice.entity

import com.baomidou.mybatisplus.annotation.FieldFill
import com.baomidou.mybatisplus.annotation.TableField
import com.baomidou.mybatisplus.annotation.TableName
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import java.io.Serializable
import java.math.BigDecimal
import java.time.LocalDateTime

/**
 * 课程
 *
 * @author LovesAsuna
 * @since 2021-11-06
 */
@TableName("edu_course")
@ApiModel(value = "EduCourse对象", description = "课程")
data class EduCourse(
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

    @field:ApiModelProperty("销售数量")
    var buyCount: Long? = null,

    @field:ApiModelProperty("浏览数量")
    var viewCount: Long? = null,

    @field:ApiModelProperty("乐观锁")
    var version: Long? = null,

    @field:ApiModelProperty("课程状态 Draft未发布  Normal已发布")
    var status: String? = null,

    @field:ApiModelProperty("逻辑删除 1（true）已删除， 0（false）未删除")
    var isDeleted: Int? = null,

    @field:ApiModelProperty("创建时间")
    @field:TableField(fill = FieldFill.INSERT)
    var gmtCreate: LocalDateTime? = null,

    @field:ApiModelProperty("更新时间")
    @field:TableField(fill = FieldFill.INSERT_UPDATE)
    var gmtModified: LocalDateTime? = null

) : Serializable {
    override fun toString(): String {
        return "EduCourse{" +
                "id=" + id +
                ", teacherId=" + teacherId +
                ", subjectId=" + subjectId +
                ", subjectParentId=" + subjectParentId +
                ", title=" + title +
                ", price=" + price +
                ", lessonNum=" + lessonNum +
                ", cover=" + cover +
                ", buyCount=" + buyCount +
                ", viewCount=" + viewCount +
                ", version=" + version +
                ", status=" + status +
                ", isDeleted=" + isDeleted +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                "}"
    }

    companion object {
        private const val serialVersionUID = 1L
    }
}