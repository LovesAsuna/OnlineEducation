package com.hyosakura.eduservice

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
@EnableDiscoveryClient
@EnableFeignClients
open class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}