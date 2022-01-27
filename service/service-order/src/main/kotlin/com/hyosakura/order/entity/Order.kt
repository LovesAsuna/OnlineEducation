package com.hyosakura.order.entity

import com.baomidou.mybatisplus.annotation.FieldFill
import com.baomidou.mybatisplus.annotation.TableField
import com.baomidou.mybatisplus.annotation.TableName
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import java.io.Serializable
import java.math.BigDecimal
import java.time.LocalDateTime

/**
 * 订单
 *
 * @author LovesAsuna
 * @since 2022-01-14
 */
@TableName("t_order")
@ApiModel(value = "Order对象", description = "订单")
data class Order(
    var id: String? = null,

    @ApiModelProperty("订单号")
    var orderNo: String? = null,

    @ApiModelProperty("课程id")
    var courseId: String? = null,

    @ApiModelProperty("课程名称")
    var courseTitle: String? = null,

    @ApiModelProperty("课程封面")
    var courseCover: String? = null,

    @ApiModelProperty("讲师名称")
    var teacherName: String? = null,

    @ApiModelProperty("会员id")
    var memberId: String? = null,

    @ApiModelProperty("会员昵称")
    var nickname: String? = null,

    @ApiModelProperty("会员手机")
    var mobile: String? = null,

    @ApiModelProperty("订单金额（分）")
    var totalFee: BigDecimal? = null,

    @ApiModelProperty("支付类型（1：微信 2：支付宝）")
    var payType: Int? = null,

    @ApiModelProperty("订单状态（0：未支付 1：已支付）")
    var status: Int? = null,

    @ApiModelProperty("逻辑删除 1（true）已删除， 0（false）未删除")
    var isDeleted: Boolean? = null,

    @ApiModelProperty("创建时间")
    @TableField(fill = FieldFill.INSERT)
    var gmtCreate: LocalDateTime? = null,

    @ApiModelProperty("更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    var gmtModified: LocalDateTime? = null
) : Serializable {
    override fun toString(): String {
        return "Order(id=$id, orderNo=$orderNo, courseId=$courseId, courseTitle=$courseTitle, courseCover=$courseCover, teacherName=$teacherName, memberId=$memberId, nickname=$nickname, mobile=$mobile, totalFee=$totalFee, payType=$payType, status=$status, isDeleted=$isDeleted, gmtCreate=$gmtCreate, gmtModified=$gmtModified)"
    }

    companion object {
        private const val serialVersionUID = 1L
    }
}