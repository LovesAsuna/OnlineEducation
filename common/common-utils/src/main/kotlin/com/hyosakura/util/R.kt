package com.hyosakura.util

import io.swagger.annotations.ApiModelProperty

data class R(
    @ApiModelProperty("是否成功")
    var success: Boolean,

    @ApiModelProperty("返回码")
    var code: Int,

    @ApiModelProperty("返回消息")
    var message: String,

    @ApiModelProperty("返回数据")
    var data: MutableMap<String, Any> = mutableMapOf()
) {
    constructor() : this(true, ResultCode.success, "成功")

    companion object {
        fun success(): R = R(true, ResultCode.success, "成功")

        fun error(): R = R(false, ResultCode.error, "失败")

        fun R.message(message: String): R {
            this.message = message
            return this
        }

        fun R.code(code: Int): R {
            this.code = code
            return this
        }

        fun R.data(key: String, value: Any): R {
            this.data[key] = value
            return this
        }

        fun R.data(data: MutableMap<String, Any>): R {
            this.data = data
            return this
        }
    }
}
