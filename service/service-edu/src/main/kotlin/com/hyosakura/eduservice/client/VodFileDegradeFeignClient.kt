package com.hyosakura.eduservice.client

import com.hyosakura.util.R
import com.hyosakura.util.R.Companion.message
import org.springframework.stereotype.Component

/**
 * @author LovesAsuna
 **/
@Component
class VodFileDegradeFeignClient: VodClient {
    override fun removeVideo(id: String): R {
        return R.error().message("删除视频时出错")
    }

    override fun deleteBatch(videoIdList: List<String>): R {
        return R.error().message("批量删除视频时出错")
    }
}