package com.hyosakura.eduservice.controller

import com.hyosakura.util.R
import com.hyosakura.util.R.Companion.data
import org.springframework.web.bind.annotation.*

/**
 * @author LovesAsuna
 **/
@RestController
@RequestMapping("/eduservice/user")
class EduLoginController {
    @PostMapping("login")
    fun login() : R {
        return R.success().data("token", "admin")
    }

    @GetMapping("info")
    fun info() : R {
        return R.success().data("roles", "[admin]")
            .data("name", "admin")
            .data("avatar", "https://upload.cc/i1/2021/11/13/w4T2lq.png")
    }
}