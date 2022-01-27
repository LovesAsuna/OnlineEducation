package com.hyosakura.order.service.impl

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import com.fasterxml.jackson.databind.ObjectMapper
import com.github.wxpay.sdk.WXPayUtil
import com.hyosakura.order.entity.Order
import com.hyosakura.order.entity.PayLog
import com.hyosakura.order.mapper.PayLogMapper
import com.hyosakura.order.service.OrderService
import com.hyosakura.order.service.PayLogService
import com.hyosakura.order.util.HttpClient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.time.LocalDateTime


/**
 * 支付日志表 服务实现类
 *
 * @author LovesAsuna
 * @since 2021-12-25
 */
@Service
open class PayLogServiceImpl : ServiceImpl<PayLogMapper, PayLog>(), PayLogService {
    @Autowired
    private lateinit var orderService: OrderService
    override fun createNative(orderNo: String): MutableMap<String, Any> {
        try {
            val wrapper = QueryWrapper<Order>()
            wrapper.eq("order_no", orderNo)
            val order = orderService.getOne(wrapper)
            val map = mutableMapOf<String, String>()
            map["appid"] = "wx74862e0dfcf69954";
            map["mch_id"] = "1558950191";
            map["nonce_str"] = WXPayUtil.generateNonceStr();
            map["body"] = order.courseTitle ?: ""
            map["out_trade_no"] = orderNo;
            map["total_fee"] = order.totalFee!!.multiply(BigDecimal("100")).longValueExact().toString()
            map["spbill_create_ip"] = "127.0.0.1";
            map["notify_url"] = "http://guli.shop/api/order/weixinPay/weixinNotify\n";
            map["trade_type"] = "NATIVE";

            val client = HttpClient("https://api.mch.weixin.qq.com/pay/unifiedorder")
            client.xmlParam = WXPayUtil.generateSignedXml(map,"T6m9iK73b0kn9g5v426MKfHQH7X8rKwb")
            client.isHttps = true

            //执行post请求发送
            client.post()
            val xml: String = client.content!!

            //把xml格式转换成map集合，把map集合返回
            val resultMap = WXPayUtil.xmlToMap(xml)

            //最终返回数据 的封装
            val dataMap: MutableMap<String, Any> = mutableMapOf()
            dataMap["out_trade_no"] = orderNo
            dataMap["course_id"] = order.courseId!!
            dataMap["total_fee"] = order.totalFee!!
            //返回二维码操作状态码
            dataMap["result_code"] = resultMap["result_code"]!!
            //二维码地址
            dataMap["code_url"] = resultMap["code_url"]!!

            return dataMap
        } catch (e: Exception) {
            throw RuntimeException("生成二维码失败！", e)
        }

    }

    override fun queryPayStatus(orderNo: String): MutableMap<String, String>? {
        //1、封装参数
        val m: MutableMap<String, String> = mutableMapOf()
        m["appid"] = "wx74862e0dfcf69954"
        m["mch_id"] = "1558950191"
        m["out_trade_no"] = orderNo
        m["nonce_str"] = WXPayUtil.generateNonceStr()

        //2.发送httpClient
        val client = HttpClient("https://api.mch.weixin.qq.com/pay/orderquery")
        return try {
            client.xmlParam = WXPayUtil.generateSignedXml(m, "T6m9iK73b0kn9g5v426MKfHQH7X8rKwb")
            client.isHttps = true
            client.post()

            //3.得到请求返回的内容
            val xml: String = client.content!!

            //4.转成map返回
            WXPayUtil.xmlToMap(xml)
        } catch (e: Exception) {
            null
        }
    }

    override fun updateOrderStatus(result: MutableMap<String, String>) {
        val orderNo = result["out_trade_no"]
        val wrapper = QueryWrapper<Order>()
        wrapper.eq("order_no", orderNo)
        val order = orderService.getOne(wrapper)
        if (order.status == 1) {
            return
        }
        order.status = 1
        orderService.updateById(order)
        val payLog = PayLog()
        payLog.orderNo = orderNo
        payLog.payTime = LocalDateTime.now()
        payLog.payType = 1
        payLog.totalFee = order.totalFee
        payLog.tradeState = result["trade_state"]
        payLog.transactionId = result["transaction_id"]
        payLog.attr = ObjectMapper().writeValueAsString(result)
        baseMapper.insert(payLog)

    }
}