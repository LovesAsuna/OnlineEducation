package com.hyosakura.msm.service

/**
 * @author LovesAsuna
 **/
interface MsmService {
    fun send(param: HashMap<String, Any>, phone: String): Boolean
}