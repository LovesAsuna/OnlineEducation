package com.hyosakura.vod.service.impl

import com.aliyun.vod.upload.impl.UploadVideoImpl
import com.aliyun.vod.upload.req.UploadStreamRequest
import com.aliyun.vod.upload.resp.UploadStreamResponse
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest
import com.hyosakura.util.R
import com.hyosakura.vod.service.VodService
import com.hyosakura.vod.util.ConstantPropertiesUtil
import com.hyosakura.vod.util.InitVodClient
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

/**
 * @author LovesAsuna
 **/
@Service
class VodServiceImpl : VodService {
    override fun uploadVideo(file: MultipartFile): String {
        val request = UploadStreamRequest(ConstantPropertiesUtil.KEYID, ConstantPropertiesUtil.KEYSECRET, file.originalFilename!!.substring(0, file.originalFilename!!.lastIndexOf(".")), file.originalFilename, file.inputStream)
        val uploader = UploadVideoImpl()
        val response: UploadStreamResponse = uploader.uploadStream(request)
        return response.videoId
    }

    override fun removeMoreVideo(videoIdList: List<String>): R {
        val client = InitVodClient.InitVodClient(ConstantPropertiesUtil.KEYID, ConstantPropertiesUtil.KEYSECRET)
        val request = DeleteVideoRequest().apply {
            videoIds = videoIdList.joinToString(",")
        }
        client.getAcsResponse(request)
        return R.success()
    }
}