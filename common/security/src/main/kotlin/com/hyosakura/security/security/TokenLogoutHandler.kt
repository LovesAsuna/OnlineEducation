package com.hyosakura.security.security

import com.hyosakura.util.R
import com.hyosakura.util.ResponseUtil
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.logout.LogoutHandler
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * 登出业务逻辑类
 */
class TokenLogoutHandler(
    private val tokenManager: TokenManager,
    private val redisTemplate: RedisTemplate<String, Any>
) : LogoutHandler {
    override fun logout(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authentication: Authentication
    ) {
        val token: String = request.getHeader("token")
        tokenManager.removeToken(token)

        //清空当前用户缓存中的权限数据
        val userName = tokenManager.getUserFromToken(token)
        redisTemplate.delete(userName)
        ResponseUtil.out(response, R.success())
    }
}