package com.hyosakura.acl.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.hyosakura.acl.entity.Permission

/**
 * 权限 Mapper 接口
 */
interface PermissionMapper : BaseMapper<Permission> {
    fun selectPermissionValueByUserId(id: String): List<String>

    fun selectAllPermissionValue(): List<String>

    fun selectPermissionByUserId(userId: String): List<Permission>
}