package com.hyosakura.statistics.controller

import com.hyosakura.statistics.service.StatisticsDailyService
import com.hyosakura.util.R
import com.hyosakura.util.R.Companion.data
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

/**
 * 网站统计日数据 前端控制器
 *
 * @author LovesAsuna
 * @since 2022-01-22
 */
@RestController
@RequestMapping("/staservice/sta")
class StatisticsDailyController {
    @Autowired
    private lateinit var service: StatisticsDailyService

    @PostMapping("registerCount/{day}")
    fun registerCount(@PathVariable day: String): R {
        service.registerCount(day)
        return R.success()
    }

    @GetMapping("showData/{type}/{begin}/{end}")
    fun showData(@PathVariable type: String, @PathVariable begin: String, @PathVariable end: String): R {
        val map = service.getShowData(type, begin, end)
        return R.success().data(map)
    }
}