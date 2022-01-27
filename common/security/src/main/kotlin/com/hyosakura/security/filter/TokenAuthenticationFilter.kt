package com.hyosakura.security.filter

import com.hyosakura.security.security.TokenManager
import com.hyosakura.util.R
import com.hyosakura.util.ResponseUtil
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import org.springframework.util.StringUtils
import java.io.IOException
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * 访问过滤器
 */
class TokenAuthenticationFilter(
    authManager: AuthenticationManager,
    private val tokenManager: TokenManager,
    private val redisTemplate: RedisTemplate<String ,Any>
) : BasicAuthenticationFilter(authManager) {
    @Throws(IOException::class, ServletException::class)
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        chain: FilterChain
    ) {
        logger.info("try authenticating uri: " + request.requestURI)
        if (request.requestURI.indexOf("admin") == -1) {
            chain.doFilter(request, response)
            return
        }
        val authentication: UsernamePasswordAuthenticationToken?
        try {
            authentication = getAuthentication(request)
        } catch (e: Exception) {
            ResponseUtil.out(response, R.error())
            return
        }
        if (authentication != null) {
            SecurityContextHolder.getContext().authentication = authentication
        } else {
            ResponseUtil.out(response, R.error())
            return
        }
        chain.doFilter(request, response)
    }

    @Suppress("UNCHECKED_CAST")
    private fun getAuthentication(request: HttpServletRequest): UsernamePasswordAuthenticationToken? {
        // token置于header里
        val token: String = request.getHeader("token")
        if ("" != token.trim { it <= ' ' }) {
            val userName: String = tokenManager.getUserFromToken(token)
            val permissionValueList = redisTemplate.opsForValue().get(userName) as List<String>
            val authorities: MutableCollection<GrantedAuthority> = mutableListOf()
            for (permissionValue in permissionValueList) {
                if (!StringUtils.hasText(permissionValue)) continue
                val authority = SimpleGrantedAuthority(permissionValue)
                authorities.add(authority)
            }
            return if (StringUtils.hasText(userName)) {
                UsernamePasswordAuthenticationToken(userName, token, authorities)
            } else null
        }
        return null
    }
}