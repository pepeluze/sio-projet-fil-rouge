package fr.centralesupelec.sio.controller

import fr.centralesupelec.sio.data.MoviesRepository
import fr.centralesupelec.sio.data.PersonRepository
import fr.centralesupelec.sio.model.Person
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@EnableOAuth2Client
@RestController
@RequestMapping("/persons")
@Api(value="persons", description="Operations pertaining to persons (actors and directors)")
class PersonController(private val personRepository: PersonRepository) {

    @Autowired
    lateinit var moviesRepository: MoviesRepository

    @ApiOperation(value = "Directors view by list of there IDs", produces = "application/json")
    @ApiResponses(value = [(ApiResponse(code = 200, message = "Successfully retrieved list")),
        (ApiResponse(code = 401, message = "You are not authorized to view the resource")),
        (ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden")),
        (ApiResponse(code = 404, message = "The resource you were trying to reach is not found"))])
    @GetMapping("/directors/find")
    fun getDirectors(@RequestParam(value = "directors", required = false) directors: List<Long>?): Set<Person> {
        return getPersons(directors)
    }

    @ApiOperation(value = "Actors view by list of there IDs", produces = "application/json")
    @ApiResponses(value = [ApiResponse(code = 200, message = "Successfully retrieved list"),
        ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        ApiResponse(code = 404, message = "The resource you were trying to reach is not found")])
    @GetMapping("/actors/find")
    fun getActors(@RequestParam(value = "actors", required = false) actors: List<Long>?): Set<Person> {
        return getPersons(actors)
    }

    @ApiOperation(value = "View the list of all directors", produces = "application/json")
    @ApiResponses(value = [ApiResponse(code = 200, message = "Successfully retrieved list"),
        ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        ApiResponse(code = 404, message = "The resource you were trying to reach is not found")])
    @GetMapping("/directors/list")
    fun getDirectors(): Set<Person> {
        var directors = setOf<Person>()
        moviesRepository.findAll().forEach { directors += it.directors!! }
        return directors
    }

    @ApiOperation(value = "View the list of all actors", produces = "application/json")
    @ApiResponses(value = [ApiResponse(code = 200, message = "Successfully retrieved list"),
        ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        ApiResponse(code = 404, message = "The resource you were trying to reach is not found")])
    @GetMapping("/actors/list")
    fun getActors(): Set<Person> {
        var actors = setOf<Person>()
        moviesRepository.findAll().forEach { actors += it.actors!! }
        return actors
    }

    fun getPersons(persons: List<Long>?): Set<Person> = personRepository.findAll(persons).toHashSet()
}