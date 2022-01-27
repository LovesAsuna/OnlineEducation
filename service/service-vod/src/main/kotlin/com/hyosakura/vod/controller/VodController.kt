package com.hyosakura.vod.controller

import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest
import com.hyosakura.util.R
import com.hyosakura.util.R.Companion.data
import com.hyosakura.vod.service.VodService
import com.hyosakura.vod.util.ConstantPropertiesUtil
import com.hyosakura.vod.util.InitVodClient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

/**
 * @author LovesAsuna
 **/
@Controller
@RequestMapping("/eduvod/video")
class VodController {
    @Autowired
    private lateinit var service: VodService

    @PostMapping("uploadVideo")
    fun uploadVideo(file: MultipartFile): R {
        val videoId = service.uploadVideo(file)
        return R.success().data("videoId", videoId)
    }

    @DeleteMapping("removeVideo/{id}")
    fun removeVideo(@PathVariable id: String): R {
        val client = InitVodClient.InitVodClient(ConstantPropertiesUtil.KEYID, ConstantPropertiesUtil.KEYSECRET)
        val request = DeleteVideoRequest().apply {
            videoIds = id
        }
        client.getAcsResponse(request)
        return R.success()
    }

    @DeleteMapping("delete-batch")
    fun deleteBatch(@RequestParam("videoIdList") videoIdList: List<String>): R {
        return service.removeMoreVideo(videoIdList)
    }

    @GetMapping("getPlayAuth/{videoId}")
    fun getPlayAuth(@PathVariable videoId: String): R {
        try {
            val client = InitVodClient.InitVodClient(ConstantPropertiesUtil.KEYID, ConstantPropertiesUtil.KEYSECRET)
            val request = GetVideoPlayAuthRequest()
            request.videoId = videoId
            val response = client.getAcsResponse(request)
            val playAuth = response.playAuth
            return R.success().data("playAuth", playAuth)
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}