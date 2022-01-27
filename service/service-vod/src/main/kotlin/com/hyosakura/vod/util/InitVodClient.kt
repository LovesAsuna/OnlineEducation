package com.hyosakura.vod.util

import com.aliyuncs.DefaultAcsClient
import com.aliyuncs.profile.DefaultProfile

/**
 * @author LovesAsuna
 **/
class InitVodClient {
    companion object {
        fun InitVodClient(accessKeyId: String, accessKeySecret: String): DefaultAcsClient {
            val regionId = "cn-shanghai"
            val profile = DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret)
            return DefaultAcsClient(profile)
        }
    }
}