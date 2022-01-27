package com.hyosakura.statistics.schedule

import com.hyosakura.statistics.service.StatisticsDailyService
import com.hyosakura.statistics.util.DateUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.util.*

/**
 * @author LovesAsuna
 **/
@Component
class ScheduledTask {
    @Autowired
    private lateinit var service: StatisticsDailyService

    @Scheduled(cron = "0 0 1 * * ?")
    fun registerCountTask() {
        service.registerCount(DateUtil.formatDate(DateUtil.addDays(Date(), -1)))
    }
}