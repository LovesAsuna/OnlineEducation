package com.hyosakura.ucenter.service.impl

import com.aliyun.oss.OSSClientBuilder
import com.hyosakura.ucenter.service.OssService
import com.hyosakura.ucenter.util.ConstantPropertiesUtil
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*


/**
 * @author LovesAsuna
 **/
@Service
class OssServiceImpl : OssService {
    private val formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd")
    override fun uploadAvatarFile(file: MultipartFile): String {
        val ossClient = OSSClientBuilder().build(
            ConstantPropertiesUtil.ENDPOINT,
            ConstantPropertiesUtil.KEYID,
            ConstantPropertiesUtil.KEYSECRET
        )
        val uuid = UUID.randomUUID().toString().replace("-", "")
        val datePath = formatter.format(LocalDateTime.now())
        val fileName = "${datePath}/${uuid}${file.originalFilename}"
        kotlin.runCatching {
            ossClient.putObject(
                ConstantPropertiesUtil.BUCKETNAME,
                fileName,
                file.inputStream
            )
        }.onFailure {
            it.printStackTrace()
        }.onSuccess {
            return "https://${ConstantPropertiesUtil.BUCKETNAME}.${ConstantPropertiesUtil.ENDPOINT}/${fileName}"
        }
        ossClient.shutdown()
        return ""
    }
}