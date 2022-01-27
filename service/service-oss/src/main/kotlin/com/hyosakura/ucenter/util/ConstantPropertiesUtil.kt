package com.hyosakura.ucenter.util

import org.springframework.beans.factory.InitializingBean
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component


@Component
data class ConstantPropertiesUtil(
    @field:Value("\${aliyun.oss.file.endpoint}")
    var endPoint: String? = null,

    @field:Value("\${aliyun.oss.file.keyid}")
    var keyID: String? = null,

    @field:Value("\${aliyun.oss.file.keysecret}")
    var keySecret: String? = null,

    @field:Value("\${aliyun.oss.file.bucketname}")
    var bucketName: String? = null
) : InitializingBean {
    companion object {
        lateinit var ENDPOINT: String

        lateinit var KEYID: String

        lateinit var KEYSECRET: String

        lateinit var BUCKETNAME: String
    }

    override fun afterPropertiesSet() {
        ENDPOINT = endPoint!!
        KEYID = keyID!!
        KEYSECRET = keySecret!!
        BUCKETNAME = bucketName!!
    }
}
