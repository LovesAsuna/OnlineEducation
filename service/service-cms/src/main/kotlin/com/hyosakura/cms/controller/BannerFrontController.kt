package com.hyosakura.cms.controller

import com.hyosakura.cms.service.CrmBannerService
import com.hyosakura.util.R
import com.hyosakura.util.R.Companion.data
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * 首页banner表 前端控制器
 *
 * @author LovesAsuna
 * @since 2021-12-02
 */
@RestController
@RequestMapping("/educms/bannerfront")
@CrossOrigin
class BannerFrontController {
    @Autowired
    private lateinit var service: CrmBannerService

    @GetMapping("getAllBanner")
    fun getAllBanner(): R {
        val list = service.selectAllBanner()
        return R.success().data("list", list)
    }
}