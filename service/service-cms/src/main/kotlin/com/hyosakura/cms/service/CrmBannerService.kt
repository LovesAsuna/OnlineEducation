package com.hyosakura.cms.service

import com.baomidou.mybatisplus.extension.service.IService
import com.hyosakura.cms.entity.CrmBanner

/**
 * 首页banner表 服务类
 *
 * @author LovesAsuna
 * @since 2021-12-02
 */
interface CrmBannerService : IService<CrmBanner> {
    fun selectAllBanner(): List<CrmBanner>
}