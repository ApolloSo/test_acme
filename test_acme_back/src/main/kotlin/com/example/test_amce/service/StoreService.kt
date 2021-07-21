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
    lateinit var storeRepository: StoreRepository

    @Value("\${jwt.apiKey}")
    val apiKey: String = ""

    @Value("\${json.load.mode}")
    val loadMode: String = ""

    @Value("\${json.storeApi.url}") 
    val storeUrl: String = ""


    fun findAll(): List<Store> = storeRepository.findAll()

    fun findOne(id: Int): Store = storeRepository.findOne(id)

    fun findByName(name: String): List<Store> = storeRepository.findByName(name)

    fun create(store: Store){
        storeRepository.insert(store)
    }

    fun update(store: Store){
        storeRepository.update(store)
    }

    fun updateName(store: Store) {
        storeRepository.updateName(store)
    }


    fun delete(id: Int) {
        storeRepository.delete(id)
    }

    fun load(){
        var jsonStr: String = "default"
        var pageNum:Int = 0
        if (loadMode=="http"){
            
            while(jsonStr.length>4){
                print("pages:  " + pageNum)

                val client = HttpClient.newBuilder().build()
                val request =
                        HttpRequest.newBuilder()
                                .uri(URI.create(storeUrl+pageNum.toString()))
                                // .POST(formData(values))
                                // .header("Content-Type", "application/json")
                                .header("apiKey", apiKey)
                                .build()

                val response = client.send(request, HttpResponse.BodyHandlers.ofString())
                jsonStr = response.body()
                // println(jsonStr)
                if(jsonStr.get(0)=='['){
                    insertData(jsonStr)
                    pageNum++
                }
                
            }
            
        }
        if (loadMode=="file"){
            var resource:File = File("response.json");
			jsonStr = String(Files.readAllBytes(resource.toPath()));

            insertData(jsonStr)
        }

        
    }

    fun insertData(jsonStr:String){
        val objectMapper: ObjectMapper = ObjectMapper()
        val rootNode: JsonNode = objectMapper.readTree(jsonStr)

        for (objNode: JsonNode in rootNode) {
            val jObj1: JSONObject = JSONObject(objNode.toString())
            val store: Store = objectMapper.readValue(jObj1.toString(), Store::class.java)
            println(store.id)
            val sfind: Store = findOne(store.id)
            if (sfind == null) {
                create(store)
            } else {
                update(store)
            }
        }
    }
}