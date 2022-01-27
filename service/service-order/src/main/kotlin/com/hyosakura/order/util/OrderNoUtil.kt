package com.hyosakura.order.util

import java.text.SimpleDateFormat
import java.util.*

object OrderNoUtil {
    fun getOrderNo(): String {
     val sdf = SimpleDateFormat("yyyyMMddHHmmssSSS")
        val newDate = sdf.format(Date())
        val builder = StringBuilder()
        val random = Random()
        for (i in 0..2) {
            builder.append(random.nextInt(10))
        }
        return newDate + builder.toString()
    }
}