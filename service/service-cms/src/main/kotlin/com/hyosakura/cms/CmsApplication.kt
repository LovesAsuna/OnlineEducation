package com.hyosakura.cms

import org.mybatis.spring.annotation.MapperScan
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.context.annotation.ComponentScan


/**
 * @author LovesAsuna
 **/
@SpringBootApplication
@ComponentScan(basePackages = ["com.hyosakura"])
@EnableDiscoveryClient
@MapperScan("com.hyosakura.cms.mapper")
open class CmsApplication

fun main(args: Array<String>) {
    runApplication<CmsApplication>(*args)
}