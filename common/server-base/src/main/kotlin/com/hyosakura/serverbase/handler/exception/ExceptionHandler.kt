package com.hyosakura.serverbase.handler.exception

import com.hyosakura.util.R
import com.hyosakura.util.R.Companion.message
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody

/**
 * @author LovesAsuna
 **/
@ControllerAdvice
class ExceptionHandler {
    private val log = LoggerFactory.getLogger(this.javaClass)

    @ExceptionHandler(Exception::class)
    @ResponseBody
    fun error(e: Exception): R {
        log.error(e.message)
        e.printStackTrace()
        return R.error().message(e.message ?: "执行了全局异常处理")
    }
}