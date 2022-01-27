package com.hyosakura.order.controller

import com.hyosakura.order.service.PayLogService
import com.hyosakura.util.R
import com.hyosakura.util.R.Companion.code
import com.hyosakura.util.R.Companion.data
import com.hyosakura.util.R.Companion.message
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

/**
 * 支付日志表 前端控制器
 *
 * @author LovesAsuna
 * @since 2021-12-25
 */
@RestController
@RequestMapping("/eduorder/paylog")
class PayLogController {
    @Autowired
    lateinit var service: PayLogService

    @GetMapping("createNative/{orderNo}")
    fun createNative(@PathVariable orderNo: String): R {
        val map = service.createNative(orderNo)
        return R.success().data(map)
    }

    @GetMapping("queryPayStatus/{orderNo}")
    fun queryPayStatus(@PathVariable orderNo: String): R {
        val result = service.queryPayStatus(orderNo) ?: return R.error().message("支付失败")
        if (result["trade_state"] == "SUCCESS") {
            service.updateOrderStatus(result)
            return R.success().message("支付成功")
        }
        return R.success().code(25000).message("支付中")
    }
}