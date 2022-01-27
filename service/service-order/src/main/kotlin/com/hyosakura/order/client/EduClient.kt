package com.hyosakura.order.client

import com.hyosakura.util.ordervo.CourseWebVoOrder
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping

/**
 * @author LovesAsuna
 **/
@Component
@FeignClient("service-edu")
interface EduClient {
    @PostMapping("/eduservice/coursefront/getCourseInfoOrder/{id}")
    fun getCourseInfoOrder(@PathVariable("id") id: String): CourseWebVoOrder
}