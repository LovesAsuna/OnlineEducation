package com.hyosakura.order.controller

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import com.hyosakura.order.entity.Order
import com.hyosakura.order.service.OrderService
import com.hyosakura.util.JwtUtil
import com.hyosakura.util.R
import com.hyosakura.util.R.Companion.data
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest

/**
 * @author LovesAsuna
 **/
@RestController
@RequestMapping("/eduorder/order")
class OrderController {
    @Autowired
    lateinit var service: OrderService

    @PostMapping("createOrder/{courseId}")
    fun saveOrder(@PathVariable courseId: String, request: HttpServletRequest): R {
        val orderNo = service.createOrders(courseId, JwtUtil.getMemberIdByJwtToken(request))
        return R.success().data("orderId", orderNo)
    }

    @GetMapping("getOrderInfo/{orderId}")
    fun getOrderInfo(@PathVariable orderId: String): R {
        val wrapper = QueryWrapper<Order>()
        wrapper.eq("order_no", orderId)
        val order = service.getOne(wrapper)
        return R.success().data("item", order)
    }

    @GetMapping("isBuyCourse/{courseId}/{memberId}")
    fun isBuyCourse(@PathVariable courseId: String, @PathVariable memberId: String): Boolean {
        val wrapper = QueryWrapper<Order>()
        wrapper.eq("course_id", courseId)
        wrapper.eq("member_id", memberId)
        wrapper.eq("status", 1)
        return service.count(wrapper) > 0
    }
}