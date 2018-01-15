package fr.centralesupelec.sio.controller

import fr.centralesupelec.sio.data.MoviesRepository
import fr.centralesupelec.sio.model.Movie
import fr.centralesupelec.sio.model.MovieGenre
import fr.centralesupelec.sio.model.Person
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Example
import org.springframework.data.domain.ExampleMatcher
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.contains
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client
import org.springframework.web.bind.annotation.*

@EnableOAuth2Client
@RestController
@RequestMapping("/movies")
@Api(value="movies", description="Operations pertaining to movies")
class MovieController(private val moviesRepository: MoviesRepository) {

    @Autowired
    lateinit var personController: PersonController

    @ApiOperation(value = "View the list of all movies", produces = "application/json")
    @ApiResponses(value = [ApiResponse(code = 200, message = "Successfully retrieved list"),
        ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        ApiResponse(code = 404, message = "The resource you were trying to reach is not found")])
    @GetMapping("/list")
    fun list() = moviesRepository.findAll()

    @ApiOperation(value = "find a movie by his ID", produces = "application/json")
    @ApiResponses(value = [ApiResponse(code = 200, message = "Successfully retrieved list"),
        ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        ApiResponse(code = 404, message = "The resource you were trying to reach is not found")])
    @GetMapping("/show/{id}")
    fun show(@PathVariable id: Long) = moviesRepository.findOne(id)

    @ApiOperation(value = "Movie genre list", produces = "application/json")
    @ApiResponses(value = [ApiResponse(code = 200, message = "Successfully retrieved list"),
        ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        ApiResponse(code = 404, message = "The resource you were trying to reach is not found")])
    @GetMapping("/genres")
    fun getGenres(): Set<MovieGenre> = MovieGenre.values().toSet()

    @ApiOperation(value = "movie search by many criteria", produces = "application/json")
    @ApiResponses(value = [ApiResponse(code = 200, message = "Successfully retrieved list"),
        ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        ApiResponse(code = 404, message = "The resource you were trying to reach is not found")])
    @GetMapping("/find")
    fun find(@RequestParam(value = "title", required = false) title: String?,
               @RequestParam(value = "genre", required = false) genre: String?,
               @RequestParam(value = "directors", required = false) directors:  List<Long>?,
               @RequestParam(value = "actors", required = false) actors: List<Long>?,
               @RequestParam(value = "offset", required = false) offset: Long?,
               @RequestParam(value = "limit", required = false) limit: Long?): List<Movie> {

        // configuration of query by example matcher declaration
        val matcher = ExampleMatcher.matching()
                .withMatcher("title", contains().ignoreCase())

        val setDirectors = if (directors != null && directors.isNotEmpty()) personController.getDirectors(directors) else setOf<Person>()
        val setActors = if (actors != null && actors.isNotEmpty()) personController.getActors(actors) else setOf<Person>()
        val movieGenre = if (genre == null) genre else MovieGenre.valueOf(genre.toUpperCase())

        // First step search the list of films discriminated by their title and genre with a query by example
        var movies = moviesRepository.findAll(Example.of(Movie(title, movieGenre, null, null, null), matcher)).toList()

        // Second step filter the list by directors and actors if the lists are not empty
        movies = if (setDirectors.isNotEmpty()) movies.filter { it.directors!!.containsAll(setDirectors) } else movies
        movies = if (setActors.isNotEmpty()) movies.filter { it.actors!!.containsAll(setActors) } else movies

        // Third step create the sublist in terms of offset and limit parameters
        movies = if (offset != null && offset < movies.size) movies.subList(offset.toInt(), movies.lastIndex) else movies
        movies = if (limit != null && limit < movies.size) movies.subList(0, limit.toInt()) else movies

        return movies
    }
}
