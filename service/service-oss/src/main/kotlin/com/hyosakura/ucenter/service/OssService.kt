package com.hyosakura.ucenter.service

import org.springframework.web.multipart.MultipartFile

/**
 * @author LovesAsuna
 **/
interface OssService {
    fun uploadAvatarFile(file: MultipartFile): String
}