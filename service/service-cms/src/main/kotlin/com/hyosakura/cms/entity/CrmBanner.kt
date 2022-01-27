package com.hyosakura.cms.entity

import com.baomidou.mybatisplus.annotation.TableName
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import java.io.Serializable
import java.time.LocalDateTime

/**
 *
 *
 * 首页banner表
 *
 *
 * @author LovesAsuna
 * @since 2021-12-02
 */
@TableName("crm_banner")
@ApiModel(value = "CrmBanner对象", description = "首页banner表")
class CrmBanner : Serializable {
    @ApiModelProperty("ID")
    var id: String? = null

    @ApiModelProperty("标题")
    var title: String? = null

    @ApiModelProperty("图片地址")
    var imageUrl: String? = null

    @ApiModelProperty("链接地址")
    var linkUrl: String? = null

    @ApiModelProperty("排序")
    var sort: Int? = null

    @ApiModelProperty("逻辑删除 1（true）已删除， 0（false）未删除")
    var isDeleted: Boolean? = null

    @ApiModelProperty("创建时间")
    var gmtCreate: LocalDateTime? = null

    @ApiModelProperty("更新时间")
    var gmtModified: LocalDateTime? = null
    override fun toString(): String {
        return "CrmBanner{" +
                "id=" + id +
                ", title=" + title +
                ", imageUrl=" + imageUrl +
                ", linkUrl=" + linkUrl +
                ", sort=" + sort +
                ", isDeleted=" + isDeleted +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                "}"
    }

    companion object {
        private const val serialVersionUID = 1L
    }
}