package com.hyosakura.statistics.service.impl

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import com.hyosakura.statistics.client.UCenterClient
import com.hyosakura.statistics.entity.StatisticsDaily
import com.hyosakura.statistics.mapper.StatisticsDailyMapper
import com.hyosakura.statistics.service.StatisticsDailyService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import kotlin.random.Random
import kotlin.random.nextInt

/**
 * 网站统计日数据 服务实现类
 *
 * @author LovesAsuna
 * @since 2022-01-22
 */
@Service
open class StatisticsDailyServiceImpl : ServiceImpl<StatisticsDailyMapper, StatisticsDaily>(), StatisticsDailyService {
    @Qualifier("com.hyosakura.statistics.client.UCenterClient")
    @Autowired
    lateinit var ucenterClient: UCenterClient

    override fun registerCount(day: String) {
        val wrapper = QueryWrapper<StatisticsDaily>()
        wrapper.eq("date_calculated", day)
        baseMapper.delete(wrapper)
        val count = ucenterClient.countRegister(day).data["countRegister"] as Int
        val sta = StatisticsDaily()
        sta.registerNum = count
        sta.dateCalculated = day
        sta.videoViewNum = Random.nextInt(100..200)
        sta.loginNum = Random.nextInt(100..200)
        sta.courseNum = Random.nextInt(100..200)
        baseMapper.insert(sta)
    }

    override fun getShowData(type: String, begin: String, end: String): MutableMap<String, Any> {
        val map = mutableMapOf<String, Any>()
        val wrapper = QueryWrapper<StatisticsDaily>()
        wrapper.between("date_calculated", begin, end)
        wrapper.select("date_calculated", type)
        val list = baseMapper.selectList(wrapper)
        val dateList = mutableListOf<String>()
        val numDateList = mutableListOf<Int>()
        for (item in list) {
            dateList.add(item.dateCalculated!!)
            when (type) {
                "login_num" -> numDateList.add(item.loginNum!!)
                "register_num" -> numDateList.add(item.registerNum!!)
                "video_view_num" -> numDateList.add(item.videoViewNum!!)
                "course_num" -> numDateList.add(item.courseNum!!)
            }
        }
        map["dateList"] = dateList
        map["numDateList"] = numDateList
        return map
    }
}