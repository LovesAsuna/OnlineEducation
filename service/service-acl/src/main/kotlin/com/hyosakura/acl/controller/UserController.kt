package com.hyosakura.acl.controller

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import com.baomidou.mybatisplus.core.metadata.IPage
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import com.hyosakura.acl.entity.User
import com.hyosakura.acl.service.RoleService
import com.hyosakura.acl.service.UserService
import com.hyosakura.util.MD5
import com.hyosakura.util.R
import com.hyosakura.util.R.Companion.data
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.util.StringUtils
import org.springframework.web.bind.annotation.*

/**
 * 用户表 前端控制器
 */
@RestController
@RequestMapping("/admin/acl/user") /*@CrossOrigin*/
class UserController {
    @Autowired
    private lateinit var userService: UserService

    @Autowired
    private lateinit var roleService: RoleService

    @ApiOperation(value = "获取管理用户分页列表")
    @GetMapping("{page}/{limit}")
    fun index(
        @ApiParam(name = "page", value = "当前页码", required = true)
        @PathVariable page: Long,
        @ApiParam(name = "limit", value = "每页记录数", required = true)
        @PathVariable limit: Long,
        @ApiParam(name = "courseQuery", value = "查询对象", required = false) userQueryVo: User
    ): R {
        val pageParam: Page<User> = Page<User>(
            page, limit
        )
        val wrapper: QueryWrapper<User> = QueryWrapper<User>()
        if (StringUtils.hasText(userQueryVo.username)) {
            wrapper.like("username", userQueryVo.username)
        }
        val pageModel: IPage<User> = userService.page(pageParam, wrapper)
        return R.success().data("items", pageModel.records).data("total", pageModel.total)
    }

    @ApiOperation(value = "新增管理用户")
    @PostMapping("save")
    fun save(@RequestBody user: User): R {
        user.password = MD5.encrypt(user.password!!)
        userService.save(user)
        return R.success()
    }

    @ApiOperation(value = "修改管理用户")
    @PutMapping("update")
    fun updateById(@RequestBody user: User): R {
        userService.updateById(user)
        return R.success()
    }

    @ApiOperation(value = "删除管理用户")
    @DeleteMapping("remove/{id}")
    fun remove(@PathVariable id: String): R {
        userService.removeById(id)
        return R.success()
    }

    @ApiOperation(value = "根据id列表删除管理用户")
    @DeleteMapping("batchRemove")
    fun batchRemove(@RequestBody idList: List<String>): R {
        userService.removeByIds(idList)
        return R.success()
    }

    @ApiOperation(value = "根据用户获取角色数据")
    @GetMapping("/toAssign/{userId}")
    fun toAssign(@PathVariable userId: String): R {
        val roleMap = roleService.findRoleByUserId(userId)
        return R.success().data(roleMap)
    }

    @ApiOperation(value = "根据用户分配角色")
    @PostMapping("/doAssign")
    fun doAssign(@RequestParam userId: String, @RequestParam roleId: Array<String>): R {
        roleService.saveUserRoleRealtionShip(userId, roleId)
        return R.success()
    }
}