package com.hyosakura.gateway.filter

import com.google.gson.JsonObject
import org.springframework.cloud.gateway.filter.GatewayFilterChain
import org.springframework.cloud.gateway.filter.GlobalFilter
import org.springframework.core.Ordered
import org.springframework.http.server.reactive.ServerHttpResponse
import org.springframework.stereotype.Component
import org.springframework.util.AntPathMatcher
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

/**
 * 全局Filter，统一处理会员登录与外部不允许访问的服务
 */
@Component
class AuthGlobalFilter : GlobalFilter, Ordered {
    private val antPathMatcher = AntPathMatcher()
    override fun filter(exchange: ServerWebExchange, chain: GatewayFilterChain): Mono<Void> {
        val request = exchange.request
        val path = request.uri.path
        //谷粒学院api接口，校验用户必须登录
        if (antPathMatcher.match("/api/**/auth/**", path)) {
            val tokenList = request.headers["token"]
            return if (null == tokenList) {
                val response = exchange.response
                out(response)
            } else {
               // Boolean isCheck = JwtUtils.checkToken(tokenList.get(0));
               // if(!isCheck) {
                val response = exchange.response
                out(response)
            // }
            }
        }
        //内部服务接口，不允许外部访问
        if (antPathMatcher.match("/**/inner/**", path)) {
            val response = exchange.response
            return out(response)
        }
        return chain.filter(exchange)
    }

    override fun getOrder(): Int {
        return 0
    }

    private fun out(response: ServerHttpResponse): Mono<Void> {
        val message = JsonObject()
        message.addProperty("success", false)
        message.addProperty("code", 28004)
        message.addProperty("data", "鉴权失败")
        val bits: ByteArray = message.toString().toByteArray(Charsets.UTF_8)
        val buffer = response.bufferFactory().wrap(bits)
        //response.setStatusCode(HttpStatus.UNAUTHORIZED);
        //指定编码，否则在浏览器中会中文乱码
        response.headers.add("Content-Type", "application/json;charset=UTF-8")
        return response.writeWith(Mono.just(buffer))
    }
}