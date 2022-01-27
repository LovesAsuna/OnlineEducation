package com.hyosakura.acl.service.impl

import com.alibaba.fastjson.JSONObject
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import com.hyosakura.acl.entity.Permission
import com.hyosakura.acl.entity.RolePermission
import com.hyosakura.acl.entity.User
import com.hyosakura.acl.helper.MemuHelper
import com.hyosakura.acl.helper.PermissionHelper
import com.hyosakura.acl.mapper.PermissionMapper
import com.hyosakura.acl.service.PermissionService
import com.hyosakura.acl.service.RolePermissionService
import com.hyosakura.acl.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.util.StringUtils
import java.util.function.Consumer

/**
 * 权限 服务实现类
 */
@Service
open class PermissionServiceImpl : ServiceImpl<PermissionMapper, Permission>(), PermissionService {
    @Autowired
    private lateinit var rolePermissionService: RolePermissionService

    @Autowired
    private lateinit var userService: UserService

    //获取全部菜单
    override fun queryAllMenu(): List<Permission> {
        val wrapper: QueryWrapper<Permission> = QueryWrapper<Permission>()
        wrapper.orderByDesc("id")
        val permissionList: List<Permission> = baseMapper.selectList(wrapper)
        return build(permissionList)
    }

    //根据角色获取菜单
    override fun selectAllMenu(roleId: String): List<Permission> {
        val allPermissionList: List<Permission> =
            baseMapper.selectList(QueryWrapper<Permission>().orderByAsc("CAST(id AS SIGNED)"))

        //根据角色id获取角色权限
        val rolePermissionList: List<RolePermission> =
            rolePermissionService.list(QueryWrapper<RolePermission>().eq("role_id", roleId))
        //转换给角色id与角色权限对应Map对象
//        List<String> permissionIdList = rolePermissionList.stream().map(e -> e.getPermissionId()).collect(Collectors.toList());
//        allPermissionList.forEach(permission -> {
//            if(permissionIdList.contains(permission.getId())) {
//                permission.setSelect(true);
//            } else {
//                permission.setSelect(false);
//            }
//        });
        for (i in allPermissionList.indices) {
            val permission: Permission = allPermissionList[i]
            for (m in rolePermissionList.indices) {
                val rolePermission: RolePermission = rolePermissionList[m]
                if (rolePermission.permissionId == permission.id) {
                    permission.isSelect = true
                }
            }
        }
        return build(allPermissionList)
    }

    //给角色分配权限
    override fun saveRolePermissionRealtionShip(roleId: String, permissionIds: Array<String>) {
        rolePermissionService.remove(QueryWrapper<RolePermission>().eq("role_id", roleId))
        val rolePermissionList: MutableList<RolePermission> = ArrayList<RolePermission>()
        for (permissionId in permissionIds) {
            if (StringUtils.isEmpty(permissionId)) continue
            val rolePermission = RolePermission()
            rolePermission.roleId = roleId
            rolePermission.permissionId = permissionId
            rolePermissionList.add(rolePermission)
        }
        rolePermissionService.saveBatch(rolePermissionList)
    }

    //递归删除菜单
    override fun removeChildById(id: String) {
        val idList: MutableList<String> = ArrayList()
        selectChildListById(id, idList)
        idList.add(id)
        baseMapper.deleteBatchIds(idList)
    }

    //根据用户id获取用户菜单
    override fun selectPermissionValueByUserId(id: String): List<String> {
        var selectPermissionValueList: List<String>? = null
        selectPermissionValueList = if (isSysAdmin(id)) {
            //如果是系统管理员，获取所有权限
            baseMapper.selectAllPermissionValue()
        } else {
            baseMapper.selectPermissionValueByUserId(id)
        }
        return selectPermissionValueList
    }

    override fun selectPermissionByUserId(userId: String): List<JSONObject> {
        val selectPermissionList: List<Permission> = if (isSysAdmin(userId)) {
            //如果是超级管理员，获取所有菜单
            baseMapper.selectList(null)
        } else {
            baseMapper.selectPermissionByUserId(userId)
        }
        val permissionList: List<Permission> = PermissionHelper.bulid(selectPermissionList)
        return MemuHelper.bulid(permissionList)
    }

    /**
     * 判断用户是否系统管理员
     * @param userId
     * @return
     */
    private fun isSysAdmin(userId: String): Boolean {
        val user: User = userService.getById(userId)
        return "admin" == user.username
    }

    /**
     * 递归获取子节点
     * @param id
     * @param idList
     */
    private fun selectChildListById(id: String, idList: MutableList<String>) {
        val childList: List<Permission> = baseMapper.selectList(QueryWrapper<Permission>().eq("pid", id).select("id"))
        childList.stream().forEach { item: Permission ->
            idList.add(item.id!!)
            selectChildListById(item.id!!, idList)
        }
    }

    //========================递归查询所有菜单================================================
    //获取全部菜单
    override fun queryAllMenuGuli(): List<Permission> {
        //1 查询菜单表所有数据
        val wrapper: QueryWrapper<Permission> = QueryWrapper<Permission>()
        wrapper.orderByDesc("id")
        val permissionList: List<Permission> = baseMapper.selectList(wrapper)
        //2 把查询所有菜单list集合按照要求进行封装
        return buildPermission(permissionList)
    }

    //============递归删除菜单==================================
    override fun removeChildByIdGuli(id: String) {
        //1 创建list集合，用于封装所有删除菜单id值
        val idList: MutableList<String> = ArrayList()
        //2 向idList集合设置删除菜单id
        selectPermissionChildById(id, idList)
        //把当前id封装到list里面
        idList.add(id)
        baseMapper.deleteBatchIds(idList)
    }

    //2 根据当前菜单id，查询菜单里面子菜单id，封装到list集合
    private fun selectPermissionChildById(id: String, idList: MutableList<String>) {
        //查询菜单里面子菜单id
        val wrapper: QueryWrapper<Permission> = QueryWrapper<Permission>()
        wrapper.eq("pid", id)
        wrapper.select("id")
        val childIdList: List<Permission> = baseMapper.selectList(wrapper)
        //把childIdList里面菜单id值获取出来，封装idList里面，做递归查询
        childIdList.stream().forEach(Consumer<Permission> { item: Permission ->
            //封装idList里面
            idList.add(item.id!!)
            //递归查询
            selectPermissionChildById(item.id!!, idList)
        })
    }

    //=========================给角色分配菜单=======================
    override fun saveRolePermissionRealtionShipGuli(roleId: String, permissionIds: Array<String>) {
        //roleId角色id
        //permissionId菜单id 数组形式
        //1 创建list集合，用于封装添加数据
        val rolePermissionList: MutableList<RolePermission> = ArrayList<RolePermission>()
        //遍历所有菜单数组
        for (perId in permissionIds) {
            //RolePermission对象
            val rolePermission = RolePermission()
            rolePermission.roleId = roleId
            rolePermission.permissionId = perId
            //封装到list集合
            rolePermissionList.add(rolePermission)
        }
        //添加到角色菜单关系表
        rolePermissionService.saveBatch(rolePermissionList)
    }

    companion object {
        /**
         * 使用递归方法建菜单
         * @param treeNodes
         * @return
         */
        private fun build(treeNodes: List<Permission>): List<Permission> {
            val trees: MutableList<Permission> = mutableListOf()
            for (treeNode in treeNodes) {
                if ("0" == treeNode.pid) {
                    treeNode.level = 1
                    trees.add(findChildren(treeNode, treeNodes))
                }
            }
            return trees
        }

        /**
         * 递归查找子节点
         * @param treeNodes
         * @return
         */
        private fun findChildren(treeNode: Permission, treeNodes: List<Permission>): Permission {
            treeNode.children = mutableListOf()
            for (it in treeNodes) {
                if (treeNode.id == it.pid) {
                    val level: Int = treeNode.level!! + 1
                    it.level = level
                    if (treeNode.children == null) {
                        treeNode.children = mutableListOf()
                    }
                    treeNode.children!!.add(findChildren(it, treeNodes))
                }
            }
            return treeNode
        }

        //把返回所有菜单list集合进行封装的方法
        fun buildPermission(permissionList: List<Permission>): List<Permission> {

            //创建list集合，用于数据最终封装
            val finalNode: MutableList<Permission> = mutableListOf()
            //把所有菜单list集合遍历，得到顶层菜单 pid=0菜单，设置level是1
            for (permissionNode in permissionList) {
                //得到顶层菜单 pid=0菜单
                if ("0" == permissionNode.pid) {
                    //设置顶层菜单的level是1
                    permissionNode.level = 1
                    //根据顶层菜单，向里面进行查询子菜单，封装到finalNode里面
                    finalNode.add(selectChildren(permissionNode, permissionList))
                }
            }
            return finalNode
        }

        private fun selectChildren(permissionNode: Permission, permissionList: List<Permission>): Permission {
            //1 因为向一层菜单里面放二层菜单，二层里面还要放三层，把对象初始化
            permissionNode.children = mutableListOf()

            //2 遍历所有菜单list集合，进行判断比较，比较id和pid值是否相同
            for (it in permissionList) {
                //判断 id和pid值是否相同
                if (permissionNode.id == it.pid) {
                    //把父菜单的level值+1
                    val level: Int = permissionNode.level!! + 1
                    it.level = level
                    //如果children为空，进行初始化操作
                    if (permissionNode.children == null) {
                        permissionNode.children = mutableListOf()
                    }
                    //把查询出来的子菜单放到父菜单里面
                    permissionNode.children!!.add(selectChildren(it, permissionList))
                }
            }
            return permissionNode
        }
    }
}