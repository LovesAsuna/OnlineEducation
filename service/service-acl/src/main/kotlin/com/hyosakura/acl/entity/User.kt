package com.hyosakura.acl.entity

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import java.io.Serializable
import java.util.*

/**
 * 用户表
 */
@TableName("acl_user")
@ApiModel(value = "User对象", description = "用户表")
data class User(
    @ApiModelProperty(value = "会员id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    var id: String? = null,

    @ApiModelProperty(value = "微信openid")
    var username: String? = null,

    @ApiModelProperty(value = "密码")
    var password: String? = null,

    @ApiModelProperty(value = "昵称")
    var nickName: String? = null,

    @ApiModelProperty(value = "用户头像")
    var salt: String? = null,

    @ApiModelProperty(value = "用户签名")
    var token: String? = null,

    @ApiModelProperty(value = "逻辑删除 1（true）已删除， 0（false）未删除")
    var isDeleted: Boolean? = null,

    @ApiModelProperty(value = "创建时间")
    var gmtCreate: Date? = null,

    @ApiModelProperty(value = "更新时间")
    var gmtModified: Date? = null
): Serializable {
    companion object {
        private const val serialVersionUID = 1L
    }
}