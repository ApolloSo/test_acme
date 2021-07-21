package com.example.test_amce.util

import java.text.SimpleDateFormat
import java.util.*
import org.apache.commons.logging.LogFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.springframework.beans.factory.annotation.Autowired

import com.example.test_amce.service.*


@Component
class ScheduledTasks {

    private val dateFormat = SimpleDateFormat("HH:mm:ss")

    @Autowired 
    lateinit var seasonService: SeasonService

    @Autowired 
    lateinit var storeService: StoreService

    @Autowired
    lateinit var svalueService: SvalueService

    @Scheduled(cron="0 0 * ? * *")
    // @Scheduled(fixedRate = 10000)
    fun reportCurrentTime() {
        println("DB update start time, ${dateFormat.format(Date())}")
        seasonService.load()
        storeService.load()
        svalueService.load()
        println("DB update end time, ${dateFormat.format(Date())}")

    }
}
