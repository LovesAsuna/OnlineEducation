package com.hyosakura.acl

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
@MapperScan("com.hyosakura.acl.mapper")
@EnableDiscoveryClient
@EnableFeignClients
open class AclApplication

fun main(args: Array<String>) {
    runApplication<AclApplication>(*args)
}