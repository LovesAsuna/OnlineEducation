package com.hyosakura.acl.entity

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import java.io.Serializable
import java.util.*

@TableName("acl_role")
@ApiModel(value = "Role对象", description = "")
data class Role(
    @ApiModelProperty(value = "角色id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    var id: String? = null,

    @ApiModelProperty(value = "角色名称")
    var roleName: String? = null,

    @ApiModelProperty(value = "角色编码")
    var roleCode: String? = null,

    @ApiModelProperty(value = "备注")
    var remark: String? = null,

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