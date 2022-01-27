package com.hyosakura.statistics.util

import java.text.SimpleDateFormat
import java.util.*

/**
 * 日期操作工具类
 */
object DateUtil {
    private const val dateFormat = "yyyy-MM-dd"

    /**
     * 格式化日期
     *
     * @param date
     * @return
     */
    fun formatDate(date: Date): String {
        val sdf = SimpleDateFormat(dateFormat)
        return sdf.format(date)
    }

    /**
     * 在日期date上增加amount天 。
     *
     * @param date   处理的日期，非null
     * @param amount 要加的天数，可能为负数
     */
    fun addDays(date: Date, amount: Int): Date {
        val now = Calendar.getInstance()
        now.time = date
        now[Calendar.DATE] = now[Calendar.DATE] + amount
        return now.time
    }
}