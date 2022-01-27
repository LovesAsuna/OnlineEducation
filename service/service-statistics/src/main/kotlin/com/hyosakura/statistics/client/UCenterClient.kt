package com.hyosakura.statistics.client

import com.hyosakura.util.R
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

/**
 * @author LovesAsuna
 **/
@Component
@FeignClient("service-ucenter")
interface UCenterClient {
    @GetMapping("/educenter/member/countRegister/{day}")
    fun countRegister(@PathVariable("day") day: String): R
}