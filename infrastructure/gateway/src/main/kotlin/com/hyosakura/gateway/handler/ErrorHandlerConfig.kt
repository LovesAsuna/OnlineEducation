package com.hyosakura.gateway.handler

import org.springframework.beans.factory.ObjectProvider
import org.springframework.boot.autoconfigure.web.ResourceProperties
import org.springframework.boot.autoconfigure.web.ServerProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.web.reactive.error.ErrorAttributes
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.codec.ServerCodecConfigurer
import org.springframework.web.reactive.result.view.ViewResolver

/**
 * 覆盖默认的异常处理
 *
 * @author yinjihuan
 */
@Configuration
@EnableConfigurationProperties(ServerProperties::class, ResourceProperties::class)
open class ErrorHandlerConfig(
    private val serverProperties: ServerProperties,
    private val resourceProperties: ResourceProperties,
    viewResolversProvider: ObjectProvider<List<ViewResolver>>,
    serverCodecConfigurer: ServerCodecConfigurer,
    private val applicationContext: ApplicationContext
) {
    private val viewResolvers: List<ViewResolver>
    private val serverCodecConfigurer: ServerCodecConfigurer

    init {
        viewResolvers = viewResolversProvider.getIfAvailable { emptyList() }
        this.serverCodecConfigurer = serverCodecConfigurer
    }

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    open fun errorWebExceptionHandler(errorAttributes: ErrorAttributes?): ErrorWebExceptionHandler {
        val exceptionHandler = JsonExceptionHandler(
            errorAttributes,
            resourceProperties,
            serverProperties.error,
            applicationContext
        )
        exceptionHandler.setViewResolvers(viewResolvers)
        exceptionHandler.setMessageWriters(serverCodecConfigurer.writers)
        exceptionHandler.setMessageReaders(serverCodecConfigurer.readers)
        return exceptionHandler
    }
}