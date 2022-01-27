package com.hyosakura.acl.service.impl

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import com.hyosakura.acl.entity.UserRole
import com.hyosakura.acl.mapper.UserRoleMapper
import com.hyosakura.acl.service.UserRoleService

/**
 * 服务实现类
 */
@org.springframework.stereotype.Service
open class UserRoleServiceImpl : ServiceImpl<UserRoleMapper, UserRole>(), UserRoleService