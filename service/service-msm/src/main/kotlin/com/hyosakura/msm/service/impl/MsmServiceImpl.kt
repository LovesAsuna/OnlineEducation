package com.hyosakura.msm.service.impl

import com.alibaba.fastjson.JSONObject
import com.aliyuncs.CommonRequest
import com.aliyuncs.DefaultAcsClient
import com.aliyuncs.http.MethodType
import com.aliyuncs.profile.DefaultProfile
import com.hyosakura.msm.service.MsmService
import org.springframework.stereotype.Service
import org.springframework.util.StringUtils

/**
 * @author LovesAsuna
 **/
@Service
class MsmServiceImpl : MsmService {
    @Suppress("DEPRECATION")
    override fun send(param: HashMap<String, Any>, phone: String): Boolean {
        if (!StringUtils.hasText(phone)) return false
        val profile = DefaultProfile.getProfile("default", "LTAI5t6RYneNF4jJRb3wdDGi", "8bUuIRjyrA1DncD7YZHCFshkKSP0ml")
        val client = DefaultAcsClient(profile)
        val request = CommonRequest()
        request.method = (MethodType.POST)
        request.domain = ("dysmsapi.aliyuncs.com")
        request.version = ("2017-05-25")
        request.action = ("SendSms")

        request.putQueryParameter("PhoneNumbers", phone)
        request.putQueryParameter("SignName", "短信测试")
        request.putQueryParameter("TemplateCode", "SMS_175850236")
        request.putQueryParameter("TemplateParam", JSONObject.toJSONString(param))
        return try {
            val response = client.getCommonResponse(request)
            val success = response.httpResponse.isSuccess
            success
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
}