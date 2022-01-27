package com.hyosakura.acl.entity

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableField
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import java.io.Serializable
import java.util.*

/**
 *
 *
 * 权限
 *
 *
 * @author testjava
 * @since 2020-01-12
 */
@TableName("acl_permission")
@ApiModel(value = "Permission对象", description = "权限")
data class Permission(
    @ApiModelProperty(value = "编号")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    var id: String? = null,

    @ApiModelProperty(value = "所属上级")
    var pid: String? = null,

    @ApiModelProperty(value = "名称")
    var name: String? = null,

    @ApiModelProperty(value = "类型(1:菜单,2:按钮)")
    var type: Int? = null,

    @ApiModelProperty(value = "权限值")
    var permissionValue: String? = null,

    @ApiModelProperty(value = "访问路径")
    var path: String? = null,

    @ApiModelProperty(value = "组件路径")
    var component: String? = null,

    @ApiModelProperty(value = "图标")
    var icon: String? = null,

    @ApiModelProperty(value = "状态(0:禁止,1:正常)")
    var status: Int? = null,

    @ApiModelProperty(value = "层级")
    @TableField(exist = false)
    var level: Int? = null,

    @ApiModelProperty(value = "下级")
    @TableField(exist = false)
    var children: MutableList<Permission>? = null,

    @ApiModelProperty(value = "是否选中")
    @TableField(exist = false)
    var isSelect: Boolean = false,

    @ApiModelProperty(value = "逻辑删除 1（true）已删除， 0（false）未删除")
    var isDeleted: Boolean? = null,

    @ApiModelProperty(value = "创建时间")
    var gmtCreate: Date? = null,

    @ApiModelProperty(value = "更新时间")
    var gmtModified: Date? = null,
) : Serializable {
    companion object {
        private const val serialVersionUID = 1L
    }
}