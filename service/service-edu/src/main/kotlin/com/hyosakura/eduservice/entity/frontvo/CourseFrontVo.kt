package com.hyosakura.eduservice.entity.frontvo

import io.swagger.annotations.ApiModelProperty

data class CourseFrontVo(
    @ApiModelProperty("课程名称")
    var title: String? = null,

    @ApiModelProperty("讲师id")
    var teacherId: String? = null,

    @ApiModelProperty("一级类别id")
    var subjectParentId: String? = null,

    @ApiModelProperty("二级类别id")
    var subjectId: String? = null,

    @ApiModelProperty("销量排序")
    var buyCountSort: String? = null,

    @ApiModelProperty("最新时间排序")
    var gmtCreateSort: String? = null,

    @ApiModelProperty("价格排序")
    var priceSort: String? = null,
)
