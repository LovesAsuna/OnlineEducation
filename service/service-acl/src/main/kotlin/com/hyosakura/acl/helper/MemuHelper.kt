package com.hyosakura.acl.helper

import com.alibaba.fastjson.JSONObject
import com.hyosakura.acl.entity.Permission
import org.springframework.util.StringUtils

/**
 * 根据权限数据构建登录用户左侧菜单数据
 */
object MemuHelper {
    /**
     * 构建菜单
     * @param treeNodes
     * @return
     */
    fun bulid(treeNodes: List<Permission>): List<JSONObject> {
        val meuns: MutableList<JSONObject> = ArrayList()
        if (treeNodes.size == 1) {
            val (_, _, _, _, _, _, _, _, _, _, oneMeunList) = treeNodes[0]
            //左侧一级菜单
            for ((id, _, name, _, _, path, component, icon, _, _, twoMeunList) in oneMeunList!!) {
                val oneMeun = JSONObject()
                oneMeun["path"] = path
                oneMeun["component"] = component
                oneMeun["redirect"] = "noredirect"
                oneMeun["name"] = "name_$id"
                oneMeun["hidden"] = false
                val oneMeta = JSONObject()
                oneMeta["title"] = name
                oneMeta["icon"] = icon
                oneMeun["meta"] = oneMeta
                val children: MutableList<JSONObject> = ArrayList()
                for ((id1, _, name1, _, _, path1, component1, _, _, _, threeMeunList) in twoMeunList!!) {
                    val twoMeun = JSONObject()
                    twoMeun["path"] = path1
                    twoMeun["component"] = component1
                    twoMeun["name"] = "name_$id1"
                    twoMeun["hidden"] = false
                    val twoMeta = JSONObject()
                    twoMeta["title"] = name1
                    twoMeun["meta"] = twoMeta
                    children.add(twoMeun)
                    for ((id2, _, name2, _, _, path2, component2) in threeMeunList!!) {
                        if (StringUtils.isEmpty(path2)) continue
                        val threeMeun = JSONObject()
                        threeMeun["path"] = path2
                        threeMeun["component"] = component2
                        threeMeun["name"] = "name_$id2"
                        threeMeun["hidden"] = true
                        val threeMeta = JSONObject()
                        threeMeta["title"] = name2
                        threeMeun["meta"] = threeMeta
                        children.add(threeMeun)
                    }
                }
                oneMeun["children"] = children
                meuns.add(oneMeun)
            }
        }
        return meuns
    }
}