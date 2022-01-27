package com.hyosakura.eduservice.client

import com.hyosakura.util.R
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam

/**
 * @author LovesAsuna
 **/
@FeignClient(name = "service-vod", fallback = VodFileDegradeFeignClient::class)
@Component
interface VodClient {
    @DeleteMapping("/eduvod/video/removeVideo/{id}")
    fun removeVideo(@PathVariable("id") id : String) : R;

    @DeleteMapping("/eduvod/video/removeVideo/delete-batch")
    fun deleteBatch(@RequestParam("videoIdList") videoIdList: List<String>): R
}