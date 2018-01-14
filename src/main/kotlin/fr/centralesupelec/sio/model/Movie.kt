package fr.centralesupelec.sio.model

import java.util.*
import javax.persistence.*

/**
 * An entity class for a movie.
 */
@Entity
data class Movie(
        val title: String?,
        @Enumerated(EnumType.STRING)
        val genres:  MovieGenre?,
        @ManyToMany
        var directors: Set<Person>?,
        @ManyToMany
        val actors: Set<Person>?,
        @Id @GeneratedValue
        val id: Long? = -1
)