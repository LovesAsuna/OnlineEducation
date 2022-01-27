package com.hyosakura.eduservice.entity.vo

import io.swagger.annotations.ApiModel
import java.io.Serializable

@ApiModel("课程发布信息")
data class CoursePublishVo(
    var title : String? = null,
    var cover : String? = null,
    var lessonNum : Int? = null,
    var subjectLevelOne : String? = null,
    var subjectLevelTwo : String? = null,
    var teacherName : String? = null,
    var price : String? = null,
) : Serializable {
    companion object {
        private const val serialVersionUID = 1L;
    }
}
