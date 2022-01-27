package com.hyosakura.security.entity

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.util.StringUtils

/**
 * 安全认证用户详情信息
 */
class SecurityUser(
    @Transient val currentUserInfo: User
) : UserDetails {
    // 当前权限
    lateinit var permissionValueList: List<String>

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        val authorities: MutableCollection<GrantedAuthority> = mutableListOf()
        for (permissionValue in permissionValueList) {
            if (!StringUtils.hasText(permissionValue)) continue
            val authority = SimpleGrantedAuthority(permissionValue)
            authorities.add(authority)
        }
        return authorities
    }

    override fun getPassword(): String = currentUserInfo.password!!

    override fun getUsername(): String = currentUserInfo.username!!

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true
}