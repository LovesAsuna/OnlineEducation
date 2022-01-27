package com.hyosakura.statistics

import org.mybatis.spring.annotation.MapperScan
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.ComponentScan
import org.springframework.scheduling.annotation.EnableScheduling

/**
 * @author LovesAsuna
 **/
@SpringBootApplication
@ComponentScan(basePackages = ["com.hyosakura"])
@MapperScan("com.hyosakura.statistics.mapper")
@EnableDiscoveryClient
@EnableFeignClients
@EnableScheduling
open class StatisticsApplication

fun main(args: Array<String>) {
    runApplication<StatisticsApplication>(*args)
}