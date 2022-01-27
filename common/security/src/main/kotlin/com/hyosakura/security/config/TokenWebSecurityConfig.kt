package com.hyosakura.security.config

import com.hyosakura.security.filter.TokenAuthenticationFilter
import com.hyosakura.security.filter.TokenLoginFilter
import com.hyosakura.security.security.DefaultPasswordEncoder
import com.hyosakura.security.security.TokenLogoutHandler
import com.hyosakura.security.security.TokenManager
import com.hyosakura.security.security.UnauthorizedEntryPoint
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.UserDetailsService

/**
 * Security配置类
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
open class TokenWebSecurityConfig(
    private val userDetailsService: UserDetailsService,
    private val defaultPasswordEncoder: DefaultPasswordEncoder,
    private val tokenManager: TokenManager,
    private val redisTemplate: RedisTemplate<String, Any>
) : WebSecurityConfigurerAdapter() {

    /**
     * 配置设置
     */
    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http.exceptionHandling()
            .authenticationEntryPoint(UnauthorizedEntryPoint())
            .and()
            .csrf().disable()
            .authorizeRequests()
            .anyRequest().authenticated()
            .and()
            .logout().logoutUrl("/admin/acl/index/logout")
            .addLogoutHandler(TokenLogoutHandler(tokenManager, redisTemplate))
            .and()
            .addFilter(TokenLoginFilter(authenticationManager(), tokenManager, redisTemplate))
            .addFilter(TokenAuthenticationFilter(authenticationManager(), tokenManager, redisTemplate))
            .httpBasic()
    }

    /**
     * 密码处理
     */
    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(userDetailsService).passwordEncoder(defaultPasswordEncoder)
    }

    /**
     * 配置哪些请求不拦截
     */
    override fun configure(web: WebSecurity) {
        web.ignoring().antMatchers(
            "/api/**",
            "/swagger-resources/**",
            "/webjars/**",
            "/v3/**",
            "/swagger-ui/**",
            "/doc.html/**"
        )
        // web.ignoring().antMatchers(
        //     "/*/**"
        // );
    }
}