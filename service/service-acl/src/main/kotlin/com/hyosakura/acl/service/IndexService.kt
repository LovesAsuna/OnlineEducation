package com.hyosakura.acl.service

import com.alibaba.fastjson.JSONObject

interface IndexService {
    /**
     * 根据用户名获取用户登录信息
     * @param username
     * @return
     */
    fun getUserInfo(username: String): MutableMap<String, Any>

    /**
     * 根据用户名获取动态菜单
     * @param username
     * @return
     */
    fun getMenu(username: String): List<JSONObject>
}