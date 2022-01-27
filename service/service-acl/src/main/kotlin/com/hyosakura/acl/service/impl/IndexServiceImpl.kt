package com.hyosakura.acl.service.impl

import com.alibaba.fastjson.JSONObject
import com.hyosakura.acl.entity.Role
import com.hyosakura.acl.entity.User
import com.hyosakura.acl.service.IndexService
import com.hyosakura.acl.service.PermissionService
import com.hyosakura.acl.service.RoleService
import com.hyosakura.acl.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
open class IndexServiceImpl : IndexService {
    @Autowired
    private lateinit var userService: UserService

    @Autowired
    private lateinit var roleService: RoleService

    @Autowired
    private lateinit var permissionService: PermissionService

    @Autowired
    private lateinit var redisTemplate: RedisTemplate<String, Any>

    /**
     * 根据用户名获取用户登录信息
     *
     * @param username
     * @return
     */
    override fun getUserInfo(username: String): MutableMap<String, Any> {
        val result: MutableMap<String, Any> = mutableMapOf()
        val user: User = userService.selectByUsername(username)

        //根据用户id获取角色
        val roleList: List<Role> = roleService.selectRoleByUserId(user.id!!)
        val roleNameList = roleList.stream().map { item: Role -> item.roleName }
            .collect(Collectors.toList<Any>())
        if (roleNameList.size == 0) {
            //前端框架必须返回一个角色，否则报错，如果没有角色，返回一个空角色
            roleNameList.add("")
        }

        //根据用户id获取操作权限值
        val permissionValueList: List<String> = permissionService.selectPermissionValueByUserId(user.id!!)
        redisTemplate.opsForValue().set(username, permissionValueList)
        result["name"] = user.username!!
        result["avatar"] = "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif"
        result["roles"] = roleNameList
        result["permissionValueList"] = permissionValueList
        return result
    }

    /**
     * 根据用户名获取动态菜单
     * @param username
     * @return
     */
    override fun getMenu(username: String): List<JSONObject> {
        val user: User = userService.selectByUsername(username)

        //根据用户id获取用户菜单权限
        return permissionService.selectPermissionByUserId(user.id!!)
    }
}