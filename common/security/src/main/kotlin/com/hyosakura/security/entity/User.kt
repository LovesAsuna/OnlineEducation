package com.hyosakura.security.entity

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import java.io.Serializable

/**
 * 用户实体类
 */
@ApiModel(description = "用户实体类")
data class User(
    @ApiModelProperty(value = "微信openid")
    val username: String? = null,

    @ApiModelProperty(value = "密码")
    val password: String? = null,

    @ApiModelProperty(value = "昵称")
    val nickName: String? = null,

    @ApiModelProperty(value = "用户头像")
    val salt: String? = null,

    @ApiModelProperty(value = "用户签名")
    val token: String? = null
) : Serializable {
    companion object {
        private const val serialVersionUID = 1L
    }
}