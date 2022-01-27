package com.hyosakura.util

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import java.io.IOException

import javax.servlet.http.HttpServletResponse

object ResponseUtil {
    fun out(response: HttpServletResponse, r: R) {
        val mapper = ObjectMapper()
        response.status = HttpStatus.OK.value()
        response.contentType = MediaType.APPLICATION_JSON_VALUE
        response.characterEncoding = "UTF-8"
        try {
            mapper.writeValue(response.writer, r)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}