package com.hyosakura.statistics.entity

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
 * 网站统计日数据
 *
 *
 * @author LovesAsuna
 * @since 2022-01-22
 */
@TableName("statistics_daily")
@ApiModel(value = "StatisticsDaily对象", description = "网站统计日数据")
data class StatisticsDaily (
    @ApiModelProperty("主键")
    var id: String? = null,

    @ApiModelProperty("统计日期")
    var dateCalculated: String? = null,

    @ApiModelProperty("注册人数")
    var registerNum: Int? = null,

    @ApiModelProperty("登录人数")
    var loginNum: Int? = null,

    @ApiModelProperty("每日播放视频数")
    var videoViewNum: Int? = null,

    @ApiModelProperty("每日新增课程数")
    var courseNum: Int? = null,

    @ApiModelProperty("创建时间")
    @TableField(fill = FieldFill.INSERT)
    var gmtCreate: LocalDateTime? = null,

    @ApiModelProperty("更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    var gmtModified: LocalDateTime? = null

) : Serializable {
    override fun toString(): String {
        return "StatisticsDaily{" +
                "id=" + id +
                ", dateCalculated=" + dateCalculated +
                ", registerNum=" + registerNum +
                ", loginNum=" + loginNum +
                ", videoViewNum=" + videoViewNum +
                ", courseNum=" + courseNum +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                "}"
    }

    companion object {
        private const val serialVersionUID = 1L
    }
}