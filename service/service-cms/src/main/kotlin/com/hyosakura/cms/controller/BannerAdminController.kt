package com.hyosakura.cms.controller

import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import com.hyosakura.cms.entity.CrmBanner
import com.hyosakura.cms.service.CrmBannerService
import com.hyosakura.util.R
import com.hyosakura.util.R.Companion.data
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

/**
 * 首页banner表 前端控制器
 *
 * @author LovesAsuna
 * @since 2021-12-02
 */
@RestController
@RequestMapping("/educms/banneradmin")
@CrossOrigin
class BannerAdminController {
    @Autowired
    private lateinit var service : CrmBannerService

    @GetMapping("pageBanner/{page}/{limit}")
    fun pageBanner(@PathVariable page: Long, @PathVariable limit: Long): R {
        val pageBanner = Page<CrmBanner>(page, limit)
        service.page(pageBanner,  null)
        return R.success().data("item", pageBanner.records).data("total", pageBanner.total)
    }

    @ApiOperation("新增Banner")
    @PostMapping("addBanner")
    fun addBanner(@RequestBody banner: CrmBanner): R {
        service.save(banner)
        return R.success()
    }

    @ApiOperation("获取Banner")
    @GetMapping("getBanner/{id}")
    fun getBanner(@PathVariable id: String): R {
        val banner = service.getById(id)
        return R.success().data("item", banner)
    }

    @ApiOperation("修改Banner")
    @PostMapping("update")
    fun updateById(@RequestBody banner: CrmBanner): R {
        service.updateById(banner)
        return R.success()
    }

    @ApiOperation("删除Banner")
    @PostMapping("remove/{id}")
    fun remove(@PathVariable id: String): R {
        service.removeById(id)
        return R.success()
    }
}