package com.hyosakura.eduservice.entity.chapter

/**
 * @author LovesAsuna
 **/
data class ChapterVo (
    var id: String? = null,
    var title : String? = null,
    var children : MutableList<VideoVo> = mutableListOf()
)