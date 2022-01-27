package com.hyosakura.eduservice.entity.vo

import io.swagger.annotations.ApiModelProperty

data class TeacherQuery(
    @ApiModelProperty("教师名称，模糊查询")
    var name: String? = null,

    @ApiModelProperty("头衔 1高级讲师 2首席讲师")
    var level: Int? = null,

    @ApiModelProperty("查询开始时间", example = "2021-01-01 00:00:00")
    var begin: String? = null,

    @ApiModelProperty("查询结束时间", example = "2021-12-01 00:00:00")
    var end: String? = null
)
