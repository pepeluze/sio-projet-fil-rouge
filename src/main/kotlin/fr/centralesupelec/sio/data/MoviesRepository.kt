package fr.centralesupelec.sio.data

import fr.centralesupelec.sio.model.Movie
import org.springframework.data.domain.Example
import org.springframework.data.domain.ExampleMatcher
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.QueryByExampleExecutor

/**
 * A data repository to expose movie-related entities.
 */
interface MoviesRepository  : CrudRepository<Movie, Long>, QueryByExampleExecutor<Movie>