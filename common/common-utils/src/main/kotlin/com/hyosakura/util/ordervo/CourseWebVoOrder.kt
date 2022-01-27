package com.hyosakura.util.ordervo

import io.swagger.annotations.ApiModelProperty
import java.math.BigDecimal

data class CourseWebVoOrder(
    var id: String? = null,

    @ApiModelProperty("课程标题")
    var title: String? = null,

    @ApiModelProperty("课程销售价格，设置为0则可免费观看")
    var price: BigDecimal? = null,

    @ApiModelProperty("总课时")
    var lessonNum: Int? = null,

    @ApiModelProperty("课程封面图片路径")
    var cover: String? = null,

    @ApiModelProperty("销售数量")
    var buyCount: Long? = null,

    @ApiModelProperty("浏览数量")
    var viewCount: Long? = null,

    @ApiModelProperty("课程简介")
    var description: String? = null,

    @ApiModelProperty("讲师ID")
    var teacherId: String? = null,

    @ApiModelProperty("讲师姓名")
    var teacherName: String? = null,

    @ApiModelProperty("讲师资历,一句话说明讲师")
    var intro: String? = null,

    @ApiModelProperty("讲师头像")
    var avatar: String? = null,

    @ApiModelProperty("课程类别ID")
    var subjectLevelOneId: String? = null,

    @ApiModelProperty("课程类别名称")
    var subjectLevelOne: String? = null,

    @ApiModelProperty("课程类别ID")
    var subjectLevelTwoId: String? = null,

    @ApiModelProperty("课程类别名称")
    var subjectLevelTwo: String? = null
)
