package com.hyosakura.acl.service.impl

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import com.hyosakura.acl.entity.RolePermission
import com.hyosakura.acl.mapper.RolePermissionMapper
import com.hyosakura.acl.service.RolePermissionService
import org.springframework.stereotype.Service

/**
 * 角色权限 服务实现类
 */
@Service
open class RolePermissionServiceImpl : ServiceImpl<RolePermissionMapper, RolePermission>(), RolePermissionService