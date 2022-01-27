package com.hyosakura.order.service

import com.baomidou.mybatisplus.extension.service.IService
import com.hyosakura.order.entity.Order

/**
 * 订单 服务类
 *
 * @author LovesAsuna
 * @since 2022-01-14
 */
interface OrderService : IService<Order> {
    fun createOrders(courseId: String, memberId: String): String
}