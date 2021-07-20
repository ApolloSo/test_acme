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
class SvalueService {

    @Autowired
    lateinit var svalueRepository: SvalueRepository

    @Value("\${jwt.apiKey}")
    val apiKey: String? = null

    @Value("\${json.csvApi.url}") 
    val svalueUrl: String = ""


    fun findAll(): List<Svalue> = svalueRepository.findAll()

    fun findOne(id: Int): Svalue = svalueRepository.findOne(id)

    fun create(svalue: Svalue){
        svalueRepository.insert(svalue)
    }

    fun update(svalue: Svalue){
        svalueRepository.update(svalue)
    }

    fun delete(id: Int) {
        svalueRepository.delete(id)
    }

    fun load() {
        val client = HttpClient.newBuilder().build()
        val request =
                HttpRequest.newBuilder()
                        .uri(URI.create(svalueUrl))
                        // .POST(formData(values))
                        .header("Content-Type", "application/json")
                        .header("apiKey", apiKey)
                        .build()

        val response = client.send(request, HttpResponse.BodyHandlers.ofString())
        val csvStr: String = response.body()

        var lines = csvStr.lines()

        lines.forEachIndexed {index, element->
            if(index!=0){
                var vals = element.split(",")
                if(vals.get(0).length >0){
                    val svalue: Svalue = Svalue(vals.get(0).toInt(), vals.get(1), vals.get(2))
                    val sfind: Svalue = findOne(svalue.store_id)

                    if (sfind == null) {
                        create(svalue)
                    } else {
                        update(svalue)
                    }

                }
                
            }
        }

        // val objectMapper: ObjectMapper = ObjectMapper()
        // val rootNode: JsonNode = objectMapper.readTree(jsonStr)

        // for (objNode: JsonNode in rootNode) {
        //     val jObj1: JSONObject = JSONObject(objNode.toString())
        //     val store: Store = objectMapper.readValue(jObj1.toString(), Store::class.java)
        //     println(store.id)
        //     val sfind: Store = findOne(store.id)
        //     if (sfind == null) {
        //         create(store)
        //     } else {
        //         update(store)
        //     }
        // }
    }

}