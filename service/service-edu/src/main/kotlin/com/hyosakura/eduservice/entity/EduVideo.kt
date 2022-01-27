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
 * 课程视频
 *
 *
 * @author LovesAsuna
 * @since 2021-11-06
 */
@TableName("edu_video")
@ApiModel(value = "EduVideo对象", description = "课程视频")
data class EduVideo(
    @field:ApiModelProperty("视频ID")
    var id: String? = null,

    @field:ApiModelProperty("课程ID")
    var courseId: String? = null,

    @field:ApiModelProperty("章节ID")
    var chapterId: String? = null,

    @field:ApiModelProperty("节点名称")
    var title: String? = null,

    @field:ApiModelProperty("云端视频资源")
    var videoSourceId: String? = null,

    @field:ApiModelProperty("原始文件名称")
    var videoOriginalName: String? = null,

    @field:ApiModelProperty("排序字段")
    var sort: Int? = null,

    @field:ApiModelProperty("播放次数")
    var playCount: Long? = null,

    @field:ApiModelProperty("是否可以试听：0收费 1免费")
    var isFree: Boolean? = null,

    @field:ApiModelProperty("视频时长（秒）")
    var duration: Float? = null,

    @field:ApiModelProperty("Empty未上传 Transcoding转码中  Normal正常")
    var status: String? = null,

    @field:ApiModelProperty("视频源文件大小（字节）")
    var size: Long? = null,

    @field:ApiModelProperty("乐观锁")
    var version: Long? = null,

    @field:ApiModelProperty("创建时间")
    @TableField(fill = FieldFill.INSERT)
    var gmtCreate: LocalDateTime? = null,

    @field:ApiModelProperty("更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    var gmtModified: LocalDateTime? = null
) : Serializable {
    override fun toString(): String {
        return "EduVideo{" +
                "id=" + id +
                ", courseId=" + courseId +
                ", chapterId=" + chapterId +
                ", title=" + title +
                ", videoSourceId=" + videoSourceId +
                ", videoOriginalName=" + videoOriginalName +
                ", sort=" + sort +
                ", playCount=" + playCount +
                ", isFree=" + isFree +
                ", duration=" + duration +
                ", status=" + status +
                ", size=" + size +
                ", version=" + version +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                "}"
    }

    companion object {
        private const val serialVersionUID = 1L
    }
}