package com.hyosakura.eduservice.entity.subject

data class OneSubject(
    var id: String? = null,
    var title: String? = null,
    val children: MutableList<TwoSubject> = mutableListOf()
)
