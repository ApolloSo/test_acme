package com.example.test_amce.entity

import java.time.LocalDate
data class Store(
    val id: Int = -1, 
    val code: String="", 
    val description: String="", 
    val name: String="", 
    val openingDate: String = "", 
    val storeType: String=""
)
