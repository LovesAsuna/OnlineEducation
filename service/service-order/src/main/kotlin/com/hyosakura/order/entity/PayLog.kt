package com.hyosakura.order.entity

import com.baomidou.mybatisplus.annotation.TableName
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import java.math.BigDecimal
import java.time.LocalDateTime

/**
 *
 *
 * 支付日志表
 *
 *
 * @author LovesAsuna
 * @since 2021-12-25
 */
@TableName("t_pay_log")
@ApiModel(value = "PayLog对象", description = "支付日志表")
data class PayLog(
    var id: String? = null,

    @ApiModelProperty("订单号")
    var orderNo: String? = null,

    @ApiModelProperty("支付完成时间")
    var payTime: LocalDateTime? = null,

    @ApiModelProperty("支付金额（分）")
    var totalFee: BigDecimal? = null,

    @ApiModelProperty("交易流水号")
    var transactionId: String? = null,

    @ApiModelProperty("交易状态")
    var tradeState: String? = null,

    @ApiModelProperty("支付类型（1：微信 2：支付宝）")
    var payType: Int? = null,

    @ApiModelProperty("其他属性")
    var attr: String? = null,

    @ApiModelProperty("逻辑删除 1（true）已删除， 0（false）未删除")
    var isDeleted: Boolean? = null,

    @ApiModelProperty("创建时间")
    var gmtCreate: LocalDateTime? = null,

    @ApiModelProperty("更新时间")
    var gmtModified: LocalDateTime? = null
) {
    override fun toString(): String {
        return "PayLog{" +
                "id=" + id +
                ", orderNo=" + orderNo +
                ", payTime=" + payTime +
                ", totalFee=" + totalFee +
                ", transactionId=" + transactionId +
                ", tradeState=" + tradeState +
                ", payType=" + payType +
                ", attr=" + attr +
                ", isDeleted=" + isDeleted +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                "}"
    }

    companion object {
        private const val serialVersionUID = 1L
    }
}
