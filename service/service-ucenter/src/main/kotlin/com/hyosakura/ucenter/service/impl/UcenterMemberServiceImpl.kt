package com.hyosakura.ucenter.service.impl

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import com.hyosakura.ucenter.entity.UcenterMember
import com.hyosakura.ucenter.entity.vo.RegisterVo
import com.hyosakura.ucenter.mapper.UcenterMemberMapper
import com.hyosakura.ucenter.service.UcenterMemberService
import com.hyosakura.util.JwtUtil
import com.hyosakura.util.MD5
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service
import org.springframework.util.StringUtils

/**
 * 会员表 服务实现类
 *
 * @author LovesAsuna
 * @since 2021-12-18
 */
@Service
open class UcenterMemberServiceImpl : ServiceImpl<UcenterMemberMapper, UcenterMember>(), UcenterMemberService {
    @Autowired
    private lateinit var redisTemplate: RedisTemplate<String, String>

    override fun login(member: UcenterMember): String {
        val phone = member.mobile
        val password = member.password

        if (!StringUtils.hasText(phone) || !StringUtils.hasText(password)) {
            throw RuntimeException("手机号或密码不能为空")
        }

        val wrapper = QueryWrapper<UcenterMember>()
        wrapper.eq("mobile", phone)
        val phoneMember = baseMapper.selectOne(wrapper) ?: throw RuntimeException("登陆失败")
        if (MD5.encrypt(password!!) != phoneMember.password || phoneMember.isDisabled!!) {
            throw RuntimeException("登陆失败")
        }
        return JwtUtil.getJsonToken(phoneMember.id!!, phoneMember.nickname!!)
    }

    override fun register(registerVo: RegisterVo) {
        val code = registerVo.code
        val phone = registerVo.mobile
        val nickName = registerVo.nickname
        val password = registerVo.password
        if (!StringUtils.hasText(code) || !StringUtils.hasText(phone) || !StringUtils.hasText(nickName) || !StringUtils.hasText(password)) {
            throw RuntimeException("注册失败")
        }
        val redisCode = redisTemplate.opsForValue().get(phone)
        if (redisCode != code) {
            throw RuntimeException("注册失败")
        }
        val wrapper = QueryWrapper<UcenterMember>()
        wrapper.eq("mobile", phone)
        val count = baseMapper.selectCount(wrapper)
        if (count > 0) {
            throw RuntimeException("注册失败")
        }
        val member = UcenterMember()
        member.mobile = phone
        member.nickname = nickName
        member.password = MD5.encrypt(password)
        member.isDisabled = false
        baseMapper.insert(member)
    }

    override fun countRegister(day: String): Int {
        return baseMapper.countRegister(day)
    }
}