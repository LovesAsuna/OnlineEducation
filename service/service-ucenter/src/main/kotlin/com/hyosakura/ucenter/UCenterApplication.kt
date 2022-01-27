package com.hyosakura.ucenter

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
@MapperScan("com.hyosakura.ucenter.mapper")
@EnableDiscoveryClient
@EnableFeignClients
open class UCenterApplication

fun main(args: Array<String>) {
    runApplication<UCenterApplication>(*args)
}