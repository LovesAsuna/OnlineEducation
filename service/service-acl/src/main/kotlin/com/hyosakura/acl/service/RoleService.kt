package com.hyosakura.acl.service

import com.baomidou.mybatisplus.extension.service.IService
import com.hyosakura.acl.entity.Role

/**
 * 服务类
 */
interface RoleService : IService<Role> {
    //根据用户获取角色数据
    fun findRoleByUserId(userId: String): MutableMap<String, Any>

    //根据用户分配角色
    fun saveUserRoleRealtionShip(userId: String, roleId: Array<String>)

    fun selectRoleByUserId(id: String): List<Role>
}