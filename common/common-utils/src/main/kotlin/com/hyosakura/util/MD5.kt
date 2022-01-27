package com.hyosakura.util

import java.security.MessageDigest

object MD5 {
    fun encrypt(str: String): String {
        try {
            val hexChars = arrayOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f')
            var bytes = str.toByteArray()
            val md = MessageDigest.getInstance("MD5")
            md.update(bytes)
            bytes = md.digest()
            val j = bytes.size
            val chars = CharArray(j * 2)
            var k = 0
            for (i in 0 until j) {
                val byte = bytes[i]
                chars[k++] = hexChars[byte.toInt() shr 4 and 0xF]
                chars[k++] = hexChars[byte.toInt() and 0xF]
            }
            return String(chars)
        } catch (e: Exception) {
            e.printStackTrace()
            throw RuntimeException("MD5加密失败", e)
        }
    }
}