package com.hyosakura.acl.entity

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import java.io.Serializable
import java.util.*

@TableName("acl_user_role")
@ApiModel(value = "UserRole对象", description = "")
data class UserRole(
    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    var id: String? = null,

    @ApiModelProperty(value = "角色id")
    var roleId: String? = null,

    @ApiModelProperty(value = "用户id")
    var userId: String? = null,

    @ApiModelProperty(value = "逻辑删除 1（true）已删除， 0（false）未删除")
    var isDeleted: Boolean? = null,

    @ApiModelProperty(value = "创建时间")
    var gmtCreate: Date? = null,

    @ApiModelProperty(value = "更新时间")
    var gmtModified: Date? = null,
): Serializable {
    companion object {
        private const val serialVersionUID = 1L
    }
}