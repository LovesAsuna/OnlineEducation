package com.hyosakura.serverbase.handler.mbp

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler
import org.apache.ibatis.reflection.MetaObject
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.util.*

/**
 * @author LovesAsuna
 **/
@Component
class MyMetaObjectHandler : MetaObjectHandler {
    override fun insertFill(metaObject: MetaObject) {
        setFieldValByName("gmtCreate", LocalDateTime.now(), metaObject)
        setFieldValByName("gmtModified", LocalDateTime.now(), metaObject)
    }

    override fun updateFill(metaObject: MetaObject?) {
        setFieldValByName("gmtModified", LocalDateTime.now(), metaObject)
    }
}