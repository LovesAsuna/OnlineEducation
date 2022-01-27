package com.hyosakura.order.client

import com.hyosakura.util.ordervo.UcenterMemberOrder
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping

/**
 * @author LovesAsuna
 **/
@Component
@FeignClient("service-ucenter")
interface UcenterClient {
    @PostMapping("/educenter/member/getUserInfoOrder/{id}")
    fun getUserInfoOrder(@PathVariable("id") id: String): UcenterMemberOrder
}