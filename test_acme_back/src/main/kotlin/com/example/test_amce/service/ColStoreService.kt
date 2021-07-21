package com.example.test_amce.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.client.RestTemplate
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.*
import org.springframework.http.HttpEntity
import org.springframework.util.MultiValueMap
import org.springframework.util.LinkedMultiValueMap

import com.example.test_amce.entity.*
import com.example.test_amce.repository.*


@Transactional
@Service
class ColStoreService {

    @Value("\${page.records}") 
    val records: String = ""

    @Autowired
    lateinit var colRepository: ColStoreRepository

    fun findAll(page:Int): List<ColStore> = colRepository.findAll(records.toInt(), page*records.toInt())

    fun findOne(id: Int): ColStore = colRepository.findOne(id)

    fun getCount(): Int = colRepository.getCount()

    fun findByName(name: String): List<ColStore> = colRepository.findByName(name)

    fun delete(id: Int) {
        
    }

}