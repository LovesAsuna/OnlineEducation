package com.hyosakura.statistics.service

import com.baomidou.mybatisplus.extension.service.IService
import com.hyosakura.statistics.entity.StatisticsDaily

/**
 * 网站统计日数据 服务类
 *
 * @author LovesAsuna
 * @since 2022-01-22
 */
interface StatisticsDailyService : IService<StatisticsDaily> {
    fun registerCount(day: String)

    fun getShowData(type: String, begin: String, end: String): MutableMap<String, Any>
}