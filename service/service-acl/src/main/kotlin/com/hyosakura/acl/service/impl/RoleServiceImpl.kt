package com.hyosakura.acl.service.impl

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import com.hyosakura.acl.entity.Role
import com.hyosakura.acl.entity.UserRole
import com.hyosakura.acl.mapper.RoleMapper
import com.hyosakura.acl.service.RoleService
import com.hyosakura.acl.service.UserRoleService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.util.StringUtils
import java.util.stream.Collectors

/**
 * 服务实现类
 */
@Service
open class RoleServiceImpl : ServiceImpl<RoleMapper, Role>(), RoleService {
    @Autowired
    private lateinit var userRoleService: UserRoleService

    //根据用户获取角色数据
    override fun findRoleByUserId(userId: String): MutableMap<String, Any> {
        //查询所有的角色
        val allRolesList: List<Role> = baseMapper.selectList(null)

        //根据用户id，查询用户拥有的角色id
        val existUserRoleList = userRoleService.list(QueryWrapper<UserRole>().eq("user_id", userId).select("role_id"))
        val existRoleList = existUserRoleList.stream()
            .map { c: UserRole -> c.roleId }
            .collect(Collectors.toList())

        //对角色进行分类
        val assignRoles: MutableList<Role> = mutableListOf()
        for (role in allRolesList) {
            //已分配
            if (existRoleList.contains(role.id)) {
                assignRoles.add(role)
            }
        }
        val roleMap: MutableMap<String, Any> = HashMap()
        roleMap["assignRoles"] = assignRoles
        roleMap["allRolesList"] = allRolesList
        return roleMap
    }

    //根据用户分配角色
    override fun saveUserRoleRealtionShip(userId: String, roleIds: Array<String>) {
        userRoleService.remove(QueryWrapper<UserRole>().eq("user_id", userId))
        val userRoleList: MutableList<UserRole> = ArrayList()
        for (roleId in roleIds) {
            if (!StringUtils.hasText(roleId)) continue
            val userRole = UserRole()
            userRole.userId = userId
            userRole.roleId = roleId
            userRoleList.add(userRole)
        }
        userRoleService.saveBatch(userRoleList)
    }

    override fun selectRoleByUserId(id: String): List<Role> {
        //根据用户id拥有的角色id
        val userRoleList = userRoleService.list(QueryWrapper<UserRole>().eq("user_id", id).select("role_id"))
        val roleIdList = userRoleList.stream()
            .map { item: UserRole -> item.roleId }
            .collect(Collectors.toList())
        var roleList: List<Role> = mutableListOf()
        if (roleIdList.size > 0) {
            roleList = baseMapper.selectBatchIds(roleIdList)
        }
        return roleList
    }
}