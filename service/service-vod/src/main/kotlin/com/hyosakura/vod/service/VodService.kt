package com.hyosakura.vod.service

import com.hyosakura.util.R
import org.springframework.web.multipart.MultipartFile

/**
 * @author LovesAsuna
 **/
interface VodService {
    fun uploadVideo(file: MultipartFile): String

    fun removeMoreVideo(videoIdList: List<String>) : R
}