package com.example.test_amce.service

import java.net.http.HttpClient
import java.io.File
import java.nio.file.Files
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
class StoreService {

    @Autowired
    lateinit var userRepository: StoreRepository

    @Value("\${jwt.apiKey}")
    val apiKey: String = ""

    @Value("\${json.load.mode}")
    val loadMode: String = ""

    @Value("\${json.storeApi.url}") 
    val storeUrl: String = ""


    fun findAll(): List<Store> = userRepository.findAll()

    fun findOne(id: Int): Store = userRepository.findOne(id)

    fun findByName(name: String): List<Store> = userRepository.findByName(name)

    fun create(store: Store){
        userRepository.insert(store)
    }

    fun update(store: Store){
        userRepository.update(store)
    }

    fun updateName(store: Store) {
        userRepository.updateName(store)
    }


    fun delete(id: Int) {
        userRepository.delete(id)
    }

    fun load(){
        var jsonStr: String = ""

        if (loadMode=="http"){
            val client = HttpClient.newBuilder().build()
            val request =
            HttpRequest.newBuilder()
                    .uri(URI.create(storeUrl))
                    // .POST(formData(values))
                    // .header("Content-Type", "application/json")
                    .header("apiKey", apiKey)
                    .build()

            val response = client.send(request, HttpResponse.BodyHandlers.ofString())
            jsonStr = response.body()
        }
        if (loadMode=="file"){
            var resource:File = File("response.json");
			jsonStr = String(Files.readAllBytes(resource.toPath()));
        }

        val objectMapper:ObjectMapper =  ObjectMapper()
        val  rootNode:JsonNode = objectMapper.readTree(jsonStr)

        for (objNode:JsonNode in rootNode) {
            val jObj1:JSONObject  = JSONObject(objNode.toString());
            val store:Store = objectMapper.readValue(jObj1.toString(), Store::class.java);
            println(store.id)
            val sfind:Store = findOne(store.id)
            if(sfind == null){
                create(store)
            }else{
                update(store)
            }
        }
    }
}