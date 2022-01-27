package com.hyosakura.acl.service.impl

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import com.hyosakura.acl.entity.User
import com.hyosakura.acl.mapper.UserMapper
import com.hyosakura.acl.service.UserService
import org.springframework.stereotype.Service

/**
 * 用户表 服务实现类
 */
@Service
open class UserServiceImpl : ServiceImpl<UserMapper, User>(), UserService {
    override fun selectByUsername(username: String): User {
        return baseMapper.selectOne(QueryWrapper<User>().eq("username", username))
    }
}