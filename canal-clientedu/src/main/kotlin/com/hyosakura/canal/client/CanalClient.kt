package com.hyosakura.canal.client

import com.alibaba.otter.canal.client.CanalConnectors
import com.alibaba.otter.canal.protocol.CanalEntry
import com.alibaba.otter.canal.protocol.CanalEntry.RowChange
import com.google.protobuf.InvalidProtocolBufferException
import org.apache.commons.dbutils.DbUtils
import org.apache.commons.dbutils.QueryRunner
import org.springframework.stereotype.Component
import java.net.InetSocketAddress
import java.sql.Connection
import java.sql.SQLException
import java.util.*
import java.util.concurrent.ConcurrentLinkedQueue
import javax.annotation.Resource
import javax.sql.DataSource

@Component
class CanalClient {
    //sql队列
    private val SQL_QUEUE: Queue<String> = ConcurrentLinkedQueue()

    @Resource
    private val dataSource: DataSource? = null

    /**
     * canal入库方法
     */
    fun run() {
        val connector = CanalConnectors.newSingleConnector(
            InetSocketAddress(
                "120.79.185.188",
                11111
            ), "example", "", ""
        )
        val batchSize = 1000
        try {
            connector.connect()
            connector.subscribe(".*\\..*")
            connector.rollback()
            try {
                while (true) {
                    //尝试从master那边拉去数据batchSize条记录，有多少取多少
                    val message = connector.getWithoutAck(batchSize)
                    val batchId = message.id
                    val size = message.entries.size
                    if (batchId == -1L || size == 0) {
                        Thread.sleep(1000)
                    } else {
                        dataHandle(message.entries)
                    }
                    connector.ack(batchId)

                    //当队列里面堆积的sql大于一定数值的时候就模拟执行
                    if (SQL_QUEUE.size >= 1) {
                        executeQueueSql()
                    }
                }
            } catch (e: InterruptedException) {
                e.printStackTrace()
            } catch (e: InvalidProtocolBufferException) {
                e.printStackTrace()
            }
        } finally {
            connector.disconnect()
        }
    }

    /**
     * 模拟执行队列里面的sql语句
     */
    fun executeQueueSql() {
        val size = SQL_QUEUE.size
        for (i in 0 until size) {
            val sql = SQL_QUEUE.poll()
            println("[sql]----> $sql")
            execute(sql.toString())
        }
    }

    /**
     * 数据处理
     *
     * @param entrys
     */
    @Throws(InvalidProtocolBufferException::class)
    private fun dataHandle(entrys: List<CanalEntry.Entry>) {
        for (entry in entrys) {
            if (CanalEntry.EntryType.ROWDATA == entry.entryType) {
                val rowChange = RowChange.parseFrom(entry.storeValue)
                val eventType = rowChange.eventType
                if (eventType == CanalEntry.EventType.DELETE) {
                    saveDeleteSql(entry)
                } else if (eventType == CanalEntry.EventType.UPDATE) {
                    saveUpdateSql(entry)
                } else if (eventType == CanalEntry.EventType.INSERT) {
                    saveInsertSql(entry)
                }
            }
        }
    }

    /**
     * 保存更新语句
     *
     * @param entry
     */
    private fun saveUpdateSql(entry: CanalEntry.Entry) {
        try {
            val rowChange = RowChange.parseFrom(entry.storeValue)
            val rowDatasList = rowChange.rowDatasList
            for (rowData in rowDatasList) {
                val newColumnList = rowData.afterColumnsList
                val sql = StringBuffer("update " + entry.header.tableName + " set ")
                for (i in newColumnList.indices) {
                    sql.append(
                        " " + newColumnList[i].name
                                + " = '" + newColumnList[i].value + "'"
                    )
                    if (i != newColumnList.size - 1) {
                        sql.append(",")
                    }
                }
                sql.append(" where ")
                val oldColumnList = rowData.beforeColumnsList
                for (column in oldColumnList) {
                    if (column.isKey) {
                        //暂时只支持单一主键
                        sql.append(column.name + "=" + column.value)
                        break
                    }
                }
                SQL_QUEUE.add(sql.toString())
            }
        } catch (e: InvalidProtocolBufferException) {
            e.printStackTrace()
        }
    }

    /**
     * 保存删除语句
     *
     * @param entry
     */
    private fun saveDeleteSql(entry: CanalEntry.Entry) {
        try {
            val rowChange = RowChange.parseFrom(entry.storeValue)
            val rowDatasList = rowChange.rowDatasList
            for (rowData in rowDatasList) {
                val columnList = rowData.beforeColumnsList
                val sql = StringBuffer("delete from " + entry.header.tableName + " where ")
                for (column in columnList) {
                    if (column.isKey) {
                        //暂时只支持单一主键
                        sql.append(column.name + "=" + column.value)
                        break
                    }
                }
                SQL_QUEUE.add(sql.toString())
            }
        } catch (e: InvalidProtocolBufferException) {
            e.printStackTrace()
        }
    }

    /**
     * 保存插入语句
     *
     * @param entry
     */
    private fun saveInsertSql(entry: CanalEntry.Entry) {
        try {
            val rowChange = RowChange.parseFrom(entry.storeValue)
            val rowDatasList = rowChange.rowDatasList
            for (rowData in rowDatasList) {
                val columnList = rowData.afterColumnsList
                val sql = StringBuffer("insert into " + entry.header.tableName + " (")
                for (i in columnList.indices) {
                    sql.append(columnList[i].name)
                    if (i != columnList.size - 1) {
                        sql.append(",")
                    }
                }
                sql.append(") VALUES (")
                for (i in columnList.indices) {
                    sql.append("'" + columnList[i].value + "'")
                    if (i != columnList.size - 1) {
                        sql.append(",")
                    }
                }
                sql.append(")")
                SQL_QUEUE.add(sql.toString())
            }
        } catch (e: InvalidProtocolBufferException) {
            e.printStackTrace()
        }
    }

    /**
     * 入库
     * @param sql
     */
    fun execute(sql: String?) {
        var con: Connection? = null
        try {
            if (null == sql) return
            con = dataSource!!.connection
            val qr = QueryRunner()
            val row = qr.execute(con, sql)
            println("update: $row")
        } catch (e: SQLException) {
            e.printStackTrace()
        } finally {
            DbUtils.closeQuietly(con)
        }
    }
}