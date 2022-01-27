package com.hyosakura.util.ordervo

import com.baomidou.mybatisplus.annotation.FieldFill
import com.baomidou.mybatisplus.annotation.TableField
import io.swagger.annotations.ApiModelProperty
import java.io.Serializable
import java.time.LocalDateTime

/**
 * 会员表
 *
 * @author LovesAsuna
 * @since 2021-12-18
 */
data class UcenterMemberOrder(
    @ApiModelProperty("会员id")
    var id: String? = null,

    @ApiModelProperty("微信openid")
    var openid: String? = null,

    @ApiModelProperty("手机号")
    var mobile: String? = null,

    @ApiModelProperty("密码")
    var password: String? = null,

    @ApiModelProperty("昵称")
    var nickname: String? = null,

    @ApiModelProperty("性别 1 女，2 男")
    var sex: Int? = null,

    @ApiModelProperty("年龄")
    var age: Int? = null,

    @ApiModelProperty("用户头像")
    var avatar: String? = null,

    @ApiModelProperty("用户签名")
    var sign: String? = null,

    @ApiModelProperty("是否禁用 1（true）已禁用，  0（false）未禁用")
    var isDisabled: Boolean? = null,

    @ApiModelProperty("逻辑删除 1（true）已删除， 0（false）未删除")
    var isDeleted: Boolean? = null,

    @ApiModelProperty("创建时间")
    @TableField(fill = FieldFill.INSERT)
    private var gmtCreate: LocalDateTime? = null,

    @ApiModelProperty("更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private var gmtModified: LocalDateTime? = null,
) : Serializable {
    companion object {
        private const val serialVersionUID = 1L
    }
}