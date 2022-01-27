package com.hyosakura.ucenter.controller

import com.hyosakura.ucenter.entity.UcenterMember
import com.hyosakura.ucenter.entity.vo.RegisterVo
import com.hyosakura.ucenter.service.UcenterMemberService
import com.hyosakura.util.JwtUtil
import com.hyosakura.util.R
import com.hyosakura.util.R.Companion.data
import com.hyosakura.util.ordervo.UcenterMemberOrder
import org.springframework.beans.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest

/**
 * 会员表 前端控制器
 *
 * @author LovesAsuna
 * @since 2021-12-18
 */
@RestController
@RequestMapping("/educenter/member")
class UcenterMemberController {
    @Autowired
    private lateinit var service: UcenterMemberService

    @PostMapping("login")
    fun loginUser(@RequestBody member: UcenterMember): R {
        val token = service.login(member)
        return R.success().data("token", token)
    }

    @PostMapping("register")
    fun registerUser(@RequestBody registerVo: RegisterVo): R {
        service.register(registerVo)
        return R.success()
    }

    @GetMapping("getMemberInfo")
    fun getMemberInfo(request: HttpServletRequest): R {
        val memberId = JwtUtil.getMemberIdByJwtToken(request)
        val member = service.getById(memberId)
        return R.success().data("userInfo", member)
    }

    @PostMapping("getUserInfoOrder/{id}")
    fun getUserInfoOrder(@PathVariable("id") id: String): UcenterMemberOrder {
        val member = service.getById(id)
        val ucenterMemberOrder = UcenterMemberOrder()
        BeanUtils.copyProperties(member, ucenterMemberOrder)
        return ucenterMemberOrder
    }

    @GetMapping("countRegister/{day}")
    fun countRegister(@PathVariable day: String): R {
        val count = service.countRegister(day)
        return R.success().data("countRegister", count)
    }
}