package com.hyosakura.acl.controller

import com.hyosakura.acl.entity.Permission
import com.hyosakura.acl.service.PermissionService
import com.hyosakura.util.R
import com.hyosakura.util.R.Companion.data
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

/**
 * 权限 菜单管理
 */
@RestController
@RequestMapping("/admin/acl/permission")
class PermissionController {
    @Autowired
    private lateinit var permissionService: PermissionService

    //获取全部菜单
    @ApiOperation(value = "查询所有菜单")
    @GetMapping
    fun indexAllPermission(): R {
        val list = permissionService.queryAllMenuGuli()
        return R.success().data("children", list)
    }

    @ApiOperation(value = "递归删除菜单")
    @DeleteMapping("remove/{id}")
    fun remove(@PathVariable id: String): R {
        permissionService.removeChildByIdGuli(id)
        return R.success()
    }

    @ApiOperation(value = "给角色分配权限")
    @PostMapping("/doAssign")
    fun doAssign(roleId: String, permissionId: Array<String>): R {
        permissionService.saveRolePermissionRealtionShipGuli(roleId, permissionId)
        return R.success()
    }

    @ApiOperation(value = "根据角色获取菜单")
    @GetMapping("toAssign/{roleId}")
    fun toAssign(@PathVariable roleId: String): R {
        val list = permissionService.selectAllMenu(roleId)
        return R.success().data("children", list)
    }

    @ApiOperation(value = "新增菜单")
    @PostMapping("save")
    fun save(@RequestBody permission: Permission): R {
        permissionService.save(permission)
        return R.success()
    }

    @ApiOperation(value = "修改菜单")
    @PutMapping("update")
    fun updateById(@RequestBody permission: Permission): R {
        permissionService.updateById(permission)
        return R.success()
    }
}