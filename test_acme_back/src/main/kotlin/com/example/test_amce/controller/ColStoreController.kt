package com.example.test_amce.controller



import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import com.example.test_amce.entity.*
import com.example.test_amce.service.*



@RestController
@CrossOrigin
@RequestMapping("/api/collection_stores")
class ColColStoreController
 {

    @Autowired
    lateinit var colstoreService: ColStoreService

    @GetMapping
    fun getColStores(@RequestParam page: Int): List<ColStore> =
            colstoreService.findAll(page)

    @GetMapping("{id}")
    fun getColStore(@PathVariable id: Int): ColStore =
            colstoreService.findOne(id)

    @GetMapping("/search")
    fun getColStoresByName(@RequestParam name: String): List<ColStore> =
            colstoreService.findByName("%"+name+"%")

    @GetMapping("/getCount")
    fun getCount(): Int =
                        colstoreService.getCount()

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteColStore(@PathVariable id: Int) {
        colstoreService.delete(id)
    }
}
