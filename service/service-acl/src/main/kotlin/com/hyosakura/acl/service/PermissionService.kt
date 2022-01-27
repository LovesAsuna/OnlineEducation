package com.hyosakura.acl.service

import com.alibaba.fastjson.JSONObject
import com.baomidou.mybatisplus.extension.service.IService
import com.hyosakura.acl.entity.Permission

/**
 * 权限 服务类
 */
interface PermissionService : IService<Permission> {
    //获取全部菜单
    fun queryAllMenu(): List<Permission>

    //根据角色获取菜单
    fun selectAllMenu(roleId: String): List<Permission>

    //给角色分配权限
    fun saveRolePermissionRealtionShip(roleId: String, permissionId: Array<String>)

    //递归删除菜单
    fun removeChildById(id: String)

    //根据用户id获取用户菜单
    fun selectPermissionValueByUserId(id: String): List<String>

    fun selectPermissionByUserId(id: String): List<JSONObject>

    //获取全部菜单
    fun queryAllMenuGuli(): List<Permission>

    //递归删除菜单
    fun removeChildByIdGuli(id: String)

    //给角色分配权限
    fun saveRolePermissionRealtionShipGuli(roleId: String, permissionId: Array<String>)
}