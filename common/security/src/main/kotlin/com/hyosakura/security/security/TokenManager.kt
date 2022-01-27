package com.hyosakura.security.security

import io.jsonwebtoken.CompressionCodecs
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.stereotype.Component
import java.util.*

/**
 * token管理
 */
@Component
class TokenManager {
    private val tokenExpiration = (24 * 60 * 60 * 1000).toLong()
    private val tokenSignKey = "123456"
    fun createToken(username: String?): String {
        return Jwts.builder().setSubject(username)
            .setExpiration(Date(System.currentTimeMillis() + tokenExpiration))
            .signWith(SignatureAlgorithm.HS512, tokenSignKey).compressWith(CompressionCodecs.GZIP).compact()
    }

    fun getUserFromToken(token: String): String {
        return Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token).body.subject
    }

    fun removeToken(token: String?) {
        //jwttoken无需删除，客户端扔掉即可。
    }
}