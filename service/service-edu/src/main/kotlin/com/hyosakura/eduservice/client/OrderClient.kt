package com.hyosakura.eduservice.client

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

/**
 * @author LovesAsuna
 **/
@Component
@FeignClient("service-order")
interface OrderClient {
    @GetMapping("/eduorder/order/isBuyCourse/{courseId}/{memberId}")
    fun isBuyCourse(@PathVariable("courseId") courseId: String, @PathVariable("memberId") memberId: String): Boolean
}