package com.hyosakura.canal

import com.hyosakura.canal.client.CanalClient
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import javax.annotation.Resource

/**
 * @author LovesAsuna
 **/
@SpringBootApplication
open class CanalApplication: CommandLineRunner {
    @Resource
    lateinit var canalClient: CanalClient

    override fun run(vararg args: String?) {
        canalClient.run()
    }
}

fun main(args: Array<String>) {
    runApplication<CanalApplication>(*args)
}