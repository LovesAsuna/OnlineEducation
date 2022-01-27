package com.hyosakura.msm.controller

import com.hyosakura.msm.service.MsmService
import com.hyosakura.msm.util.RandomUtil
import com.hyosakura.util.R
import com.hyosakura.util.R.Companion.message
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.util.StringUtils
import org.springframework.web.bind.annotation.*
import java.util.concurrent.TimeUnit

/**
 * @author LovesAsuna
 **/
@RestController
@RequestMapping("edumsm/msm")
class MsmController {
    @Autowired
    private lateinit var service: MsmService

    @Autowired
    private lateinit var redisTemplate: RedisTemplate<String, String>

    @GetMapping("send/{phone}")
    fun sendMsm(@PathVariable phone: String): R {
        var code = redisTemplate.opsForValue().get(phone)
        if (StringUtils.hasText(code)) {
            return R.success()
        }
        code = RandomUtil.getFourBitRandom()
        val param = hashMapOf<String, Any>()
        param["code"] = code
        val isSend = service.send(param, phone)
        return if (isSend) {
            redisTemplate.opsForValue().set(phone, code, 5, TimeUnit.MINUTES)
            R.success()
        } else {
            R.error().message("短信发送失败")
        }
    }
}