package com.hyosakura.ucenter.controller

import com.hyosakura.ucenter.service.OssService
import com.hyosakura.util.R
import com.hyosakura.util.R.Companion.data
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile


/**
 * @author LovesAsuna
 **/
@RestController
@RequestMapping("/eduoss/file")
open class OssController {
    @Autowired
    private lateinit var service: OssService

    @PostMapping("/upload")
    open fun uploadFile(file: MultipartFile): R {
        val url = service.uploadAvatarFile(file)
        return R.success().data("url", url)
    }
}