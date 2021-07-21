package com.example.test_amce.controller



import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import com.example.test_amce.entity.*
import com.example.test_amce.service.*



@RestController
@CrossOrigin
@RequestMapping("/api/stores")
class StoreController {

    @Autowired
    lateinit var storeService: StoreService

    @GetMapping
    fun getStores(): List<Store> =
            storeService.findAll()

    @GetMapping("{id}")
    fun getStore(@PathVariable id: Int): Store =
            storeService.findOne(id)

    @GetMapping("/search")
    fun getStoresByName(@RequestParam name: String): List<Store> =
            storeService.findByName("%"+name+"%")

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createStore(@RequestBody season: Store) =
            storeService.create(season)

    @PostMapping("/update")
    fun updateStore(@RequestBody store: Store) {
        storeService.updateName(store)
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteStore(@PathVariable id: Int) {
        storeService.delete(id)
    }

    @GetMapping("/load")
    fun loadStore() {
        storeService.load()
    }

}
