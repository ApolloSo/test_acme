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

    @Autowired
    lateinit var colRepository: ColStoreRepository

    fun findAll(): List<ColStore> = colRepository.findAll()

    fun findOne(id: Int): ColStore = colRepository.findOne(id)

    fun findByName(name: String): List<ColStore> = colRepository.findByName(name)

    fun delete(id: Int) {
        
    }

}