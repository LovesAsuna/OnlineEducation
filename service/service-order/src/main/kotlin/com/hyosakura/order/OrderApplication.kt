package com.hyosakura.order

import org.mybatis.spring.annotation.MapperScan
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.ComponentScan

/**
 * @author LovesAsuna
 **/
@SpringBootApplication
@ComponentScan(basePackages = ["com.hyosakura"])
@MapperScan("com.hyosakura.order.mapper")
@EnableDiscoveryClient
@EnableFeignClients
open class OrderApplication

fun main(args: Array<String>) {
    runApplication<OrderApplication>(*args)
}