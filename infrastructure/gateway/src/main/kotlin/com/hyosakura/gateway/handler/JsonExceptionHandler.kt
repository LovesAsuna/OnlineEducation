package com.hyosakura.gateway.handler

import org.springframework.boot.autoconfigure.web.ErrorProperties
import org.springframework.boot.autoconfigure.web.ResourceProperties
import org.springframework.boot.autoconfigure.web.reactive.error.DefaultErrorWebExceptionHandler
import org.springframework.boot.web.reactive.error.ErrorAttributes
import org.springframework.context.ApplicationContext
import org.springframework.web.reactive.function.server.*

/**
 * 自定义异常处理
 * 异常时用JSON代替HTML异常信息
 */
class JsonExceptionHandler(
    errorAttributes: ErrorAttributes?, resourceProperties: ResourceProperties?,
    errorProperties: ErrorProperties?, applicationContext: ApplicationContext?
) : DefaultErrorWebExceptionHandler(errorAttributes, resourceProperties, errorProperties, applicationContext) {
    /**
     * 获取异常属性
     */
    override fun getErrorAttributes(request: ServerRequest, includeStackTrace: Boolean): Map<String, Any?> {
        val map: MutableMap<String, Any?> = HashMap()
        map["success"] = false
        map["code"] = 20005
        map["message"] = "网关失败"
        map["data"] = null
        return map
    }

    /**
     * 指定响应处理方法为JSON处理的方法
     * @param errorAttributes
     */
    override fun getRoutingFunction(errorAttributes: ErrorAttributes): RouterFunction<ServerResponse> {
        return RouterFunctions.route(RequestPredicates.all()) { request: ServerRequest? -> renderErrorResponse(request) }
    }

    /**
     * 根据code获取对应的HttpStatus
     * @param errorAttributes
     */
    override fun getHttpStatus(errorAttributes: Map<String, Any>): Int {
        return 200
    }
}