package com.hyosakura.cms.service.impl

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import com.hyosakura.cms.entity.CrmBanner
import com.hyosakura.cms.mapper.CrmBannerMapper
import com.hyosakura.cms.service.CrmBannerService
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

/**
 * 首页banner表 服务实现类
 *
 * @author LovesAsuna
 * @since 2021-12-02
 */
@Service
open class CrmBannerServiceImpl : ServiceImpl<CrmBannerMapper, CrmBanner>(), CrmBannerService {
    @Cacheable(key = "'selectIndexList'", value = ["banner"])
    override fun selectAllBanner(): List<CrmBanner> {
        val wrapper = QueryWrapper<CrmBanner>()
        wrapper.orderByDesc("id")
        wrapper.last("limit 2")
        return baseMapper.selectList(null)
    }
}