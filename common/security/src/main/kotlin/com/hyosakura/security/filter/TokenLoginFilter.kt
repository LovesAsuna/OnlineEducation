package com.hyosakura.security.filter

import com.fasterxml.jackson.databind.ObjectMapper
import com.hyosakura.security.entity.SecurityUser
import com.hyosakura.security.entity.User
import com.hyosakura.security.security.TokenManager
import com.hyosakura.util.R
import com.hyosakura.util.R.Companion.data
import com.hyosakura.util.ResponseUtil
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import java.io.IOException
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * 登录过滤器，继承UsernamePasswordAuthenticationFilter，对用户名密码进行登录校验
 */
class TokenLoginFilter(
    authenticationManager: AuthenticationManager,
    private val tokenManager: TokenManager,
    private val redisTemplate: RedisTemplate<String, Any>
) : UsernamePasswordAuthenticationFilter(authenticationManager) {
    private val objectMapper = ObjectMapper()

    init {
        this.setPostOnly(false)
        this.setRequiresAuthenticationRequestMatcher(AntPathRequestMatcher("/admin/acl/login", "POST"))
    }

    override fun attemptAuthentication(
        request: HttpServletRequest,
        response: HttpServletResponse
    ): Authentication {
        return try {
            val user: User = objectMapper.readValue(request.inputStream, User::class.java)
            authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(
                    user.username,
                    user.password,
                    mutableListOf()
                )
            )
        } catch (e: IOException) {
            throw RuntimeException(e)
        }
    }

    /**
     * 登录成功
     */
    @Throws(IOException::class, ServletException::class)
    override fun successfulAuthentication(
        request: HttpServletRequest,
        response: HttpServletResponse,
        chain: FilterChain,
        auth: Authentication
    ) {
        val user: SecurityUser = auth.principal as SecurityUser
        val token: String = tokenManager.createToken(user.currentUserInfo.username)
        redisTemplate.opsForValue().set(user.currentUserInfo.username!!, user.permissionValueList)
        ResponseUtil.out(response, R.success().data("token", token))
    }

    /**
     * 登录失败
     */
    @Throws(IOException::class, ServletException::class)
    override fun unsuccessfulAuthentication(
        request: HttpServletRequest,
        response: HttpServletResponse,
        exception: AuthenticationException
    ) {
        ResponseUtil.out(response, R.error())
    }
}