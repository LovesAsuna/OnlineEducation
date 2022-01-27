package com.hyosakura.eduservice.entity.vo

import io.swagger.annotations.ApiModelProperty

data class CourseQuery(
    @ApiModelProperty("课程名称，模糊查询")
    var title: String? = null,

    @ApiModelProperty("状态 Normal已发布 Draft未发布")
    var status: String? = null,

    @ApiModelProperty("课时数")
    var lessonNum: Int? = null,

    @ApiModelProperty("查询开始时间", example = "2021-01-01 00:00:00")
    var begin: String? = null,

    @ApiModelProperty("查询结束时间", example = "2021-12-01 00:00:00")
    var end: String? = null
)
