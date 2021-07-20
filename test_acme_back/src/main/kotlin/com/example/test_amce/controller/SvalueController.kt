package com.example.test_amce.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import com.example.test_amce.entity.*
import com.example.test_amce.service.*

@RestController
@CrossOrigin
@RequestMapping("/api/svalues")
class SvalueController {

    @Autowired
    lateinit var svalueService: SvalueService

    @GetMapping
    fun getSvalues(): List<Svalue> =
            svalueService.findAll()

    @GetMapping("{id}")
    fun getSvalue(@PathVariable id: Int): Svalue =
            svalueService.findOne(id)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createSvalue(@RequestBody svalue: Svalue) =
            svalueService.create(svalue)

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteSvalue(@PathVariable id: Int) {
        svalueService.delete(id)
    }

    @GetMapping("/load")
    fun loadSvalue() {
        svalueService.load()
    }

}
