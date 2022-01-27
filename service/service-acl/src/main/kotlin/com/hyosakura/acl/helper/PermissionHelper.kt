package com.hyosakura.acl.helper

import com.hyosakura.acl.entity.Permission

/**
 * 根据权限数据构建菜单数据
 */
object PermissionHelper {
    /**
     * 使用递归方法建菜单
     * @param treeNodes
     * @return
     */
    fun bulid(treeNodes: List<Permission>): List<Permission> {
        val trees: MutableList<Permission> = ArrayList()
        for (treeNode in treeNodes) {
            if ("0" == treeNode.pid) {
                treeNode.level = 1
                trees.add(findChildren(treeNode, treeNodes))
            }
        }
        return trees
    }

    /**
     * 递归查找子节点
     * @param treeNodes
     * @return
     */
    fun findChildren(treeNode: Permission, treeNodes: List<Permission>): Permission {
        treeNode.children = mutableListOf()
        for (it in treeNodes) {
            if (treeNode.id == it.pid) {
                val level = treeNode.level!! + 1
                it.level = level
                if (treeNode.children == null) {
                    treeNode.children = ArrayList()
                }
                treeNode.children!!.add(findChildren(it, treeNodes))
            }
        }
        return treeNode
    }
}