package com.example.test_amce

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling


@SpringBootApplication
@EnableScheduling
class TestAmceApplication

fun main(args: Array<String>) {
	runApplication<TestAmceApplication>(*args)
}
