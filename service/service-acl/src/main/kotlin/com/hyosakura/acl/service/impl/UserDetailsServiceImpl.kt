package com.hyosakura.acl.service.impl

import com.hyosakura.acl.entity.User
import com.hyosakura.acl.service.PermissionService
import com.hyosakura.acl.service.UserService
import com.hyosakura.security.entity.SecurityUser
import org.springframework.beans.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

/**
 * 自定义userDetailsService - 认证用户详情
 */
@Service("userDetailsService")
open class UserDetailsServiceImpl : UserDetailsService {
    @Autowired
    private lateinit var userService: UserService

    @Autowired
    private lateinit var permissionService: PermissionService

    /***
     * 根据账号获取用户信息
     *
     * @param username 用户名
     * @return UserDetails 用户信息
     */
    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails {
        // 从数据库中取出用户信息
        val user: User = userService.selectByUsername(username)

        // 返回UserDetails实现类
        val curUser = com.hyosakura.security.entity.User()
        BeanUtils.copyProperties(user, curUser)
        val authorities: List<String> = permissionService.selectPermissionValueByUserId(user.id!!)
        val securityUser = SecurityUser(curUser)
        securityUser.permissionValueList = authorities
        return securityUser
    }
}