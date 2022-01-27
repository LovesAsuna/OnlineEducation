package com.hyosakura.ucenter.service

import com.baomidou.mybatisplus.extension.service.IService
import com.hyosakura.ucenter.entity.UcenterMember
import com.hyosakura.ucenter.entity.vo.RegisterVo

/**
 * 会员表 服务类
 *
 * @author LovesAsuna
 * @since 2021-12-18
 */
interface UcenterMemberService : IService<UcenterMember> {
    fun login(member: UcenterMember): String

    fun register(registerVo: RegisterVo)

    fun countRegister(day: String): Int
}