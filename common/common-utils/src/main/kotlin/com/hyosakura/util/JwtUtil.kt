package com.hyosakura.util

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.util.StringUtils
import java.util.*
import javax.servlet.http.HttpServletRequest

object JwtUtil {
    const val EXPIRE = 1000 * 60 * 60 * 24
    const val APP_SECRET = "ukc8BDbRigUDaY6pZFfwus2jZWLPHO"

    fun getJsonToken(id: String, nickName: String): String {
        return Jwts.builder()
            .setHeaderParam("typ", "JWT")
            .setHeaderParam("alg", "HS256")
            .setSubject("guli-user")
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + EXPIRE))
            .claim("id", id)
            .claim("nickname", nickName)
            .signWith(SignatureAlgorithm.HS256, APP_SECRET)
            .compact()
    }

    fun checkToken(jwtToken: String): Boolean {
        if (!StringUtils.hasText(jwtToken)) return false
        try {
            Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken)
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }
        return true
    }

    fun checkToken(request: HttpServletRequest): Boolean {
        try {
            val jwtToken = request.getHeader("token")
            if (!StringUtils.hasText(jwtToken)) return false
            Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken)
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }
        return true
    }

    fun getMemberIdByJwtToken(request: HttpServletRequest): String {
        val jwtToken = request.getHeader("token")
        if (!StringUtils.hasText(jwtToken)) return ""
        val claims = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken).body
        return claims["id"] as String
    }
}