package com.hyosakura.security.security

import com.hyosakura.util.MD5
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

/**
 * 密码的处理方法类型
 */
@Component
class DefaultPasswordEncoder @JvmOverloads constructor(strength: Int = -1) : PasswordEncoder {
    override fun encode(rawPassword: CharSequence): String {
        return MD5.encrypt(rawPassword.toString())
    }

    override fun matches(rawPassword: CharSequence, encodedPassword: String): Boolean {
        return encodedPassword == encode(rawPassword)
    }
}