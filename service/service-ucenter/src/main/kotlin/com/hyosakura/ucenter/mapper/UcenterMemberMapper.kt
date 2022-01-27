package com.hyosakura.ucenter.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.hyosakura.ucenter.entity.UcenterMember

/**
 * 会员表 Mapper 接口
 *
 * @author LovesAsuna
 * @since 2021-12-18
 */
interface UcenterMemberMapper : BaseMapper<UcenterMember> {
    fun countRegister(day: String): Int
}