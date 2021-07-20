package com.example.test_amce.service

import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.net.URI
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

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.JsonNode
import org.json.JSONObject


import com.example.test_amce.entity.*
import com.example.test_amce.repository.*


@Transactional
@Service
class SeasonService{

    @Autowired
    lateinit var seasonRepository: SeasonRepository

    @Value("\${jwt.apiKey}")
    val apiKey: String? = null

    @Value("\${json.seasonApi.url}") 
    val seasonUrl: String = ""


    fun findAll(): List<Season> = seasonRepository.findAll()

    fun findOne(id: Int): Season = seasonRepository.findOne(id)

    fun create(season: Season){
        seasonRepository.insert(season)
    }

    fun update(season: Season){
        seasonRepository.update(season)
    }

    fun delete(id: Int) {
        seasonRepository.delete(id)
    }

    fun load() {
        val client = HttpClient.newBuilder().build()
        val request =
                HttpRequest.newBuilder()
                        .uri(URI.create(seasonUrl))
                        // .POST(formData(values))
                        .header("Content-Type", "application/json")
                        .header("apiKey", apiKey)
                        .build()

        val response = client.send(request, HttpResponse.BodyHandlers.ofString())
        val jsonStr: String = response.body()

        val objectMapper: ObjectMapper = ObjectMapper()
        val rootNode: JsonNode = objectMapper.readTree(jsonStr)

        for (objNode: JsonNode in rootNode) {
            val jObj1: JSONObject = JSONObject(objNode.toString())
            val season: Season = objectMapper.readValue(jObj1.toString(), Season::class.java)
            val sfind: Season = findOne(season.storeId)
            if (sfind == null) {
                create(season)
            } else {
                update(season)
            }
        }
    } 

}