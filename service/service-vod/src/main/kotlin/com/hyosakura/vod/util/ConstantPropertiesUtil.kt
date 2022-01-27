package com.hyosakura.vod.util

import org.springframework.beans.factory.InitializingBean
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
data class ConstantPropertiesUtil(
    @field:Value("\${aliyun.vod.file.keyid}")
    var keyID: String? = null,

    @field:Value("\${aliyun.vod.file.keysecret}")
    var keySecret: String? = null,
) : InitializingBean {
    companion object {
        lateinit var KEYID: String

        lateinit var KEYSECRET: String
    }

    override fun afterPropertiesSet() {
        KEYID = keyID!!
        KEYSECRET = keySecret!!
    }
}
