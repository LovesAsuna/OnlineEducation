package com.hyosakura.order.service

import com.baomidou.mybatisplus.extension.service.IService
import com.hyosakura.order.entity.PayLog

/**
 * 支付日志表 服务类
 *
 * @author LovesAsuna
 * @since 2021-12-25
 */
interface PayLogService : IService<PayLog> {
    fun createNative(orderNo: String): MutableMap<String, Any>

    fun queryPayStatus(orderNo: String): MutableMap<String, String>?

    fun updateOrderStatus(result: MutableMap<String, String>)
}