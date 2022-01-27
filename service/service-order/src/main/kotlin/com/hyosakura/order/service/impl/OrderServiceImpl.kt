package com.hyosakura.order.service.impl

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import com.hyosakura.order.client.EduClient
import com.hyosakura.order.client.UcenterClient
import com.hyosakura.order.entity.Order
import com.hyosakura.order.mapper.OrderMapper
import com.hyosakura.order.service.OrderService
import com.hyosakura.order.util.OrderNoUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * 订单 服务实现类
 *
 * @author LovesAsuna
 * @since 2022-01-14
 */
@Service
open class OrderServiceImpl : ServiceImpl<OrderMapper, Order>(), OrderService {
    @Autowired
    private lateinit var eduClient: EduClient

    @Autowired
    private lateinit var ucenterClient: UcenterClient

    override fun createOrders(courseId: String, memberId: String): String {
        val userInfoOrder = ucenterClient.getUserInfoOrder(memberId)
        val courseInfoOrder = eduClient.getCourseInfoOrder(courseId)
        val order = Order()
        order.orderNo = OrderNoUtil.getOrderNo()
        order.courseId = courseId
        order.courseTitle = courseInfoOrder.title
        order.courseCover = courseInfoOrder.cover
        order.teacherName = courseInfoOrder.teacherName
        order.totalFee = courseInfoOrder.price
        order.memberId = memberId
        order.mobile = userInfoOrder.mobile
        order.nickname = userInfoOrder.nickname
        order.status = 0
        order.payType = 1
        baseMapper.insert(order)
        return order.orderNo!!
    }
}