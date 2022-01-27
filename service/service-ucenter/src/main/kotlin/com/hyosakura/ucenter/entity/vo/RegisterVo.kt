package com.hyosakura.ucenter.entity.vo

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

@ApiModel(value = "RegisterVo对象", description = "vo")
data class RegisterVo(
    @ApiModelProperty("昵称")
    val nickname: String,

    @ApiModelProperty("手机号")
    val mobile: String,

    @ApiModelProperty("密码")
    val password: String,

    @ApiModelProperty("验证码")
    val code: String
)
