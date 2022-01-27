package com.hyosakura.acl.controller

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import com.hyosakura.acl.entity.Role
import com.hyosakura.acl.service.RoleService
import com.hyosakura.util.R
import com.hyosakura.util.R.Companion.data
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.util.StringUtils
import org.springframework.web.bind.annotation.*

/**
 * 前端控制器
 */
@RestController
@RequestMapping("/admin/acl/role") /*@CrossOrigin*/
class RoleController {
    @Autowired
    private lateinit var roleService: RoleService

    @ApiOperation(value = "获取角色分页列表")
    @GetMapping("{page}/{limit}")
    fun index(
        @ApiParam(name = "page", value = "当前页码", required = true) @PathVariable page: Long?,
        @ApiParam(name = "limit", value = "每页记录数", required = true) @PathVariable limit: Long?,
        role: Role
    ): R {
        val pageParam: Page<Role> = Page<Role>(
            page!!, limit!!
        )
        val wrapper: QueryWrapper<Role> = QueryWrapper<Role>()
        if (StringUtils.hasText(role.roleName)) {
            wrapper.like("role_name", role.roleName)
        }
        roleService.page(pageParam, wrapper)
        return R.success().data("items", pageParam.getRecords()).data("total", pageParam.getTotal())
    }

    @ApiOperation(value = "获取角色")
    @GetMapping("get/{id}")
    operator fun get(@PathVariable id: String?): R {
        val role: Role = roleService.getById(id)
        return R.success().data("item", role)
    }

    @ApiOperation(value = "新增角色")
    @PostMapping("save")
    fun save(@RequestBody role: Role?): R {
        roleService.save(role)
        return R.success()
    }

    @ApiOperation(value = "修改角色")
    @PutMapping("update")
    fun updateById(@RequestBody role: Role?): R {
        roleService.updateById(role)
        return R.success()
    }

    @ApiOperation(value = "删除角色")
    @DeleteMapping("remove/{id}")
    fun remove(@PathVariable id: String?): R {
        roleService.removeById(id)
        return R.success()
    }

    @ApiOperation(value = "根据id列表删除角色")
    @DeleteMapping("batchRemove")
    fun batchRemove(@RequestBody idList: List<String?>?): R {
        roleService.removeByIds(idList)
        return R.success()
    }
}