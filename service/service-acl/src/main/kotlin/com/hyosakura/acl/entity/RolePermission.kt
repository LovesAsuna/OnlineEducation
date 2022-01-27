package com.hyosakura.acl.entity

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import java.io.Serializable
import java.util.*

/**
 * 角色权限
 */
@TableName("acl_role_permission")
@ApiModel(value = "RolePermission对象", description = "角色权限")
data class RolePermission(
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    var id: String? = null,

    var roleId: String? = null,

    var permissionId: String? = null,

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