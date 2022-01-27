package com.hyosakura.acl.controller

import com.hyosakura.acl.service.IndexService
import com.hyosakura.util.R
import com.hyosakura.util.R.Companion.data
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/admin/acl/index")
class IndexController {
    @Autowired
    private val indexService: IndexService? = null

    /**
     * 根据token获取用户信息
     */
    @GetMapping("info")
    fun info(): R {
        //获取当前登录用户用户名
        val username: String = SecurityContextHolder.getContext().getAuthentication().getName()
        val userInfo = indexService!!.getUserInfo(username)
        return R.success().data(userInfo)
    }//获取当前登录用户用户名

    /**
     * 获取菜单
     * @return
     */
    @get:GetMapping("menu")
    val menu: R
        get() {
            //获取当前登录用户用户名
            val username: String = SecurityContextHolder.getContext().authentication.name
            val permissionList = indexService!!.getMenu(username)
            return R.success().data("permissionList", permissionList)
        }

    @PostMapping("logout")
    fun logout(): R {
        return R.success()
    }
}