package com.example.test_amce.controller



import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import com.example.test_amce.entity.*
import com.example.test_amce.service.*



@RestController
@CrossOrigin
@RequestMapping("/api/seasons")
class SeasonController {

    @Autowired
    lateinit var seasonService: SeasonService

    @GetMapping
    fun getSeasons(): List<Season> =
            seasonService.findAll()

    @GetMapping("{id}")
    fun getSeason(@PathVariable id: Int): Season =
            seasonService.findOne(id)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createSeason(@RequestBody store: Season) =
            seasonService.create(store)

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteSeason(@PathVariable id: Int) {
        seasonService.delete(id)
    }

    @GetMapping("/load")
    fun loadSeason() {
        seasonService.load()
    }

}
