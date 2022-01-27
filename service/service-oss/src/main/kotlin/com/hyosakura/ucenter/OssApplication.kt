package com.hyosakura.ucenter

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.context.annotation.ComponentScan


/**
 * @author LovesAsuna
 **/
@SpringBootApplication(exclude = [DataSourceAutoConfiguration::class])
@ComponentScan(basePackages = ["com.hyosakura"])
@EnableDiscoveryClient
open class OssApplication

fun main(args: Array<String>) {
    runApplication<OssApplication>(*args)
}