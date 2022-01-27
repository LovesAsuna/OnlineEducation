package com.hyosakura.msm

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

/**
 * @author LovesAsuna
 **/

@SpringBootApplication(exclude = [DataSourceAutoConfiguration::class])
@ComponentScan(basePackages = ["com.hyosakura"])
open class MsmApplication

fun main(args: Array<String>) {
    runApplication<MsmApplication>(*args)
}