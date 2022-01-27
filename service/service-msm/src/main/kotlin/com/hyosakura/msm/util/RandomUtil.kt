package com.hyosakura.msm.util

import java.text.DecimalFormat
import java.util.*

object RandomUtil {
    private val random = Random()
    private val fourdf = DecimalFormat("0000")
    private val sixdf = DecimalFormat("000000")

    fun getFourBitRandom(): String {
        return fourdf.format(random.nextInt(10000))
    }

    fun getSixBitRandom(): String {
        return sixdf.format(random.nextInt(1000000))
    }


}