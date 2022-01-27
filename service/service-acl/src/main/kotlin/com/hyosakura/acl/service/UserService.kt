package com.hyosakura.acl.service

import com.baomidou.mybatisplus.extension.service.IService
import com.hyosakura.acl.entity.User


/**
 * 用户表 服务类
 */
interface UserService : IService<User> {
    // 从数据库中取出用户信息
    fun selectByUsername(username: String): User
}